package fr.klemms.slotmachine.interraction.machinemenu;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.interraction.StringInput;
import fr.klemms.slotmachine.utils.PlayerUtil;
import org.apache.commons.lang3.math.NumberUtils;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class MenuItemChangePlayDuration extends MenuItem {
    @Override
    public ItemStack getMaterial(SlotMachine machine, Player player) {
        return new ItemStack(Material.CLOCK, 1);
    }

    @Override
    public String getTitle(SlotMachine machine, Player player) {
        return ChatContent.GOLD + "Change Play Duration";
    }

    @Override
    public List<String> getDescription(SlotMachine machine, Player player) {
        return Arrays.asList(
                ChatContent.AQUA + ChatContent.ITALIC + "Change how much time the items",
                ChatContent.AQUA + ChatContent.ITALIC + "in the machine are 'spinning'",
                ChatContent.AQUA + ChatContent.ITALIC,
                ChatContent.GRAY + "Note : This is accurate only when",
                ChatContent.GRAY + "using default spin-speed (6)",
                "",
                ChatContent.AQUA + ChatContent.ITALIC + "Current duration (min 1s) :",
                ChatContent.RESET + machine.getSecondsBeforePrize() + "s"
        );
    }

    @Override
    public void onClick(SlotMachine machine, Player player, ClickType clickType, MenuState state) {
        switch (clickType) {
            case LEFT:
                player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);

                StringInput.inputString(
                        player,
                        "Change Play Duration",
                        String.valueOf(machine.getSecondsBeforePrize()),
                        text -> {
                            if (!text.isEmpty()) {
                                if (!NumberUtils.isParsable(text)) {
                                    PlayerUtil.sendErrorMessage(player, "Please input a number (integer)");
                                    return;
                                }

                                int number = (int)Double.parseDouble(text);
                                if (number < 1 || number > 30) {
                                    PlayerUtil.sendErrorMessage(player, "Please input a valid number (integer) : [1-30]");
                                    return;
                                }

                                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.8F, 2F);

                                PlayerUtil.sendSuccessMessage(player, "Successfully changed play duration to " + number + "s");

                                machine.setSecondsBeforePrize(number);
                                machine.save();

                                state.reloadPage();
                            } else {
                                PlayerUtil.sendErrorMessage(player, "Duration can't be empty");
                            }
                        },
                        true,
                        false,
                        false,
                        new ItemStack(Material.CLOCK),
                        new ItemStack(Material.CLOCK)
                );
                break;
        }
    }
}
