package fr.klemms.slotmachine;

import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import fr.klemms.slotmachine.Issue.IssueType;
import fr.klemms.slotmachine.exceptioncollector.ExceptionCollector;
import fr.klemms.slotmachine.interraction.MachineInterractionInventory;
import fr.klemms.slotmachine.translation.Language;
import net.citizensnpcs.api.CitizensAPI;
import net.md_5.bungee.api.ChatColor;

public class MachineMethods {
	
	private static ChatColor nextButtonColor = ChatColor.AQUA;
	
	private static ChatColor getButtonColor() {
		return nextButtonColor == ChatColor.AQUA ? (nextButtonColor = ChatColor.DARK_AQUA) : (nextButtonColor = ChatColor.AQUA);
	}
	
	public static void openmachine(Player player, SlotMachine machine) {
		
		if(machine.getSlotMachineItems().size() > 0) {
			if(machine.getPriceType() == PriceType.GAMEPOINTS && !SlotPlugin.isGamePointsEnabled) {
				player.sendMessage(ChatContent.RED + "[Slot Machine] " + Language.translate("slotmachine.access.missinggamepoints"));
				return;
			}
			if(machine.getPriceType() == PriceType.TOKENMANAGER && !SlotPlugin.oldTokenManagerWorks && SlotPlugin.tokenManager == null) {
				player.sendMessage(ChatContent.RED + "[Slot Machine] " + Language.translate("slotmachine.access.missingtokenmanager"));
				return;
			}
			if(machine.getPriceType() == PriceType.VOTINGPLUGIN && SlotPlugin.votingPlugin == null) {
				player.sendMessage(ChatContent.RED + "[Slot Machine] " + Language.translate("slotmachine.access.missingvotingplugin"));
				return;
			}
			if(machine.getPriceType() == PriceType.MONEY && SlotPlugin.econ == null) {
				player.sendMessage(ChatContent.RED + "[Slot Machine] " + Language.translate("slotmachine.access.missingvault"));
				return;
			}
			
			player.closeInventory();
			machine.openMachine(player, true);
			player.playSound(player.getLocation(), machine.getMachineOpeningSound(), 1.9f, 1.2f);
		} else {
			player.sendMessage(ChatContent.RED + "[Slot Machine] " + Language.translate("slotmachine.access.notsetup"));
		}
	}
	
