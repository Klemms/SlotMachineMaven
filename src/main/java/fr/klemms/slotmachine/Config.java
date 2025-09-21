package fr.klemms.slotmachine;

import fr.klemms.slotmachine.Issue.IssueType;
import fr.klemms.slotmachine.MachineItem.Reward;
import fr.klemms.slotmachine.MachineItem.RewardType;
import fr.klemms.slotmachine.exceptioncollector.ExceptionCollector;
import fr.klemms.slotmachine.translation.Language;
import fr.klemms.slotmachine.utils.EntityUtil;
import fr.klemms.slotmachine.utils.ItemStackUtil;
import fr.klemms.slotmachine.utils.Util;
import fr.klemms.slotmachine.utils.sounds.SSound;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;

public class Config {

    public static volatile int pluginVersion = 6;

    public static volatile boolean debug = false;

    public static volatile boolean enableLanguageOTAUpdates = true;
    public static volatile String language = "en";

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

    public static boolean noDefaultPermission = false;

    public static volatile double luckLevelToPercentConversion = 12.5F;
    public static volatile double badLuckLevelToPercentConversion = -12.5F;

    public static volatile HashMap<String, ItemStack> tokens = new HashMap<String, ItemStack>();

    public static volatile Material adminToolMaterial = Material.BLAZE_ROD;

    public static boolean quickReload = true;

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
            plugin.getConfig().setComments("language", Arrays.asList("",
                    "Language to be used",
                    "See the 'Supports Localization' part of the plugin page on Spigot website",
                    "Valid values are :",
                    "en    (english)              -- Default value - Complete and high quality",
                    "fr    (french)               --               - Complete and high quality",
                    "zh-CN (Simplified Chinese)   --               - Complete and high quality",
                    "zh-TW (Traditional Chinese)  --               - Complete and high quality",
                    "de    (German)               --               - Complete but quality is unknown",
                    "ru    (Russian)              --               - Complete but quality is unknown",
                    "hu    (Hungarian)            --               - Complete but quality is unknown",
                    "it    (Italian)              --               - Complete but quality is unknown",
                    "es-ES (Spanish)              --               - Complete but quality is unknown",
                    "pt-BR (Brazilian Portuguese) --               - Complete but quality is unknown",
                    "cs    (Czech)                --               - Complete but quality is unknown",
                    "ja    (Japanese)             --               - Incomplete and unknown quality",
                    "tr    (tr-TR)                --               - Incomplete and unknown quality",
                    "ko    (Korean)               --               - Very incomplete and quality is unknown",
                    "ro    (Romanian)             --               - Very incomplete and quality is unknown",
                    "vi    (Vietnamese)           --               - Extremely incomplete and quality may be low",
                    "",
                    "IMPORTANT NOTE : If you did not disable language over-the-air updates,",
                    "your selected language will get updated automatically when the plugin loads/at server startup or",
                    "by running the /smupdatelanguages command.",
                    "Because of that the quality and coverage of the language you selected may improve over time.",
                    "",
                    "Want to contribute to the quality of your language or find your language is missing ?",
                    "Head here and help us translate the plugin : https://crowdin.com/project/slot-machine/invite?h=85a84eff6fc4e68863a2560c3d765e491657749",
                    "",
                    "You can also create your own translation, please read the 'readme' file in the langs folder for more informations."));

        plugin.getConfig().addDefault("enableLanguageOTAUpdates", enableLanguageOTAUpdates);
        if (Util.isAtLeastMC118())
            plugin.getConfig().setComments("enableLanguageOTAUpdates", Arrays.asList("", "Allows you to receive updates for the official language files without having to update the plugin", "Updates are done when the plugin loads", "You can force an update with the command /smupdatelanguages and the changes will be applied immediately", "Updates are fetched directly from Crowdin, our official translation platform", "You can help us translate the plugin by going to https://crowdin.com/project/slot-machine"));

