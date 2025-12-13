package fr.klemms.slotmachine.interraction.machinemenu;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.dialogs.DialogInfo;
import fr.klemms.slotmachine.dialogs.DialogResettableInputText;
import fr.klemms.slotmachine.dialogs.callbacks.ResettableCallback;
import fr.klemms.slotmachine.translation.Language;
import fr.klemms.slotmachine.utils.PlayerUtil;
import fr.klemms.slotmachine.utils.Util;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

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
			DialogResettableInputText.open(new ResettableCallback<String>() {
				@Override
				public void validateCallback(String text) {
					machine.setHasWinMessage(true);
					machine.setWinMessage(text);
					machine.save();

					DialogInfo.open(() -> {
								player.clearDialog();
								state.reloadPage();
							},
							player,
							"Success",
							"Back",
							true,
							new ComponentBuilder("Win message has been successfully changed").color(ChatColor.GOLD).build(),
							new ComponentBuilder("New win message :").build(),
							new ComponentBuilder(ChatContent.translateColorCodes(machine.getFinalWinMessage())).build()
					);
				}

				@Override
				public void resetCallback() {
					machine.setHasWinMessage(true);
					machine.setWinMessage("");
					machine.save();

					DialogInfo.open(() -> {
								player.clearDialog();
								state.reloadPage();
							},
							player,
							"Success",
							"Back",
							true,
							new ComponentBuilder("Win message has been successfully reset to default").color(ChatColor.GOLD).build(),
							new ComponentBuilder("New win message :").build(),
							new ComponentBuilder(ChatContent.translateColorCodes(machine.getFinalWinMessage())).build()
					);
				}

				@Override
				public void removeCallback() {
					machine.setHasWinMessage(false);
					machine.save();

					DialogInfo.open(() -> {
								player.clearDialog();
								state.reloadPage();
							},
							player,
							"Success",
							"Back",
							true,
							new ComponentBuilder("Win message has been removed").color(ChatColor.GOLD).build()
					);

				}
			}, player, "Change Win Message", machine.hasWinMessage() ? machine.getFinalWinMessage() : machine.getWinMessage(), null, true, true, true, true, true, false);
		} else {
			player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
			player.closeInventory();
			PlayerUtil.resetPlayerData(player);
			player.setMetadata("slotmachine_setwinmessage", new FixedMetadataValue(SlotPlugin.pl, machine.getMachineUUID().toString()));
			player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("command.slotmachineaction.winmessage").replace("%winMessage%", ChatContent.RESET + machine.getWinMessage() + ChatContent.DARK_PURPLE + ChatContent.BOLD));
			player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + "Type \"null\" for no message");
			player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + "Type \"reset\" to reset to default message");
			player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + "Type \"cancel\" to cancel");
		}
	}
}
