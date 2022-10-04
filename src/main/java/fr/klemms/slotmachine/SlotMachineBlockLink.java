package fr.klemms.slotmachine;

import java.util.UUID;

public class SlotMachineBlockLink extends SlotMachineBlock {

	private UUID linkTo;

	public SlotMachineBlockLink(UUID linkTo, int blockX, int blockY, int blockZ, boolean isLocked, UUID worldUID) {
		super(SlotMachineType.BLOCK_LINK, blockX, blockY, blockZ, isLocked, worldUID);
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
