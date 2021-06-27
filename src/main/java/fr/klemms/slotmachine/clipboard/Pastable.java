package fr.klemms.slotmachine.clipboard;

import fr.klemms.slotmachine.SlotMachine;

public interface Pastable {
	
	public ClipboardContent accepts();
	
	public SlotMachine paste(SlotMachine clipboardMachine);

	public void reloadUI(boolean movement);
}
