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
												player.clearDialog();
												state.reloadPage();
											},
											player,
											"Success",
											"Back",
											true,
											new ComponentBuilder("Machine permission has been successfully changed.").color(ChatColor.GOLD).build(),
											new ComponentBuilder("New permission :").build(),
											new ComponentBuilder("slotmachine.access.")
													.append(new ComponentBuilder(newPerm).color(ChatColor.AQUA).build())
													.build()
									);
								}

								@Override
								public void resetCallback() {
									machine.setGuiPermission("slotmachine.access.default");
									machine.save();
									player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.8F, 2F);

									DialogInfo.open(() -> {
												player.clearDialog();
												state.reloadPage();
											},
											player,
											"Success",
											"Back",
											true,
											new ComponentBuilder("Machine permission has been reset.").color(ChatColor.GOLD).build(),
											new ComponentBuilder("New permission :").build(),
											new ComponentBuilder("slotmachine.access.default").build()
									);
								}

								@Override
								public void removeCallback() {}
							},
							player, "Change Permission", machine.getGuiPermission().replace("slotmachine.access.", ""),
							null, false, true, false, true, false, true,
							new ComponentBuilder().color(ChatColor.GOLD)
									.append(new ComponentBuilder("The new permission will automatically begin with ").build())
									.append(new ComponentBuilder("slotmachine.access.").color(ChatColor.AQUA).build())
									.build(),
							new ComponentBuilder().color(ChatColor.GOLD)
									.append(new ComponentBuilder("Type in the box below the permission you want after ").build())
									.append(new ComponentBuilder("slotmachine.access.").color(ChatColor.AQUA).build())
									.build(),
							new ComponentBuilder("Note : Only alphabetic characters are allowed").italic(true).color(ChatColor.GRAY).build()
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
