package fr.klemms.slotmachine;

import org.bukkit.inventory.ItemStack;

import fr.klemms.slotmachine.utils.PlayerHeadsUtil;

public enum PlayMode {

	UNLIMITED(
			"Unlimited", 
			"Allows players to trigger this machine as much time as possible by closing and re-opening this machine UI", 
			PlayerHeadsUtil.INFINITY_SYMBOL),
	LIMITED_PLAYER(
			"Player Limited", 
			"Allows players to play only once at a time on this machine", 
			PlayerHeadsUtil.ONE_BLACKBG),
	LIMITED_PLAYER_GLOBAL(
			"Player Limited (Global)", 
			"Allows players to play only once at a time on all machines that are on this setting", 
			PlayerHeadsUtil.EARTH),
	LIMITED_MACHINE(
			"Machine Limited", 
			"Only one player can play on this machine at a time (Spectating is not supported yet)", 
			PlayerHeadsUtil.QUEUE_SCREEN);
	
	public String title;
	public String description;
	public ItemStack icon;
	
	PlayMode(String title, String description, ItemStack icon) {
		this.title = title;
		this.description = description;
		this.icon = icon;
	}
}
