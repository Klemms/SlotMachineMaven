package fr.klemms.slotmachine.commands;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.SlotPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandSlotMachineVersion implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		sender.sendMessage(ChatContent.AQUA + SlotPlugin.CHAT_PREFIX + "This server is running Slot Machine version " + SlotPlugin.pl.getDescription().getVersion() + " (Update " + SlotPlugin.VERSION + ") for " + SlotPlugin.MC_FOR);
		sender.sendMessage(ChatContent.AQUA + SlotPlugin.CHAT_PREFIX + "Currently detected soft-dependencies :");
		if (SlotPlugin.econ != null)
			sender.sendMessage(ChatContent.AQUA + SlotPlugin.CHAT_PREFIX + "- Vault");
		if (SlotPlugin.isCoinsEngineEnabled)
			sender.sendMessage(ChatContent.AQUA + SlotPlugin.CHAT_PREFIX + "- CoinsEngine");
		if (SlotPlugin.tokenManager != null)
			sender.sendMessage(ChatContent.AQUA + SlotPlugin.CHAT_PREFIX + "- TokenManager");
		if (SlotPlugin.isCitizensEnabled)
			sender.sendMessage(ChatContent.AQUA + SlotPlugin.CHAT_PREFIX + "- Citizens");
		if (SlotPlugin.votingPlugin != null)
			sender.sendMessage(ChatContent.AQUA + SlotPlugin.CHAT_PREFIX + "- VotingPlugin");
		if (SlotPlugin.isPlaceholderAPIEnabled)
			sender.sendMessage(ChatContent.AQUA + SlotPlugin.CHAT_PREFIX + "- PlaceholderAPI");

		return true;
	}
}
