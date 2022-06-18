package fr.klemms.slotmachine.tokens;

import fr.klemms.slotmachine.Config;
import fr.klemms.slotmachine.SlotPlugin;
import org.bukkit.inventory.ItemStack;

public class Token {

	/**
	 * Returns default token if it exists, if it doesn't it will generate a default one, save it and return it.
	 * @return Default token ItemStack, should never return null
	 */
	public static ItemStack getDefaultToken() {
		if (Config.tokens.isEmpty() || Config.tokens.get("default") == null) {
			Config.tokens.put("default", new ItemStack(SlotPlugin.DEFAULT_TOKEN));
			SlotPlugin.writeTokens();
		}

		return Config.tokens.get("default");
	}

	public String identifier;
	public ItemStack itemStack;
	
	public Token(String identifier, ItemStack itemStack) {
		this.identifier = identifier;
		this.itemStack = itemStack;
	}
}
