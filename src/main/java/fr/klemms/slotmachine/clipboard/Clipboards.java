package fr.klemms.slotmachine.clipboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.utils.ItemStackUtil;
import fr.klemms.slotmachine.utils.PlayerHeadsUtil;
import fr.klemms.slotmachine.utils.Util;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;

public class Clipboards {

	private static HashMap<UUID, Clipboard> clipboards;
	
	public static void setupClipboards() {
		clipboards = new HashMap<UUID, Clipboard>();
	}
	
	public static Clipboard getPlayerClipboard(Player player) {
		if (clipboards.containsKey(player.getUniqueId())) {
			return clipboards.get(player.getUniqueId());
		} else {
			Clipboard cb = new Clipboard();
			clipboards.put(player.getUniqueId(), cb);
			return cb;
		}
	}
	
	public static void clipboardUI(Player pl, InventoryContents content, CopyPastable cp, int copyRow, int copyColumn, int pasteRow, int pasteColumn, PasteCallback callback) {
		Clipboard cb = getPlayerClipboard(pl);
		if (cp instanceof Copyable && copyRow >= 0 && copyColumn >= 0 && ((Copyable) cp).copy() != null) {
			Copyable cpp = (Copyable) cp;
			
			List<String> copyLore = new ArrayList<String>();
			copyLore.addAll(Util.addToStartOfLines(ChatContent.AQUA, Util.splitLines(cpp.gives().contentDescription, 175)));
			copyLore.add("");
			copyLore.addAll(Util.addToStartOfLines(ChatContent.GRAY + ChatContent.ITALIC, Util.splitLines("Can only be pasted in interfaces that accept '" + cpp.gives().contentTitle + "'", 175)));
			
			content.set(copyRow, copyColumn, ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.COPY), ChatContent.GOLD + "Copy '" + cpp.gives().contentTitle + "' to Clipboard"), copyLore), event -> {
				cb.cbContent = cpp.gives();
				cb.cbMachine = cpp.copy();
				pl.sendMessage(ChatContent.AQUA + "[Slot Machine] Copied '" + cpp.gives().contentTitle + "' to Clipboard");
				pl.playSound(pl.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1f, 1f);
				cpp.reloadUI(false);
			}));
		}
		if (cp instanceof Pastable) {
			Pastable ppp = (Pastable) cp;
			
			List<String> pasteLore = new ArrayList<String>();
			pasteLore.addAll(Util.addToStartOfLines(ChatContent.AQUA, Util.splitLines(ppp.accepts().contentDescription, 175)));
			if (cb.cbContent != null) {
				pasteLore.add("");
				pasteLore.addAll(Util.addToStartOfLines(ChatContent.AQUA, Util.splitLines("Your clipboard currently contains '" + cb.cbContent.contentTitle + "'", 175)));
			}
			pasteLore.add("");
			pasteLore.addAll(Util.addToStartOfLines(ChatContent.GRAY + ChatContent.ITALIC, Util.splitLines("This interface only accepts '" + ppp.accepts().contentTitle + "'", 175)));
			
			if (cb.cbContent == ppp.accepts()) {
				content.set(pasteRow, pasteColumn, ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.PASTE), ChatContent.GOLD + "Paste '" + ppp.accepts().contentTitle + "' here"), pasteLore), event -> {
					SlotMachine inputMachine = cb.cbMachine;
					SlotMachine outputMachine = ppp.paste(cb.cbMachine);
					
					if (callback != null)
						outputMachine = callback.beforePaste(inputMachine, outputMachine);
					
					ppp.accepts().contentCopier.copyContent(pl, inputMachine, outputMachine);
					pl.sendMessage(ChatContent.AQUA + "[Slot Machine] Successfully pasted to '" + ppp.accepts().contentTitle + "'");
					pl.playSound(pl.getLocation(), Sound.ENTITY_VILLAGER_CELEBRATE, 1f, 1f);
					ppp.reloadUI(true);
					
					if (callback != null)
						callback.afterPaste(inputMachine, outputMachine);
					
					SlotPlugin.saveToDisk();
				}));
			} else {
				content.set(pasteRow, pasteColumn, ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(Material.BARRIER), cb.cbContent == null ? (ChatContent.RED + "Your clipboard is empty !") : (ChatContent.RED + "Can't paste '" + cb.cbContent.contentTitle + "' here")), pasteLore), event -> {
					pl.playSound(pl.getLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 1f);
				}));
			}
		}
	}
}
