package fr.klemms.slotmachine;

import java.util.List;
import java.util.UUID;

public class SlotMachineEntityLink extends SlotMachineEntity implements SlotMachineLink {

	public static synchronized SlotMachineEntity getAllSlotMachineByEntityUUID(UUID entityUUID) {
		List<SlotMachine> slotMachines = SlotMachine.getSlotMachinesByType(SlotMachineType.ENTITY);
		slotMachines.addAll(SlotMachine.getSlotMachinesByType(SlotMachineType.ENTITY_LINK));
		for (SlotMachine slotMachine : slotMachines) {
			if (((SlotMachineEntity) slotMachine).getEntityUUID().compareTo(entityUUID) == 0) {
				return ((SlotMachineEntity) slotMachine);
			}
		}
		return null;
	}

	private UUID linkTo;

	public SlotMachineEntityLink(UUID linkTo, UUID entityUID) {
		super(SlotMachineType.ENTITY_LINK, entityUID);
		this.linkTo = linkTo;
	}

	public UUID getLinkTo() {
		return linkTo;
	}

	public void setLinkTo(UUID linkTo) {
		this.linkTo = linkTo;
	}

	public SlotMachine getLink() {
		return SlotMachine.getSlotMachineByUUID(this.linkTo);
	}
}
