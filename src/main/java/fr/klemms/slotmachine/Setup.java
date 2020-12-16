package fr.klemms.slotmachine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.logging.Level;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import fr.klemms.slotmachine.exceptioncollector.ExceptionCollector;
import fr.klemms.slotmachine.translation.Language;
import net.milkbowl.vault.economy.Economy;

public class Setup {

	public static void setupLanguages(JavaPlugin plugin) {
		try {
			Files.createDirectories(plugin.getDataFolder().toPath().resolve("langs"));
		} catch (IOException e1) {
			e1.printStackTrace();
			ExceptionCollector.sendException(SlotPlugin.pl, e1);
		}
		
		try {
			if(Files.notExists(plugin.getDataFolder().toPath().resolve("langs").resolve("ENGLISH.txt"))) {
				IOUtils.copy(plugin.getResource("ENGLISH.txt"), new FileOutputStream(plugin.getDataFolder().toPath().resolve("langs").resolve("ENGLISH.txt").toFile()));
			}
			if(Files.notExists(plugin.getDataFolder().toPath().resolve("langs").resolve("FRENCH.txt"))) {
				IOUtils.copy(plugin.getResource("FRENCH.txt"), new FileOutputStream(plugin.getDataFolder().toPath().resolve("langs").resolve("FRENCH.txt").toFile()));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			ExceptionCollector.sendException(SlotPlugin.pl, e);
		} catch (IOException e) {
			e.printStackTrace();
			ExceptionCollector.sendException(SlotPlugin.pl, e);
		}
		
		for(File file : FileUtils.listFiles(plugin.getDataFolder().toPath().resolve("langs").toFile(), TrueFileFilter.INSTANCE, null)) {
			plugin.getLogger().log(Level.INFO, "Adding language '" + FilenameUtils.removeExtension(file.getName()).toUpperCase() + "'");
			try {
				Language.parseLanguageFromStrings(FilenameUtils.removeExtension(file.getName()), FileUtils.readLines(file, Charset.forName("UTF-8")));
			} catch (IOException e) {
				e.printStackTrace();
				ExceptionCollector.sendException(SlotPlugin.pl, e);
			}
		}
		
		boolean redo = false;
		try {
			if(Integer.valueOf(Language.translateInLanguage("ENGLISH", "version")) < Integer.valueOf(Language.translateInLanguage(Language.getParseLanguageFromStrings(IOUtils.readLines(plugin.getResource("ENGLISH.txt"), Charset.forName("UTF-8"))), "version"))) {
				IOUtils.copy(plugin.getResource("ENGLISH.txt"), new FileOutputStream(plugin.getDataFolder().toPath().resolve("langs").resolve("ENGLISH.txt").toFile()));
				plugin.getLogger().log(Level.INFO, "A newer version of ENGLISH.txt is available, overwriting old file...");
				redo = true;
			}
			if(Integer.valueOf(Language.translateInLanguage("FRENCH", "version")) < Integer.valueOf(Language.translateInLanguage(Language.getParseLanguageFromStrings(IOUtils.readLines(plugin.getResource("FRENCH.txt"), Charset.forName("UTF-8"))), "version"))) {
				IOUtils.copy(plugin.getResource("FRENCH.txt"), new FileOutputStream(plugin.getDataFolder().toPath().resolve("langs").resolve("FRENCH.txt").toFile()));
				plugin.getLogger().log(Level.INFO, "A newer version of FRENCH.txt is available, overwriting old file...");
				redo = true;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			ExceptionCollector.sendException(SlotPlugin.pl, e);
		} catch (IOException e) {
			e.printStackTrace();
			ExceptionCollector.sendException(SlotPlugin.pl, e);
		}
		
		if(redo) {
			plugin.getLogger().log(Level.INFO, "Reloading languages...");
			Setup.setupLanguages(plugin);
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
}
