package fr.klemms.slotmachine.dialogs;

import com.google.gson.JsonObject;
import fr.klemms.slotmachine.dialogs.callbacks.NumberInputCallback;
import fr.klemms.slotmachine.interraction.StringInputCallback;
import fr.klemms.slotmachine.utils.Util;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.dialog.ConfirmationDialog;
import net.md_5.bungee.api.dialog.DialogBase;
import net.md_5.bungee.api.dialog.action.ActionButton;
import net.md_5.bungee.api.dialog.body.DialogBody;
import net.md_5.bungee.api.dialog.body.PlainMessageBody;
import net.md_5.bungee.api.dialog.input.TextInput;
import org.apache.commons.lang3.math.NumberUtils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.*;

public class DialogInputNumber extends DialogHandler<StringInputCallback> {

	public static final String NAMESPACE = "smdginputnumber";
	public static final DialogInputNumber instance = new DialogInputNumber();

	@Override
	public String getNamespace() {
		return NAMESPACE;
	}

	@Override
	public boolean handle(AwaitingCallbacks<StringInputCallback> ac, Player caller, JsonObject json) {
		if (json.has("number")) {
			ac.callback.callback(json.get("number").getAsString());
			return true;
		}
		return false;
	}

	public static void open(NumberInputCallback callback, Player player, String dialogTitle, float initialValue, String inputLabel, String errorString, int min, int max, boolean allowDecimals, boolean canClose, boolean allowNegative, BaseComponent... bodies) {
		UUID key = UUID.randomUUID();

		JsonObject json = new JsonObject();
		json.addProperty("key", key.toString());

		List<DialogBody> dialogs = new ArrayList<>();
		Arrays.stream(bodies).forEach(baseComponent -> {
			PlainMessageBody msg = new PlainMessageBody(baseComponent);
			msg.width(300);
			dialogs.add(msg);
		});

		if (errorString != null && !errorString.isEmpty()) {
			PlainMessageBody error = new PlainMessageBody(new ComponentBuilder(errorString).color(ChatColor.RED).build());
			error.width(450);
			dialogs.add(error);
		}

		String correctValue = allowDecimals ? String.valueOf(initialValue) : String.valueOf((int) initialValue);

		ConfirmationDialog dialog = new ConfirmationDialog(
				new DialogBase(new TextComponent(dialogTitle))
						.pause(false)
						.body(dialogs)
						.afterAction(DialogBase.AfterAction.NONE)
						.canCloseWithEscape(canClose)
						.inputs(Collections.singletonList(new TextInput("number", 300, new TextComponent(inputLabel), true, correctValue, 16)))
		)
				.yes(new ActionButton(new TextComponent("Done"), instance.getClickAction(key, null)))
				.no(new ActionButton(Util.cancelComponent(), instance.getCloseAction(key)));

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