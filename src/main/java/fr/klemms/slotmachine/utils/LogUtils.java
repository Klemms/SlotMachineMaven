package fr.klemms.slotmachine.utils;

import fr.klemms.slotmachine.Config;
import fr.klemms.slotmachine.SlotPlugin;

import java.util.logging.Level;

public class LogUtils {

	public static void debug(String message) {
		if (Config.debug) {
			SlotPlugin.pl.getLogger().log(Level.INFO, "[Debug] " + message);
		}
	}
}
