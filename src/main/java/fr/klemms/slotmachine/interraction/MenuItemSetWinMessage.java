package fr.klemms.slotmachine.interraction;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.interraction.machinemenu.ClickType;
import fr.klemms.slotmachine.interraction.machinemenu.MenuItem;
import fr.klemms.slotmachine.interraction.machinemenu.MenuState;
import fr.klemms.slotmachine.placeholders.Variables;
import fr.klemms.slotmachine.utils.Util;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class MenuItemSetWinMessage extends MenuItem {
	@Override
	public ItemStack getMaterial(SlotMachine machine, Player player) {
		return new ItemStack(Material.GREEN_BANNER, 1);
	}

	@Override
	public String getTitle(SlotMachine machine, Player player) {
		return ChatContent.GOLD + "Change Win Message";
	}

	@Override
	public List<String> getDescription(SlotMachine machine, Player player) {
		return Arrays.asList(
				ChatContent.AQUA + ChatContent.ITALIC + "Change the message players",
				ChatContent.AQUA + ChatContent.ITALIC + "will see when winning",
				"",
				ChatContent.AQUA + ChatContent.ITALIC + "Current message :",
				ChatContent.RESET + ChatContent.translateColorCodes(machine.getFinalWinMessage())
		);
	}

	@Override
	public void onClick(SlotMachine machine, Player player, ClickType clickType, MenuState state) {
		player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);

		if (Util.canUseDialogs()) {

		} else {
			StringInput.inputString(
					player,
					"Change Win Message",
					machine.getWinMessage(),
					text -> {
						if (text.length() > 0) {
							player.closeInventory();
							player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.8F, 2F);

							if (text.equals("reset")) {
								machine.setHasWinMessage(true);
								machine.setWinMessage("");
							} else if (text.equals("null")) {
								machine.setHasWinMessage(false);
							} else {
								machine.setHasWinMessage(true);
								machine.setWinMessage(text);
							}

							player.sendMessage(
									ChatContent.GREEN +
											SlotPlugin.CHAT_PREFIX +
											"Successfully set win message to : " +
											ChatContent.RESET +
											text
							);
							player.sendMessage(
									ChatContent.GREEN +
											SlotPlugin.CHAT_PREFIX +
											"Win message preview : " +
											ChatContent.RESET +
											Variables.getFormattedString(machine.getFinalWinMessage(), player, machine)
							);

							machine.save();
						} else {
							player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 0.8F, 1.5F);

							player.sendMessage(
									ChatContent.RED +
											SlotPlugin.CHAT_PREFIX +
											"Lever title needs to have at least 1 character"
							);
						}
					},
					true,
					false,
					false
			);
		}
	}
}
