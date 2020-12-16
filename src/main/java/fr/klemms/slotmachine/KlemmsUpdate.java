package fr.klemms.slotmachine;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;

import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;

public class KlemmsUpdate implements Listener {
	
	private JavaPlugin plugin;
	private String project;
	private int version;
	private String branch;
	private Path folderPath;
	private final Path configFile;
	
	private boolean check_for_updates = true;
	private boolean log_if_update_found = true;
	private boolean notice_admins = true;
	private int delay_between_checks = 30;
	
	private boolean needsUpdate = false;
	private JSONObject latestVersion = null;

	public KlemmsUpdate(JavaPlugin _plugin, String _project, int _version, String _branch) throws IOException {
		this.plugin = _plugin;
		this.project = _project;
		this.version = _version;
		this.branch = _branch;
		
		_plugin.getServer().getPluginManager().registerEvents(this, _plugin);
		
		this.folderPath = this.plugin.getDataFolder().getParentFile().toPath().resolve("KlemmsPlugins");
		if(!Files.exists(this.folderPath))
			Files.createDirectories(this.folderPath);
		this.configFile = this.folderPath.resolve("update_config.yml");
		
		this.makeConfig();
		
		plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, new Runnable() {

			@Override
			public void run() {
				if(check_for_updates) {
					
					try {
						URL url = new URL("https://klemms.ovh:8443/checkupdate/1.0/" +
								"?pluginName=" + project +
								"&branch=" + branch +
								"&formattedVersion=" + plugin.getDescription().getVersion() +
								"&version=" + version +
								"&minecraftVersion=" + getMCVersion());
						
						String output = IOUtils.toString(url, Charset.forName("utf-8"));
						JSONParser parser = new JSONParser();
						JSONObject updateObject = (JSONObject)parser.parse(output);
						
						if((boolean)updateObject.get("needsUpdate")) {
							needsUpdate = (boolean)updateObject.get("needsUpdate");
							JSONObject version = (JSONObject) updateObject.get("relatedVersion");
							latestVersion = version;
							if(log_if_update_found) {
								plugin.getLogger().log(Level.INFO, "Update " + version.get("formatted_version").toString() + " is available for your version of Spigot at " + version.get("url").toString());
							}
							if(notice_admins) {
								for(Player player : Bukkit.getOnlinePlayers()) {
									if(player.isOp()) {
										player.spigot().sendMessage(new ComponentBuilder("[" + version.get("formatted_name").toString() + "] Update " + version.get("formatted_version").toString() + " is available for your version of Spigot. Click Here")
											.event(new ClickEvent(Action.OPEN_URL, version.get("url").toString()))
											.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(version.get("url").toString()).create()))
											.color(ChatColor.AQUA).create());
									}
								}
							}
						}
						
					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
			
		}, 1 * 20, this.delay_between_checks * 20 * 60);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		if (event.getPlayer().isOp() && notice_admins && needsUpdate && latestVersion != null) {
			event.getPlayer().spigot().sendMessage(new ComponentBuilder("[" + latestVersion.get("formatted_name").toString() + "] Update " + latestVersion.get("formatted_version").toString() + " is available for your version of Spigot. Click Here")
					.event(new ClickEvent(Action.OPEN_URL, latestVersion.get("url").toString()))
					.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(latestVersion.get("url").toString()).create()))
					.color(ChatColor.AQUA).create());
		}
	}
	
	private void makeConfig() throws IOException {
		YamlConfiguration updateFile = YamlConfiguration.loadConfiguration(this.configFile.toFile());
		
		if(Files.exists(this.configFile)) {
			this.check_for_updates = updateFile.getBoolean("check_for_updates", check_for_updates);
			this.log_if_update_found = updateFile.getBoolean("log_if_update_found", log_if_update_found);
			this.notice_admins = updateFile.getBoolean("notice_admins", notice_admins);
			this.delay_between_checks = updateFile.getInt("delay_between_checks_in_minutes", delay_between_checks);
		} else {
			updateFile.set("check_for_updates", check_for_updates);
			updateFile.set("log_if_update_found", log_if_update_found);
			updateFile.set("notice_admins", notice_admins);
			updateFile.set("delay_between_checks_in_minutes", delay_between_checks);
		}
		
		updateFile.save(this.configFile.toFile());
	}
	
	public static String getMCVersion() {
		return Bukkit.getBukkitVersion().split("-")[0];
	}
} 