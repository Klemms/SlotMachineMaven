package fr.klemms.slotmachine.interraction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.utils.ItemStackUtil;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import fr.minuskube.inv.content.Pagination;
import fr.minuskube.inv.content.SlotIterator.Type;

public class MachineSoundlistInventory {

	public static void openSoundlist(Player player, SlotMachine machine, int page, SoundlistCallback callback, float volume, float pitch) {
		SmartInventory inv = SmartInventory.builder()
				.manager(SlotPlugin.invManager)
				.title("Sounds")
				.size(6, 9)
				.closeable(true)
				.provider(new InventoryProvider() {

					@Override
					public void init(Player player, InventoryContents contents) {
						Pagination pagination = contents.pagination();

						contents.fill(ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1), " ")));
						
						pagination.setItemsPerPage(4 * 9);
						List<ClickableItem> items = new ArrayList<ClickableItem>();
						
						for(final Sound sound : Sound.values())  {
							ItemStack soundStack = new ItemStack(SlotPlugin.soundMaterialMap.containsKey(sound) ? SlotPlugin.soundMaterialMap.get(sound) : Material.NOTE_BLOCK, 1);
							soundStack = ItemStackUtil.changeItemStackName(soundStack, sound.toString());
							soundStack = ItemStackUtil.setItemStackLore(soundStack, Arrays.asList(
									ChatContent.AQUA + ChatContent.ITALIC + "Left Click to pick this sound", 
									ChatContent.AQUA + ChatContent.ITALIC + "Right Click to listen to this sound"));
							
							items.add(ClickableItem.of(soundStack, event -> {
								if (event.isLeftClick()) {
									callback.callback(sound);
									MachineSoundCustomization.customizeSounds(player, machine);
								} else if (event.isRightClick()) {
									player.playSound(player.getLocation(), sound, volume, pitch);
								}
							}));
						}
						
						pagination.setItems(items.toArray(new ClickableItem[items.size()]));
						pagination.addToIterator(contents.newIterator(Type.HORIZONTAL, 1, 0));
						
						contents.set(0, 2, ClickableItem.empty(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(Material.PAINTING, 1), ChatContent.GOLD + "Informations"), Arrays.asList(
								ChatContent.AQUA + "Left click on a Note Block to",
								ChatContent.AQUA + "pick this sound",
								"",
								ChatContent.AQUA + "Right click on a Note Block to",
								ChatContent.AQUA + "listen to the sound",
								"",
								ChatContent.AQUA + "To cancel click on back"
								))));

						contents.set(5, 1, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(Material.COMPASS), "<- Back"), event -> {
							MachineSoundCustomization.customizeSounds(player, machine);
						}));
						
						
						if (!pagination.isFirst())
							contents.set(5, 3, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(Material.ARROW), "< Previous Page"), event -> {
								openSoundlist(player, machine, page - 1, callback, volume, pitch);
							}));

						if (!pagination.isLast())
							contents.set(5, 5, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(Material.ARROW), "Next Page >"), event -> {
								openSoundlist(player, machine, page + 1, callback, volume, pitch);
							}));
						
						contents.set(5, 4, ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(Material.PAPER), "Page " + (pagination.getPage() + 1) + "/" + (pagination.last().getPage() + 1))));
					}

					@Override
					public void update(Player player, InventoryContents contents) {
						
					}
					
				})
				.build();
		
		inv.open(player, page);
	}
}
