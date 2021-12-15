package fr.klemms.slotmachine.interraction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.MachineItem;
import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.fr.minuskube.inv.ClickableItem;
import fr.klemms.slotmachine.fr.minuskube.inv.SmartInventory;
import fr.klemms.slotmachine.fr.minuskube.inv.content.InventoryContents;
import fr.klemms.slotmachine.fr.minuskube.inv.content.InventoryProvider;
import fr.klemms.slotmachine.fr.minuskube.inv.content.Pagination;
import fr.klemms.slotmachine.fr.minuskube.inv.content.SlotIterator.Type;
import fr.klemms.slotmachine.translation.Language;
import fr.klemms.slotmachine.utils.ItemStackUtil;
import fr.klemms.slotmachine.utils.PlayerHeadsUtil;
import fr.klemms.slotmachine.utils.Util;

public class ItemPreviewInventory {

	public static void showPreview(Player player, SlotMachine machine, int page) {
		SmartInventory inv = SmartInventory.builder()
				.manager(SlotPlugin.invManager)
				.title("Item Preview")
				.size(6, 9)
				.closeable(true)
				.provider(new InventoryProvider() {

					@Override
					public void init(Player player, InventoryContents contents) {
						Pagination pagination = contents.pagination();

						contents.fill(ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1), " ")));
						
						pagination.setItemsPerPage(4 * 9);
						List<ClickableItem> items = new ArrayList<ClickableItem>();
						
						for(final MachineItem item : machine.getSlotMachineItems())  {
							List<String> isLore = new ArrayList<String>();
							if (machine.showItemWeightOnPreview() || machine.showChanceOfItemOnPreview()) {
								isLore.add(ChatContent.DARK_AQUA + " " + (machine.showItemWeightOnPreview() ? Language.translate("basic.weight") + " : " + item.getWeight() : "") + (machine.showItemWeightOnPreview() && machine.showChanceOfItemOnPreview() ? "  -  " : "") + (machine.showChanceOfItemOnPreview() ? Language.translate("basic.chance") + " : " + Util.formatNumberTwoDigits(machine.getItemChance(item) * 100) + "% " : " ")) ;
								isLore.add("");
								if (item.getItemStack().getItemMeta().hasLore() && item.getItemStack().getItemMeta().getLore().size() > 0)
									isLore.add("");
							}
							if (item.getItemStack().getItemMeta().hasLore() && item.getItemStack().getItemMeta().getLore().size() > 0)
								isLore.addAll(item.getItemStack().getItemMeta().getLore());
							items.add(ClickableItem.empty(ItemStackUtil.setItemStackLore(new ItemStack(item.getItemStack()), isLore)));
						}
						
						pagination.setItems(items.toArray(new ClickableItem[items.size()]));
						pagination.addToIterator(contents.newIterator(Type.HORIZONTAL, 1, 0));
						
						if (machine.isAffectedByLuck()) {
							contents.set(0, 1, ClickableItem.empty(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.INFOS), ChatContent.GOLD + "Informations"), Arrays.asList(
									ChatContent.AQUA + "Potion effects will affect",
									ChatContent.AQUA + "your chances on this machine",
									ChatContent.AQUA + "",
									ChatContent.DARK_GRAY + ChatContent.ITALIC + "Note : Chances don't include",
									ChatContent.DARK_GRAY + ChatContent.ITALIC + "potion effects"
									))));
						}

						contents.set(5, 1, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.BACK), "<- Back"), event -> {
							player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
							machine.openMachine(player, true);
						}));
						
						if (!pagination.isFirst())
							contents.set(5, 3, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.LEFT), "< Previous Page"), event -> {
								player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
								showPreview(player, machine, page - 1);
							}));

						if (!pagination.isLast())
							contents.set(5, 5, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.RIGHT), "Next Page >"), event -> {
								player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
								showPreview(player, machine, page + 1);
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
