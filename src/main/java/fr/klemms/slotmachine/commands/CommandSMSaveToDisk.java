package fr.klemms.slotmachine.commands;

import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.translation.Language;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandSMSaveToDisk implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		sender.sendMessage("[SlotMachine] " + Language.translate("command.smsavetodisk.saving"));
		SlotPlugin.saveToDisk();
		sender.sendMessage("[SlotMachine] " + Language.translate("command.smsavetodisk.saved"));
		
		return true;
	}
}
