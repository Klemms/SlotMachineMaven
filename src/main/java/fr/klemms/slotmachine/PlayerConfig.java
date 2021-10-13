package fr.klemms.slotmachine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.klemms.slotmachine.exceptioncollector.ExceptionCollector;

public class PlayerConfig {

	public static SMPlayerConfig getOrCreateSMPlayerConfig(OfflinePlayer player, SlotMachine machine, boolean createIfMissing) {
		PlayerConfig plc = getPlayerConfig(player, createIfMissing);
		
		return plc.getMachineConfig(machine.getMachineUUID(), createIfMissing);
	}
	
	public static SMPlayerConfig getSMPlayerConfig(OfflinePlayer player, SlotMachine machine) {
		PlayerConfig plc = getPlayerConfig(player, false);
		
		if (plc != null) {
			return plc.getMachineConfig(machine.getMachineUUID(), false);
		}
		
		return null;
	}
	
	public static PlayerConfig getPlayerConfig(OfflinePlayer player, boolean createIfMissing) {
		PlayerConfig plc = SlotPlugin.playerConfigs.get(player.getUniqueId());
		
		if (plc == null && createIfMissing) {
			plc = new PlayerConfig(player.getUniqueId());
			SlotPlugin.playerConfigs.put(player.getUniqueId(), plc);
			PlayerConfig.writeSpecificPlayerConfig(plc);
		}
		
		return plc;
	}
	public static Collection<PlayerConfig> getPlayerConfigs() {
		return SlotPlugin.playerConfigs.values();
	}

	public static void loadPlayerConfig() {
		SlotPlugin pl = SlotPlugin.pl;
		pl.getLogger().log(Level.INFO, "Loading player files...");
		SlotPlugin.playerConfigs = new HashMap<UUID, PlayerConfig>();
		
		if (Files.exists(pl.getDataFolder().toPath().resolve("players"))) {
			Collection<File> detectedFiles = FileUtils.listFiles(pl.getDataFolder().toPath().resolve("players").toFile(), new SuffixFileFilter(".yml"), null);
			pl.getLogger().log(Level.INFO, "Detected " + detectedFiles.size() + " player files");
			
			for (File file : detectedFiles) {
				YamlConfiguration ymlFile = YamlConfiguration.loadConfiguration(file);
				
				if (ymlFile.isSet("playerUUID")) {
					PlayerConfig plc = new PlayerConfig(UUID.fromString(ymlFile.getString("playerUUID")));
					
					if (ymlFile.contains("machines")) {
						for (String key : ymlFile.getConfigurationSection("machines").getKeys(false)) {
							if (ymlFile.isSet("machines." + key + ".cooldown") && ymlFile.getInt("machines." + key + ".cooldown") > 0) {
								plc.addMachineConfig(UUID.fromString(key), new SMPlayerConfig(plc, ymlFile.getInt("machines." + key + ".cooldown")));
							}
						}
						
						if (plc.getMachinesConfig().size() > 0) {
							SlotPlugin.playerConfigs.put(plc.playerUUID, plc);
						} else {
							try {
								Files.delete(file.toPath());
							} catch (IOException e) {
								e.printStackTrace();
								ExceptionCollector.sendException(pl, e);
							}
						}
					}
				}
				
				try {
					
				} catch (Exception e) {
					e.printStackTrace();
					ExceptionCollector.sendException(pl, e);
				}
			}
			pl.getLogger().log(Level.INFO, "Loaded " + SlotPlugin.playerConfigs.size() + " player files");
		} else {
			pl.getLogger().log(Level.INFO, "No player file found");
		}
	}
	
	public static void writePlayerConfig() {
		
	}
	
	public static void writeSpecificPlayerConfig(PlayerConfig cfg) {
		SlotPlugin pl = SlotPlugin.pl;
		Path playersPath = pl.getDataFolder().toPath().resolve("players");

		try {
			Files.createDirectories(playersPath);
		} catch (IOException e) {
			e.printStackTrace();
			ExceptionCollector.sendException(pl, e);
			return;
		}
		
		if (Files.exists(playersPath)) {
			YamlConfiguration yamlFile = YamlConfiguration.loadConfiguration(playersPath.resolve(cfg.getPlayerUUID().toString() + ".yml").toFile());
			
			yamlFile.set("playerUUID", cfg.playerUUID.toString());
			
			boolean save = false;
			for (UUID uuid : cfg.getMachinesConfig().keySet()) {
				yamlFile.set("machines." + uuid.toString() + ".cooldown", cfg.getMachinesConfig().get(uuid).getCooldown());
				
				if (cfg.getMachinesConfig().get(uuid).getCooldown() >= 60) {
					save = true;
				}
			}

			try {
				if (save)
					yamlFile.save(playersPath.resolve(cfg.getPlayerUUID().toString() + ".yml").toFile());
				else if (Files.exists(playersPath.resolve(cfg.getPlayerUUID().toString() + ".yml"))) {
					Files.delete(playersPath.resolve(cfg.getPlayerUUID().toString() + ".yml"));
				}
			} catch (IOException e) {
				e.printStackTrace();
				ExceptionCollector.sendException(SlotPlugin.pl, e);
			}
		}
	}
	
	private UUID playerUUID;
	private HashMap<UUID, SMPlayerConfig> machinesConfig;
	
	public PlayerConfig(UUID playerUUID) {
		this.playerUUID = playerUUID;
		this.machinesConfig = new HashMap<UUID, SMPlayerConfig>();
	}

	public UUID getPlayerUUID() {
		return playerUUID;
	}

	public HashMap<UUID, SMPlayerConfig> getMachinesConfig() {
		return machinesConfig;
	}
	
	/**
	 * Won't saveToDisk
	 * @param machineUUID
	 * @param createIfMissing
	 * @return
	 */
	public SMPlayerConfig getMachineConfig(UUID machineUUID, boolean createIfMissing) {
		SMPlayerConfig cfg = machinesConfig.get(machineUUID);
		
		if (cfg == null && createIfMissing) {
			cfg = new SMPlayerConfig(this);
			this.machinesConfig.put(machineUUID, cfg);
		}
			
		return cfg;
	}
	
	public void addMachineConfig(UUID machineUUID, SMPlayerConfig smpc) {
		this.machinesConfig.put(machineUUID, smpc);
	}
	
	public void saveToDisk() {
		PlayerConfig.writeSpecificPlayerConfig(this);
	}
}
