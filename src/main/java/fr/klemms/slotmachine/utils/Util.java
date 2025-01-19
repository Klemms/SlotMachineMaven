package fr.klemms.slotmachine.utils;

import fr.klemms.slotmachine.utils.sounds.SSound;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.map.MinecraftFont;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

	public static void printStringArray(String[] array) {
		System.out.print("[");
		for (String str : array) {
			System.out.print(str + ", ");
		}
		System.out.println("]");
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

		while (curIndex < splitText.length) {
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

	public static boolean isAtLeastMC118() {
		return !Util.getMCVersion().startsWith("1.8") &&
				!Util.getMCVersion().startsWith("1.9") &&
				!Util.getMCVersion().startsWith("1.10") &&
				!Util.getMCVersion().startsWith("1.11") &&
				!Util.getMCVersion().startsWith("1.12") &&
				!Util.getMCVersion().startsWith("1.13") &&
				!Util.getMCVersion().startsWith("1.14") &&
				!Util.getMCVersion().startsWith("1.15") &&
				!Util.getMCVersion().startsWith("1.16") &&
				!Util.getMCVersion().startsWith("1.17");
	}

	public static String getMCVersion() {
		return Bukkit.getBukkitVersion().split("-")[0];
	}

	public static String formatNumber(double number) {
		if(number >= 1000000D) {
			return String.format("%.0f", number);
		}
		return (number == ((int)number)) ? String.valueOf((int)number) : String.valueOf(number);
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

	public static void playSoundPlayer(Player player, SSound sound, float volume, float pitch) {
		if (sound.isCustomKey()) {
			player.playSound(player.getLocation(), sound.getKey(), 1f, 1f);
		} else {
			player.playSound(player.getLocation(), sound.getKey(), volume, pitch);
		}
	}
}
