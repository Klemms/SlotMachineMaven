package fr.klemms.slotmachine.threads;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.translation.Language;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class ThreadGiveItem extends Thread {

	private ItemStack is;
	private UUID playerUUID;

	public ThreadGiveItem(ItemStack is, UUID playerUUID) {
		this.is = is;
		this.playerUUID = playerUUID;
	}

	@Override
	public void run() {
		Player player;
		if ((player = Bukkit.getPlayer(this.playerUUID)) != null) {
			if (player.getInventory().firstEmpty() >= 0) {
				player.getInventory().addItem(this.is);
				player.updateInventory();
			} else {
				player.sendMessage(ChatContent.RED + SlotPlugin.CHAT_PREFIX + ChatContent.translateColorCodes(Language.translate("slotmachine.giveitem.noroom")));
				Bukkit.getScheduler().scheduleSyncDelayedTask(SlotPlugin.pl, this, 10 * 20);
			}
		}
	}
}
