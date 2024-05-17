package fr.klemms.slotmachine;

import fr.klemms.slotmachine.Issue.IssueType;
import fr.klemms.slotmachine.exceptioncollector.ExceptionCollector;
import fr.klemms.slotmachine.interraction.MachineInterractionInventory;
import fr.klemms.slotmachine.translation.Language;
import net.citizensnpcs.api.CitizensAPI;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;
import java.util.logging.Level;

public class MachineMethods {

	/*private static ChatColor nextButtonColor = ChatColor.AQUA;

	private static ChatColor getButtonColor() {
		return nextButtonColor == ChatColor.AQUA ? (nextButtonColor = ChatColor.DARK_AQUA) : (nextButtonColor = ChatColor.AQUA);
	}*/

	public static void openmachine(Player player, SlotMachine machine) {
		if (machine instanceof SlotMachineEntity)
			player.setMetadata("slotmachineinteractentity", new FixedMetadataValue(SlotPlugin.pl, "yes"));
		if (machine instanceof SlotMachineBlock)
			player.setMetadata("slotmachineinteractblock", new FixedMetadataValue(SlotPlugin.pl, "yes"));

		Bukkit.getScheduler().scheduleSyncDelayedTask(SlotPlugin.pl, new Runnable() {
			@Override
			public void run() {
				player.removeMetadata("slotmachineinteractentity", SlotPlugin.pl);
				player.removeMetadata("slotmachineinteractblock", SlotPlugin.pl);
			}
		});

		if (machine.getSlotMachineType() == SlotMachineType.BLOCK_LINK) {
			machine = ((SlotMachineBlockLink)machine).getLink();
		} else if (machine.getSlotMachineType() == SlotMachineType.ENTITY_LINK) {
			machine = ((SlotMachineEntityLink)machine).getLink();
		}

		if(machine.getSlotMachineItems().size() > 0) {
			if(machine.getPriceType() == PriceType.GAMEPOINTS && !SlotPlugin.isGamePointsEnabled) {
				player.sendMessage(ChatContent.RED + SlotPlugin.CHAT_PREFIX + Language.translate("slotmachine.access.missinggamepoints"));
				return;
			}
			if(machine.getPriceType() == PriceType.TOKENMANAGER && !SlotPlugin.oldTokenManagerWorks && SlotPlugin.tokenManager == null) {
				player.sendMessage(ChatContent.RED + SlotPlugin.CHAT_PREFIX + Language.translate("slotmachine.access.missingtokenmanager"));
				return;
			}
			if(machine.getPriceType() == PriceType.VOTINGPLUGIN && SlotPlugin.votingPlugin == null) {
				player.sendMessage(ChatContent.RED + SlotPlugin.CHAT_PREFIX + Language.translate("slotmachine.access.missingvotingplugin"));
				return;
			}
			if(machine.getPriceType() == PriceType.MONEY && SlotPlugin.econ == null) {
				player.sendMessage(ChatContent.RED + SlotPlugin.CHAT_PREFIX + Language.translate("slotmachine.access.missingvault"));
				return;
			}

			player.closeInventory();
			machine.openMachine(player, true);
			player.playSound(player.getLocation(), machine.getMachineOpeningSound().getKey(), 1.9f, 1.2f);
		} else {
			player.sendMessage(ChatContent.RED + SlotPlugin.CHAT_PREFIX + Language.translate("slotmachine.access.notsetup"));
		}
	}

	public static void magicWand(Player player, Entity entity, Block block) {
		if (entity != null)
			player.setMetadata("slotmachineinteractentity", new FixedMetadataValue(SlotPlugin.pl, "yes"));
		if (block != null)
			player.setMetadata("slotmachineinteractblock", new FixedMetadataValue(SlotPlugin.pl, "yes"));

		Bukkit.getScheduler().scheduleSyncDelayedTask(SlotPlugin.pl, new Runnable() {
			@Override
			public void run() {
				player.removeMetadata("slotmachineinteractentity", SlotPlugin.pl);
				player.removeMetadata("slotmachineinteractblock", SlotPlugin.pl);
			}
		});


		UUID uuid = entity != null ? entity.getUniqueId() : null;
		if (uuid != null && SlotPlugin.isCitizensEnabled && CitizensAPI.getNPCRegistry().isNPC(entity))
			uuid = CitizensAPI.getNPCRegistry().getNPC(entity).getUniqueId();

		SlotMachine machine = null;
		if (entity != null) {
			machine = SlotMachineEntityLink.getAllSlotMachineByEntityUUID(uuid);
		} else if (block != null) {
			machine = SlotMachineBlockLink.getAllSlotMachineByBlock(block);
		}

		MachineInterractionInventory.manageMachine(player, machine, entity, block, 0);
	}

