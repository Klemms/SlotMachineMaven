package fr.klemms.slotmachine.interraction;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotMachineBlock;
import fr.klemms.slotmachine.SlotMachineEntity;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.fr.minuskube.inv.ClickableItem;
import fr.klemms.slotmachine.fr.minuskube.inv.InventoryListener;
import fr.klemms.slotmachine.fr.minuskube.inv.SmartInventory;
import fr.klemms.slotmachine.fr.minuskube.inv.content.InventoryContents;
import fr.klemms.slotmachine.fr.minuskube.inv.content.InventoryProvider;
import fr.klemms.slotmachine.translation.Language;
import fr.klemms.slotmachine.utils.ItemStackUtil;
import fr.klemms.slotmachine.utils.PlayerHeadsUtil;

public class MachineBackgroundCustomization {

	public static void customizeBackground(Player player, SlotMachine machine) {
		SmartInventory inv = SmartInventory.builder()
				.manager(SlotPlugin.invManager)
				.title("Background customization")
				.size(6, 9)
				.closeable(true)
				.listener(new InventoryListener<InventoryClickEvent>(InventoryClickEvent.class, event -> {
					if (event.getCursor().getType() != Material.AIR && event.isLeftClick()) {
						switch(event.getSlot()) {
							case 28:
								machine.setBackgroundItem(new ItemStack(event.getCursor()));
								SlotPlugin.saveToDisk();
								break;
							case 30:
								machine.setEmphasisItem(new ItemStack(event.getCursor()));
								SlotPlugin.saveToDisk();
								break;
							case 32:
								machine.setLeverItem(new ItemStack(event.getCursor()));
								SlotPlugin.saveToDisk();
								break;
							case 34:
								machine.setItemListItem(new ItemStack(event.getCursor()));
								SlotPlugin.saveToDisk();
								break;
						}
						event.setCursor(null);
						event.setCancelled(true);
						customizeBackground(player, machine);
					}
				}))
				.provider(new InventoryProvider() {

					@Override
					public void init(Player player, InventoryContents contents) {
						contents.fill(ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1), " ")));

						contents.fillRow(1, ClickableItem.empty(null));
						contents.fillRow(2, ClickableItem.empty(null));
						contents.fillRow(3, ClickableItem.empty(null));
						contents.fillRow(4, ClickableItem.empty(null));
						
						contents.set(2, 1, ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.SMALL_INFOS), ChatContent.GOLD + "Background Item")));
						contents.set(3, 1, ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(machine.getBackgroundItem()), " ")));

						contents.set(2, 3, ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.SMALL_INFOS), ChatContent.GOLD + "Emphasis Item")));
						contents.set(3, 3, ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(machine.getEmphasisItem()), " ")));

						contents.set(2, 5, ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.SMALL_INFOS), ChatContent.GOLD + "Lever Item")));
						contents.set(3, 5, ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(machine.getLeverItem()), " ")));

						contents.set(2, 7, ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.SMALL_INFOS), ChatContent.GOLD + "Item-List Item")));
						contents.set(3, 7, ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(machine.getItemListItem()), " ")));
						
						contents.set(0, 2, ClickableItem.empty(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.INFOS), ChatContent.GOLD + "Informations"), Arrays.asList(
								ChatContent.AQUA + "Drag and Drop an item on another item",
								ChatContent.AQUA + "to replace it, hover information icons",
								ChatContent.AQUA + "to get a description of the item",
								ChatContent.AQUA + "",
								ChatContent.GRAY + "Note : This will destroy the item",
								ChatContent.GRAY + "on your cursor"
								))));
						
						contents.set(0, 6, ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(Material.BARRIER), ChatContent.RED + "Reset to default"), Arrays.asList(
								ChatContent.GRAY + "Resets all background customization",
								ChatContent.GRAY + "to default values"
							)), event -> {
								ConfirmInventory.confirmWindow(player, "Reset customization ?", "No, cancel", "Yes, reset", callback -> {
									if (callback) {
										player.playSound(player.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1F, 1F);
										machine.resetBackgroundCustomization();
										SlotPlugin.saveToDisk();
									}
									customizeBackground(player, machine);
								}, false);
								
						}));

						contents.set(5, 1, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.BACK), Language.translate("basic.back")), event -> {
							player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
							if (machine instanceof SlotMachineEntity)
								MachineInterractionInventory.manageMachine(player, machine, ((SlotMachineEntity) machine).getEntity(), null, 0);
							else if (machine instanceof SlotMachineBlock)
								MachineInterractionInventory.manageMachine(player, machine, null, ((SlotMachineBlock) machine).getBlock(), 0);
						}));
						
					}

					@Override
					public void update(Player player, InventoryContents contents) {
						
					}
					
				})
				.build();
		
		inv.open(player);
	}
}
