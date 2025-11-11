package fr.klemms.slotmachine.saving.machines;

import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.saving.SMSaveData;

public class MDataPluginVersion implements SMSaveData<SlotMachine, Integer> {
	@Override
	public String getKey() {
		return "pluginVersion";
	}

	@Override
	public boolean shouldSave(SlotMachine obj) {
		return true;
	}

	@Override
	public Integer save(SlotMachine obj) {
		return SlotPlugin.VERSION;
	}

	@Override
	public void load(SlotMachine obj, Integer data) {
		return;
	}
}
