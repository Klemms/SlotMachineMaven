package fr.klemms.slotmachine;

import java.util.UUID;

public class SlotMachineEntityLink extends SlotMachineEntity {

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
