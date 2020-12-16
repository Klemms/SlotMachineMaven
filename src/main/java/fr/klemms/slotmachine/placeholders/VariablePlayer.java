package fr.klemms.slotmachine.placeholders;

import fr.klemms.slotmachine.SlotMachine;
import org.bukkit.entity.Player;

public class VariablePlayer implements Variable {

	@Override
	public String getVariable(Player player, SlotMachine slotMachine) {
		return player.getName();
	}
}
