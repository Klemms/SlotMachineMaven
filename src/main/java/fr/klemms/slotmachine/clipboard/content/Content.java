package fr.klemms.slotmachine.clipboard.content;

import org.bukkit.entity.Player;

import fr.klemms.slotmachine.SlotMachine;

public abstract class Content {
	
	public abstract void copyContent(Player player, SlotMachine inputMachine, SlotMachine outputMachine);
}
