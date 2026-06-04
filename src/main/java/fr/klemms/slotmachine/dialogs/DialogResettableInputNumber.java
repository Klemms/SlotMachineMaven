package fr.klemms.slotmachine.dialogs;

import com.google.gson.JsonObject;
import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.dialogs.callbacks.ResettableCallback;
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

public class DialogResettableInputNumber extends DialogHandler<ResettableCallback<String>> {

	public static final String NAMESPACE = "slotmachine:dialog_resettable_input_number";
	public static final DialogResettableInputNumber instance = new DialogResettableInputNumber();

	@Override
	public Key getNamespace() {
		return Key.key(NAMESPACE);
	}

	@Override
	public boolean handle(AwaitingCallbacks<ResettableCallback<String>> ac, Player caller, JsonObject json) {
		if (json.has("data")) {
			JsonObject data = json.getAsJsonObject("data");
			if (data.has("remove")) {
				ac.callback.removeCallback();
				return true;
			} else if (data.has("reset")) {
				ac.callback.resetCallback();
				return true;
			}
		} else if (json.has("number")) {
			ac.callback.validateCallback(json.get("number").getAsString());
			return true;
		}
		return false;
	}

	public static void open(ResettableCallback<Float> callback, Player player, String dialogTitle, float initialValue, String inputLabel, String errorString, int min, int max, boolean allowDecimals, boolean canClose, boolean showReset, boolean showRemove, TextComponent... bodies) {
		UUID key = UUID.randomUUID();

		List<DialogBody> body = new ArrayList<>();
		Arrays.stream(bodies).forEach(textComponent -> {
			body.add(DialogBody.plainMessage(
					textComponent,
					300
			));
		});

		if (errorString != null && !errorString.isEmpty()) {
			body.add(DialogBody.plainMessage(
					Component.text(errorString)
							.color(ChatContent.TEX_RED),
					450
			));
		}

		List<ActionButton> actionButtons = new ArrayList<>();
		actionButtons.add(ActionButton.builder(Component.text("Done").color(ChatContent.TEX_GREEN)).width(100).action(instance.getClickAction(key, null)).build());
		if (showReset) {
			JsonObject obj = new JsonObject();
			obj.addProperty("reset", true);
			actionButtons.add(ActionButton.builder(Component.text("Reset to default")).width(100).action(instance.getClickAction(key, obj)).build());
		}
		if (showRemove) {
			JsonObject obj = new JsonObject();
			obj.addProperty("remove", true);
			actionButtons.add(ActionButton.builder(Component.text("Remove")).width(100).action(instance.getClickAction(key, obj)).build());
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
				.type(DialogType.multiAction(
						actionButtons,
						ActionButton.builder(Util.cancelNeoComponent()).action(instance.getCloseAction(key)).build(),
						3
				))
		);

		if (callback != null) {
			instance.awaitCallback(new ResettableCallback<String>() {
				@Override
				public void validateCallback(String value) {
					final String convertedDecimals = value.replace(',', '.').trim();
					String errorMessage = null;

					if (convertedDecimals.isEmpty()) {
						errorMessage = "Invalid number : \nValue can't be empty";
					} else if (!NumberUtils.isParsable(convertedDecimals)) {
						errorMessage = "Invalid number : \nThis is not a valid number";
					} else {
						final float parsed = Float.parseFloat(convertedDecimals);

						if (parsed < min || parsed > max) {
							errorMessage = "Invalid number : \nNumber is outside valid range : [" + min + "-" + max + "]";
						} else {
							callback.validateCallback(allowDecimals ? parsed : ((int) parsed));
							return;
						}
					}

					player.playSound(player, Sound.ENTITY_VILLAGER_HURT, 1.3f, 1.2f);
					open(callback, player, dialogTitle, initialValue, inputLabel, errorMessage, min, max, allowDecimals, canClose, showReset, showRemove, bodies);
				}

				@Override
				public void resetCallback() {
					callback.resetCallback();
				}

				@Override
				public void removeCallback() {
					callback.removeCallback();
				}
			}, key, player);
		}

		player.showDialog(dialog);
	}
}