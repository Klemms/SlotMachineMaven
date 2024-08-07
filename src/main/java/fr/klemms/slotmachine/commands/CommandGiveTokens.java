package fr.klemms.slotmachine.commands;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.Config;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.tokens.TokensInventory;
import fr.klemms.slotmachine.utils.ItemStackUtil;
import org.apache.commons.lang3.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CommandGiveTokens implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length >= 2) {
            List<Player> players = new ArrayList<Player>();
            if (args[0].equalsIgnoreCase("@a")) {
                players.addAll(Bukkit.getOnlinePlayers());
            } else if (args[0].equalsIgnoreCase("@r")) {
                players.add((new ArrayList<Player>(Bukkit.getOnlinePlayers())).get(ThreadLocalRandom.current().nextInt(0, Bukkit.getOnlinePlayers().size())));
            } else if (Bukkit.getPlayer(args[0]) != null) {
                players.add(Bukkit.getPlayer(args[0]));
            } else {
                sender.sendMessage(ChatContent.RED + SlotPlugin.CHAT_PREFIX + "This player does not exist or is not online. Please use <player name> or @a or @r");
                return true;
            }
            if (!players.isEmpty()) {
                if (NumberUtils.isCreatable(args[1])) {
                    for (Player player : players) {
                        final int amount = Integer.parseInt(args[1]);

                        if (args.length == 3 && Config.tokens.containsKey(args[2])) {
                            player.getInventory().addItem(ItemStackUtil.changeItemStackAmount(new ItemStack(Config.tokens.get(args[2])), amount));
                            sender.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + "You have successfully given " + amount + " token '" + args[2] + "' to " + player.getName());
                        } else {
                            TokensInventory.showManagementScreen(player, 0, "Pick a Token to give", "Left click to pick this Token", (player1, token) -> {
                                player1.closeInventory();
                                player1.getInventory().addItem(ItemStackUtil.changeItemStackAmount(new ItemStack(token.itemStack), amount));
                                sender.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + "You have successfully given " + amount + " token '" + token.identifier + "' to " + player1.getName());
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
