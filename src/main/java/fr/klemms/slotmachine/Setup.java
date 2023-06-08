package fr.klemms.slotmachine;

import com.bencodez.votingplugin.VotingPluginHooks;
import fr.klemms.slotmachine.exceptioncollector.ExceptionCollector;
import fr.klemms.slotmachine.translation.Language;
import net.milkbowl.vault.economy.Economy;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.zip.GZIPInputStream;

public class Setup {

	public static HashMap<String, String> officialLanguages;

	public static void setupOTALanguages(JavaPlugin plugin) {
		String language = officialLanguages.get(Config.language);

		plugin.getLogger().log(Level.INFO, "Fetching updated translations from Crowdin for : " + Config.language);
		try {
			URL url = new URL("https://distributions.crowdin.net/a39ae3df9b64b4b2421a317dtpi/content/" + Config.language + "/" + language + ".properties");
			URLConnection urlConnection = url.openConnection();
			urlConnection.setConnectTimeout(5000);
			urlConnection.setReadTimeout(5000);
			GZIPInputStream in = new GZIPInputStream(urlConnection.getInputStream());
			Language.parseLanguageFromStrings(Config.language, IOUtils.readLines(in, Charset.forName("UTF-8")));
			plugin.getLogger().log(Level.INFO, "Success !");
		} catch (IOException e) {
			plugin.getLogger().log(Level.INFO, "Couldn't get OTA language updates. Falling back to built-in language.");
			e.printStackTrace();
			ExceptionCollector.sendException(SlotPlugin.pl, e);
		}
	}

	public static void setupLanguages(JavaPlugin plugin) {
		officialLanguages = new HashMap<String, String>();

		// Language code to file name
		officialLanguages.put("en", "en-US");
		officialLanguages.put("fr", "fr-FR");
		officialLanguages.put("zh-CN", "zh-CN");
		officialLanguages.put("zh-TW", "zh-TW");
		//officialLanguages.put("es-ES", "es-ES"); // Translation quality is too bad

		try {
			Files.createDirectories(plugin.getDataFolder().toPath().resolve("langs"));
		} catch (IOException e1) {
			e1.printStackTrace();
			ExceptionCollector.sendException(SlotPlugin.pl, e1);
		}

		try {
			if (Files.exists(plugin.getDataFolder().toPath().resolve("langs").resolve("ENGLISH.txt"))) {
				FileUtils.delete(plugin.getDataFolder().toPath().resolve("langs").resolve("ENGLISH.txt").toFile());
			}
			if (Files.exists(plugin.getDataFolder().toPath().resolve("langs").resolve("FRENCH.txt"))) {
				FileUtils.delete(plugin.getDataFolder().toPath().resolve("langs").resolve("FRENCH.txt").toFile());
			}
			IOUtils.copy(plugin.getResource("readme.txt"), new FileOutputStream(plugin.getDataFolder().toPath().resolve("langs").resolve("readme.txt").toFile()));
			IOUtils.copy(plugin.getResource("en-US.properties"), new FileOutputStream(plugin.getDataFolder().toPath().resolve("langs").resolve("en-demo.properties").toFile()));
		} catch (Exception e) {
			e.printStackTrace();
			ExceptionCollector.sendException(SlotPlugin.pl, e);
		}

		for (String language : officialLanguages.keySet()) {
			plugin.getLogger().log(Level.INFO, "Adding official language : " + language);
			try {
				Language.parseLanguageFromStrings(language, IOUtils.readLines(plugin.getResource(officialLanguages.get(language) + ".properties"), Charset.forName("UTF-8")));
			} catch (IOException e) {
				e.printStackTrace();
				ExceptionCollector.sendException(SlotPlugin.pl, e);
			}
		}

		for(File file : FileUtils.listFiles(plugin.getDataFolder().toPath().resolve("langs").toFile(), TrueFileFilter.INSTANCE, null)) {
			if (file.getName().equals("readme.txt") || file.getName().equals("en-demo.properties")) {
				continue;
			}

			if (officialLanguages.containsKey(FilenameUtils.removeExtension(file.getName()))) {
				plugin.getLogger().log(Level.INFO, "You can't override official plugin languages, please create a new file with a different name. Ignoring file : " + file.getName());
				continue;
			}

			plugin.getLogger().log(Level.INFO, "Adding custom language : " + FilenameUtils.removeExtension(file.getName()).toUpperCase());
			try {
				Language.parseLanguageFromStrings(FilenameUtils.removeExtension(file.getName()), FileUtils.readLines(file, Charset.forName("UTF-8")));
			} catch (IOException e) {
				e.printStackTrace();
				ExceptionCollector.sendException(SlotPlugin.pl, e);
			}
		}
	}

	public static boolean setupEconomy(JavaPlugin plugin) {
        if (plugin.getServer().getPluginManager().getPlugin("Vault") != null) {
            RegisteredServiceProvider<Economy> rsp = plugin.getServer().getServicesManager().getRegistration(Economy.class);
            if(rsp != null) {
            	SlotPlugin.econ = rsp.getProvider();
            }
            return true;
        }
        plugin.getLogger().log(Level.WARNING, "Vault could not be detected. Transactions using money will not work");
        return false;
    }

	public static boolean setupVotingPlugin(JavaPlugin plugin) {
        if (plugin.getServer().getPluginManager().getPlugin("VotingPlugin") != null) {
        	SlotPlugin.votingPlugin = VotingPluginHooks.getInstance();
            plugin.getLogger().log(Level.INFO, "VotingPlugin support enabled");
            return true;
        }
        plugin.getLogger().log(Level.INFO, "VotingPlugin support disabled");
        return false;
    }
}
