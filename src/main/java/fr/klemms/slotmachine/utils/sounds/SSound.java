package fr.klemms.slotmachine.utils.sounds;

import org.apache.commons.lang3.EnumUtils;
import org.bukkit.Sound;

public class SSound {

    public static SSound fromInput(String inputSound) {
        if (EnumUtils.isValidEnum(Sound.class, inputSound)) {
            return new SSound(Sound.valueOf(inputSound));
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

    public boolean isSound() {
        return this.sound != null;
    }

    public boolean isCustomKey() {
        return this.customKey != null;
    }

    @Override
    public String toString() {
        if (this.customKey != null) {
            return this.customKey;
        }
        return this.sound.toString();
    }
}
