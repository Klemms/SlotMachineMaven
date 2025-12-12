package fr.klemms.slotmachine.dialogs;

import com.google.gson.JsonObject;
import fr.klemms.slotmachine.dialogs.callbacks.ResettableCallback;
import fr.klemms.slotmachine.placeholders.Variables;
import fr.klemms.slotmachine.translation.Language;
import fr.klemms.slotmachine.utils.Util;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.dialog.DialogBase;
import net.md_5.bungee.api.dialog.MultiActionDialog;
import net.md_5.bungee.api.dialog.action.ActionButton;
import net.md_5.bungee.api.dialog.body.DialogBody;
import net.md_5.bungee.api.dialog.body.PlainMessageBody;
import net.md_5.bungee.api.dialog.input.TextInput;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class DialogResettableInputText extends DialogHandler<ResettableCallback> {

	public static final String NAMESPACE = "smdgresettableinputtext";
	public static final DialogResettableInputText instance = new DialogResettableInputText();

	@Override
	public String getNamespace() {
		return NAMESPACE;
	}

	@Override
	public boolean handle(AwaitingCallbacks<ResettableCallback> ac, Player caller, JsonObject json) {
		if (json.has("data")) {
			JsonObject data = json.getAsJsonObject("data");
			if (data.has("remove")) {
				ac.callback.removeCallback();
				return true;
			} else if (data.has("reset")) {
				ac.callback.resetCallback();
				return true;
			}
		} else if (json.has("text")) {
			ac.callback.validateCallback(json.get("text").getAsString());
			return true;
		}
		return false;
	}

	public static void open(ResettableCallback callback, Player player, String dialogTitle, String initialText, String bodyText, String errorString, boolean showPlaceholders, boolean canClose, boolean showPlaceholdersAPI, boolean showReset, boolean showRemove) {
		UUID key = UUID.randomUUID();

		JsonObject json = new JsonObject();
		json.addProperty("key", key.toString());

		List<DialogBody> body = new ArrayList<>();

		if (!showPlaceholders) {
			PlainMessageBody base = new PlainMessageBody(new ComponentBuilder(bodyText).bold(true).color(ChatColor.GOLD).build());
			base.width(300);
			body.add(base);
		}

		if (showPlaceholders) {
			PlainMessageBody base = new PlainMessageBody(new ComponentBuilder("Valid placeholders :").bold(true).color(ChatColor.GOLD).build());
			base.width(300);
			body.add(base);

			ComponentBuilder variables = new ComponentBuilder();
			List<Variables> validVars = Variables.getValidVariables();
			for (Variables var : validVars) {
				variables.append(
						new ComponentBuilder()
								.append(new ComponentBuilder("$" + var.variableName).color(ChatColor.LIGHT_PURPLE).build())
								.append(new ComponentBuilder(" -> ").build())
								.append(new ComponentBuilder(Language.translate(var.variableDescription)).color(ChatColor.GRAY).build())
								.build()
				);
				variables.append("\n");
			}

			if (showPlaceholdersAPI) {
				variables.append("\n");
				variables.append(new ComponentBuilder(Language.translate("command.slotmachineaction.placeholderAPI")).italic(true).color(ChatColor.YELLOW).build());
			}

			PlainMessageBody varLines = new PlainMessageBody(variables.build());
			varLines.width(600);
			body.add(varLines);
		}

		if (errorString != null && !errorString.isEmpty()) {
			PlainMessageBody error = new PlainMessageBody(new ComponentBuilder(errorString).color(ChatColor.RED).build());
			error.width(450);
			body.add(error);
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
			actionButtons.add(new ActionButton(new ComponentBuilder("Remove message").build(), instance.getClickAction(key, obj)).width(100));
		}

		MultiActionDialog dialog = new MultiActionDialog(
				new DialogBase(new TextComponent(dialogTitle))
						.pause(false)
						.body(body)
						.afterAction(DialogBase.AfterAction.NONE)
						.canCloseWithEscape(canClose)
						.inputs(Collections.singletonList(new TextInput("text", 300, new TextComponent("Text (Max length : 256) :"), true, initialText != null ? initialText : "", 256))),
				actionButtons.toArray(new ActionButton[actionButtons.size()])
		)
				.columns(3)
				.exitAction(new ActionButton(Util.cancelComponent(), instance.getCloseAction(key)));

		if (callback != null) {
			instance.awaitCallback(new ResettableCallback() {
				@Override
				public void validateCallback(String text) {
					if (text.trim().isEmpty()) {
						open(callback, player, dialogTitle, text, bodyText, "Invalid text : Text can't be empty", showPlaceholders, canClose, showPlaceholdersAPI, showReset, showRemove);
					} else {
						callback.validateCallback(text.trim());
					}
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