package fr.klemms.slotmachine.commands;

import fr.klemms.slotmachine.*;
import fr.klemms.slotmachine.exceptioncollector.ExceptionCollector;
import fr.klemms.slotmachine.placeholders.Variables;
import fr.klemms.slotmachine.threads.ThreadGiveItem;
import fr.klemms.slotmachine.tokens.Token;
import fr.klemms.slotmachine.tokens.TokenSelectionListener;
import fr.klemms.slotmachine.tokens.TokensInventory;
import fr.klemms.slotmachine.translation.Language;
import fr.klemms.slotmachine.utils.EntityUtil;
import fr.klemms.slotmachine.utils.PlayerUtil;
import fr.klemms.slotmachine.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CommandSlotMachineAction implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player)sender;
			if(args.length >= 2) {
				UUID uuid = null;
				Entity entity = null;
				SlotMachine slotMachine = null;
				
				if(!args[0].equalsIgnoreCase("createblockslotmachine")) {
					uuid = UUID.fromString(args[1]);
					entity = EntityUtil.getEntityInWorld(uuid);
					slotMachine = SlotMachine.getSlotMachineByUUID(uuid);
					if(slotMachine == null && entity != null) {
						slotMachine = SlotMachineEntity.getSlotMachineByEntityUUID(entity.getUniqueId());
					}
				}
				
				Block block = null;
				if(slotMachine != null) {
					if(slotMachine.getSlotMachineType() == SlotMachineType.BLOCK) {
						block = ((SlotMachineBlock)slotMachine).getBlock();
					}
				}
				Entity slotMachineEntity = null;
				if(slotMachine != null) {
					if(slotMachine.getSlotMachineType() == SlotMachineType.ENTITY) {
						slotMachineEntity = ((SlotMachineEntity)slotMachine).getEntity();
					}
				}
				switch(args[0]) {
					case "createentityslotmachine":
						if(entity != null) {
							MachineMethods.createSlotMachineEntity(player, entity);
							MachineMethods.magicWand(player, entity, null);
							player.sendMessage(ChatContent.AQUA + "[Slot Machine] Right Clicking this Slot Machine with a Blaze Rod will now show the Slot Machine Customization menu in chat");
						}
						break;
					case "createblockslotmachine":
						if(args.length >= 4) {
							Block block1 = player.getWorld().getBlockAt(Integer.valueOf(args[1]), Integer.valueOf(args[2]), Integer.valueOf(args[3]));
							if(block1 != null) {
								MachineMethods.createSlotMachineBlock(player, block1);
								MachineMethods.magicWand(player, null, block1);
								player.sendMessage(ChatContent.AQUA + "[Slot Machine] Right Clicking this Slot Machine with a Blaze Rod will now show the Slot Machine Customization menu in chat");
							} else {
								player.sendMessage(ChatContent.RED + "[Slot Machine] Couldn't create this machine, contact the developper with the logs");
							}
						}
						break;
					case "removeslotmachine":
						if(slotMachine != null) {
							MachineMethods.removeSlotMachine(player, slotMachine.getMachineUUID());
						}
						break;
					case "informations":
						if(slotMachine != null) {
							MachineMethods.sendSlotMachineInformations(player, slotMachine);
						}
						break;
					case "rename":
						if(slotMachine != null) {
							PlayerUtil.resetPlayerData(player);
							player.setMetadata("slotmachine_changename", new FixedMetadataValue(SlotPlugin.pl, slotMachine.getMachineUUID().toString()));
							player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("command.slotmachineaction.rename"));
							player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + "Type \"cancel\" to cancel");
						}
						break;
					case "changepermission":
						if(slotMachine != null) {
							PlayerUtil.resetPlayerData(player);
							player.setMetadata("slotmachine_changepermission", new FixedMetadataValue(SlotPlugin.pl, slotMachine.getMachineUUID().toString()));
							player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("command.slotmachineaction.changepermission"));
							player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + "Type \"cancel\" to cancel");
						}
						break;
					case "csgowheel":
						if(slotMachine != null) {
							slotMachine.setVisualType(VisualType.CSGOWHEEL);
							player.sendMessage(ChatContent.GREEN + "[Slot Machine] " + Language.translate("command.slotmachineaction.csgowheel"));
							SlotPlugin.saveToDisk();
							if(slotMachineEntity != null || block != null) {
								MachineMethods.magicWand(player, slotMachineEntity, block);
							}
						}
						break;
					case "csgowheelvertical":
						if(slotMachine != null) {
							slotMachine.setVisualType(VisualType.CSGOWHEEL_VERTICAL);
							player.sendMessage(ChatContent.GREEN + "[Slot Machine] " + Language.translate("command.slotmachineaction.csgowheelvertical"));
							SlotPlugin.saveToDisk();
							if(slotMachineEntity != null || block != null) {
								MachineMethods.magicWand(player, slotMachineEntity, block);
							}
						}
						break;
					case "slotmachine":
						if(slotMachine != null) {
							slotMachine.setVisualType(VisualType.SLOTMACHINE);
							player.sendMessage(ChatContent.GREEN + "[Slot Machine] " + Language.translate("command.slotmachineaction.slotmachine"));
							SlotPlugin.saveToDisk();
							if(slotMachineEntity != null || block != null) {
								MachineMethods.magicWand(player, slotMachineEntity, block);
							}
						}
						break;
					case "paymentplayerpoints":
						if(slotMachine != null) {
							slotMachine.setPriceType(PriceType.PLAYERPOINTS);
							player.sendMessage(ChatContent.GREEN + "[Slot Machine] " + Language.translate("command.slotmachineaction.paymentplayerpoints"));
							SlotPlugin.saveToDisk();
							if(slotMachineEntity != null || block != null) {
								MachineMethods.magicWand(player, slotMachineEntity, block);
							}
						}
						break;
					case "paymenttokenmanager":
						if(slotMachine != null) {
							slotMachine.setPriceType(PriceType.TOKENMANAGER);
							player.sendMessage(ChatContent.GREEN + "[Slot Machine] " + Language.translate("command.slotmachineaction.paymenttokenmanager"));
							SlotPlugin.saveToDisk();
							if(slotMachineEntity != null || block != null) {
								MachineMethods.magicWand(player, slotMachineEntity, block);
							}
						}
						break;
					case "paymenttokens":
						if(slotMachine != null) {
							slotMachine.setPriceType(PriceType.TOKEN);
							player.sendMessage(ChatContent.GREEN + "[Slot Machine] " + Language.translate("command.slotmachineaction.paymenttokens"));
							SlotPlugin.saveToDisk();
							if(slotMachineEntity != null || block != null) {
								MachineMethods.magicWand(player, slotMachineEntity, block);
							}
						}
						break;
					case "paymentmoney":
						if(slotMachine != null) {
							slotMachine.setPriceType(PriceType.MONEY);
							player.sendMessage(ChatContent.GREEN + "[Slot Machine] " + Language.translate("command.slotmachineaction.paymentmoney"));
							SlotPlugin.saveToDisk();
							if(slotMachineEntity != null || block != null) {
								MachineMethods.magicWand(player, slotMachineEntity, block);
							}
						}
						break;
					case "changeprice":
						if(slotMachine != null) {
							PlayerUtil.resetPlayerData(player);
							player.setMetadata("slotmachine_changeprice", new FixedMetadataValue(SlotPlugin.pl, slotMachine.getMachineUUID().toString()));
							player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("command.slotmachineaction.changeprice") + " :");
							player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + "Type \"cancel\" to cancel");
						}
						break;
					case "setitems":
						if(slotMachine != null) {
							ItemStack newMachine = new ItemStack(Material.BLAZE_ROD);
							ItemMeta newMachineMeta = newMachine.getItemMeta();
							newMachineMeta.setDisplayName(ChatContent.GOLD + ChatContent.BOLD + Language.translate("slotmachine.instructions.setitems"));
							List<String> newMachineLore = new ArrayList<String>();
							newMachineLore.add(slotMachine.getMachineUUID().toString());
							newMachineLore.add(ChatContent.AQUA + Language.translate("slotmachine.instructions.line1"));
							newMachineLore.add(ChatContent.AQUA + Language.translate("slotmachine.instructions.line2"));
							newMachineMeta.setLore(newMachineLore);
							newMachine.setItemMeta(newMachineMeta);
							Bukkit.getScheduler().scheduleSyncDelayedTask(SlotPlugin.pl, new ThreadGiveItem(newMachine, player.getUniqueId()));
							Bukkit.getScheduler().scheduleSyncDelayedTask(SlotPlugin.pl, new ThreadGiveItem(new ItemStack(Material.CHEST, 1), player.getUniqueId()));
						}
						break;
					case "additems":
						if(slotMachine != null) {
							ItemStack newMachine = new ItemStack(Material.BLAZE_ROD);
							ItemMeta newMachineMeta = newMachine.getItemMeta();
							newMachineMeta.setDisplayName(ChatContent.GOLD + ChatContent.BOLD + Language.translate("slotmachine.instructions.additems"));
							List<String> newMachineLore = new ArrayList<String>();
							newMachineLore.add(slotMachine.getMachineUUID().toString());
							newMachineLore.add(ChatContent.AQUA + Language.translate("slotmachine.instructions.add.line1"));
							newMachineLore.add(ChatContent.AQUA + Language.translate("slotmachine.instructions.add.line2"));
							newMachineMeta.setLore(newMachineLore);
							newMachine.setItemMeta(newMachineMeta);
							Bukkit.getScheduler().scheduleSyncDelayedTask(SlotPlugin.pl, new ThreadGiveItem(newMachine, player.getUniqueId()));
							Bukkit.getScheduler().scheduleSyncDelayedTask(SlotPlugin.pl, new ThreadGiveItem(new ItemStack(Material.CHEST, 1), player.getUniqueId()));
						}
						break;
					case "toggleluck":
						if(slotMachine != null) {
							slotMachine.setAffectedByLuck(!slotMachine.isAffectedByLuck());
							player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + "Done.");
							SlotPlugin.saveToDisk();
							if(slotMachineEntity != null || block != null) {
								MachineMethods.magicWand(player, slotMachineEntity, block);
							}
						}
						break;
					case "chancetowin":
						if(slotMachine != null) {
							PlayerUtil.resetPlayerData(player);
							player.setMetadata("slotmachine_changechance", new FixedMetadataValue(SlotPlugin.pl, slotMachine.getMachineUUID().toString()));
							player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("command.slotmachineaction.chancetowin") + " :");
							player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + "Type \"cancel\" to cancel");
						}
						break;
					case "spinduration":
						if(slotMachine != null) {
							PlayerUtil.resetPlayerData(player);
							player.setMetadata("slotmachine_changeduration", new FixedMetadataValue(SlotPlugin.pl, slotMachine.getMachineUUID().toString()));
							player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("command.slotmachineaction.spinduration").replace("%spinDuration%", String.valueOf(slotMachine.getSecondsBeforePrize())));
							player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + "Type \"cancel\" to cancel");
						}
						break;
					case "winmessage":
						if(slotMachine != null) {
							PlayerUtil.resetPlayerData(player);
							player.setMetadata("slotmachine_setwinmessage", new FixedMetadataValue(SlotPlugin.pl, slotMachine.getMachineUUID().toString()));
							player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("command.slotmachineaction.winmessage").replace("%winMessage%", ChatContent.RESET + slotMachine.getWinMessage() + ChatContent.DARK_PURPLE + ChatContent.BOLD));
							player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + "Type \"cancel\" to cancel");
						}
						break;
					case "lossmessage":
						if(slotMachine != null) {
							PlayerUtil.resetPlayerData(player);
							player.setMetadata("slotmachine_setlossmessage", new FixedMetadataValue(SlotPlugin.pl, slotMachine.getMachineUUID().toString()));
							player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("command.slotmachineaction.lossmessage").replace("%lossMessage%", ChatContent.RESET + slotMachine.getLossMessage() + ChatContent.DARK_PURPLE + ChatContent.BOLD));
							player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + "Type \"cancel\" to cancel");
						}
						break;
					case "levertitle":
						if(slotMachine != null) {
							PlayerUtil.resetPlayerData(player);
							player.setMetadata("slotmachine_setlevertitle", new FixedMetadataValue(SlotPlugin.pl, slotMachine.getMachineUUID().toString()));
							player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("command.slotmachineaction.levertitle") + " :");
							for(int a = 0; a < Variables.values().length; a++) {
								player.sendMessage(ChatContent.DARK_AQUA + " - $" + Variables.values()[a].variableName + " - " + Language.translate(Variables.values()[a].variableDescription));
							}
							player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("command.slotmachineaction.levertitle.current").replace("%leverTitle%", ChatContent.RESET + slotMachine.getLeverTitle()));
							player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + "Type \"cancel\" to cancel");
						}
						break;
					case "leverdescription":
						if(slotMachine != null) {
							PlayerUtil.resetPlayerData(player);
							player.setMetadata("slotmachine_setleverdescription", new FixedMetadataValue(SlotPlugin.pl, slotMachine.getMachineUUID().toString()));
							player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("command.slotmachineaction.leverdescription") + " :");
							for(int a = 0; a < Variables.values().length; a++) {
								player.sendMessage(ChatContent.DARK_AQUA + " - $" + Variables.values()[a].variableName + " - " + Language.translate(Variables.values()[a].variableDescription));
							}
							player.sendMessage(ChatContent.DARK_AQUA + " - $newline - " + Language.translate("command.slotmachineaction.leverdescription.newline"));
							player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("command.slotmachineaction.leverdescription.current").replace("%leverDescription%", ChatContent.RESET + slotMachine.getLeverDescription()));
							player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + "Type \"cancel\" to cancel");
						}
						break;
					case "turnoffai":
						if(entity != null) {
							if(entity instanceof LivingEntity) {
								((LivingEntity)entity).setAI(false);
								player.sendMessage(ChatContent.GREEN + "[Slot Machine] " + Language.translate("command.slotmachineaction.turnoffai"));
							}
							MachineMethods.magicWand(player, entity, null);
						}
						break;
					case "turnonai":
						if(entity != null) {
							if(entity instanceof LivingEntity) {
								((LivingEntity)entity).setAI(true);
								player.sendMessage(ChatContent.GREEN + "[Slot Machine] " + Language.translate("command.slotmachineaction.turnonai"));
							}
							MachineMethods.magicWand(player, entity, null);
						}
						break;
					case "turnoffsound":
						if(entity != null) {
							if(entity instanceof LivingEntity) {
								((LivingEntity)entity).setSilent(true);
								player.sendMessage(ChatContent.GREEN + "[Slot Machine] " + Language.translate("command.slotmachineaction.turnoffsound"));
							}
							MachineMethods.magicWand(player, entity, null);
						}
						break;
					case "turnonsound":
						if(entity != null) {
							if(entity instanceof LivingEntity) {
								((LivingEntity)entity).setSilent(false);
								player.sendMessage(ChatContent.GREEN + "[Slot Machine] " + Language.translate("command.slotmachineaction.turnonsound"));
							}
							MachineMethods.magicWand(player, entity, null);
						}
						break;
					case "turnoffgravity":
						if(entity != null && !Util.getMCVersion().startsWith("1.9")) {
							try {
								entity.getClass().getMethod("setGravity", boolean.class).invoke(entity, false);
								player.sendMessage(ChatContent.GREEN + "[Slot Machine] " + Language.translate("command.slotmachineaction.turnoffgravity"));
							} catch (Exception e) {
								e.printStackTrace();
								ExceptionCollector.sendException(SlotPlugin.pl, e);
							}
						}
						break;
					case "turnongravity":
						if(entity != null && !Util.getMCVersion().startsWith("1.9")) {
							try {
								entity.getClass().getMethod("setGravity", boolean.class).invoke(entity, true);
								player.sendMessage(ChatContent.GREEN + "[Slot Machine] " + Language.translate("command.slotmachineaction.turnongravity"));
							} catch (Exception e) {
								e.printStackTrace();
								ExceptionCollector.sendException(SlotPlugin.pl, e);
							}
						}
						break;
					case "turnoffname":
						if(entity != null) {
							entity.setCustomNameVisible(false);
							player.sendMessage(ChatContent.GREEN + "[Slot Machine] " + Language.translate("command.slotmachineaction.turnoffname"));
							MachineMethods.magicWand(player, entity, null);
						}
						break;
					case "turnonname":
						if(entity != null) {
							entity.setCustomNameVisible(true);
							player.sendMessage(ChatContent.GREEN + "[Slot Machine] " + Language.translate("command.slotmachineaction.turnonname"));
							MachineMethods.magicWand(player, entity, null);
						}
						break;
					case "settoken":
						if(slotMachine != null) {
							final UUID smUUID = slotMachine.getMachineUUID();
							TokensInventory.showManagementScreen(player, 0, "Pick this machine's Token", "Left click to choose this Token", new TokenSelectionListener() {

								@Override
								public void callback(Player player, Token token) {
									player.closeInventory();
									player.sendMessage(ChatContent.GREEN + "[Slot Machine] The Token for this Slot Machine has been changed");
									SlotMachine.getSlotMachineByUUID(smUUID).setTokenIdentifier(token.identifier);
									SlotPlugin.saveToDisk();
								}
								
							}, false);
						}
						break;
				}
			}
		}
		return true;
	}
}
