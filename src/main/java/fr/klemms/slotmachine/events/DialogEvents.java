package fr.klemms.slotmachine.events;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.dialogs.DialogHandler;
import fr.klemms.slotmachine.utils.LogUtils;
import fr.klemms.slotmachine.utils.Util;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCustomClickEvent;

import java.util.UUID;

public class DialogEvents implements Listener {
	@EventHandler
	public void onCustomClick(PlayerCustomClickEvent event) {
		if (!Util.canUseDialogs()) {
			return;
		}

		for (DialogHandler handler : SlotPlugin.dialogHandlers) {
			if (handler.getNamespace().equals(event.getId().getKey())) {
				JsonElement json = event.getData();

				if (json != null && json.isJsonObject()) {
					JsonObject obj = json.getAsJsonObject();

					if (obj.has("key")) {
						UUID key = UUID.fromString(obj.get("key").getAsString());
						DialogHandler.AwaitingCallbacks ac = handler.getAwaitingCallback(event.getPlayer(), key);

						if (ac != null) {
							LogUtils.debug("Custom Click Validated : " + event.getData().toString());

							if (obj.has("__close")) {
								event.getPlayer().clearDialog();
							} else {
								handler.handle(ac, event.getPlayer(), obj);
							}

							handler.awaitingCallbacks.remove(ac);
							break;
						}
					}
				}
			}
		}
	}
}
