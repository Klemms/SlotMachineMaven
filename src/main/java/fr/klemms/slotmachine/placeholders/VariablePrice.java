package fr.klemms.slotmachine.placeholders;

import org.bukkit.entity.Player;

import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.utils.Util;

public class VariablePrice implements Variable {

	@Override
	public String getVariable(Player player, SlotMachine slotMachine) {
		switch(slotMachine.getPriceType()) {
			case EXPERIENCE:
				return String.valueOf((int)slotMachine.getPullPrice());
			case MONEY:
				return Util.formatNumber(slotMachine.getPullPrice());
			case PLAYERPOINTS:
				return String.valueOf((int)slotMachine.getPullPrice());
			case TOKEN:
				return String.valueOf((int)slotMachine.getPullPrice());
			case TOKENMANAGER:
				return String.valueOf((int)slotMachine.getPullPrice());
			default:
				return Util.formatNumber(slotMachine.getPullPrice());
		}
	}
}
