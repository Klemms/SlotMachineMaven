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

import java.util.Arrays;
import java.util.List;

public class MenuItemChangeLeverName extends MenuItem {
    @Override
    public ItemStack getMaterial(SlotMachine machine, Player player) {
        return new ItemStack(Material.TRIPWIRE_HOOK, 1);
    }

    @Override
    public String getTitle(SlotMachine machine, Player player) {
        return ChatContent.GOLD + "Change Lever Name";
    }

    @Override
    public List<String> getDescription(SlotMachine machine, Player player) {
        return Arrays.asList(
                ChatContent.AQUA + ChatContent.ITALIC + "Change the lever's name",
                "",
                ChatContent.RED + "Right Click" + ChatContent.AQUA + ChatContent.ITALIC + " to reset to ",
                ChatContent.AQUA + ChatContent.ITALIC + "default name and description",
                "",
                ChatContent.AQUA + ChatContent.ITALIC + "Current name :",
                ChatContent.RESET + Variables.getFormattedString(machine.getLeverTitle(), player, machine)
        );
    }

    @Override
    public void onClick(SlotMachine machine, Player player, ClickType clickType, MenuState state) {
        switch (clickType) {
            case LEFT:
                player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);

                StringInput.inputString(
                        player,
                        "Change Lever Name",
                        machine.getLeverTitle(),
                        text -> {
                            if (text.length() > 0) {
                                player.closeInventory();
                                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.8F, 2F);

                                player.sendMessage(
                                        ChatContent.GREEN +
                                                SlotPlugin.CHAT_PREFIX +
                                                "Successfully set lever title to : " +
                                                ChatContent.RESET +
                                                text
                                );
                                player.sendMessage(
                                        ChatContent.GREEN +
                                                SlotPlugin.CHAT_PREFIX +
                                                "Lever title preview : " +
                                                ChatContent.RESET +
                                                Variables.getFormattedString(text, player, machine)
                                );

                                machine.setLeverTitle(text);
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

    @Override
    public boolean hasGlow(SlotMachine machine, Player player) {
        return true;
    }
}