	public static SlotMachineEntity createSlotMachineEntityLink(Player player, Entity entity, SlotMachine linkTo) {
		UUID uuid = entity.getUniqueId();
		boolean isCitizensNPC = false;

		if (SlotPlugin.isCitizensEnabled && CitizensAPI.getNPCRegistry().isNPC(entity)) {
			uuid = CitizensAPI.getNPCRegistry().getNPC(entity).getUniqueId();
			isCitizensNPC = true;
		}

		if(SlotMachineEntityLink.getAllSlotMachineByEntityUUID(entity.getUniqueId()) == null) {
			if(!isCitizensNPC && entity instanceof LivingEntity) {
				((LivingEntity)entity).setAI(false);
				entity.setSilent(true);
			}
			SlotMachineEntityLink slotMachineEntityLink = new SlotMachineEntityLink(linkTo.getMachineUUID(), entity.getUniqueId());
			slotMachineEntityLink.setCitizensNPC(isCitizensNPC);
			SlotMachineEntityLink.addSlotMachineEntity(slotMachineEntityLink);
			player.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + Language.translate("slotmachine.created").replace("%entityUUID%", uuid.toString()));

			if(SlotPlugin.econ == null) {
				player.sendMessage(ChatContent.BOLD + ChatContent.AQUA + SlotPlugin.CHAT_PREFIX + Language.translate("slotmachine.tokensfallback"));
			}

			SlotPlugin.pl.getLogger().log(Level.INFO, "New SlotMachine (Entity)" + (isCitizensNPC ? " (Citizens NPC)" : "") + " (Created by : " + player.getName() + ") : '" + entity.getType().toString() + "' with UUID '" + uuid.toString() + "' in world '" + entity.getWorld().getName() + "' at '" + entity.getLocation().getX() + " " + entity.getLocation().getY() + " " + entity.getLocation().getZ() + "'");
			slotMachineEntityLink.save();
			return slotMachineEntityLink;
		} else {
			player.sendMessage(ChatContent.RED + ChatContent.BOLD + SlotPlugin.CHAT_PREFIX + Language.translate("slotmachine.alreadyslotmachine"));
			return SlotMachineEntityLink.getAllSlotMachineByEntityUUID(entity.getUniqueId());
		}
	}

	public static SlotMachineEntity createSlotMachineEntity(Player player, Entity entity) {
		UUID uuid = entity.getUniqueId();
		boolean isCitizensNPC = false;

		if (SlotPlugin.isCitizensEnabled && CitizensAPI.getNPCRegistry().isNPC(entity)) {
			uuid = CitizensAPI.getNPCRegistry().getNPC(entity).getUniqueId();
			isCitizensNPC = true;
		}

		if(SlotMachineEntity.getSlotMachineByEntityUUID(uuid) == null) {
			if(!isCitizensNPC && entity instanceof LivingEntity) {
				((LivingEntity)entity).setAI(false);
				entity.setSilent(true);
			}
			SlotMachineEntity slotMachineEntity = new SlotMachineEntity(uuid);
			slotMachineEntity.setCitizensNPC(isCitizensNPC);
			SlotMachineEntity.addSlotMachineEntity(slotMachineEntity);
			player.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + Language.translate("slotmachine.created").replace("%entityUUID%", uuid.toString()));

			if(SlotPlugin.econ == null) {
				player.sendMessage(ChatContent.BOLD + ChatContent.AQUA + SlotPlugin.CHAT_PREFIX + Language.translate("slotmachine.tokensfallback"));
			}


			SlotPlugin.pl.getLogger().log(Level.INFO, "New SlotMachine (Entity)" + (isCitizensNPC ? " (Citizens NPC)" : "") + " (Created by : " + player.getName() + ") : '" + entity.getType().toString() + "' with UUID '" + uuid.toString() + "' in world '" + entity.getWorld().getName() + "' at '" + entity.getLocation().getX() + " " + entity.getLocation().getY() + " " + entity.getLocation().getZ() + "'");
			slotMachineEntity.save();
			return slotMachineEntity;
		} else {
			player.sendMessage(ChatContent.RED + ChatContent.BOLD + SlotPlugin.CHAT_PREFIX + Language.translate("slotmachine.alreadyslotmachine"));
			return SlotMachineEntity.getSlotMachineByEntityUUID(uuid);
		}
	}

	public static SlotMachineBlock createSlotMachineBlockLink(Player player, Block block, SlotMachine linkTo) {
		if(SlotMachineBlockLink.getAllSlotMachineByBlock(block) == null) {
			SlotMachineBlockLink slotMachineBlockLink = new SlotMachineBlockLink(linkTo.getMachineUUID(), block.getX(), block.getY(), block.getZ(), true, block.getWorld().getUID());
			SlotMachineBlockLink.addSlotMachineBlock(slotMachineBlockLink);
			player.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + Language.translate("slotmachine.created.block").replace("%location%", block.getX() + "," + block.getY() + "," + block.getZ()));

			if(SlotPlugin.econ == null) {
				player.sendMessage(ChatContent.BOLD + ChatContent.AQUA + SlotPlugin.CHAT_PREFIX + Language.translate("slotmachine.tokensfallback"));
			}

			SlotPlugin.pl.getLogger().log(Level.INFO, "New SlotMachine (Block) (Created by : " + player.getName() + ") : '" + block.getType().toString() + "' in world '" + block.getWorld().getName() + "' at '" + block.getLocation().getX() + " " + block.getLocation().getY() + " " + block.getLocation().getZ() + "'");
			slotMachineBlockLink.save();
			return slotMachineBlockLink;
		} else {
			player.sendMessage(ChatContent.RED + ChatContent.BOLD + SlotPlugin.CHAT_PREFIX + Language.translate("slotmachine.alreadyslotmachine"));
			return SlotMachineBlockLink.getAllSlotMachineByBlock(block);
		}
	}

	public static SlotMachineBlock createSlotMachineBlock(Player player, Block block) {
		if(SlotMachineBlock.getSlotMachineByBlock(block) == null) {
			SlotMachineBlock slotMachineBlock = new SlotMachineBlock(block.getX(), block.getY(), block.getZ(), true, block.getWorld().getUID());
			SlotMachineBlock.addSlotMachineBlock(slotMachineBlock);
			player.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + Language.translate("slotmachine.created.block").replace("%location%", block.getX() + "," + block.getY() + "," + block.getZ()));

			if(SlotPlugin.econ == null) {
				player.sendMessage(ChatContent.BOLD + ChatContent.AQUA + SlotPlugin.CHAT_PREFIX + Language.translate("slotmachine.tokensfallback"));
			}

			SlotPlugin.pl.getLogger().log(Level.INFO, "New SlotMachine (Block) (Created by : " + player.getName() + ") : '" + block.getType().toString() + "' in world '" + block.getWorld().getName() + "' at '" + block.getLocation().getX() + " " + block.getLocation().getY() + " " + block.getLocation().getZ() + "'");
			slotMachineBlock.save();
			return slotMachineBlock;
		} else {
			player.sendMessage(ChatContent.RED + ChatContent.BOLD + SlotPlugin.CHAT_PREFIX + Language.translate("slotmachine.alreadyslotmachine"));
			return SlotMachineBlock.getSlotMachineByBlock(block);
		}
	}

	public static void removeSlotMachine(Player player, UUID machineUUID) {
		if(SlotMachine.getSlotMachineByUUID(machineUUID) != null) {
			SlotMachine slotMachine = SlotMachine.getSlotMachineByUUID(machineUUID);
			SlotMachine.removeSlotMachine(slotMachine);
			player.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + Language.translate("slotmachine.removed"));
			SlotPlugin.pl.getLogger().log(Level.INFO, Language.translate("slotmachine.removed.logs"));
			try {
				Files.deleteIfExists(SlotPlugin.pl.getDataFolder().toPath().resolve("machines").resolve(slotMachine.getMachineUUID().toString() + ".yml"));
			} catch (IOException e) {
				e.printStackTrace();
				Issue.newIssue(IssueType.MACHINE_REMOVAL_EXCEPTION, e.getMessage(), true);
				ExceptionCollector.sendException(SlotPlugin.pl, e);
			}
			slotMachine.save();
		} else {
			player.sendMessage(ChatContent.RED + ChatContent.BOLD + SlotPlugin.CHAT_PREFIX + Language.translate("slotmachine.notslotmachine"));
		}
	}

	public static void sendSlotMachineInformations(Player player, SlotMachine slotMachine) {
		player.sendMessage(ChatContent.translateColorCodes("&#1976d2&l---- &#81c784&l" + Language.translate("slotmachine.informations") + "&#1976d2&l ----"));
		if(slotMachine.getSlotMachineType() == SlotMachineType.ENTITY || slotMachine.getSlotMachineType() == SlotMachineType.ENTITY_LINK) {
			player.sendMessage(ChatContent.translateColorCodes("&#1976d2-- &#81c784" + Language.translate("slotmachine.informations.entityuuid") + " : " + ChatContent.RESET + ChatContent.GOLD + ((SlotMachineEntity)slotMachine).getEntityUUID().toString()));
		}
		if(slotMachine.getSlotMachineType() == SlotMachineType.BLOCK || slotMachine.getSlotMachineType() == SlotMachineType.BLOCK_LINK) {
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
		player.sendMessage(ChatContent.translateColorCodes("&#1976d2-- &#81c784" + Language.translate("slotmachine.informations.items") + " : " + ChatContent.RESET + ChatContent.GOLD + slotMachine.getSlotMachineItems().size() + " items"));
		player.sendMessage(ChatContent.translateColorCodes("&#1976d2&l------------------------------"));

		player.spigot().sendMessage(new ComponentBuilder(" -- Click to copy this machine's UUID to your clipboard --")
				.color(ChatColor.GOLD).italic(true)
				.event(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, slotMachine.getMachineUUID().toString()))
				.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click to copy '" + slotMachine.getMachineUUID().toString() + "' to your clipboard").create()))
				.create());
	}
}
