package fr.klemms.slotmachine.placeholders;

import org.bukkit.entity.Player;

import com.bencodez.votingplugin.user.UserManager;
import com.bencodez.votingplugin.user.VotingPluginUser;

import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.translation.Language;
import fr.klemms.slotmachine.utils.PlayerUtil;
import me.realized.tm.api.TMAPI;
import su.nightexpress.gamepoints.api.GamePointsAPI;
import su.nightexpress.gamepoints.data.PointUser;

public class VariableBalance implements Variable {

	@Override
	public String getVariable(Player player, SlotMachine slotMachine) {
		switch(slotMachine.getPriceType()) {
			case MONEY:
				if(SlotPlugin.econ != null) {
					return SlotPlugin.econ.format(SlotPlugin.econ.getBalance(player));
				}
				break;
			case GAMEPOINTS:
				if(SlotPlugin.isGamePointsEnabled) {
					PointUser user = GamePointsAPI.getUserData(player);
					if (user != null)
						return String.valueOf(user.getBalance());
					return "null";
				}
				break;
			case TOKEN:
				return PlayerUtil.countItems(player, slotMachine.getToken()) + " " + Language.translate("currency.tokens");
			case EXPERIENCE:
				return player.getLevel() + " " + Language.translate("currency.levels").toLowerCase();
			case VOTINGPLUGIN:
				if (SlotPlugin.votingPlugin != null) {
					VotingPluginUser user = UserManager.getInstance().getVotingPluginUser(player);
					
					if (user == null)
						return "0";
					
					return "" + user.getPoints();
				}
				break;
			case TOKENMANAGER:
				if(SlotPlugin.tokenManager != null) {
					return SlotPlugin.tokenManager.getTokens(player).getAsLong() + " " + Language.translate("currency.tmtokens");
				} else if(SlotPlugin.oldTokenManagerWorks) {
					return TMAPI.getTokens(player) + " " + Language.translate("currency.tmtokens");
				}
				break;
		}
		
		return "";
	}
}
