package fr.klemms.slotmachine.threads;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class ThreadPlaySound extends Thread {
	
	private Sound sound;
	private float volume;
	private float pitch;
	private Player player;
	
	public ThreadPlaySound(Sound sound, float volume, float pitch, Player player) {
		this.sound = sound;
		this.volume = volume;
		this.pitch = pitch;
		this.player = player;
	}
	
	@Override
	public void run() {
		player.playSound(player.getLocation(), this.sound, this.volume, this.pitch);
	}
}
