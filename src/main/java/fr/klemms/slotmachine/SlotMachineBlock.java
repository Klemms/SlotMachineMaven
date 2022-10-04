package fr.klemms.slotmachine;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

public class SlotMachineBlock extends SlotMachine {

	public static synchronized SlotMachineBlock getSlotMachineByBlock(Block block) {
		List<SlotMachine> slotMachines = SlotMachine.getSlotMachinesByType(SlotMachineType.BLOCK);

		for (SlotMachine machine : slotMachines) {
			SlotMachineBlock machineBlock = (SlotMachineBlock) machine;

			if (machineBlock.isSame(block)) {
				return machineBlock;
			}
		}
		return null;
	}
	
	public static synchronized void addSlotMachineBlock(SlotMachineBlock slotMachineBlock) {
		if (SlotMachineBlock.getSlotMachineByBlock(slotMachineBlock.getBlock()) == null) {
			SlotMachine.addSlotMachine(slotMachineBlock);
		} else {
			SlotPlugin.pl.getLogger().log(Level.SEVERE, "Slot Machine " + slotMachineBlock.getMachineUUID().toString() + " is duplicated ! Ignoring this one...");
			Issue.newIssue(Issue.IssueType.MACHINE_READING_ISSUE, "Slot Machine " + slotMachineBlock.getMachineUUID().toString() + " is duplicated ! Ignoring this one...", true);
		}
	}
	
	public static synchronized void removeSlotMachineBlock(Block block) {
		removeSlotMachine(getSlotMachineByBlock(block));
	}

	private UUID worldUID;
	private int blockX;
	private int blockY;
	private int blockZ;
	private boolean isLocked;

	public SlotMachineBlock(int blockX, int blockY, int blockZ, boolean isLocked, UUID worldUID) {
		this(SlotMachineType.BLOCK, blockX, blockY, blockZ, isLocked, worldUID);
	}

	public SlotMachineBlock(SlotMachineType slotMachineType, int blockX, int blockY, int blockZ, boolean isLocked, UUID worldUID) {
		super(slotMachineType);
		this.worldUID = worldUID;
		this.blockX = blockX;
		this.blockY = blockY;
		this.blockZ = blockZ;
		this.isLocked = isLocked;
	}

	public UUID getWorldUID() {
		return worldUID;
	}

	public void setWorldUID(UUID worldUID) {
		this.worldUID = worldUID;
	}

	public int getBlockX() {
		return blockX;
	}

	public void setBlockX(int blockX) {
		this.blockX = blockX;
	}

	public int getBlockY() {
		return blockY;
	}

	public void setBlockY(int blockY) {
		this.blockY = blockY;
	}

	public int getBlockZ() {
		return blockZ;
	}

	public void setBlockZ(int blockZ) {
		this.blockZ = blockZ;
	}

	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	public Block getBlock() {
		return Bukkit.getWorld(this.getWorldUID()).getBlockAt(this.getBlockX(), this.getBlockY(), this.getBlockZ());
	}

	public boolean isSame(Block block) {
		World world = Bukkit.getWorld(this.getWorldUID());

		if (world != null) {
			if (block.getWorld().equals(world)) {
				Block thisBlock = world.getBlockAt(this.getBlockX(), this.getBlockY(), this.getBlockZ());
				if (thisBlock != null) {
					return block.equals(thisBlock);
				}
			}
		}
		return false;
	}
}
