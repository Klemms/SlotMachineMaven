package fr.klemms.slotmachine;

import com.bencodez.votingplugin.VotingPluginHooks;
import fr.klemms.slotmachine.Issue.IssueType;
import fr.klemms.slotmachine.MachineItem.RewardType;
import fr.klemms.slotmachine.clipboard.Clipboards;
import fr.klemms.slotmachine.commands.*;
import fr.klemms.slotmachine.events.PluginListener;
import fr.klemms.slotmachine.exceptioncollector.ExceptionCollector;
import fr.klemms.slotmachine.fr.minuskube.inv.InventoryManager;
import fr.klemms.slotmachine.metrics.Metrics;
import fr.klemms.slotmachine.translation.Language;
import fr.klemms.slotmachine.utils.ItemStackUtil;
import fr.klemms.slotmachine.utils.Util;
import fr.klemms.slotmachine.utils.sounds.*;
import me.realized.tokenmanager.api.TokenManager;
import net.milkbowl.vault.economy.Economy;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.black_ixx.playerpoints.PlayerPoints;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.logging.Level;

public class SlotPlugin extends JavaPlugin {

	public static final String MC_FOR = "Spigot 1.16/1.17/1.18/1.19/1.20";
	public static final ItemStack DEFAULT_TOKEN = ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(Material.GOLD_NUGGET, 1), ChatContent.GOLD + "Token"), Collections.singletonList(ChatContent.AQUA + ChatContent.ITALIC + "Default Slot Machine Token"));

	public static volatile SlotPlugin pl;
	public static Economy econ = null;
	public static VotingPluginHooks votingPlugin = null;
	public static final String BRANCH = "release";
	public static final int VERSION = 81;
	public static Metrics metrics;
	public static boolean supportEnding = false;
	public static String supportMessage = "";
	public static boolean shouldSaveMachinesToDisk = true;
	public static boolean suspendSaving = false;

	public static HashMap<Sound, Material> soundMaterialMap;

	public static InventoryManager invManager;

	public static boolean isGamePointsEnabled = false;
	public static boolean isCitizensEnabled = false;

	public static boolean oldTokenManagerWorks = false;
	public static TokenManager tokenManager;

	public static boolean isPlaceholderAPIEnabled = false;

	public static PlayerPointsAPI playerPointsAPI;

	protected static HashMap<UUID, PlayerConfig> playerConfigs;

	public static List<Issue> issues;

	public static HashMap<UUID, List<ItemStack>> playerRewardsQueue;

	@Override
	public void onEnable() {
		pl = this;
		issues = new ArrayList<Issue>();
		Config.quickReload = true;

		this.getLogger().log(Level.INFO, "Detected Minecraft version " + Util.getMCVersion());
		this.getLogger().log(Level.INFO, "Slot Machine version " + this.getDescription().getVersion() + " (" + VERSION + ") for " + MC_FOR);
		if (Util.getMCVersion().startsWith("1.7") || Util.getMCVersion().startsWith("1.8") || Util.getMCVersion().startsWith("1.9") ||
				Util.getMCVersion().startsWith("1.10") || Util.getMCVersion().startsWith("1.11") || Util.getMCVersion().startsWith("1.12")
				|| Util.getMCVersion().startsWith("1.13") || Util.getMCVersion().startsWith("1.14") || Util.getMCVersion().startsWith("1.15")) {
			this.getLogger().log(Level.SEVERE, "This version of Slot Machine can only work on Spigot 1.16 or greater");
			Config.backupMachinesOnPluginUnload = false;
			Bukkit.getPluginManager().disablePlugin(this);
			return;
		}

		invManager = new InventoryManager(this);
		invManager.init();

		if (Util.getMCVersion().startsWith("1.16")) {
			this.getLogger().log(Level.INFO, "Using 1.16 Sound Mappings");
			SoundToMaterialList_116.initList();
		} else if(Util.getMCVersion().startsWith("1.17")) {
			this.getLogger().log(Level.INFO, "Using 1.17 Sound Mappings");
			SoundToMaterialList_117.initList();
		} else if(Util.getMCVersion().startsWith("1.18")) {
			this.getLogger().log(Level.INFO, "Using 1.18 Sound Mappings");
			SoundToMaterialList_118.initList();
		} else if(Util.getMCVersion().startsWith("1.19")) {
			this.getLogger().log(Level.INFO, "Using 1.19 Sound Mappings");
			SoundToMaterialList_119.initList();
		} else {
			this.getLogger().log(Level.INFO, "Using 1.20 Sound Mappings");
			SoundToMaterialList_120.initList();
		}

		isCitizensEnabled = Bukkit.getPluginManager().isPluginEnabled("Citizens");
		if (isCitizensEnabled)
			this.getLogger().log(Level.INFO, "Enabled Citizens 2 support");

		if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null)
			isPlaceholderAPIEnabled = true;

		if (Bukkit.getPluginManager().isPluginEnabled("PlayerPoints"))
			playerPointsAPI = PlayerPoints.getInstance().getAPI();

		Setup.setupLanguages(this);
		try {
			Config.registerConfig(this);
			Config.readConfig(this);
			if (Config.enableLanguageOTAUpdates) {
				Setup.setupOTALanguages(this);
			}
			SlotPlugin.readTokens();
			SlotPlugin.writeTokens();
			Config.loadMachines(this);
			PlayerConfig.loadPlayerConfig();
			Clipboards.setupClipboards();
		} catch(Exception e) {
			e.printStackTrace();
			ExceptionCollector.sendException(this, e);
		}

		Objects.requireNonNull(getCommand("slotmachine")).setExecutor(new CommandSlotMachine());
		Objects.requireNonNull(getCommand("openmachine")).setExecutor(new CommandOpenMachine());
		Objects.requireNonNull(getCommand("tpmachine")).setExecutor(new CommandTPMachine());
		Objects.requireNonNull(getCommand("givetokens")).setExecutor(new CommandGiveTokens());
		Objects.requireNonNull(getCommand("slotmachineversion")).setExecutor(new CommandSlotMachineVersion());
		Objects.requireNonNull(getCommand("slotmachinetoken")).setExecutor(new CommandSlotMachineTokens());
		Objects.requireNonNull(getCommand("smsavetodisk")).setExecutor(new CommandSMSaveToDisk());
		Objects.requireNonNull(getCommand("smcooldown")).setExecutor(new CommandCooldown());
		Objects.requireNonNull(getCommand("smreload")).setExecutor(new CommandReloadMachines());
		Objects.requireNonNull(getCommand("smupdatelanguages")).setExecutor(new CommandUpdateLanguages());
		Objects.requireNonNull(getCommand("smbackup")).setExecutor(new CommandSlotMachineBackup());

		playerRewardsQueue = new HashMap<UUID, List<ItemStack>>();

		Setup.setupEconomy(this);
		Setup.setupVotingPlugin(this);

		this.getServer().getPluginManager().registerEvents(new PluginListener(), this);

		if(this.getServer().getPluginManager().getPlugin("GamePoints") != null) {
			SlotPlugin.isGamePointsEnabled = true;
		}

		if(this.getServer().getPluginManager().getPlugin("TokenManager") != null) {
			try {
				Class.forName("me.realized.tm.api.TMAPI");
				SlotPlugin.oldTokenManagerWorks = true;
			} catch (ClassNotFoundException e) {
				tokenManager = (TokenManager) this.getServer().getPluginManager().getPlugin("TokenManager");
			}
		}

		try {
			new KlemmsUpdate(this, "SlotMachine", VERSION, BRANCH);
		} catch (IOException e) {
			e.printStackTrace();
		}

		metrics = new Metrics(this, 1065);

		metrics.addCustomChart(new Metrics.SimplePie("internal_version", new Callable<String>() {

			@Override
			public String call() throws Exception {
				return String.valueOf(SlotPlugin.VERSION);
			}

		}));

		metrics.addCustomChart(new Metrics.SingleLineChart("times_used", new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				return countUsage();
			}

			private int countUsage() {
				List<SlotMachine> machines = SlotMachine.getSlotMachines();
				int result = 0;

				for(SlotMachine machine : machines)
					result += machine.getTimesUsed();

				return result;
			}

		}));

		metrics.addCustomChart(new Metrics.AdvancedPie("slot_machine_type", new Callable<Map<String, Integer>>() {

			@Override
			public Map<String, Integer> call() throws Exception {
				Map<String, Integer> valueMap = new HashMap<String, Integer>();

				valueMap.put("Block", countBlockMachines());
				valueMap.put("Entity", countEntityMachines());

				return valueMap;
			}

			private int countBlockMachines() {
				return SlotMachine.getAllSlotMachineBlocks().size();
			}

			private int countEntityMachines() {
				return SlotMachine.getAllSlotMachineEntities().size();
			}

		}));

		metrics.addCustomChart(new Metrics.SimplePie("slot_machine_language", () -> {
			if (Language.isValidLanguage(Config.language)) {
				return Config.language;
			} else {
				return "custom";
			}
		}));

		metrics.addCustomChart(new Metrics.SimplePie("slot_machine_language_custom", () -> Config.language));

		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
			Iterator<Issue> iss = issues.iterator();

			while (iss.hasNext()) {
				Issue issue = iss.next();

				boolean remove = false;
				for (Player player : Bukkit.getOnlinePlayers()) {
					if (player.isOp()) {
						player.sendMessage(ChatContent.DARK_RED + ChatContent.BOLD + "[Slot Machine] " + ChatContent.RED
								+ (issue.amount > 1 ? ChatContent.ITALIC + "(" + issue.amount + "x) " + ChatContent.RED : "") + issue.getLocalizedTitle());
						player.sendMessage(ChatContent.DARK_RED + ChatContent.BOLD + " - " + ChatContent.RED + issue.description);
						remove = true;
					}
				}

				if (remove)
					iss.remove();
			}
		}, 10 * 20, 30 * 20);

		// Cooldown
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
			for (PlayerConfig plc : SlotPlugin.playerConfigs.values()) {
				for (SMPlayerConfig smpc : plc.getMachinesConfig().values()) {
					if (smpc.getCooldown() > 0) {
						smpc.setCooldown(smpc.getCooldown() - 1);
					}
				}
			}
		}, 1 * 20, 1 * 20);

		// Saving
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
			if (!suspendSaving) {
				saveCooldownsToDisk();
				if (shouldSaveMachinesToDisk) {
					saveMachinesToDisk();
				}
			}
		}, 10 * 20, 10 * 20);

		// Items Giving
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
			if (playerRewardsQueue.isEmpty()) {
				return;
			}

			List<UUID> toRemove = new ArrayList<UUID>();
			for (UUID uuid : playerRewardsQueue.keySet()) {
				Player player = Bukkit.getPlayer(uuid);
				if (player == null) {
					continue;
				}

				List<ItemStack> items = playerRewardsQueue.get(uuid);

				if (player.getInventory().firstEmpty() < 0) {
					player.sendMessage(ChatContent.YELLOW + "[Slot Machine] " + ChatContent.translateColorCodes(Language.translate("slotmachine.rewards.waiting").replace("%amount%", String.valueOf(items.size()))));
					continue;
				}

				if (items.size() == 0) {
					toRemove.add(uuid);
					continue;
				}

				Iterator<ItemStack> it = items.iterator();
				boolean updateInventory = false;
				while(it.hasNext()) {
					ItemStack is = it.next();

					if (player.getInventory().firstEmpty() >= 0) {
						player.getInventory().addItem(is);
						updateInventory = true;
						it.remove();
					} else {
						break;
					}
				}

				if (updateInventory) {
					player.updateInventory();
				}
			}

			for (UUID uuid : toRemove) {
				playerRewardsQueue.remove(uuid);
			}
		}, 10 * 20, 10 * 20);
	}

	@Override
	public void onDisable() {
		saveCooldownsToDisk();
		saveMachinesToDisk();

		if(Config.backupMachinesOnPluginUnload) {
			try {
				this.getLogger().log(Level.INFO, "Removing old backup");
				FileUtils.deleteDirectory(this.getDataFolder().toPath().resolve("machinesLastBackup").toFile());
			} catch (IOException e) {
				this.getLogger().log(Level.INFO, "Removing old backup failed");
				e.printStackTrace();
				Issue.newIssue(IssueType.MACHINES_BACKUP, "Removing old backups failed, see your server logs for the related exception.", true);
				ExceptionCollector.sendException(SlotPlugin.pl, e);
			}
			try {
				this.getLogger().log(Level.INFO, "Creating a backup of /machines to /machinesLastBackup");
				FileUtils.copyDirectory(this.getDataFolder().toPath().resolve("machines").toFile(), this.getDataFolder().toPath().resolve("machinesLastBackup").toFile(), false);
			} catch (IOException e) {
				this.getLogger().log(Level.INFO, "Backup creation failed");
				e.printStackTrace();
				Issue.newIssue(IssueType.MACHINES_BACKUP, "Creating a backup for your machines failed, see your server logs for the related exception. Your disk may be FULL.", true);
				ExceptionCollector.sendException(SlotPlugin.pl, e);
			}
		}
	}

	public static void saveCooldownsToDisk() {
		for (PlayerConfig plc : SlotPlugin.playerConfigs.values()) {
			boolean write = false;
			for (SMPlayerConfig smpc : plc.getMachinesConfig().values()) {
				if (smpc.changed)
					write = true;
				smpc.changed = false;
			}
			if (write)
				PlayerConfig.writeSpecificPlayerConfig(plc);
		}
	}

	public static void saveToDisk() {
		shouldSaveMachinesToDisk = true;
	}

	public static void saveMachinesToDisk() {
		try {
			Files.createDirectories(pl.getDataFolder().toPath().resolve("machines"));
		} catch (IOException e) {
			e.printStackTrace();
			ExceptionCollector.sendException(SlotPlugin.pl, e);
			return;
		}

		if(Files.exists(pl.getDataFolder().toPath().resolve("machines"))) {
			for(SlotMachine slotMachine : SlotMachine.getSlotMachines()) {
				YamlConfiguration yamlFile = YamlConfiguration.loadConfiguration(pl.getDataFolder().toPath().resolve("machines").resolve(slotMachine.getMachineUUID().toString() + ".yml").toFile());

				yamlFile.set("saveTime", DateFormat.getDateTimeInstance().format(new Date()).toString());
				yamlFile.set("machineType", slotMachine.getSlotMachineType().toString());
				if(slotMachine.getSlotMachineType() == SlotMachineType.ENTITY || slotMachine.getSlotMachineType() == SlotMachineType.ENTITY_LINK) {
					yamlFile.set("entityUID", ((SlotMachineEntity)slotMachine).getEntityUUID().toString());
					yamlFile.set("isCitizensNPC", slotMachine.isCitizensNPC());

					if (slotMachine.getSlotMachineType() == SlotMachineType.ENTITY_LINK) {
						yamlFile.set("linkTo", ((SlotMachineEntityLink)slotMachine).getLinkTo().toString());
					}
				}
				if(slotMachine.getSlotMachineType() == SlotMachineType.BLOCK || slotMachine.getSlotMachineType() == SlotMachineType.BLOCK_LINK) {
					yamlFile.set("worldUID", ((SlotMachineBlock)slotMachine).getWorldUID().toString());
					yamlFile.set("blockX", ((SlotMachineBlock)slotMachine).getBlockX());
					yamlFile.set("blockY", ((SlotMachineBlock)slotMachine).getBlockY());
					yamlFile.set("blockZ", ((SlotMachineBlock)slotMachine).getBlockZ());
					yamlFile.set("locked", ((SlotMachineBlock)slotMachine).isLocked());

					if (slotMachine.getSlotMachineType() == SlotMachineType.BLOCK_LINK) {
						yamlFile.set("linkTo", ((SlotMachineBlockLink)slotMachine).getLinkTo().toString());
					}
				}
				yamlFile.set("machineUUID", slotMachine.getMachineUUID().toString());
				if (slotMachine.getSlotMachineType() != SlotMachineType.ENTITY_LINK && slotMachine.getSlotMachineType() != SlotMachineType.BLOCK_LINK) {
					yamlFile.set("guiPermission", slotMachine.getGuiPermission());
					yamlFile.set("slotMachineName", slotMachine.getSlotMachineName());
					yamlFile.set("visualType", slotMachine.getVisualType().toString());
					yamlFile.set("priceType", slotMachine.getPriceType().toString());
					yamlFile.set("tokenIdentifier", slotMachine.getTokenIdentifier());
					yamlFile.set("pullPrice", slotMachine.getPullPrice());
					yamlFile.set("chanceToWin", slotMachine.getChanceToWin());
					yamlFile.set("secondsBeforePrize", slotMachine.getSecondsBeforePrize());
					yamlFile.set("winMessage", slotMachine.getWinMessage());
					yamlFile.set("hasWinMessage", slotMachine.hasWinMessage());
					yamlFile.set("lossMessage", slotMachine.getLossMessage());
					yamlFile.set("hasLossMessage", slotMachine.hasLossMessage());
					yamlFile.set("displayItemNameInChat", slotMachine.isDisplayWonItemInChat());
					yamlFile.set("broadcastWonItem", slotMachine.shouldBroadcastWonItem());
					yamlFile.set("leverTitle", slotMachine.getLeverTitle());
					yamlFile.set("leverDescription", slotMachine.getLeverDescription());
					yamlFile.set("customLever", slotMachine.isLeverCustom());
					yamlFile.set("affectedByLuck", slotMachine.isAffectedByLuck());
					yamlFile.set("allowContentPreview", slotMachine.allowContentPreview());
					yamlFile.set("itemWeightOnPreview", slotMachine.showItemWeightOnPreview());
					yamlFile.set("itemChanceOnPreview", slotMachine.showChanceOfItemOnPreview());
					yamlFile.set("timesUsed", slotMachine.getTimesUsed());
					yamlFile.set("playMode", slotMachine.getPlayMode().toString());
					yamlFile.set("cooldown", slotMachine.getCooldown());

					yamlFile.set("backgroundItem", slotMachine.getBackgroundItem());
					yamlFile.set("emphasisItem", slotMachine.getEmphasisItem());
					yamlFile.set("leverItem", slotMachine.getLeverItem());
					yamlFile.set("itemListItem", slotMachine.getItemListItem());

					yamlFile.set("machineOpeningSound", slotMachine.getMachineOpeningSound().toString());
					yamlFile.set("leverSound", slotMachine.getLeverSound().toString());
					yamlFile.set("slotmachineSpinSound", slotMachine.getSlotmachineSpinSound().toString());
					yamlFile.set("csgoSpinSound", slotMachine.getCsgoSpinSound().toString());
					yamlFile.set("winSound", slotMachine.getWinSound().toString());
					yamlFile.set("lossSound", slotMachine.getLossSound().toString());
					yamlFile.set("errorSound", slotMachine.getErrorSound().toString());

					yamlFile.set("itemCount", null);
					yamlFile.set("items", null);

					for (int b = 0; b < slotMachine.getSlotMachineItems().size(); b++) {
						MachineItem item = slotMachine.getSlotMachineItems().get(b);

						yamlFile.set("items." + b + ".reward", null);
						yamlFile.set("items." + b + ".rewardType", null);

						yamlFile.set("items." + b + ".item", item.getItemStack());
						yamlFile.set("items." + b + ".weight", item.getWeight());

						yamlFile.set("items." + b + ".rewards", null);
						for (int c = 0; c < item.getRewards().size(); c++) {
							MachineItem.Reward reward = item.getRewards().get(c);
							yamlFile.set("items." + b + ".rewards." + c + ".type", reward.rewardType.toString());
							if (reward.rewardType == RewardType.ITEM) {
								yamlFile.set("items." + b + ".rewards." + c + ".item", reward.itemReward);
								yamlFile.set("items." + b + ".rewards." + c + ".command", null);
							} else if (reward.rewardType == RewardType.COMMAND) {
								yamlFile.set("items." + b + ".rewards." + c + ".item", null);
								yamlFile.set("items." + b + ".rewards." + c + ".command", reward.commandReward);
							}
						}

						yamlFile.set("items." + b + ".stats.timesWon", item.itemStats.timesWon);
					}
				}
				try {
					String fileName = slotMachine.getMachineUUID().toString() + ".yml";

					if (slotMachine.getLastFileName().length() > 0 && slotMachine.getLastFileName().endsWith(".yml")) {
						try {
							UUID.fromString(StringUtils.remove(slotMachine.getLastFileName(), ".yml"));
						} catch (Exception e) {
							fileName = slotMachine.getLastFileName();
						}
						Files.deleteIfExists(pl.getDataFolder().toPath().resolve("machines").resolve(slotMachine.getLastFileName()));
					}

					yamlFile.save(pl.getDataFolder().toPath().resolve("machines").resolve(fileName).toFile());
				} catch (IOException e) {
					e.printStackTrace();
					ExceptionCollector.sendException(SlotPlugin.pl, e);
				}
			}
			shouldSaveMachinesToDisk = false;
			return;
		}
		pl.getLogger().log(Level.SEVERE, "Couldn't save Machines ! Please contact the developper");
	}

	public static void writeTokens() {
		YamlConfiguration yamlFile = YamlConfiguration.loadConfiguration(pl.getDataFolder().toPath().resolve("tokens.yml").toFile());

		yamlFile.set("tokenCount", Config.tokens.size());
		yamlFile.set("tokens", null);

		int a = 0;
		for(String identifier : Config.tokens.keySet()) {
			yamlFile.set("tokens." + a + ".identifier", identifier);
			yamlFile.set("tokens." + a + ".itemstack", Config.tokens.get(identifier));
			a++;
		}

		try {
			yamlFile.save(pl.getDataFolder().toPath().resolve("tokens.yml").toFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void readTokens() {
		pl.getLogger().log(Level.INFO, "Loading Tokens");
		if(Files.exists(pl.getDataFolder().toPath().resolve("tokens.yml"))) {
			YamlConfiguration yamlFile = YamlConfiguration.loadConfiguration(pl.getDataFolder().toPath().resolve("tokens.yml").toFile());

			int tokenCount = yamlFile.getInt("tokenCount");
			pl.getLogger().log(Level.INFO, tokenCount + " tokens found");

			for(int a = 0; a < tokenCount; a++) {
				if (yamlFile.contains("tokens." + a + ".identifier") && yamlFile.contains("tokens." + a + ".itemstack")) {
					ItemStack is = yamlFile.getItemStack("tokens." + a + ".itemstack");
					if (Objects.requireNonNull(is).getType() == Material.AIR && Objects.requireNonNull(yamlFile.getString("tokens." + a + ".identifier")).equals("default")) {
						pl.getLogger().log(Level.SEVERE, "Default token was AIR, resetting to original Slot Machine token");
						Config.tokens.put("default", SlotPlugin.DEFAULT_TOKEN);
						continue;
					}
					if (is.getType() == Material.AIR) {
						pl.getLogger().log(Level.SEVERE, "A token was AIR, this token has been removed");
						continue;
					}
					pl.getLogger().log(Level.INFO, "Loading token '" + yamlFile.getString("tokens." + a + ".identifier") + "'");
					Config.tokens.put(yamlFile.getString("tokens." + a + ".identifier"), ItemStackUtil.changeItemStackAmount(yamlFile.getItemStack("tokens." + a + ".itemstack"), 1));
				} else {
					pl.getLogger().log(Level.SEVERE, "A token could not be loaded because some of its data was missing or corrupted. This token has been removed");
				}
			}

			if(tokenCount == 0 || !Config.tokens.containsKey("default")) {
				pl.getLogger().log(Level.INFO, "Couldnt find token 'default', adding original Slot Machine token");
				Config.tokens.put("default", new ItemStack(DEFAULT_TOKEN));
			}

			pl.getLogger().log(Level.INFO, Config.tokens.size() + " tokens loaded");

		} else {
			pl.getLogger().log(Level.INFO, "First time, creating default token...");
			Config.tokens.put("default", new ItemStack(DEFAULT_TOKEN));
		}
		pl.getLogger().log(Level.INFO, "Finished loading Tokens");
	}
}
