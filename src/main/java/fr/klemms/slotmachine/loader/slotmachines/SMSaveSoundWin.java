package fr.klemms.slotmachine.loader.slotmachines;

import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.loader.migrators.IMigrator;
import fr.klemms.slotmachine.utils.Util;
import fr.klemms.slotmachine.utils.sounds.SSound;
import org.bukkit.configuration.file.YamlConfiguration;

public class SMSaveSoundWin implements ISMValueSaver {
	@Override
	public IMigrator<SlotMachine> migrator() {
		return new IMigrator<SlotMachine>() {
			@Override
			public boolean needsMigration(YamlConfiguration yaml) {
				return !yaml.isBoolean("sounds.win.isCustom") || !yaml.isString("sounds.win.key");
			}

			@Override
			public void migrate(YamlConfiguration yaml, SlotMachine obj) {
				if (yaml.isString("winSound")) {
					String str = yaml.getString("winSound");
					if (Util.isValidSound(str)) {
						SSound sound = SSound.fromInput(str, false);

						if (sound != null) {
							obj.setWinSound(sound);
							return;
						}
					}
				}
				obj.resetSoundWin();
			}
		};
	}

	@Override
	public void load(YamlConfiguration yaml, SlotMachine machine) {
		machine.setWinSound(
				SSound.fromInput(
						yaml.getString("sounds.win.key"),
						yaml.getBoolean("sounds.win.isCustom")
				)
		);
	}

	@Override
	public void save(YamlConfiguration yaml, SlotMachine machine) {
		yaml.set("sounds.win.isCustom", machine.getWinSound().isCustomKey());
		yaml.set("sounds.win.key", machine.getWinSound().getSaveString());
	}
}
