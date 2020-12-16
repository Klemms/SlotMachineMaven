package fr.klemms.slotmachine.interraction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.MachineItem;
import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotMachineBlock;
import fr.klemms.slotmachine.SlotMachineEntity;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.translation.Language;
import fr.klemms.slotmachine.utils.ItemStackUtil;
import fr.klemms.slotmachine.utils.PlayerHeadsUtil;
import fr.klemms.slotmachine.utils.Util;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.InventoryListener;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import fr.minuskube.inv.content.Pagination;
import fr.minuskube.inv.content.SlotIterator;

public class MachineItemsInterractionInventory {

	public static void manageItems(Player player, SlotMachine machine, int page) {
		SmartInventory inv = SmartInventory.builder()
				.manager(SlotPlugin.invManager)
				.title("Items & Stats (Hover Infos)")
				.size(6, 9)
				.closeable(true)
				.listener(new InventoryListener<InventoryClickEvent>(InventoryClickEvent.class, event -> {
					if (event.getCursor().getType() != Material.AIR && event.isLeftClick()) {
						MachineItem item = new MachineItem(new ItemStack(event.getCursor()), 1);
						machine.addItem(item);
						SlotPlugin.saveToDisk();
						event.setCursor(null);
						event.setCancelled(true);
						manageItems(player, machine, page);
					}
				}))
				.provider(new InventoryProvider() {

					@Override
					public void init(Player player, InventoryContents contents) {
						Pagination pagination = contents.pagination();

						contents.fill(ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1), " ")));
						
						pagination.setItemsPerPage(4 * 9);
						List<ClickableItem> items = new ArrayList<ClickableItem>();
						
						for(final MachineItem item : machine.getSlotMachineItems())  {
							List<String> isLore = new ArrayList<String>();
							isLore.add(ChatContent.DARK_AQUA + ChatContent.ITALIC + " - Right click to take this item back");
							isLore.add(ChatContent.DARK_AQUA + ChatContent.ITALIC + " - Left click to change this item's weight");
							isLore.add(ChatContent.DARK_AQUA + " ------------ Statistics ------------");
							isLore.add(ChatContent.DARK_AQUA + " - " + Language.translate("basic.weight") + " : " + item.getWeight() + " (" + Util.formatNumberTwoDigits(machine.getItemChance(item) * 100) + "% chance to win)");
							isLore.add(ChatContent.DARK_AQUA + " - Times won : " + item.itemStats.timesWon);
							if (isLore.size() > 5)
								isLore.add("");
							if (item.getItemStack().getItemMeta().hasLore() && item.getItemStack().getItemMeta().getLore().size() > 0) {
								isLore.add("");
								isLore.addAll(item.getItemStack().getItemMeta().getLore());
							}
							items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(new ItemStack(item.getItemStack()), isLore), event -> {
								if (event.isRightClick()) {
									player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
									if (player.getInventory().firstEmpty() < 0)
										player.sendMessage(ChatContent.RED + "[Slot Machine] You need a free slot in your inventory");
									else {
										player.getInventory().addItem(new ItemStack(item.getItemStack()));
										machine.removeItem(item);
										SlotPlugin.saveToDisk();
										player.updateInventory();
										manageItems(player, machine, page);
									}
								} else if (event.isLeftClick() && event.getCursor().getType() == Material.AIR) {
									player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
									ChangeItemWeight.changeItemWeight(player, machine, item, page);
								}
							}));
						}
						
						pagination.setItems(items.toArray(new ClickableItem[items.size()]));
						pagination.addToIterator(contents.newIterator(SlotIterator.Type.HORIZONTAL, 1, 0));
						
						contents.set(0, 2, ClickableItem.empty(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.INFOS), ChatContent.GOLD + "Informations"), Arrays.asList(
								ChatContent.AQUA + "Right click an item to remove it from",
								ChatContent.AQUA + "the Slot Machine, it will be given back",
								ChatContent.AQUA + "to you",
								"",
								ChatContent.AQUA + "Drag and Drop an item inside this space",
								ChatContent.AQUA + "to add it to the Slot Machine",
								"",
								ChatContent.AQUA + "Left Click (Scroll Wheel click) to edit",
								ChatContent.AQUA + "an item's weight",
								"",
								ChatContent.AQUA + "You can hover items to see each item's",
								ChatContent.AQUA + "statistics (weight, chance and times won).",
								ChatContent.AQUA + "Hover the yellow info icon to see this",
								ChatContent.AQUA + "machine's statistics",
								"",
								ChatContent.GRAY + "Note : Times used stat for machines",
								ChatContent.GRAY + "was added in Slot Machine 6.2.1",
								ChatContent.GRAY + "Times won stat for items has been",
								ChatContent.GRAY + "added in Slot Machine 6.3.0"
								))));
						
						contents.set(0, 4, ClickableItem.empty(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.SMALL_INFOS), ChatContent.GOLD + "Machine Statistics"), Arrays.asList(
								ChatContent.AQUA + "This machine has been used " + ChatContent.DARK_AQUA + machine.getTimesUsed() + ChatContent.AQUA + " times",
								"",
								ChatContent.GRAY + "Note : Times used stat has been",
								ChatContent.GRAY + "added in Slot machine 6.2.1"
								))));
						
						contents.set(0, 6, ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(Material.BARRIER), ChatContent.RED + "Clear ALL Items"), Arrays.asList(
								ChatContent.GRAY + "Remove ALL items from this machine",
								ChatContent.GRAY + "This will NOT give you back the items"
							)), event -> {
								ConfirmInventory.confirmWindow(player, "Clear all items ?", "No, cancel", "Yes, clear them", callback -> {
									if (callback) {
										player.playSound(player.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1F, 1F);
										machine.setSlotMachineItems(new ArrayList<MachineItem>());
										SlotPlugin.saveToDisk();
										manageItems(player, machine, 0);
									} else
										manageItems(player, machine, page);
								}, false);
								
						}));
						
						contents.set(0, 7, ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.TRASH_CAN), ChatContent.RED + "Reset all Statistics"), Arrays.asList(
								ChatContent.GRAY + "This will reset this machine's and all",
								ChatContent.GRAY + "items statistics to 0"
							)), event -> {
								ConfirmInventory.confirmWindow(player, "Reset all Statistics ?", "No, cancel", "Yes, reset", callback -> {
									if (callback) {
										player.playSound(player.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1F, 1F);
										for(MachineItem it : machine.getSlotMachineItems()) {
											it.itemStats.timesWon = 0;
										}
										machine.setTimesUsed(0);
										SlotPlugin.saveToDisk();
										manageItems(player, machine, page);
									}
								}, false);
								
						}));

						contents.set(5, 1, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.BACK), "<- Back"), event -> {
							player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
							if (machine instanceof SlotMachineEntity)
								MachineInterractionInventory.manageMachine(player, machine, ((SlotMachineEntity) machine).getEntity(), null, 0);
							else if (machine instanceof SlotMachineBlock)
								MachineInterractionInventory.manageMachine(player, machine, null, ((SlotMachineBlock) machine).getBlock(), 0);
						}));
						
						
						if (!pagination.isFirst())
							contents.set(5, 3, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.LEFT), "< Previous Page"), event -> {
								player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
								manageItems(player, machine, page - 1);
							}));

						if (!pagination.isLast())
							contents.set(5, 5, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.RIGHT), "Next Page >"), event -> {
								player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
								manageItems(player, machine, page + 1);
							}));
						
						contents.set(5, 4, ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(Material.PAPER), "Page " + (pagination.getPage() + 1) + "/" + (pagination.last().getPage() + 1))));
					}

					@Override
					public void update(Player player, InventoryContents contents) {
						
					}
					
				})
				.build();
		
		inv.open(player, page);
	}
}
