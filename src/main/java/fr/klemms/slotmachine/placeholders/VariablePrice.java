package fr.klemms.slotmachine.placeholders;

import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.utils.Util;
import org.bukkit.entity.Player;
import su.nightexpress.coinsengine.api.CoinsEngineAPI;
import su.nightexpress.coinsengine.api.currency.Currency;

public class VariablePrice implements Variable {

	@Override
	public String getVariable(Player player, SlotMachine slotMachine) {
		switch(slotMachine.getPriceType()) {
			case MONEY:
				return Util.formatNumber(slotMachine.getPullPrice());
			case EXPERIENCE:
			case VOTINGPLUGIN:
			case PLAYERPOINTS:
			case TOKEN:
			case TOKENMANAGER:
				return String.valueOf((int)slotMachine.getPullPrice());
			case COINSENGINE:
				Currency currency = CoinsEngineAPI.getCurrency(slotMachine.getCoinsEngineCurrencyID());

				if (currency != null) {
					return currency.format(slotMachine.getPullPrice());
				}
			default:
				return Util.formatNumber(slotMachine.getPullPrice());
		}
	}
}
