package fr.klemms.slotmachine.placeholders;

import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.utils.PlayerUtil;
import org.bukkit.entity.Player;

public class VariableTokens implements Variable {

	@Override
	public String getVariable(Player player, SlotMachine slotMachine) {
		return String.valueOf(PlayerUtil.countItems(player, slotMachine.getToken()));
	}

	@Override
	public boolean canBeUsed() {
		return true;
	}
}
