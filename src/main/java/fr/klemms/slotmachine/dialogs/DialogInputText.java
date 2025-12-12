package fr.klemms.slotmachine.dialogs;

import com.google.gson.JsonObject;
import fr.klemms.slotmachine.interraction.StringInputCallback;
import fr.klemms.slotmachine.placeholders.Variables;
import fr.klemms.slotmachine.translation.Language;
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
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.*;

public class DialogInputText extends DialogHandler<StringInputCallback> {

	public static final String NAMESPACE = "smdginputtext";
	public static final DialogInputText instance = new DialogInputText();

	@Override
	public String getNamespace() {
		return NAMESPACE;
	}

	@Override
	public boolean handle(AwaitingCallbacks<StringInputCallback> ac, Player caller, JsonObject json) {
		if (json.has("text")) {
			ac.callback.callback(json.get("text").getAsString());
		}
		return true;
	}

	public static void open(StringInputCallback callback, Player player, String dialogTitle, String initialText, String errorString, boolean showPlaceholders, boolean canClose, boolean showPlaceholdersAPI, BaseComponent... bodies) {
		UUID key = UUID.randomUUID();

		JsonObject json = new JsonObject();
		json.addProperty("key", key.toString());

		List<DialogBody> dialogs = new ArrayList<>();
		Arrays.stream(bodies).forEach(baseComponent -> {
			PlainMessageBody msg = new PlainMessageBody(baseComponent);
			msg.width(300);
			dialogs.add(msg);
		});

		if (showPlaceholders) {
			PlainMessageBody base = new PlainMessageBody(new ComponentBuilder("Valid placeholders :").bold(true).color(ChatColor.GOLD).build());
			base.width(300);
			dialogs.add(base);

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
			dialogs.add(varLines);
		}

		if (errorString != null && !errorString.isEmpty()) {
			PlainMessageBody error = new PlainMessageBody(new ComponentBuilder(errorString).color(ChatColor.RED).build());
			error.width(450);
			dialogs.add(error);
		}

		ConfirmationDialog dialog = new ConfirmationDialog(
				new DialogBase(new TextComponent(dialogTitle))
						.pause(false)
						.body(dialogs)
						.afterAction(DialogBase.AfterAction.NONE)
						.canCloseWithEscape(canClose)
						.inputs(Collections.singletonList(new TextInput("text", 300, new TextComponent("Text (Max length : 256) :"), true, initialText != null ? initialText : "", 256)))
		)
				.yes(new ActionButton(new TextComponent("Done"), instance.getClickAction(key, null)))
				.no(new ActionButton(Util.cancelComponent(), instance.getCloseAction(key)));

		instance.awaitCallback(text -> {
			if (text.trim().isEmpty()) {
				player.playSound(player, Sound.ENTITY_VILLAGER_HURT, 1.3f, 1.2f);
				open(callback, player, dialogTitle, text, "Invalid text : Text can't be empty", showPlaceholders, canClose, showPlaceholdersAPI, bodies);
			} else {
				callback.callback(text.trim());
			}
		}, key, player);

		player.showDialog(dialog);
	}
}