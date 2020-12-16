package fr.klemms.slotmachine.placeholders;

import fr.klemms.slotmachine.SlotMachine;
import org.bukkit.entity.Player;

public interface Variable {

	public String getVariable(Player player, SlotMachine slotMachine);
}
