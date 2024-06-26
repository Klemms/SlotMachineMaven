package fr.klemms.slotmachine.events;

import fr.klemms.slotmachine.*;
import fr.klemms.slotmachine.threads.ThreadChangeEntityName;
import fr.klemms.slotmachine.translation.Language;
import fr.klemms.slotmachine.utils.PlayerUtil;
import fr.klemms.slotmachine.utils.Util;
import net.citizensnpcs.api.CitizensAPI;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.player.PlayerFishEvent.State;
import org.bukkit.inventory.EquipmentSlot;

import java.util.UUID;

public class PluginListener implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		if (event.getPlayer().isOp() && SlotPlugin.supportEnding) {
			event.getPlayer().sendMessage(ChatContent.AQUA + SlotPlugin.CHAT_PREFIX + ChatContent.PINK + SlotPlugin.supportMessage);
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
		if (event.getPlayer().hasMetadata("slotmachineinteractblock"))
			return;

		if (event.getHand().equals(EquipmentSlot.HAND)) {
			boolean openSlotMachine = true;
			if(event.getPlayer().getInventory().getItemInMainHand() != null && event.getPlayer().getInventory().getItemInMainHand().getType() == Config.adminToolMaterial) {
				if(event.getPlayer().hasPermission("slotmachine.machineedit") || event.getPlayer().hasPermission("slotmachine.shopedit") || event.getPlayer().isOp()) {
					event.setCancelled(true);
					openSlotMachine = false;

					MachineMethods.magicWand(event.getPlayer(), event.getRightClicked(), null);
				} else if(Config.debug) {
					event.getPlayer().sendMessage(ChatContent.RED + SlotPlugin.CHAT_PREFIX + "You don't have the required permissions to edit this Slot Machine");
				}
			}
			UUID uuid = event.getRightClicked().getUniqueId();
			if (SlotPlugin.isCitizensEnabled && CitizensAPI.getNPCRegistry().isNPC(event.getRightClicked()))
				uuid = CitizensAPI.getNPCRegistry().getNPC(event.getRightClicked()).getUniqueId();

			SlotMachineEntity machine = SlotMachineEntityLink.getAllSlotMachineByEntityUUID(uuid);

			if(machine != null && openSlotMachine) {
				event.setCancelled(true);
				MachineMethods.openmachine(event.getPlayer(), machine);
			}
		}
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		SlotMachineBlock smb = SlotMachineBlockLink.getAllSlotMachineByBlock(event.getBlock());
		if(smb != null && smb.isLocked()) {
			event.setCancelled(true);
			if(event.getPlayer().hasPermission("slotmachine.machineedit") || event.getPlayer().hasPermission("slotmachine.shopedit") || event.getPlayer().isOp()) {
				event.getPlayer().sendMessage(ChatContent.RED + SlotPlugin.CHAT_PREFIX + Language.translate("slotmachine.breakblock.cant"));
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
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
				slotMachine.save();
				event.getPlayer().sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("slotmachine.informations.levertitle") + " : " + ChatContent.RESET + slotMachine.getLeverTitle());
			}
		}
		if(event.getPlayer().hasMetadata("slotmachine_setleverdescription")) {
			event.setCancelled(true);
			if(SlotMachine.getSlotMachineByUUID(UUID.fromString(event.getPlayer().getMetadata("slotmachine_setleverdescription").get(0).asString())) != null) {
				SlotMachine slotMachine = SlotMachine.getSlotMachineByUUID(UUID.fromString(event.getPlayer().getMetadata("slotmachine_setleverdescription").get(0).asString()));
				slotMachine.setLeverDescription(event.getMessage());
				slotMachine.setLeverCustom(true);
				slotMachine.save();
				event.getPlayer().sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("slotmachine.informations.leverdescription") + " : " + ChatContent.RESET + slotMachine.getLeverDescription());
			}
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
				slotMachine.save();
				event.getPlayer().sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("slotmachine.informations.lossmessage") + " : " + ChatContent.RESET + ChatContent.translateColorCodes(slotMachine.getFinalLossMessage()));
			}
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
				slotMachine.save();
				event.getPlayer().sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("slotmachine.informations.winmessage") + " : " + ChatContent.RESET + ChatContent.translateColorCodes(slotMachine.getFinalWinMessage()));
			}
		}
		if(event.getPlayer().hasMetadata("slotmachine_changeduration")) {
			event.setCancelled(true);
			if(SlotMachine.getSlotMachineByUUID(UUID.fromString(event.getPlayer().getMetadata("slotmachine_changeduration").get(0).asString())) != null) {
				SlotMachine slotMachine = SlotMachine.getSlotMachineByUUID(UUID.fromString(event.getPlayer().getMetadata("slotmachine_changeduration").get(0).asString()));
				if(NumberUtils.isCreatable(event.getMessage())) {
					if(NumberUtils.createInteger(event.getMessage().replace("\"", "")) >= 1) {
						slotMachine.setSecondsBeforePrize(NumberUtils.createInteger(event.getMessage().replace("\"", "")));
						slotMachine.save();
						event.getPlayer().sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("slotmachine.informations.new.spinduration") + " : " + ChatContent.RESET + slotMachine.getSecondsBeforePrize());
					} else {
						event.getPlayer().sendMessage(ChatContent.RED + ChatContent.BOLD + SlotPlugin.CHAT_PREFIX + "Number must be greater than 0");
					}
				} else {
					event.getPlayer().sendMessage(ChatContent.RED + ChatContent.BOLD + SlotPlugin.CHAT_PREFIX + Language.translate("error.notvalidinteger"));
				}
			}
		}
		if(event.getPlayer().hasMetadata("slotmachine_changechance")) {
			event.setCancelled(true);
			if(SlotMachine.getSlotMachineByUUID(UUID.fromString(event.getPlayer().getMetadata("slotmachine_changechance").get(0).asString())) != null) {
				SlotMachine slotMachine = SlotMachine.getSlotMachineByUUID(UUID.fromString(event.getPlayer().getMetadata("slotmachine_changechance").get(0).asString()));

				if(NumberUtils.isCreatable(event.getMessage())) {
					if(NumberUtils.createDouble(event.getMessage().replace("\"", "")) >= 0D && NumberUtils.createDouble(event.getMessage().replace("\"", "")) <= 100D) {
						slotMachine.setChanceToWin(NumberUtils.createDouble(event.getMessage().replace("\"", "")) / 100D);
						slotMachine.save();
						event.getPlayer().sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("slotmachine.informations.new.chancetowin") + " : " + ChatContent.RESET + (int)(slotMachine.getChanceToWin() * 100) + "%");
					} else {
						event.getPlayer().sendMessage(ChatContent.RED + ChatContent.BOLD + SlotPlugin.CHAT_PREFIX + "Number must be between 0 and 100 (included)");
					}
				} else {
					event.getPlayer().sendMessage(ChatContent.RED + ChatContent.BOLD + SlotPlugin.CHAT_PREFIX + Language.translate("error.notvaliddecimal"));
				}
			}
		}
		if(event.getPlayer().hasMetadata("slotmachine_changename")) {
			event.setCancelled(true);
			if(SlotMachine.getSlotMachineByUUID(UUID.fromString(event.getPlayer().getMetadata("slotmachine_changename").get(0).asString())) != null) {
				SlotMachine slotMachine = SlotMachine.getSlotMachineByUUID(UUID.fromString(event.getPlayer().getMetadata("slotmachine_changename").get(0).asString()));
				if(slotMachine.getSlotMachineType() == SlotMachineType.ENTITY || slotMachine.getSlotMachineType() == SlotMachineType.ENTITY_LINK) {
					Bukkit.getScheduler().scheduleSyncDelayedTask(SlotPlugin.pl, new ThreadChangeEntityName(((SlotMachineEntity)slotMachine).getEntityUUID(), ChatContent.translateColorCodes(event.getMessage())));
				}
				slotMachine.setSlotMachineName(ChatContent.translateColorCodes(event.getMessage()));
				slotMachine.save();
				event.getPlayer().sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("slotmachine.informations.new.name") + " : " + ChatContent.RESET + ChatContent.translateColorCodes(slotMachine.getSlotMachineName()));
			}
		}
		if(event.getPlayer().hasMetadata("slotmachine_changepermission")) {
			event.setCancelled(true);
			if(SlotMachine.getSlotMachineByUUID(UUID.fromString(event.getPlayer().getMetadata("slotmachine_changepermission").get(0).asString())) != null) {
				SlotMachine slotMachine = SlotMachine.getSlotMachineByUUID(UUID.fromString(event.getPlayer().getMetadata("slotmachine_changepermission").get(0).asString()));
				slotMachine.setGuiPermission("slotmachine.access." + event.getMessage());
				slotMachine.save();
				event.getPlayer().sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("slotmachine.informations.new.permission") + " : " + ChatContent.RESET + slotMachine.getGuiPermission());
			}
		}
		if(event.getPlayer().hasMetadata("slotmachine_changeprice")) {
			event.setCancelled(true);
			if(SlotMachine.getSlotMachineByUUID(UUID.fromString(event.getPlayer().getMetadata("slotmachine_changeprice").get(0).asString())) != null) {
				SlotMachine slotMachine = SlotMachine.getSlotMachineByUUID(UUID.fromString(event.getPlayer().getMetadata("slotmachine_changeprice").get(0).asString()));

				if(NumberUtils.isCreatable(event.getMessage())) {
					slotMachine.setPullPrice(NumberUtils.createDouble(event.getMessage().replace("\"", "")));
					slotMachine.save();
					event.getPlayer().sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("slotmachine.informations.new.price") + " : " + ChatContent.RESET + Util.formatNumber(slotMachine.getPullPrice()));
				} else {
					event.getPlayer().sendMessage(ChatContent.RED + ChatContent.BOLD + SlotPlugin.CHAT_PREFIX + Language.translate("error.notvaliddecimal"));
				}
			}
		}
		if(event.getPlayer().hasMetadata("slotmachine_setcooldown")) {
			event.setCancelled(true);
			SlotMachine slotMachine = SlotMachine.getSlotMachineByUUID(UUID.fromString(event.getPlayer().getMetadata("slotmachine_setcooldown").get(0).asString()));
			if(slotMachine != null) {

				if(NumberUtils.isCreatable(event.getMessage())) {
					slotMachine.setCooldown(NumberUtils.createInteger(event.getMessage().replace("\"", "")));
					slotMachine.save();
					event.getPlayer().sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + "New machine's cooldown : " + slotMachine.getCooldown() + "s");
				} else {
					event.getPlayer().sendMessage(ChatContent.RED + SlotPlugin.CHAT_PREFIX + Language.translate("error.notvalidinteger"));
				}
			}
		}
		PlayerUtil.resetPlayerData(event.getPlayer());
	}

	@EventHandler
	public void onPlayerFish(PlayerFishEvent event) {
		if(event.getState() == State.CAUGHT_ENTITY) {
			if(event.getCaught() != null && SlotMachineEntityLink.getAllSlotMachineByEntityUUID(event.getCaught().getUniqueId()) != null) {
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
		if(SlotMachineEntityLink.getAllSlotMachineByEntityUUID(event.getEntity().getUniqueId()) != null) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onEntityDamaged(EntityDamageEvent event) {
		if(SlotMachineEntityLink.getAllSlotMachineByEntityUUID(event.getEntity().getUniqueId()) != null) {
			event.setCancelled(true);
		}
	}

	@EventHandler(ignoreCancelled = false)
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.getPlayer().hasMetadata("slotmachineinteractentity"))
			return;

		if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getHand().equals(EquipmentSlot.HAND)) {
			boolean openSlotMachine = true;
			if(event.getPlayer().getInventory().getItemInMainHand() != null && event.getPlayer().getInventory().getItemInMainHand().getType() == Config.adminToolMaterial) {
				if(event.getPlayer().hasPermission("slotmachine.machineedit") || event.getPlayer().hasPermission("slotmachine.shopedit") || event.getPlayer().isOp()) {
					event.setCancelled(true);
					openSlotMachine = false;

					MachineMethods.magicWand(event.getPlayer(), null, event.getClickedBlock());
				} else if(Config.debug) {
					event.getPlayer().sendMessage(ChatContent.RED + SlotPlugin.CHAT_PREFIX + "You don't have the required permissions to edit this Slot Machine");
				}
			}
			SlotMachineBlock machine = SlotMachineBlockLink.getAllSlotMachineByBlock(event.getClickedBlock());

			if(machine != null && openSlotMachine) {
				event.setCancelled(true);

				MachineMethods.openmachine(event.getPlayer(), machine);
			}
		}
	}
}
