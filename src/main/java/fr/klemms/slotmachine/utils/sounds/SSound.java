package fr.klemms.slotmachine.utils.sounds;

import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.utils.Util;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class SSound {

    public static SSound fromInput(String inputSound) {
        NamespacedKey key = NamespacedKey.fromString(inputSound);
        if (key != null) {
            Sound s = Registry.SOUNDS.get(key);

            if (s != null) {
                return new SSound(s);
            }
        }

        if (inputSound.startsWith("minecraft:") || inputSound.contains("CraftSound{") || !StringUtils.isAlphanumeric(inputSound
                .replace(":", "")
                .replace("_", "")
                .replace(".", ""))) {
            SlotPlugin.pl.getLogger().log(Level.WARNING, "An invalid Sound was detected : '" + inputSound + "', if this is a custom sound, their name shouldn't contain any special character other than _. If this isn't a custom sound this may be due to a Spigot/Paper upgrade.");
            return null;
        }

        return new SSound(inputSound);
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

        return this.sound.getKey().getKey();
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
        return Util.prettifyMCEnum(this.getSound().name());
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
