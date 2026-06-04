package fr.klemms.slotmachine.dialogs;

import com.google.gson.JsonObject;
import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.interraction.StringInputCallback;
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
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class DialogInputCommand extends DialogHandler<StringInputCallback> {

	public static final String NAMESPACE = "slotmachine:dialog_input_command";
	public static final DialogInputCommand instance = new DialogInputCommand();

	@Override
	public Key getNamespace() {
		return Key.key(NAMESPACE);
	}

	@Override
	public boolean handle(AwaitingCallbacks<StringInputCallback> ac, Player caller, JsonObject json) {
		if (json.has("command")) {
			ac.callback.callback(json.get("command").getAsString());
		}
		return true;
	}

	public static void open(StringInputCallback callback, Player player, String dialogTitle, String initialCommand, String bodyText, String errorString, boolean showPlaceholders, boolean canClose, boolean showPlaceholdersAPI) {
		UUID key = UUID.randomUUID();

		List<DialogBody> body = new ArrayList<>();

		if (!showPlaceholders) {
			body.add(DialogBody.plainMessage(
					Component.text(bodyText).decorate(TextDecoration.BOLD).color(ChatContent.TEX_GOLD),
					300
			));
		}

		if (showPlaceholders) {
			body.add(DialogBody.plainMessage(
					Component.text("Valid placeholders :").decorate(TextDecoration.BOLD).color(ChatContent.TEX_GOLD),
					600
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

		Dialog dialog = Dialog.create(builder -> builder.empty()
				.base(DialogBase.builder(Component.text(dialogTitle))
						.pause(false)
						.body(body)
						.afterAction(DialogBase.DialogAfterAction.NONE)
						.canCloseWithEscape(canClose)
						.inputs(Collections.singletonList(
								DialogInput.text(
										"command",
										300,
										Component.text("Command to execute (Max length : 512) :"),
										true,
										initialCommand != null ? initialCommand : "",
										512,
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

		instance.awaitCallback(text -> {
			if (text.trim().isEmpty() || (text.trim().equals("/"))) {
				player.playSound(player, Sound.ENTITY_VILLAGER_HURT, 1.3f, 1.2f);
				open(callback, player, dialogTitle, text, bodyText, "Invalid command : Command must not be empty", showPlaceholders, canClose, showPlaceholdersAPI);
			} else {
				callback.callback(text.trim().startsWith("/") ? text.trim().substring(1) : text.trim());
			}
		}, key, player);

		player.showDialog(dialog);
	}
}