	public static void magicWand(Player player, Entity entity, Block block) {
		//player.spigot().sendMessage(new ComponentBuilder("- [Slot Machine] " + Language.translate("magicwand.chatinstructions")).color(ChatColor.DARK_GREEN).bold(true).create());
		
		UUID uuid = entity != null ? entity.getUniqueId() : null;
		if (uuid != null && SlotPlugin.isCitizensEnabled && CitizensAPI.getNPCRegistry().isNPC(entity))
			uuid = CitizensAPI.getNPCRegistry().getNPC(entity).getUniqueId();
		
		SlotMachine machine = entity != null ? SlotMachineEntity.getSlotMachineByEntityUUID(uuid) : block != null ? SlotMachineBlock.getSlotMachineByBlock(block) : null;
		MachineInterractionInventory.manageMachine(player, machine, entity, block, 0);
		
		/*if(machine == null) {
			player.spigot().sendMessage(new ComponentBuilder("[" + Language.translate("magicwand.chatinstructions.createslotmachine") + "]")
					.color(getButtonColor())
					.event(new ClickEvent(Action.RUN_COMMAND, "/slotmachineaction " + (entity != null ? "createentityslotmachine " + entity.getUniqueId().toString() : "createblockslotmachine " + block.getX() + " " + block.getY() + " " + block.getZ())))
					.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Language.translate("magicwand.chatinstructions.createslotmachine.description")).color(ChatColor.AQUA).create()))
					.create());
		} else {
			MachineInterractionInventory.manageMachine(player, machine, entity, 0);
			player.spigot().sendMessage(new ComponentBuilder("[" + Language.translate("magicwand.chatinstructions.removeslotmachine") + "]")
					.color(getButtonColor())
					.event(new ClickEvent(Action.RUN_COMMAND, "/slotmachineaction removeslotmachine " + machine.getMachineUUID().toString()))
					.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Language.translate("magicwand.chatinstructions.removeslotmachine.description")).color(ChatColor.AQUA).create()))
					.create());
			
			player.spigot().sendMessage(new ComponentBuilder("[" + Language.translate("magicwand.chatinstructions.informations") + "]")
					.color(getButtonColor())
					.event(new ClickEvent(Action.RUN_COMMAND, "/slotmachineaction informations " + machine.getMachineUUID().toString()))
					.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Language.translate("magicwand.chatinstructions.informations.description")).color(ChatColor.AQUA).create()))
					.create());
			
			player.spigot().sendMessage(new ComponentBuilder("[" + Language.translate("magicwand.chatinstructions.rename") + "]")
					.color(getButtonColor())
					.event(new ClickEvent(Action.RUN_COMMAND, "/slotmachineaction rename " + machine.getMachineUUID().toString()))
					.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Language.translate("magicwand.chatinstructions.rename.description")).color(ChatColor.AQUA).create()))
					.create());
			
			player.spigot().sendMessage(new ComponentBuilder("[" + Language.translate("magicwand.chatinstructions.changepermission") + "]")
					.color(getButtonColor())
					.event(new ClickEvent(Action.RUN_COMMAND, "/slotmachineaction changepermission " + machine.getMachineUUID().toString()))
					.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Language.translate("magicwand.chatinstructions.changepermission.description")).color(ChatColor.AQUA).create()))
					.create());
			
			if(machine.getVisualType() != VisualType.CSGOWHEEL) {
				player.spigot().sendMessage(new ComponentBuilder("[" + Language.translate("magicwand.chatinstructions.csgowheel") + "]")
						.color(getButtonColor())
						.event(new ClickEvent(Action.RUN_COMMAND, "/slotmachineaction csgowheel " + machine.getMachineUUID().toString()))
						.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Language.translate("magicwand.chatinstructions.csgowheel.description")).color(ChatColor.AQUA).create()))
						.create());
			}
			
			if(machine.getVisualType() != VisualType.CSGOWHEEL_VERTICAL) {
				player.spigot().sendMessage(new ComponentBuilder("[" + Language.translate("magicwand.chatinstructions.csgowheelvertical") + "]")
						.color(getButtonColor())
						.event(new ClickEvent(Action.RUN_COMMAND, "/slotmachineaction csgowheelvertical " + machine.getMachineUUID().toString()))
						.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Language.translate("magicwand.chatinstructions.csgowheelvertical.description")).color(ChatColor.AQUA).create()))
						.create());
			}
			
			if(machine.getVisualType() != VisualType.SLOTMACHINE) {
				player.spigot().sendMessage(new ComponentBuilder("[" + Language.translate("magicwand.chatinstructions.slotmachine") + "]")
						.color(getButtonColor())
						.event(new ClickEvent(Action.RUN_COMMAND, "/slotmachineaction slotmachine " + machine.getMachineUUID().toString()))
						.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Language.translate("magicwand.chatinstructions.slotmachine.description")).color(ChatColor.AQUA).create()))
						.create());
			}
			
			if(machine.getPriceType() != PriceType.TOKEN) {
				player.spigot().sendMessage(new ComponentBuilder("[" + Language.translate("magicwand.chatinstructions.paymenttokens") + "]")
						.color(getButtonColor())
						.event(new ClickEvent(Action.RUN_COMMAND, "/slotmachineaction paymenttokens " + machine.getMachineUUID().toString()))
						.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Language.translate("magicwand.chatinstructions.paymenttokens.description")).color(ChatColor.AQUA).create()))
						.create());
			}
			
			if(machine.getPriceType() == PriceType.TOKEN) {
				player.spigot().sendMessage(new ComponentBuilder("[" + Language.translate("magicwand.chatinstructions.settoken") + "]")
						.color(getButtonColor())
						.event(new ClickEvent(Action.RUN_COMMAND, "/slotmachineaction settoken " + machine.getMachineUUID().toString()))
						.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Language.translate("magicwand.chatinstructions.settoken.description")).color(ChatColor.AQUA).create()))
						.create());
			}
			
			if(machine.getPriceType() != PriceType.MONEY) {
				if(SlotPlugin.econ != null) {
					player.spigot().sendMessage(new ComponentBuilder("[" + Language.translate("magicwand.chatinstructions.paymentmoney") + "]")
							.color(getButtonColor())
							.event(new ClickEvent(Action.RUN_COMMAND, "/slotmachineaction paymentmoney " + machine.getMachineUUID().toString()))
							.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Language.translate("magicwand.chatinstructions.paymentmoney.description")).color(ChatColor.AQUA).create()))
							.create());
				} else {
					player.spigot().sendMessage(new ComponentBuilder(Language.translate("magicwand.chatinstructions.paymentmoney.unable"))
							.color(getButtonColor())
							.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Language.translate("magicwand.chatinstructions.paymentmoney.unable.description")).color(ChatColor.AQUA).create()))
							.create());
				}
			}
			
			if(machine.getPriceType() != PriceType.GAMEPOINTS && SlotPlugin.isGamePointsEnabled) {
				player.spigot().sendMessage(new ComponentBuilder("[" + Language.translate("magicwand.chatinstructions.paymentgamepoints") + "]")
						.color(getButtonColor())
						.event(new ClickEvent(Action.RUN_COMMAND, "/slotmachineaction paymentgamepoints " + machine.getMachineUUID().toString()))
						.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Language.translate("magicwand.chatinstructions.paymentgamepoints.description")).color(ChatColor.AQUA).create()))
						.create());
			}
			
			if(machine.getPriceType() != PriceType.TOKENMANAGER && (SlotPlugin.oldTokenManagerWorks || SlotPlugin.tokenManager != null)) {
				player.spigot().sendMessage(new ComponentBuilder("[" + Language.translate("magicwand.chatinstructions.paymenttokenmanager") + "]")
						.color(getButtonColor())
						.event(new ClickEvent(Action.RUN_COMMAND, "/slotmachineaction paymenttokenmanager " + machine.getMachineUUID().toString()))
						.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Language.translate("magicwand.chatinstructions.paymenttokenmanager.description")).color(ChatColor.AQUA).create()))
						.create());
			}
			
			player.spigot().sendMessage(new ComponentBuilder("[" + Language.translate("magicwand.chatinstructions.changeprice") + "]")
					.color(getButtonColor())
					.event(new ClickEvent(Action.RUN_COMMAND, "/slotmachineaction changeprice " + machine.getMachineUUID().toString()))
					.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Language.translate("magicwand.chatinstructions.changeprice.description")).color(ChatColor.AQUA).create()))
					.create());
			
			player.spigot().sendMessage(new ComponentBuilder("[" + Language.translate("magicwand.chatinstructions.setitems") + "]")
					.color(getButtonColor())
					.event(new ClickEvent(Action.RUN_COMMAND, "/slotmachineaction setitems " + machine.getMachineUUID().toString()))
					.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Language.translate("magicwand.chatinstructions.setitems.description")).color(ChatColor.AQUA).create()))
					.create());
			
			player.spigot().sendMessage(new ComponentBuilder("[" + Language.translate("magicwand.chatinstructions.additems") + "]")
					.color(getButtonColor())
					.event(new ClickEvent(Action.RUN_COMMAND, "/slotmachineaction additems " + machine.getMachineUUID().toString()))
					.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Language.translate("magicwand.chatinstructions.additems.description")).color(ChatColor.AQUA).create()))
					.create());
			
			player.spigot().sendMessage(new ComponentBuilder("[" + Language.translate("magicwand.chatinstructions.toggleluck") + " : " + Language.translate(machine.isAffectedByLuck() ? "basic.yes" : "basic.no") + "]")
					.color(getButtonColor())
					.event(new ClickEvent(Action.RUN_COMMAND, "/slotmachineaction toggleluck " + machine.getMachineUUID().toString()))
					.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Language.translate("magicwand.chatinstructions.toggleluck.description")).color(ChatColor.AQUA).create()))
					.create());
			
			player.spigot().sendMessage(new ComponentBuilder("[" + Language.translate("magicwand.chatinstructions.chancetowin") + "]")
					.color(getButtonColor())
					.event(new ClickEvent(Action.RUN_COMMAND, "/slotmachineaction chancetowin " + machine.getMachineUUID().toString()))
					.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Language.translate("magicwand.chatinstructions.chancetowin.description")).color(ChatColor.AQUA).create()))
					.create());
			
			player.spigot().sendMessage(new ComponentBuilder("[" + Language.translate("magicwand.chatinstructions.spinduration") + "]")
					.color(getButtonColor())
					.event(new ClickEvent(Action.RUN_COMMAND, "/slotmachineaction spinduration " + machine.getMachineUUID().toString()))
					.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Language.translate("magicwand.chatinstructions.spinduration.description")).color(ChatColor.AQUA).create()))
					.create());
			
			player.spigot().sendMessage(new ComponentBuilder("[" + Language.translate("magicwand.chatinstructions.winmessage") + "]")
					.color(getButtonColor())
					.event(new ClickEvent(Action.RUN_COMMAND, "/slotmachineaction winmessage " + machine.getMachineUUID().toString()))
					.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Language.translate("magicwand.chatinstructions.winmessage.description")).color(ChatColor.AQUA).create()))
					.create());
			
			player.spigot().sendMessage(new ComponentBuilder("[" + Language.translate("magicwand.chatinstructions.lossmessage") + "]")
					.color(getButtonColor())
					.event(new ClickEvent(Action.RUN_COMMAND, "/slotmachineaction lossmessage " + machine.getMachineUUID().toString()))
					.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Language.translate("magicwand.chatinstructions.lossmessage.description")).color(ChatColor.AQUA).create()))
					.create());
			
			player.spigot().sendMessage(new ComponentBuilder("[" + Language.translate("magicwand.chatinstructions.levertitle") + "]")
					.color(getButtonColor())
					.event(new ClickEvent(Action.RUN_COMMAND, "/slotmachineaction levertitle " + machine.getMachineUUID().toString()))
					.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Language.translate("magicwand.chatinstructions.levertitle.description")).color(ChatColor.AQUA).create()))
					.create());
			
			player.spigot().sendMessage(new ComponentBuilder("[" + Language.translate("magicwand.chatinstructions.leverdescription") + "]")
					.color(getButtonColor())
					.event(new ClickEvent(Action.RUN_COMMAND, "/slotmachineaction leverdescription " + machine.getMachineUUID().toString()))
					.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Language.translate("magicwand.chatinstructions.leverdescription.description")).color(ChatColor.AQUA).create()))
					.create());
		}
		
		if(entity != null && entity instanceof LivingEntity) {
			if(((LivingEntity)entity).hasAI()) {
				player.spigot().sendMessage(new ComponentBuilder("[" + Language.translate("magicwand.chatinstructions.turnoffai") + "]")
						.color(getButtonColor())
						.event(new ClickEvent(Action.RUN_COMMAND, "/slotmachineaction turnoffai " + entity.getUniqueId().toString()))
						.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Language.translate("magicwand.chatinstructions.turnoffai.description")).color(ChatColor.AQUA).create()))
						.create());
			} else {
				player.spigot().sendMessage(new ComponentBuilder("[" + Language.translate("magicwand.chatinstructions.turnonai") + "]")
						.color(getButtonColor())
						.event(new ClickEvent(Action.RUN_COMMAND, "/slotmachineaction turnonai " + entity.getUniqueId().toString()))
						.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Language.translate("magicwand.chatinstructions.turnonai.description")).color(ChatColor.AQUA).create()))
						.create());
			}
			if(!((LivingEntity)entity).isSilent()) {
				player.spigot().sendMessage(new ComponentBuilder("[" + Language.translate("magicwand.chatinstructions.turnoffsound") + "]")
						.color(getButtonColor())
						.event(new ClickEvent(Action.RUN_COMMAND, "/slotmachineaction turnoffsound " + entity.getUniqueId().toString()))
						.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Language.translate("magicwand.chatinstructions.turnoffsound.description")).color(ChatColor.AQUA).create()))
						.create());
			} else {
				player.spigot().sendMessage(new ComponentBuilder("[" + Language.translate("magicwand.chatinstructions.turnonsound") + "]")
						.color(getButtonColor())
						.event(new ClickEvent(Action.RUN_COMMAND, "/slotmachineaction turnonsound " + entity.getUniqueId().toString()))
						.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Language.translate("magicwand.chatinstructions.turnonsound.description")).color(ChatColor.AQUA).create()))
						.create());
			}
		}
		
		if(entity != null) {
			if(entity.isCustomNameVisible()) {
				player.spigot().sendMessage(new ComponentBuilder("[" + Language.translate("magicwand.chatinstructions.turnoffname") + "]")
						.color(getButtonColor())
						.event(new ClickEvent(Action.RUN_COMMAND, "/slotmachineaction turnoffname " + entity.getUniqueId().toString()))
						.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Language.translate("magicwand.chatinstructions.turnoffname.description")).color(ChatColor.AQUA).create()))
						.create());
			} else {
				player.spigot().sendMessage(new ComponentBuilder("[" + Language.translate("magicwand.chatinstructions.turnonname") + "]")
						.color(getButtonColor())
						.event(new ClickEvent(Action.RUN_COMMAND, "/slotmachineaction turnonname " + entity.getUniqueId().toString()))
						.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Language.translate("magicwand.chatinstructions.turnonname.description")).color(ChatColor.AQUA).create()))
						.create());
			}
		}
		if (!Util.getMCVersion().startsWith("1.9") && entity != null) {
			try {
				Method hasGravityMethod = entity.getClass().getMethod("hasGravity");
				if((boolean) hasGravityMethod.invoke(entity)) {
					player.spigot().sendMessage(new ComponentBuilder("[" + Language.translate("magicwand.chatinstructions.turnoffgravity") + "]")
							.color(getButtonColor())
							.event(new ClickEvent(Action.RUN_COMMAND, "/slotmachineaction turnoffgravity " + entity.getUniqueId().toString()))
							.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Language.translate("magicwand.chatinstructions.turnoffgravity.description")).color(ChatColor.AQUA).create()))
							.create());
				} else {
					player.spigot().sendMessage(new ComponentBuilder("[" + Language.translate("magicwand.chatinstructions.turnongravity") + "]")
							.color(getButtonColor())
							.event(new ClickEvent(Action.RUN_COMMAND, "/slotmachineaction turnongravity " + entity.getUniqueId().toString()))
							.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Language.translate("magicwand.chatinstructions.turnongravity.description")).color(ChatColor.AQUA).create()))
							.create());
				}
			} catch (Exception e) {
				e.printStackTrace();
				ExceptionCollector.sendException(SlotPlugin.pl, e);
			}
		}
		player.spigot().sendMessage(new ComponentBuilder("-----------------------------").color(ChatColor.DARK_GREEN).bold(true).create());*/
	}

