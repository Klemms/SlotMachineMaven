package fr.klemms.slotmachine.saving;

import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotMachineBlock;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.serialization.SMJSONFile;

import java.nio.file.Files;
import java.text.DateFormat;
import java.util.Date;

public class SlotMachineSave {

	public static void save(SlotMachine machine) {
		SMJSONFile file = new SMJSONFile(machine.getMachineUUID().toString());

		file.json.put("saveTime", DateFormat.getDateTimeInstance().format(new Date()).toString());
		file.json.put("smVersion", SlotPlugin.VERSION);

		file.json.put("machineType", machine.getSlotMachineType().toString());

		if (machine instanceof SlotMachineBlock) {
			SlotMachineBlock smBlock = ((SlotMachineBlock) machine);
			file.json.put("blockData",
					new SMBlockData(
							smBlock.getWorldUID(),
							smBlock.getBlockX(),
							smBlock.getBlockY(),
							smBlock.getBlockZ(),
							smBlock.isLocked()
					).toJson()
			);
		}

		file.write(SlotPlugin.pl.getDataFolder().toPath().resolve("machines-json"));
	}

	public static void saveAllMachines(boolean forceSave) {
		if (Files.exists(SlotPlugin.pl.getDataFolder().toPath().resolve("machines"))) {
			for (SlotMachine slotMachine : SlotMachine.getSlotMachines()) {
				if (!forceSave && !slotMachine.needsSaving()) {
					continue;
				}

				save(slotMachine);
			}
		}
	}
}
