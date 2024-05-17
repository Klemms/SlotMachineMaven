package fr.klemms.slotmachine.commands;

import fr.klemms.slotmachine.*;
import fr.klemms.slotmachine.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CommandCooldown implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length >= 3 && (sender.hasPermission("slotmachine.smcooldown") || sender.isOp())) {
			List<OfflinePlayer> players = new ArrayList<OfflinePlayer>();

			if (args[0].equalsIgnoreCase("@a")) {
				players.addAll(Bukkit.getOnlinePlayers());
			} else if (args[0].equalsIgnoreCase("@everyone")) {
				for (PlayerConfig plc : PlayerConfig.getPlayerConfigs()) {
					OfflinePlayer pl = Bukkit.getOfflinePlayer(plc.getPlayerUUID());
					if (pl != null)
						players.add(pl);
				}
			} else {
				Player pl = Bukkit.getPlayer(args[0]);
				if (pl != null)
					players.add(pl);
			}
			if (players.size() == 0) {
				sender.sendMessage(ChatContent.RED + SlotPlugin.CHAT_PREFIX + "Found 0 player or no player has an active cooldown");
				return false;
			}

			List<SlotMachine> machines = new ArrayList<SlotMachine>();

			if (args[1].equalsIgnoreCase("all")) {
				machines.addAll(SlotMachine.getSlotMachines());
			} else if (Util.isValidUUID(args[1])) {
				SlotMachine sm = SlotMachine.getSlotMachineByUUID(UUID.fromString(args[1]));
				if (sm != null)
					machines.add(sm);
			}
			if (machines.size() == 0) {
				sender.sendMessage(ChatContent.RED + SlotPlugin.CHAT_PREFIX + "Found 0 machine");
				return false;
			}

			if (!args[2].equalsIgnoreCase("reset")) {
				sender.sendMessage(ChatContent.RED + SlotPlugin.CHAT_PREFIX + "Third argument can only be : reset");
				return false;
			}

			for (OfflinePlayer pl : players) {
				for (SlotMachine machine : machines) {
					SMPlayerConfig sm = PlayerConfig.getSMPlayerConfig(pl, machine);

					if (sm != null) {
						if (args[2].equalsIgnoreCase("reset"))
							sm.setCooldown(0);
					}
				}
			}

			SlotPlugin.saveCooldownsToDisk();
			sender.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + "Successfully changed " + players.size() + " player's cooldowns on " + machines.size() + " machines.");
			return true;
		}
		sender.sendMessage(ChatContent.RED + SlotPlugin.CHAT_PREFIX + "Missing arguments");
		return false;
	}
}
