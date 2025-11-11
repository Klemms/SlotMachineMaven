package fr.klemms.slotmachine.loader.slotmachines;

import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.loader.migrators.IMigrator;
import org.bukkit.configuration.file.YamlConfiguration;

public class SMSaveCommandOnLoss implements ISMValueSaver {
	@Override
	public IMigrator<SlotMachine> migrator() {
		return new IMigrator<SlotMachine>() {
			@Override
			public boolean needsMigration(YamlConfiguration yaml) {
				return yaml.isSet("commands.onLoss.command") && !yaml.isString("commands.onLoss.command");
			}

			@Override
			public void migrate(YamlConfiguration yaml, SlotMachine obj) {
				obj.setCommandLoss(null);
			}
		};
	}

	@Override
	public void load(YamlConfiguration yaml, SlotMachine machine) {
		if (yaml.isString("commands.onLoss.command")) {
			machine.setCommandLoss(yaml.getString("commands.onLoss.command"));
		}
	}

	@Override
	public void save(YamlConfiguration yaml, SlotMachine machine) {
		yaml.set("commands.onLoss.command", machine.getCommandLoss());
	}
}
