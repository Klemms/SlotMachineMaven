package fr.klemms.slotmachine.commands;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.Config;
import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandReloadMachines implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		sender.sendMessage(ChatContent.PINK + SlotPlugin.CHAT_PREFIX + "Please make sure no one is currently using machines...");
		sender.sendMessage(ChatContent.PINK + SlotPlugin.CHAT_PREFIX + "It is recommended to run /smsavetodisk before making changes to files");
		SlotMachine.slotMachines.clear();
		Config.loadMachines(SlotPlugin.pl);
		Config.tokens.clear();
		SlotPlugin.readTokens();
		sender.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + "Machines and tokens reloaded");

		return true;
	}
}
