package fr.klemms.slotmachine;

import fr.klemms.slotmachine.Issue.IssueType;
import fr.klemms.slotmachine.MachineItem.Reward;
import fr.klemms.slotmachine.MachineItem.RewardType;
import fr.klemms.slotmachine.exceptioncollector.ExceptionCollector;
import fr.klemms.slotmachine.translation.Language;
import fr.klemms.slotmachine.utils.EntityUtil;
import fr.klemms.slotmachine.utils.ItemStackUtil;
import fr.klemms.slotmachine.utils.Util;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.nio.file.Files;
import java.util.*;
import java.util.logging.Level;

public class Config {

    public static volatile int pluginVersion = 5;

    public static volatile boolean debug = false;

    public static volatile String language = "ENGLISH";

    public static volatile String noAccessDefaultString = "permission.denied";
    public static volatile String notEnoughMoneyDefaultString = "money.notenough";
    public static volatile String notEnoughTokensDefaultString = "tokens.notenough";
    public static volatile String notEnoughGamePointsDefaultString = "gamepoints.notenough";
    public static volatile String notEnoughTokensManagerDefaultString = "tokenmanager.notenough";

    public static volatile String defaultWinMessage = "&a&lYou Won !";
    public static volatile String defaultLossMessage = "&c&lYou Lost, try again !";
    public static volatile String goodLuckDefaultString = "play.goodluck";

    public static volatile boolean backupMachinesOnPluginUnload = true;
    public static volatile boolean anonymouslyReportExceptionsToDevelopper = true;
    public static volatile boolean showItemName = true;

    public static volatile double luckLevelToPercentConversion = 12.5F;
    public static volatile double badLuckLevelToPercentConversion = -12.5F;

    public static volatile HashMap<String, ItemStack> tokens = new HashMap<String, ItemStack>();

    public static volatile Material adminToolMaterial = Material.BLAZE_ROD;

    public static void registerConfig(JavaPlugin plugin) {
        if (Util.isAtLeastMC118())
            plugin.getConfig().options().setHeader(Arrays.asList("IMPORTANT : When you need to add apostrophes : ' please add TWO apostrophes, like this '' otherwise the config file will be wiped", ""));

        plugin.getConfig().addDefault("debug", debug);
        if (Util.isAtLeastMC118())
            plugin.getConfig().setComments("debug", Arrays.asList("", "Internal value"));

        plugin.getConfig().addDefault("pluginVersion", pluginVersion);
        if (Util.isAtLeastMC118())
            plugin.getConfig().setComments("pluginVersion", Arrays.asList("", "Internal value"));

        plugin.getConfig().addDefault("language", language);
        if (Util.isAtLeastMC118())
            plugin.getConfig().setComments("language", Arrays.asList("", "Language to be used", "See the 'Supports Localization' part of the plugin page on Spigot website"));

        plugin.getConfig().addDefault("backupMachinesOnPluginUnload", backupMachinesOnPluginUnload);
        if (Util.isAtLeastMC118())
            plugin.getConfig().setComments("backupMachinesOnPluginUnload", Arrays.asList("", "Whether all machines should be copied to the 'machinesLastBackup' folder when the plugin is disabled"));

        plugin.getConfig().addDefault("anonymouslyReportExceptionsToDeveloper", anonymouslyReportExceptionsToDevelopper);
        if (Util.isAtLeastMC118())
            plugin.getConfig().setComments("anonymouslyReportExceptionsToDeveloper", Arrays.asList("", "Whether to report exceptions to Slot Machine developer, no identifying information is transmitted"));

        plugin.getConfig().addDefault("permissionDenied", noAccessDefaultString);
        if (Util.isAtLeastMC118())
            plugin.getConfig().setComments("permissionDenied", Arrays.asList("", "Default permission denied message", "Default value is to check the language file with the key 'permission.denied'"));

        plugin.getConfig().addDefault("notEnoughMoney", notEnoughMoneyDefaultString);
        if (Util.isAtLeastMC118())
            plugin.getConfig().setComments("notEnoughMoney", Arrays.asList("", "Default message when a player doesn't have enough money", "Default value is to check the language file with the key 'money.notenough'"));

        plugin.getConfig().addDefault("notEnoughTokens", notEnoughTokensDefaultString);
        if (Util.isAtLeastMC118())
            plugin.getConfig().setComments("notEnoughTokens", Arrays.asList("", "Default message when a player doesn't have enough tokens (from Slot Machine)", "Default value is to check the language file with the key 'tokens.notenough'"));

        plugin.getConfig().addDefault("notEnoughGamePoints", notEnoughGamePointsDefaultString);
        if (Util.isAtLeastMC118())
            plugin.getConfig().setComments("notEnoughGamePoints", Arrays.asList("", "Default message when a player doesn't have enough GamePoints", "Default value is to check the language file with the key 'gamepoints.notenough'"));

        plugin.getConfig().addDefault("notEnoughTokensManager", notEnoughTokensManagerDefaultString);
        if (Util.isAtLeastMC118())
            plugin.getConfig().setComments("notEnoughTokensManager", Arrays.asList("", "Default message when a player doesn't have enough tokens (from TokensManager)", "Default value is to check the language file with the key 'tokenmanager.notenough'"));

        plugin.getConfig().addDefault("showItemName", showItemName);
        if (Util.isAtLeastMC118())
            plugin.getConfig().setComments("showItemName", Arrays.asList("", "Whether to the item name the player has won in the chat when they win", "Setting this to 'true' can help with players not realizing what they won"));

        plugin.getConfig().addDefault("defaultWinMessage", defaultWinMessage);
        if (Util.isAtLeastMC118())
            plugin.getConfig().setComments("defaultWinMessage", Arrays.asList("", "Default win message", "This can be customized independently for each machine"));

        plugin.getConfig().addDefault("defaultLossMessage", defaultLossMessage);
        if (Util.isAtLeastMC118())
            plugin.getConfig().setComments("defaultLossMessage", Arrays.asList("", "Default win message", "This can be customized independently for each machine"));

        plugin.getConfig().addDefault("goodLuck", goodLuckDefaultString);
        if (Util.isAtLeastMC118())
            plugin.getConfig().setComments("goodLuck", Arrays.asList("", "Default 'Good Luck' message when a player starts playing", "Default value is to check the language file with the key 'play.goodluck'", "Set it to '' (2 apostrophes) to remove it"));

        plugin.getConfig().addDefault("luckLevelToPercentConversion", luckLevelToPercentConversion);
        if (Util.isAtLeastMC118())
            plugin.getConfig().setComments("luckLevelToPercentConversion", Arrays.asList("", "How much chance to win per luck level is added to the machine's chance", "This can be negative and will affect chance accordingly"));

        plugin.getConfig().addDefault("badLuckLevelToPercentConversion", badLuckLevelToPercentConversion);
        if (Util.isAtLeastMC118())
            plugin.getConfig().setComments("badLuckLevelToPercentConversion", Arrays.asList("", "How much chance to win per luck level is added to the machine's chance", "This can be negative and will affect chance accordingly"));

        plugin.getConfig().addDefault("adminToolMaterial", adminToolMaterial.toString());
        if (Util.isAtLeastMC118())
            plugin.getConfig().setComments("adminToolMaterial", Arrays.asList("", "Which Spigot Material to use to interact with machines", "A complete list can be found here https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Material.html"));

        plugin.getConfig().set("anonymouslyReportExceptionsToDevelopper", null);

        plugin.getConfig().options().copyDefaults(true);
        plugin.saveConfig();
    }

