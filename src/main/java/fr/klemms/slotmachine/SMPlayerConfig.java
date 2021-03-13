package fr.klemms.slotmachine;

public class SMPlayerConfig {

	private PlayerConfig cfg;
	private int cooldown;
	public boolean changed;

	public SMPlayerConfig(PlayerConfig cfg) {
		this(cfg, 0);
	}
	
	public SMPlayerConfig(PlayerConfig cfg, int cooldown) {
		this.cfg = cfg;
		this.cooldown = cooldown;
		this.changed = false;
	}
	
	public void saveToDisk() {
		cfg.saveToDisk();
	}

	public int getCooldown() {
		return cooldown;
	}

	public void setCooldown(int cooldown) {
		this.changed = true;
		this.cooldown = cooldown;
	}
	
	public SMPlayerConfig changed() {
		this.changed = true;
		
		return this;
	}
}
