package fr.klemms.slotmachine.interraction;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.utils.ItemStackUtil;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;

public class ConfirmInventory {

	public static void confirmWindow(Player player, String title, String declineTitle, String acceptTitle, InterractionCallback callback, boolean closeAfter) {
		SmartInventory inv = SmartInventory.builder()
				.manager(SlotPlugin.invManager)
				.title(title)
				.size(3, 9)
				.closeable(true)
				.provider(new InventoryProvider() {

					@Override
					public void init(Player player, InventoryContents contents) {
						contents.fill(ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1), " ")));
						
						ClickableItem decline = ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(Material.RED_STAINED_GLASS_PANE, 1), declineTitle), event -> {
							if (closeAfter)
								player.closeInventory();
							callback.callback(false);
						});
						
						ClickableItem accept = ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1), acceptTitle), event -> {
							if (closeAfter)
								player.closeInventory();
							callback.callback(true);
						});
						
						contents.set(1, 1, decline);
						contents.set(1, 2, decline);
						contents.set(1, 3, decline);
						
						contents.set(1, 5, accept);
						contents.set(1, 6, accept);
						contents.set(1, 7, accept);
					}

					@Override
					public void update(Player player, InventoryContents contents) { }
					
				})
				.build();
		
		inv.open(player);
	}
}
