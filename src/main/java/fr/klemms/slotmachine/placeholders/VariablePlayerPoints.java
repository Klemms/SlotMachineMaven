package fr.klemms.slotmachine.placeholders;

import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotPlugin;
import org.bukkit.entity.Player;

public class VariablePlayerPoints implements Variable {

	@Override
	public String getVariable(Player player, SlotMachine slotMachine) {
		if(SlotPlugin.playerPoints != null) {
			return String.valueOf(SlotPlugin.playerPoints.getAPI().look(player.getUniqueId()));
		}
		return "{PLAYERPOINTS_NOT_INSTALLED}";
	}
}