	public static void createSlotMachineEntity(Player player, Entity entity) {
		UUID uuid = entity.getUniqueId();
		boolean isCitizensNPC = false;
		
		if (SlotPlugin.isCitizensEnabled && CitizensAPI.getNPCRegistry().isNPC(entity)) {
			uuid = CitizensAPI.getNPCRegistry().getNPC(entity).getUniqueId();
			isCitizensNPC = true;
		}
		
		if(SlotMachineEntity.getSlotMachineByEntityUUID(uuid) == null) {
			if(!isCitizensNPC && entity instanceof LivingEntity) {
				((LivingEntity)entity).setAI(false);
				((LivingEntity)entity).setSilent(true);
			}
			SlotMachineEntity slotMachineEntity = new SlotMachineEntity(uuid, entity.getWorld().getUID(), entity.getLocation().getChunk().getX(), entity.getLocation().getChunk().getZ());
			slotMachineEntity.setCitizensNPC(isCitizensNPC);
			SlotMachineEntity.addSlotMachineEntity(slotMachineEntity);
			player.sendMessage(ChatContent.GREEN + "[SlotMachine] " + Language.translate("slotmachine.created").replace("%entityUUID%", uuid.toString()));
			
			if(SlotPlugin.econ == null) {
				player.sendMessage(ChatContent.BOLD + ChatContent.AQUA + "[SlotMachine] " + Language.translate("slotmachine.tokensfallback"));
			}
			
			Bukkit.getLogger().log(Level.INFO, "New SlotMachine (Entity)" + (isCitizensNPC ? " (Citizens NPC)" : "") + " (Created by : " + player.getName() + ") : '" + entity.getType().toString() + "' with UUID '" + uuid.toString() + "' in world '" + entity.getWorld().getName() + "' at '" + entity.getLocation().getX() + " " + entity.getLocation().getY() + " " + entity.getLocation().getZ() + "'");
			SlotPlugin.saveToDisk();
		} else {
			player.sendMessage(ChatContent.RED + ChatContent.BOLD + "[SlotMachine] " + Language.translate("slotmachine.alreadyslotmachine"));
		}
	}

