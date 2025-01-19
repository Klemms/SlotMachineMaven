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

public class MenuItemChangeChanceToWin extends MenuItem {
    @Override
    public ItemStack getMaterial(SlotMachine machine, Player player) {
        return new ItemStack(Material.LARGE_FERN, 1);
    }

    @Override
    public String getTitle(SlotMachine machine, Player player) {
        return ChatContent.GOLD + "Change Chance to Win";
    }

    @Override
    public List<String> getDescription(SlotMachine machine, Player player) {
        return Arrays.asList(
                ChatContent.AQUA + ChatContent.ITALIC + "Change the chance players",
                ChatContent.AQUA + ChatContent.ITALIC + "have to win",
                "",
                ChatContent.AQUA + ChatContent.ITALIC + "Current chance :",
                ChatContent.RESET + (machine.getChanceToWin() * 100) + "%"
        );
    }

    @Override
    public void onClick(SlotMachine machine, Player player, ClickType clickType, MenuState state) {
        switch (clickType) {
            case LEFT:
                player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);

                StringInput.inputString(
                        player,
                        "Change Chance to Win",
                        String.valueOf(machine.getChanceToWin() * 100),
                        text -> {
                            if (!text.isEmpty()) {
                                if (!NumberUtils.isParsable(text)) {
                                    PlayerUtil.sendErrorMessage(player, "Please input a number. Decimals must be separated with a dot : .");
                                    return;
                                }

                                double number = Double.parseDouble(text);
                                if (number < 0D || number > 100D) {
                                    PlayerUtil.sendErrorMessage(player, "Please input a valid number : [0-100]. Decimals must be separated with a dot : .");
                                    return;
                                }

                                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.8F, 2F);

                                PlayerUtil.sendSuccessMessage(player, "Successfully changed chance to win to " + number + "%");

                                machine.setChanceToWin(number / 100D);
                                machine.save();

                                state.reloadPage();
                            } else {
                                PlayerUtil.sendErrorMessage(player, "Chance can't be empty");
                            }
                        },
                        true,
                        false,
                        false,
                        new ItemStack(Material.LARGE_FERN),
                        new ItemStack(Material.LARGE_FERN)
                );
                break;
        }
    }
}
