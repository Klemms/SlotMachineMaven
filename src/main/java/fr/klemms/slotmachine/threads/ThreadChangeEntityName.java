package fr.klemms.slotmachine.threads;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.utils.EntityUtil;
import org.bukkit.entity.Entity;

import java.util.UUID;

public class ThreadChangeEntityName extends Thread {
	
	private UUID worldUID;
	private UUID entityUID;
	private int chunkX;
	private int chunkZ;
	private String customName;
	
	public ThreadChangeEntityName(UUID worldUID, UUID entityUID, int chunkX, int chunkZ, String customName) {
		this.worldUID = worldUID;
		this.entityUID = entityUID;
		this.chunkX = chunkX;
		this.chunkZ = chunkZ;
		this.customName = customName;
	}
	
	@Override
	public void run() {
		Entity entity = EntityUtil.getEntityByUUIDLoadChunks(this.worldUID, this.chunkX, this.chunkZ, this.entityUID);
		if(entity != null) {
			if(!this.customName.equals("null")) {
				entity.setCustomName(ChatContent.translateColorCodes(this.customName));
				entity.setCustomNameVisible(true);
			} else {
				entity.setCustomName("");
				entity.setCustomNameVisible(false);
			}
		}
	}
}
