package fr.klemms.slotmachine.tokens;

import org.bukkit.entity.Player;

public interface TokenSelectionListener {

	public void callback(Player player, Token token);
}
