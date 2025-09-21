package fr.klemms.slotmachine;

import fr.klemms.slotmachine.utils.ItemStackUtil;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.apache.commons.lang3.text.WordUtils;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MachineItem {

	private ItemStack itemStack;
	private int weight;
	private List<Reward> rewards;
	public ItemStat itemStats;
	public boolean showAttributeModifiers;

	public MachineItem(ItemStack itemStack, int weight) {
		this(itemStack, weight, new ArrayList<Reward>(Arrays.asList(new Reward(itemStack))), true);
	}

	public MachineItem(ItemStack itemStack, int weight, List<Reward> rewards, boolean showAttributeModifiers) {
		this.itemStack = itemStack;
		this.weight = weight;
		this.rewards = rewards;
		this.itemStats = new ItemStat();
		this.showAttributeModifiers = showAttributeModifiers;
	}

	public BaseComponent[] getRewardName() {
		if (itemStack.hasItemMeta()) {
			ItemMeta im = itemStack.getItemMeta();
			if (im.hasDisplayName())
				return new ComponentBuilder(im.getDisplayName()).create();
			else if (im.hasLocalizedName())
				return new ComponentBuilder(im.getLocalizedName()).color(ChatColor.WHITE).create();
			else
				return new ComponentBuilder(WordUtils.capitalizeFully(itemStack.getType().toString().replace('_', ' '))).color(ChatColor.WHITE).create();
		} else {
			return new ComponentBuilder(WordUtils.capitalizeFully(itemStack.getType().toString().replace('_', ' '))).color(ChatColor.WHITE).create();
		}
	}

	public MachineItem copy() {
		List<Reward> newRewards = new ArrayList<Reward>();
		for (int i = 0; i < rewards.size(); i++) {
			if (rewards.get(i).rewardType == RewardType.ITEM) {
				newRewards.add(new Reward(new ItemStack(rewards.get(i).itemReward)));
			} else if (rewards.get(i).rewardType == RewardType.COMMAND) {
				newRewards.add(new Reward(rewards.get(i).commandReward));
			}
		}

		return new MachineItem(new ItemStack(this.itemStack), weight, newRewards, showAttributeModifiers);
	}

	public String getRealName() {
		return itemStack.getItemMeta().hasDisplayName() ? itemStack.getItemMeta().getDisplayName() : WordUtils.capitalizeFully(itemStack.getType().toString().replace('_', ' '));
	}

	public ItemStack getItemStack(boolean affectedByModifiers) {
		if (affectedByModifiers) {
			return ItemStackUtil.changeAttributeModifiers(new ItemStack(itemStack), showAttributeModifiers);
		}

		return itemStack;
	}

	public void setItemStack(ItemStack itemStack) {
		this.itemStack = itemStack;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public List<Reward> getRewards() {
		return rewards;
	}

	public void setRewards(List<Reward> rewards) {
		this.rewards = rewards;
	}

	public void addReward(Reward reward) {
		this.rewards.add(reward);
	}

	public enum RewardType {
		ITEM,
		COMMAND
	}

	public static class Reward {

		public ItemStack itemReward = null;
		public String commandReward = null;
		public RewardType rewardType;

		public Reward(ItemStack itemReward) {
			this.rewardType = RewardType.ITEM;
			this.itemReward = itemReward;
		}

		public Reward(String commandReward) {
			this.rewardType = RewardType.COMMAND;
			this.commandReward = commandReward;
		}
	}
}
