package fr.klemms.slotmachine.interraction.machinemenu;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.interraction.StringInput;
import fr.klemms.slotmachine.utils.PlayerHeadsUtil;
import fr.klemms.slotmachine.utils.PlayerUtil;
import org.apache.commons.lang3.math.NumberUtils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class MenuItemChangeCooldown extends MenuItem {
    @Override
    public ItemStack getMaterial(SlotMachine machine, Player player) {
        return new ItemStack(PlayerHeadsUtil.CLOCK);
    }

    @Override
    public String getTitle(SlotMachine machine, Player player) {
        return ChatContent.GOLD + "Set Cooldown";
    }

    @Override
    public List<String> getDescription(SlotMachine machine, Player player) {
        return Arrays.asList(
                ChatContent.AQUA + ChatContent.ITALIC + "Sets this machine cooldown.",
                ChatContent.AQUA + ChatContent.ITALIC,
                ChatContent.AQUA + ChatContent.ITALIC + "- Value is in seconds",
                ChatContent.AQUA + ChatContent.ITALIC + "- Cooldown is per-player",
                ChatContent.AQUA + ChatContent.ITALIC + "- 0s means no cooldown",
                ChatContent.AQUA + ChatContent.ITALIC,
                ChatContent.AQUA + ChatContent.ITALIC + "Current cooldown :",
                ChatContent.RESET + (machine.getCooldown()) + "s"
        );
    }

    @Override
    public void onClick(SlotMachine machine, Player player, ClickType clickType, MenuState state) {
        switch (clickType) {
            case LEFT:
                player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);

                StringInput.inputString(
                        player,
                        "Set Cooldown",
                        String.valueOf(machine.getCooldown()),
                        text -> {
                            if (!text.isEmpty()) {
                                if (!NumberUtils.isParsable(text)) {
                                    PlayerUtil.sendErrorMessage(player, "Please input a number (integer)");
                                    return;
                                }

                                int number = (int) Double.parseDouble(text);
                                if (number < 0) {
                                    PlayerUtil.sendErrorMessage(player, "Please input a valid number (integer) from 0 to 2 billion");
                                    return;
                                }

                                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.8F, 2F);

                                PlayerUtil.sendSuccessMessage(player, "Successfully changed cooldown to " + number + "s");

                                machine.setCooldown(number);
                                machine.save();

                                state.reloadPage();
                            } else {
                                PlayerUtil.sendErrorMessage(player, "Cooldown can't be empty");
                            }
                        },
                        true,
                        false,
                        false,
                        new ItemStack(PlayerHeadsUtil.CLOCK),
                        new ItemStack(PlayerHeadsUtil.CLOCK)
                );
                break;
        }
    }
}
