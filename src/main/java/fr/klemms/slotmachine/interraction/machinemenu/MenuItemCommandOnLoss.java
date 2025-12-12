package fr.klemms.slotmachine.interraction.machinemenu;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.dialogs.DialogInputCommand;
import fr.klemms.slotmachine.interraction.StringInput;
import fr.klemms.slotmachine.utils.PlayerHeadsUtil;
import fr.klemms.slotmachine.utils.PlayerUtil;
import fr.klemms.slotmachine.utils.Util;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class MenuItemCommandOnLoss extends MenuItem {
	@Override
	public ItemStack getMaterial(SlotMachine machine, Player player) {
		return new ItemStack(PlayerHeadsUtil.REPEATING_COMMAND_BLOCK);
	}

	@Override
	public String getTitle(SlotMachine machine, Player player) {
		return ChatContent.GOLD + "Command on Loss";
	}

	@Override
	public List<String> getDescription(SlotMachine machine, Player player) {
		return Arrays.asList(
				ChatContent.AQUA + ChatContent.ITALIC + "Execute a command when a",
				ChatContent.AQUA + ChatContent.ITALIC + "player loses.",
				"",
				ChatContent.DARK_RED + "[Right Click]" + ChatContent.RED + " remove the command.",
				"",
				ChatContent.AQUA + ChatContent.ITALIC + "Current command :",
				machine.getCommandLoss() != null ? ChatContent.RESET + machine.getCommandLoss() : ChatContent.GRAY + ChatContent.ITALIC + "none"
		);
	}

	@Override
	public void onClick(SlotMachine machine, Player player, ClickType clickType, MenuState state) {
		switch (clickType) {
			case LEFT:
				player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);

				if (Util.canUseDialogs()) {
					DialogInputCommand.open(text -> {
						if (!text.isEmpty()) {
							player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.8F, 2F);

							PlayerUtil.sendSuccessMessage(player, "Successfully changed command on loss to : " + text);

							machine.setCommandLoss(text);
						} else {
							machine.setCommandLoss(null);
						}
						machine.save();
						state.reloadPage();
					}, player, "Command on Loss", machine.getCommandLoss(), "", null, true, true, true);
				} else {
					StringInput.inputString(
							player,
							"Command on Loss",
							machine.getCommandLoss() != null ? machine.getCommandLoss() : "",
							text -> {
								String str = text.startsWith("/") ? text.substring(1) : text;

								if (!str.isEmpty()) {
									player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.8F, 2F);

									PlayerUtil.sendSuccessMessage(player, "Successfully changed command on loss to : " + text);

									machine.setCommandLoss(str);
								} else {
									machine.setCommandLoss(null);
								}
								machine.save();
								state.reloadPage();
							},
							true,
							true,
							true,
							new ItemStack(Material.REPEATING_COMMAND_BLOCK),
							new ItemStack(PlayerHeadsUtil.INFOS)
					);
				}
				break;
			case RIGHT:
				PlayerUtil.sendSuccessMessage(player, "Successfully removed command on loss");
				machine.setCommandLoss(null);
				machine.save();
				state.reloadPage();
				break;
		}
	}
}
