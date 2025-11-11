package fr.klemms.slotmachine.interraction.machinemenu;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.interraction.StringInput;
import fr.klemms.slotmachine.utils.PlayerHeadsUtil;
import fr.klemms.slotmachine.utils.PlayerUtil;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class MenuItemCommandOnPlay extends MenuItem {
	@Override
	public ItemStack getMaterial(SlotMachine machine, Player player) {
		return new ItemStack(Material.COMMAND_BLOCK, 1);
	}

	@Override
	public String getTitle(SlotMachine machine, Player player) {
		return ChatContent.GOLD + "Command on Play";
	}

	@Override
	public List<String> getDescription(SlotMachine machine, Player player) {
		return Arrays.asList(
				ChatContent.AQUA + ChatContent.ITALIC + "Execute a command when a",
				ChatContent.AQUA + ChatContent.ITALIC + "player starts playing.",
				"",
				ChatContent.DARK_RED + "[Right Click]" + ChatContent.RED + " remove the command.",
				"",
				ChatContent.AQUA + ChatContent.ITALIC + "Current command :",
				machine.getCommandPlay() != null ? ChatContent.RESET + machine.getCommandPlay() : ChatContent.GRAY + ChatContent.ITALIC + "none"
		);
	}

	@Override
	public void onClick(SlotMachine machine, Player player, ClickType clickType, MenuState state) {
		switch (clickType) {
			case LEFT:
				player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);

				StringInput.inputString(
						player,
						"Command on Play",
						machine.getCommandPlay() != null ? machine.getCommandPlay() : "",
						text -> {
							String str = text.startsWith("/") ? text.substring(1) : text;

							if (!str.isEmpty()) {
								player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.8F, 2F);

								PlayerUtil.sendSuccessMessage(player, "Successfully changed command on play to : " + text);

								machine.setCommandPlay(str);
							} else {
								machine.setCommandPlay(null);
							}
							machine.save();
							state.reloadPage();
						},
						true,
						true,
						true,
						new ItemStack(Material.COMMAND_BLOCK),
						new ItemStack(PlayerHeadsUtil.INFOS)
				);
				break;
			case RIGHT:
				PlayerUtil.sendSuccessMessage(player, "Successfully removed command on play");
				machine.setCommandPlay(null);
				machine.save();
				state.reloadPage();
				break;
		}
	}
}
