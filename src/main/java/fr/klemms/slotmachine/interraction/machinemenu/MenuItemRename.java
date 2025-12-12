package fr.klemms.slotmachine.interraction.machinemenu;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotMachineEntity;
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
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.ArrayList;
import java.util.List;

public class MenuItemRename extends MenuItem {
	@Override
	public ItemStack getMaterial(SlotMachine machine, Player player) {
		return new ItemStack(Material.NAME_TAG, 1);
	}

	@Override
	public String getTitle(SlotMachine machine, Player player) {
		return ChatContent.GOLD + "Rename";
	}

	@Override
	public List<String> getDescription(SlotMachine machine, Player player) {
		List<String> leverDescLore = new ArrayList<String>();
		leverDescLore.add(ChatContent.AQUA + ChatContent.ITALIC + "Rename this machine");
		leverDescLore.add("");
		leverDescLore.add(ChatContent.AQUA + ChatContent.ITALIC + "Current name :");
		leverDescLore.add(ChatContent.WHITE + machine.getSlotMachineName());

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
									if (machine instanceof SlotMachineEntity slotMachineEntity) {
										Entity entity = slotMachineEntity.getEntity();

										if (entity != null) {
											entity.setCustomName(ChatContent.translateColorCodes(text));
											entity.setCustomNameVisible(true);
										}
									}

									machine.setSlotMachineName(ChatContent.translateColorCodes(text));
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
											new ComponentBuilder("Machine name has been successfully changed.").color(ChatColor.GOLD).build(),
											new ComponentBuilder("New name :").build(),
											new ComponentBuilder(machine.getSlotMachineName()).build()
									);
								}

								@Override
								public void resetCallback() {}

								@Override
								public void removeCallback() {
									if (machine instanceof SlotMachineEntity slotMachineEntity) {
										Entity entity = slotMachineEntity.getEntity();

										if (entity != null) {
											entity.setCustomName("");
											entity.setCustomNameVisible(false);
										}
									}

									machine.setSlotMachineName("Slot Machine");
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
											new ComponentBuilder("Machine name has been reset.").color(ChatColor.GOLD).build(),
											new ComponentBuilder("New name :").build(),
											new ComponentBuilder(machine.getSlotMachineName()).build()
									);
								}
							},
							player, "Rename Machine", machine.getSlotMachineName(), null, false, true, false, false, true,
							new ComponentBuilder("Color codes can be used.").color(ChatColor.GOLD).build()
					);
				} else {
					player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
					player.closeInventory();
					PlayerUtil.resetPlayerData(player);
					player.setMetadata("slotmachine_changename", new FixedMetadataValue(SlotPlugin.pl, machine.getMachineUUID().toString()));
					player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("command.slotmachineaction.rename"));
					player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + "Type \"cancel\" to cancel");
				}
				break;
		}
	}
}
