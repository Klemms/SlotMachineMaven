package fr.klemms.slotmachine.loader.slotmachines;

import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.loader.migrators.IMigrator;
import fr.klemms.slotmachine.utils.Util;
import fr.klemms.slotmachine.utils.sounds.SSound;
import org.bukkit.configuration.file.YamlConfiguration;

public class SMSaveSoundMachineSpin implements ISMValueSaver {
	@Override
	public IMigrator<SlotMachine> migrator() {
		return new IMigrator<SlotMachine>() {
			@Override
			public boolean needsMigration(YamlConfiguration yaml) {
				return !yaml.isBoolean("sounds.machineSpin.isCustom") || !yaml.isString("sounds.machineSpin.key");
			}

			@Override
			public void migrate(YamlConfiguration yaml, SlotMachine obj) {
				if (yaml.isString("slotmachineSpinSound")) {
					String str = yaml.getString("slotmachineSpinSound");
					if (Util.isValidSound(str)) {
						SSound sound = SSound.fromInput(str, false);

						if (sound != null) {
							obj.setSlotmachineSpinSound(sound);
							return;
						}
					}
				}
				obj.resetSoundSlotMachineSpin();
			}
		};
	}

	@Override
	public void load(YamlConfiguration yaml, SlotMachine machine) {
		machine.setSlotmachineSpinSound(
				SSound.fromInput(
						yaml.getString("sounds.machineSpin.key"),
						yaml.getBoolean("sounds.machineSpin.isCustom")
				)
		);
	}

	@Override
	public void save(YamlConfiguration yaml, SlotMachine machine) {
		yaml.set("sounds.machineSpin.isCustom", machine.getSlotmachineSpinSound().isCustomKey());
		yaml.set("sounds.machineSpin.key", machine.getSlotmachineSpinSound().getSaveString());
	}
}
