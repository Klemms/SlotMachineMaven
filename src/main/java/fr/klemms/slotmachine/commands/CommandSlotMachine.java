package fr.klemms.slotmachine.commands;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandSlotMachine implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			sender.sendMessage(ChatContent.AQUA + "[Slot Machine] Get a '" + new ItemStack(Config.adminToolMaterial).getItemMeta().getLocalizedName() + "' and right-click any entity or block.");
		}
		return true;
	}
}
