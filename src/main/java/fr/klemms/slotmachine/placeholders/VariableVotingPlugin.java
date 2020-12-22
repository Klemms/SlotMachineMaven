package fr.klemms.slotmachine.placeholders;

import org.bukkit.entity.Player;

import com.bencodez.votingplugin.user.UserManager;
import com.bencodez.votingplugin.user.VotingPluginUser;

import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotPlugin;

public class VariableVotingPlugin implements Variable {

	@Override
	public String getVariable(Player player, SlotMachine slotMachine) {
		if (SlotPlugin.votingPlugin != null) {
			VotingPluginUser user = UserManager.getInstance().getVotingPluginUser(player);
			
			if (user == null)
				return "0";
			return String.valueOf(user.getPoints());
		}
		
		return "{VOTINGPLUGIN_NOT_INSTALLED}";
	}
}
