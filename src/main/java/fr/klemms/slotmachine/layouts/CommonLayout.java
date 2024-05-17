package fr.klemms.slotmachine.layouts;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.Config;
import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.fr.minuskube.inv.ClickableItem;
import fr.klemms.slotmachine.fr.minuskube.inv.content.InventoryContents;
import fr.klemms.slotmachine.fr.minuskube.inv.content.InventoryProvider;
import fr.klemms.slotmachine.placeholders.Variables;
import fr.klemms.slotmachine.threads.ThreadPullLever;
import fr.klemms.slotmachine.translation.Language;
import fr.klemms.slotmachine.utils.ItemStackUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class CommonLayout implements InventoryProvider {

	protected SlotMachine machine;
	protected int update;

	public CommonLayout(SlotMachine machine) {
		this.machine = machine;
		this.update = 20;
	}

	public ClickableItem generateLever(Player player, InventoryContents contents, int leverX, int leverY) {
		List<String> lore = new ArrayList<String>();
		lore.addAll(Variables.getFormattedStrings(machine.getLeverDescription(), player, machine));
		if (machine.getCooldown() > 0) {
			lore.add("");
			if (machine.isPlayerInCooldown(player)) {
				lore.add(ChatContent.DARK_GRAY + "Cooldown : " + ChatContent.GRAY + ChatContent.ITALIC + machine.getPlayerCooldown(player) + "s/" + machine.getCooldown() + "s");
			} else {
				lore.add(ChatContent.DARK_GRAY + Language.translate("basic.cooldown").replace("%cooldown%", ChatContent.GRAY + ChatContent.ITALIC + String.valueOf(machine.getCooldown())));
			}
		} else {
			machine.updateCooldown(player);
		}

		ItemStack leverItem = new ItemStack(machine.isPlayerRolling(player) ? machine.getLeverItemActivated() : machine.getLeverItem());
		if (machine.getCooldown() > 0 && machine.isPlayerInCooldown(player)) {
			leverItem = ItemStackUtil.changeItemStackAmount(leverItem, machine.getPlayerCooldown(player) + 1);
		}

		ClickableItem lever = ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(leverItem,
								Variables.getFormattedString(machine.getLeverTitle(), player, machine)),
						lore),
				event -> {
					if (machine.canPlay(player)) {
						if (LayoutUtils.triggerLever(player, machine)) {
							machine.addUse();
							machine.setPlayerRolling(player, true);
							if (!Config.goodLuckDefaultString.isEmpty())
								player.sendMessage(Variables.getFormattedString(Language.translate(Config.goodLuckDefaultString), player, machine));

							player.playSound(player.getLocation(), machine.getLeverSound().getKey(), 1.9f, 1.2f);
							Bukkit.getScheduler().runTaskLaterAsynchronously(SlotPlugin.pl, new ThreadPullLever(player, machine, contents, conts -> {
								Bukkit.getScheduler().runTask(SlotPlugin.pl, () -> {
									machine.setPlayerRolling(player, false);
									machine.save();
									contents.set(leverX, leverY, generateLever(player, conts, leverX, leverY));
								});
							}), 0);
							machine.openMachine(player, false);
						}
					}
				});

		return lever;
	}
}
