package fr.klemms.slotmachine.commands;

import fr.klemms.slotmachine.Config;
import fr.klemms.slotmachine.Setup;
import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.translation.Language;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandReload implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		sender.sendMessage("[SlotMachine] " + Language.translate("command.slotmachinereload.starting"));
		SlotPlugin.pl.reloadConfig();
		SlotMachine.slotMachines.clear();
		Setup.setupLanguages(SlotPlugin.pl);
		Config.registerConfig(SlotPlugin.pl);
		Config.readConfig(SlotPlugin.pl);
		Config.loadMachines(SlotPlugin.pl);
		sender.sendMessage("[SlotMachine] " + Language.translate("command.slotmachinereload.successful"));
		
		return true;
	}
}
