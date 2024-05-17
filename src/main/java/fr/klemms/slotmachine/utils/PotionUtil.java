package fr.klemms.slotmachine.utils;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PotionUtil {

	public static PotionEffect getPotionEffect(LivingEntity entity, PotionEffectType potionType) {
		for(PotionEffect potionEffect : entity.getActivePotionEffects()) {
			if (potionEffect.getType().equals(potionType))
				return potionEffect;
		}
		return null;
	}

	public static ItemStack makePotion(PotionEffectType effect, int durationInTicks, int level, Color color) {
		ItemStack potion = new ItemStack(Material.POTION, 1);
		PotionMeta pm = (PotionMeta) potion.getItemMeta();

		pm.addCustomEffect(new PotionEffect(effect, durationInTicks, level), true);
		pm.setColor(color);

		potion.setItemMeta(pm);
		return potion;
	}
}
