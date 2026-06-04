package fr.klemms.slotmachine.dialogs;

import com.google.gson.JsonObject;
import fr.klemms.slotmachine.interraction.SimpleCallback;
import io.papermc.paper.dialog.Dialog;
import io.papermc.paper.registry.data.dialog.ActionButton;
import io.papermc.paper.registry.data.dialog.DialogBase;
import io.papermc.paper.registry.data.dialog.body.DialogBody;
import io.papermc.paper.registry.data.dialog.type.DialogType;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class DialogInfo extends DialogHandler<SimpleCallback> {

	public static final String NAMESPACE = "slotmachine:dialog_info";
	public static final DialogInfo instance = new DialogInfo();

	@Override
	public Key getNamespace() {
		return Key.key(NAMESPACE);
	}

	@Override
	public boolean handle(AwaitingCallbacks<SimpleCallback> ac, Player caller, JsonObject json) {
		ac.callback.callback();
		return true;
	}

	public static void open(SimpleCallback callback, Player player, String dialogTitle, String buttonText, boolean canClose, TextComponent... body) {
		UUID key = UUID.randomUUID();

		List<DialogBody> dialogs = new ArrayList<DialogBody>();
		Arrays.stream(body).forEach(textComponent -> {
			dialogs.add(DialogBody.plainMessage(textComponent, 500));
		});

		Dialog dialog = Dialog.create(builder -> builder.empty()
				.base(DialogBase.builder(Component.text(dialogTitle))
						.pause(false)
						.body(dialogs)
						.afterAction(DialogBase.DialogAfterAction.NONE)
						.canCloseWithEscape(canClose)
						.build()
				)
				.type(DialogType.notice(
						ActionButton.builder(Component.text(buttonText))
								.action(instance.getClickAction(key, null))
								.build()
				))
		);

		instance.awaitCallback(callback, key, player);

		player.showDialog(dialog);
	}
}