	public static void createSlotMachineBlock(Player player, Block block) {
		if(SlotMachineBlock.getSlotMachineByBlock(block) == null) {
			SlotMachineBlock slotMachineBlock = new SlotMachineBlock(block.getX(), block.getY(), block.getZ(), true, block.getWorld().getUID(), block.getLocation().getChunk().getX(), block.getLocation().getChunk().getZ());
			SlotMachineBlock.addSlotMachineBlock(slotMachineBlock);
			player.sendMessage(ChatContent.GREEN + ChatContent.BOLD + "[SlotMachine] " + Language.translate("slotmachine.created.block").replace("%location%", block.getX() + "," + block.getY() + "," + block.getZ()));
			
			if(SlotPlugin.econ == null) {
				player.sendMessage(ChatContent.BOLD + ChatContent.AQUA + "[SlotMachine] " + Language.translate("slotmachine.tokensfallback"));
			}
			
			Bukkit.getLogger().log(Level.INFO, "New SlotMachine (Block) (Created by : " + player.getName() + ") : '" + block.getType().toString() + "' in world '" + block.getWorld().getName() + "' at '" + block.getLocation().getX() + " " + block.getLocation().getY() + " " + block.getLocation().getZ() + "'");
			SlotPlugin.saveToDisk();
		} else {
			player.sendMessage(ChatContent.RED + ChatContent.BOLD + "[SlotMachine] " + Language.translate("slotmachine.alreadyslotmachine"));
		}
	}
	