        plugin.getConfig().addDefault("noPermissionNeededForDefaultAccess", noDefaultPermission);
        if (Util.isAtLeastMC118())
            plugin.getConfig().setComments("noPermissionNeededForDefaultAccess", Arrays.asList("", "Don't require players to have the 'slotmachine.access.default' permission to play", "Note : Slot Machines using a different permission name will still require permission"));

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
            plugin.getConfig().setComments("showItemName", Arrays.asList("", "Whether to show the item name the player has won in the chat when they win", "Setting this to true can help with players not realizing what they won"));

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
            plugin.getConfig().setComments("luckLevelToPercentConversion", Arrays.asList("", "How much chance to win per luck (Potion Effect) level is added to the machine's chance", "This can be negative and will affect chance accordingly"));

        plugin.getConfig().addDefault("badLuckLevelToPercentConversion", badLuckLevelToPercentConversion);
        if (Util.isAtLeastMC118())
            plugin.getConfig().setComments("badLuckLevelToPercentConversion", Arrays.asList("", "How much chance to win per bad-luck (Potion Effect) level is added to the machine's chance", "This can be negative and will affect chance accordingly"));

        plugin.getConfig().addDefault("adminToolMaterial", adminToolMaterial.toString());
        if (Util.isAtLeastMC118())
            plugin.getConfig().setComments("adminToolMaterial", Arrays.asList("", "Which Spigot Material to use to interact with machines", "A complete list can be found here https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Material.html"));

        plugin.getConfig().set("anonymouslyReportExceptionsToDevelopper", null);

        plugin.getConfig().options().copyDefaults(true);
        plugin.saveConfig();

