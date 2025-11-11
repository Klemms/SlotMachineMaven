package fr.klemms.slotmachine.utils.sounds;

import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.utils.Util;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class SSound {

    public static SSound fromInput(String inputSound, boolean isCustom) {
        NamespacedKey key = NamespacedKey.fromString(inputSound);

        if (isCustom) {
            if (key != null) {
                return new SSound(key.toString());
            }
        } else {
            if (key != null) {
                Sound s = Registry.SOUNDS.get(key);

                if (s != null) {
                    return new SSound(s);
                }
            }
            SlotPlugin.pl.getLogger().log(Level.WARNING, "An invalid Sound was detected : '" + inputSound + "'.");
        }

        return null;
    }

    public Sound sound = null;
    public String customKey = null;

    public SSound(Sound sound, String customKey) {
        if (sound != null) {
            this.sound = sound;
        } else if (customKey != null) {
            this.customKey = customKey;
        }
    }

    public SSound(Sound sound) {
        this(sound, null);
    }

    public SSound(String customKey) {
        this(null, customKey);
    }

    public String getKey() {
        if (this.customKey != null) {
            return this.customKey;
        }

        return this.sound.getKey().toString();
    }

    public String getName() {
        if (this.customKey != null) {
            return this.customKey;
        }
        return this.getSound().name();
    }

    public String getPrettyName() {
        if (this.customKey != null) {
            return this.customKey;
        }
        return Util.prettifyMCEnum(this.getSound().getKey().getKey());
    }

    public Sound getSound() {
        return this.sound;
    }

    public void playSound(Player player, float volume, float pitch) {
        if (this.isCustomKey()) {
            player.playSound(player.getLocation(), this.getKey(), volume, pitch);
        } else {
            player.playSound(player, this.getSound(), volume, pitch);
        }
    }

    public void stopSound(Player player) {
        if (this.isCustomKey()) {
            player.stopSound(this.getKey());
        } else {
            player.stopSound(this.getSound());
        }
    }

    public boolean isSound() {
        return this.sound != null;
    }

    public boolean isCustomKey() {
        return this.customKey != null;
    }

    public String getSaveString() {
        if (this.isCustomKey()) {
            return this.getKey();
        }
        return this.getSound().getKey().toString();
    }

    @Override
    public String toString() {
        if (this.customKey != null) {
            return this.customKey;
        }
        return this.sound.toString();
    }
}
