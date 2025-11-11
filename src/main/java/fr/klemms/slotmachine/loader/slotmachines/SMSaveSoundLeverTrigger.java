package fr.klemms.slotmachine.loader.slotmachines;

import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.loader.migrators.IMigrator;
import fr.klemms.slotmachine.utils.Util;
import fr.klemms.slotmachine.utils.sounds.SSound;
import org.bukkit.configuration.file.YamlConfiguration;

public class SMSaveSoundLeverTrigger implements ISMValueSaver {
	@Override
	public IMigrator<SlotMachine> migrator() {
		return new IMigrator<SlotMachine>() {
			@Override
			public boolean needsMigration(YamlConfiguration yaml) {
				return !yaml.isBoolean("sounds.leverTrigger.isCustom") || !yaml.isString("sounds.leverTrigger.key");
			}

			@Override
			public void migrate(YamlConfiguration yaml, SlotMachine obj) {
				if (yaml.isString("leverSound")) {
					String str = yaml.getString("leverSound");
					if (Util.isValidSound(str)) {
						SSound sound = SSound.fromInput(str, false);

						if (sound != null) {
							obj.setLeverSound(sound);
							return;
						}
					}
				}
				obj.resetSoundLever();
			}
		};
	}

	@Override
	public void load(YamlConfiguration yaml, SlotMachine machine) {
		machine.setLeverSound(
				SSound.fromInput(
						yaml.getString("sounds.leverTrigger.key"),
						yaml.getBoolean("sounds.leverTrigger.isCustom")
				)
		);
	}

	@Override
	public void save(YamlConfiguration yaml, SlotMachine machine) {
		yaml.set("sounds.leverTrigger.isCustom", machine.getLeverSound().isCustomKey());
		yaml.set("sounds.leverTrigger.key", machine.getLeverSound().getSaveString());
	}
}