        if (quickReload) {
            quickReload = false;
            registerConfig(plugin);
        }
    }

    public static void readConfig(JavaPlugin plugin) {
        if (Language.isValidLanguage(SlotPlugin.pl.getConfig().getString("language"))) {
            language = SlotPlugin.pl.getConfig().getString("language");
        } else {
            plugin.getLogger().log(Level.WARNING, "The config language '" + SlotPlugin.pl.getConfig().getString("language") + "' does not exist, falling back to 'en' (English)");
            language = "en";
            plugin.getConfig().set("language", language);
            plugin.saveConfig();
        }
        enableLanguageOTAUpdates = SlotPlugin.pl.getConfig().getBoolean("enableLanguageOTAUpdates");
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
        Material tool = Material.getMaterial(SlotPlugin.pl.getConfig().getString("adminToolMaterial"));
        adminToolMaterial = tool != null ? tool : Material.BLAZE_ROD;
        noDefaultPermission = SlotPlugin.pl.getConfig().getBoolean("noPermissionNeededForDefaultAccess");

        if (adminToolMaterial == null)
            adminToolMaterial = Material.BLAZE_ROD;

        SlotPlugin.saveToDisk();
    }

    public static void loadMachines(JavaPlugin plugin) {
        if (plugin.getConfig().getInt("pluginVersion") < 6) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HHmmss_dd-MMM-yyyy", new Locale.Builder().setLanguage("en").setRegion("US").build());
            Setup.makeBackup("MACHINES_BACKUP-" + dateFormat.format(new Date()));
            plugin.getConfig().set("pluginVersion", Config.pluginVersion);
        }
        if (plugin.getConfig().getInt("pluginVersion") == 4 || plugin.getConfig().getInt("pluginVersion") == 5 || plugin.getConfig().getInt("pluginVersion") == 6) {
            plugin.getLogger().log(Level.INFO, Language.translate("config.version").replace("%version%", String.valueOf(plugin.getConfig().getInt("pluginVersion"))));
            if (Files.exists(plugin.getDataFolder().toPath().resolve("machines"))) {
                boolean saveMade = false;

                Collection<File> detectedFiles = FileUtils.listFiles(plugin.getDataFolder().toPath().resolve("machines").toFile(), TrueFileFilter.INSTANCE, null);
                plugin.getLogger().log(Level.INFO, Language.translate("load.slotmachine.detected").replace("%amount%", String.valueOf(detectedFiles.size())));
                for (File file : detectedFiles) {
                    if (!FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("yml")) {
                        plugin.getLogger().log(Level.WARNING, "Skipping unknown file : " + file.getName());
                        continue;
                    }

                    YamlConfiguration ymlFile = YamlConfiguration.loadConfiguration(file);
                    plugin.getLogger().log(Level.INFO, Language.translate("load.slotmachine.loading").replace("%file%", file.getName()));
                    try {
                        if (!saveMade && (!ymlFile.contains("iVersion") || ymlFile.getInt("iVersion", 0) < SlotPlugin.VERSION)) {
                            plugin.getLogger().log(Level.INFO, "Detected Slot Machine version upgrade, making a backup of machines called : " + "MACHINES_BACKUP-UPGRADE-TO-" + SlotPlugin.VERSION);
                            Setup.makeBackup("MACHINES_BACKUP-UPGRADE-TO-" + SlotPlugin.VERSION);
                            saveMade = true;
                        }

                        if (ymlFile.getInt("iVersion", 0) > SlotPlugin.VERSION) {
                            plugin.getLogger().log(Level.WARNING, "Machine file '" + file.getName() + "' is coming from a newer version of Slot Machine, plugin downgrade is not recommended as it could lead to data loss. Current Plugin version : " + SlotPlugin.VERSION + " - File version : " + ymlFile.getInt("iVersion", 0));
                        }

                        UUID machineUUID = UUID.randomUUID();;
                        try {
                            if (!ymlFile.contains("machineType") || ymlFile.getString("machineType") == null) {
                                Issue.newIssue(IssueType.MACHINE_READING_ISSUE, "Machine in file " + file.getName() + " has no 'machineType', it WON'T be loaded, please fix this in the machine file. (Must be ENTITY or BLOCK)", true);
                                SlotPlugin.pl.getLogger().log(Level.SEVERE, "Machine in file " + file.getName() + " has no 'machineType', it WON'T be loaded, please fix this in the machine file. (Must be ENTITY or BLOCK)");
                                continue;
                            } else {
                                try {
                                    // Throwing exception if it's not a valid type
                                    SlotMachineType.valueOf(ymlFile.getString("machineType"));
                                } catch (IllegalArgumentException e) {
                                    plugin.getLogger().log(Level.SEVERE, "Machine in file " + file.getName() + " has a wrong 'machineType', it must be ENTITY or BLOCK (case sensitive). This machine won't be loaded until you fix this issue");
                                    Issue.newIssue(IssueType.MACHINE_READING_ISSUE, "Machine in file " + file.getName() + " has a wrong 'machineType', it must be ENTITY or BLOCK (case sensitive). This machine won't be loaded until you fix this issue", true);
                                    continue;
                                }
                            }

                            if (ymlFile.contains("machineUUID")) {
                                try {
                                    // Throwing exception if it's not a valid UUID
                                    machineUUID = UUID.fromString(ymlFile.getString("machineUUID"));
                                } catch (IllegalArgumentException e) {
                                    plugin.getLogger().log(Level.SEVERE, "Machine in file " + file.getName() + " has a wrong 'machineUUID' format, it will be loaded with a new UUID");
                                    Issue.newIssue(IssueType.MACHINE_READING_ISSUE, "Machine in file " + file.getName() + " has a wrong 'machineUUID' format, it will be loaded with a new UUID", true);
                                }
                            } else {
                                plugin.getLogger().log(Level.SEVERE, "Machine in file " + file.getName() + " has no 'machineUUID', it will be loaded with a new UUID");
                                Issue.newIssue(IssueType.MACHINE_READING_ISSUE, "Machine in file " + file.getName() + " has no 'machineUUID', it will be loaded with a new UUID", true);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Issue.newIssue(IssueType.MACHINE_READING_ISSUE, "Machine in file " + file.getName() + " could not be loaded, see the related exception in server logs", true);
                            SlotPlugin.pl.getLogger().log(Level.SEVERE, "Machine in file " + file.getName() + " could not be loaded, see the related exception");
                            ExceptionCollector.sendException(plugin, e);
                            continue;
                        }
                        SlotMachine slotMachine = null;

                        SlotMachineType slotMachineType = SlotMachineType.valueOf(ymlFile.getString("machineType"));
                        if (slotMachineType == SlotMachineType.ENTITY || slotMachineType == SlotMachineType.ENTITY_LINK) {
                            try {
                                if (slotMachineType == SlotMachineType.ENTITY_LINK) {
                                    UUID linkTo = null;
                                    try {
                                        linkTo = UUID.fromString(ymlFile.getString("linkTo"));
                                    } catch (Exception e) {
                                        SlotPlugin.pl.getLogger().log(Level.SEVERE, "Machine in file " + file.getName() + " has an invalid linkTo. Skipping this machine.");
                                        Issue.newIssue(IssueType.MACHINE_READING_ISSUE, "Machine in file " + file.getName() + " has an invalid linkTo. Skipping this machine.", true);
                                        continue;
                                    }

                                    slotMachine = new SlotMachineEntityLink(linkTo, UUID.fromString(ymlFile.getString("entityUID")));
                                } else {
                                    slotMachine = new SlotMachineEntity(UUID.fromString(ymlFile.getString("entityUID")));
                                }
                            } catch (Exception e) {
                                SlotPlugin.pl.getLogger().log(Level.SEVERE, "Machine in file " + file.getName() + " has an invalid entityUID. Skipping this machine.");
                                Issue.newIssue(IssueType.MACHINE_READING_ISSUE, "Machine in file " + file.getName() + " has an invalid entityUID. Skipping this machine.", true);
                                continue;
                            }
                        } else if (slotMachineType == SlotMachineType.BLOCK || slotMachineType == SlotMachineType.BLOCK_LINK) {
                            UUID worldUUID = Bukkit.getWorlds().get(0).getUID();
                            try {
                                worldUUID = UUID.fromString(ymlFile.getString("worldUID"));
                            } catch (Exception e) {
                                SlotPlugin.pl.getLogger().log(Level.SEVERE, "Machine in file " + file.getName() + " has an invalid worldUID. Skipping this machine.");
                                Issue.newIssue(IssueType.MACHINE_READING_ISSUE, "Machine in file " + file.getName() + " has an invalid worldUID. Skipping this machine.", true);
                                continue;
                            }

                            if (slotMachineType == SlotMachineType.BLOCK_LINK) {
                                UUID linkTo = null;
                                try {
                                    linkTo = UUID.fromString(ymlFile.getString("linkTo"));
                                } catch (Exception e) {
                                    SlotPlugin.pl.getLogger().log(Level.SEVERE, "Machine in file " + file.getName() + " has an invalid linkTo. Skipping this machine.");
                                    Issue.newIssue(IssueType.MACHINE_READING_ISSUE, "Machine in file " + file.getName() + " has an invalid linkTo. Skipping this machine.", true);
                                    continue;
                                }

                                slotMachine = new SlotMachineBlockLink(linkTo, ymlFile.getInt("blockX"), ymlFile.getInt("blockY"), ymlFile.getInt("blockZ"), ymlFile.getBoolean("locked"), worldUUID);
                            } else {
                                slotMachine = new SlotMachineBlock(ymlFile.getInt("blockX"), ymlFile.getInt("blockY"), ymlFile.getInt("blockZ"), ymlFile.getBoolean("locked"), worldUUID);
                            }
                        }

                        slotMachine.setLastFileName(file.getName());
                        slotMachine.setMachineUUID(machineUUID);

                        if (slotMachine.getSlotMachineType() == SlotMachineType.ENTITY || slotMachine.getSlotMachineType() == SlotMachineType.ENTITY_LINK) {
                            if (ymlFile.isSet("isCitizensNPC")) {
                                slotMachine.setCitizensNPC(ymlFile.getBoolean("isCitizensNPC"));
                            }
                        }

                        if (slotMachine.getSlotMachineType() != SlotMachineType.BLOCK_LINK && slotMachine.getSlotMachineType() != SlotMachineType.ENTITY_LINK) {
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
                            if (ymlFile.isSet("hasWinMessage")) {
                                slotMachine.setHasWinMessage(ymlFile.getBoolean("hasWinMessage"));
                            }
                            if (ymlFile.isSet("hasLossMessage")) {
                                slotMachine.setHasLossMessage(ymlFile.getBoolean("hasLossMessage"));
                            }
                            if (ymlFile.isSet("displayItemNameInChat")) {
                                slotMachine.setDisplayWonItemInChat(ymlFile.getBoolean("displayItemNameInChat"));
                            }
                            if (ymlFile.isSet("broadcastWonItem")) {
                                slotMachine.setBroadcastWonItem(ymlFile.getBoolean("broadcastWonItem"));
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
                            if (ymlFile.isSet("coinsEngineCurrencyName")) {
                                slotMachine.setCoinsEngineCurrencyName(ymlFile.getString("coinsEngineCurrencyName"));
                            }

                            if (ymlFile.isSet("backgroundItem")) {
                                if (ymlFile.isItemStack("backgroundItem")) {
                                    slotMachine.setBackgroundItem(ymlFile.getItemStack("backgroundItem"));
                                } else if (ItemStackUtil.isValidMaterial(ymlFile.getString("backgroundItem"))) {
                                    Material mat = Material.getMaterial(ymlFile.getString("backgroundItem"));
                                    mat = mat == Material.AIR ? null : mat;
                                    slotMachine.setBackgroundItem(mat == null ? null : new ItemStack(mat, 1));
                                }
                            }
                            if (ymlFile.isSet("emphasisItem")) {
                                if (ymlFile.isItemStack("emphasisItem")) {
                                    slotMachine.setEmphasisItem(ymlFile.getItemStack("emphasisItem"));
                                } else if (ItemStackUtil.isValidMaterial(ymlFile.getString("emphasisItem"))) {
                                    Material mat = Material.getMaterial(ymlFile.getString("emphasisItem"));
                                    mat = mat == Material.AIR ? null : mat;
                                    slotMachine.setEmphasisItem(mat == null ? null : new ItemStack(mat, 1));
                                }
                            }
                            if (ymlFile.isSet("leverItem")) {
                                if (ymlFile.isItemStack("leverItem")) {
                                    slotMachine.setLeverItem(ymlFile.getItemStack("leverItem"));
                                } else if (ItemStackUtil.isValidMaterial(ymlFile.getString("leverItem"))) {
                                    Material mat = Material.getMaterial(ymlFile.getString("leverItem"));
                                    mat = mat == Material.AIR ? null : mat;
                                    slotMachine.setLeverItem(mat == null ? null : new ItemStack(mat, 1));
                                }
                            }
                            if (ymlFile.isSet("leverItemActivated")) {
                                if (ymlFile.isItemStack("leverItemActivated")) {
                                    slotMachine.setLeverItemActivated(ymlFile.getItemStack("leverItemActivated"));
                                } else if (ItemStackUtil.isValidMaterial(ymlFile.getString("leverItemActivated"))) {
                                    Material mat = Material.getMaterial(ymlFile.getString("leverItemActivated"));
                                    mat = mat == Material.AIR ? null : mat;
                                    slotMachine.setLeverItemActivated(mat == null ? null : new ItemStack(mat, 1));
                                }
                            } else {
                                // Fallback to the regular leverItem, this is to help when upgrading existing machines
                                slotMachine.setLeverItemActivated(new ItemStack(slotMachine.getLeverItem()));
                            }
                            if (ymlFile.isSet("itemListItem")) {
                                if (ymlFile.isItemStack("itemListItem")) {
                                    slotMachine.setItemListItem(ymlFile.getItemStack("itemListItem"));
                                } else if (ItemStackUtil.isValidMaterial(ymlFile.getString("itemListItem"))) {
                                    Material mat = Material.getMaterial(ymlFile.getString("itemListItem"));
                                    mat = mat == Material.AIR ? null : mat;
                                    slotMachine.setItemListItem(mat == null ? null : new ItemStack(mat, 1));
                                }
                            }

                            if (ymlFile.isSet("machineOpeningSound")) {
                                slotMachine.setMachineOpeningSound(SSound.fromInput(ymlFile.getString("machineOpeningSound")));
                            }
                            if (ymlFile.isSet("leverSound")) {
                                slotMachine.setLeverSound(SSound.fromInput(ymlFile.getString("leverSound")));
                            }
                            if (ymlFile.isSet("slotmachineSpinSound")) {
                                slotMachine.setSlotmachineSpinSound(SSound.fromInput(ymlFile.getString("slotmachineSpinSound")));
                            }
                            if (ymlFile.isSet("errorSound")) {
                                slotMachine.setErrorSound(SSound.fromInput(ymlFile.getString("errorSound")));
                            }
                            if (ymlFile.isSet("csgoSpinSound")) {
                                slotMachine.setCsgoSpinSound(SSound.fromInput(ymlFile.getString("csgoSpinSound")));
                            }
                            if (ymlFile.isSet("winSound")) {
                                slotMachine.setWinSound(SSound.fromInput(ymlFile.getString("winSound")));
                            }
                            if (ymlFile.isSet("lossSound")) {
                                slotMachine.setLossSound(SSound.fromInput(ymlFile.getString("lossSound")));
                            }
                            if (ymlFile.isSet("errorSound")) {
                                slotMachine.setErrorSound(SSound.fromInput(ymlFile.getString("errorSound")));
                            }

                            List<MachineItem> slotMachineItems = new ArrayList<MachineItem>();
                            if (ymlFile.contains("items")) {
                                Set<String> items = ymlFile.getConfigurationSection("items").getKeys(false);

                                int i = 0;

                                for (String itm : items) {
                                    if (ymlFile.isSet("itemCount")) {
                                        if (!(i < ymlFile.getInt("itemCount"))) {
                                            break;
                                        }
                                        i++;
                                    }
                                    String item = "items." + itm;
									boolean showAttributeModifiers = ymlFile.getBoolean(item + ".showAttributeModifiers", true);

                                    if (ymlFile.isSet(item + ".reward")) {
                                        MachineItem.RewardType rewardType = ymlFile.isSet(item + ".rewardType") ? MachineItem.RewardType.valueOf(ymlFile.getString(item + ".rewardType")) : null;
                                        if (rewardType != null) {
                                            if (rewardType == RewardType.ITEM) {
                                                slotMachineItems.add(new MachineItem(ymlFile.getItemStack(item + ".item"), ymlFile.getInt(item + ".weight"), new ArrayList<Reward>(Arrays.asList(new MachineItem.Reward(ymlFile.getItemStack(item + ".reward")))), showAttributeModifiers));
                                            } else if (rewardType == RewardType.COMMAND) {
                                                slotMachineItems.add(new MachineItem(ymlFile.getItemStack(item + ".item"), ymlFile.getInt(item + ".weight"), new ArrayList<Reward>(Arrays.asList(new MachineItem.Reward(ymlFile.getString(item + ".reward")))), showAttributeModifiers));
                                            }
                                        } else {
                                            slotMachineItems.add(new MachineItem(ymlFile.getItemStack(item + ".item"), ymlFile.getInt(item + ".weight")));
                                        }
                                    } else if (ymlFile.isSet(item + ".rewards")) {
                                        Set<String> strRewards = ymlFile.getConfigurationSection(item + ".rewards").getKeys(false);
                                        List<Reward> rewards = new ArrayList<Reward>();

                                        for (String str : strRewards) {
                                            if (ymlFile.isSet(item + ".rewards." + str + ".type") && (ymlFile.isSet(item + ".rewards." + str + ".item") || ymlFile.isSet(item + ".rewards." + str + ".command"))) {
                                                if (RewardType.valueOf(ymlFile.getString(item + ".rewards." + str + ".type")) == RewardType.ITEM) {
                                                    rewards.add(new MachineItem.Reward(ymlFile.getItemStack(item + ".rewards." + str + ".item")));
                                                } else if (RewardType.valueOf(ymlFile.getString(item + ".rewards." + str + ".type")) == RewardType.COMMAND) {
                                                    rewards.add(new MachineItem.Reward(ymlFile.getString(item + ".rewards." + str + ".command")));
                                                }
                                            } else {
                                                Issue.newIssue(IssueType.MACHINE_READING_ISSUE, "Machine " + slotMachine.getMachineUUID().toString() + " has a malformed item reward (" + str + ")", true);
                                                SlotPlugin.pl.getLogger().log(Level.SEVERE, "Machine " + slotMachine.getMachineUUID().toString() + " has a malformed item reward (" + str + "), we're not loading this reward");
                                            }
                                        }
                                        MachineItem it = new MachineItem(ymlFile.getItemStack(item + ".item"), ymlFile.getInt(item + ".weight"), rewards, showAttributeModifiers);

                                        if (ymlFile.isSet(item + ".stats.timesWon")) {
                                            it.itemStats.timesWon = ymlFile.getInt(item + ".stats.timesWon");
                                        }

                                        slotMachineItems.add(it);
                                    } else {
                                        slotMachineItems.add(
                                                new MachineItem(
                                                        ymlFile.getItemStack(item + ".item"),
                                                        ymlFile.getInt(item + ".weight")
                                                )
                                        );
                                    }
                                }
                            }
                            slotMachine.setSlotMachineItems(slotMachineItems);
                        }

                        switch (slotMachine.getSlotMachineType()) {
                            case ENTITY:
                                SlotMachineEntity.addSlotMachineEntity((SlotMachineEntity) slotMachine);
                                break;
                            case ENTITY_LINK:
                                SlotMachineEntity.addSlotMachineEntity((SlotMachineEntityLink) slotMachine);
                                break;
                            case BLOCK:
                                SlotMachineBlock.addSlotMachineBlock((SlotMachineBlock) slotMachine);
                                break;
                            case BLOCK_LINK:
                                SlotMachineBlock.addSlotMachineBlock((SlotMachineBlockLink) slotMachine);
                                break;
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
                        slotMachine = new SlotMachineEntity(UUID.fromString(SlotPlugin.pl.getConfig().getString("slotMachine." + a + ".entityUID")));
                        break;
                    case ENTITY:
                        slotMachine = new SlotMachineEntity(UUID.fromString(SlotPlugin.pl.getConfig().getString("slotMachine." + a + ".entityUID")));
                        break;
                    default:
                        slotMachine = new SlotMachineEntity(UUID.fromString(SlotPlugin.pl.getConfig().getString("slotMachine." + a + ".entityUID")));
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
                                slotMachineItems.add(new MachineItem(SlotPlugin.pl.getConfig().getItemStack("items." + b + ".item"), SlotPlugin.pl.getConfig().getInt("items." + b + ".weight"), Arrays.asList(new MachineItem.Reward(SlotPlugin.pl.getConfig().getItemStack("items." + b + ".reward"))), true));
                            } else if (rewardType == RewardType.COMMAND) {
                                slotMachineItems.add(new MachineItem(SlotPlugin.pl.getConfig().getItemStack("items." + b + ".item"), SlotPlugin.pl.getConfig().getInt("items." + b + ".weight"), Arrays.asList(new MachineItem.Reward(SlotPlugin.pl.getConfig().getString("items." + b + ".reward"))), true));
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

                        slotMachineItems.add(new MachineItem(SlotPlugin.pl.getConfig().getItemStack("items." + b + ".item"), SlotPlugin.pl.getConfig().getInt("items." + b + ".weight"), rewards, true));
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
                SlotMachineEntity slotMachineEntity = new SlotMachineEntity(UUID.fromString(SlotPlugin.pl.getConfig().getString("slotMachine." + a + ".entityUID")));
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
        SlotPlugin.saveMachinesToDisk(true);
        plugin.saveConfig();
    }
}
