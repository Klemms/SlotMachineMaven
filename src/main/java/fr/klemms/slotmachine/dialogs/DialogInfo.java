package fr.klemms.slotmachine.dialogs;

import com.google.gson.JsonObject;
import fr.klemms.slotmachine.interraction.SimpleCallback;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.dialog.DialogBase;
import net.md_5.bungee.api.dialog.NoticeDialog;
import net.md_5.bungee.api.dialog.action.ActionButton;
import net.md_5.bungee.api.dialog.body.DialogBody;
import net.md_5.bungee.api.dialog.body.PlainMessageBody;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class DialogInfo extends DialogHandler<SimpleCallback> {

	public static final String NAMESPACE = "smdginfo";
	public static final DialogInfo instance = new DialogInfo();

	@Override
	public String getNamespace() {
		return NAMESPACE;
	}

	@Override
	public boolean handle(AwaitingCallbacks<SimpleCallback> ac, Player caller, JsonObject json) {
		ac.callback.callback();
		return true;
	}

	public static void open(SimpleCallback callback, Player player, String dialogTitle, String buttonText, boolean canClose, BaseComponent ...body) {
		UUID key = UUID.randomUUID();

		List<DialogBody> dialogs = new ArrayList<DialogBody>();
		Arrays.stream(body).forEach(baseComponent -> {
			PlainMessageBody msg = new PlainMessageBody(baseComponent);
			msg.width(500);
			dialogs.add(msg);
		});

		NoticeDialog dialog = new NoticeDialog(
				new DialogBase(new TextComponent(dialogTitle))
						.pause(false)
						.body(dialogs)
						.afterAction(DialogBase.AfterAction.NONE)
						.canCloseWithEscape(canClose)
		)
				.action(new ActionButton(new TextComponent(buttonText), instance.getClickAction(key, null)));

		instance.awaitCallback(callback, key, player);

		player.showDialog(dialog);
	}
}