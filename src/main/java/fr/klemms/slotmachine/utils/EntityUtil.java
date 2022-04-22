package fr.klemms.slotmachine.utils;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;

public class EntityUtil {

	public static Entity getEntityByUUID(UUID entityUUID) {
		return Bukkit.getEntity(entityUUID);
	}
	
	/**
	 * This doesnt work because fuck spigot
	 * @param worldUID
	 * @param chunkX
	 * @param chunkZ
	 * @param entityUUID
	 * @return
	 */
	public static Entity getEntityByUUIDLoadChunks(UUID worldUID, int chunkX, int chunkZ, UUID entityUUID) {
		Entity[] entities = Bukkit.getWorld(worldUID).getChunkAt(chunkX, chunkZ).getEntities();
		
		for(Entity entity : entities) {
			if(entity.getUniqueId().compareTo(entityUUID) == 0) {
				return entity;
			}
		}
		
		return null;
    }
}
