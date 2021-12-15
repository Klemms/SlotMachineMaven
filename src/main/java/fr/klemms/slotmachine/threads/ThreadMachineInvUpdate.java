package fr.klemms.slotmachine.threads;

import org.bukkit.entity.Player;

import fr.klemms.slotmachine.MachineItem;
import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.fr.minuskube.inv.ClickableItem;
import fr.klemms.slotmachine.fr.minuskube.inv.content.InventoryContents;

public class ThreadMachineInvUpdate extends Thread {
	
	private SlotMachine machine;
	private Player player;
	private int column;
	private int row;
	private MachineItem machineItem;
	private InventoryContents contents;
	
	public ThreadMachineInvUpdate(Player player, SlotMachine machine, int column, int row, MachineItem machineItem, InventoryContents contents) {
		this.player = player;
		this.machine = machine;
		this.column = column;
		this.machineItem = machineItem;
		this.row = row;
		this.contents = contents;
	}
	
	@Override
	public void run() {
		contents.set(row, column, ClickableItem.empty(machineItem.getItemStack()));
		
		
		/*List<MachineItem> listRow = row == 0 ? machine.getPlayerRow0(player) : (row == 1 ? machine.getPlayerRow1(player) : machine.getPlayerRow2(player));
		System.out.println(machineItem.getItemStack());
		System.out.println(machine.getPlayerRow0(player).get(slot).getItemStack());
		if (row == 0) {
			listRow.set(slot, machineItem);
			machine.setPlayerRow0(player, listRow);
		} else if (row == 1) {
			listRow.set(slot, machineItem);
			machine.setPlayerRow1(player, listRow);
		} else if (row == 2) {
			listRow.set(slot, machineItem);
			machine.setPlayerRow2(player, listRow);
		}
		System.out.println(machine.getPlayerRow0(player).get(slot).getItemStack());
		System.out.println("-----");*/
	}
}
