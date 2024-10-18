package fr.klemms.slotmachine.interraction.machinemenu;

import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.fr.minuskube.inv.ClickableItem;
import fr.klemms.slotmachine.utils.ItemStackUtil;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public abstract class MenuItem {

    public static ClickableItem getMenuItem(SlotMachine machine, Player player, MenuItem menuItem, MenuState state) {
        ItemStack is = new ItemStack(menuItem.getMaterial(machine, player));
        is = ItemStackUtil.changeItemStackName(is, menuItem.getTitle(machine, player));
        is = ItemStackUtil.setItemStackLore(is, menuItem.getDescription(machine, player));

        if (menuItem.hasGlow(machine, player)) {
            is = ItemStackUtil.addGlow(is);
        }

        return ClickableItem.of(is, event -> {
            ClickType clickType = event.isLeftClick() ? ClickType.LEFT : (event.isRightClick() ? ClickType.RIGHT : ClickType.MIDDLE);

            menuItem.onClick(machine, player, clickType, state);
        });
    }

    public abstract ItemStack getMaterial(SlotMachine machine, Player player);

    public abstract String getTitle(SlotMachine machine, Player player);

    public abstract List<String> getDescription(SlotMachine machine, Player player);

    public abstract void onClick(SlotMachine machine, Player player, ClickType clickType, MenuState state);

    public boolean hasGlow(SlotMachine machine, Player player) {
        return false;
    }
}
