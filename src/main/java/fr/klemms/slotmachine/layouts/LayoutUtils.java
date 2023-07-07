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
import me.realized.tm.api.TMAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import su.nightexpress.gamepoints.api.GamePointsAPI;
import su.nightexpress.gamepoints.data.PointUser;

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
                    player.playSound(player.getLocation(), machine.getErrorSound(), 1.3f, 1f);
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
                    player.playSound(player.getLocation(), machine.getErrorSound(), 1.3f, 1f);
                    return false;
                }
            case VOTINGPLUGIN:
                if (SlotPlugin.votingPlugin != null) {
                    VotingPluginUser user = SlotPlugin.votingPlugin.getUserManager().getVotingPluginUser(player);

                    if (user == null) {
                        player.sendMessage(Variables.getFormattedString(Language.translate("votingplugin.notenough"), player, machine));
                        player.playSound(player.getLocation(), machine.getErrorSound(), 1.3f, 1f);
                        return false;
                    }
                    if (!testAndApplyCooldown(player, machine))
                        return false;
                    if (user.removePoints((int)machine.getPullPrice())) {
                        return true;
                    } else {
                        player.sendMessage(Variables.getFormattedString(Language.translate("votingplugin.notenough"), player, machine));
                        player.playSound(player.getLocation(), machine.getErrorSound(), 1.3f, 1f);
                        return false;
                    }
                } else {
                    player.sendMessage(ChatContent.RED + machine.getChatName() + Language.translate("slotmachine.access.missingvotingplugin"));
                    player.playSound(player.getLocation(), machine.getErrorSound(), 1.3f, 1f);
                    return false;
                }
            case GAMEPOINTS:
                if(SlotPlugin.isGamePointsEnabled) {
                    PointUser user = GamePointsAPI.getUserData(player);
                    if(user != null && user.getBalance() >= (int)machine.getPullPrice()) {
                        if (!testAndApplyCooldown(player, machine))
                            return false;
                        user.takePoints((int)machine.getPullPrice());
                        return true;
                    } else {
                        player.sendMessage(Variables.getFormattedString(Language.translate(Config.notEnoughGamePointsDefaultString), player, machine));
                        player.playSound(player.getLocation(), machine.getErrorSound(), 1.3f, 1f);
                        return false;
                    }
                } else {
                    player.sendMessage(ChatContent.RED + machine.getChatName() + Language.translate("slotmachine.access.missinggamepoints"));
                    player.playSound(player.getLocation(), machine.getErrorSound(), 1.3f, 1f);
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
                    player.playSound(player.getLocation(), machine.getErrorSound(), 1.3f, 1f);
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
                        player.playSound(player.getLocation(), machine.getErrorSound(), 1.3f, 1f);
                        return false;
                    }
                } else {
                    player.sendMessage(ChatContent.RED + "[Slot Machine] " + Language.translate("slotmachine.access.missingtokenmanager"));
                    player.playSound(player.getLocation(), machine.getErrorSound(), 1.3f, 1f);
                    return false;
                }
            default:
                player.playSound(player.getLocation(), machine.getErrorSound(), 1.3f, 1f);
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
            player.playSound(player.getLocation(), machine.getErrorSound(), 1.3f, 1f);
            return false;
        } else if (machine.getCooldown() > 0) {
            machine.setPlayerCooldown(player, machine.getCooldown());
            return true;
        }
        return true;
    }
}
