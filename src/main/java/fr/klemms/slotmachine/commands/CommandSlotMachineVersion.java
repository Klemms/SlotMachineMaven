package fr.klemms.slotmachine.commands;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.SlotPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandSlotMachineVersion implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		sender.sendMessage(ChatContent.AQUA + "[Slot Machine] This server is running Slot Machine version " + SlotPlugin.pl.getDescription().getVersion() + " (Update " + SlotPlugin.VERSION + ") for " + SlotPlugin.MC_FOR);
		
		return true;
	}
}
