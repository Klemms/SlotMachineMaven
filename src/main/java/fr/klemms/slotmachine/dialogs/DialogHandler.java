package fr.klemms.slotmachine.dialogs;

import com.google.gson.JsonObject;
import fr.klemms.slotmachine.utils.LogUtils;
import io.papermc.paper.registry.data.dialog.action.DialogAction;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.nbt.api.BinaryTagHolder;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class DialogHandler<T> {

	public List<AwaitingCallbacks<T>> awaitingCallbacks = new ArrayList<AwaitingCallbacks<T>>();

	public abstract Key getNamespace();

	/**
	 *
	 * @param ac
	 * @param caller
	 * @param json
	 * @return Return true to remove the AwaitingCallback, false will not remove it
	 */
	public abstract boolean handle(AwaitingCallbacks<T> ac, Player caller, JsonObject json);

	public void dispose() {
		LogUtils.debug("Disposing of : " + this.getClass().getName());
		this.awaitingCallbacks.clear();
	}

	public void awaitCallback(T callback, UUID key, Player caller) {
		AwaitingCallbacks<T> ac = new AwaitingCallbacks<T>();
		ac.callback = callback;
		ac.callerUUID = caller.getUniqueId();
		ac.key = key;

		this.awaitingCallbacks.add(ac);
	}

	public DialogAction.CustomClickAction getClickAction(UUID key, JsonObject json) {
		JsonObject obj = new JsonObject();
		obj.addProperty("key", key.toString());
		if (json != null) {
			obj.add("data", json);
		}

		return DialogAction.customClick(this.getNamespace(), BinaryTagHolder.binaryTagHolder(obj.toString()));
	}

	public DialogAction.CustomClickAction getCloseAction(UUID key) {
		JsonObject obj = new JsonObject();
		obj.addProperty("key", key.toString());
		obj.addProperty("__close", true);

		return DialogAction.customClick(this.getNamespace(), BinaryTagHolder.binaryTagHolder(obj.toString()));
	}

	public AwaitingCallbacks<T> getAwaitingCallback(Player caller, UUID key) {
		for (AwaitingCallbacks<T> ac : this.awaitingCallbacks) {
			if (ac.callerUUID.compareTo(caller.getUniqueId()) == 0 && ac.key.compareTo(key) == 0) {
				return ac;
			}
		}

		return null;
	}

	public static class AwaitingCallbacks<T> {
		public T callback;
		public UUID callerUUID;
		public UUID key;

		public boolean isValid(Player player, UUID key) {
			return player.getUniqueId().compareTo(this.callerUUID) == 0 && key.compareTo(this.key) == 0;
		}
	}
}
