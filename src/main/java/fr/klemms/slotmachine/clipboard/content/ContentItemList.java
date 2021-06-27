package fr.klemms.slotmachine.clipboard.content;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import fr.klemms.slotmachine.MachineItem;
import fr.klemms.slotmachine.SlotMachine;

public class ContentItemList extends Content {

	@Override
	public void copyContent(Player player, SlotMachine inputMachine, SlotMachine outputMachine) {
		outputMachine.setSlotMachineItems(copyItems(inputMachine.getSlotMachineItems()));
	}
	
	private List<MachineItem> copyItems(List<MachineItem> items) {
		List<MachineItem> newItems = new ArrayList<MachineItem>(items.size());
		
		for(int i = 0; i < items.size(); i++) {
			newItems.add(items.get(i).copy());
		}
		
		return newItems;
	}

}
