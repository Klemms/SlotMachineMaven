package fr.klemms.slotmachine.saving;

import org.json.JSONObject;

import java.util.UUID;

public class SMBlockData {

	public UUID worldUUID;
	public int x;
	public int y;
	public int z;
	public boolean isLocked;

	public SMBlockData(UUID worldUUID, int x, int y, int z,  boolean isLocked) {
		this.worldUUID = worldUUID;
		this.x = x;
		this.y = y;
		this.z = z;
		this.isLocked = isLocked;
	}

	public JSONObject toJson() {
		return new JSONObject(this);
	}
}
