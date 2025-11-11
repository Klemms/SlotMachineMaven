package fr.klemms.slotmachine.saving.machines;

import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.saving.SMSaveData;

import java.text.DateFormat;
import java.util.Date;

public class MDataSaveTime implements SMSaveData<SlotMachine, String> {
	@Override
	public String getKey() {
		return "saveTime";
	}

	@Override
	public boolean shouldSave(SlotMachine obj) {
		return true;
	}

	@Override
	public String save(SlotMachine obj) {
		return DateFormat.getDateTimeInstance().format(new Date()).toString();
	}

	@Override
	public void load(SlotMachine obj, String data) {
		return;
	}
}
