package fr.klemms.slotmachine.placeholders;

import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotPlugin;
import me.realized.tm.api.TMAPI;
import org.bukkit.entity.Player;

public class VariableTMTokens implements Variable {

	@Override
	public String getVariable(Player player, SlotMachine slotMachine) {
		if(SlotPlugin.oldTokenManagerWorks) {
			return String.valueOf(TMAPI.getTokens(player.getUniqueId()));
		} else if(SlotPlugin.tokenManager != null) {
			return String.valueOf(SlotPlugin.tokenManager.getTokens(player).getAsLong());
		}
		return "{TOKENSMANAGER_NOT_INSTALLED}";
	}

	@Override
	public boolean canBeUsed() {
		return SlotPlugin.oldTokenManagerWorks || SlotPlugin.tokenManager != null;
	}
}
