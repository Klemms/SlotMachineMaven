package fr.klemms.slotmachine.dialogs;

import com.google.gson.JsonObject;
import fr.klemms.slotmachine.interraction.StringInputCallback;
import fr.klemms.slotmachine.placeholders.Variables;
import fr.klemms.slotmachine.translation.Language;
import fr.klemms.slotmachine.utils.LogUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.dialog.ConfirmationDialog;
import net.md_5.bungee.api.dialog.DialogBase;
import net.md_5.bungee.api.dialog.action.ActionButton;
import net.md_5.bungee.api.dialog.body.DialogBody;
import net.md_5.bungee.api.dialog.body.PlainMessageBody;
import net.md_5.bungee.api.dialog.input.TextInput;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class DialogInputCommand extends DialogHandler<StringInputCallback> {

	public static final String NAMESPACE = "smdginputcommand";
	public static final DialogInputCommand instance = new DialogInputCommand();

	@Override
	public String getNamespace() {
		return NAMESPACE;
	}

	@Override
	public void handle(AwaitingCallbacks<StringInputCallback> ac, Player caller, JsonObject json) {
		if (json.has("command")) {
			ac.callback.callback(json.get("command").getAsString());
		}
	}

	public static void open(StringInputCallback callback, Player player, String dialogTitle, String initialCommand, String bodyText, String errorString, boolean showPlaceholders, boolean canClose, boolean showPlaceholdersAPI) {
		UUID key = UUID.randomUUID();

		JsonObject json = new JsonObject();
		json.addProperty("key", key.toString());


		List<DialogBody> body = new ArrayList<>();

		if (errorString != null && !errorString.isEmpty()) {
			PlainMessageBody error = new PlainMessageBody(new ComponentBuilder(errorString).color(ChatColor.RED).build());
			error.width(450);
			body.add(error);
		}

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
			for (Variables var : Variables.values()) {
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
			isAdd(body, varLines);
		}

		ConfirmationDialog dialog = new ConfirmationDialog(
				new DialogBase(new TextComponent(dialogTitle))
						.pause(false)
						.body(body)
						.afterAction(DialogBase.AfterAction.NONE)
						.canCloseWithEscape(canClose)
						.inputs(Collections.singletonList(new TextInput("command", 300, new TextComponent("Command to execute (Max length : 512) :"), true, initialCommand, 512)))
		)
				.yes(new ActionButton(new TextComponent("Done"), instance.getClickAction(key, null)))
				.no(new ActionButton(new TextComponent("Cancel"), instance.getCloseAction(key)));

		instance.awaitCallback(callback, key, player);

		player.showDialog(dialog);
	}

	private static boolean isAdd(List<DialogBody> body, PlainMessageBody varLines) {
		return body.add(varLines);
	}
}