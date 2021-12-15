package fr.klemms.slotmachine.tokens;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.Config;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.fr.minuskube.inv.ClickableItem;
import fr.klemms.slotmachine.fr.minuskube.inv.SmartInventory;
import fr.klemms.slotmachine.fr.minuskube.inv.content.InventoryContents;
import fr.klemms.slotmachine.fr.minuskube.inv.content.InventoryProvider;
import fr.klemms.slotmachine.fr.minuskube.inv.content.Pagination;
import fr.klemms.slotmachine.fr.minuskube.inv.content.SlotIterator.Type;
import fr.klemms.slotmachine.interraction.ConfirmInventory;
import fr.klemms.slotmachine.utils.ItemStackUtil;
import fr.klemms.slotmachine.utils.PlayerHeadsUtil;
import fr.klemms.slotmachine.utils.TokenUtil;

public class TokensInventory {

	/**
	 * 
	 * @param player Player to show to
	 * @param page Page to show
	 * @param sentenceToShow Sentence showed when hovering a token
	 * @param callback Function that is called when a Token is left-clicked. Can be <b>null</b>
	 */
	public static void showManagementScreen(Player player, final int page, final String windowTitle, final String sentenceToShow, final TokenSelectionListener callback, final boolean showDeleteButton) {
		final SmartInventory inv = SmartInventory.builder()
				.manager(SlotPlugin.invManager)
				.id("tokenManagement")
				.title(windowTitle)
				.size(6, 9)
				.closeable(true)
				.provider(new InventoryProvider() {

					@Override
					public void init(Player player, InventoryContents contents) {
						List<Token> tokens = TokenUtil.getTokenList();
						
						Pagination pagination = contents.pagination();
						
						ClickableItem[] items = new ClickableItem[tokens.size()];
						
						for(int i = 0; i < items.length; i++) {
							final Token token = tokens.get(i);
							
							ItemStack is = new ItemStack(token.itemStack);
							ItemMeta im = is.getItemMeta();
							List<String> lore = im.hasLore() ? im.getLore() : new ArrayList<String>();
							lore.add(0, ChatContent.AQUA + "----- Token name : " + ChatContent.GOLD + ChatContent.ITALIC + token.identifier + ChatContent.AQUA + " -----");
							lore.add(1, ChatContent.AQUA + ChatContent.ITALIC + " - " + sentenceToShow);
							if (lore.size() > 2)
								lore.add(2, "");
							im.setLore(lore);
							is.setItemMeta(im);
							
							items[i] = ClickableItem.of(is, event -> {
								if (event.isLeftClick()) {
									player.playSound(player.getLocation(), Sound.BLOCK_WOODEN_BUTTON_CLICK_OFF, 0.8F, 0.9F);
									callback.callback(player, token);
								}
							});
						}
						
						pagination.setItemsPerPage(4 * 9);
						pagination.setItems(items);
						
						contents.fill(ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1), " ")));
						pagination.addToIterator(contents.newIterator(Type.HORIZONTAL, 1, 0));
						
						
						if (showDeleteButton)
							contents.set(0, 7, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(Material.BARRIER), ChatContent.GRAY + "Remove " + ChatContent.RED + "all" + ChatContent.GRAY + " Tokens"), event -> {
								if (event.isLeftClick()) {
									ConfirmInventory.confirmWindow(player, "Delete " + ChatContent.BOLD + "ALL" + ChatContent.RESET + " tokens ?", "No, cancel", "Yes, delete all tokens", (boolean result) -> {
										if (result) {
											player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_HURT, 0.4F, 2F);
											player.sendMessage(ChatContent.AQUA + "[Slot Machine] All Tokens have been removed (Except your default Token)");
											ItemStack defaultToken = new ItemStack(Config.tokens.get("default"));
											Config.tokens.clear();
											Config.tokens.put("default", defaultToken);
											SlotPlugin.writeTokens();
										}
									}, true);
								}
							}));

						if (showDeleteButton)
							contents.set(0, 1, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(Material.GOLD_NUGGET), ChatContent.GRAY + "Reset " + ChatContent.AQUA + "default" + ChatContent.GRAY + " token"), event -> {
								if (event.isLeftClick()) {
									player.closeInventory();
									player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_HURT, 0.4F, 2F);
									player.sendMessage(ChatContent.AQUA + "[Slot Machine] Your default token has been reset");
									Config.tokens.put("default", SlotPlugin.DEFAULT_TOKEN);
									SlotPlugin.writeTokens();
								}
							}));
						
						
						if (!pagination.isFirst())
							contents.set(5, 3, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.LEFT), "< Previous Page"), event -> {
								player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
								showManagementScreen(player, page - 1, windowTitle, sentenceToShow, callback, showDeleteButton);
							}));

						if (!pagination.isLast())
							contents.set(5, 5, ClickableItem.of(ItemStackUtil.changeItemStackName(new ItemStack(PlayerHeadsUtil.RIGHT), "Next Page >"), event -> {
								player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1F, 1F);
								showManagementScreen(player, page + 1, windowTitle, sentenceToShow, callback, showDeleteButton);
							}));
						
						contents.set(5, 4, ClickableItem.empty(ItemStackUtil.changeItemStackName(new ItemStack(Material.PAPER), "Page " + (pagination.getPage() + 1) + "/" + (pagination.last().getPage() + 1))));
					}

					@Override
					public void update(Player player, InventoryContents contents) {
						
					}
					
				})
				.build();
		
		inv.open(player, page);
	}
}
