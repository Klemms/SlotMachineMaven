package fr.klemms.slotmachine.interraction.machinemenu;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.dialogs.DialogInfo;
import fr.klemms.slotmachine.dialogs.DialogInputNumber;
import fr.klemms.slotmachine.placeholders.Variables;
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

public class MenuItemChangePrice extends MenuItem {
	@Override
	public ItemStack getMaterial(SlotMachine machine, Player player) {
		return new ItemStack(Material.DIAMOND, 1);
	}

	@Override
	public String getTitle(SlotMachine machine, Player player) {
		return ChatContent.GOLD + "Change Price";
	}

	@Override
	public List<String> getDescription(SlotMachine machine, Player player) {
		return Arrays.asList(
				ChatContent.AQUA + ChatContent.ITALIC + "Change the price to pay",
				ChatContent.AQUA + ChatContent.ITALIC + "to use this machine",
				"",
				ChatContent.AQUA + ChatContent.ITALIC + "Current price :",
				ChatContent.WHITE + machine.getPullPrice(),
				"",
				ChatContent.AQUA + ChatContent.ITALIC + "Current formatted price :",
				ChatContent.WHITE + Variables.PRICE.variable.getVariable(player, machine)
		);
	}

	@Override
	public void onClick(SlotMachine machine, Player player, ClickType clickType, MenuState state) {
		switch (clickType) {
			case LEFT:
				player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);

				if (Util.canUseDialogs()) {
					DialogInputNumber.open(
							number -> {
								machine.setPullPrice(number);
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
										new ComponentBuilder("Price has been successfully changed").color(ChatColor.GOLD).build(),
										new ComponentBuilder("New price :").build(),
										new ComponentBuilder(String.valueOf(machine.getPullPrice())).build(),
										new ComponentBuilder("Formatted price (as it will appear with $price placeholder) :").build(),
										new ComponentBuilder(Variables.PRICE.variable.getVariable(player, machine)).build()
								);
							},
							player, "Change Price", (float) (machine.getPullPrice()), "Price : (decimals allowed)",
							null, 1, Integer.MAX_VALUE, true, true, false,
							new ComponentBuilder("Change the price players will pay to play.").color(ChatColor.GOLD).build(),
							new ComponentBuilder("Minimum value is 0").color(ChatColor.GOLD).italic(true).build(),
							new ComponentBuilder("A value of 0 means free").color(ChatColor.GOLD).italic(true).build()
					);
				} else {
					player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
					player.closeInventory();
					PlayerUtil.resetPlayerData(player);
					player.setMetadata("slotmachine_changeprice", new FixedMetadataValue(SlotPlugin.pl, machine.getMachineUUID().toString()));
					player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("command.slotmachineaction.changeprice") + " :");
					player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + "Type \"cancel\" to cancel");
				}
				break;
		}
	}
}
