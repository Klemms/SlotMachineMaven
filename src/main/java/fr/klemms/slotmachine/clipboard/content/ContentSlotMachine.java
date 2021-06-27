package fr.klemms.slotmachine.clipboard.content;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.clipboard.ClipboardContent;

public class ContentSlotMachine extends Content {

	@Override
	public void copyContent(Player player, SlotMachine inputMachine, SlotMachine outputMachine) {
		outputMachine.setGuiPermission(inputMachine.getGuiPermission());
		outputMachine.setSlotMachineName(inputMachine.getSlotMachineName());
		
		outputMachine.setWinMessage(inputMachine.getWinMessage());
		outputMachine.setLossMessage(inputMachine.getLossMessage());
		outputMachine.setWinMessage(inputMachine.getWinMessage());
		outputMachine.setHasWinMessage(inputMachine.hasWinMessage());
		outputMachine.setHasLossMessage(inputMachine.hasLossMessage());

		outputMachine.setLeverCustom(inputMachine.isLeverCustom());
		outputMachine.setLeverTitle(inputMachine.getLeverTitle());
		outputMachine.setLeverDescription(inputMachine.getLeverDescription());
		
		outputMachine.setVisualType(inputMachine.getVisualType());
		outputMachine.setPriceType(inputMachine.getPriceType());
		outputMachine.setPlayMode(inputMachine.getPlayMode());
		
		outputMachine.setPullPrice(inputMachine.getPullPrice());
		outputMachine.setChanceToWin(inputMachine.getChanceToWin());
		outputMachine.setAffectedByLuck(inputMachine.isAffectedByLuck());
		outputMachine.allowContentPreview(inputMachine.allowContentPreview());
		outputMachine.showItemWeightOnPreview(inputMachine.showItemWeightOnPreview());
		outputMachine.showChanceOfItemOnPreview(inputMachine.showChanceOfItemOnPreview());
		outputMachine.setCitizensNPC(inputMachine.isCitizensNPC());
		outputMachine.setDisplayWonItemInChat(inputMachine.isDisplayWonItemInChat());
		outputMachine.setSecondsBeforePrize(inputMachine.getSecondsBeforePrize());
		outputMachine.setSpinSpeed(inputMachine.getSpinSpeed());
		outputMachine.setCooldown(inputMachine.getCooldown());
		outputMachine.setTokenIdentifier(inputMachine.getTokenIdentifier());
		
		outputMachine.setBackgroundItem(new ItemStack(inputMachine.getBackgroundItem()));
		outputMachine.setEmphasisItem(new ItemStack(inputMachine.getEmphasisItem()));
		outputMachine.setLeverItem(new ItemStack(inputMachine.getLeverItem()));
		outputMachine.setItemListItem(new ItemStack(inputMachine.getItemListItem()));

		outputMachine.setMachineOpeningSound(inputMachine.getMachineOpeningSound());
		outputMachine.setLeverSound(inputMachine.getLeverSound());
		outputMachine.setSlotmachineSpinSound(inputMachine.getSlotmachineSpinSound());
		outputMachine.setErrorSound(inputMachine.getErrorSound());
		outputMachine.setCsgoSpinSound(inputMachine.getCsgoSpinSound());
		outputMachine.setWinSound(inputMachine.getWinSound());
		outputMachine.setLossSound(inputMachine.getLossSound());
		
		ClipboardContent.ITEMLIST.contentCopier.copyContent(player, inputMachine, outputMachine);
	}

}
