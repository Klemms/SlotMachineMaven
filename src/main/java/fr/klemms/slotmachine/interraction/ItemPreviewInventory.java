package fr.klemms.slotmachine.interraction;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.MachineItem;
import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.translation.Language;
import fr.klemms.slotmachine.utils.ItemStackUtil;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import fr.minuskube.inv.content.Pagination;
import fr.minuskube.inv.content.SlotIterator.Type;

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
								isLore.add(ChatContent.DARK_AQUA + " " + (machine.showItemWeightOnPreview() ? Language.translate("basic.weight") + " : " + item.getWeight() : "") + (machine.showItemWeightOnPreview() && machine.showChanceOfItemOnPreview() ? "  -  " : "") + (machine.showChanceOfItemOnPreview() ? Language.translate("basic.chance") + " : " + (int)(machine.getItemChance(item) * 100) + "% " : " ")) ;
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

						contents.set(5, 1, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(Material.COMPASS), "<- Back"), event -> {
							machine.openMachine(player, true);
						}));
						
						if (!pagination.isFirst())
							contents.set(5, 3, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(Material.ARROW), "< Previous Page"), event -> {
								showPreview(player, machine, page - 1);
							}));

						if (!pagination.isLast())
							contents.set(5, 5, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(Material.ARROW), "Next Page >"), event -> {
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
