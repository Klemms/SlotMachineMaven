package fr.klemms.slotmachine.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.Config;
import fr.klemms.slotmachine.tokens.Token;
import fr.klemms.slotmachine.tokens.TokenSelectionListener;
import fr.klemms.slotmachine.tokens.TokensInventory;
import fr.klemms.slotmachine.utils.ItemStackUtil;

public class CommandGiveTokens implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(args.length >= 2) {
			List<Player> players = new ArrayList<Player>();
			if(args[0].equalsIgnoreCase("@a")) {
				players.addAll(Bukkit.getOnlinePlayers());
			} else if(args[0].equalsIgnoreCase("@r")) {
				players.add((new ArrayList<Player>(Bukkit.getOnlinePlayers())).get(ThreadLocalRandom.current().nextInt(0, Bukkit.getOnlinePlayers().size())));
			} else if(Bukkit.getPlayer(args[0]) != null) {
				players.add(Bukkit.getPlayer(args[0]));
			} else {
				sender.sendMessage(ChatContent.RED + "[Slot Machine] This player does not exist or is not online. Please use <player name> or @a or @r");
				return true;
			}
			if(players.size() > 0) {
				if(NumberUtils.isNumber(args[1])) {
					for(Player player : players) {
						ItemStack is = Config.tokens.get("default");
						final int amount = Integer.valueOf(args[1]);
						if (args.length == 3 && Config.tokens.containsKey(args[2])) {
							is = Config.tokens.get(args[2]);
							player.getInventory().addItem(ItemStackUtil.changeItemStackAmount(new ItemStack(is), amount));
							player.updateInventory();
							sender.sendMessage(ChatContent.GREEN + "[Slot Machine] You have successfully given " + amount + " " + args[2] + " to" + player.getName());
						} else {
							TokensInventory.showManagementScreen(player, 0, "Pick a Token to give", "Left click to pick this Token", new TokenSelectionListener() {

								@Override
								public void callback(Player player, Token token) {
									player.closeInventory();
									player.getInventory().addItem(ItemStackUtil.changeItemStackAmount(new ItemStack(token.itemStack), amount));
									player.updateInventory();
									sender.sendMessage(ChatContent.GREEN + "[Slot Machine] You have successfully given " + amount + " " + token.identifier + " to" + player.getName());
								}
								
							}, false);
						}
					}
					return true;
				}
			}
		}
		return false;
	}
}
