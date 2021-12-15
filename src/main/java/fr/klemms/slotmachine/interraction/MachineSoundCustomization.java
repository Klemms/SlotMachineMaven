package fr.klemms.slotmachine.interraction;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotMachineBlock;
import fr.klemms.slotmachine.SlotMachineEntity;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.fr.minuskube.inv.ClickableItem;
import fr.klemms.slotmachine.fr.minuskube.inv.SmartInventory;
import fr.klemms.slotmachine.fr.minuskube.inv.content.InventoryContents;
import fr.klemms.slotmachine.fr.minuskube.inv.content.InventoryProvider;
import fr.klemms.slotmachine.utils.ItemStackUtil;
import fr.klemms.slotmachine.utils.PlayerHeadsUtil;

public class MachineSoundCustomization {

	public static void customizeSounds(Player player, SlotMachine machine) {
		SmartInventory inv = SmartInventory.builder()
				.manager(SlotPlugin.invManager)
				.title("Sound customization")
				.size(6, 9)
				.closeable(true)
				.provider(new InventoryProvider() {

					@Override
					public void init(Player player, InventoryContents contents) {
						contents.fill(ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1), " ")));

						contents.fillRow(1, ClickableItem.empty(null));
						contents.fillRow(2, ClickableItem.empty(null));
						contents.fillRow(3, ClickableItem.empty(null));
						contents.fillRow(4, ClickableItem.empty(null));
						
						contents.set(2, 1, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.HEADSET), ChatContent.GOLD + "Machine Interraction Sound"), event -> {
							player.playSound(player.getLocation(), machine.getMachineOpeningSound(), 1.9f, 1.2f);
						}));
						contents.set(3, 1, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(Material.NOTE_BLOCK, 1), machine.getMachineOpeningSound().toString()), event -> {
							if (event.isLeftClick()) {
								player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
								MachineSoundlistInventory.openSoundlist(player, machine, 0, (sound) -> {
									machine.setMachineOpeningSound(sound);
									player.sendMessage(ChatContent.GREEN + "[Slot Machine] Successfully changed sound to '" + sound.toString() + "'");
									player.playSound(player.getLocation(), machine.getMachineOpeningSound(), 1.9f, 1.2f);
									SlotPlugin.saveToDisk();
								}, 1.9f, 1.2f);
							} else if (event.isRightClick()) {
								ConfirmInventory.confirmWindow(player, "Reset this sound to default ?", "No, cancel", "Yes, reset", (result) -> {
									if (result) {
										machine.resetSoundMachineOpening();
										player.sendMessage(ChatContent.GREEN + "[Slot Machine] Successfully reset sound");
										SlotPlugin.saveToDisk();
									}
									customizeSounds(player, machine);
								}, false);
							}
						}));

						contents.set(2, 2, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.HEADSET), ChatContent.GOLD + "Lever Sound"), event -> {
							player.playSound(player.getLocation(), machine.getLeverSound(), 1.9f, 1.2f);
						}));
						contents.set(3, 2, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(Material.NOTE_BLOCK, 2), machine.getLeverSound().toString()), event -> {
							if (event.isLeftClick()) {
								player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
								MachineSoundlistInventory.openSoundlist(player, machine, 0, (sound) -> {
									machine.setLeverSound(sound);
									player.sendMessage(ChatContent.GREEN + "[Slot Machine] Successfully changed sound to '" + sound.toString() + "'");
									player.playSound(player.getLocation(), machine.getLeverSound(), 1.9f, 1.2f);
									SlotPlugin.saveToDisk();
								}, 1.9f, 1.2f);
							} else if (event.isRightClick()) {
								ConfirmInventory.confirmWindow(player, "Reset this sound to default ?", "No, cancel", "Yes, reset", (result) -> {
									if (result) {
										machine.resetSoundLever();
										player.sendMessage(ChatContent.GREEN + "[Slot Machine] Successfully reset sound");
										SlotPlugin.saveToDisk();
									}
									customizeSounds(player, machine);
								}, false);
							}
						}));

						contents.set(2, 3, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.HEADSET), ChatContent.GOLD + "Win Sound"), event -> {
							player.playSound(player.getLocation(), machine.getWinSound(), 1.9f, 0.9f);
						}));
						contents.set(3, 3, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(Material.NOTE_BLOCK, 3), machine.getWinSound().toString()), event -> {
							if (event.isLeftClick()) {
								player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
								MachineSoundlistInventory.openSoundlist(player, machine, 0, (sound) -> {
									machine.setWinSound(sound);
									player.sendMessage(ChatContent.GREEN + "[Slot Machine] Successfully changed sound to '" + sound.toString() + "'");
									player.playSound(player.getLocation(), machine.getWinSound(), 1.9f, 0.9f);
									SlotPlugin.saveToDisk();
								}, 1.9f, 0.9f);
							} else if (event.isRightClick()) {
								ConfirmInventory.confirmWindow(player, "Reset this sound to default ?", "No, cancel", "Yes, reset", (result) -> {
									if (result) {
										machine.resetSoundWin();
										player.sendMessage(ChatContent.GREEN + "[Slot Machine] Successfully reset sound");
										SlotPlugin.saveToDisk();
									}
									customizeSounds(player, machine);
								}, false);
							}
						}));

						contents.set(2, 5, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.HEADSET), ChatContent.GOLD + "Loss Sound"), event -> {
							player.playSound(player.getLocation(), machine.getLossSound(), 0.3f, 0.7f);
						}));
						contents.set(3, 5, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(Material.NOTE_BLOCK, 4), machine.getLossSound().toString()), event -> {
							if (event.isLeftClick()) {
								player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
								MachineSoundlistInventory.openSoundlist(player, machine, 0, (sound) -> {
									machine.setLossSound(sound);
									player.sendMessage(ChatContent.GREEN + "[Slot Machine] Successfully changed sound to '" + sound.toString() + "'");
									player.playSound(player.getLocation(), machine.getLossSound(), 0.3f, 0.7f);
									SlotPlugin.saveToDisk();
								}, 0.3f, 0.7f);
							} else if (event.isRightClick()) {
								ConfirmInventory.confirmWindow(player, "Reset this sound to default ?", "No, cancel", "Yes, reset", (result) -> {
									if (result) {
										machine.resetSoundLoss();
										player.sendMessage(ChatContent.GREEN + "[Slot Machine] Successfully reset sound");
										SlotPlugin.saveToDisk();
									}
									customizeSounds(player, machine);
								}, false);
							}
						}));
						
						contents.set(2, 6, ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.HEADSET), ChatContent.GOLD + "Slot Machine Layout Sound"), Arrays.asList(
								ChatContent.AQUA + "The sound items make when spinning",
								ChatContent.AQUA + "in a Slot Machine with the default",
								ChatContent.AQUA + "'Slot Machine' layout"
								)), event -> {
									player.playSound(player.getLocation(), machine.getSlotmachineSpinSound(), 0.4f, 0.9f);
								}));
						contents.set(3, 6, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(Material.NOTE_BLOCK, 5), machine.getSlotmachineSpinSound().toString()), event -> {
							if (event.isLeftClick()) {
								player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
								MachineSoundlistInventory.openSoundlist(player, machine, 0, (sound) -> {
									machine.setSlotmachineSpinSound(sound);
									player.sendMessage(ChatContent.GREEN + "[Slot Machine] Successfully changed sound to '" + sound.toString() + "'");
									player.playSound(player.getLocation(), machine.getSlotmachineSpinSound(), 0.4f, 0.9f);
									SlotPlugin.saveToDisk();
								}, 0.4f, 0.9f);
							} else if (event.isRightClick()) {
								ConfirmInventory.confirmWindow(player, "Reset this sound to default ?", "No, cancel", "Yes, reset", (result) -> {
									if (result) {
										machine.resetSoundSlotMachineSpin();
										player.sendMessage(ChatContent.GREEN + "[Slot Machine] Successfully reset sound");
										SlotPlugin.saveToDisk();
									}
									customizeSounds(player, machine);
								}, false);
							}
						}));

						contents.set(2, 7, ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.HEADSET), ChatContent.GOLD + "CSGO Layout Sound"), Arrays.asList(
								ChatContent.AQUA + "The sound items make when spinning",
								ChatContent.AQUA + "in a Slot Machine with a CSGO-like",
								ChatContent.AQUA + "layout"
								)), event -> {
									player.playSound(player.getLocation(), machine.getCsgoSpinSound(), 0.7f, 0.9f);
								}));
						contents.set(3, 7, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(Material.NOTE_BLOCK, 6), machine.getCsgoSpinSound().toString()), event -> {
							if (event.isLeftClick()) {
								player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
								MachineSoundlistInventory.openSoundlist(player, machine, 0, (sound) -> {
									machine.setCsgoSpinSound(sound);
									player.sendMessage(ChatContent.GREEN + "[Slot Machine] Successfully changed sound to '" + sound.toString() + "'");
									player.playSound(player.getLocation(), machine.getCsgoSpinSound(), 0.7f, 0.9f);
									SlotPlugin.saveToDisk();
								}, 0.7f, 0.9f);
							} else if (event.isRightClick()) {
								ConfirmInventory.confirmWindow(player, "Reset this sound to default ?", "No, cancel", "Yes, reset", (result) -> {
									if (result) {
										machine.resetSoundCSGOSpin();
										player.sendMessage(ChatContent.GREEN + "[Slot Machine] Successfully reset sound");
										SlotPlugin.saveToDisk();
									}
									customizeSounds(player, machine);
								}, false);
							}
						}));
						
						
						contents.set(0, 2, ClickableItem.empty(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.INFOS), ChatContent.GOLD + "Informations"), Arrays.asList(
								ChatContent.AQUA + "Click on any sound icon to hear",
								ChatContent.AQUA + "a preview of the sound (Pitch and",
								ChatContent.AQUA + "volume may not be accurate)",
								ChatContent.AQUA + "",
								ChatContent.AQUA + "Left Click on a noteblock to bring up",
								ChatContent.AQUA + "a list of all available sounds in",
								ChatContent.AQUA + "the game",
								ChatContent.AQUA + "",
								ChatContent.AQUA + "Right Click on a noteblock to reset",
								ChatContent.AQUA + "this sound to its default value",
								ChatContent.AQUA + "",
								ChatContent.AQUA + "Follow the information panel on that",
								ChatContent.AQUA + "menu to listen to the sounds",
								ChatContent.AQUA + "",
								ChatContent.GRAY + "Note : Sounds pitch and volume are",
								ChatContent.GRAY + "not customizable"
								))));
						
						contents.set(0, 6, ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(Material.BARRIER), ChatContent.RED + "Reset to default"), Arrays.asList(
								ChatContent.GRAY + "Resets all sound customization",
								ChatContent.GRAY + "to default values"
							)), event -> {
								ConfirmInventory.confirmWindow(player, "Reset all sounds to default ?", "No, cancel", "Yes, reset", callback -> {
									if (callback) {
										player.playSound(player.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1F, 1F);
										machine.resetSoundCustomization();
										player.sendMessage(ChatContent.GREEN + "[Slot Machine] Successfully reset all sounds to default");
										SlotPlugin.saveToDisk();
									}
									customizeSounds(player, machine);
								}, false);
								
						}));

						contents.set(5, 1, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.BACK), "<- Back"), event -> {
							player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
							if (machine instanceof SlotMachineEntity)
								MachineInterractionInventory.manageMachine(player, machine, ((SlotMachineEntity) machine).getEntity(), null, 0);
							else if (machine instanceof SlotMachineBlock)
								MachineInterractionInventory.manageMachine(player, machine, null, ((SlotMachineBlock) machine).getBlock(), 0);
						}));
						
					}

					@Override
					public void update(Player player, InventoryContents contents) {
						
					}
					
				})
				.build();
		
		inv.open(player);
	}
}
