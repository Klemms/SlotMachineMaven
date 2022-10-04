package fr.klemms.slotmachine.interraction;

import fr.klemms.slotmachine.*;
import fr.klemms.slotmachine.fr.minuskube.inv.ClickableItem;
import fr.klemms.slotmachine.fr.minuskube.inv.SmartInventory;
import fr.klemms.slotmachine.fr.minuskube.inv.content.InventoryContents;
import fr.klemms.slotmachine.fr.minuskube.inv.content.InventoryProvider;
import fr.klemms.slotmachine.fr.minuskube.inv.content.Pagination;
import fr.klemms.slotmachine.fr.minuskube.inv.content.SlotIterator.Type;
import fr.klemms.slotmachine.translation.Language;
import fr.klemms.slotmachine.utils.ItemStackUtil;
import fr.klemms.slotmachine.utils.PlayerHeadsUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MachineListInventory {

	public static void openMachineList(Player player, int page, MachineListCallback callback) {
		SmartInventory inv = SmartInventory.builder()
				.manager(SlotPlugin.invManager)
				.title("Slot Machines")
				.size(6, 9)
				.closeable(true)
				.provider(new InventoryProvider() {

					@Override
					public void init(Player player, InventoryContents contents) {
						Pagination pagination = contents.pagination();

						contents.fill(ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1), " ")));
						
						pagination.setItemsPerPage(4 * 9);
						List<ClickableItem> items = new ArrayList<ClickableItem>();

						List<SlotMachine> machines = new ArrayList<SlotMachine>();
						machines.addAll(SlotMachine.getSlotMachinesByType(SlotMachineType.BLOCK));
						machines.addAll(SlotMachine.getSlotMachinesByType(SlotMachineType.ENTITY));
						
						for(final SlotMachine machine : machines)  {
							ItemStack machineStack = new ItemStack(PlayerHeadsUtil.SLOT_MACHINE);
							ItemStackUtil.changeItemStackName(machineStack, ChatContent.GOLD + (machine.getSlotMachineName().length() > 0 ? machine.getSlotMachineName() : machine.getMachineUUID().toString()));
							List<String> machineLore = new ArrayList<String>();

							machineLore.add(ChatContent.AQUA + "Type : " + ChatContent.GRAY + ChatContent.ITALIC + machine.getSlotMachineType().toString());
							machineLore.add(ChatContent.AQUA + "Machine UUID : " + ChatContent.GRAY + ChatContent.ITALIC + machine.getMachineUUID().toString());
							if (machine.getLastFileName().length() > 0) {
								machineLore.add(ChatContent.AQUA + "File Name : " + ChatContent.GRAY + ChatContent.ITALIC + machine.getLastFileName());
							}
							machineLore.add("");

							if (machine.getSlotMachineType() == SlotMachineType.ENTITY) {
								SlotMachineEntity machineEntity = (SlotMachineEntity) machine;
								machineLore.add(ChatContent.AQUA + "Entity UUID : " + ChatContent.GRAY + ChatContent.ITALIC + machineEntity.getEntityUUID().toString());
							}

							if (machine.getSlotMachineType() == SlotMachineType.BLOCK) {
								SlotMachineBlock machineBlock = (SlotMachineBlock) machine;
								if (Bukkit.getWorld(machineBlock.getWorldUID()) != null) {
									machineLore.add(ChatContent.AQUA + "World Name : " + ChatContent.GRAY + ChatContent.ITALIC + Bukkit.getWorld(machineBlock.getWorldUID()).getName());
								}
								machineLore.add(ChatContent.AQUA + "World UUID : " + ChatContent.GRAY + ChatContent.ITALIC + machineBlock.getWorldUID().toString());
								machineLore.add(ChatContent.AQUA + "Block Position : " + ChatContent.GRAY + ChatContent.ITALIC + machineBlock.getBlockX() + ", " + machineBlock.getBlockY() + ", " + machineBlock.getBlockZ());
							}

							ItemStackUtil.setItemStackLore(machineStack, machineLore);

							items.add(ClickableItem.of(machineStack, event -> {
								if (event.isLeftClick()) {
									callback.callback(machine);
								}
							}));
						}
						
						pagination.setItems(items.toArray(new ClickableItem[items.size()]));
						pagination.addToIterator(contents.newIterator(Type.HORIZONTAL, 1, 0));
						
						contents.set(0, 2, ClickableItem.empty(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.INFOS), ChatContent.GOLD + "Informations"), Arrays.asList(
								ChatContent.AQUA + "Pick a Slot Machine that will be",
								ChatContent.AQUA + "the original machine your new",
								ChatContent.AQUA + "link will inherit from."
								))));
						
						
						if (!pagination.isFirst())
							contents.set(5, 3, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.LEFT), Language.translate("basic.previouspage")), event -> {
								player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
								openMachineList(player, page - 1, callback);
							}));

						if (!pagination.isLast())
							contents.set(5, 5, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.RIGHT), Language.translate("basic.nextpage")), event -> {
								player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
								openMachineList(player, page + 1, callback);
							}));
						
						contents.set(5, 4, ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(Material.PAPER), Language.translate("basic.page") + " " + (pagination.getPage() + 1) + "/" + (pagination.last().getPage() + 1))));
					}

					@Override
					public void update(Player player, InventoryContents contents) {
						
					}
					
				})
				.build();
		
		inv.open(player, page);
	}
}
