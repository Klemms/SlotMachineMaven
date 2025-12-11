package fr.klemms.slotmachine.placeholders;

import com.bencodez.votingplugin.user.VotingPluginUser;
import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.translation.Language;
import fr.klemms.slotmachine.utils.PlayerUtil;
import me.realized.tm.api.TMAPI;
import org.bukkit.entity.Player;
import su.nightexpress.coinsengine.api.CoinsEngineAPI;
import su.nightexpress.coinsengine.api.currency.Currency;

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
				if(SlotPlugin.playerPointsAPI != null) {
					return String.valueOf(SlotPlugin.playerPointsAPI.lookFormatted(player.getUniqueId()));
				}
				break;
			case COINSENGINE:
				if (SlotPlugin.isCoinsEngineEnabled) {
					Currency currency = CoinsEngineAPI.getCurrency(slotMachine.getCoinsEngineCurrencyID());


					if (currency != null) {
						return currency.format(CoinsEngineAPI.getBalance(player, currency));
					}
				}
				break;
			case TOKEN:
				return PlayerUtil.countItems(player, slotMachine.getToken()) + " " + Language.translate("currency.tokens");
			case EXPERIENCE:
				return player.getLevel() + " " + Language.translate("currency.levels").toLowerCase();
			case VOTINGPLUGIN:
				if (SlotPlugin.votingPlugin != null) {
					VotingPluginUser user = SlotPlugin.votingPlugin.getUserManager().getVotingPluginUser(player);

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

	@Override
	public boolean canBeUsed() {
		return true;
	}
}
