package fr.klemms.slotmachine.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.map.MinecraftFont;

public class Util {
	
	public static List<String> addToStartOfLines(String textToAdd, List<String> lines) {
		for (int i = 0; i < lines.size(); i++) {
			lines.set(i, textToAdd + lines.get(i));
		}
		
		return lines;
	}
	
	public static List<String> addLines(List<String> lines, String... strs) {
		for (String str : strs) {
			lines.add(str);
		}
		
		return lines;
	}
	
	/**
	 * This has flaws
	 * @param text
	 * @param size
	 * @return
	 */
	public static List<String> splitLines(String text, int size) {
		if (MinecraftFont.Font.getWidth(text) <= size)
			return Arrays.asList(text);
		
		List<String> lines = new ArrayList<String>();
		String[] splitText = StringUtils.split(text);
		int curIndex = 0;
		int max = splitText.length;
		
		while (curIndex < splitText.length - 1) {
			if (MinecraftFont.Font.getWidth(makeString(curIndex, max, splitText)) <= size) {
				lines.add(makeString(curIndex, max, splitText));
				curIndex = max;
				max = splitText.length;
			} else {
				max--;
			}
		}
		
		return lines;
	}
	
	/**
	 * This has flaws
	 * @param start
	 * @param max
	 * @param strings
	 * @return
	 */
	private static String makeString(int start, int max, String... strings) {
		String str = "";
		
		for (int i = start; i < max && i < strings.length; i++) {
			str += strings[i] + " ";
		}
		
		return str.trim();
	}
	
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

	public static String formatNumberTwoDigits(double number) {
		return new DecimalFormat("###.##").format(number);
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
	
	public static boolean isValidUUID(String uuid) {
		return uuid.matches("[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}");
	}
}
