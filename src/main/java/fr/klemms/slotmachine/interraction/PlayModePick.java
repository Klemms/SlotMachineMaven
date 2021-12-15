package fr.klemms.slotmachine.interraction;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.PlayMode;
import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotMachineBlock;
import fr.klemms.slotmachine.SlotMachineEntity;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.fr.minuskube.inv.ClickableItem;
import fr.klemms.slotmachine.fr.minuskube.inv.SmartInventory;
import fr.klemms.slotmachine.fr.minuskube.inv.content.InventoryContents;
import fr.klemms.slotmachine.fr.minuskube.inv.content.InventoryProvider;
import fr.klemms.slotmachine.fr.minuskube.inv.content.Pagination;
import fr.klemms.slotmachine.fr.minuskube.inv.content.SlotIterator.Type;
import fr.klemms.slotmachine.utils.ItemStackUtil;
import fr.klemms.slotmachine.utils.PlayerHeadsUtil;
import fr.klemms.slotmachine.utils.Util;

public class PlayModePick {

	public static void pickPlayMode(Player player, SlotMachine machine, int page) {
		SmartInventory inv = SmartInventory.builder()
				.manager(SlotPlugin.invManager)
				.title("Select a Mode")
				.size(6, 9)
				.closeable(true)
				.provider(new InventoryProvider() {

					@Override
					public void init(Player player, InventoryContents contents) {
						Pagination pagination = contents.pagination();

						contents.fill(ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1), " ")));
						
						pagination.setItemsPerPage(4 * 9);
						List<ClickableItem> items = new ArrayList<ClickableItem>();
						
						for(PlayMode mode : PlayMode.values()) {
							ItemStack is = new ItemStack(mode.icon);
							
							ItemStackUtil.changeItemStackName(is, ChatContent.GOLD + mode.title);
							
							List<String> lore = Util.addToStartOfLines(ChatContent.AQUA + ChatContent.ITALIC, Util.splitLines(mode.description, 170));
							if (mode == PlayMode.LIMITED_PLAYER || mode == machine.getPlayMode()) {
								lore.add("");
								
								if (mode == machine.getPlayMode())
									lore.add(ChatContent.DARK_PURPLE + ChatContent.ITALIC + " - Current Setting");
								if (mode == PlayMode.LIMITED_PLAYER)
									lore.add(ChatContent.DARK_PURPLE + ChatContent.ITALIC + " - Default Setting");
							}
							
							ItemStackUtil.setItemStackLore(is, lore);
							
							items.add(ClickableItem.of(is, event -> {
								player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
								machine.setPlayMode(mode);
								player.sendMessage(ChatContent.GREEN + "[Slot Machine] Successfully changed play mode to : " + mode.title);
								SlotPlugin.saveToDisk();
								
								if (machine instanceof SlotMachineEntity)
									MachineInterractionInventory.manageMachine(player, machine, ((SlotMachineEntity) machine).getEntity(), null, 0);
								else if (machine instanceof SlotMachineBlock)
									MachineInterractionInventory.manageMachine(player, machine, null, ((SlotMachineBlock) machine).getBlock(), 0);
							}));
						}
						
						pagination.setItems(items.toArray(new ClickableItem[items.size()]));
						pagination.addToIterator(contents.newIterator(Type.HORIZONTAL, 1, 0));

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
								pickPlayMode(player, machine, page - 1);
							}));

						if (!pagination.isLast())
							contents.set(5, 5, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.RIGHT), "Next Page >"), event -> {
								player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
								pickPlayMode(player, machine, page + 1);
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
