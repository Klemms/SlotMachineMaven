package fr.klemms.slotmachine.interraction;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.MachineItem;
import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.fr.minuskube.inv.ClickableItem;
import fr.klemms.slotmachine.fr.minuskube.inv.InventoryListener;
import fr.klemms.slotmachine.fr.minuskube.inv.SmartInventory;
import fr.klemms.slotmachine.fr.minuskube.inv.content.InventoryContents;
import fr.klemms.slotmachine.fr.minuskube.inv.content.InventoryProvider;
import fr.klemms.slotmachine.interraction.rewards.RewardsCustomization;
import fr.klemms.slotmachine.translation.Language;
import fr.klemms.slotmachine.utils.ItemStackUtil;
import fr.klemms.slotmachine.utils.PlayerHeadsUtil;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class ItemEdit {

	public static void editItem(Player player, SlotMachine machine, MachineItem item, int backPage) {
		SmartInventory inv = SmartInventory.builder()
				.manager(SlotPlugin.invManager)
				.title("What do you want to do ?")
				.size(5, 9)
				.closeable(true)
				.listener(new InventoryListener<InventoryClickEvent>(InventoryClickEvent.class, event -> {
					if (event.getCursor().getType() != Material.AIR && event.isLeftClick()) {

					}
				}))
				.provider(new InventoryProvider() {

					@Override
					public void init(Player player, InventoryContents contents) {
						contents.fill(ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1), " ")));

						contents.fillRow(1, ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1), " ")));
						contents.fillRow(2, ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1), " ")));
						contents.fillRow(3, ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1), " ")));

						contents.set(0, 1, ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.INFOS), ChatContent.GOLD + "Pick what you want to edit")));

						contents.set(2, 1, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.DUMBBELL), ChatContent.GOLD + "Edit Item Weight"), event -> {
							player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
							ChangeItemWeight.changeItemWeight(player, machine, item, backPage);
						}));

						contents.set(2, 3, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.CUSTOMIZE), ChatContent.GOLD + "Edit Rewards & Commands"), event -> {
							player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
							RewardsCustomization.rewardsCustomization(player, machine, item, backPage, 0);
						}));

						contents.set(2, 7, ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.ALLAY), ChatContent.GOLD + (item.showAttributeModifiers ? "Hide Attribute Modifiers" : "Show Attribute Modifiers")), Arrays.asList(
								ChatContent.AQUA + "Toggles showing basic attribute",
								ChatContent.AQUA + "modifiers on the item in GUIs to",
								ChatContent.AQUA + "make them look cleaner.",
								"",
								ChatContent.AQUA + "Won't affect the reward, this effect",
								ChatContent.AQUA + "is purely cosmetic."
						)), event -> {
							player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
							item.showAttributeModifiers = !item.showAttributeModifiers;
							machine.save();
							ItemEdit.editItem(player, machine, item, backPage);
						}));

						contents.set(4, 1, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.BACK), Language.translate("basic.back")), event -> {
							player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
							MachineItemsInterractionInventory.manageItems(player, machine, backPage);
						}));

						contents.set(4, 7, ClickableItem.empty(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.SMALL_INFOS), ChatContent.GOLD + "Item Being Edited"), Arrays.asList(
								ChatContent.AQUA + "The item on the right is the item",
								ChatContent.AQUA + "currently being edited"
						))));

						contents.set(4, 8, ClickableItem.empty(new ItemStack(item.getItemStack(true))));
					}

					@Override
					public void update(Player player, InventoryContents contents) {

					}

				})
				.build();

		inv.open(player);
	}
}
