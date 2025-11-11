package fr.klemms.slotmachine.loader.slotmachines;

import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.loader.migrators.IMigrator;
import fr.klemms.slotmachine.utils.Util;
import fr.klemms.slotmachine.utils.sounds.SSound;
import org.bukkit.configuration.file.YamlConfiguration;

public class SMSaveSoundCSGOSpin implements ISMValueSaver {
	@Override
	public IMigrator<SlotMachine> migrator() {
		return new IMigrator<SlotMachine>() {
			@Override
			public boolean needsMigration(YamlConfiguration yaml) {
				return !yaml.isBoolean("sounds.csgoSpin.isCustom") || !yaml.isString("sounds.csgoSpin.key");
			}

			@Override
			public void migrate(YamlConfiguration yaml, SlotMachine obj) {
				if (yaml.isString("csgoSpinSound")) {
					String str = yaml.getString("csgoSpinSound");
					if (Util.isValidSound(str)) {
						SSound sound = SSound.fromInput(str, false);

						if (sound != null) {
							obj.setCsgoSpinSound(sound);
							return;
						}
					}
				}
				obj.resetSoundCSGOSpin();
			}
		};
	}

	@Override
	public void load(YamlConfiguration yaml, SlotMachine machine) {
		machine.setCsgoSpinSound(
				SSound.fromInput(
						yaml.getString("sounds.csgoSpin.key"),
						yaml.getBoolean("sounds.csgoSpin.isCustom")
				)
		);
	}

	@Override
	public void save(YamlConfiguration yaml, SlotMachine machine) {
		yaml.set("sounds.csgoSpin.isCustom", machine.getCsgoSpinSound().isCustomKey());
		yaml.set("sounds.csgoSpin.key", machine.getCsgoSpinSound().getSaveString());
	}
}
