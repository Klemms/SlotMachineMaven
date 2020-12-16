package fr.klemms.slotmachine.utils;

import org.bukkit.Bukkit;
import org.bukkit.Sound;

public class Util {
	
	public static int getPages(int listSize, int pageSize) {
		return (int)Math.ceil(listSize / pageSize) + 1;
	}
	
	public static boolean isPageValid(int listSize, int page, double pageSize) {
		return page >= 0 && page < ((int)Math.ceil(((double)listSize) / pageSize));
	}
	
	public static String getMCVersion() {
		return Bukkit.getBukkitVersion().split("-")[0];
	}

	public static String formatNumber(double number) {
		if(number >= 1000000D) {
			return String.format("%.0f", number);
		}
		return String.valueOf(number);
	}
	
	public static boolean isValidSound(String sound) {
		if (sound == null)
			return false;
		
		for(Sound sSound : Sound.values()) {
			if (sSound.toString().equals(sound))
				return true;
		}
		
		return false;
	}
}
