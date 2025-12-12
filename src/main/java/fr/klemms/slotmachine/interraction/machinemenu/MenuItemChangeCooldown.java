package fr.klemms.slotmachine.interraction.machinemenu;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.dialogs.DialogInfo;
import fr.klemms.slotmachine.dialogs.DialogResettableInputNumber;
import fr.klemms.slotmachine.dialogs.callbacks.ResettableCallback;
import fr.klemms.slotmachine.interraction.StringInput;
import fr.klemms.slotmachine.utils.PlayerHeadsUtil;
import fr.klemms.slotmachine.utils.PlayerUtil;
import fr.klemms.slotmachine.utils.Util;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.apache.commons.lang3.math.NumberUtils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class MenuItemChangeCooldown extends MenuItem {
	@Override
	public ItemStack getMaterial(SlotMachine machine, Player player) {
		return new ItemStack(PlayerHeadsUtil.CLOCK);
	}

	@Override
	public String getTitle(SlotMachine machine, Player player) {
		return ChatContent.GOLD + "Change Cooldown";
	}

	@Override
	public List<String> getDescription(SlotMachine machine, Player player) {
		return Arrays.asList(
				ChatContent.AQUA + ChatContent.ITALIC + "Sets this machine cooldown.",
				ChatContent.AQUA + ChatContent.ITALIC,
				ChatContent.AQUA + ChatContent.ITALIC + "- Value is in seconds",
				ChatContent.AQUA + ChatContent.ITALIC + "- Cooldown is per-player",
				ChatContent.AQUA + ChatContent.ITALIC + "- 0s means no cooldown",
				ChatContent.AQUA + ChatContent.ITALIC,
				ChatContent.AQUA + ChatContent.ITALIC + "Current cooldown :",
				ChatContent.RESET + (machine.getCooldown()) + "s"
		);
	}

	@Override
	public void onClick(SlotMachine machine, Player player, ClickType clickType, MenuState state) {
		switch (clickType) {
			case LEFT:
				player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);

				if (Util.canUseDialogs()) {
					DialogResettableInputNumber.open(
							new ResettableCallback<Float>() {
								@Override
								public void validateCallback(Float text) {
									machine.setCooldown((int)(float)text);
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
											new ComponentBuilder("Cooldown has been successfully changed").color(ChatColor.GOLD).build(),
											new ComponentBuilder("New cooldown :").build(),
											new ComponentBuilder(machine.getCooldown() + "s").build()
									);
								}

								@Override
								public void resetCallback() {
									machine.setCooldown(0);
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
											new ComponentBuilder("Cooldown has been reset").color(ChatColor.GOLD).build(),
											new ComponentBuilder("New cooldown :").build(),
											new ComponentBuilder(machine.getCooldown() + "s").build()
									);
								}

								@Override
								public void removeCallback() {
								}
							},
							player, "Change Cooldown", (float) (machine.getCooldown()), "Cooldown : (in seconds, integer)",
							null, 0, Integer.MAX_VALUE, false, true, true, false,
							new ComponentBuilder("Change the cooldown players need to wait after playing.").color(ChatColor.GOLD).build(),
							new ComponentBuilder("A value of 0 means no cooldown").color(ChatColor.GOLD).italic(true).build(),
							new ComponentBuilder("Cooldown is in seconds").color(ChatColor.GOLD).italic(true).build()
					);
				} else {
					StringInput.inputString(
							player,
							"Set Cooldown",
							String.valueOf(machine.getCooldown()),
							text -> {
								if (!text.isEmpty()) {
									if (!NumberUtils.isParsable(text)) {
										PlayerUtil.sendErrorMessage(player, "Please input a number (integer)");
										return;
									}

									int number = (int) Double.parseDouble(text);
									if (number < 0) {
										PlayerUtil.sendErrorMessage(player, "Please input a valid number (integer) from 0 to 2 billion");
										return;
									}

									player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.8F, 2F);

									PlayerUtil.sendSuccessMessage(player, "Successfully changed cooldown to " + number + "s");

									machine.setCooldown(number);
									machine.save();

									state.reloadPage();
								} else {
									PlayerUtil.sendErrorMessage(player, "Cooldown can't be empty");
								}
							},
							true,
							false,
							false,
							new ItemStack(PlayerHeadsUtil.CLOCK),
							new ItemStack(PlayerHeadsUtil.CLOCK)
					);
				}
				break;
		}
	}
}
