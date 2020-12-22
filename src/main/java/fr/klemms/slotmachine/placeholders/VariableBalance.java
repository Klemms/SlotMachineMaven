package fr.klemms.slotmachine.placeholders;

import org.bukkit.entity.Player;

import com.bencodez.votingplugin.user.UserManager;
import com.bencodez.votingplugin.user.VotingPluginUser;

import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.utils.PlayerUtil;
import me.realized.tm.api.TMAPI;

public class VariableBalance implements Variable {

	@Override
	public String getVariable(Player player, SlotMachine slotMachine) {
		switch(slotMachine.getPriceType()) {
			case MONEY:
				if(SlotPlugin.econ != null) {
					return SlotPlugin.econ.format(SlotPlugin.econ.getBalance(player));
				}
				break;
			case PLAYERPOINTS:
				if(SlotPlugin.playerPoints != null) {
					return SlotPlugin.playerPoints.getAPI().look(player.getUniqueId()) + " PlayerPoints";
				}
				break;
			case TOKEN:
				return PlayerUtil.countItems(player, slotMachine.getToken()) + " Tokens";
			case EXPERIENCE:
				return player.getLevel() + " levels";
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
					return SlotPlugin.tokenManager.getTokens(player).getAsLong() + " TM Tokens";
				} else if(SlotPlugin.oldTokenManagerWorks) {
					return TMAPI.getTokens(player) + " TM Tokens";
//					try {
//						return Class.forName("me.realized.tm.api.TMAPI").getMethod("getTokens", Player.class).invoke(null, player) + " Tokens";
//					} catch (ClassNotFoundException |NoSuchMethodException | SecurityException |
//							IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//						e.printStackTrace();
//					}
				}
				break;
		}
		
		return "";
	}
}
