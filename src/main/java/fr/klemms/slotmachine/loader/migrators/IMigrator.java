package fr.klemms.slotmachine.loader.migrators;

import org.bukkit.configuration.file.YamlConfiguration;

public interface IMigrator<T> {

	public boolean needsMigration(YamlConfiguration yaml);

	public void migrate(YamlConfiguration yaml, T obj);
}
