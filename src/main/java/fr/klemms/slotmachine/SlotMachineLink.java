package fr.klemms.slotmachine;

import java.util.UUID;

public interface SlotMachineLink {

	public UUID getLinkTo();

	public void setLinkTo(UUID linkTo);

	public SlotMachine getLink();
}
