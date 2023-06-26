package fr.klemms.slotmachine.threads;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.utils.EntityUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;

import java.util.UUID;

public class ThreadChangeEntityName extends Thread {

	private UUID entityUID;
	private String customName;
	
	public ThreadChangeEntityName(UUID entityUID, String customName) {
		this.entityUID = entityUID;
		this.customName = customName;
	}
	
	@Override
	public void run() {
		Entity entity = Bukkit.getEntity(this.entityUID);
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
