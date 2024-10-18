package fr.klemms.slotmachine.interraction;

import fr.klemms.slotmachine.*;
import fr.klemms.slotmachine.clipboard.ClipboardContent;
import fr.klemms.slotmachine.clipboard.Clipboards;
import fr.klemms.slotmachine.clipboard.PasteCallback;
import fr.klemms.slotmachine.exceptioncollector.ExceptionCollector;
import fr.klemms.slotmachine.fr.minuskube.inv.ClickableItem;
import fr.klemms.slotmachine.fr.minuskube.inv.SmartInventory;
import fr.klemms.slotmachine.fr.minuskube.inv.content.InventoryContents;
import fr.klemms.slotmachine.fr.minuskube.inv.content.Pagination;
import fr.klemms.slotmachine.fr.minuskube.inv.content.SlotIterator.Type;
import fr.klemms.slotmachine.interraction.machinemenu.MenuItem;
import fr.klemms.slotmachine.interraction.machinemenu.MenuItemChangeLeverDescription;
import fr.klemms.slotmachine.interraction.machinemenu.MenuItemChangeLeverName;
import fr.klemms.slotmachine.interraction.machinemenu.MenuState;
import fr.klemms.slotmachine.interraction.providers.CopyPastableProvider;
import fr.klemms.slotmachine.placeholders.Variables;
import fr.klemms.slotmachine.tokens.Token;
import fr.klemms.slotmachine.tokens.TokenSelectionListener;
import fr.klemms.slotmachine.tokens.TokensInventory;
import fr.klemms.slotmachine.translation.Language;
import fr.klemms.slotmachine.utils.*;
import net.citizensnpcs.api.CitizensAPI;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

public class MachineInterractionInventory {

	public static void manageMachine(Player player, SlotMachine machine, Entity entity, Block block, int page) {
		if (entity == null && block == null) {
			player.sendMessage(ChatContent.RED + SlotPlugin.CHAT_PREFIX + "Both ENTITY and BLOCK variables are null when entering manageMachine. This is not normal");
			SlotPlugin.pl.getLogger().log(Level.SEVERE, "Both ENTITY and BLOCK variables are null when entering manageMachine. This is not normal");
			return;
		}

		if (entity instanceof Player && !(SlotPlugin.isCitizensEnabled && CitizensAPI.getNPCRegistry().isNPC(entity))) {
			player.sendMessage(ChatContent.RED + SlotPlugin.CHAT_PREFIX + "You can't create a machine on a player");
			return;
		}

		LivingEntity livingEntity = entity instanceof LivingEntity ? (LivingEntity) entity : null;

		SmartInventory inv = SmartInventory.builder()
				.manager(SlotPlugin.invManager)
				.id(machine == null ? "managment" : machine.getMachineUUID().toString())
				.title(machine == null ? ("Slot Machine on " + (entity != null ? entity.getType().toString() : block.getType().toString())) : machine.getSlotMachineName())
				.size(6, 9)
				.closeable(true)
				.provider(new CopyPastableProvider() {

					@Override
					public void init(Player player, InventoryContents contents) {
						Pagination pagination = contents.pagination();

						List<ClickableItem> items = new ArrayList<ClickableItem>();

						MenuState state = new MenuState() {
							@Override
							public void reloadPage() {
								manageMachine(player, machine, entity, block, pagination.getPage());
							}
						};

						if (machine == null) {
							List<String> createMachineLore = new ArrayList<String>();
							createMachineLore.add(ChatContent.AQUA + ChatContent.ITALIC + "Create a Slot Machine from this " + (entity == null ? "block" : "entity"));
							createMachineLore.add("");
							if (entity != null) {
								createMachineLore.add(ChatContent.AQUA + ChatContent.ITALIC + "This entity will :");
								createMachineLore.add(ChatContent.GRAY + ChatContent.ITALIC + " - Have its AI disabled");
								createMachineLore.add(ChatContent.GRAY + ChatContent.ITALIC + " - Be made Silent");
								createMachineLore.add(ChatContent.GRAY + ChatContent.ITALIC + " - Be invincible");
								createMachineLore.add(ChatContent.GRAY + ChatContent.ITALIC + " - Have its default interractions disabled");
								createMachineLore.add("");
								createMachineLore.add(ChatContent.AQUA + ChatContent.ITALIC + "Please use /tpmachine to move this machine");
								createMachineLore.add(ChatContent.AQUA + ChatContent.ITALIC + "/openmachine is available if you don't want to");
								createMachineLore.add(ChatContent.AQUA + ChatContent.ITALIC + "right click this machine");
							} else if (block != null) {
								createMachineLore.add(ChatContent.AQUA + ChatContent.ITALIC + "This block will :");
								createMachineLore.add(ChatContent.GRAY + ChatContent.ITALIC + " - Be unbreakable by players");
								createMachineLore.add(ChatContent.GRAY + ChatContent.ITALIC + " - Be breakable using commands");
								createMachineLore.add("");
								createMachineLore.add(ChatContent.AQUA + ChatContent.ITALIC + "You can replace or break this block,");
								createMachineLore.add(ChatContent.AQUA + ChatContent.ITALIC + "it won't remove the machine");
								createMachineLore.add(ChatContent.AQUA + ChatContent.ITALIC + "/openmachine is available if you don't want to");
								createMachineLore.add(ChatContent.AQUA + ChatContent.ITALIC + "right click this machine");
							}
							items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.PLUS_SIGN), ChatContent.GOLD + "Create Slot Machine"), createMachineLore), event -> {
								player.closeInventory();
								player.playSound(player.getLocation(), Sound.BLOCK_END_PORTAL_SPAWN, 0.3F, 1.3F);
								if (entity != null) {
									MachineMethods.createSlotMachineEntity(player, entity);
								} else if (block != null) {
									MachineMethods.createSlotMachineBlock(player, block);
								}
							}));

