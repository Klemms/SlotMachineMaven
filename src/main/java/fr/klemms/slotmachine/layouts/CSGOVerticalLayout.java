package fr.klemms.slotmachine.layouts;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.Config;
import fr.klemms.slotmachine.MachineItem;
import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.interraction.ItemPreviewInventory;
import fr.klemms.slotmachine.placeholders.Variables;
import fr.klemms.slotmachine.threads.ThreadPullLever;
import fr.klemms.slotmachine.translation.Language;
import fr.klemms.slotmachine.utils.ItemStackUtil;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;

public class CSGOVerticalLayout implements InventoryProvider {
	
	public SlotMachine machine;
	
	public CSGOVerticalLayout(SlotMachine machine) {
		this.machine = machine;
	}

	@Override
	public void init(Player player, InventoryContents contents) {
		List<MachineItem> row_0 = machine.getPlayerRow0(player);
		
		ClickableItem bluePane = ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(machine.getBackgroundItem()), " "));
		contents.fill(bluePane);

		contents.set(0, 2, ClickableItem.empty(row_0.get(0).getItemStack()));
		contents.set(1, 2, ClickableItem.empty(row_0.get(1).getItemStack()));
		contents.set(2, 2, ClickableItem.empty(row_0.get(2).getItemStack()));
		contents.set(3, 2, ClickableItem.empty(row_0.get(3).getItemStack()));
		contents.set(4, 2, ClickableItem.empty(row_0.get(4).getItemStack()));
		
		contents.set(2, 1, ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(machine.getEmphasisItem()), " ")));
		contents.set(2, 3, ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(machine.getEmphasisItem()), " ")));
		
		if (machine.allowContentPreview())
			contents.set(1, 6, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(machine.getItemListItem(), 1), ChatContent.GOLD + Language.translate("machine.preview")), event -> {
				ItemPreviewInventory.showPreview(player, machine, 0);
			}));
		
		ClickableItem lever = ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(machine.getLeverItem(), 1),
				Variables.getFormattedString(machine.getLeverTitle(), player, machine)),
				Variables.getFormattedStrings(machine.getLeverDescription(), player, machine)),
				event -> {
					if (!machine.isPlayerRolling(player)) {
						if (CommonLayout.triggerLever(player, machine)) {
							machine.addUse();
							machine.setPlayerRolling(player, true);
							player.sendMessage(Variables.getFormattedString(Language.translate(Config.goodLuckDefaultString), player, machine));
							player.playSound(player.getLocation(), machine.getLeverSound(), 1.9f, 1.2f);
							Bukkit.getScheduler().runTaskLaterAsynchronously(SlotPlugin.pl, new ThreadPullLever(player, machine, contents, callback -> {
								machine.setPlayerRolling(player, false);
							}), 0);
						}
					}
				});
		if (!machine.playerHasPermission(player))
			lever = CommonLayout.leverNoPermission;
		contents.set(machine.allowContentPreview() ? 3 : 2, 6, lever);
	}

	@Override
	public void update(Player player, InventoryContents contents) { }
	
}
