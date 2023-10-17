package fr.klemms.slotmachine.interraction.rewards;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.MachineItem;
import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.fr.minuskube.inv.ClickableItem;
import fr.klemms.slotmachine.fr.minuskube.inv.SmartInventory;
import fr.klemms.slotmachine.fr.minuskube.inv.content.InventoryContents;
import fr.klemms.slotmachine.fr.minuskube.inv.content.InventoryProvider;
import fr.klemms.slotmachine.translation.Language;
import fr.klemms.slotmachine.utils.ItemStackUtil;
import fr.klemms.slotmachine.utils.PlayerHeadsUtil;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class AddRewardChoice {

	public static void addReward(Player player, SlotMachine machine, MachineItem item, int backPage, int page, RewardCallback callback) {
		SmartInventory inv = SmartInventory.builder()
				.manager(SlotPlugin.invManager)
				.title("What type do you want to add ?")
				.size(5, 9)
				.closeable(true)
				.provider(new InventoryProvider() {

					@Override
					public void init(Player player, InventoryContents contents) {
						contents.fill(ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1), " ")));

						contents.fillRow(1, ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1), " ")));
						contents.fillRow(2, ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1), " ")));
						contents.fillRow(3, ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1), " ")));

						contents.set(0, 1, ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.INFOS), ChatContent.GOLD + "Pick the kind of reward you want to add")));

						contents.set(2, 3, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(Material.GRASS_BLOCK), ChatContent.GOLD + "Add an item reward"), event -> {
							player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1F, 1F);
							callback.callback(MachineItem.RewardType.ITEM);
						}));

						contents.set(2, 5, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(Material.COMMAND_BLOCK), ChatContent.GOLD + "Add a command reward"), event -> {
							player.playSound(player.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1F, 1F);
							callback.callback(MachineItem.RewardType.COMMAND);
						}));

						contents.set(4, 1, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.BACK), Language.translate("basic.back")), event -> {
							player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
							RewardsCustomization.rewardsCustomization(player, machine, item, backPage, page);
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
