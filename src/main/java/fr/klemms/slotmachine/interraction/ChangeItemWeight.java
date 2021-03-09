package fr.klemms.slotmachine.interraction;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.MachineItem;
import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.utils.ItemStackUtil;
import fr.klemms.slotmachine.utils.PlayerHeadsUtil;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;

public class ChangeItemWeight {

	public static void changeItemWeight(Player player, SlotMachine machine, MachineItem item, int backpage) {
		SmartInventory inv = SmartInventory.builder()
				.manager(SlotPlugin.invManager)
				.title("Edit Item Weight")
				.size(5, 9)
				.closeable(true)
				.provider(new InventoryProvider() {

					@Override
					public void init(Player player, InventoryContents contents) {
						contents.fill(ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1), " ")));

						contents.fillRow(1, ClickableItem.empty(null));
						contents.fillRow(2, ClickableItem.empty(null));
						contents.fillRow(3, ClickableItem.empty(null));
						
						contents.set(2, 1, ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.MINUS_SIGN), ChatContent.GOLD + "Minus (-)"), Arrays.asList(
								ChatContent.AQUA + "Decrement weight",
								ChatContent.AQUA + "",
								ChatContent.GRAY + "Left Click  : -1",
								ChatContent.GRAY + "Right Click : -10"
								)), event -> {
									if (event.isLeftClick() && item.getWeight() - 1 >= 0) {
										item.setWeight(item.getWeight() - 1);
										SlotPlugin.saveToDisk();
										player.playSound(player.getLocation(), Sound.ENTITY_PAINTING_PLACE, 1F, 0.7F);
										ChangeItemWeight.changeItemWeight(player, machine, item, backpage);
									} else if(event.isRightClick() && item.getWeight() - 10 >= 0) {
										item.setWeight(item.getWeight() - 10);
										SlotPlugin.saveToDisk();
										player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_CLOSE, 1F, 1.7F);
										ChangeItemWeight.changeItemWeight(player, machine, item, backpage);
									} else {
										item.setWeight(999);
										SlotPlugin.saveToDisk();
										player.playSound(player.getLocation(), Sound.BLOCK_BEACON_POWER_SELECT, 1F, 1.6F);
										ChangeItemWeight.changeItemWeight(player, machine, item, backpage);
									}
								}));
						
						contents.set(2, 7, ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.PLUS_SIGN), ChatContent.GOLD + "Plus (+)"), Arrays.asList(
								ChatContent.AQUA + "Increment weight",
								ChatContent.AQUA + "",
								ChatContent.GRAY + "Left Click  : +1",
								ChatContent.GRAY + "Right Click : +10"
								)), event -> {
									if (event.isLeftClick() && item.getWeight() + 1 <= 999) {
										item.setWeight(item.getWeight() + 1);
										SlotPlugin.saveToDisk();
										player.playSound(player.getLocation(), Sound.ENTITY_PAINTING_PLACE, 1F, 1.3F);
										ChangeItemWeight.changeItemWeight(player, machine, item, backpage);
									} else if(event.isRightClick() && item.getWeight() + 10 <= 999) {
										item.setWeight(item.getWeight() + 10);
										SlotPlugin.saveToDisk();
										player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_CLOSE, 1F, 2F);
										ChangeItemWeight.changeItemWeight(player, machine, item, backpage);
									} else {
										item.setWeight(0);
										SlotPlugin.saveToDisk();
										player.playSound(player.getLocation(), Sound.BLOCK_BEACON_POWER_SELECT, 1F, 2F);
										ChangeItemWeight.changeItemWeight(player, machine, item, backpage);
									}
								}));
						
						if (item.getWeight() > 999 || item.getWeight() < 0) {
							contents.set(2, 4, ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(Material.BARRIER, 1), ChatContent.GOLD + "Invalid Weight"), Arrays.asList(
									ChatContent.AQUA + "This item's weight is out of ",
									ChatContent.AQUA + "this editor's range (0 to 999)",
									"",
									ChatContent.AQUA + "Current Weight : " + ChatContent.DARK_AQUA + item.getWeight(),
									"",
									ChatContent.DARK_AQUA + "Click to reset its weight to 1"
									)), event -> {
										ConfirmInventory.confirmWindow(player, "Reset to 1 ?", "No, don't reset", "Yes, reset to 1", result -> {
											if (result) {
												item.setWeight(1);
												SlotPlugin.saveToDisk();
												player.playSound(player.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1F, 2F);
												ChangeItemWeight.changeItemWeight(player, machine, item, backpage);
											} else {
												player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
												ChangeItemWeight.changeItemWeight(player, machine, item, backpage);
											}
										}, false);
									}));
						} else {
							int hundredsDigit = hundredsDigit(item.getWeight());
							contents.set(2, 3, ClickableItem.empty(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(digitToHead(hundredsDigit)), ChatContent.GOLD + hundredsDigit), Arrays.asList(
									ChatContent.AQUA + "Current Weight : " + ChatContent.DARK_AQUA + item.getWeight()
									))));
							
							int dozensDigit = dozensDigit(item.getWeight());
							contents.set(2, 4, ClickableItem.empty(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(digitToHead(dozensDigit)), ChatContent.GOLD + dozensDigit), Arrays.asList(
									ChatContent.AQUA + "Current Weight : " + ChatContent.DARK_AQUA + item.getWeight()
									))));
							
							int unitDigit = unitDigit(item.getWeight());
							contents.set(2, 5, ClickableItem.empty(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(digitToHead(unitDigit)), ChatContent.GOLD + unitDigit), Arrays.asList(
									ChatContent.AQUA + "Current Weight : " + ChatContent.DARK_AQUA + item.getWeight()
									))));
						}
						
						contents.set(0, 2, ClickableItem.empty(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.INFOS), ChatContent.GOLD + "Informations"), Arrays.asList(
								ChatContent.AQUA + "Click on + or - to increment and",
								ChatContent.AQUA + "decrement the item's weight",
								ChatContent.AQUA + "",
								ChatContent.AQUA + "Left Click  : -1/+1",
								ChatContent.AQUA + "Right Click : -10/+10",
								ChatContent.AQUA + ChatContent.ITALIC + "Weight will loop back to 999 when",
								ChatContent.AQUA + ChatContent.ITALIC + "going under 0 and vice-versa",
								ChatContent.AQUA + "",
								ChatContent.AQUA + "Item weight can go from 0 (unwinnable)",
								ChatContent.AQUA + "to 2 billions however this editor will",
								ChatContent.AQUA + "only allow you to edit it up to 999"
								))));
						
						contents.set(0, 6, ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.ONE_BLACKBG), ChatContent.RED + "Reset to 1"), Arrays.asList(
								ChatContent.GRAY + "Resets the weight to 1"
							)), event -> {
								ConfirmInventory.confirmWindow(player, "Reset to 1 ?", "No, don't reset", "Yes, reset to 1", result -> {
									if (result) {
										item.setWeight(1);
										SlotPlugin.saveToDisk();
										player.playSound(player.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1F, 2F);
										ChangeItemWeight.changeItemWeight(player, machine, item, backpage);
									} else {
										player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
										ChangeItemWeight.changeItemWeight(player, machine, item, backpage);
									}
								}, false);
								
						}));

						contents.set(4, 1, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.BACK), "<- Back"), event -> {
							player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
							MachineItemsInterractionInventory.manageItems(player, machine, backpage);
						}));

						contents.set(4, 6, ClickableItem.empty(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.SMALL_INFOS), ChatContent.GOLD + "Item Being Edited"), Arrays.asList(
								ChatContent.AQUA + "The item on the right is the item",
								ChatContent.AQUA + "currently being edited"
								))));
						
						contents.set(4, 7, ClickableItem.empty(new ItemStack(item.getItemStack())));
						
					}

					@Override
					public void update(Player player, InventoryContents contents) {
						
					}
					
				})
				.build();
		
		inv.open(player);
	}
	
	private static ItemStack digitToHead(int digit) {
		switch(digit) {
			case 1:
				return PlayerHeadsUtil.ONE_BLACKBG;
			case 2:
				return PlayerHeadsUtil.TWO_BLACKBG;
			case 3:
				return PlayerHeadsUtil.THREE_BLACKBG;
			case 4:
				return PlayerHeadsUtil.FOUR_BLACKBG;
			case 5:
				return PlayerHeadsUtil.FIVE_BLACKBG;
			case 6:
				return PlayerHeadsUtil.SIX_BLACKBG;
			case 7:
				return PlayerHeadsUtil.SEVEN_BLACKBG;
			case 8:
				return PlayerHeadsUtil.EIGHT_BLACKBG;
			case 9:
				return PlayerHeadsUtil.NINE_BLACKBG;
		}
		return PlayerHeadsUtil.ZERO_BLACKBG;
	}
	
	private static int hundredsDigit(int weight) {
		if (weight >= 100) {
			return Character.getNumericValue(StringUtils.reverse(String.valueOf(weight)).toCharArray()[2]);
		}
		return 0;
	}
	
	private static int dozensDigit(int weight) {
		if (weight >= 10) {
			return Character.getNumericValue(StringUtils.reverse(String.valueOf(weight)).toCharArray()[1]);
		}
		return 0;
	}
	
	private static int unitDigit(int weight) {
		return Character.getNumericValue(StringUtils.reverse(String.valueOf(weight)).toCharArray()[0]);
	}
}
