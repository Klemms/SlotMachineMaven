package fr.klemms.slotmachine.interraction;

import fr.klemms.slotmachine.*;
import fr.klemms.slotmachine.fr.minuskube.inv.ClickableItem;
import fr.klemms.slotmachine.fr.minuskube.inv.InventoryListener;
import fr.klemms.slotmachine.fr.minuskube.inv.SmartInventory;
import fr.klemms.slotmachine.fr.minuskube.inv.content.InventoryContents;
import fr.klemms.slotmachine.fr.minuskube.inv.content.InventoryProvider;
import fr.klemms.slotmachine.translation.Language;
import fr.klemms.slotmachine.utils.ItemStackUtil;
import fr.klemms.slotmachine.utils.PlayerHeadsUtil;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RewardsCustomization {

	public static void rewardsCustomization(Player player, SlotMachine machine, MachineItem item, int backPage, int page) {
		SmartInventory inv = SmartInventory.builder()
				.manager(SlotPlugin.invManager)
				.title("Reward Customization")
				.size(6, 9)
				.closeable(true)
				.listener(new InventoryListener<InventoryClickEvent>(InventoryClickEvent.class, event -> {
					if (event.getCursor().getType() != Material.AIR && event.isLeftClick()) {

					}
				}))
				.provider(new InventoryProvider() {

					@Override
					public void init(Player player, InventoryContents contents) {
						contents.fill(ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1), " ")));

						contents.fillRow(1, ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1), " ")));
						contents.fillRow(2, ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1), " ")));
						contents.fillRow(3, ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1), " ")));
						contents.fillRow(4, ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1), " ")));

						contents.set(0, 7, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(Material.BARRIER, 1), ChatContent.RED + "Reset reward to the appearance item"), event -> {
							player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
							ConfirmInventory.confirmWindow(player, "Reset reward to appearance ?", "No, cancel", "Yes, reset", result -> {
								if (result) {
									List<MachineItem.Reward> newRewards = new ArrayList<MachineItem.Reward>();
									newRewards.add(new MachineItem.Reward(new ItemStack(item.getItemStack())));
									item.setRewards(newRewards);
									SlotPlugin.saveToDisk();
									player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1.5F, 1F);
								}
								RewardsCustomization.rewardsCustomization(player, machine, item, backPage, page);
							}, false);
						}));

						contents.set(2, 1, ClickableItem.empty(ItemStackUtil.changeItemStackName(ItemStackUtil.addLoreLines(new ItemStack(PlayerHeadsUtil.INFOS),
								ChatContent.AQUA + "This is the item as it appears",
								ChatContent.AQUA + "to players in machines.",
								"",
								ChatContent.AQUA + "You can change its appearance by",
								ChatContent.AQUA + "drag-and-dropping an item here.",
								"",
								ChatContent.GRAY + "Note that only the appearance will",
								ChatContent.GRAY + "change, the rewards will stay."
								), ChatContent.GOLD + "Item Appearance")));
						contents.set(3, 1, ClickableItem.empty(new ItemStack(item.getItemStack())));

						for (int i = 0; i < (item.getRewards().size() - 5 * page) && i < 5; i++) {
							MachineItem.Reward reward = item.getRewards().get(i + 5 * page);

							contents.set(2, i + 3, ClickableItem.empty(ItemStackUtil.changeItemStackName(ItemStackUtil.addLoreLines(new ItemStack(PlayerHeadsUtil.ARROW_RIGHT),
									ChatContent.AQUA + "Rewards are given in order",
									ChatContent.AQUA + "from left to right.",
									"",
									ChatContent.AQUA + "Reward Type : " + ChatContent.GOLD + (reward.rewardType == MachineItem.RewardType.ITEM ? "Item" : "Command"),
									"",
									ChatContent.AQUA + (reward.rewardType == MachineItem.RewardType.ITEM ? "Drag and drop an item" : "Click here to edit"),
									ChatContent.AQUA + (reward.rewardType == MachineItem.RewardType.ITEM ? "here to replace it" : "the command")
							), ChatContent.GOLD + "Reward")));

							contents.set(3, i + 3, ClickableItem.empty(ItemStackUtil.addLoreLines(new ItemStack(reward.rewardType == MachineItem.RewardType.ITEM ? reward.itemReward : PlayerHeadsUtil.COMMAND_BLOCK),
									ChatContent.AQUA + (reward.rewardType == MachineItem.RewardType.ITEM ? "Drag and drop an item" : "Click here to edit"),
									ChatContent.AQUA + (reward.rewardType == MachineItem.RewardType.ITEM ? "here to replace it" : "the command"),
									"--------------",
									""
							)));
						}

						if ((item.getRewards().size() - (5 * page)) < 5) {
							contents.set(3, 3 + (item.getRewards().size() - (5 * page)), ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.PLUS_SIGN), ChatContent.GOLD + "Add Reward"), event -> {
								player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
							}));
						}

						if (page > 0) {
							contents.set(3, 2, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.LEFT), Language.translate("basic.previouspage")), event -> {
								player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
								RewardsCustomization.rewardsCustomization(player, machine, item, backPage, page - 1);
							}));
						}

						if ((item.getRewards().size() - (5 * page)) >= 5) {
							contents.set(3, 8, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.RIGHT), Language.translate("basic.nextpage")), event -> {
								player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
								RewardsCustomization.rewardsCustomization(player, machine, item, backPage, page + 1);
							}));
						}

						contents.set(0, 1, ClickableItem.empty(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.INFOS), ChatContent.GOLD + "Informations"), Arrays.asList(
								ChatContent.AQUA + "Drag and Drop an item on another item",
								ChatContent.AQUA + "to replace it, hover information icons",
								ChatContent.AQUA + "to get a description of the item",
								ChatContent.AQUA + "",
								ChatContent.GRAY + "Note : This will destroy the item",
								ChatContent.GRAY + "on your cursor"
								))));

						contents.set(5, 1, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.BACK), Language.translate("basic.back")), event -> {
							player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
							if (machine instanceof SlotMachineEntity)
								MachineInterractionInventory.manageMachine(player, machine, ((SlotMachineEntity) machine).getEntity(), null, 0);
							else if (machine instanceof SlotMachineBlock)
								MachineInterractionInventory.manageMachine(player, machine, null, ((SlotMachineBlock) machine).getBlock(), 0);
						}));

						contents.set(5, 7, ClickableItem.empty(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.SMALL_INFOS), ChatContent.GOLD + "Item Being Edited"), Arrays.asList(
								ChatContent.AQUA + "The item on the right is the item",
								ChatContent.AQUA + "currently being edited"
						))));

						contents.set(5, 8, ClickableItem.empty(new ItemStack(item.getItemStack())));
					}

					@Override
					public void update(Player player, InventoryContents contents) {

					}

				})
				.build();

		inv.open(player);
	}
}
