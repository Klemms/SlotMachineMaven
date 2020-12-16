package fr.klemms.slotmachine.utils;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;

import java.util.UUID;

public class EntityUtil {

	public static Entity getEntityInWorld(UUID entityUUID) {
		for(World world : Bukkit.getWorlds()) {
			for(Entity entity : world.getEntities()) {
				if(entity.getUniqueId().compareTo(entityUUID) == 0) {
					return entity;
				}
			}
		}
		return null;
	}
	
	public static Entity getEntityByUID(UUID worldUID, int chunkX, int chunkZ, UUID entityUUID) {
		for(World world : Bukkit.getWorlds()) {
			world.loadChunk(chunkX, chunkZ);
			for(Entity entity : world.getEntities()) {
				if(entity.getUniqueId().compareTo(entityUUID) == 0) {
					return entity;
				}
			}
		}
		return null;
    }
}
