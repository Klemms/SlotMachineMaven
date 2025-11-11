package fr.klemms.slotmachine.loader.slotmachines;

import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.loader.migrators.IMigrator;
import fr.klemms.slotmachine.utils.Util;
import fr.klemms.slotmachine.utils.sounds.SSound;
import org.bukkit.configuration.file.YamlConfiguration;

public class SMSaveSoundMachineOpen implements ISMValueSaver {
	@Override
	public IMigrator<SlotMachine> migrator() {
		return new IMigrator<SlotMachine>() {
			@Override
			public boolean needsMigration(YamlConfiguration yaml) {
				return !yaml.isBoolean("sounds.machineOpen.isCustom") || !yaml.isString("sounds.machineOpen.key");
			}

			@Override
			public void migrate(YamlConfiguration yaml, SlotMachine obj) {
				if (yaml.isString("machineOpeningSound")) {
					String str = yaml.getString("machineOpeningSound");
					if (Util.isValidSound(str)) {
						SSound sound = SSound.fromInput(str, false);

						if (sound != null) {
							obj.setMachineOpeningSound(sound);
							return;
						}
					}
				}
				obj.resetSoundMachineOpening();
			}
		};
	}

	@Override
	public void load(YamlConfiguration yaml, SlotMachine machine) {
		machine.setMachineOpeningSound(
				SSound.fromInput(
						yaml.getString("sounds.machineOpen.key"),
						yaml.getBoolean("sounds.machineOpen.isCustom")
				)
		);
	}

	@Override
	public void save(YamlConfiguration yaml, SlotMachine machine) {
		yaml.set("sounds.machineOpen.isCustom", machine.getMachineOpeningSound().isCustomKey());
		yaml.set("sounds.machineOpen.key", machine.getMachineOpeningSound().getSaveString());
	}
}
