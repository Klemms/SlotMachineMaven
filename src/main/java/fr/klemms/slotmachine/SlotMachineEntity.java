package fr.klemms.slotmachine;

import fr.klemms.slotmachine.utils.EntityUtil;
import org.bukkit.entity.Entity;

import java.util.List;
import java.util.UUID;

public class SlotMachineEntity extends SlotMachine {
	
	public static synchronized SlotMachineEntity getSlotMachineByEntityUUID(UUID entityUUID) {
		List<SlotMachine> slotMachines = SlotMachine.getSlotMachinesByType(SlotMachineType.ENTITY);
		for(int a = 0; a < slotMachines.size(); a++) {
			if(((SlotMachineEntity)slotMachines.get(a)).getEntityUUID().compareTo(entityUUID) == 0) {
				return ((SlotMachineEntity)slotMachines.get(a));
			}
		}
		return null;
	}
	
	public static synchronized void addSlotMachineEntity(SlotMachineEntity slotMachineEntity) {
		SlotMachine.addSlotMachine(slotMachineEntity);
	}
	
	public static synchronized void removeSlotMachineEntity(UUID entityUUID) {
		removeSlotMachine(getSlotMachineByEntityUUID(entityUUID));
	}
	
	private UUID entityUUID;

	public SlotMachineEntity(UUID entityUUID, UUID worldUID, int chunkX, int chunkZ) {
		super(SlotMachineType.ENTITY, worldUID, chunkX, chunkZ);
		this.entityUUID = entityUUID;
	}

	public UUID getEntityUUID() {
		return entityUUID;
	}

	public SlotMachineEntity setEntityUUID(UUID entityUUID) {
		this.entityUUID = entityUUID;
		return this;
	}

	public Entity getEntity() {
		return EntityUtil.getEntityByUID(this.getWorldUID(), this.getChunkX(), this.getChunkZ(), this.getEntityUUID());
	}
}
