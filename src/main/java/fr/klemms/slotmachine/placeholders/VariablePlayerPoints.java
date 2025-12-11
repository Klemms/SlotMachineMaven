package fr.klemms.slotmachine.placeholders;

import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotPlugin;
import org.bukkit.entity.Player;

public class VariablePlayerPoints implements Variable {

	@Override
	public String getVariable(Player player, SlotMachine slotMachine) {
		if(SlotPlugin.playerPointsAPI != null) {
			return String.valueOf(SlotPlugin.playerPointsAPI.lookFormatted(player.getUniqueId()));
		}
		return "{PLAYERPOINTS_NOT_INSTALLED}";
	}

	@Override
	public boolean canBeUsed() {
		return SlotPlugin.playerPointsAPI != null;
	}
}
