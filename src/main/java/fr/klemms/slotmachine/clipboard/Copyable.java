package fr.klemms.slotmachine.clipboard;

import fr.klemms.slotmachine.SlotMachine;

public interface Copyable {
	
	public ClipboardContent gives();
	
	public SlotMachine copy();
	
	public void reloadUI(boolean movement);
}
