package fr.klemms.slotmachine.placeholders;

import com.bencodez.votingplugin.user.VotingPluginUser;
import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.utils.PlayerUtil;
import me.realized.tm.api.TMAPI;
import org.bukkit.entity.Player;

public class VariableRawBalance implements Variable {

	@Override
	public String getVariable(Player player, SlotMachine slotMachine) {
		switch(slotMachine.getPriceType()) {
			case MONEY:
				if(SlotPlugin.econ != null) {

					return (SlotPlugin.econ.getBalance(player) == ((int)SlotPlugin.econ.getBalance(player))) ? String.valueOf((int)SlotPlugin.econ.getBalance(player)) : String.valueOf(SlotPlugin.econ.getBalance(player));
				}
				break;
			case PLAYERPOINTS:
				if(SlotPlugin.playerPointsAPI != null) {
					return String.valueOf(SlotPlugin.playerPointsAPI.look(player.getUniqueId()));
				}
				break;
			case TOKEN:
				return String.valueOf(PlayerUtil.countItems(player, slotMachine.getToken()));
			case EXPERIENCE:
				return String.valueOf(player.getLevel());
			case VOTINGPLUGIN:
				if (SlotPlugin.votingPlugin != null) {
					VotingPluginUser user = SlotPlugin.votingPlugin.getUserManager().getVotingPluginUser(player);

					if (user == null)
						return "0";

					return String.valueOf(user.getPoints());
				}
				break;
			case TOKENMANAGER:
				if(SlotPlugin.tokenManager != null) {
					return String.valueOf(SlotPlugin.tokenManager.getTokens(player).getAsLong());
				} else if(SlotPlugin.oldTokenManagerWorks) {
					return String.valueOf(TMAPI.getTokens(player));
				}
				break;
		}

		return "";
	}
}
