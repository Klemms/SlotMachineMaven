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
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
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
											player.closeDialog();
											state.reloadPage();
										},
										player,
										"Success",
										"Back",
										true,
										Component.text("Price has been successfully changed").color(ChatContent.TEX_GOLD),
										Component.text("New price :"),
										Component.text(String.valueOf(machine.getPullPrice())),
										Component.text("Formatted price (as it will appear with $price placeholder) :"),
										Component.text(Variables.PRICE.variable.getVariable(player, machine))
								);
							},
							player, "Change Price", (float) (machine.getPullPrice()), "Price : (decimals allowed)",
							null, 0, Integer.MAX_VALUE, true, true, false,
							Component.text("Change the price players will pay to play.").color(ChatContent.TEX_GOLD),
							Component.text("Minimum value is 0").color(ChatContent.TEX_GOLD).decorate(TextDecoration.ITALIC),
							Component.text("A value of 0 means free").color(ChatContent.TEX_GOLD).decorate(TextDecoration.ITALIC)
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
