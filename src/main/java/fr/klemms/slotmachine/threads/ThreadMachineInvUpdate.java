package fr.klemms.slotmachine.threads;

import fr.klemms.slotmachine.MachineItem;
import fr.klemms.slotmachine.fr.minuskube.inv.ClickableItem;
import fr.klemms.slotmachine.fr.minuskube.inv.content.InventoryContents;

public class ThreadMachineInvUpdate extends Thread {

	private final int column;
	private final int row;
	private final MachineItem machineItem;
	private final InventoryContents contents;
	private final boolean affectedByModifiers;
	
	public ThreadMachineInvUpdate(int column, int row, MachineItem machineItem, InventoryContents contents, boolean affectedByModifiers) {
		this.column = column;
		this.machineItem = machineItem;
		this.row = row;
		this.contents = contents;
		this.affectedByModifiers = affectedByModifiers;
	}
	
	@Override
	public void run() {
		contents.set(row, column, ClickableItem.empty(machineItem.getItemStack(affectedByModifiers)));
	}
}
