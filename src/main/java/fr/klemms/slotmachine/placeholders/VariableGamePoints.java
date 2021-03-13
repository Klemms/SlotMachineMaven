package fr.klemms.slotmachine.placeholders;

import org.bukkit.entity.Player;

import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotPlugin;
import su.nightexpress.gamepoints.GamePointsAPI;
import su.nightexpress.gamepoints.data.objects.StoreUser;

public class VariableGamePoints implements Variable {

	@Override
	public String getVariable(Player player, SlotMachine slotMachine) {
		if(SlotPlugin.isGamePointsEnabled) {
			StoreUser user = GamePointsAPI.getUserData(player);
			if (user != null)
				return String.valueOf(user.getBalance());
			return "null";
		}
		return "{GAMEPOINTS_NOT_INSTALLED}";
	}
}
