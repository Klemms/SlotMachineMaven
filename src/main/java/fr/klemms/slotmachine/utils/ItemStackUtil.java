package fr.klemms.slotmachine.utils;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemStackUtil {

	public static ItemStack changeItemStackName(ItemStack itemStack, String name) {
		ItemMeta im = itemStack.getItemMeta();
		im.setDisplayName(name);
		itemStack.setItemMeta(im);
		return itemStack;
	}
	
	public static ItemStack changeItemStackAmount(ItemStack itemStack, int amount) {
		if(itemStack != null)
			itemStack.setAmount(amount);
		return itemStack;
	}
	
	public static ItemStack setItemStackLore(ItemStack itemStack, List<String> lore) {
		ItemMeta im = itemStack.getItemMeta();
		im.setLore(lore);
		itemStack.setItemMeta(im);
		return itemStack;
	}
	
	public static ItemStack addGlow(ItemStack itemStack) {
		ItemMeta im = itemStack.getItemMeta();
		
		im.addEnchant(Enchantment.MENDING, 1, true);
		im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		itemStack.setItemMeta(im);
		return (itemStack);
	}
	
	public static boolean isValidMaterial(String material) {
		return material != null && Material.getMaterial(material) != null;
	}
}
