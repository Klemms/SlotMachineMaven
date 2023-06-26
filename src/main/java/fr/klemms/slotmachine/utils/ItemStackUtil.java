package fr.klemms.slotmachine.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemStackUtil {

	public static ItemStack changeItemStackName(ItemStack itemStack, String name) {
		ItemMeta im = itemStack.getItemMeta();
		im.setDisplayName(name);
		itemStack.setItemMeta(im);
		return itemStack;
	}

	public static ItemStack addLoreLines(ItemStack itemStack, String... lines) {
		ItemMeta im = itemStack.getItemMeta();
		List<String> lore = im.getLore() == null ? new ArrayList<String>() : im.getLore();

		for (String str : lines)
			lore.add(str);

		im.setLore(lore);

		itemStack.setItemMeta(im);
		return itemStack;
	}
	
	public static ItemStack changeItemStackAmount(ItemStack itemStack, int amount) {
		if(itemStack != null)
			itemStack.setAmount(amount);
		return itemStack;
	}
	
	public static ItemStack setItemStackLore(ItemStack itemStack, List<String> lore) {
		if (lore != null) {
			ItemMeta im = itemStack.getItemMeta();
			im.setLore(lore);
			itemStack.setItemMeta(im);
		}
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
