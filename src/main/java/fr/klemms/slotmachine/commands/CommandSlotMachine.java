package fr.klemms.slotmachine.commands;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.threads.ThreadGiveItem;
import fr.klemms.slotmachine.translation.Language;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CommandSlotMachine implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			ItemStack magicWand = new ItemStack(Material.BLAZE_ROD);
			ItemMeta magicWandMeta = magicWand.getItemMeta();
			magicWandMeta.setDisplayName(ChatContent.GOLD + ChatContent.BOLD + Language.translate("command.slotmachine"));
			List<String> magicWandLore = new ArrayList<String>();
			magicWandLore.add(ChatContent.AQUA + ChatContent.ITALIC + Language.translate("command.slotmachine.line1"));
			magicWandLore.add(ChatContent.AQUA + ChatContent.ITALIC + Language.translate("command.slotmachine.line2"));
			magicWandMeta.setLore(magicWandLore);
			magicWand.setItemMeta(magicWandMeta);

			Bukkit.getScheduler().scheduleSyncDelayedTask(SlotPlugin.pl, new ThreadGiveItem(magicWand, ((Player)sender).getUniqueId()));
		}
		return true;
	}
}
