package fr.klemms.slotmachine.placeholders;

import fr.klemms.slotmachine.SlotMachine;
import org.bukkit.entity.Player;

public class VariableMachineName implements Variable {

	@Override
	public String getVariable(Player player, SlotMachine slotMachine) {
		return slotMachine.getSlotMachineName();
	}
}
