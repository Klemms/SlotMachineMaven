package fr.klemms.slotmachine.dialogs;

import com.google.gson.JsonObject;
import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.dialogs.callbacks.NumberInputCallback;
import fr.klemms.slotmachine.interraction.StringInputCallback;
import fr.klemms.slotmachine.utils.Util;
import io.papermc.paper.dialog.Dialog;
import io.papermc.paper.registry.data.dialog.ActionButton;
import io.papermc.paper.registry.data.dialog.DialogBase;
import io.papermc.paper.registry.data.dialog.body.DialogBody;
import io.papermc.paper.registry.data.dialog.input.DialogInput;
import io.papermc.paper.registry.data.dialog.type.DialogType;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.apache.commons.lang3.math.NumberUtils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.*;

public class DialogInputNumber extends DialogHandler<StringInputCallback> {

	public static final String NAMESPACE = "slotmachine:dialog_input_number";
	public static final DialogInputNumber instance = new DialogInputNumber();

	@Override
	public Key getNamespace() {
		return Key.key(NAMESPACE);
	}

	@Override
	public boolean handle(AwaitingCallbacks<StringInputCallback> ac, Player caller, JsonObject json) {
		if (json.has("number")) {
			ac.callback.callback(json.get("number").getAsString());
			return true;
		}
		return false;
	}

	public static void open(NumberInputCallback callback, Player player, String dialogTitle, float initialValue, String inputLabel, String errorString, int min, int max, boolean allowDecimals, boolean canClose, boolean allowNegative, TextComponent... bodies) {
		UUID key = UUID.randomUUID();

		List<DialogBody> body = new ArrayList<>();
		Arrays.stream(bodies).forEach(baseComponent -> {
			body.add(DialogBody.plainMessage(
					baseComponent,
					300
			));
		});

		if (errorString != null && !errorString.isEmpty()) {
			body.add(DialogBody.plainMessage(
					Component.text(errorString).color(ChatContent.TEX_RED),
					450
			));
		}

		String correctValue = allowDecimals ? String.valueOf(initialValue) : String.valueOf((int) initialValue);

		Dialog dialog = Dialog.create(builder -> builder.empty()
				.base(DialogBase.builder(Component.text(dialogTitle))
						.pause(false)
						.body(body)
						.afterAction(DialogBase.DialogAfterAction.NONE)
						.canCloseWithEscape(canClose)
						.inputs(Collections.singletonList(
								DialogInput.text(
										"number",
										300,
										Component.text(inputLabel),
										true,
										correctValue,
										16,
										null
								)
						))
						.build()
				)
				.type(DialogType.confirmation(
						ActionButton.builder(Component.text("Done")).action(instance.getClickAction(key, null)).build(),
						ActionButton.builder(Util.cancelNeoComponent()).action(instance.getCloseAction(key)).build()
				))
		);

		if (callback != null) {
			instance.awaitCallback(text -> {
				final String convertedDecimals = text.replace(',', '.').trim();
				String errorMessage = null;

				if (convertedDecimals.isEmpty()) {
					errorMessage = "Invalid number : \nValue can't be empty";
				} else if (!NumberUtils.isParsable(convertedDecimals)) {
					errorMessage = "Invalid number : \nThis is not a valid number";
				} else {
					final float parsed = Float.parseFloat(convertedDecimals);

					if (!allowNegative && parsed < 0) {
						errorMessage = "Invalid number : \nNumber can't be negative";
					} else if (parsed < min || parsed > max) {
						errorMessage = "Invalid number : \nNumber is outside valid range : [" + min + "-" + max + "]";
					} else {
						if (allowDecimals) {
							callback.callback(parsed);
						} else {
							callback.callback((float) (int) parsed);
						}
						return;
					}
				}

				player.playSound(player, Sound.ENTITY_VILLAGER_HURT, 1.3f, 1.2f);
				open(callback, player, dialogTitle, initialValue, inputLabel, errorMessage, min, max, allowDecimals, canClose, allowNegative, bodies);
			}, key, player);
		}

		player.showDialog(dialog);
	}
}