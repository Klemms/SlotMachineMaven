package fr.klemms.slotmachine.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.Config;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.interraction.ConfirmInventory;
import fr.klemms.slotmachine.tokens.Token;
import fr.klemms.slotmachine.tokens.TokenSelectionListener;
import fr.klemms.slotmachine.tokens.TokensInventory;
import fr.klemms.slotmachine.utils.ItemStackUtil;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;

public class CommandSlotMachineTokens implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player)sender;
			if (args.length > 0) {
				switch(args[0]) {
					case "list":
						final TokenSelectionListener listToken = new TokenSelectionListener() {
							@Override
							public void callback(Player player, Token token) {
								if (!token.identifier.equals("default")) {
									ConfirmInventory.confirmWindow(player, "Delete this token ? (" + ChatContent.ITALIC + token.identifier + ChatContent.RESET + ")", "No, cancel", "Yes, delete '" + token.identifier + "'", (boolean result) -> {
										if (result) {
											player.sendMessage(ChatContent.AQUA + "[Slot Machine] Token " + token.identifier + " has been removed");
											Config.tokens.remove(token.identifier);
											SlotPlugin.writeTokens();
										}
									}, true);
								} else {
									player.sendMessage(ChatContent.AQUA + "[Slot Machine] You can't delete your default token. To replace it add a token with the name 'default'");
								}
							}
						};
						
						TokensInventory.showManagementScreen(player, 0, "Tokens Manager", "Left click to remove this token", listToken, true);
						return true;
					case "add":
						if(args.length == 2) {
							ItemStack is = player.getInventory().getItemInMainHand();
							if (is != null && is.getType() != Material.AIR) {
								Config.tokens.put(args[1].toLowerCase(), ItemStackUtil.changeItemStackAmount(new ItemStack(player.getInventory().getItemInMainHand()), 1));
								SlotPlugin.writeTokens();
								
								BaseComponent[] successMessage = new ComponentBuilder("[Slot Machine] Token '" + args[1].toLowerCase() + "' has been added ")
								.color(ChatColor.GREEN)
								.append(new ComponentBuilder("[Open Token List]")
										.color(ChatColor.AQUA)
										.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click to open").create()))
										.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/slotmachinetoken list"))
										.create())
								.create();
								
								player.spigot().sendMessage(successMessage);
							} else
								player.sendMessage(ChatContent.AQUA + "[Slot Machine] Have an item in your main hand before executing this command");
							return true;
						}
						player.sendMessage(ChatContent.RED + "[Slot Machine] Please add an identifier for this Token");
						return false;
					default:
						return false;
				}
			}
			return false;
		}
		sender.sendMessage(ChatContent.RED + "[Slot Machine] You must be a player");
		return false;
	}
}