	public static void removeSlotMachine(Player player, UUID machineUUID) {
		if(SlotMachine.getSlotMachineByUUID(machineUUID) != null) {
			SlotMachine slotMachine = SlotMachine.getSlotMachineByUUID(machineUUID);
			SlotMachine.removeSlotMachine(slotMachine);
			player.sendMessage(ChatContent.GREEN + ChatContent.BOLD + "[SlotMachine] " + Language.translate("slotmachine.removed"));
			SlotPlugin.pl.getLogger().log(Level.INFO, Language.translate("slotmachine.removed.logs"));
			try {
				Files.deleteIfExists(SlotPlugin.pl.getDataFolder().toPath().resolve("machines").resolve(slotMachine.getMachineUUID().toString() + ".yml"));
			} catch (IOException e) {
				e.printStackTrace();
				Issue.newIssue(IssueType.MACHINE_REMOVAL_EXCEPTION, e.getMessage(), true);
				ExceptionCollector.sendException(SlotPlugin.pl, e);
			}
			SlotPlugin.saveToDisk();
		} else {
			player.sendMessage(ChatContent.RED + ChatContent.BOLD + "[SlotMachine] " + Language.translate("slotmachine.notslotmachine"));
		}
	}
	
	public static void sendSlotMachineInformations(Player player, SlotMachine slotMachine) {
		if (SlotPlugin.is116) {
			player.sendMessage(ChatContent.translateColorCodes("&#1976d2&l---- &#81c784&l" + Language.translate("slotmachine.informations") + "&#1976d2&l ----"));
			if(slotMachine.getSlotMachineType() == SlotMachineType.ENTITY) {
				player.sendMessage(ChatContent.translateColorCodes("&#1976d2-- &#81c784" + Language.translate("slotmachine.informations.entityuuid") + " : " + ChatContent.RESET + ChatContent.GOLD + ((SlotMachineEntity)slotMachine).getEntityUUID().toString()));
			}
			if(slotMachine.getSlotMachineType() == SlotMachineType.BLOCK) {
				SlotMachineBlock smb = (SlotMachineBlock)slotMachine;
				Block block = smb.getBlock();
				player.sendMessage(ChatContent.translateColorCodes("&#1976d2-- &#81c784" + Language.translate("slotmachine.informations.location") + " : " + ChatContent.RESET + ChatContent.GOLD + block.getWorld().getName() + " | X:" + block.getX() + " Y:" + block.getY() + " Z:" + block.getZ()));
			}
			player.sendMessage(ChatContent.translateColorCodes("&#1976d2-- &#81c784" + Language.translate("slotmachine.informations.machineuuid") + " : " + ChatContent.RESET + ChatContent.GOLD + slotMachine.getMachineUUID().toString()));
			if (SlotPlugin.isCitizensEnabled)
				player.sendMessage(ChatContent.translateColorCodes("&#1976d2-- &#81c784" + Language.translate("slotmachine.informations.citizens") + " : " + ChatContent.RESET + (slotMachine.isCitizensNPC() ? ChatContent.GREEN : ChatContent.RED) + "\u2713"));
			player.sendMessage(ChatContent.translateColorCodes("&#1976d2-- &#81c784" + Language.translate("slotmachine.informations.name") + " : " + ChatContent.RESET + ChatContent.GOLD + slotMachine.getSlotMachineName()));
			player.sendMessage(ChatContent.translateColorCodes("&#1976d2-- &#81c784" + Language.translate("slotmachine.informations.timesUsed") + " : " + ChatContent.RESET + ChatContent.GOLD + slotMachine.getTimesUsed()));
			player.sendMessage(ChatContent.translateColorCodes("&#1976d2-- &#81c784" + Language.translate("slotmachine.informations.permission") + " : " + ChatContent.RESET + ChatContent.GOLD + slotMachine.getGuiPermission()));
			player.sendMessage(ChatContent.translateColorCodes("&#1976d2-- &#81c784" + Language.translate("slotmachine.informations.price") + " : " + ChatContent.RESET + ChatContent.GOLD + slotMachine.getPullPrice()));
			player.sendMessage(ChatContent.translateColorCodes("&#1976d2-- &#81c784" + Language.translate("slotmachine.informations.chancetowin") + " : " + ChatContent.RESET + ChatContent.GOLD + slotMachine.getChanceToWin() * 100 + "%"));
			player.sendMessage(ChatContent.translateColorCodes("&#1976d2-- &#81c784" + Language.translate("slotmachine.informations.spinduration") + " : " + ChatContent.RESET + ChatContent.GOLD + slotMachine.getSecondsBeforePrize() + "s"));
			player.sendMessage(ChatContent.translateColorCodes("&#1976d2-- &#81c784" + Language.translate("slotmachine.informations.winmessage") + " : " + ChatContent.RESET + ChatContent.GOLD + slotMachine.getWinMessage()));
			player.sendMessage(ChatContent.translateColorCodes("&#1976d2-- &#81c784" + Language.translate("slotmachine.informations.lossmessage") + " : " + ChatContent.RESET + ChatContent.GOLD + slotMachine.getLossMessage()));
			player.sendMessage(ChatContent.translateColorCodes("&#1976d2-- &#81c784" + Language.translate("slotmachine.informations.levertitle") + " : " + ChatContent.RESET + ChatContent.GOLD + slotMachine.getLeverTitle()));
			player.sendMessage(ChatContent.translateColorCodes("&#1976d2-- &#81c784" + Language.translate("slotmachine.informations.leverdescription") + " : " + ChatContent.RESET + ChatContent.GOLD + slotMachine.getLeverDescription()));
			player.sendMessage(ChatContent.translateColorCodes("&#1976d2-- &#81c784" + Language.translate("slotmachine.informations.chunkx") + " : " + ChatContent.RESET + ChatContent.GOLD + slotMachine.getChunkX()));
			player.sendMessage(ChatContent.translateColorCodes("&#1976d2-- &#81c784" + Language.translate("slotmachine.informations.chunkz") + " : " + ChatContent.RESET + ChatContent.GOLD + slotMachine.getChunkZ()));
			player.sendMessage(ChatContent.translateColorCodes("&#1976d2-- &#81c784" + Language.translate("slotmachine.informations.items") + " : " + ChatContent.RESET + ChatContent.GOLD + slotMachine.getSlotMachineItems().size() + " items"));
			player.sendMessage(ChatContent.translateColorCodes("&#1976d2&l------------------------------"));
		} else {
			player.sendMessage(ChatContent.DARK_GREEN + ChatContent.BOLD + "---- " + Language.translate("slotmachine.informations") + " :");
			if(slotMachine.getSlotMachineType() == SlotMachineType.ENTITY) {
				player.sendMessage(ChatContent.GREEN + "-- " + Language.translate("slotmachine.informations.entityuuid") + " : " + ChatContent.RESET + ChatContent.GOLD + ((SlotMachineEntity)slotMachine).getEntityUUID().toString());
			}
			if(slotMachine.getSlotMachineType() == SlotMachineType.BLOCK) {
				SlotMachineBlock smb = (SlotMachineBlock)slotMachine;
				Block block = smb.getBlock();
				player.sendMessage(ChatContent.GREEN + "-- " + Language.translate("slotmachine.informations.location") + " : " + ChatContent.RESET + ChatContent.GOLD + block.getWorld().getName() + " | X:" + block.getX() + " Y:" + block.getY() + " Z:" + block.getZ());
			}
			player.sendMessage(ChatContent.GREEN + "-- " + Language.translate("slotmachine.informations.machineuuid") + " : " + ChatContent.RESET + ChatContent.GOLD + slotMachine.getMachineUUID().toString());
			if (SlotPlugin.isCitizensEnabled)
				player.sendMessage(ChatContent.GREEN + "-- " + Language.translate("slotmachine.informations.citizens") + " : " + ChatContent.RESET + (slotMachine.isCitizensNPC() ? ChatContent.GREEN : ChatContent.RED) + "\u2713");
			player.sendMessage(ChatContent.GREEN + "-- " + Language.translate("slotmachine.informations.name") + " : " + ChatContent.RESET + ChatContent.GOLD + slotMachine.getSlotMachineName());
			player.sendMessage(ChatContent.GREEN + "-- " + Language.translate("slotmachine.informations.timesUsed") + " : " + ChatContent.RESET + ChatContent.GOLD + slotMachine.getTimesUsed());
			player.sendMessage(ChatContent.GREEN + "-- " + Language.translate("slotmachine.informations.permission") + " : " + ChatContent.RESET + ChatContent.GOLD + slotMachine.getGuiPermission());
			player.sendMessage(ChatContent.GREEN + "-- " + Language.translate("slotmachine.informations.price") + " : " + ChatContent.RESET + ChatContent.GOLD + slotMachine.getPullPrice());
			player.sendMessage(ChatContent.GREEN + "-- " + Language.translate("slotmachine.informations.chancetowin") + " : " + ChatContent.RESET + ChatContent.GOLD + slotMachine.getChanceToWin() * 100 + "%");
			player.sendMessage(ChatContent.GREEN + "-- " + Language.translate("slotmachine.informations.spinduration") + " : " + ChatContent.RESET + ChatContent.GOLD + slotMachine.getSecondsBeforePrize() + "s");
			player.sendMessage(ChatContent.GREEN + "-- " + Language.translate("slotmachine.informations.winmessage") + " : " + ChatContent.RESET + ChatContent.GOLD + slotMachine.getWinMessage());
			player.sendMessage(ChatContent.GREEN + "-- " + Language.translate("slotmachine.informations.lossmessage") + " : " + ChatContent.RESET + ChatContent.GOLD + slotMachine.getLossMessage());
			player.sendMessage(ChatContent.GREEN + "-- " + Language.translate("slotmachine.informations.levertitle") + " : " + ChatContent.RESET + ChatContent.GOLD + slotMachine.getLeverTitle());
			player.sendMessage(ChatContent.GREEN + "-- " + Language.translate("slotmachine.informations.leverdescription") + " : " + ChatContent.RESET + ChatContent.GOLD + slotMachine.getLeverDescription());
			player.sendMessage(ChatContent.GREEN + "-- " + Language.translate("slotmachine.informations.chunkx") + " : " + ChatContent.RESET + ChatContent.GOLD + slotMachine.getChunkX());
			player.sendMessage(ChatContent.GREEN + "-- " + Language.translate("slotmachine.informations.chunkz") + " : " + ChatContent.RESET + ChatContent.GOLD + slotMachine.getChunkZ());
			player.sendMessage(ChatContent.GREEN + "-- " + Language.translate("slotmachine.informations.items") + " : " + ChatContent.RESET + ChatContent.GOLD + slotMachine.getSlotMachineItems().size() + " items");
			player.sendMessage(ChatContent.DARK_GREEN + ChatContent.BOLD + "----");
		}
	}
}
