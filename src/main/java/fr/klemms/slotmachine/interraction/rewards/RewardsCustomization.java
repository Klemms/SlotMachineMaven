package fr.klemms.slotmachine.interraction.rewards;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.MachineItem;
import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.fr.minuskube.inv.ClickableItem;
import fr.klemms.slotmachine.fr.minuskube.inv.InventoryListener;
import fr.klemms.slotmachine.fr.minuskube.inv.SmartInventory;
import fr.klemms.slotmachine.fr.minuskube.inv.content.InventoryContents;
import fr.klemms.slotmachine.fr.minuskube.inv.content.InventoryProvider;
import fr.klemms.slotmachine.interraction.ConfirmInventory;
import fr.klemms.slotmachine.interraction.ItemEdit;
import fr.klemms.slotmachine.interraction.StringInput;
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
import java.util.Iterator;
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
						List<MachineItem.Reward> rewards = item.getRewards();
						int slot = -1;
						switch(event.getSlot()) {
							// Appearance Item
							case 19:
							case 28:
								event.setCancelled(true);
								item.setItemStack(new ItemStack(event.getCursor()));
								machine.save();
								RewardsCustomization.rewardsCustomization(player, machine, item, backPage, page);
								return;
							case 21:
							case 30:
								event.setCancelled(true);
								slot = 5 * page;
								break;
							case 22:
							case 31:
								event.setCancelled(true);
								slot = 1 + 5 * page;
								break;
							case 23:
							case 32:
								event.setCancelled(true);
								slot = 2 + 5 * page;
								break;
							case 24:
							case 33:
								event.setCancelled(true);
								slot = 3 + 5 * page;
								break;
							case 25:
							case 34:
								event.setCancelled(true);
								slot = 4 + 5 * page;
								break;
						}

						if (slot == -1 || rewards.size() <= slot || rewards.get(slot).rewardType == MachineItem.RewardType.COMMAND)
							return;

						rewards.set(slot, new MachineItem.Reward(new ItemStack(event.getCursor())));
						player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 2F, 1.5F);
						machine.save();
						RewardsCustomization.rewardsCustomization(player, machine, item, backPage, page);
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
									machine.save();
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

							contents.set(2, i + 3, ClickableItem.of(ItemStackUtil.changeItemStackName(ItemStackUtil.addLoreLines(new ItemStack(PlayerHeadsUtil.ARROW_RIGHT),
									ChatContent.AQUA + "Rewards are given in order",
									ChatContent.AQUA + "from left to right.",
									"",
									ChatContent.AQUA + "Reward Type : " + ChatContent.GOLD + (reward.rewardType == MachineItem.RewardType.ITEM ? "Item" : "Command"),
									"",
									ChatContent.AQUA + (reward.rewardType == MachineItem.RewardType.ITEM ? "Drag and drop an item" : ChatContent.DARK_AQUA + "[Left Click]" + ChatContent.AQUA + " to edit the command"),
									ChatContent.AQUA + (reward.rewardType == MachineItem.RewardType.ITEM ? "here to replace it" : ""),
									"",
									ChatContent.DARK_RED + "[Right Click]" + ChatContent.RED + " to remove reward."
							), ChatContent.GOLD + "Reward"), event -> {
								if (event.isRightClick()) {
									if (item.getRewards().size() == 1) {
										player.sendMessage(ChatContent.RED + SlotPlugin.CHAT_PREFIX + "You can't remove the last reward of an item");
										player.closeInventory();
										return;
									}

									Iterator<MachineItem.Reward> rewardIterator = item.getRewards().iterator();
									while (rewardIterator.hasNext()) {
										MachineItem.Reward rew = rewardIterator.next();
										if (reward.equals(rew)) {
											rewardIterator.remove();
											machine.save();
											RewardsCustomization.rewardsCustomization(player, machine, item, backPage, 0);
											break;
										}
									}
								} else if (event.isLeftClick() && reward.rewardType == MachineItem.RewardType.COMMAND) {
									StringInput.inputString(player, "Edit the command", reward.commandReward, text -> {
										reward.commandReward = text;
										machine.save();
										RewardsCustomization.rewardsCustomization(player, machine, item, backPage, page);
									}, true);
								}
							}));

							contents.set(3, i + 3, ClickableItem.of(ItemStackUtil.changeItemStackName(ItemStackUtil.addLoreLines(new ItemStack(reward.rewardType == MachineItem.RewardType.ITEM ? reward.itemReward : PlayerHeadsUtil.COMMAND_BLOCK),
									ChatContent.AQUA + (reward.rewardType == MachineItem.RewardType.ITEM ? "Drag and drop an item" : ChatContent.DARK_AQUA + "[Left Click]" + ChatContent.AQUA + " to edit the command"),
									ChatContent.AQUA + (reward.rewardType == MachineItem.RewardType.ITEM ? "here to replace it" : ""),
									"",
									ChatContent.DARK_RED + "[Right Click]" + ChatContent.RED + " to remove reward.",
									"",
									reward.rewardType == MachineItem.RewardType.ITEM ? "--------------" : ChatContent.GRAY + "Command :",
									reward.rewardType == MachineItem.RewardType.ITEM ? "" : ChatContent.PINK + reward.commandReward
							), reward.rewardType == MachineItem.RewardType.COMMAND ? ChatContent.GOLD + "Command" : null), event -> {
								if (event.isRightClick()) {
									if (item.getRewards().size() == 1) {
										player.sendMessage(ChatContent.RED + SlotPlugin.CHAT_PREFIX + "You can't remove the last reward of an item");
										player.closeInventory();
										return;
									}

									Iterator<MachineItem.Reward> rewardIterator = item.getRewards().iterator();
									while (rewardIterator.hasNext()) {
										MachineItem.Reward rew = rewardIterator.next();
										if (reward.equals(rew)) {
											rewardIterator.remove();
											machine.save();
											RewardsCustomization.rewardsCustomization(player, machine, item, backPage, 0);
											break;
										}
									}
								} else if (event.isLeftClick() && reward.rewardType == MachineItem.RewardType.COMMAND) {
									StringInput.inputString(player, "Edit the command", reward.commandReward, text -> {
										reward.commandReward = text;
										machine.save();
										RewardsCustomization.rewardsCustomization(player, machine, item, backPage, page);
									}, true);
								}
							}));
						}

						if ((item.getRewards().size() - (5 * page)) < 5) {
							contents.set(3, 3 + (item.getRewards().size() - (5 * page)), ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.PLUS_SIGN), ChatContent.GOLD + "Add a Reward"), event -> {
								player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
								AddRewardChoice.addReward(player, machine, item, backPage, page, rewardType -> {
									if (rewardType == MachineItem.RewardType.ITEM) {
										RewardAddItemInput.addReward(player, "Put the item you want to add", false, newItem -> {
											item.addReward(new MachineItem.Reward(newItem));
											player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1.5F, 1.5F);
											machine.save();
											RewardsCustomization.rewardsCustomization(player, machine, item, backPage, page);
										});
									} else if (rewardType == MachineItem.RewardType.COMMAND) {
										StringInput.inputString(player, "Type the command", "Check [i] for infos", text -> {
											item.addReward(new MachineItem.Reward(text));
											player.playSound(player.getLocation(), Sound.BLOCK_BEACON_POWER_SELECT, 1.5F, 2F);
											machine.save();
											RewardsCustomization.rewardsCustomization(player, machine, item, backPage, page);
										}, false);
									}
								});
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
								ChatContent.AQUA + "to get a description of the item"
								))));

						contents.set(5, 1, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.BACK), Language.translate("basic.back")), event -> {
							player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
							ItemEdit.editItem(player, machine, item, backPage);
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
