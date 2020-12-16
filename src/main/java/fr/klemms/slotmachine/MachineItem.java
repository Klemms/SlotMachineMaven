package fr.klemms.slotmachine;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.WordUtils;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;

public class MachineItem {

	private ItemStack itemStack;
	private int weight;
	private List<Reward> rewards;

	public MachineItem(ItemStack itemStack, int weight) {
		this(itemStack, weight, Arrays.asList(new Reward(itemStack)));
	}

	public MachineItem(ItemStack itemStack, int weight, List<Reward> rewards) {
		this.itemStack = itemStack;
		this.weight = weight;
		this.rewards = rewards;
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
	
	public String getRealName() {
		return itemStack.getItemMeta().hasDisplayName() ? itemStack.getItemMeta().getDisplayName() : WordUtils.capitalizeFully(itemStack.getType().toString().replace('_', ' '));
	}

	public ItemStack getItemStack() {
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
