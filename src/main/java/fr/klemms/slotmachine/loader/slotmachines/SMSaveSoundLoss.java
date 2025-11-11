package fr.klemms.slotmachine.loader.slotmachines;

import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.loader.migrators.IMigrator;
import fr.klemms.slotmachine.utils.Util;
import fr.klemms.slotmachine.utils.sounds.SSound;
import org.bukkit.configuration.file.YamlConfiguration;

public class SMSaveSoundLoss implements ISMValueSaver {
	@Override
	public IMigrator<SlotMachine> migrator() {
		return new IMigrator<SlotMachine>() {
			@Override
			public boolean needsMigration(YamlConfiguration yaml) {
				return !yaml.isBoolean("sounds.loss.isCustom") || !yaml.isString("sounds.loss.key");
			}

			@Override
			public void migrate(YamlConfiguration yaml, SlotMachine obj) {
				if (yaml.isString("lossSound")) {
					String str = yaml.getString("lossSound");
					if (Util.isValidSound(str)) {
						SSound sound = SSound.fromInput(str, false);

						if (sound != null) {
							obj.setLossSound(sound);
							return;
						}
					}
				}
				obj.resetSoundLoss();
			}
		};
	}

	@Override
	public void load(YamlConfiguration yaml, SlotMachine machine) {
		machine.setLossSound(
				SSound.fromInput(
						yaml.getString("sounds.loss.key"),
						yaml.getBoolean("sounds.loss.isCustom")
				)
		);
	}

	@Override
	public void save(YamlConfiguration yaml, SlotMachine machine) {
		yaml.set("sounds.loss.isCustom", machine.getLossSound().isCustomKey());
		yaml.set("sounds.loss.key", machine.getLossSound().getSaveString());
	}
}