							List<String> createLinkLore = Arrays.asList(
									ChatContent.AQUA + ChatContent.ITALIC + "Create a link to another Slot Machine from this " + (entity == null ? "block" : "entity"),
									"",
									ChatContent.AQUA + "A link will inherit all properties",
									ChatContent.AQUA + "of the original Slot Machine",
									"",
									ChatContent.AQUA + "You won't be able to edit this Slot Machine",
									"",
									ChatContent.AQUA + "All changes you do on the original Slot Machine",
									ChatContent.AQUA + "will affect this one"
							);
							items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.EARTH), ChatContent.GOLD + "Create a link to another Slot Machine"), createLinkLore), event -> {
								MachineListInventory.openMachineList(player, 0, new MachineListCallback() {
									@Override
									public void callback(SlotMachine pickedSlotMachine) {
										player.closeInventory();
										player.playSound(player.getLocation(), Sound.BLOCK_END_PORTAL_SPAWN, 0.3F, 1.3F);
										if (entity != null) {
											MachineMethods.createSlotMachineEntityLink(player, entity, pickedSlotMachine);
										} else if (block != null) {
											MachineMethods.createSlotMachineBlockLink(player, block, pickedSlotMachine);
										}
									}
								});
							}));
						} else if (machine.getSlotMachineType() == SlotMachineType.ENTITY_LINK || machine.getSlotMachineType() == SlotMachineType.BLOCK_LINK) {
							items.add(ClickableItem.empty(
									ItemStackUtil.setItemStackLore(
											ItemStackUtil.changeItemStackName(
													new ItemStack(PlayerHeadsUtil.INFOS), ChatContent.GOLD + "Slot Machine Informations"),
											Arrays.asList(
													ChatContent.AQUA + "Machine UUID : " + ChatContent.GRAY + ChatContent.ITALIC + machine.getMachineUUID().toString()
											)
									)));

							items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.EARTH), ChatContent.GOLD + "Change Link"),
									Arrays.asList(
											ChatContent.AQUA + "Change the original Slot Machine",
											ChatContent.AQUA + "this machine inherits from"
									)), event -> {
								if (event.isLeftClick()) {
									player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);

									MachineListInventory.openMachineList(player, 0, new MachineListCallback() {
										@Override
										public void callback(SlotMachine pickedSlotMachine) {
											player.closeInventory();
											player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 0.3F, 1.6F);
											if (machine.getSlotMachineType() == SlotMachineType.ENTITY_LINK) {
												((SlotMachineEntityLink)machine).setLinkTo(pickedSlotMachine.getMachineUUID());
												machine.save();
											} else if(machine.getSlotMachineType() == SlotMachineType.BLOCK_LINK) {
												((SlotMachineBlockLink)machine).setLinkTo(pickedSlotMachine.getMachineUUID());
												machine.save();
											}
											player.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + "Successfully changed link");
										}
									});
								}
							}));

							if (machine.getSlotMachineType() == SlotMachineType.BLOCK_LINK && block != null) {
								SlotMachineBlockLink machineBlock = ((SlotMachineBlockLink) machine);
								if (machineBlock.isLocked())
									items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(Material.STONE, 1), ChatContent.GOLD + "Make Breakable"), Arrays.asList(
											ChatContent.AQUA + ChatContent.ITALIC + "Make this block breakable",
											ChatContent.AQUA + ChatContent.ITALIC + "You will be able to break it"
									)), event -> {
										player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
										machineBlock.setLocked(false);
										player.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + Language.translate("command.slotmachineaction.breakable"));
										machine.save();
										manageMachine(player, machine, entity, block, pagination.getPage());
									}));
								else
									items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(Material.BEDROCK, 1), ChatContent.GOLD + "Make Unbreakable"), Arrays.asList(
											ChatContent.AQUA + ChatContent.ITALIC + "Make this block unbreakable",
											ChatContent.AQUA + ChatContent.ITALIC + "No one will be able to break this block"
									)), event -> {
										player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
										machineBlock.setLocked(true);
										player.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + Language.translate("command.slotmachineaction.unbreakable"));
										machine.save();
										manageMachine(player, machine, entity, block, pagination.getPage());
									}));
							}
						} else {
							items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.INFOS), ChatContent.GOLD + "Machine Informations"), Arrays.asList(
									ChatContent.AQUA + ChatContent.ITALIC + "Display this machine's",
									ChatContent.AQUA + ChatContent.ITALIC + "informations in chat"
									)), event -> {
										player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
										player.closeInventory();
										MachineMethods.sendSlotMachineInformations(player, machine);
							}));
							items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(Material.NAME_TAG, 1), ChatContent.GOLD + "Rename"), Arrays.asList(
									ChatContent.AQUA + ChatContent.ITALIC + "Rename this machine",
									"",
									ChatContent.AQUA + ChatContent.ITALIC + "Current name :",
									ChatContent.RESET + machine.getSlotMachineName()
									)), event -> {
										player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
										player.closeInventory();
										PlayerUtil.resetPlayerData(player);
										player.setMetadata("slotmachine_changename", new FixedMetadataValue(SlotPlugin.pl, machine.getMachineUUID().toString()));
										player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("command.slotmachineaction.rename"));
										player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + "Type \"cancel\" to cancel");
							}));
							items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(Material.CHEST, 1), ChatContent.GOLD + "Item Editor & Statistics"), Arrays.asList(
									ChatContent.AQUA + ChatContent.ITALIC + " - Add and remove items",
									ChatContent.AQUA + ChatContent.ITALIC + " - Edit items' weights",
									ChatContent.AQUA + ChatContent.ITALIC + " - View statistics"
									)), event -> {
										player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
										MachineItemsInterractionInventory.manageItems(player, machine, 0);
							}));
							items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(Material.GOLD_BLOCK, 1), ChatContent.GOLD + "Change Permission"), Arrays.asList(
									ChatContent.AQUA + ChatContent.ITALIC + "Change this machine's",
									ChatContent.AQUA + ChatContent.ITALIC + "permission",
									"",
									ChatContent.AQUA + ChatContent.ITALIC + "Current permission : ",
									ChatContent.RESET + machine.getGuiPermission()
									)), event -> {
										player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
										player.closeInventory();
										PlayerUtil.resetPlayerData(player);
										player.setMetadata("slotmachine_changepermission", new FixedMetadataValue(SlotPlugin.pl, machine.getMachineUUID().toString()));
										player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("command.slotmachineaction.changepermission"));
										player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + "Type \"cancel\" to cancel");
							}));
							items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.PLAY_BUTTON), ChatContent.GOLD + "Change play mode"), Arrays.asList(
									ChatContent.AQUA + ChatContent.ITALIC + "Play Mode affects the way players",
									ChatContent.AQUA + ChatContent.ITALIC + "interact with this machine, it can",
									ChatContent.AQUA + ChatContent.ITALIC + "restrict them from playing while other",
									ChatContent.AQUA + ChatContent.ITALIC + "players play and more...",
									"",
									ChatContent.AQUA + ChatContent.ITALIC + "Click to change",
									"",
									ChatContent.AQUA + ChatContent.ITALIC + "Current Play Mode :",
									ChatContent.DARK_PURPLE + ChatContent.ITALIC + machine.getPlayMode().title
									)), event -> {
										player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
										PlayModePick.pickPlayMode(player, machine, 0);
							}));
							if (machine.getVisualType() != VisualType.SLOTMACHINE)
								items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(Material.BRICKS, 1), ChatContent.GOLD + "Change Layout"), Arrays.asList(
										ChatContent.AQUA + ChatContent.ITALIC + "Change this machine's layout to",
										ChatContent.GREEN + ChatContent.ITALIC + "Slot Machine (default)",
										"",
										ChatContent.AQUA + ChatContent.ITALIC + "Current layout :",
										ChatContent.RESET + machine.getVisualType().name
										)), event -> {
											player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
											machine.setVisualType(VisualType.SLOTMACHINE);
											player.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + Language.translate("command.slotmachineaction.slotmachine"));
											machine.save();
											manageMachine(player, machine, entity, block, pagination.getPage());
								}));
							if (machine.getVisualType() != VisualType.CSGOWHEEL)
								items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(Material.STONE_BRICKS, 1), ChatContent.GOLD + "Change Layout"), Arrays.asList(
										ChatContent.AQUA + ChatContent.ITALIC + "Change this machine's layout to",
										ChatContent.GREEN + ChatContent.ITALIC + "CS-GO Wheel",
										"",
										ChatContent.AQUA + ChatContent.ITALIC + "Current layout :",
										ChatContent.RESET + machine.getVisualType().name
										)), event -> {
											player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
											machine.setVisualType(VisualType.CSGOWHEEL);
											player.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + Language.translate("command.slotmachineaction.csgowheel"));
											machine.save();
											manageMachine(player, machine, entity, block, pagination.getPage());
								}));
							if (machine.getVisualType() != VisualType.CSGOWHEEL_VERTICAL)
								items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(Material.END_STONE_BRICKS, 1), ChatContent.GOLD + "Change Layout"), Arrays.asList(
										ChatContent.AQUA + ChatContent.ITALIC + "Change this machine's layout to",
										ChatContent.GREEN + ChatContent.ITALIC + "CS-GO Wheel (vertical)",
										"",
										ChatContent.AQUA + ChatContent.ITALIC + "Current layout :",
										ChatContent.RESET + machine.getVisualType().name
										)), event -> {
											player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
											machine.setVisualType(VisualType.CSGOWHEEL_VERTICAL);
											player.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + Language.translate("command.slotmachineaction.csgowheelvertical"));
											machine.save();
											manageMachine(player, machine, entity, block, pagination.getPage());
								}));
							if (machine.getPriceType() != PriceType.TOKEN)
								items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(Material.GOLD_NUGGET, 1), ChatContent.GOLD + "Change Payment"), Arrays.asList(
										ChatContent.AQUA + ChatContent.ITALIC + "Change this machine's",
										ChatContent.AQUA + ChatContent.ITALIC + "payment to",
										ChatContent.GREEN + ChatContent.ITALIC + "Tokens",
										"",
										ChatContent.AQUA + ChatContent.ITALIC + "Current payment :",
										ChatContent.RESET + machine.getPriceType().name
										)), event -> {
											player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
											machine.setPriceType(PriceType.TOKEN);
											player.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + Language.translate("command.slotmachineaction.paymenttokens"));
											machine.save();
											manageMachine(player, machine, entity, block, pagination.getPage());
								}));
							if (machine.getPriceType() != PriceType.MONEY)
								if (SlotPlugin.econ != null) {
									items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(Material.GOLD_INGOT, 1), ChatContent.GOLD + "Change Payment"), Arrays.asList(
											ChatContent.AQUA + ChatContent.ITALIC + "Change this machine's",
											ChatContent.AQUA + ChatContent.ITALIC + "payment to",
											ChatContent.GREEN + ChatContent.ITALIC + "Money",
											"",
											ChatContent.AQUA + ChatContent.ITALIC + "Current payment :",
											ChatContent.RESET + machine.getPriceType().name
											)), event -> {
												player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
												machine.setPriceType(PriceType.MONEY);
												player.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + Language.translate("command.slotmachineaction.paymentmoney"));
												machine.save();
												manageMachine(player, machine, entity, block, pagination.getPage());
									}));
								} else {
									items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(Material.GOLD_INGOT, 1), ChatContent.GOLD + "Change Payment"), Arrays.asList(
											ChatContent.RED + ChatContent.ITALIC + "You need to install VAULT",
											ChatContent.RED + ChatContent.ITALIC + "plugin to pay with Money",
											"",
											ChatContent.AQUA + ChatContent.ITALIC + "Click to show Vault's download link"
											)), event -> {
												player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
												player.closeInventory();
												player.spigot().sendMessage(new ComponentBuilder("https://dev.bukkit.org/projects/vault").color(ChatColor.AQUA).event(new ClickEvent(Action.OPEN_URL, "https://dev.bukkit.org/projects/vault")).create());
									}));
								}
							if (machine.getPriceType() != PriceType.TOKENMANAGER && (SlotPlugin.oldTokenManagerWorks || SlotPlugin.tokenManager != null))
								items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(Material.IRON_NUGGET, 1), ChatContent.GOLD + "Change Payment"), Arrays.asList(
										ChatContent.AQUA + ChatContent.ITALIC + "Change this machine's",
										ChatContent.AQUA + ChatContent.ITALIC + "payment to",
										ChatContent.GREEN + ChatContent.ITALIC + "Tokens from TokenManager",
										"",
										ChatContent.AQUA + ChatContent.ITALIC + "Current payment :",
										ChatContent.RESET + machine.getPriceType().name
										)), event -> {
											player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
											machine.setPriceType(PriceType.TOKENMANAGER);
											player.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + Language.translate("command.slotmachineaction.paymenttokenmanager"));
											machine.save();
											manageMachine(player, machine, entity, block, pagination.getPage());
								}));
							if (machine.getPriceType() != PriceType.GAMEPOINTS && SlotPlugin.isGamePointsEnabled)
								items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(Material.GHAST_TEAR, 1), ChatContent.GOLD + "Change Payment"), Arrays.asList(
										ChatContent.AQUA + ChatContent.ITALIC + "Change this machine's",
										ChatContent.AQUA + ChatContent.ITALIC + "payment to",
										ChatContent.GREEN + ChatContent.ITALIC + "Game Points from GamePoints",
										"",
										ChatContent.AQUA + ChatContent.ITALIC + "Current payment :",
										ChatContent.RESET + machine.getPriceType().name
										)), event -> {
											player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
											machine.setPriceType(PriceType.GAMEPOINTS);
											player.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + Language.translate("command.slotmachineaction.paymentgamepoints"));
											machine.save();
											manageMachine(player, machine, entity, block, pagination.getPage());
								}));

							if (machine.getPriceType() != PriceType.PLAYERPOINTS && SlotPlugin.playerPointsAPI != null)
								items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(Material.GHAST_TEAR, 1), ChatContent.GOLD + "Change Payment"), Arrays.asList(
										ChatContent.AQUA + ChatContent.ITALIC + "Change this machine's",
										ChatContent.AQUA + ChatContent.ITALIC + "payment to",
										ChatContent.GREEN + ChatContent.ITALIC + "Player Points from PlayerPoints",
										"",
										ChatContent.AQUA + ChatContent.ITALIC + "Current payment :",
										ChatContent.RESET + machine.getPriceType().name
								)), event -> {
									player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
									machine.setPriceType(PriceType.PLAYERPOINTS);
									player.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + "Payment changed to PLAYER POINTS for this Slot Machine");
									machine.save();
									manageMachine(player, machine, entity, block, pagination.getPage());
								}));

							if (machine.getPriceType() != PriceType.VOTINGPLUGIN && SlotPlugin.votingPlugin != null)
								items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.THUMBS_UP), ChatContent.GOLD + "VotingPlugin"), Arrays.asList(
										ChatContent.AQUA + ChatContent.ITALIC + "Change this machine's",
										ChatContent.AQUA + ChatContent.ITALIC + "payment to",
										ChatContent.GREEN + ChatContent.ITALIC + "VotingPlugin",
										"",
										ChatContent.AQUA + ChatContent.ITALIC + "Current payment :",
										ChatContent.RESET + machine.getPriceType().name
										)), event -> {
											player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
											machine.setPriceType(PriceType.VOTINGPLUGIN);
											player.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + "This machine will now use VotingPlugin as a currency");
											machine.save();
											manageMachine(player, machine, entity, block, pagination.getPage());
								}));
							if (machine.getPriceType() != PriceType.EXPERIENCE)
								items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(Material.EXPERIENCE_BOTTLE, 1), ChatContent.GOLD + "Change Payment"), Arrays.asList(
										ChatContent.AQUA + ChatContent.ITALIC + "Change this machine's",
										ChatContent.AQUA + ChatContent.ITALIC + "payment to",
										ChatContent.GREEN + ChatContent.ITALIC + "Experience levels",
										"",
										ChatContent.AQUA + ChatContent.ITALIC + "Current payment :",
										ChatContent.RESET + machine.getPriceType().name
										)), event -> {
											player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
											machine.setPriceType(PriceType.EXPERIENCE);
											player.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + Language.translate("command.slotmachineaction.paymentexperience"));
											machine.save();
											manageMachine(player, machine, entity, block, pagination.getPage());
								}));
							if (machine.getPriceType() == PriceType.TOKEN)
								items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(ItemStackUtil.addGlow(new ItemStack(machine.getToken().getType(), 1)), ChatContent.GOLD + "Change Token"), Arrays.asList(
										ChatContent.AQUA + ChatContent.ITALIC + "Change this machine's Token",
										ChatContent.AQUA + ChatContent.ITALIC + "Players will need to use this token",
										ChatContent.AQUA + ChatContent.ITALIC + "to play",
										"",
										ChatContent.AQUA + ChatContent.ITALIC + "Current Token :",
										ChatContent.RESET + machine.getTokenIdentifier()
										)), event -> {
											player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
											TokensInventory.showManagementScreen(player, 0, "Pick this machine's Token", "Left click to choose this Token", new TokenSelectionListener() {

												@Override
												public void callback(Player player, Token token) {
													player.closeInventory();
													player.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + "The Token for this Slot Machine has been changed");
													machine.setTokenIdentifier(token.identifier);
													machine.save();
												}

											}, false);
								}));
							items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(Material.DIAMOND, 1), ChatContent.GOLD + "Change Price"), Arrays.asList(
									ChatContent.AQUA + ChatContent.ITALIC + "Change the price to pay",
									ChatContent.AQUA + ChatContent.ITALIC + "to use this machine",
									"",
									ChatContent.AQUA + ChatContent.ITALIC + "Current price :",
									ChatContent.RESET + machine.getPullPrice()
									)), event -> {
										player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
										player.closeInventory();
										PlayerUtil.resetPlayerData(player);
										player.setMetadata("slotmachine_changeprice", new FixedMetadataValue(SlotPlugin.pl, machine.getMachineUUID().toString()));
										player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("command.slotmachineaction.changeprice") + " :");
										player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + "Type \"cancel\" to cancel");
							}));
							items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(machine.isAffectedByLuck() ? new ItemStack(Material.GLASS_BOTTLE, 1) : PotionUtil.makePotion(PotionEffectType.LUCK, 1200, 0, Color.GREEN), ChatContent.GOLD + "Luck Effect"), Arrays.asList(
									ChatContent.AQUA + ChatContent.ITALIC + "Should this machine's chance to win be",
									ChatContent.AQUA + ChatContent.ITALIC + "affected by Minecraft's luck potion effect",
									"",
									ChatContent.AQUA + ChatContent.ITALIC + "Current effect (click to switch) :",
									ChatContent.RESET + (machine.isAffectedByLuck() ? ChatContent.GREEN + "Affected by luck" : ChatContent.RED + "Not affected by luck"),
									"",
									ChatContent.AQUA + ChatContent.ITALIC + "You can change luck's effect on",
									ChatContent.AQUA + ChatContent.ITALIC + "machines in Slot Machine's config file",
									"",
									ChatContent.AQUA + ChatContent.ITALIC + "Current config :",
									ChatContent.GREEN + "Luck : " + (Config.luckLevelToPercentConversion >= 0 ? "+" : "-") + Math.abs(Config.luckLevelToPercentConversion) + "% chance per level",
									ChatContent.RED + "Bad Luck : " + (Config.badLuckLevelToPercentConversion >= 0 ? "+" : "-") + Math.abs(Config.badLuckLevelToPercentConversion) + "% chance per level"
									)), event -> {
										player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
										machine.setAffectedByLuck(!machine.isAffectedByLuck());
										player.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + "Successfully switched luck effect");
										machine.save();
										manageMachine(player, machine, entity, block, pagination.getPage());
							}));
							items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(Material.LARGE_FERN, 1), ChatContent.GOLD + "Change Chance to Win"), Arrays.asList(
									ChatContent.AQUA + ChatContent.ITALIC + "Change the chance players",
									ChatContent.AQUA + ChatContent.ITALIC + "have to win",
									"",
									ChatContent.AQUA + ChatContent.ITALIC + "Current chance :",
									ChatContent.RESET + (machine.getChanceToWin() * 100) + "%"
									)), event -> {
										player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
										player.closeInventory();
										PlayerUtil.resetPlayerData(player);
										player.setMetadata("slotmachine_changechance", new FixedMetadataValue(SlotPlugin.pl, machine.getMachineUUID().toString()));
										player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("command.slotmachineaction.chancetowin") + " :");
										player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + "Type \"cancel\" to cancel");
							}));
							items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(Material.CLOCK, 1), ChatContent.GOLD + "Change Play Duration"), Arrays.asList(
									ChatContent.AQUA + ChatContent.ITALIC + "Change how much time the items",
									ChatContent.AQUA + ChatContent.ITALIC + "in the machine are 'spinning'",
									ChatContent.AQUA + ChatContent.ITALIC + "",
									ChatContent.GRAY + "Note : This is accurate only when",
									ChatContent.GRAY + "using default spin-speed (6)",
									"",
									ChatContent.AQUA + ChatContent.ITALIC + "Current duration (min 1s) :",
									ChatContent.RESET + machine.getSecondsBeforePrize() + "s"
									)), event -> {
										player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
										player.closeInventory();
										PlayerUtil.resetPlayerData(player);
										player.setMetadata("slotmachine_changeduration", new FixedMetadataValue(SlotPlugin.pl, machine.getMachineUUID().toString()));
										player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("command.slotmachineaction.spinduration").replace("%spinDuration%", String.valueOf(machine.getSecondsBeforePrize())));
										player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + "Type \"cancel\" to cancel");
							}));
							items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(Material.GREEN_BANNER, 1), ChatContent.GOLD + "Change Win Message"), Arrays.asList(
									ChatContent.AQUA + ChatContent.ITALIC + "Change the message players",
									ChatContent.AQUA + ChatContent.ITALIC + "will see when winning",
									"",
									ChatContent.AQUA + ChatContent.ITALIC + "Current message :",
									ChatContent.RESET + ChatContent.translateColorCodes(machine.getFinalWinMessage())
									)), event -> {
										player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
										player.closeInventory();
										PlayerUtil.resetPlayerData(player);
										player.setMetadata("slotmachine_setwinmessage", new FixedMetadataValue(SlotPlugin.pl, machine.getMachineUUID().toString()));
										player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("command.slotmachineaction.winmessage").replace("%winMessage%", ChatContent.RESET + machine.getWinMessage() + ChatContent.DARK_PURPLE + ChatContent.BOLD));
										player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + "Type \"null\" for no message");
										player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + "Type \"reset\" to reset to default message");
										player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + "Type \"cancel\" to cancel");
							}));
							items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(Material.RED_BANNER, 1), ChatContent.GOLD + "Change Loss Message"), Arrays.asList(
									ChatContent.AQUA + ChatContent.ITALIC + "Change the message players",
									ChatContent.AQUA + ChatContent.ITALIC + "will see when losing",
									"",
									ChatContent.AQUA + ChatContent.ITALIC + "Current message :",
									ChatContent.RESET + ChatContent.translateColorCodes(machine.getFinalLossMessage())
									)), event -> {
										player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
										player.closeInventory();
										PlayerUtil.resetPlayerData(player);
										player.setMetadata("slotmachine_setlossmessage", new FixedMetadataValue(SlotPlugin.pl, machine.getMachineUUID().toString()));
										player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("command.slotmachineaction.lossmessage").replace("%lossMessage%", ChatContent.RESET + machine.getLossMessage() + ChatContent.DARK_PURPLE + ChatContent.BOLD));
										player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + "Type \"null\" for no message");
										player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + "Type \"reset\" to reset to default message");
										player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + "Type \"cancel\" to cancel");
							}));
							items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(Material.OAK_SIGN, 1), ChatContent.GOLD + "Display Won Item In Chat"), Arrays.asList(
									ChatContent.AQUA + ChatContent.ITALIC + "Should we display the name of the",
									ChatContent.AQUA + ChatContent.ITALIC + "item the player got.",
									ChatContent.AQUA + ChatContent.ITALIC + "Click to toggle",
									"",
									ChatContent.AQUA + ChatContent.ITALIC + "Current : " + (machine.isDisplayWonItemInChat() ? ChatContent.GREEN + Language.translate("basic.yes") : ChatContent.RED + Language.translate("basic.no"))
									)), event -> {
										player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
										machine.setDisplayWonItemInChat(!machine.isDisplayWonItemInChat());
										machine.save();
										player.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + (machine.isDisplayWonItemInChat() ? "Enabled" : "Disabled") + " item name in chat");
										manageMachine(player, machine, entity, block, pagination.getPage());
							}));
							items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(machine.shouldBroadcastWonItem() ? ItemStackUtil.addGlow(new ItemStack(Material.BELL, 1)) : new ItemStack(Material.BELL, 1), ChatContent.GOLD + "Broadcast won item to all players"), Arrays.asList(
									ChatContent.AQUA + ChatContent.ITALIC + "Should the won player and the won",
									ChatContent.AQUA + ChatContent.ITALIC + "item be broadcasted to all players ",
									ChatContent.AQUA + ChatContent.ITALIC + "on the server.",
									ChatContent.AQUA + ChatContent.ITALIC + "Click to toggle",
									"",
									ChatContent.AQUA + ChatContent.ITALIC + "Current : " + (machine.shouldBroadcastWonItem() ? ChatContent.GREEN + Language.translate("basic.yes") : ChatContent.RED + Language.translate("basic.no"))
							)), event -> {
								player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
								machine.setBroadcastWonItem(!machine.shouldBroadcastWonItem());
								machine.save();
								player.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + (machine.shouldBroadcastWonItem() ? "Enabled" : "Disabled") + " broadcasting to all players");
								manageMachine(player, machine, entity, block, pagination.getPage());
							}));

							items.add(MenuItem.getMenuItem(machine, player, new MenuItemChangeLeverName(), state));
							items.add(MenuItem.getMenuItem(machine, player, new MenuItemChangeLeverDescription(), state));

							/*items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(ItemStackUtil.addGlow(new ItemStack(Material.TRIPWIRE_HOOK, 1)), ChatContent.GOLD + "Change Lever Name"), Arrays.asList(
									ChatContent.AQUA + ChatContent.ITALIC + "Change the lever's name",
									"",
									ChatContent.RED + "Right Click" + ChatContent.AQUA + ChatContent.ITALIC + " to reset to ",
									ChatContent.AQUA + ChatContent.ITALIC + "default name and description",
									"",
									ChatContent.AQUA + ChatContent.ITALIC + "Current name :",
									ChatContent.RESET + Variables.getFormattedString(machine.getLeverTitle(), player, machine)
									)), event -> {
										player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
										if (event.isLeftClick()) {
											player.closeInventory();
											PlayerUtil.resetPlayerData(player);
											player.setMetadata("slotmachine_setlevertitle", new FixedMetadataValue(SlotPlugin.pl, machine.getMachineUUID().toString()));
											player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("command.slotmachineaction.levertitle") + " :");
											for(int a = 0; a < Variables.values().length; a++) {
												player.sendMessage(ChatContent.AQUA + " - $" + Variables.values()[a].variableName + ChatContent.DARK_AQUA + ChatContent.ITALIC + " - " + Language.translate(Variables.values()[a].variableDescription));
											}
											player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("command.slotmachineaction.placeholderAPI"));

											BaseComponent[] currentText = new ComponentBuilder(Language.translate("command.slotmachineaction.levertitle.current") + " ")
													.color(ChatColor.DARK_PURPLE)
													.bold(true)
													.append(new ComponentBuilder(machine.getLeverTitle())
															.event(new ClickEvent(Action.SUGGEST_COMMAND, machine.getLeverTitle()))
															.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(new ComponentBuilder("Click to fill your chat box with this").create())))
															.bold(false)
															.italic(false)
															.color(ChatColor.WHITE)
															.create())
													.create();
											player.spigot().sendMessage(currentText);

											player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + "Type \"cancel\" to cancel");
										} else if (event.isRightClick()) {
											player.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + "Successfully reset to default");
											machine.setLeverCustom(false);
											machine.setPriceType(machine.getPriceType());
											machine.save();
											manageMachine(player, machine, entity, block, pagination.getPage());
										}
							}));*/


							List<String> leverDescLore = new ArrayList<String>();
							leverDescLore.add(ChatContent.AQUA + ChatContent.ITALIC + "Change the lever's description");
							leverDescLore.add("");
							leverDescLore.add(ChatContent.RED + "Right Click" + ChatContent.AQUA + ChatContent.ITALIC + " to reset to ");
							leverDescLore.add(ChatContent.AQUA + ChatContent.ITALIC + "default name and description");
							leverDescLore.add("");
							leverDescLore.add(ChatContent.AQUA + ChatContent.ITALIC + "Current description :");
							leverDescLore.addAll(Variables.getFormattedStrings(machine.getLeverDescription(), player, machine));
							items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(Material.TRIPWIRE_HOOK, 1), ChatContent.GOLD + "Change Lever Description"), leverDescLore), event -> {
								player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
								if (event.isLeftClick()) {
									player.closeInventory();
									PlayerUtil.resetPlayerData(player);
									player.setMetadata("slotmachine_setleverdescription", new FixedMetadataValue(SlotPlugin.pl, machine.getMachineUUID().toString()));
									player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("command.slotmachineaction.leverdescription") + " :");
									for(int a = 0; a < Variables.values().length; a++) {
										player.sendMessage(ChatContent.AQUA + " - $" + Variables.values()[a].variableName + ChatContent.DARK_AQUA + ChatContent.ITALIC + " - " + Language.translate(Variables.values()[a].variableDescription));
									}
									player.sendMessage(ChatContent.AQUA + " - $newline" + ChatContent.DARK_AQUA + ChatContent.ITALIC + " - " + Language.translate("command.slotmachineaction.leverdescription.newline"));

									BaseComponent[] currentText = new ComponentBuilder(Language.translate("command.slotmachineaction.leverdescription.current") + " ")
											.color(ChatColor.DARK_PURPLE)
											.bold(true)
											.append(new ComponentBuilder(machine.getLeverDescription())
													.event(new ClickEvent(Action.SUGGEST_COMMAND, machine.getLeverDescription()))
													.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(new ComponentBuilder("Click to fill your chat box with this").create())))
													.bold(false)
													.italic(false)
													.color(ChatColor.WHITE)
													.create())
											.create();
									player.spigot().sendMessage(currentText);

									player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + "Type \"cancel\" to cancel");
								} else if (event.isRightClick()) {
									player.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + "Successfully reset to default");
									machine.setLeverCustom(false);
									machine.setPriceType(machine.getPriceType());
									machine.save();
									manageMachine(player, machine, entity, block, pagination.getPage());
								}
							}));
							if (machine.allowContentPreview())
								items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(Material.ENDER_CHEST, 1), ChatContent.GOLD + "Disable Item Preview"), Arrays.asList(
										ChatContent.AQUA + ChatContent.ITALIC + "Disable the Ender Chest icon",
										ChatContent.AQUA + ChatContent.ITALIC + "on this machine's interface"
										)), event -> {
											player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
											machine.allowContentPreview(false);
											machine.save();
											player.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + "Disabled item preview");
											manageMachine(player, machine, entity, block, pagination.getPage());
								}));
							else
								items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(ItemStackUtil.addGlow(new ItemStack(Material.ENDER_CHEST, 1)), ChatContent.GOLD + "Enable Item Preview"), Arrays.asList(
										ChatContent.AQUA + ChatContent.ITALIC + "Enable the Ender Chest icon",
										ChatContent.AQUA + ChatContent.ITALIC + "on this machine's interface"
										)), event -> {
											player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
											machine.allowContentPreview(true);
											machine.save();
											player.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + "Enabled item preview");
											manageMachine(player, machine, entity, block, pagination.getPage());
								}));
							if (machine.allowContentPreview()) {
								if (machine.showItemWeightOnPreview())
									items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(Material.LIGHT_WEIGHTED_PRESSURE_PLATE, 1), ChatContent.GOLD + "Hide Item Weight"), Arrays.asList(
											ChatContent.AQUA + ChatContent.ITALIC + "Hide the item's weight on",
											ChatContent.AQUA + ChatContent.ITALIC + "a machine's item preview"
											)), event -> {
												player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
												machine.showItemWeightOnPreview(false);
												machine.save();
												player.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + "Disabled item weight");
												manageMachine(player, machine, entity, block, pagination.getPage());
									}));
								else
									items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(ItemStackUtil.addGlow(new ItemStack(Material.HEAVY_WEIGHTED_PRESSURE_PLATE, 1)), ChatContent.GOLD + "Show Item Weight"), Arrays.asList(
											ChatContent.AQUA + ChatContent.ITALIC + "Show the item's weight on",
											ChatContent.AQUA + ChatContent.ITALIC + "a machine's item preview"
											)), event -> {
												player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
												machine.showItemWeightOnPreview(true);
												machine.save();
												player.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + "Enabled item weight");
												manageMachine(player, machine, entity, block, pagination.getPage());
									}));

								if (machine.showChanceOfItemOnPreview())
									items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.LUCKY_BLOCK_RED), ChatContent.GOLD + "Hide Item Chance"), Arrays.asList(
											ChatContent.AQUA + ChatContent.ITALIC + "Hide the item's chance to be won on the item preview",
											"",
											ChatContent.GRAY + "Note : chance is calculated using the following equation :",
											ChatContent.WHITE + "1 / allItemsWeight * weight * (machineChance / 100) * 100"
											)), event -> {
												player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
												machine.showChanceOfItemOnPreview(false);
												machine.save();
												player.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + "Disabled item chance");
												manageMachine(player, machine, entity, block, pagination.getPage());
									}));
								else
									items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.LUCKY_BLOCK), ChatContent.GOLD + "Show Item Chance"), Arrays.asList(
											ChatContent.AQUA + ChatContent.ITALIC + "Show the item's chance to be won on the item preview",
											"",
											ChatContent.GRAY + "Note : chance is calculated using the following equation :",
											ChatContent.WHITE + "1 / allItemsWeight * weight * (machineChance / 100) * 100"
											)), event -> {
												player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
												machine.showChanceOfItemOnPreview(true);
												machine.save();
												player.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + "Enabled item chance");
												manageMachine(player, machine, entity, block, pagination.getPage());
									}));
							}
							items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(Material.BLUE_STAINED_GLASS, 1), ChatContent.GOLD + "Customize background & colors"), Arrays.asList(
									ChatContent.AQUA + ChatContent.ITALIC + "Allows you to customize the",
									ChatContent.AQUA + ChatContent.ITALIC + "background items, lever",
									ChatContent.AQUA + ChatContent.ITALIC + "and enderchest icon"
									)), event -> {
										player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
										MachineBackgroundCustomization.customizeBackground(player, machine);
							}));
							items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(Material.JUKEBOX, 1), ChatContent.GOLD + "Customize sounds"), Arrays.asList(
									ChatContent.AQUA + ChatContent.ITALIC + "Allows you to customize all",
									ChatContent.AQUA + ChatContent.ITALIC + "sounds played by this Slot Machine"
									)), event -> {
										player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
										MachineSoundCustomization.customizeSounds(player, machine);
							}));
							items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(PlayerHeadsUtil.CLOCK, ChatContent.GOLD + "Set Cooldown"), Arrays.asList(
									ChatContent.AQUA + ChatContent.ITALIC + "Sets this machine cooldown.",
									ChatContent.AQUA + ChatContent.ITALIC + "",
									ChatContent.AQUA + ChatContent.ITALIC + "- Value is in seconds",
									ChatContent.AQUA + ChatContent.ITALIC + "- Cooldown is per-player"
									)), event -> {
										player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
										player.closeInventory();
										PlayerUtil.resetPlayerData(player);
										player.setMetadata("slotmachine_setcooldown", new FixedMetadataValue(SlotPlugin.pl, machine.getMachineUUID().toString()));
										player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + "Write the cooldown you want this machine to have in seconds (integer), 0 means no cooldown :");
										player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + "Current cooldown : " + machine.getCooldown() + "s");
										player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + "Type \"cancel\" to cancel");
							}));
							if (block != null) {
								SlotMachineBlock machineBlock = ((SlotMachineBlock) machine);
								if (machineBlock.isLocked())
									items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(Material.STONE, 1), ChatContent.GOLD + "Make Breakable"), Arrays.asList(
											ChatContent.AQUA + ChatContent.ITALIC + "Make this block breakable",
											ChatContent.AQUA + ChatContent.ITALIC + "You will be able to break it"
											)), event -> {
												player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
												machineBlock.setLocked(false);
												player.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + Language.translate("command.slotmachineaction.breakable"));
												machine.save();
												manageMachine(player, machine, entity, block, pagination.getPage());
									}));
								else
									items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(Material.BEDROCK, 1), ChatContent.GOLD + "Make Unbreakable"), Arrays.asList(
											ChatContent.AQUA + ChatContent.ITALIC + "Make this block unbreakable",
											ChatContent.AQUA + ChatContent.ITALIC + "No one will be able to break this block"
											)), event -> {
												player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
												machineBlock.setLocked(true);
												player.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + Language.translate("command.slotmachineaction.unbreakable"));
												machine.save();
												manageMachine(player, machine, entity, block, pagination.getPage());
									}));
							}
						}

						if (livingEntity != null) {
							if (!livingEntity.hasAI())
								items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(Material.ARMOR_STAND, 1), ChatContent.GOLD + "Turn on AI"), Arrays.asList(
										ChatContent.AQUA + ChatContent.ITALIC + "Turn on this entity's AI",
										ChatContent.AQUA + ChatContent.ITALIC + "It will start moving again"
										)), event -> {
											player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
											livingEntity.setAI(true);
											player.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + Language.translate("command.slotmachineaction.turnonai"));
											manageMachine(player, machine, entity, block, pagination.getPage());
								}));
							else
								items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(Material.ARMOR_STAND, 1), ChatContent.GOLD + "Turn off AI"), Arrays.asList(
										ChatContent.AQUA + ChatContent.ITALIC + "Turn off this entity's AI",
										ChatContent.AQUA + ChatContent.ITALIC + "It will stop moving"
										)), event -> {
											player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
											livingEntity.setAI(false);
											player.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + Language.translate("command.slotmachineaction.turnoffai"));
											manageMachine(player, machine, entity, block, pagination.getPage());
								}));

							if (livingEntity.isSilent())
								items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(Material.NOTE_BLOCK, 1), ChatContent.GOLD + "Enable Sounds"), Arrays.asList(
										ChatContent.AQUA + ChatContent.ITALIC + "Allow this entity to make sound"
										)), event -> {
											player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
											livingEntity.setSilent(false);
											player.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + Language.translate("command.slotmachineaction.turnonsound"));
											manageMachine(player, machine, entity, block, pagination.getPage());
								}));
							else
								items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(Material.NOTE_BLOCK, 1), ChatContent.GOLD + "Make Silent"), Arrays.asList(
										ChatContent.AQUA + ChatContent.ITALIC + "Make this entity silent",
										ChatContent.AQUA + ChatContent.ITALIC + "It will stop making any sound"
										)), event -> {
											player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
											livingEntity.setSilent(true);
											player.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + Language.translate("command.slotmachineaction.turnoffsound"));
											manageMachine(player, machine, entity, block, pagination.getPage());
								}));
						}
						if (entity != null) {
							if (entity.isCustomNameVisible())
								items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(Material.MAP, 1), ChatContent.GOLD + "Turn Off Name Visibility"), Arrays.asList(
										ChatContent.AQUA + ChatContent.ITALIC + "Stop showing this machine's name"
										)), event -> {
											player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
											entity.setCustomNameVisible(false);
											player.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + Language.translate("command.slotmachineaction.turnoffname"));
											manageMachine(player, machine, entity, block, pagination.getPage());
								}));
							else
								items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(Material.FILLED_MAP, 1), ChatContent.GOLD + "Turn on Name Visibility"), Arrays.asList(
										ChatContent.AQUA + ChatContent.ITALIC + "Show this machine's name",
										ChatContent.AQUA + ChatContent.ITALIC + "above itself",
										"",
										ChatContent.GRAY + "Note : This is bugged in",
										ChatContent.GRAY + "some Minecraft versions"
										)), event -> {
											player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
											entity.setCustomNameVisible(true);
											player.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + Language.translate("command.slotmachineaction.turnonname"));
											manageMachine(player, machine, entity, block, pagination.getPage());
								}));
						}
						if (!Util.getMCVersion().startsWith("1.9") && entity != null) {
							try {
								Method hasGravityMethod = entity.getClass().getMethod("hasGravity");
								if((boolean) hasGravityMethod.invoke(entity)) {
									items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(Material.GLASS, 1), ChatContent.GOLD + "Turn Off Gravity"), Arrays.asList(
											ChatContent.AQUA + ChatContent.ITALIC + "Disable gravity for this machine",
											ChatContent.AQUA + ChatContent.ITALIC + "It will not fall"
											)), event -> {
												player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
												try {
													entity.getClass().getMethod("setGravity", boolean.class).invoke(entity, false);
													player.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + Language.translate("command.slotmachineaction.turnoffgravity"));
												} catch (Exception e) {
													e.printStackTrace();
													ExceptionCollector.sendException(SlotPlugin.pl, e);
												}
												manageMachine(player, machine, entity, block, pagination.getPage());
									}));
								} else {
									items.add(ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(Material.SAND, 1), ChatContent.GOLD + "Turn On Gravity"), Arrays.asList(
											ChatContent.AQUA + ChatContent.ITALIC + "Enable gravity for this machine",
											ChatContent.AQUA + ChatContent.ITALIC + "It will fall on the ground"
											)), event -> {
												player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
												try {
													entity.getClass().getMethod("setGravity", boolean.class).invoke(entity, true);
													player.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + Language.translate("command.slotmachineaction.turnongravity"));
												} catch (Exception e) {
													e.printStackTrace();
													ExceptionCollector.sendException(SlotPlugin.pl, e);
												}
												manageMachine(player, machine, entity, block, pagination.getPage());
									}));
								}
							} catch (Exception e) {
								e.printStackTrace();
								ExceptionCollector.sendException(SlotPlugin.pl, e);
							}
						}

						pagination.setItemsPerPage(4 * 9);
						pagination.setItems(items.toArray(new ClickableItem[items.size()]));

						contents.fill(ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1), " ")));
						pagination.addToIterator(contents.newIterator(Type.HORIZONTAL, 1, 0));

						if (machine != null) {
							contents.set(0, 7, ClickableItem.of(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.TRASH_CAN), ChatContent.GOLD + "Remove this Slot Machine"), Arrays.asList(
									ChatContent.AQUA + ChatContent.ITALIC + "This entity or block will no",
									ChatContent.AQUA + ChatContent.ITALIC + "longer be a Slot Machine, you will",
									ChatContent.AQUA + ChatContent.ITALIC + "be able to kill or break it",
									"",
									ChatContent.RED + ChatContent.BOLD + "Removing a Slot Machine is",
									ChatContent.RED + ChatContent.BOLD + "PERMANENT, you can't go back"
							)), event -> {
								ConfirmInventory.confirmWindow(player, "Confirm Machine Deletion ?", "No, cancel", "Yes, delete", callback -> {
									if (callback) {
										player.playSound(player.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1F, 1F);
										MachineMethods.removeSlotMachine(player, machine.getMachineUUID());
										player.closeInventory();
									} else {
										manageMachine(player, machine, entity, block, page);
										player.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + "This Slot Machine has NOT been removed");
									}
								}, false);
							}));
						}

						if (!pagination.isFirst())
							contents.set(5, 3, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.LEFT), Language.translate("basic.previouspage")), event -> {
								player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
								manageMachine(player, machine, entity, block, page - 1);
							}));

						if (!pagination.isLast())
							contents.set(5, 5, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.RIGHT), Language.translate("basic.nextpage")), event -> {
								player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
								manageMachine(player, machine, entity, block, page + 1);
							}));

						if (!pagination.isFirst() && !pagination.isLast())
							contents.set(5, 4, ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(Material.PAPER), Language.translate("basic.page") + " " + (pagination.getPage() + 1) + "/" + (pagination.last().getPage() + 1))));

						Clipboards.clipboardUI(player, contents, this, 5, 7, 5, 8, new PasteCallback() {

							@Override
							public SlotMachine beforePaste(SlotMachine inputMachine, SlotMachine outputMachine) {
								if (outputMachine == null) {
									player.closeInventory();
									player.playSound(player.getLocation(), Sound.BLOCK_END_PORTAL_SPAWN, 0.3F, 1.3F);
									if (entity != null) {
										outputMachine = MachineMethods.createSlotMachineEntity(player, entity);
									} else if (block != null) {
										outputMachine = MachineMethods.createSlotMachineBlock(player, block);
									}
								}
								return outputMachine;
							}

							@Override
							public void afterPaste(SlotMachine inputMachine, SlotMachine outputMachine) {
								manageMachine(player, outputMachine, entity, block, 0);
							}

						});
					}

					@Override
					public void update(Player player, InventoryContents contents) { }

					@Override
					public boolean disableCopyPaste() {
						if (machine == null) {
							return false;
						}

						return machine.getSlotMachineType() == SlotMachineType.ENTITY_LINK || machine.getSlotMachineType() == SlotMachineType.BLOCK_LINK;
					}

					@Nullable
					@Override
					public List<String> disableReason() {
						return Arrays.asList(
								ChatContent.AQUA + ChatContent.ITALIC + "Copying and Pasting isn't",
								ChatContent.AQUA + ChatContent.ITALIC + "currently available for links."
						);
					}

					@Override
					public ClipboardContent gives() {
						return ClipboardContent.SLOTMACHINE;
					}

					@Override
					public SlotMachine copy() {
						return machine;
					}

					@Override
					public ClipboardContent accepts() {
						return ClipboardContent.SLOTMACHINE;
					}

					@Override
					public SlotMachine paste(SlotMachine clipboardMachine) {
						return machine;
					}

					@Override
					public void reloadUI(boolean movement) {
						manageMachine(player, machine, entity, block, movement ? 0 : page);
					}

				})
				.build();

		inv.open(player, page);
	}
}
