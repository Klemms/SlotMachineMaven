package fr.klemms.slotmachine.exceptioncollector;

import fr.klemms.slotmachine.Config;
import fr.klemms.slotmachine.SlotPlugin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class ExceptionCollector {

	public static void sendException(JavaPlugin plugin, Exception exception) {
		if(Config.anonymouslyReportExceptionsToDevelopper) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			exception.printStackTrace(pw);
			String reportURL = "";
			try {
				reportURL = "https://klemms.ovh/minecraftdev/exceptionCollector/?stacktrace=" + URLEncoder.encode(sw.toString(), "UTF-8") + 
						"&pl=slotmachine5" +
						"&plv=" + URLEncoder.encode("" + SlotPlugin.VERSION, "UTF-8") +
						"&mcv=" + URLEncoder.encode(Bukkit.getBukkitVersion(), "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			
			URLConnection urlC = null;
			try {
				urlC = new URL(reportURL).openConnection();
				urlC.setConnectTimeout(10000);
			} catch (Exception e) {
				return;
			}
			try(BufferedReader in1 = new BufferedReader(new InputStreamReader(urlC.getInputStream()))) {
			} catch (Exception e) {
				return;
			}
		}
	}
}
