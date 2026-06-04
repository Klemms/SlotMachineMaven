package fr.klemms.slotmachine.interraction.machinemenu;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.dialogs.DialogInfo;
import fr.klemms.slotmachine.dialogs.DialogResettableInputNumber;
import fr.klemms.slotmachine.dialogs.callbacks.ResettableCallback;
import fr.klemms.slotmachine.interraction.StringInput;
import fr.klemms.slotmachine.utils.PlayerUtil;
import fr.klemms.slotmachine.utils.Util;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.apache.commons.lang3.math.NumberUtils;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class MenuItemChangeChanceToWin extends MenuItem {
	@Override
	public ItemStack getMaterial(SlotMachine machine, Player player) {
		return new ItemStack(Material.LARGE_FERN, 1);
	}

	@Override
	public String getTitle(SlotMachine machine, Player player) {
		return ChatContent.GOLD + "Change Chance to Win";
	}

	@Override
	public List<String> getDescription(SlotMachine machine, Player player) {
		return Arrays.asList(
				ChatContent.AQUA + ChatContent.ITALIC + "Change the chance players",
				ChatContent.AQUA + ChatContent.ITALIC + "have to win",
				"",
				ChatContent.AQUA + ChatContent.ITALIC + "Current chance :",
				ChatContent.RESET + Util.formatNumberThreeDigits(machine.getChanceToWin() * 100) + "%"
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
									machine.setChanceToWin(text / 100D);
									machine.save();
									player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.8F, 2F);

									DialogInfo.open(() -> {
												player.closeDialog();
												state.reloadPage();
											},
											player,
											"Success",
											"Back",
											true,
											Component.text("Chance to win has been successfully changed").color(ChatContent.TEX_GOLD),
											Component.text("New chance to win :"),
											Component.text(Util.formatNumberThreeDigits(machine.getChanceToWin() * 100) + "%")
									);
								}

								@Override
								public void resetCallback() {
									machine.setChanceToWin(0.4D);
									machine.save();
									player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.8F, 2F);

									DialogInfo.open(() -> {
												player.closeDialog();
												state.reloadPage();
											},
											player,
											"Success",
											"Back",
											true,
											Component.text("Chance to win has been reset").color(ChatContent.TEX_GOLD),
											Component.text("New chance to win :"),
											Component.text(Util.formatNumberThreeDigits(machine.getChanceToWin() * 100) + "%")
									);
								}

								@Override
								public void removeCallback() {
								}
							},
							player, "Change Chance to Win", (float) (machine.getChanceToWin() * 100), "Chance to win : (decimals allowed)",
							null, 0, 100, true, true, true, false,
							Component.text("Change the chance for players to win.").color(ChatContent.TEX_GOLD),
							Component.text("Value goes from 0 to 100").color(ChatContent.TEX_GOLD).decorate(TextDecoration.ITALIC),
							Component.text("Decimals are allowed (e.g: 55.75)").color(ChatContent.TEX_GOLD).decorate(TextDecoration.ITALIC)
					);
				} else {
					StringInput.inputString(
							player,
							"Change Chance to Win",
							String.valueOf(machine.getChanceToWin() * 100),
							text -> {
								if (!text.isEmpty()) {
									if (!NumberUtils.isParsable(text)) {
										PlayerUtil.sendErrorMessage(player, "Please input a number. Decimals must be separated with a dot : .");
										return;
									}

									double number = Double.parseDouble(text);
									if (number < 0D || number > 100D) {
										PlayerUtil.sendErrorMessage(player, "Please input a valid number : [0-100]. Decimals must be separated with a dot : .");
										return;
									}

									player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.8F, 2F);

									PlayerUtil.sendSuccessMessage(player, "Successfully changed chance to win to " + number + "%");

									machine.setChanceToWin(number / 100D);
									machine.save();

									state.reloadPage();
								} else {
									PlayerUtil.sendErrorMessage(player, "Chance can't be empty");
								}
							},
							true,
							false,
							false,
							new ItemStack(Material.LARGE_FERN),
							new ItemStack(Material.LARGE_FERN)
					);
				}
				break;
		}
	}
}
