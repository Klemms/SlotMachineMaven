package fr.klemms.slotmachine.commands;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.PriceType;
import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.translation.Language;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CommandOpenMachine implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(args.length == 2 || args.length == 3) {
			boolean overridePermission = false;
			if(args.length == 3) {
				if(args[2].equals("true")) {
					overridePermission = true;
				}
			}
			Player player = Bukkit.getPlayer(args[0]);
			UUID machineUUID = null;
			try {
				machineUUID = UUID.fromString(args[1]);
			} catch(IllegalArgumentException e) {
				sender.sendMessage(ChatContent.RED + SlotPlugin.CHAT_PREFIX + "Invalid Slot Machine UUID");
				return false;
			}
			SlotMachine slotMachine = SlotMachine.getSlotMachineByUUID(machineUUID);
			if(player != null) {
				if (slotMachine != null) {
					if(slotMachine.playerHasPermission(player) || overridePermission) {
						if(slotMachine.getSlotMachineItems().size() > 0) {
							if(slotMachine.getPriceType() == PriceType.GAMEPOINTS && !SlotPlugin.isGamePointsEnabled) {
								sender.sendMessage(ChatContent.RED + SlotPlugin.CHAT_PREFIX + Language.translate("slotmachine.access.missinggamepoints"));
							} else if(slotMachine.getPriceType() == PriceType.MONEY && SlotPlugin.econ == null) {
								sender.sendMessage(ChatContent.RED + SlotPlugin.CHAT_PREFIX + Language.translate("slotmachine.access.missingvault"));
							} else {
								player.closeInventory();
								slotMachine.openMachine(player, true);
								player.playSound(player.getLocation(), slotMachine.getMachineOpeningSound().getKey(), 1.9f, 1.2f);
								sender.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + "Successfully opened machine for " + player.getName());
							}
						} else {
							sender.sendMessage(ChatContent.RED + SlotPlugin.CHAT_PREFIX + Language.translate("command.openmachine.noitems"));
						}
					} else {
						sender.sendMessage(ChatContent.RED + SlotPlugin.CHAT_PREFIX + Language.translate("command.openmachine.nopermissionandhelp"));
						player.sendMessage(ChatContent.RED + SlotPlugin.CHAT_PREFIX + Language.translate("permission.denied"));
					}
					return true;
				}
				sender.sendMessage(ChatContent.RED + SlotPlugin.CHAT_PREFIX + "Slot Machine not detected");
				return true;
			}
			sender.sendMessage(ChatContent.RED + SlotPlugin.CHAT_PREFIX + "Player not detected");
			return true;
		}
		sender.sendMessage(ChatContent.RED + SlotPlugin.CHAT_PREFIX + "Not enough arguments");
		return false;
	}
}
