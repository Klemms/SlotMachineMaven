package fr.klemms.slotmachine.placeholders;

import org.bukkit.entity.Player;

import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotPlugin;
import su.nightexpress.gamepoints.api.GamePointsAPI;
import su.nightexpress.gamepoints.data.PointUser;

public class VariableGamePoints implements Variable {

	@Override
	public String getVariable(Player player, SlotMachine slotMachine) {
		if(SlotPlugin.isGamePointsEnabled) {
			PointUser user = GamePointsAPI.getUserData(player);
			if (user != null)
				return String.valueOf(user.getBalance());
			return "null";
		}
		return "{GAMEPOINTS_NOT_INSTALLED}";
	}
}
