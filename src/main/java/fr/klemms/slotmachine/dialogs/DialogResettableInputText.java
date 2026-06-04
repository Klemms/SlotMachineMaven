package fr.klemms.slotmachine.dialogs;

import com.google.gson.JsonObject;
import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.dialogs.callbacks.ResettableCallback;
import fr.klemms.slotmachine.placeholders.Variables;
import fr.klemms.slotmachine.translation.Language;
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
import net.kyori.adventure.text.format.TextDecoration;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.entity.Player;

import java.util.*;

public class DialogResettableInputText extends DialogHandler<ResettableCallback<String>> {

	public static final String NAMESPACE = "slotmachine:dialog_resetttable_input_text";
	public static final DialogResettableInputText instance = new DialogResettableInputText();

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
		} else if (json.has("text")) {
			ac.callback.validateCallback(json.get("text").getAsString());
			return true;
		}
		return false;
	}

	public static void open(ResettableCallback<String> callback, Player player, String dialogTitle, String initialText, String errorString, boolean showPlaceholders, boolean canClose, boolean showPlaceholdersAPI, boolean showReset, boolean showRemove, boolean alphaOnly, TextComponent... bodies) {
		UUID key = UUID.randomUUID();

		List<DialogBody> body = new ArrayList<>();
		Arrays.stream(bodies).forEach(textComponent -> {
			body.add(DialogBody.plainMessage(
					textComponent,
					300
			));
		});

		if (showPlaceholders) {
			body.add(DialogBody.plainMessage(
					Component.text("Valid placeholders :").decorate(TextDecoration.BOLD).color(ChatContent.TEX_GOLD),
					300
			));

			TextComponent comp = Component.text("");
			List<Variables> validVars = Variables.getValidVariables();
			for (Variables var : validVars) {

				comp = comp.append(Component.text("").append(
						Component.text("$" + var.variableName).color(ChatContent.TEX_PINK),
						Component.text(" -> "),
						Component.text(Language.translate(var.variableDescription)).color(ChatContent.TEX_GRAY)
				)).appendNewline();
			}

			body.add(DialogBody.plainMessage(comp, 600));

			if (showPlaceholdersAPI) {
				body.add(DialogBody.plainMessage(
						Component.text(Language.translate("command.slotmachineaction.placeholderAPI"))
								.decorate(TextDecoration.ITALIC)
								.color(ChatContent.TEX_YELLOW),
						600
				));
			}
		}

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

		Dialog dialog = Dialog.create(builder -> builder.empty()
				.base(DialogBase.builder(Component.text(dialogTitle))
						.pause(false)
						.body(body)
						.afterAction(DialogBase.DialogAfterAction.NONE)
						.canCloseWithEscape(canClose)
						.inputs(Collections.singletonList(
								DialogInput.text(
										"text",
										300,
										Component.text("Text (Max length : 256) :"),
										true,
										initialText != null ? initialText : "",
										256,
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
				public void validateCallback(String text) {
					String errorMessage = null;

					if (alphaOnly && !StringUtils.isAlpha(text)) {
						errorMessage = "Invalid text : Only alphabetic characters are allowed";
					} else if (text.trim().isEmpty()) {
						errorMessage = "Invalid text : Text can't be empty";
					} else {
						callback.validateCallback(text.trim());
						return;
					}
					open(callback, player, dialogTitle, text, errorMessage, showPlaceholders, canClose, showPlaceholdersAPI, showReset, showRemove, alphaOnly, bodies);
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