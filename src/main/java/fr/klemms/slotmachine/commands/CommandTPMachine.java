package fr.klemms.slotmachine.commands;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.SlotMachineEntity;
import fr.klemms.slotmachine.SlotMachineEntityLink;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.translation.Language;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CommandTPMachine implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(args.length == 1 && sender instanceof Player) {
			SlotMachineEntity slotMachine = SlotMachineEntityLink.getAllSlotMachineByEntityUUID(UUID.fromString(args[0]));
			if(slotMachine != null) {
				if(slotMachine.getEntity() != null) {
					slotMachine.getEntity().teleport(((Player)sender).getLocation());
					slotMachine.save();
					sender.sendMessage(ChatContent.RED + SlotPlugin.CHAT_PREFIX + Language.translate("command.tpmachine.successful"));
					return true;
				}
				sender.sendMessage(ChatContent.RED + SlotPlugin.CHAT_PREFIX + Language.translate("command.tpmachine.cantfindentity"));
				return false;
			}
			sender.sendMessage(ChatContent.RED + SlotPlugin.CHAT_PREFIX + Language.translate("command.tpmachine.cantfindmachine"));
			return false;
		}
		return false;
	}
}