    public static void readConfig(JavaPlugin plugin) {
        if (Language.isValidLanguage(SlotPlugin.pl.getConfig().getString("language").toUpperCase())) {
            language = SlotPlugin.pl.getConfig().getString("language").toUpperCase();
        } else {
            plugin.getLogger().log(Level.WARNING, "The config language '" + SlotPlugin.pl.getConfig().getString("language") + "' does not exist");
            language = "ENGLISH";
        }
        debug = SlotPlugin.pl.getConfig().getBoolean("debug");
        backupMachinesOnPluginUnload = SlotPlugin.pl.getConfig().getBoolean("backupMachinesOnPluginUnload");
        anonymouslyReportExceptionsToDevelopper = SlotPlugin.pl.getConfig().getBoolean("anonymouslyReportExceptionsToDeveloper");
        noAccessDefaultString = SlotPlugin.pl.getConfig().getString("permissionDenied");
        notEnoughMoneyDefaultString = SlotPlugin.pl.getConfig().getString("notEnoughMoney");
        notEnoughTokensDefaultString = SlotPlugin.pl.getConfig().getString("notEnoughTokens");
        notEnoughGamePointsDefaultString = SlotPlugin.pl.getConfig().getString("notEnoughGamePoints");
        notEnoughTokensManagerDefaultString = SlotPlugin.pl.getConfig().getString("notEnoughTokensManager");
        showItemName = SlotPlugin.pl.getConfig().getBoolean("showItemName");
        defaultWinMessage = SlotPlugin.pl.getConfig().getString("defaultWinMessage");
        defaultLossMessage = SlotPlugin.pl.getConfig().getString("defaultLossMessage");
        goodLuckDefaultString = SlotPlugin.pl.getConfig().getString("goodLuck");
        luckLevelToPercentConversion = SlotPlugin.pl.getConfig().getDouble("luckLevelToPercentConversion");
        badLuckLevelToPercentConversion = SlotPlugin.pl.getConfig().getDouble("badLuckLevelToPercentConversion");
        adminToolMaterial = Material.getMaterial(SlotPlugin.pl.getConfig().getString("adminToolMaterial"));

        if (adminToolMaterial == null)
            adminToolMaterial = Material.BLAZE_ROD;

        SlotPlugin.saveToDisk();
    }

