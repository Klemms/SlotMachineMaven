package fr.klemms.slotmachine;

import fr.klemms.slotmachine.exceptioncollector.ExceptionCollector;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;

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

				try {
					if (ymlFile.isSet("playerUUID") && ymlFile.isString("playerUUID")) {
						PlayerConfig plc = new PlayerConfig(UUID.fromString(ymlFile.getString("playerUUID")));

						if (ymlFile.contains("version") && ymlFile.isInt("version")) {
							switch (ymlFile.getInt("version")) {
								case 2:
									if (ymlFile.contains("machines")) {
										ymlFile.getConfigurationSection("machines").getKeys(false).stream().forEach(
												(path) -> {
													if (!ymlFile.contains("machines." + path)) {
														return;
													}

													Map<String, Object> obj = new HashMap<>();

													ymlFile.getConfigurationSection("machines." + path).getKeys(false).forEach((key) -> obj.put(key, ymlFile.get("machines." + path + "." + key)));

													if (obj.isEmpty()) {
														return;
													}

													SMPlayerConfig smpc = SMPlayerConfig.deserialize(plc, obj);
													plc.addMachineConfig(UUID.fromString(path), smpc);
												}
										);
									}
									break;
								default:
									plc.forceSave = true;
									break;
							}
						} else {
							plc.forceSave = true;
						}

						SlotPlugin.playerConfigs.put(plc.playerUUID, plc);
					}
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

	public static boolean writePlayerConfig() {
		boolean hasWritten = false;

		for (PlayerConfig plc : SlotPlugin.playerConfigs.values()) {
			if (writeSpecificPlayerConfig(plc)) {
				hasWritten = true;
			}
		}

		return hasWritten;
	}

	public static boolean writeSpecificPlayerConfig(PlayerConfig cfg) {
		SlotPlugin pl = SlotPlugin.pl;
		Path playersPath = pl.getDataFolder().toPath().resolve("players");

		try {
			Files.createDirectories(playersPath);
		} catch (IOException e) {
			e.printStackTrace();
			ExceptionCollector.sendException(pl, e);
			return false;
		}

		if (Files.exists(playersPath)) {
			YamlConfiguration yamlFile = new YamlConfiguration();

			yamlFile.set("version", 2);
			yamlFile.set("playerUUID", cfg.playerUUID.toString());

			boolean save = false;
			for (UUID uuid : cfg.getMachinesConfig().keySet()) {
				final SMPlayerConfig smpc = cfg.getMachinesConfig().get(uuid);

				final Map<String, Object> sSMPC = smpc.serialize();

				for (String key : sSMPC.keySet()) {
					yamlFile.set("machines." + uuid.toString() + "." + key, sSMPC.get(key));
				}

				if (smpc.changed) {
					save = true;
					smpc.changed = false;
				}
			}

			try {
				if (save || cfg.forceSave) {
					yamlFile.save(playersPath.resolve(cfg.getPlayerUUID().toString() + ".yml").toFile());
					cfg.forceSave = false;
					return true;
				}
			} catch (IOException e) {
				e.printStackTrace();
				ExceptionCollector.sendException(SlotPlugin.pl, e);
				return false;
			}
		}

		return false;
	}

	private final UUID playerUUID;
	private final HashMap<UUID, SMPlayerConfig> machinesConfig;
	public boolean forceSave;

	public PlayerConfig(UUID playerUUID) {
		this.playerUUID = playerUUID;
		this.machinesConfig = new HashMap<UUID, SMPlayerConfig>();
		this.forceSave = false;
	}

	public UUID getPlayerUUID() {
		return playerUUID;
	}

	public HashMap<UUID, SMPlayerConfig> getMachinesConfig() {
		return machinesConfig;
	}

	/**
	 * Won't saveToDisk
	 *
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

	@Override
	public String toString() {
		return new StringBuilder()
				.append("{")
				.append("forceSave: ").append(this.forceSave)
				.append(",playerUUID: ").append(this.playerUUID)
				.append(",machinesConfig: ")
				.append("[")
				.append(this.machinesConfig)
				.append("]")
				.append("}")
				.toString();
	}
}
