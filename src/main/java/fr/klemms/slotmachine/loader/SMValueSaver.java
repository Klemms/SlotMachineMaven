package fr.klemms.slotmachine.loader;

import fr.klemms.slotmachine.Config;
import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.loader.migrators.IMigrator;
import fr.klemms.slotmachine.loader.slotmachines.*;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

public class SMValueSaver {

	public static final List<ISMValueSaver> SLOT_MACHINE_SAVERS = Arrays.asList(
			new SMSaveSoundMachineOpen(),
			new SMSaveSoundLeverTrigger(),
			new SMSaveSoundMachineSpin(),
			new SMSaveSoundCSGOSpin(),
			new SMSaveSoundWin(),
			new SMSaveSoundLoss(),
			new SMSaveSoundError()
	);

	public static void load(YamlConfiguration yaml, SlotMachine machine) {
		for (ISMValueSaver loader : SLOT_MACHINE_SAVERS) {
			IMigrator<SlotMachine> migrator = loader.migrator();

			if (migrator.needsMigration(yaml)) {
				if (Config.debug) {
					SlotPlugin.pl.getLogger().log(Level.INFO, "SLOT_MACHINE_SAVERS / Needs migration : " + loader.getClass().getName());
				}
				migrator.migrate(yaml, machine);
			} else {
				loader.load(yaml, machine);
			}
		}
	}

	public static void save(YamlConfiguration yaml, SlotMachine machine) {
		for (ISMValueSaver loader : SLOT_MACHINE_SAVERS) {
			loader.save(yaml, machine);
		}
	}
}
