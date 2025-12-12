package fr.klemms.slotmachine.interraction.machinemenu;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.dialogs.DialogInfo;
import fr.klemms.slotmachine.dialogs.DialogResettableInputText;
import fr.klemms.slotmachine.dialogs.callbacks.ResettableCallback;
import fr.klemms.slotmachine.placeholders.Variables;
import fr.klemms.slotmachine.translation.Language;
import fr.klemms.slotmachine.utils.PlayerUtil;
import fr.klemms.slotmachine.utils.Util;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.Arrays;
import java.util.List;

public class MenuItemChangeLeverName extends MenuItem {
	@Override
	public ItemStack getMaterial(SlotMachine machine, Player player) {
		return new ItemStack(Material.TRIPWIRE_HOOK, 1);
	}

	@Override
	public String getTitle(SlotMachine machine, Player player) {
		return ChatContent.GOLD + "Change Lever Name";
	}

	@Override
	public List<String> getDescription(SlotMachine machine, Player player) {
		return Arrays.asList(
				ChatContent.AQUA + ChatContent.ITALIC + "Change the lever's name",
				"",
				ChatContent.RED + "Right Click" + ChatContent.AQUA + ChatContent.ITALIC + " to reset to ",
				ChatContent.AQUA + ChatContent.ITALIC + "default name and description",
				"",
				ChatContent.AQUA + ChatContent.ITALIC + "Current name :",
				ChatContent.RESET + Variables.getFormattedString(machine.getLeverTitle(), player, machine)
		);
	}

	@Override
	public void onClick(SlotMachine machine, Player player, ClickType clickType, MenuState state) {
		switch (clickType) {
			case LEFT:
				player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);

				if (Util.canUseDialogs()) {
					DialogResettableInputText.open(
							new ResettableCallback<String>() {
								@Override
								public void validateCallback(String text) {
									machine.setLeverTitle(text);
									machine.setLeverCustom(true);
									machine.save();
									player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.8F, 2F);

									DialogInfo.open(() -> {
												player.clearDialog();
												state.reloadPage();
											},
											player,
											"Success",
											"Back",
											true,
											new ComponentBuilder("Lever name has been successfully changed").color(ChatColor.GOLD).build(),
											new ComponentBuilder("New lever name :").build(),
											new ComponentBuilder(ChatContent.translateColorCodes(machine.getLeverTitle())).build()
									);
								}

								@Override
								public void resetCallback() {
									machine.setLeverCustom(false);
									machine.setPriceType(machine.getPriceType());
									machine.save();
									player.playSound(player.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1F, 1F);

									DialogInfo.open(() -> {
												player.clearDialog();
												state.reloadPage();
											},
											player,
											"Success",
											"Back",
											true,
											new ComponentBuilder("Lever name has been reset").color(ChatColor.GOLD).build(),
											new ComponentBuilder("New lever name :").build(),
											new ComponentBuilder(ChatContent.translateColorCodes(machine.getLeverTitle())).build()
									);
								}

								@Override
								public void removeCallback() {
								}
							},
							player, "Change Lever Name", machine.getLeverTitle(), null, true, true, true, true, false
					);
				} else {
					player.closeInventory();
					PlayerUtil.resetPlayerData(player);
					player.setMetadata("slotmachine_setlevertitle", new FixedMetadataValue(SlotPlugin.pl, machine.getMachineUUID().toString()));
					player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("command.slotmachineaction.levertitle") + " :");

					List<Variables> validVars = Variables.getValidVariables();
					for (Variables var : validVars) {
						player.sendMessage(ChatContent.AQUA + " - $" + var.variableName + ChatContent.DARK_AQUA + ChatContent.ITALIC + " - " + Language.translate(var.variableDescription));
					}
					player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + Language.translate("command.slotmachineaction.placeholderAPI"));

					BaseComponent[] currentText = new ComponentBuilder(Language.translate("command.slotmachineaction.levertitle.current") + " ")
							.color(ChatColor.DARK_PURPLE)
							.bold(true)
							.append(new ComponentBuilder(machine.getLeverTitle())
									.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, machine.getLeverTitle()))
									.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(new ComponentBuilder("Click to fill your chat box with this").create())))
									.bold(false)
									.italic(false)
									.color(ChatColor.WHITE)
									.create())
							.create();
					player.spigot().sendMessage(currentText);

					player.sendMessage(ChatContent.DARK_PURPLE + ChatContent.BOLD + "Type \"cancel\" to cancel");
				}
				break;
			case RIGHT:
				player.playSound(player.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1F, 1F);

				player.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + "Successfully reset to default");
				machine.setLeverCustom(false);
				machine.setPriceType(machine.getPriceType());
				machine.save();

				state.reloadPage();
				break;
		}
	}

	@Override
	public boolean hasGlow(SlotMachine machine, Player player) {
		return true;
	}
}
