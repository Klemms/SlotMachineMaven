package fr.klemms.slotmachine.interraction.machinemenu;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.PriceType;
import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.translation.Language;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import su.nightexpress.coinsengine.api.CoinsEngineAPI;
import su.nightexpress.coinsengine.api.currency.Currency;

import java.util.Arrays;
import java.util.List;

public class MenuItemChangePaymentCoinsEngine extends MenuItem {
	@Override
	public ItemStack getMaterial(SlotMachine machine, Player player) {
		return new ItemStack(Material.GHAST_TEAR, 1);
	}

	@Override
	public String getTitle(SlotMachine machine, Player player) {
		return ChatContent.GOLD + "Change Payment Method";
	}

	@Override
	public List<String> getDescription(SlotMachine machine, Player player) {
		Currency currency = CoinsEngineAPI.getCurrency(machine.getCoinsEngineCurrencyName());

		return Arrays.asList(
				ChatContent.AQUA + ChatContent.ITALIC + "Select a new currency",
				ChatContent.AQUA + ChatContent.ITALIC + "from " + ChatContent.GREEN + ChatContent.ITALIC + "CoinsEngine",
				"",
				ChatContent.AQUA + ChatContent.ITALIC + "Current payment method :",
				ChatContent.RESET + ((machine.getPriceType() == PriceType.COINSENGINE && currency != null) ? "CoinsEngine : " + currency.getName() : machine.getPriceType().name)
		);
	}

	@Override
	public void onClick(SlotMachine machine, Player player, ClickType clickType, MenuState state) {
		if (clickType == ClickType.LEFT) {
			player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
			machine.setPriceType(PriceType.COINSENGINE);
			player.sendMessage(ChatContent.GREEN + SlotPlugin.CHAT_PREFIX + Language.translate("command.slotmachineaction.paymentcoinsenginecurrency"));
			machine.save();
			state.reloadPage();
		}
	}
}
