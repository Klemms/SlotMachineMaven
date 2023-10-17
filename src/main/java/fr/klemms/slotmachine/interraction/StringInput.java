package fr.klemms.slotmachine.interraction;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.placeholders.Variables;
import fr.klemms.slotmachine.translation.Language;
import fr.klemms.slotmachine.utils.ItemStackUtil;
import fr.klemms.slotmachine.utils.PlayerHeadsUtil;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StringInput {

	public static void inputString(Player player, String title, String placeholder, StringInputCallback callback) {
		List<String> variableLore = new ArrayList<String>();

		for (Variables var : Variables.values()) {
			variableLore.add(ChatContent.DARK_PURPLE + "$" + var.variableName + ChatContent.DARK_AQUA + " -> " + ChatContent.AQUA + Language.translate(var.variableDescription));
		}

		new AnvilGUI.Builder()
				.plugin(SlotPlugin.pl)
				.title(title)
				.itemLeft(ItemStackUtil.changeItemStackName(new ItemStack(Material.COMMAND_BLOCK), ChatContent.GOLD + "Validate with the green button"))
				.itemRight(ItemStackUtil.setItemStackLore(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.INFOS), ChatContent.GOLD + "You can use those placeholders :"), variableLore))
				.itemOutput(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.CHECKMARK), " "))
				.text(placeholder)
				.onClick((slot, stateSnapshot) -> {
					if (slot !=  AnvilGUI.Slot.OUTPUT) {
						return Collections.emptyList();
					}

					if (!stateSnapshot.getText().trim().isEmpty() && !stateSnapshot.getText().equals(placeholder)) {
						return Collections.singletonList(
                                AnvilGUI.ResponseAction.run(() -> {
									callback.callback(stateSnapshot.getText().trim());
                                })
                        );
					}

					return Collections.emptyList();
				})
				.open(player);
	}
}
