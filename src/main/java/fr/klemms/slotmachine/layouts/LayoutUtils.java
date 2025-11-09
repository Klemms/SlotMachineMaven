package fr.klemms.slotmachine.layouts;

import com.bencodez.votingplugin.user.VotingPluginUser;
import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.Config;
import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.fr.minuskube.inv.ClickableItem;
import fr.klemms.slotmachine.placeholders.Variables;
import fr.klemms.slotmachine.translation.Language;
import fr.klemms.slotmachine.utils.ItemStackUtil;
import fr.klemms.slotmachine.utils.Util;
import me.realized.tm.api.TMAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import su.nightexpress.coinsengine.api.CoinsEngineAPI;
import su.nightexpress.coinsengine.api.currency.Currency;

public class LayoutUtils {

    public static final ClickableItem leverNoPermission = ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(Material.BARRIER, 1), ChatContent.translateColorCodes(Language.translate(Config.noAccessDefaultString))));

    public static boolean triggerLever(Player player, SlotMachine machine) {
        player.setMetadata("slotmachine_soundremovalprevention", new FixedMetadataValue(SlotPlugin.pl, true));
        Bukkit.getScheduler().runTask(SlotPlugin.pl, () -> {
            if (player.hasMetadata("slotmachine_soundremovalprevention"))
                player.removeMetadata("slotmachine_soundremovalprevention", SlotPlugin.pl);
        });

        switch(machine.getPriceType()) {
            case MONEY:
                if(SlotPlugin.econ.getBalance(player) >= machine.getPullPrice()) {
                    if (!testAndApplyCooldown(player, machine))
                        return false;
                    SlotPlugin.econ.withdrawPlayer(player, machine.getPullPrice());
                    return true;
                } else {
                    player.sendMessage(Variables.getFormattedString(Language.translate(Config.notEnoughMoneyDefaultString), player, machine));
                    Util.playSoundPlayer(player, machine.getErrorSound(), 1.3f, 1f);
                    return false;
                }
            case EXPERIENCE:
                if(player.getLevel() >= (int)machine.getPullPrice()) {
                    if (!testAndApplyCooldown(player, machine))
                        return false;
                    player.setLevel(player.getLevel() - (int)machine.getPullPrice());
                    return true;
                } else {
                    player.sendMessage(Variables.getFormattedString(Language.translate("experience.notenough"), player, machine));
                    Util.playSoundPlayer(player, machine.getErrorSound(), 1.3f, 1f);
                    return false;
                }
            case VOTINGPLUGIN:
                if (SlotPlugin.votingPlugin != null) {
                    VotingPluginUser user = SlotPlugin.votingPlugin.getUserManager().getVotingPluginUser(player);

                    if (user == null) {
                        player.sendMessage(Variables.getFormattedString(Language.translate("votingplugin.notenough"), player, machine));
                        Util.playSoundPlayer(player, machine.getErrorSound(), 1.3f, 1f);
                        return false;
                    }
                    if (!testAndApplyCooldown(player, machine))
                        return false;
                    if (user.removePoints((int)machine.getPullPrice())) {
                        return true;
                    } else {
                        player.sendMessage(Variables.getFormattedString(Language.translate("votingplugin.notenough"), player, machine));
                        Util.playSoundPlayer(player, machine.getErrorSound(), 1.3f, 1f);
                        return false;
                    }
                } else {
                    player.sendMessage(ChatContent.RED + SlotPlugin.CHAT_PREFIX + Language.translate("slotmachine.access.missingvotingplugin"));
                    Util.playSoundPlayer(player, machine.getErrorSound(), 1.3f, 1f);
                    return false;
                }
            case COINSENGINE:
                if(SlotPlugin.isCoinsEngineEnabled) {
                    Currency currency = CoinsEngineAPI.getCurrency(machine.getCoinsEngineCurrencyID());

                    if (currency == null) {
                        player.sendMessage(Variables.getFormattedString(
                                Language.translate("coinsengine.invalidcurrency").replace("%currency%", machine.getCoinsEngineCurrencyID()),
                                player,
                                machine
                        ));
                        Util.playSoundPlayer(player, machine.getErrorSound(), 1.3f, 1f);
                        return false;
                    }

                    double userBalance = CoinsEngineAPI.getBalance(player, currency);

                    if(userBalance >= ((int)machine.getPullPrice())) {
                        if (!testAndApplyCooldown(player, machine))
                            return false;
                        CoinsEngineAPI.removeBalance(player, currency, ((int)machine.getPullPrice()));
                        return true;
                    } else {
                        player.sendMessage(Variables.getFormattedString(Language.translate(Config.notEnoughCoinsEngineCurrencyDefaultString).replace("%currency%", currency.getName()), player, machine));
                        Util.playSoundPlayer(player, machine.getErrorSound(), 1.3f, 1f);
                        return false;
                    }
                } else {
                    player.sendMessage(ChatContent.RED + SlotPlugin.CHAT_PREFIX + Language.translate("slotmachine.access.missingcoinsengine"));
                    Util.playSoundPlayer(player, machine.getErrorSound(), 1.3f, 1f);
                    return false;
                }
            case PLAYERPOINTS:
                if(SlotPlugin.playerPointsAPI != null) {
                    if (SlotPlugin.playerPointsAPI.look(player.getUniqueId()) >= ((int)machine.getPullPrice())) {
                        if (!testAndApplyCooldown(player, machine)) {
                            return false;
                        }
                        SlotPlugin.playerPointsAPI.take(player.getUniqueId(), ((int)machine.getPullPrice()));
                        return true;
                    } else {
                        player.sendMessage(Variables.getFormattedString(Language.translate("playerpoints.notenough"), player, machine));
                        Util.playSoundPlayer(player, machine.getErrorSound(), 1.3f, 1f);
                        return false;
                    }
                } else {
                    player.sendMessage(ChatContent.RED + SlotPlugin.CHAT_PREFIX + Language.translate("slotmachine.access.missingplayerpoints"));
                    Util.playSoundPlayer(player, machine.getErrorSound(), 1.3f, 1f);
                    return false;
                }
            case TOKEN:
                if(player.getInventory().containsAtLeast(machine.getToken(), (int)machine.getPullPrice())) {
                    if (!testAndApplyCooldown(player, machine))
                        return false;
                    player.getInventory().removeItem(ItemStackUtil.changeItemStackAmount(new ItemStack(machine.getToken()), (int)machine.getPullPrice()));
                    return true;
                } else {
                    player.sendMessage(Variables.getFormattedString(Language.translate(Config.notEnoughTokensDefaultString), player, machine));
                    Util.playSoundPlayer(player, machine.getErrorSound(), 1.3f, 1f);
                    return false;
                }
            case TOKENMANAGER:
                if(SlotPlugin.oldTokenManagerWorks || SlotPlugin.tokenManager != null) {
                    if(SlotPlugin.oldTokenManagerWorks && TMAPI.getTokens(player.getUniqueId()) >= (long)machine.getPullPrice()) {
                        if (!testAndApplyCooldown(player, machine))
                            return false;
                        TMAPI.removeTokens(player.getUniqueId(), (int)machine.getPullPrice());
                        return true;
                    } else if (SlotPlugin.tokenManager != null && SlotPlugin.tokenManager.getTokens(player).getAsLong() >= (long)machine.getPullPrice()) {
                        if (!testAndApplyCooldown(player, machine))
                            return false;
                        SlotPlugin.tokenManager.setTokens(player, SlotPlugin.tokenManager.getTokens(player).getAsLong() - (long)machine.getPullPrice());
                        return true;
                    } else {
                        player.sendMessage(Variables.getFormattedString(Language.translate(Config.notEnoughTokensManagerDefaultString), player, machine));
                        Util.playSoundPlayer(player, machine.getErrorSound(), 1.3f, 1f);
                        return false;
                    }
                } else {
                    player.sendMessage(ChatContent.RED + SlotPlugin.CHAT_PREFIX + Language.translate("slotmachine.access.missingtokenmanager"));
                    Util.playSoundPlayer(player, machine.getErrorSound(), 1.3f, 1f);
                    return false;
                }
            default:
                Util.playSoundPlayer(player, machine.getErrorSound(), 1.3f, 1f);
                return false;
        }
    }

    /**
     * Tests if the player is in cooldown and applies the cooldown if not
     * @param player The player to test
     * @param machine The machine to test
     * @return True if the player is not in cooldown or the cooldown is 0, false otherwise
     */
    public static boolean testAndApplyCooldown(Player player, SlotMachine machine) {
        if (machine.isPlayerInCooldown(player)) {
            player.sendMessage(Variables.getFormattedString(ChatContent.GOLD +  "[" + machine.getSlotMachineName() + "] " + Language.translate("error.cooldown").replace("%cooldown%", String.valueOf(machine.getPlayerCooldown(player))), player, machine));
            Util.playSoundPlayer(player, machine.getErrorSound(), 1.3f, 1f);
            return false;
        } else if (machine.getCooldown() > 0) {
            machine.setPlayerCooldown(player, machine.getCooldown());
            return true;
        }
        return true;
    }
}
