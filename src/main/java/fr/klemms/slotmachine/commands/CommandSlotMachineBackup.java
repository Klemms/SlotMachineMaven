package fr.klemms.slotmachine.commands;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.Setup;
import fr.klemms.slotmachine.SlotPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CommandSlotMachineBackup implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		sender.sendMessage(ChatContent.PINK + SlotPlugin.CHAT_PREFIX + "Saving all machines to disk...");
		SlotPlugin.suspendSaving = true;
		SlotPlugin.saveMachinesToDisk(true);
		SimpleDateFormat dateFormat = new SimpleDateFormat("HHmmss_dd-MMM-yyyy", new Locale.Builder().setLanguage("en").setRegion("US").build());
		String backupName = "MACHINES_BACKUP-" + dateFormat.format(new Date());
		sender.sendMessage(ChatContent.PINK + SlotPlugin.CHAT_PREFIX + "Creating a backup of /machines to : /" + backupName);

		if (Setup.makeBackup(backupName)) {
			sender.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + "Backup successfully created");
		} else {
			sender.sendMessage(ChatContent.RED + SlotPlugin.CHAT_PREFIX + "An issue occurred while making a backup, please check the logs or ask operators.");
		}
		SlotPlugin.suspendSaving = false;

		return true;
	}
}
