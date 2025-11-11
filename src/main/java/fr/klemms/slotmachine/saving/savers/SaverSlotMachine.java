package fr.klemms.slotmachine.saving.savers;

import fr.klemms.slotmachine.Issue;
import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotMachineType;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.saving.SMSaveData;
import fr.klemms.slotmachine.saving.machines.MDataPluginVersion;
import fr.klemms.slotmachine.saving.machines.MDataSaveTime;
import fr.klemms.slotmachine.serialization.SMJSONFile;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

public class SaverSlotMachine extends Saver<SlotMachine> {

	public List<SMSaveData<SlotMachine, ?>> saveDatas = Arrays.asList(
			new MDataSaveTime(),
			new MDataPluginVersion()
	);

	@Override
	public SlotMachine prepareLoad(JSONObject data, String fileName) {
		try {
			SlotMachineType smType = data.getEnum(SlotMachineType.class, "machineType");
		} catch (Exception e) {
			Issue.newIssue(Issue.IssueType.MACHINE_READING_ISSUE, "Machine in file " + fileName + " has an invalid 'machineType', this machine has been skipped.", true);
			SlotPlugin.pl.getLogger().log(Level.SEVERE, "Machine in file " + fileName + " has an invalid 'machineType', this machine has been skipped.");
		}
		return null;
	}

	@Override
	public void postLoad(SlotMachine loadedData) {

	}

	@Override
	public SMJSONFile prepareSave(SlotMachine data) {
		return null;
	}
}
