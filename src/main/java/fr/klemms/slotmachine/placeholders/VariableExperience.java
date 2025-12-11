package fr.klemms.slotmachine.placeholders;

import org.bukkit.entity.Player;

import fr.klemms.slotmachine.SlotMachine;

public class VariableExperience implements Variable {

	@Override
	public String getVariable(Player player, SlotMachine slotMachine) {
		return String.valueOf(player.getLevel());
	}

	@Override
	public boolean canBeUsed() {
		return true;
	}
}
