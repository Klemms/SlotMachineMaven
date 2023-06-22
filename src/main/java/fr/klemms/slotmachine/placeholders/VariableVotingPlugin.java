package fr.klemms.slotmachine.placeholders;

import com.bencodez.votingplugin.user.VotingPluginUser;
import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotPlugin;
import org.bukkit.entity.Player;

public class VariableVotingPlugin implements Variable {

	@Override
	public String getVariable(Player player, SlotMachine slotMachine) {
		if (SlotPlugin.votingPlugin != null) {
			VotingPluginUser user = SlotPlugin.votingPlugin.getUserManager().getVotingPluginUser(player);

			if (user == null)
				return "0";
			return String.valueOf(user.getPoints());
		}

		return "{VOTINGPLUGIN_NOT_INSTALLED}";
	}
}
