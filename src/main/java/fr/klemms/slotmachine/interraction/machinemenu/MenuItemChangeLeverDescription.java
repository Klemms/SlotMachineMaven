package fr.klemms.slotmachine.interraction.machinemenu;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.interraction.StringInput;
import fr.klemms.slotmachine.placeholders.Variables;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class MenuItemChangeLeverDescription extends MenuItem {
    @Override
    public ItemStack getMaterial(SlotMachine machine, Player player) {
        return new ItemStack(Material.TRIPWIRE_HOOK, 1);
    }

    @Override
    public String getTitle(SlotMachine machine, Player player) {
        return ChatContent.GOLD + "Change Lever Description";
    }

    @Override
    public List<String> getDescription(SlotMachine machine, Player player) {
        List<String> leverDescLore = new ArrayList<String>();
        leverDescLore.add(ChatContent.AQUA + ChatContent.ITALIC + "Change the lever's description");
        leverDescLore.add("");
        leverDescLore.add(ChatContent.RED + "Right Click" + ChatContent.AQUA + ChatContent.ITALIC + " to reset to ");
        leverDescLore.add(ChatContent.AQUA + ChatContent.ITALIC + "default name and description");
        leverDescLore.add("");
        leverDescLore.add(ChatContent.AQUA + ChatContent.ITALIC + "Current description :");
        leverDescLore.addAll(Variables.getFormattedStrings(machine.getLeverDescription(), player, machine));

        return leverDescLore;
    }

    @Override
    public void onClick(SlotMachine machine, Player player, ClickType clickType, MenuState state) {
        switch (clickType) {
            case LEFT:
                player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);

                StringInput.inputString(
                        player,
                        "Change Lever Description",
                        machine.getLeverDescription(),
                        text -> {
                            if (text.length() > 0) {
                                player.closeInventory();
                                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.8F, 2F);

                                player.sendMessage(
                                        ChatContent.GREEN +
                                                SlotPlugin.CHAT_PREFIX +
                                                "Successfully set lever description to : " +
                                                ChatContent.RESET +
                                                text
                                );
                                player.sendMessage(
                                        ChatContent.GREEN +
                                                SlotPlugin.CHAT_PREFIX +
                                                "Lever description preview : " +
                                                ChatContent.RESET +
                                                Variables.getFormattedString(text, player, machine)
                                );

                                machine.setLeverDescription(text);
                                machine.setLeverCustom(true);
                                machine.save();
                            } else {
                                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 0.8F, 1.5F);

                                player.sendMessage(
                                        ChatContent.RED +
                                                SlotPlugin.CHAT_PREFIX +
                                                "Lever title needs to have at least 1 character"
                                );
                            }
                        },
                        true,
                        true,
                        true
                );
                break;
            case RIGHT:
                player.playSound(player.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1F, 1F);

                player.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + "Successfully reset to default");
                machine.setLeverCustom(false);
                machine.setPriceType(machine.getPriceType());
                machine.save();

                state.reloadPage();
                break;
        }
    }
}
