package fr.klemms.slotmachine.interraction.rewards;

import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.fr.minuskube.inv.ClickableItem;
import fr.klemms.slotmachine.fr.minuskube.inv.InventoryListener;
import fr.klemms.slotmachine.fr.minuskube.inv.SmartInventory;
import fr.klemms.slotmachine.fr.minuskube.inv.content.InventoryContents;
import fr.klemms.slotmachine.fr.minuskube.inv.content.InventoryProvider;
import fr.klemms.slotmachine.utils.ItemStackUtil;
import fr.klemms.slotmachine.utils.PlayerHeadsUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class RewardAddItemInput {

	public static void addReward(Player player, String title, boolean closeBeforeCallback, ItemCallback callback) {
		SmartInventory inv = SmartInventory.builder()
				.manager(SlotPlugin.invManager)
				.title(title)
				.size(1, 9)
				.closeable(true)
				.listener(new InventoryListener<InventoryClickEvent>(InventoryClickEvent.class, event -> {
					if (event.getCursor().getType() != Material.AIR && event.isLeftClick() && event.getSlot() == 4) {
						if (closeBeforeCallback) {
							player.closeInventory();
						}
						callback.callback(new ItemStack(event.getCursor()));
					}
				}))
				.provider(new InventoryProvider() {

					@Override
					public void init(Player player, InventoryContents contents) {
						contents.fill(ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1), " ")));
						contents.set(0, 3, ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.ARROW_RIGHT), " ")));
						contents.set(0, 4, null);
						contents.set(0, 5, ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.ARROW_LEFT), " ")));

					}

					@Override
					public void update(Player player, InventoryContents contents) {

					}

				})
				.build();

		inv.open(player);
	}
}
