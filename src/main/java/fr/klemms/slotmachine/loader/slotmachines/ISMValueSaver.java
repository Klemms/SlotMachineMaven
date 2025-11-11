package fr.klemms.slotmachine.loader.slotmachines;

import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.loader.migrators.IMigrator;
import org.bukkit.configuration.file.YamlConfiguration;

public interface ISMValueSaver {

	public IMigrator<SlotMachine> migrator();

	public void load(YamlConfiguration yaml, SlotMachine machine);

	public void save(YamlConfiguration yaml, SlotMachine machine);
}
