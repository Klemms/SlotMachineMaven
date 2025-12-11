package fr.klemms.slotmachine.dialogs;

import com.google.gson.JsonObject;
import fr.klemms.slotmachine.interraction.SimpleCallback;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.dialog.DialogBase;
import net.md_5.bungee.api.dialog.NoticeDialog;
import net.md_5.bungee.api.dialog.action.ActionButton;
import net.md_5.bungee.api.dialog.body.PlainMessageBody;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.UUID;

public class DialogInfo extends DialogHandler<SimpleCallback> {

	public static final String NAMESPACE = "smdginfo";
	public static final DialogInfo instance = new DialogInfo();

	@Override
	public String getNamespace() {
		return NAMESPACE;
	}

	@Override
	public void handle(AwaitingCallbacks<SimpleCallback> ac, Player caller, JsonObject json) {
		ac.callback.callback();
	}

	public static void open(SimpleCallback callback, Player player, String dialogTitle, String bodyText, String buttonText, boolean canClose) {
		UUID key = UUID.randomUUID();

		NoticeDialog dialog = new NoticeDialog(
				new DialogBase(new TextComponent(dialogTitle))
						.pause(false)
						.body(Collections.singletonList(new PlainMessageBody(new TextComponent(bodyText))))
						.afterAction(DialogBase.AfterAction.NONE)
						.canCloseWithEscape(canClose)
		)
				.action(new ActionButton(new TextComponent(buttonText), instance.getClickAction(key, null)));

		instance.awaitCallback(callback, key, player);

		player.showDialog(dialog);
	}
}