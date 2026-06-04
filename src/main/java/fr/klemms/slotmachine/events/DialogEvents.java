package fr.klemms.slotmachine.events;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.dialogs.DialogHandler;
import fr.klemms.slotmachine.utils.LogUtils;
import fr.klemms.slotmachine.utils.Util;
import io.papermc.paper.connection.PlayerGameConnection;
import io.papermc.paper.dialog.DialogResponseView;
import io.papermc.paper.event.player.PlayerCustomClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;

public class DialogEvents implements Listener {
	@EventHandler
	public void onCustomClick(PlayerCustomClickEvent event) {
		LogUtils.debug("Custom Click : " + event.getIdentifier().asString() + " // " + event.getDialogResponseView().toString());

		if (!Util.canUseDialogs() || !(event.getCommonConnection() instanceof PlayerGameConnection)) {
			return;
		}

		Player player = ((PlayerGameConnection) event.getCommonConnection()).getPlayer();

		for (DialogHandler handler : SlotPlugin.dialogHandlers) {
			if (handler.getNamespace().equals(event.getIdentifier())) {
				DialogResponseView view = event.getDialogResponseView();

				if (view == null) {
					return;
				}

				LogUtils.debug("JSON string : " + view.payload().string());
				JsonObject json = JsonParser.parseString(view.payload().string()).getAsJsonObject();

				if (json.has("key")) {
					UUID key = UUID.fromString(json.get("key").getAsString());
					DialogHandler.AwaitingCallbacks ac = handler.getAwaitingCallback(player, key);

					if (ac != null) {
						LogUtils.debug("Custom Click Validated : " + view.payload().string());
						boolean removeAC = false;

						if (json.has("__close")) {
							removeAC = true;
							player.closeDialog();
						} else {
							removeAC = handler.handle(ac, player, json);
						}

						if (removeAC) {
							handler.awaitingCallbacks.remove(ac);
						}
						break;
					}
				}
			}
		}
	}
}
