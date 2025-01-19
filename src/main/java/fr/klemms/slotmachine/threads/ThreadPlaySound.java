package fr.klemms.slotmachine.threads;

import fr.klemms.slotmachine.utils.Util;
import fr.klemms.slotmachine.utils.sounds.SSound;
import org.bukkit.entity.Player;

public class ThreadPlaySound extends Thread {

	private SSound sound;
	private float volume;
	private float pitch;
	private Player player;

	public ThreadPlaySound(SSound sound, float volume, float pitch, Player player) {
		this.sound = sound;
		this.volume = volume;
		this.pitch = pitch;
		this.player = player;
	}

	@Override
	public void run() {
		Util.playSoundPlayer(player, sound, this.volume, this.pitch);
	}
}
