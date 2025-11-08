package fr.klemms.slotmachine;

import org.bukkit.block.Block;

import java.util.List;
import java.util.UUID;

public class SlotMachineBlockLink extends SlotMachineBlock implements SlotMachineLink {

	public static synchronized SlotMachineBlock getAllSlotMachineByBlock(Block block) {
		List<SlotMachine> slotMachines = SlotMachine.getSlotMachinesByType(SlotMachineType.BLOCK);
		slotMachines.addAll(SlotMachine.getSlotMachinesByType(SlotMachineType.BLOCK_LINK));

		for (SlotMachine machine : slotMachines) {
			SlotMachineBlock machineBlock = (SlotMachineBlock) machine;

			if (machineBlock.isSame(block)) {
				return machineBlock;
			}
		}
		return null;
	}

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
