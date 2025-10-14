package fr.klemms.slotmachine;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SMPlayerConfig {

	public static SMPlayerConfig deserialize(PlayerConfig cfg, Map<String, Object> map) {
		SMPlayerConfig obj = new SMPlayerConfig(cfg);

		obj.lastUsed = (long) map.getOrDefault("lastUsed", new Long(0L));
		obj.cooldownDuration = (int) map.getOrDefault("cooldownDuration", new Integer(0));

		return obj;
	}

	private final PlayerConfig cfg;
	private long lastUsed;
	private int cooldownDuration;
	public boolean changed;

	public SMPlayerConfig(PlayerConfig cfg) {
		this.cfg = cfg;
		this.lastUsed = 0L;
		this.cooldownDuration = 0;
		this.changed = false;
	}

	public int getCooldownDuration() {
		return this.cooldownDuration;
	}

	public void setCooldownDuration(int cooldownDuration) {
		this.cooldownDuration = cooldownDuration;
		this.changed = true;
	}

	public Date getLastUsed() {
		return new Date(this.lastUsed);
	}

	public int getTimeUsedToNow() {
		return Math.toIntExact((new Date().getTime() / 1000) - (this.getLastUsed().getTime() / 1000));
	}

	public int getRemainingCooldown() {
		int remaining = Math.toIntExact((new Date().getTime() / 1000) - (this.getLastUsed().getTime() / 1000 + this.cooldownDuration));

		if (remaining < 0) {
			return Math.abs(remaining);
		}

		return 0;
	}

	public void setLastUsed(Date dateUsed) {
		this.lastUsed = dateUsed.getTime();
		this.changed = true;
	}

	public void usedNow() {
		this.setLastUsed(new Date());
	}
	
	public SMPlayerConfig changed() {
		this.changed = true;
		
		return this;
	}

	public Map<String, Object> serialize() {
		Map<String, Object> map = new HashMap<>();

		map.put("lastUsed", new Long(this.lastUsed));
		map.put("cooldownDuration", new Integer(this.cooldownDuration));

		return map;
	}

	@Override
	public String toString() {
		return new StringBuilder()
				.append("{")
				.append("changed: ").append(this.changed)
				.append(",lastUsed: ").append(this.lastUsed)
				.append(",cooldownDuration: ").append(this.cooldownDuration)
				.append("}")
				.toString();
	}
}
