package fr.klemms.slotmachine.interraction.rewards;

import fr.klemms.slotmachine.MachineItem;

public interface RewardCallback {

	public void callback(MachineItem.RewardType rewardType);
}