    public static void loadMachines(JavaPlugin plugin) {
        if (plugin.getConfig().getInt("pluginVersion") == 4 || plugin.getConfig().getInt("pluginVersion") == 5) {
            plugin.getLogger().log(Level.INFO, Language.translate("config.version").replace("%version%", String.valueOf(plugin.getConfig().getInt("pluginVersion"))));
            if (Files.exists(plugin.getDataFolder().toPath().resolve("machines"))) {
                Collection<File> detectedFiles = FileUtils.listFiles(plugin.getDataFolder().toPath().resolve("machines").toFile(), TrueFileFilter.INSTANCE, null);
                plugin.getLogger().log(Level.INFO, Language.translate("load.slotmachine.detected").replace("%amount%", String.valueOf(detectedFiles.size())));
                for (File file : detectedFiles) {
                    if (!FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("yml"))
                        continue;
                    YamlConfiguration ymlFile = YamlConfiguration.loadConfiguration(file);
                    plugin.getLogger().log(Level.INFO, Language.translate("load.slotmachine.loading").replace("%file%", file.getName()));
                    try {
                        UUID machineUUID;
                        try {
                            if (!ymlFile.contains("machineType") || ymlFile.getString("machineType") == null || SlotMachineType.valueOf(ymlFile.getString("machineType")) == null) {
                                Issue.newIssue(IssueType.MACHINE_READING_ISSUE, "Machine in file " + file.getName() + " has no 'machineType', it WON'T be loaded, please fix this in the machine file. (Must be ENTITY or BLOCK)", true);
                                SlotPlugin.pl.getLogger().log(Level.SEVERE, "Machine in file " + file.getName() + " has no 'machineType', it WON'T be loaded, please fix this in the machine file. (Must be ENTITY or BLOCK)");
                                continue;
                            }
                            if (ymlFile.getString("machineUUID") == null) {
                                Issue.newIssue(IssueType.MACHINE_READING_ISSUE, "Machine in file " + file.getName() + " has no 'machineUUID', we will try using the file name as this machine's UUID instead", true);
                                SlotPlugin.pl.getLogger().log(Level.SEVERE, "Machine in file " + file.getName() + " has no 'machineUUID', we will try using the file name as this machine's UUID instead");
                                machineUUID = UUID.fromString(FilenameUtils.getBaseName(file.getName()));
                            } else {
                                machineUUID = UUID.fromString(ymlFile.getString("machineUUID"));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Issue.newIssue(IssueType.MACHINE_READING_ISSUE, "Machine in file " + file.getName() + " could not be loaded, see the related exception in server logs", true);
                            SlotPlugin.pl.getLogger().log(Level.SEVERE, "Machine in file " + file.getName() + " could not be loaded, see the related exception");
                            ExceptionCollector.sendException(plugin, e);
                            continue;
                        }
                        SlotMachine slotMachine = null;
                        UUID worldUUID = UUID.fromString(ymlFile.getString("worldUID"));

                        switch (SlotMachineType.valueOf(ymlFile.getString("machineType"))) {
                            case BLOCK:
                                slotMachine = new SlotMachineBlock(ymlFile.getInt("blockX"), ymlFile.getInt("blockY"), ymlFile.getInt("blockZ"), ymlFile.getBoolean("locked"), worldUUID, ymlFile.getInt("chunkX"), ymlFile.getInt("chunkZ"));
                                break;
                            case ENTITY:
                                slotMachine = new SlotMachineEntity(UUID.fromString(ymlFile.getString("entityUID")), worldUUID, ymlFile.getInt("chunkX"), ymlFile.getInt("chunkZ"));
                                break;
                        }
                        slotMachine.setMachineUUID(machineUUID);
                        slotMachine.setGuiPermission(ymlFile.getString("guiPermission"));
                        slotMachine.setSlotMachineName(ymlFile.getString("slotMachineName"));
                        slotMachine.setPullPrice(ymlFile.getDouble("pullPrice"));
                        slotMachine.setChanceToWin(ymlFile.getDouble("chanceToWin"));
                        slotMachine.setSecondsBeforePrize(ymlFile.getInt("secondsBeforePrize"));
                        slotMachine.setWinMessage(ymlFile.getString("winMessage"));
                        slotMachine.setLossMessage(ymlFile.getString("lossMessage"));
                        slotMachine.setLeverTitle(ymlFile.getString("leverTitle"));
                        slotMachine.setLeverDescription(ymlFile.getString("leverDescription"));
                        if (ymlFile.isSet("customLever")) {
                            slotMachine.setLeverCustom(ymlFile.getBoolean("customLever"));
                        }
                        if (ymlFile.isSet("priceType")) {
                            slotMachine.setPriceType(PriceType.valueOf(ymlFile.getString("priceType")));
                        }
                        if (ymlFile.isSet("tokenIdentifier")) {
                            slotMachine.setTokenIdentifier(ymlFile.getString("tokenIdentifier"));
                        }
                        if (ymlFile.isSet("visualType")) {
                            slotMachine.setVisualType(VisualType.valueOf(ymlFile.getString("visualType")));
                        }
                        if (ymlFile.isSet("affectedByLuck")) {
                            slotMachine.setAffectedByLuck(ymlFile.getBoolean("affectedByLuck"));
                        }
                        if (ymlFile.isSet("allowContentPreview")) {
                            slotMachine.allowContentPreview(ymlFile.getBoolean("allowContentPreview"));
                        }
                        if (ymlFile.isSet("itemWeightOnPreview")) {
                            slotMachine.showItemWeightOnPreview(ymlFile.getBoolean("itemWeightOnPreview"));
                        }
                        if (ymlFile.isSet("itemChanceOnPreview")) {
                            slotMachine.showChanceOfItemOnPreview(ymlFile.getBoolean("itemChanceOnPreview"));
                        }
                        if (ymlFile.isSet("isCitizensNPC")) {
                            slotMachine.setCitizensNPC(ymlFile.getBoolean("isCitizensNPC"));
                        }
                        if (ymlFile.isSet("hasWinMessage")) {
                            slotMachine.setHasWinMessage(ymlFile.getBoolean("hasWinMessage"));
                        }
                        if (ymlFile.isSet("hasLossMessage")) {
                            slotMachine.setHasLossMessage(ymlFile.getBoolean("hasLossMessage"));
                        }
                        if (ymlFile.isSet("displayItemNameInChat")) {
                            slotMachine.setDisplayWonItemInChat(ymlFile.getBoolean("displayItemNameInChat"));
                        }
                        if (ymlFile.isSet("timesUsed")) {
                            slotMachine.setTimesUsed(ymlFile.getInt("timesUsed"));
                        }
                        if (ymlFile.isSet("playMode")) {
                            slotMachine.setPlayMode(PlayMode.valueOf(ymlFile.getString("playMode")));
                        }
                        if (ymlFile.isSet("cooldown")) {
                            slotMachine.setCooldown(ymlFile.getInt("cooldown"));
                        }

                        if (ymlFile.isSet("backgroundItem")) {
                            if (ymlFile.isItemStack("backgroundItem")) {
                                slotMachine.setBackgroundItem(ymlFile.getItemStack("backgroundItem"));
                            } else if (ItemStackUtil.isValidMaterial(ymlFile.getString("backgroundItem"))) {
                                slotMachine.setBackgroundItem(new ItemStack(Material.getMaterial(ymlFile.getString("backgroundItem")), 1));
                            }
                        }
                        if (ymlFile.isSet("emphasisItem")) {
                            if (ymlFile.isItemStack("emphasisItem")) {
                                slotMachine.setEmphasisItem(ymlFile.getItemStack("emphasisItem"));
                            } else if (ItemStackUtil.isValidMaterial(ymlFile.getString("emphasisItem"))) {
                                slotMachine.setEmphasisItem(new ItemStack(Material.getMaterial(ymlFile.getString("emphasisItem")), 1));
                            }
                        }
                        if (ymlFile.isSet("leverItem")) {
                            if (ymlFile.isItemStack("leverItem")) {
                                slotMachine.setLeverItem(ymlFile.getItemStack("leverItem"));
                            } else if (ItemStackUtil.isValidMaterial(ymlFile.getString("leverItem"))) {
                                slotMachine.setLeverItem(new ItemStack(Material.getMaterial(ymlFile.getString("leverItem")), 1));
                            }
                        }
                        if (ymlFile.isSet("itemListItem")) {
                            if (ymlFile.isItemStack("itemListItem")) {
                                slotMachine.setItemListItem(ymlFile.getItemStack("itemListItem"));
                            } else if (ItemStackUtil.isValidMaterial(ymlFile.getString("itemListItem"))) {
                                slotMachine.setItemListItem(new ItemStack(Material.getMaterial(ymlFile.getString("itemListItem")), 1));
                            }
                        }

                        if (ymlFile.isSet("machineOpeningSound") && Util.isValidSound(ymlFile.getString("machineOpeningSound"))) {
                            slotMachine.setMachineOpeningSound(Sound.valueOf(ymlFile.getString("machineOpeningSound")));
                        }
                        if (ymlFile.isSet("leverSound") && Util.isValidSound(ymlFile.getString("leverSound"))) {
                            slotMachine.setLeverSound(Sound.valueOf(ymlFile.getString("leverSound")));
                        }
                        if (ymlFile.isSet("slotmachineSpinSound") && Util.isValidSound(ymlFile.getString("slotmachineSpinSound"))) {
                            slotMachine.setSlotmachineSpinSound(Sound.valueOf(ymlFile.getString("slotmachineSpinSound")));
                        }
                        if (ymlFile.isSet("errorSound") && Util.isValidSound(ymlFile.getString("errorSound"))) {
                            slotMachine.setErrorSound(Sound.valueOf(ymlFile.getString("errorSound")));
                        }
                        if (ymlFile.isSet("csgoSpinSound") && Util.isValidSound(ymlFile.getString("csgoSpinSound"))) {
                            slotMachine.setCsgoSpinSound(Sound.valueOf(ymlFile.getString("csgoSpinSound")));
                        }
                        if (ymlFile.isSet("winSound") && Util.isValidSound(ymlFile.getString("winSound"))) {
                            slotMachine.setWinSound(Sound.valueOf(ymlFile.getString("winSound")));
                        }
                        if (ymlFile.isSet("lossSound") && Util.isValidSound(ymlFile.getString("lossSound"))) {
                            slotMachine.setLossSound(Sound.valueOf(ymlFile.getString("lossSound")));
                        }
                        if (ymlFile.isSet("errorSound") && Util.isValidSound(ymlFile.getString("errorSound"))) {
                            slotMachine.setErrorSound(Sound.valueOf(ymlFile.getString("errorSound")));
                        }

                        List<MachineItem> slotMachineItems = new ArrayList<MachineItem>();
                        for (int b = 0; b < ymlFile.getInt("itemCount"); b++) {

                            if (ymlFile.isSet("items." + b + ".reward")) {
                                MachineItem.RewardType rewardType = ymlFile.isSet("items." + b + ".rewardType") ? MachineItem.RewardType.valueOf(ymlFile.getString("items." + b + ".rewardType")) : null;
                                if (rewardType != null) {
                                    if (rewardType == RewardType.ITEM) {
                                        slotMachineItems.add(new MachineItem(ymlFile.getItemStack("items." + b + ".item"), ymlFile.getInt("items." + b + ".weight"), Arrays.asList(new MachineItem.Reward(ymlFile.getItemStack("items." + b + ".reward")))));
                                    } else if (rewardType == RewardType.COMMAND) {
                                        slotMachineItems.add(new MachineItem(ymlFile.getItemStack("items." + b + ".item"), ymlFile.getInt("items." + b + ".weight"), Arrays.asList(new MachineItem.Reward(ymlFile.getString("items." + b + ".reward")))));
                                    }
                                } else {
                                    slotMachineItems.add(new MachineItem(ymlFile.getItemStack("items." + b + ".item"), ymlFile.getInt("items." + b + ".weight")));
                                }
                            } else if (ymlFile.isSet("items." + b + ".rewards")) {
                                Set<String> strRewards = ymlFile.getConfigurationSection("items." + b + ".rewards").getKeys(false);
                                List<Reward> rewards = new ArrayList<Reward>();

                                for (String str : strRewards) {
                                    if (ymlFile.isSet("items." + b + ".rewards." + str + ".type") && (ymlFile.isSet("items." + b + ".rewards." + str + ".item") || ymlFile.isSet("items." + b + ".rewards." + str + ".command"))) {
                                        if (RewardType.valueOf(ymlFile.getString("items." + b + ".rewards." + str + ".type")) == RewardType.ITEM) {
                                            rewards.add(new MachineItem.Reward(ymlFile.getItemStack("items." + b + ".rewards." + str + ".item")));
                                        } else if (RewardType.valueOf(ymlFile.getString("items." + b + ".rewards." + str + ".type")) == RewardType.COMMAND) {
                                            rewards.add(new MachineItem.Reward(ymlFile.getString("items." + b + ".rewards." + str + ".command")));
                                        }
                                    } else {
                                        Issue.newIssue(IssueType.MACHINE_READING_ISSUE, "Machine " + slotMachine.getMachineUUID().toString() + " has a malformed item reward (" + str + ")", true);
                                        SlotPlugin.pl.getLogger().log(Level.SEVERE, "Machine " + slotMachine.getMachineUUID().toString() + " has a malformed item reward (" + str + "), we're not loading this reward");
                                    }
                                }
                                MachineItem it = new MachineItem(ymlFile.getItemStack("items." + b + ".item"), ymlFile.getInt("items." + b + ".weight"), rewards);

                                if (ymlFile.isSet("items." + b + ".stats.timesWon")) {
                                    it.itemStats.timesWon = ymlFile.getInt("items." + b + ".stats.timesWon");
                                }

                                slotMachineItems.add(it);
                            } else {
                                slotMachineItems.add(
                                        new MachineItem(
                                                ymlFile.getItemStack("items." + b + ".item"),
                                                ymlFile.getInt("items." + b + ".weight")
                                        )
                                );
                            }
                        }
                        slotMachine.setSlotMachineItems(slotMachineItems);
                        if (slotMachine.getSlotMachineType() == SlotMachineType.ENTITY) {
                            SlotMachineEntity.addSlotMachineEntity((SlotMachineEntity) slotMachine);
                        }
                        if (slotMachine.getSlotMachineType() == SlotMachineType.BLOCK) {
                            SlotMachineBlock.addSlotMachineBlock((SlotMachineBlock) slotMachine);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Issue.newIssue(IssueType.MACHINE_READING_ISSUE, e.getMessage() + " // " + Language.translate("load.slotmachine.exception").replace("%file%", file.getName()), true);
                        plugin.getLogger().log(Level.SEVERE, Language.translate("load.slotmachine.exception").replace("%file%", file.getName()));
                        ExceptionCollector.sendException(SlotPlugin.pl, e);
                    }
                }
                plugin.getLogger().log(Level.INFO, Language.translate("load.slotmachine.finished"));
            }
        }

        if (SlotPlugin.pl.getConfig().getInt("pluginVersion") == 2 || SlotPlugin.pl.getConfig().getInt("pluginVersion") == 3) {
            plugin.getLogger().log(Level.INFO, Language.translate("config.version.or").replace("%version1%", "2").replace("%version2%", "3"));
            for (int a = 0; a < SlotPlugin.pl.getConfig().getInt("slotMachineCount"); a++) {
                if (EntityUtil.getEntityByUUIDLoadChunks(UUID.fromString(SlotPlugin.pl.getConfig().getString("slotMachine." + a + ".worldUID")), SlotPlugin.pl.getConfig().getInt("slotMachine." + a + ".chunkX"), SlotPlugin.pl.getConfig().getInt("slotMachine." + a + ".chunkZ"), UUID.fromString(SlotPlugin.pl.getConfig().getString("slotMachine." + a + ".entityUID"))) != null) {
                    Entity entity = EntityUtil.getEntityByUUIDLoadChunks(UUID.fromString(SlotPlugin.pl.getConfig().getString("slotMachine." + a + ".worldUID")), SlotPlugin.pl.getConfig().getInt("slotMachine." + a + ".chunkX"), SlotPlugin.pl.getConfig().getInt("slotMachine." + a + ".chunkZ"), UUID.fromString(SlotPlugin.pl.getConfig().getString("slotMachine." + a + ".entityUID")));
                    if (entity instanceof LivingEntity) {
                        if (!((LivingEntity) entity).hasAI()) {
                            ((LivingEntity) entity).setAI(true);
                            ((LivingEntity) entity).setAI(false);
                        }
                    }
                } else {
                    //SlotPlugin.pl.getLogger().log(Level.SEVERE, Language.translate("load.slotmachine.entitynotfound").replace("%entityUUID%", SlotPlugin.pl.getConfig().getString("slotMachine." + a + ".entityUID")));
                }
                SlotMachine slotMachine = null;
                switch (SlotMachineType.valueOf(SlotPlugin.pl.getConfig().getString("slotMachine." + a + ".machineType"))) {
                    case BLOCK:
                        slotMachine = new SlotMachineEntity(UUID.fromString(SlotPlugin.pl.getConfig().getString("slotMachine." + a + ".entityUID")), UUID.fromString(SlotPlugin.pl.getConfig().getString("slotMachine." + a + ".worldUID")), SlotPlugin.pl.getConfig().getInt("slotMachine." + a + ".chunkX"), SlotPlugin.pl.getConfig().getInt("slotMachine." + a + ".chunkZ"));
                        break;
                    case ENTITY:
                        slotMachine = new SlotMachineEntity(UUID.fromString(SlotPlugin.pl.getConfig().getString("slotMachine." + a + ".entityUID")), UUID.fromString(SlotPlugin.pl.getConfig().getString("slotMachine." + a + ".worldUID")), SlotPlugin.pl.getConfig().getInt("slotMachine." + a + ".chunkX"), SlotPlugin.pl.getConfig().getInt("slotMachine." + a + ".chunkZ"));
                        break;
                    default:
                        slotMachine = new SlotMachineEntity(UUID.fromString(SlotPlugin.pl.getConfig().getString("slotMachine." + a + ".entityUID")), UUID.fromString(SlotPlugin.pl.getConfig().getString("slotMachine." + a + ".worldUID")), SlotPlugin.pl.getConfig().getInt("slotMachine." + a + ".chunkX"), SlotPlugin.pl.getConfig().getInt("slotMachine." + a + ".chunkZ"));
                        break;
                }
                slotMachine.setMachineUUID(UUID.fromString(SlotPlugin.pl.getConfig().getString("slotMachine." + a + ".machineUUID")));
                slotMachine.setGuiPermission(SlotPlugin.pl.getConfig().getString("slotMachine." + a + ".guiPermission"));
                slotMachine.setGuiPermission(SlotPlugin.pl.getConfig().getString("slotMachine." + a + ".guiPermission"));
                slotMachine.setSlotMachineName(SlotPlugin.pl.getConfig().getString("slotMachine." + a + ".slotMachineName"));
                slotMachine.setPullPrice(SlotPlugin.pl.getConfig().getDouble("slotMachine." + a + ".pullPrice"));
                slotMachine.setChanceToWin(SlotPlugin.pl.getConfig().getDouble("slotMachine." + a + ".chanceToWin"));
                slotMachine.setSecondsBeforePrize(SlotPlugin.pl.getConfig().getInt("slotMachine." + a + ".secondsBeforePrize"));
                slotMachine.setWinMessage(SlotPlugin.pl.getConfig().getString("slotMachine." + a + ".winMessage"));
                slotMachine.setLossMessage(SlotPlugin.pl.getConfig().getString("slotMachine." + a + ".lossMessage"));
                slotMachine.setLeverTitle(SlotPlugin.pl.getConfig().getString("slotMachine." + a + ".leverTitle"));
                slotMachine.setLeverDescription(SlotPlugin.pl.getConfig().getString("slotMachine." + a + ".leverDescription"));
                if (SlotPlugin.pl.getConfig().isSet("slotMachine." + a + ".priceType")) {
                    slotMachine.setPriceType(PriceType.valueOf(SlotPlugin.pl.getConfig().getString("slotMachine." + a + ".priceType")));
                }
                if (SlotPlugin.pl.getConfig().isSet("slotMachine." + a + ".visualType")) {
                    slotMachine.setVisualType(VisualType.valueOf(SlotPlugin.pl.getConfig().getString("slotMachine." + a + ".visualType")));
                }
                List<MachineItem> slotMachineItems = new ArrayList<MachineItem>();
                for (int b = 0; b < SlotPlugin.pl.getConfig().getInt("slotMachine." + a + ".itemCount"); b++) {
                    if (SlotPlugin.pl.getConfig().isSet("items." + b + ".reward")) {
                        MachineItem.RewardType rewardType = SlotPlugin.pl.getConfig().isSet("items." + b + ".rewardType") ? MachineItem.RewardType.valueOf(SlotPlugin.pl.getConfig().getString("items." + b + ".rewardType")) : null;
                        if (rewardType != null) {
                            if (rewardType == RewardType.ITEM) {
                                slotMachineItems.add(new MachineItem(SlotPlugin.pl.getConfig().getItemStack("items." + b + ".item"), SlotPlugin.pl.getConfig().getInt("items." + b + ".weight"), Arrays.asList(new MachineItem.Reward(SlotPlugin.pl.getConfig().getItemStack("items." + b + ".reward")))));
                            } else if (rewardType == RewardType.COMMAND) {
                                slotMachineItems.add(new MachineItem(SlotPlugin.pl.getConfig().getItemStack("items." + b + ".item"), SlotPlugin.pl.getConfig().getInt("items." + b + ".weight"), Arrays.asList(new MachineItem.Reward(SlotPlugin.pl.getConfig().getString("items." + b + ".reward")))));
                            }
                        } else {
                            slotMachineItems.add(new MachineItem(SlotPlugin.pl.getConfig().getItemStack("items." + b + ".item"), SlotPlugin.pl.getConfig().getInt("items." + b + ".weight")));
                        }
                    } else if (SlotPlugin.pl.getConfig().isSet("items." + b + ".rewards")) {
                        Set<String> strRewards = SlotPlugin.pl.getConfig().getConfigurationSection("items." + b + ".rewards").getKeys(false);
                        List<Reward> rewards = new ArrayList<Reward>();

                        for (String str : strRewards) {
                            if (SlotPlugin.pl.getConfig().isSet("items." + b + ".rewards." + str + ".type") && (SlotPlugin.pl.getConfig().isSet("items." + b + ".rewards." + str + ".item") || SlotPlugin.pl.getConfig().isSet("items." + b + ".rewards." + str + ".command"))) {
                                if (RewardType.valueOf(SlotPlugin.pl.getConfig().getString("items." + b + ".rewards." + str + ".type")) == RewardType.ITEM) {
                                    rewards.add(new MachineItem.Reward(SlotPlugin.pl.getConfig().getItemStack("items." + b + ".rewards." + str + ".item")));
                                } else if (RewardType.valueOf(SlotPlugin.pl.getConfig().getString("items." + b + ".rewards." + str + ".type")) == RewardType.COMMAND) {
                                    rewards.add(new MachineItem.Reward(SlotPlugin.pl.getConfig().getString("items." + b + ".rewards." + str + ".command")));
                                }
                            } else {
                                SlotPlugin.pl.getLogger().log(Level.SEVERE, "Machine " + slotMachine.getMachineUUID().toString() + " has a malformed item reward (" + str + "), we're not loading this reward");
                            }
                        }

                        slotMachineItems.add(new MachineItem(SlotPlugin.pl.getConfig().getItemStack("items." + b + ".item"), SlotPlugin.pl.getConfig().getInt("items." + b + ".weight"), rewards));
                    } else {
                        slotMachineItems.add(
                                new MachineItem(
                                        SlotPlugin.pl.getConfig().getItemStack("items." + b + ".item"),
                                        SlotPlugin.pl.getConfig().getInt("items." + b + ".weight")
                                )
                        );
                    }
                }
                slotMachine.setSlotMachineItems(slotMachineItems);
                if (slotMachine.getSlotMachineType() == SlotMachineType.ENTITY) {
                    SlotMachineEntity.addSlotMachineEntity((SlotMachineEntity) slotMachine);
                }
            }
        }
        if (SlotPlugin.pl.getConfig().getInt("pluginVersion") == 1) {
            plugin.getLogger().log(Level.INFO, Language.translate("config.version").replace("%version%", "1"));
            for (int a = 0; a < SlotPlugin.pl.getConfig().getInt("slotMachineCount"); a++) {
                if (EntityUtil.getEntityByUUIDLoadChunks(UUID.fromString(SlotPlugin.pl.getConfig().getString("slotMachine." + a + ".worldUID")), SlotPlugin.pl.getConfig().getInt("slotMachine." + a + ".chunkX"), SlotPlugin.pl.getConfig().getInt("slotMachine." + a + ".chunkZ"), UUID.fromString(SlotPlugin.pl.getConfig().getString("slotMachine." + a + ".entityUID"))) != null) {
                    Entity entity = EntityUtil.getEntityByUUIDLoadChunks(UUID.fromString(SlotPlugin.pl.getConfig().getString("slotMachine." + a + ".worldUID")), SlotPlugin.pl.getConfig().getInt("slotMachine." + a + ".chunkX"), SlotPlugin.pl.getConfig().getInt("slotMachine." + a + ".chunkZ"), UUID.fromString(SlotPlugin.pl.getConfig().getString("slotMachine." + a + ".entityUID")));
                    if (entity instanceof LivingEntity) {
                        if (!((LivingEntity) entity).hasAI()) {
                            ((LivingEntity) entity).setAI(true);
                            ((LivingEntity) entity).setAI(false);
                        }
                    }
                } else {
                    SlotPlugin.pl.getLogger().log(Level.SEVERE, Language.translate("load.slotmachine.entitynotfound").replace("%entityUUID%", SlotPlugin.pl.getConfig().getString("slotMachine." + a + ".entityUID")));
                }
                SlotMachineEntity slotMachineEntity = new SlotMachineEntity(UUID.fromString(SlotPlugin.pl.getConfig().getString("slotMachine." + a + ".entityUID")), UUID.fromString(SlotPlugin.pl.getConfig().getString("slotMachine." + a + ".worldUID")), SlotPlugin.pl.getConfig().getInt("slotMachine." + a + ".chunkX"), SlotPlugin.pl.getConfig().getInt("slotMachine." + a + ".chunkZ"));
                slotMachineEntity.setGuiPermission(SlotPlugin.pl.getConfig().getString("slotMachine." + a + ".guiPermission"));
                slotMachineEntity.setSlotMachineName(SlotPlugin.pl.getConfig().getString("slotMachine." + a + ".slotMachineName"));
                if (SlotPlugin.pl.getConfig().isSet("slotMachine." + a + ".pullPrice")) {
                    slotMachineEntity.setPullPrice(SlotPlugin.pl.getConfig().getDouble("slotMachine." + a + ".pullPrice"));
                }
                if (SlotPlugin.pl.getConfig().isSet("slotMachine." + a + ".chanceToWin")) {
                    slotMachineEntity.setChanceToWin(SlotPlugin.pl.getConfig().getDouble("slotMachine." + a + ".chanceToWin"));
                }
                if (SlotPlugin.pl.getConfig().isSet("slotMachine." + a + ".secondsBeforePrize")) {
                    slotMachineEntity.setSecondsBeforePrize(SlotPlugin.pl.getConfig().getInt("slotMachine." + a + ".secondsBeforePrize"));
                }
                if (SlotPlugin.pl.getConfig().isSet("slotMachine." + a + ".winMessage")) {
                    slotMachineEntity.setWinMessage(SlotPlugin.pl.getConfig().getString("slotMachine." + a + ".winMessage"));
                }
                if (SlotPlugin.pl.getConfig().isSet("slotMachine." + a + ".lossMessage")) {
                    slotMachineEntity.setLossMessage(SlotPlugin.pl.getConfig().getString("slotMachine." + a + ".lossMessage"));
                }
                if (SlotPlugin.pl.getConfig().isSet("slotMachine." + a + ".leverTitle")) {
                    slotMachineEntity.setLeverTitle(SlotPlugin.pl.getConfig().getString("slotMachine." + a + ".leverTitle"));
                }
                if (SlotPlugin.pl.getConfig().isSet("slotMachine." + a + ".leverDescription")) {
                    slotMachineEntity.setLeverDescription(SlotPlugin.pl.getConfig().getString("slotMachine." + a + ".leverDescription"));
                }
                List<MachineItem> slotMachineItems = new ArrayList<MachineItem>();
                for (int b = 0; b < SlotPlugin.pl.getConfig().getInt("slotMachine." + a + ".itemCount"); b++) {
                    slotMachineItems.add(new MachineItem(SlotPlugin.pl.getConfig().getItemStack("slotMachine." + a + ".items." + b), 1));
                }
                slotMachineEntity.setSlotMachineItems(slotMachineItems);
                SlotMachineEntity.addSlotMachineEntity(slotMachineEntity);
            }
            SlotPlugin.pl.getConfig().set("pluginVersion", 2);
        }
        if (SlotPlugin.pl.getConfig().getInt("pluginVersion") == 2) {
            plugin.getLogger().log(Level.INFO, Language.translate("config.migrating").replace("%version%", "3"));
            SlotPlugin.pl.getConfig().set("pluginVersion", 3);
        }
        if (SlotPlugin.pl.getConfig().getInt("pluginVersion") == 3) {
            plugin.getLogger().log(Level.INFO, Language.translate("config.migrating").replace("%version%", "4"));
            plugin.getLogger().log(Level.INFO, Language.translate("config.migrating.success").replace("%version%", "4"));
            SlotPlugin.pl.getConfig().set("pluginVersion", 4);
            SlotPlugin.pl.getConfig().set("slotMachineCount", null);
            SlotPlugin.pl.getConfig().set("slotMachine", null);
            SlotPlugin.pl.saveConfig();
        }
        if (SlotPlugin.pl.getConfig().getInt("pluginVersion") == 4) {
            plugin.getLogger().log(Level.INFO, Language.translate("config.migrating").replace("%version%", "5"));
            SlotPlugin.pl.getConfig().set("pluginVersion", 5);
            SlotPlugin.pl.getConfig().set("defaultToken", null);
            SlotPlugin.pl.saveConfig();
        }
        SlotPlugin.saveToDisk();
    }
}
