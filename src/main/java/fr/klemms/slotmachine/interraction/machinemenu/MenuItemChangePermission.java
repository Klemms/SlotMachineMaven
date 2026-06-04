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
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.ArrayList;
import java.util.List;

public class MenuItemChangePermission extends MenuItem {
	@Override
	public ItemStack getMaterial(SlotMachine machine, Player player) {
		return new ItemStack(Material.GOLD_BLOCK, 1);
	}

	@Override
	public String getTitle(SlotMachine machine, Player player) {
		return ChatContent.GOLD + "Change Permission";
	}

	@Override
	public List<String> getDescription(SlotMachine machine, Player player) {
		List<String> leverDescLore = new ArrayList<String>();
		leverDescLore.add(ChatContent.AQUA + ChatContent.ITALIC + "Change this machine's permission");
		leverDescLore.add("");
		leverDescLore.add(ChatContent.AQUA + ChatContent.ITALIC + "Current permission :");
		leverDescLore.add(ChatContent.WHITE + machine.getGuiPermission());

		return leverDescLore;
	}

	@Override
	public void onClick(SlotMachine machine, Player player, ClickType clickType, MenuState state) {
		switch (clickType) {
			case LEFT:
				player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);

				if (Util.canUseDialogs()) {
					DialogResettableInputText.open(
							new ResettableCallback<String>() {
								@Override
								public void validateCallback(String text) {
									String newPerm = StringUtils.deleteWhitespace(text);
									machine.setGuiPermission("slotmachine.access." + newPerm);
									machine.save();
									player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.8F, 2F);

									DialogInfo.open(() -> {
												player.closeDialog();
												state.reloadPage();
											},
											player,
											"Success",
											"Back",
											true,
											Component.text("Machine permission has been successfully changed.").color(ChatContent.TEX_GOLD),
											Component.text("New permission :"),
											Component.text("slotmachine.access.").append(
													Component.text(newPerm).color(ChatContent.TEX_AQUA)
											)
									);
								}

								@Override
								public void resetCallback() {
									machine.setGuiPermission("slotmachine.access.default");
									machine.save();
									player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.8F, 2F);

									DialogInfo.open(() -> {
												player.closeDialog();
												state.reloadPage();
											},
											player,
											"Success",
											"Back",
											true,
											Component.text("Machine permission has been reset.").color(ChatContent.TEX_GOLD),
											Component.text("New permission :"),
											Component.text("slotmachine.access.default")
									);
								}

								@Override
								public void removeCallback() {
								}
							},
							player, "Change Permission", machine.getGuiPermission().replace("slotmachine.access.", ""),
							null, false, true, false, true, false, true,
							Component.text("The new permission will automatically begin with ").color(ChatContent.TEX_GOLD)
									.append(Component.text("slotmachine.access.").color(ChatContent.TEX_AQUA)),
							Component.text("Type in the box below the permission you want after ").color(ChatContent.TEX_GOLD)
									.append(Component.text("slotmachine.access.").color(ChatContent.TEX_AQUA)),
							Component.text("Note : Only alphabetic characters are allowed").color(ChatContent.TEX_GRAY).decorate(TextDecoration.ITALIC)
					);
				} else {
					player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
					player.closeInventory();
					PlayerUtil.resetPlayerData(player);
					player.setMetadata("slotmachine_changepermission", new FixedMetadataValue(SlotPlugin.pl, machine.getMachineUUID().toString()));
					player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("command.slotmachineaction.changepermission"));
					player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + "Type \"cancel\" to cancel");
				}
				break;
		}
	}
}
