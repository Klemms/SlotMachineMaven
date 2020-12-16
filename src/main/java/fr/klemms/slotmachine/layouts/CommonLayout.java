package fr.klemms.slotmachine.layouts;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.Config;
import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.placeholders.Variables;
import fr.klemms.slotmachine.translation.Language;
import fr.klemms.slotmachine.utils.ItemStackUtil;
import fr.minuskube.inv.ClickableItem;
import me.realized.tm.api.TMAPI;

public class CommonLayout {
	
	public static final ClickableItem leverNoPermission = ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(Material.BARRIER, 1), ChatContent.translateColorCodes(Language.translate(Config.noAccessDefaultString))));

	public static boolean triggerLever(Player player, SlotMachine machine) {
		switch(machine.getPriceType()) {
			case MONEY:
				if(SlotPlugin.econ.getBalance(player) >= machine.getPullPrice()) {
					SlotPlugin.econ.withdrawPlayer(player, machine.getPullPrice());
					return true;
				} else {
					player.sendMessage(Variables.getFormattedString(Language.translate(Config.notEnoughMoneyDefaultString), player, machine));
					player.playSound(player.getLocation(), machine.getMachineOpeningSound(), 1.9f, 0.3f);
					return false;
				}
			case EXPERIENCE:
				if(player.getLevel() >= (int)machine.getPullPrice()) {
					player.setLevel(player.getLevel() - (int)machine.getPullPrice());
					return true;
				} else {
					player.sendMessage(Variables.getFormattedString(Language.translate("experience.notenough"), player, machine));
					player.playSound(player.getLocation(), machine.getMachineOpeningSound(), 1.9f, 0.3f);
					return false;
				}
			case PLAYERPOINTS:
				if(SlotPlugin.playerPoints != null) {
					if(SlotPlugin.playerPoints.getAPI().look(player.getUniqueId()) >= (int)machine.getPullPrice()) {
						SlotPlugin.playerPoints.getAPI().take(player.getUniqueId(), (int)machine.getPullPrice());
						return true;
					} else {
						player.sendMessage(Variables.getFormattedString(Language.translate(Config.notEnoughPlayerPointsDefaultString), player, machine));
						player.playSound(player.getLocation(), machine.getMachineOpeningSound(), 1.9f, 0.3f);
						return false;
					}
				} else {
					player.sendMessage(ChatContent.RED + machine.getChatName() + Language.translate("slotmachine.access.missingplayerpoints"));
					return false;
				}
			case TOKEN:
				if(player.getInventory().containsAtLeast(machine.getToken(), (int)machine.getPullPrice())) {
					player.getInventory().removeItem(ItemStackUtil.changeItemStackAmount(new ItemStack(machine.getToken()), (int)machine.getPullPrice()));
					return true;
				} else {
					player.sendMessage(Variables.getFormattedString(Language.translate(Config.notEnoughTokensDefaultString), player, machine));
					player.playSound(player.getLocation(), machine.getMachineOpeningSound(), 1.9f, 0.3f);
					return false;
				}
			case TOKENMANAGER:
				if(SlotPlugin.oldTokenManagerWorks || SlotPlugin.tokenManager != null) {
					if(SlotPlugin.oldTokenManagerWorks && TMAPI.getTokens(player.getUniqueId()) >= (long)machine.getPullPrice()) {
						TMAPI.removeTokens(player.getUniqueId(), (int)machine.getPullPrice());
						return true;
					} else if (SlotPlugin.tokenManager != null && SlotPlugin.tokenManager.getTokens(player).getAsLong() >= (long)machine.getPullPrice()){
						SlotPlugin.tokenManager.setTokens(player, SlotPlugin.tokenManager.getTokens(player).getAsLong() - (long)machine.getPullPrice());
						return true;
					} else {	
						player.sendMessage(Variables.getFormattedString(Language.translate(Config.notEnoughTokensManagerDefaultString), player, machine));
						player.playSound(player.getLocation(), machine.getMachineOpeningSound(), 1.9f, 0.3f);
						return false;
					}
				} else {
					player.sendMessage(ChatContent.RED + "[Slot Machine] " + Language.translate("slotmachine.access.missingtokenmanager"));
					return false;
				}
			default:
				return false;
		}
	}
}
