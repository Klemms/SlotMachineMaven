package fr.klemms.slotmachine.layouts;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.Config;
import fr.klemms.slotmachine.MachineItem;
import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.fr.minuskube.inv.ClickableItem;
import fr.klemms.slotmachine.fr.minuskube.inv.content.InventoryContents;
import fr.klemms.slotmachine.fr.minuskube.inv.content.InventoryProvider;
import fr.klemms.slotmachine.interraction.ItemPreviewInventory;
import fr.klemms.slotmachine.placeholders.Variables;
import fr.klemms.slotmachine.threads.ThreadPullLever;
import fr.klemms.slotmachine.translation.Language;
import fr.klemms.slotmachine.utils.ItemStackUtil;

public class CSGOLayout implements InventoryProvider {
	
	public SlotMachine machine;
	private int update;
	
	public CSGOLayout(SlotMachine machine) {
		this.machine = machine;
		update = 20;
	}

	@Override
	public void init(Player player, InventoryContents contents) {
		List<MachineItem> row_0 = machine.getPlayerRow0(player);
		
		ClickableItem bluePane = ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(machine.getBackgroundItem()), " "));
		contents.fill(bluePane);

		contents.set(2, 1, ClickableItem.empty(row_0.get(0).getItemStack()));
		contents.set(2, 2, ClickableItem.empty(row_0.get(1).getItemStack()));
		contents.set(2, 3, ClickableItem.empty(row_0.get(2).getItemStack()));
		contents.set(2, 4, ClickableItem.empty(row_0.get(3).getItemStack()));
		contents.set(2, 5, ClickableItem.empty(row_0.get(4).getItemStack()));
		contents.set(2, 6, ClickableItem.empty(row_0.get(5).getItemStack()));
		contents.set(2, 7, ClickableItem.empty(row_0.get(6).getItemStack()));
		
		contents.set(1, 4, ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(machine.getEmphasisItem()), " ")));
		contents.set(3, 4, ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(machine.getEmphasisItem()), " ")));
		
		if (machine.allowContentPreview())
			contents.set(4, 1, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(machine.getItemListItem()), ChatContent.GOLD + Language.translate("machine.preview")), event -> {
				player.setMetadata("slotmachine_soundremovalprevention", new FixedMetadataValue(SlotPlugin.pl, true));
				Bukkit.getScheduler().runTask(SlotPlugin.pl, () -> {
					if (player.hasMetadata("slotmachine_soundremovalprevention"))
						player.removeMetadata("slotmachine_soundremovalprevention", SlotPlugin.pl);
				});
				
				player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
				ItemPreviewInventory.showPreview(player, machine, 0);
			}));

		ClickableItem lever = generateLever(player, contents);
		if (!machine.playerHasPermission(player))
			lever = CommonLayout.leverNoPermission;
		contents.set(4, 7, lever);
	}
	
	public ClickableItem generateLever(Player player, InventoryContents contents) {
		List<String> lore = new ArrayList<String>();
		lore.addAll(Variables.getFormattedStrings(machine.getLeverDescription(), player, machine));
		if (machine.getCooldown() > 0) {
			lore.add("");
			if (machine.getPlayerCooldown(player) > 0) {
				lore.add(ChatContent.DARK_GRAY + "Cooldown : " + ChatContent.GRAY + ChatContent.ITALIC + machine.getPlayerCooldown(player) + "s/" + machine.getCooldown() + "s");
			} else {
				lore.add(ChatContent.DARK_GRAY + Language.translate("basic.cooldown").replace("%cooldown%", ChatContent.GRAY + ChatContent.ITALIC + String.valueOf(machine.getCooldown())));
			}
		}
		ClickableItem lever = ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(machine.getLeverItem()),
				Variables.getFormattedString(machine.getLeverTitle(), player, machine)),
				lore),
				event -> {
					if (machine.canPlay(player)) {
						if (CommonLayout.triggerLever(player, machine)) {
							machine.addUse();
							machine.setPlayerRolling(player, true);
							player.sendMessage(Variables.getFormattedString(Language.translate(Config.goodLuckDefaultString), player, machine));
							player.playSound(player.getLocation(), machine.getLeverSound(), 1.9f, 1.2f);
							Bukkit.getScheduler().runTaskLaterAsynchronously(SlotPlugin.pl, new ThreadPullLever(player, machine, contents, callback -> {
								Bukkit.getScheduler().runTask(SlotPlugin.pl, () -> {
									machine.setPlayerRolling(player, false);
									SlotPlugin.saveToDisk();
								});
							}), 0);
							machine.openMachine(player, false);
						}
					}
				});
		
		return lever;
	}

	@Override
	public void update(Player player, InventoryContents contents) {
		if (update <= 0) {
			if (machine.getCooldown() > 0) {
				contents.set(4, 7, generateLever(player, contents));
			}
			update = 20;
		} else
			update--;
	}
	
}
