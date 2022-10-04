package fr.klemms.slotmachine;

import fr.klemms.slotmachine.utils.EntityUtil;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.entity.Entity;

import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

public class SlotMachineEntity extends SlotMachine {
	
	public static synchronized SlotMachineEntity getSlotMachineByEntityUUID(UUID entityUUID) {
		List<SlotMachine> slotMachines = SlotMachine.getSlotMachinesByType(SlotMachineType.ENTITY);
		for (SlotMachine slotMachine : slotMachines) {
			if (((SlotMachineEntity) slotMachine).getEntityUUID().compareTo(entityUUID) == 0) {
				return ((SlotMachineEntity) slotMachine);
			}
		}
		return null;
	}
	
	public static synchronized void addSlotMachineEntity(SlotMachineEntity slotMachineEntity) {
		if (SlotMachineEntityLink.getAllSlotMachineByEntityUUID(slotMachineEntity.entityUUID) == null) {
			SlotMachine.addSlotMachine(slotMachineEntity);
		} else {
			SlotPlugin.pl.getLogger().log(Level.SEVERE, "Slot Machine " + slotMachineEntity.getMachineUUID().toString() + " is duplicated ! Ignoring this one...");
			Issue.newIssue(Issue.IssueType.MACHINE_READING_ISSUE, "Slot Machine " + slotMachineEntity.getMachineUUID().toString() + " is duplicated ! Ignoring this one...", true);
		}
	}
	
	public static synchronized void removeSlotMachineEntity(UUID entityUUID) {
		removeSlotMachine(getSlotMachineByEntityUUID(entityUUID));
	}
	
	private UUID entityUUID;

	public SlotMachineEntity(UUID entityUUID) {
		this(SlotMachineType.ENTITY, entityUUID);
	}

	public SlotMachineEntity(SlotMachineType slotMachineType, UUID entityUUID) {
		super(slotMachineType);
		this.entityUUID = entityUUID;
	}

	public UUID getEntityUUID() {
		return entityUUID;
	}

	public SlotMachineEntity setEntityUUID(UUID entityUUID) {
		this.entityUUID = entityUUID;
		return this;
	}

	/**
	 * This will NOT load chunks
	 * @return
	 */
	public Entity getEntity() {
		if (this.isCitizensNPC()) {
			NPC npc = CitizensAPI.getNPCRegistry().getByUniqueId(this.getEntityUUID());
			
			if (npc != null)
				return npc.getEntity();
		}
		
		return EntityUtil.getEntityByUUID(this.getEntityUUID());
	}
}
