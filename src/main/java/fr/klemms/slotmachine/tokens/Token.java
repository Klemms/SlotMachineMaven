package fr.klemms.slotmachine.tokens;

import org.bukkit.inventory.ItemStack;

public class Token {

	public String identifier;
	public ItemStack itemStack;
	
	public Token(String identifier, ItemStack itemStack) {
		this.identifier = identifier;
		this.itemStack = itemStack;
	}
}
