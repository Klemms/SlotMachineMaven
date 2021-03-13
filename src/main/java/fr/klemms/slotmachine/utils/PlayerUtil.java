package fr.klemms.slotmachine.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.klemms.slotmachine.SlotPlugin;

public class PlayerUtil {

	public static boolean hasAnyMetadata(Player player) {
		if (player.hasMetadata("slotmachine_setleverdescription") ||
				player.hasMetadata("slotmachine_setlevertitle") ||
				player.hasMetadata("slotmachine_setlossmessage") ||
				player.hasMetadata("slotmachine_setwinmessage") ||
				player.hasMetadata("slotmachine_changeduration") ||
				player.hasMetadata("slotmachine_changechance") ||
				player.hasMetadata("slotmachine_changename") ||
				player.hasMetadata("slotmachine_changepermission") ||
				player.hasMetadata("slotmachine_setcooldown") ||
				player.hasMetadata("slotmachine_changeprice")) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void resetPlayerData(Player player) {
		player.removeMetadata("slotmachine_setleverdescription", SlotPlugin.pl);
		player.removeMetadata("slotmachine_setlevertitle", SlotPlugin.pl);
		player.removeMetadata("slotmachine_setlossmessage", SlotPlugin.pl);
		player.removeMetadata("slotmachine_setwinmessage", SlotPlugin.pl);
		player.removeMetadata("slotmachine_changeduration", SlotPlugin.pl);
		player.removeMetadata("slotmachine_changechance", SlotPlugin.pl);
		player.removeMetadata("slotmachine_changename", SlotPlugin.pl);
		player.removeMetadata("slotmachine_changepermission", SlotPlugin.pl);
		player.removeMetadata("slotmachine_setcooldown", SlotPlugin.pl);
		player.removeMetadata("slotmachine_changeprice", SlotPlugin.pl);
	}
	
	public static int countItems(Player player, ItemStack item) {
		int count = 0;
		
		for(ItemStack is : player.getInventory().getContents()) {
			if(is != null && is.isSimilar(item))
				count += is.getAmount();
		}
		
		return count;
	}
}
