package fr.klemms.slotmachine.dialogs;

import com.google.gson.JsonObject;
import fr.klemms.slotmachine.dialogs.callbacks.ResettableCallback;
import fr.klemms.slotmachine.utils.Util;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.dialog.DialogBase;
import net.md_5.bungee.api.dialog.MultiActionDialog;
import net.md_5.bungee.api.dialog.action.ActionButton;
import net.md_5.bungee.api.dialog.body.DialogBody;
import net.md_5.bungee.api.dialog.body.PlainMessageBody;
import net.md_5.bungee.api.dialog.input.TextInput;
import org.apache.commons.lang3.math.NumberUtils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.*;

public class DialogResettableInputNumber extends DialogHandler<ResettableCallback<String>> {

	public static final String NAMESPACE = "smdgresettableinputnumber";
	public static final DialogResettableInputNumber instance = new DialogResettableInputNumber();

	@Override
	public String getNamespace() {
		return NAMESPACE;
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

	public static void open(ResettableCallback<Float> callback, Player player, String dialogTitle, float initialValue, String inputLabel, String errorString, int min, int max, boolean allowDecimals, boolean canClose, boolean showReset, boolean showRemove, BaseComponent... bodies) {
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

		List<ActionButton> actionButtons = new ArrayList<>();
		actionButtons.add(new ActionButton(new ComponentBuilder("Done").color(ChatColor.GREEN).build(), instance.getClickAction(key, null)).width(100));
		if (showReset) {
			JsonObject obj = new JsonObject();
			obj.addProperty("reset", true);
			actionButtons.add(new ActionButton(new ComponentBuilder("Reset to default").build(), instance.getClickAction(key, obj)).width(100));
		}
		if (showRemove) {
			JsonObject obj = new JsonObject();
			obj.addProperty("remove", true);
			actionButtons.add(new ActionButton(new ComponentBuilder("Remove").build(), instance.getClickAction(key, obj)).width(100));
		}

		String correctValue = allowDecimals ? String.valueOf(initialValue) : String.valueOf((int) initialValue);

		MultiActionDialog dialog = new MultiActionDialog(
				new DialogBase(new TextComponent(dialogTitle))
						.pause(false)
						.body(dialogs)
						.afterAction(DialogBase.AfterAction.NONE)
						.canCloseWithEscape(canClose)
						.inputs(Collections.singletonList(new TextInput("number", 300, new TextComponent(inputLabel), true, correctValue, 16))),
				actionButtons.toArray(new ActionButton[actionButtons.size()])
		)
				.columns(3)
				.exitAction(new ActionButton(Util.cancelComponent(), instance.getCloseAction(key)));

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