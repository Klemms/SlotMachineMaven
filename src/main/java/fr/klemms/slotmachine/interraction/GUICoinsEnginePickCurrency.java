package fr.klemms.slotmachine.interraction;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.fr.minuskube.inv.ClickableItem;
import fr.klemms.slotmachine.fr.minuskube.inv.SmartInventory;
import fr.klemms.slotmachine.fr.minuskube.inv.content.InventoryContents;
import fr.klemms.slotmachine.fr.minuskube.inv.content.InventoryProvider;
import fr.klemms.slotmachine.fr.minuskube.inv.content.Pagination;
import fr.klemms.slotmachine.fr.minuskube.inv.content.SlotIterator;
import fr.klemms.slotmachine.translation.Language;
import fr.klemms.slotmachine.utils.ItemStackUtil;
import fr.klemms.slotmachine.utils.PlayerHeadsUtil;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import su.nightexpress.coinsengine.api.CoinsEngineAPI;
import su.nightexpress.coinsengine.api.currency.Currency;

import java.util.ArrayList;
import java.util.List;

public class GUICoinsEnginePickCurrency {

	public static void show(Player player, Currency currentCurrency, ICurrencySelected onSelected, int page) {
		SmartInventory inv = SmartInventory.builder()
				.manager(SlotPlugin.invManager)
				.title("Pick a new currency")
				.size(6, 9)
				.closeable(true)
				.provider(new InventoryProvider() {

					@Override
					public void init(Player player, InventoryContents contents) {
						Pagination pagination = contents.pagination();

						contents.fillRow(0, ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1), " ")));
						contents.fillRow(5, ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1), " ")));

						pagination.setItemsPerPage(4 * 9);
						List<ClickableItem> items = new ArrayList<ClickableItem>();

						List<Currency> currencies = new ArrayList<>(CoinsEngineAPI.getCurrencies());
						for (int i = 0; i < currencies.size(); i++) {
							Currency cur = currencies.get(i);

							items.add(ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.MONEY_BAG), (currentCurrency != null && currentCurrency.equals(cur) ? ChatContent.GREEN + "[Selected] " : "") + ChatContent.GOLD + cur.getName()),event -> {
								onSelected.callback(cur);
							}));
						}

						pagination.setItems(items.toArray(new ClickableItem[items.size()]));
						pagination.addToIterator(contents.newIterator(SlotIterator.Type.HORIZONTAL, 1, 0));

						if (!pagination.isFirst())
							contents.set(5, 3, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.LEFT), Language.translate("basic.previouspage")), event -> {
								player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
								show(player, currentCurrency, onSelected, page - 1);
							}));

						if (!pagination.isLast())
							contents.set(5, 5, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.RIGHT), Language.translate("basic.nextpage")), event -> {
								player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
								show(player, currentCurrency, onSelected, page + 1);
							}));

						contents.set(5, 4, ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(Material.PAPER), Language.translate("basic.page") + " " + (pagination.getPage() + 1))));
					}

					@Override
					public void update(Player player, InventoryContents contents) {
					}

				})
				.build();

		inv.open(player, page);
	}

	public interface ICurrencySelected {
		public void callback(Currency currency);
	}
}
