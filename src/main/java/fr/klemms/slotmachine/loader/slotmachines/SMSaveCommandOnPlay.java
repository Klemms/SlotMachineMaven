package fr.klemms.slotmachine.loader.slotmachines;

import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.loader.migrators.IMigrator;
import org.bukkit.configuration.file.YamlConfiguration;

public class SMSaveCommandOnPlay implements ISMValueSaver {
	@Override
	public IMigrator<SlotMachine> migrator() {
		return new IMigrator<SlotMachine>() {
			@Override
			public boolean needsMigration(YamlConfiguration yaml) {
				return yaml.isSet("commands.onPlay.command") && !yaml.isString("commands.onPlay.command");
			}

			@Override
			public void migrate(YamlConfiguration yaml, SlotMachine obj) {
				obj.setCommandPlay(null);
			}
		};
	}

	@Override
	public void load(YamlConfiguration yaml, SlotMachine machine) {
		if (yaml.isString("commands.onPlay.command")) {
			machine.setCommandPlay(yaml.getString("commands.onPlay.command"));
		}
	}

	@Override
	public void save(YamlConfiguration yaml, SlotMachine machine) {
		yaml.set("commands.onPlay.command", machine.getCommandPlay());
	}
}
