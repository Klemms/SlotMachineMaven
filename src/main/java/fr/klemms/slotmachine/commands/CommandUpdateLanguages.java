package fr.klemms.slotmachine.commands;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.Config;
import fr.klemms.slotmachine.Setup;
import fr.klemms.slotmachine.SlotPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandUpdateLanguages implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!Config.enableLanguageOTAUpdates) {
			sender.sendMessage(ChatContent.RED + SlotPlugin.CHAT_PREFIX + "Language OTA updates are disabled in the config file. Please change 'enableLanguageOTAUpdates' to 'true' and try again.");
			return true;
		}

		long date = System.currentTimeMillis();
		sender.sendMessage(ChatContent.PINK + SlotPlugin.CHAT_PREFIX + "Fetching updates to '" + Config.language + "' from Crowdin...");
		if (Setup.setupOTALanguages(SlotPlugin.pl)) {
			sender.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + "Updates fetched and applied for '" + Config.language + "' (Update took " + (System.currentTimeMillis() - date) + "ms)");
		} else {
			sender.sendMessage(ChatContent.RED + SlotPlugin.CHAT_PREFIX + "There was an issue while fetching updates for '" + Config.language + "', please check the logs");
		}
		return true;
	}
}
