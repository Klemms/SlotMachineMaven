package fr.klemms.slotmachine.events;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.Config;
import fr.klemms.slotmachine.MachineItem;
import fr.klemms.slotmachine.MachineMethods;
import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotMachineBlock;
import fr.klemms.slotmachine.SlotMachineEntity;
import fr.klemms.slotmachine.SlotMachineType;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.placeholders.Variables;
import fr.klemms.slotmachine.threads.ThreadChangeEntityName;
import fr.klemms.slotmachine.translation.Language;
import fr.klemms.slotmachine.utils.PlayerUtil;
import fr.klemms.slotmachine.utils.Util;
import net.citizensnpcs.api.CitizensAPI;

public class PluginListener implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		if (event.getPlayer().isOp() && SlotPlugin.supportEnding) {
			event.getPlayer().sendMessage(ChatContent.AQUA + "[Slot Machine] " + ChatContent.PINK + SlotPlugin.supportMessage);
		}
	}
	
	@EventHandler
	public void onArmorStandClick(PlayerInteractAtEntityEvent event) {
		if (event.getHand().equals(EquipmentSlot.HAND)) {
			if(event.getRightClicked().getType() == EntityType.ARMOR_STAND) {
				onPlayerInteractWithEntity(event);
			}
		}
	}

	@EventHandler
	public void onPlayerInteractWithEntity(PlayerInteractEntityEvent event) {
		if (event.getHand().equals(EquipmentSlot.HAND)) {
			boolean openSlotMachine = true;
			if(event.getPlayer().getInventory().getItemInMainHand() != null && event.getPlayer().getInventory().getItemInMainHand().getType() == Material.BLAZE_ROD) {
				if(event.getPlayer().hasPermission("slotmachine.machineedit") || event.getPlayer().hasPermission("slotmachine.shopedit") || event.getPlayer().isOp()) {
					event.setCancelled(true);
					openSlotMachine = false;
					MachineMethods.magicWand(event.getPlayer(), event.getRightClicked(), null);
				} else if(Config.debug) {
					event.getPlayer().sendMessage(ChatContent.RED + "[Slot Machine] You don't have the required permissions to edit this Slot Machine");
				}
			}
			UUID uuid = event.getRightClicked().getUniqueId();
			if (SlotPlugin.isCitizensEnabled && CitizensAPI.getNPCRegistry().isNPC(event.getRightClicked()))
				uuid = CitizensAPI.getNPCRegistry().getNPC(event.getRightClicked()).getUniqueId();
			
			SlotMachineEntity machine = SlotMachineEntity.getSlotMachineByEntityUUID(uuid);
			
			if(machine != null && openSlotMachine) {
				event.setCancelled(true);
				MachineMethods.openmachine(event.getPlayer(), machine);
			}
		}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		SlotMachineBlock smb = SlotMachineBlock.getSlotMachineByBlock(event.getBlock());
		if(smb != null && smb.isLocked()) {
			event.setCancelled(true);
			if(event.getPlayer().hasPermission("slotmachine.machineedit") || event.getPlayer().hasPermission("slotmachine.shopedit") || event.getPlayer().isOp()) {
				event.getPlayer().sendMessage(ChatContent.RED + smb.getChatName() + Language.translate("slotmachine.breakblock.cant"));
			}
		}
	}

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		if(PlayerUtil.hasAnyMetadata(event.getPlayer()) && StringUtils.deleteWhitespace(event.getMessage().toLowerCase()).equalsIgnoreCase("cancel")) {
			event.setCancelled(true);
			PlayerUtil.resetPlayerData(event.getPlayer());
			event.getPlayer().sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + "Cancelled.");
			return;
		}
		if(event.getPlayer().hasMetadata("slotmachine_setlevertitle")) {
			event.setCancelled(true);
			if(SlotMachine.getSlotMachineByUUID(UUID.fromString(event.getPlayer().getMetadata("slotmachine_setlevertitle").get(0).asString())) != null) {
				SlotMachine slotMachine = SlotMachine.getSlotMachineByUUID(UUID.fromString(event.getPlayer().getMetadata("slotmachine_setlevertitle").get(0).asString()));
				slotMachine.setLeverTitle(event.getMessage());
				slotMachine.setLeverCustom(true);
				event.getPlayer().sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("slotmachine.informations.levertitle") + " : " + ChatContent.RESET + slotMachine.getLeverTitle());
			}
			SlotPlugin.saveToDisk();
		}
		if(event.getPlayer().hasMetadata("slotmachine_setleverdescription")) {
			event.setCancelled(true);
			if(SlotMachine.getSlotMachineByUUID(UUID.fromString(event.getPlayer().getMetadata("slotmachine_setleverdescription").get(0).asString())) != null) {
				SlotMachine slotMachine = SlotMachine.getSlotMachineByUUID(UUID.fromString(event.getPlayer().getMetadata("slotmachine_setleverdescription").get(0).asString()));
				slotMachine.setLeverDescription(event.getMessage());
				slotMachine.setLeverCustom(true);
				event.getPlayer().sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("slotmachine.informations.leverdescription") + " : " + ChatContent.RESET + slotMachine.getLeverDescription());
			}
			SlotPlugin.saveToDisk();
		}
		if(event.getPlayer().hasMetadata("slotmachine_setlossmessage")) {
			event.setCancelled(true);
			SlotMachine slotMachine = SlotMachine.getSlotMachineByUUID(UUID.fromString(event.getPlayer().getMetadata("slotmachine_setlossmessage").get(0).asString()));
			if(slotMachine != null) {
				if (event.getMessage().equals("reset")) {
					slotMachine.setHasLossMessage(true);
					slotMachine.setLossMessage("");
				} else if (event.getMessage().equals("null")) {
					slotMachine.setHasLossMessage(false);
				} else {
					slotMachine.setHasLossMessage(true);
					slotMachine.setLossMessage(event.getMessage());
				}
				event.getPlayer().sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("slotmachine.informations.lossmessage") + " : " + ChatContent.RESET + ChatContent.translateColorCodes(slotMachine.getFinalLossMessage()));
			}
			SlotPlugin.saveToDisk();
		}
		if(event.getPlayer().hasMetadata("slotmachine_setwinmessage")) {
			event.setCancelled(true);
			SlotMachine slotMachine = SlotMachine.getSlotMachineByUUID(UUID.fromString(event.getPlayer().getMetadata("slotmachine_setwinmessage").get(0).asString()));
			if(slotMachine != null) {
				if (event.getMessage().equals("reset")) {
					slotMachine.setHasWinMessage(true);
					slotMachine.setWinMessage("");
				} else if (event.getMessage().equals("null")) {
					slotMachine.setHasWinMessage(false);
				} else {
					slotMachine.setHasWinMessage(true);
					slotMachine.setWinMessage(event.getMessage());
				}
				event.getPlayer().sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("slotmachine.informations.winmessage") + " : " + ChatContent.RESET + ChatContent.translateColorCodes(slotMachine.getFinalWinMessage()));
			}
			SlotPlugin.saveToDisk();
		}
		if(event.getPlayer().hasMetadata("slotmachine_changeduration")) {
			event.setCancelled(true);
			if(SlotMachine.getSlotMachineByUUID(UUID.fromString(event.getPlayer().getMetadata("slotmachine_changeduration").get(0).asString())) != null) {
				SlotMachine slotMachine = SlotMachine.getSlotMachineByUUID(UUID.fromString(event.getPlayer().getMetadata("slotmachine_changeduration").get(0).asString()));
				if(NumberUtils.isNumber(event.getMessage())) {
					if(Integer.valueOf(event.getMessage()) >= 1) {
						slotMachine.setSecondsBeforePrize(Integer.valueOf(event.getMessage().replace("\"", "")));
						event.getPlayer().sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("slotmachine.informations.new.spinduration") + " : " + ChatContent.RESET + slotMachine.getSecondsBeforePrize());
					}
				} else {
					event.getPlayer().sendMessage(ChatContent.RED + ChatContent.BOLD + "[Slot Machine] " + Language.translate("error.notvalidinteger"));
				}
			}
			SlotPlugin.saveToDisk();
		}
		if(event.getPlayer().hasMetadata("slotmachine_changechance")) {
			event.setCancelled(true);
			if(SlotMachine.getSlotMachineByUUID(UUID.fromString(event.getPlayer().getMetadata("slotmachine_changechance").get(0).asString())) != null) {
				SlotMachine slotMachine = SlotMachine.getSlotMachineByUUID(UUID.fromString(event.getPlayer().getMetadata("slotmachine_changechance").get(0).asString()));
				
				if(NumberUtils.isNumber(event.getMessage())) {
					if(Double.valueOf(event.getMessage()) >= 0D && Double.valueOf(event.getMessage().replace("\"", "")) <= 100D) {
						slotMachine.setChanceToWin(Double.valueOf(event.getMessage()) / 100D);
						event.getPlayer().sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("slotmachine.informations.new.chancetowin") + " : " + ChatContent.RESET + (int)(slotMachine.getChanceToWin() * 100) + "%");
					}
				} else {
					event.getPlayer().sendMessage(ChatContent.RED + ChatContent.BOLD + "[Slot Machine] " + Language.translate("error.notvaliddecimal"));
				}
			}
			SlotPlugin.saveToDisk();
		}
		if(event.getPlayer().hasMetadata("slotmachine_changename")) {
			event.setCancelled(true);
			if(SlotMachine.getSlotMachineByUUID(UUID.fromString(event.getPlayer().getMetadata("slotmachine_changename").get(0).asString())) != null) {
				SlotMachine slotMachine = SlotMachine.getSlotMachineByUUID(UUID.fromString(event.getPlayer().getMetadata("slotmachine_changename").get(0).asString()));
				if(slotMachine.getSlotMachineType() == SlotMachineType.ENTITY) {
					Bukkit.getScheduler().scheduleSyncDelayedTask(SlotPlugin.pl, new ThreadChangeEntityName(slotMachine.getWorldUID(), ((SlotMachineEntity)slotMachine).getEntityUUID(), slotMachine.getChunkX(), slotMachine.getChunkZ(), ChatContent.translateColorCodes(event.getMessage())));
				}
				slotMachine.setSlotMachineName(ChatContent.translateColorCodes(event.getMessage()));
				event.getPlayer().sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("slotmachine.informations.new.name") + " : " + ChatContent.RESET + ChatContent.translateColorCodes(slotMachine.getSlotMachineName()));
			}
			SlotPlugin.saveToDisk();
		}
		if(event.getPlayer().hasMetadata("slotmachine_changepermission")) {
			event.setCancelled(true);
			if(SlotMachine.getSlotMachineByUUID(UUID.fromString(event.getPlayer().getMetadata("slotmachine_changepermission").get(0).asString())) != null) {
				SlotMachine slotMachine = SlotMachine.getSlotMachineByUUID(UUID.fromString(event.getPlayer().getMetadata("slotmachine_changepermission").get(0).asString()));
				slotMachine.setGuiPermission("slotmachine.access." + event.getMessage());
				event.getPlayer().sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("slotmachine.informations.new.permission") + " : " + ChatContent.RESET + slotMachine.getGuiPermission());
			}
			SlotPlugin.saveToDisk();
		}
		if(event.getPlayer().hasMetadata("slotmachine_changeprice")) {
			event.setCancelled(true);
			if(SlotMachine.getSlotMachineByUUID(UUID.fromString(event.getPlayer().getMetadata("slotmachine_changeprice").get(0).asString())) != null) {
				SlotMachine slotMachine = SlotMachine.getSlotMachineByUUID(UUID.fromString(event.getPlayer().getMetadata("slotmachine_changeprice").get(0).asString()));
				
				if(NumberUtils.isNumber(event.getMessage())) {
					slotMachine.setPullPrice(Double.parseDouble(event.getMessage().replace("\"", "")));
					event.getPlayer().sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("slotmachine.informations.new.price") + " : " + ChatContent.RESET + Util.formatNumber(slotMachine.getPullPrice()));
				} else {
					event.getPlayer().sendMessage(ChatContent.RED + ChatContent.BOLD + "[Slot Machine] " + Language.translate("error.notvaliddecimal"));
				}
			}
			SlotPlugin.saveToDisk();
		}
		if(event.getPlayer().hasMetadata("slotmachine_setcooldown")) {
			event.setCancelled(true);
			SlotMachine slotMachine = SlotMachine.getSlotMachineByUUID(UUID.fromString(event.getPlayer().getMetadata("slotmachine_setcooldown").get(0).asString()));
			if(slotMachine != null) {
				
				if(NumberUtils.isNumber(event.getMessage())) {
					slotMachine.setCooldown(Integer.valueOf(event.getMessage()));
					event.getPlayer().sendMessage(ChatContent.GREEN + "[Slot Machine] New machine's cooldown : " + slotMachine.getCooldown() + "s");
				} else {
					event.getPlayer().sendMessage(ChatContent.RED + "[Slot Machine] " + Language.translate("error.notvalidinteger"));
				}
			}
			SlotPlugin.saveToDisk();
		}
		PlayerUtil.resetPlayerData(event.getPlayer());
	}
	
	@EventHandler
	public void onPlayerFish(PlayerFishEvent event) {
		if(event.getState() == State.CAUGHT_ENTITY) {
			if(SlotMachineEntity.getSlotMachineByEntityUUID(event.getCaught().getUniqueId()) != null) {
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		if(event.getPlayer().hasMetadata("slotmachine_machineuid")) {
			event.getPlayer().removeMetadata("slotmachine_machineuid", SlotPlugin.pl);
			PlayerUtil.resetPlayerData(event.getPlayer());
		}
	}
	
	@EventHandler
	public void onPlayerKicked(PlayerKickEvent event) {
		if(event.getPlayer().hasMetadata("slotmachine_machineuid")) {
			event.getPlayer().removeMetadata("slotmachine_machineuid", SlotPlugin.pl);
			PlayerUtil.resetPlayerData(event.getPlayer());
		}
	}
	
	@EventHandler
	public void onInventoryClosed(InventoryCloseEvent event) {
		if(event.getPlayer().hasMetadata("slotmachine_machineuid")) {
			event.getPlayer().removeMetadata("slotmachine_machineuid", SlotPlugin.pl);
		}
	}
	
	@EventHandler
	public void onItemFrameDamaged(HangingBreakEvent event) {
		if(SlotMachineEntity.getSlotMachineByEntityUUID(event.getEntity().getUniqueId()) != null) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onEntityDamaged(EntityDamageEvent event) {
		if(SlotMachineEntity.getSlotMachineByEntityUUID(event.getEntity().getUniqueId()) != null) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if(!event.getPlayer().hasMetadata("slotmachineinteractentity")) {
			event.getPlayer().setMetadata("slotmachineinteractentity", new FixedMetadataValue(SlotPlugin.pl, true));
			final UUID playerUUID = event.getPlayer().getUniqueId();
			Bukkit.getScheduler().scheduleSyncDelayedTask(SlotPlugin.pl, new Runnable() {
					@Override
					public void run() {
						if(Bukkit.getPlayer(playerUUID) != null) {
							Bukkit.getPlayer(playerUUID).removeMetadata("slotmachineinteractentity", SlotPlugin.pl);
						}
					}
				}, 10);
			
			if(event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getPlayer().getInventory().getItemInMainHand() != null && event.getPlayer().getInventory().getItemInMainHand().getType() == Material.BLAZE_ROD && (event.getPlayer().hasPermission("slotmachine.machineedit") || event.getPlayer().hasPermission("slotmachine.shopedit") || event.getPlayer().isOp())) {
				Bukkit.getScheduler().scheduleSyncDelayedTask(SlotPlugin.pl, new Runnable() {
					@Override
					public void run() {
						if (SlotPlugin.invManager.getInventory(event.getPlayer()).isPresent()) {
							event.setCancelled(true);
							return;
						}
						if(event.getPlayer().getInventory().getItemInMainHand().hasItemMeta() && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasDisplayName() && (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains(Language.translate("slotmachine.instructions.setitems")) || event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains(Language.translate("slotmachine.instructions.additems")))) {
							SlotMachine slotMachine = SlotMachine.getSlotMachineByUUID(UUID.fromString(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore().get(0)));
							if(event.getClickedBlock().getType() == Material.CHEST) {
								Chest chest = (Chest)event.getClickedBlock().getState();
								if(slotMachine != null) {
									List<MachineItem> slotMachineItems = new ArrayList<MachineItem>();
									for(int a = 0; a < 27; a++) {
										if(chest.getBlockInventory().getItem(a) != null) {
											if(chest.getBlockInventory().getItem(a).hasItemMeta() && chest.getBlockInventory().getItem(a).getType() == Material.NAME_TAG) {
												if(chest.getBlockInventory().getItem(a).getItemMeta().hasDisplayName()) {
													if(NumberUtils.isNumber(chest.getBlockInventory().getItem(a).getItemMeta().getDisplayName())) {
														ItemStack is = new ItemStack(chest.getBlockInventory().getItem(a));
														ItemMeta im = is.getItemMeta();
														List<String> lore = new ArrayList<String>();
														im.setDisplayName(ChatContent.GOLD + ChatContent.ITALIC + chest.getBlockInventory().getItem(a).getItemMeta().getDisplayName() + "$");
														lore.add("Money :");
														lore.add(chest.getBlockInventory().getItem(a).getItemMeta().getDisplayName());
														im.setLore(lore);
														is.setItemMeta(im);
														slotMachineItems.add(new MachineItem(is, 1));
													} else {
														slotMachineItems.add(new MachineItem(chest.getBlockInventory().getItem(a), 1));
													}
												} else {
													slotMachineItems.add(new MachineItem(chest.getBlockInventory().getItem(a), 1));
												}
											} else {
												slotMachineItems.add(new MachineItem(chest.getBlockInventory().getItem(a), 1));
											}
										}
									}
									if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains(Language.translate("slotmachine.instructions.setitems"))) {
										slotMachine.setSlotMachineItems(slotMachineItems);
										event.getPlayer().sendMessage(ChatContent.GREEN + "[Slot Machine] " + Language.translate("slotmachine.instructions.setitems.successful"));
									} else if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains(Language.translate("slotmachine.instructions.additems"))) {
										slotMachine.addSlotMachineItems(slotMachineItems);
										event.getPlayer().sendMessage(ChatContent.GREEN + "[Slot Machine] " + Language.translate("slotmachine.instructions.additems.successful"));
									}
								}
								for(int a = 0; a < chest.getBlockInventory().getSize(); a++) {
									chest.getBlockInventory().clear();
								}
								event.getClickedBlock().breakNaturally();
								SlotPlugin.saveToDisk();
							}
						} else {
							MachineMethods.magicWand(event.getPlayer(), null, event.getClickedBlock());
						}
					}
				}, 1);
			} else if(event.getAction() == Action.RIGHT_CLICK_BLOCK && SlotMachineBlock.getSlotMachineByBlock(event.getClickedBlock()) != null) {
				SlotMachineBlock smb = SlotMachineBlock.getSlotMachineByBlock(event.getClickedBlock());
				event.setCancelled(true);
				
				if(event.getPlayer().hasPermission(smb.getGuiPermission()) || event.getPlayer().isOp()) {
					MachineMethods.openmachine(event.getPlayer(), smb);
				} else {
					event.getPlayer().sendMessage(Variables.getFormattedString(Language.translate(Config.noAccessDefaultString), event.getPlayer(), smb));
					event.getPlayer().playSound(event.getPlayer().getLocation(), smb.getMachineOpeningSound(), 1.9f, 0.4f);
				}
			}
		} else {
			final UUID playerUUID = event.getPlayer().getUniqueId();
			Bukkit.getScheduler().scheduleSyncDelayedTask(SlotPlugin.pl, new Runnable() {
					@Override
					public void run() {
						if(Bukkit.getPlayer(playerUUID) != null) {
							Bukkit.getPlayer(playerUUID).removeMetadata("slotmachineinteractentity", SlotPlugin.pl);
						}
					}
				}, 10);
		}
	}
}
