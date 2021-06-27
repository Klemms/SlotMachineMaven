package fr.klemms.slotmachine.clipboard;

import fr.klemms.slotmachine.SlotMachine;

public interface PasteCallback {

	/**
	 * 
	 * @param inputMachine
	 * @param outputMachine
	 * @return Updated outputMachine
	 */
	public SlotMachine beforePaste(SlotMachine inputMachine, SlotMachine outputMachine);
	
	public void afterPaste(SlotMachine inputMachine, SlotMachine outputMachine);
}
