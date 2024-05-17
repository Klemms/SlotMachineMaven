package fr.klemms.slotmachine.layouts;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.MachineItem;
import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.fr.minuskube.inv.ClickableItem;
import fr.klemms.slotmachine.fr.minuskube.inv.content.InventoryContents;
import fr.klemms.slotmachine.interraction.ItemPreviewInventory;
import fr.klemms.slotmachine.translation.Language;
import fr.klemms.slotmachine.utils.ItemStackUtil;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.List;

public class SlotMachineLayout extends CommonLayout {

	public SlotMachineLayout(SlotMachine machine) {
		super(machine);
	}

	@Override
	public void init(Player player, InventoryContents contents) {
		List<MachineItem> row_0 = machine.getPlayerRow0(player);
		List<MachineItem> row_1 = machine.getPlayerRow1(player);
		List<MachineItem> row_2 = machine.getPlayerRow2(player);

		ClickableItem bluePane = ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(machine.getBackgroundItem()), " "));
		contents.fill(bluePane);

		contents.set(1, 1, ClickableItem.empty(row_0.get(0).getItemStack()));
		contents.set(1, 3, ClickableItem.empty(row_0.get(1).getItemStack()));
		contents.set(1, 5, ClickableItem.empty(row_0.get(2).getItemStack()));

		contents.set(2, 1, ClickableItem.empty(row_1.get(0).getItemStack()));
		contents.set(2, 3, ClickableItem.empty(row_1.get(1).getItemStack()));
		contents.set(2, 5, ClickableItem.empty(row_1.get(2).getItemStack()));

		contents.set(3, 1, ClickableItem.empty(row_2.get(0).getItemStack()));
		contents.set(3, 3, ClickableItem.empty(row_2.get(1).getItemStack()));
		contents.set(3, 5, ClickableItem.empty(row_2.get(2).getItemStack()));

		contents.set(2, 2, ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(machine.getEmphasisItem()), " ")));
		contents.set(2, 4, ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(machine.getEmphasisItem()), " ")));

		if (machine.allowContentPreview())
			contents.set(1, 7, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(machine.getItemListItem()), ChatContent.GOLD + Language.translate("machine.preview")), event -> {
				player.setMetadata("slotmachine_soundremovalprevention", new FixedMetadataValue(SlotPlugin.pl, true));
				Bukkit.getScheduler().runTask(SlotPlugin.pl, () -> {
					if (player.hasMetadata("slotmachine_soundremovalprevention"))
						player.removeMetadata("slotmachine_soundremovalprevention", SlotPlugin.pl);
				});

				player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
				ItemPreviewInventory.showPreview(player, machine, 0);
			}));

		final int leverX = machine.allowContentPreview() ? 3 : 2;
		final int leverY = 7;

		ClickableItem lever = generateLever(player, contents, leverX, leverY);
		if (!machine.playerHasPermission(player))
			lever = LayoutUtils.leverNoPermission;
		contents.set(leverX, leverY, lever);
	}

	@Override
	public void update(Player player, InventoryContents contents) {
		if (update <= 0) {
			if (machine.getCooldown() > 0) {
				final int leverX = machine.allowContentPreview() ? 3 : 2;
				final int leverY = 7;

				contents.set(leverX, leverY, generateLever(player, contents, leverX, leverY));
			}
			update = 20;
		} else {
			update--;
		}
	}

}
