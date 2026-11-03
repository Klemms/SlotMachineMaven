package fr.klemms.slotmachine.brigadier;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.Config;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.interraction.ConfirmInventory;
import fr.klemms.slotmachine.tokens.Token;
import fr.klemms.slotmachine.tokens.TokenSelectionListener;
import fr.klemms.slotmachine.tokens.TokensInventory;
import fr.klemms.slotmachine.utils.ItemStackUtil;
import fr.klemms.slotmachine.utils.Words;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandSlotMachineTokens implements ICommand {
	@Override
	public LiteralArgumentBuilder<CommandSourceStack> register() {
		LiteralArgumentBuilder<CommandSourceStack> base = Commands.literal("slotmachinetoken")
				.requires(sender -> sender.getSender().hasPermission("slotmachine.tokenmanagement"))
				.requires(stack -> stack.getSender() instanceof Player);

		base.then(Commands.literal("list").executes(ctx -> {
			final TokenSelectionListener listToken = new TokenSelectionListener() {
				@Override
				public void callback(Player player, Token token) {
					if (!token.identifier.equals("default")) {
						ConfirmInventory.confirmWindow(player, "Delete this token ? (" + ChatContent.ITALIC + token.identifier + ChatContent.RESET + ")", "No, cancel", "Yes, delete '" + token.identifier + "'", (boolean result) -> {
							if (result) {
								player.sendMessage(ChatContent.AQUA + SlotPlugin.CHAT_PREFIX + "Token " + token.identifier + " has been removed");
								Config.tokens.remove(token.identifier);
								SlotPlugin.writeTokens();
							}

							showTokens((Player) ctx.getSource().getSender(), this);
						}, false);
					} else {
						player.sendMessage(ChatContent.AQUA + SlotPlugin.CHAT_PREFIX + "You can't delete your default token. To replace it add a token with the name 'default'");
					}
				}
			};

			showTokens((Player) ctx.getSource().getSender(), listToken);
			return Command.SINGLE_SUCCESS;
		}));

		base.then(Commands.literal("add")
				.then(Commands.argument("identifier", StringArgumentType.word())
						.executes(ctx -> {
							final Player player = (Player) ctx.getSource().getSender();
							final String identifier = StringArgumentType.getString(ctx, "identifier").toLowerCase();
							final ItemStack handIS = player.getInventory().getItemInMainHand();

							if (handIS != null && handIS.getType() != Material.AIR) {
								boolean existsAlready = Config.tokens.containsKey(identifier);
								Config.tokens.put(identifier, ItemStackUtil.changeItemStackAmount(new ItemStack(player.getInventory().getItemInMainHand()), 1));
								SlotPlugin.writeTokens();

								String successString = existsAlready ?
										"<green><prefix>The existing token <gold><token></gold> has been replaced. <aqua><opentokens></aqua></green>" :
										"<green><prefix>Token <gold><token></gold> has been added. <aqua><opentokens></aqua></green>";

								ctx.getSource().sendSuccess(Words.mm(successString,
										Placeholder.unparsed("prefix", SlotPlugin.CHAT_PREFIX),
										Placeholder.unparsed("token", identifier),
										Placeholder.component("opentokens", Component.text("[Open Token List]")
												.hoverEvent(HoverEvent.showText(Words.mm("<gold>Click to open</gold>")))
												.clickEvent(ClickEvent.runCommand("/slotmachinetoken list"))
										)
								));
							} else {
								ctx.getSource().sendFailure(Words.mm("<red><prefix>You need to have an item in your main hand before executing this command</red>",
										Placeholder.unparsed("prefix", SlotPlugin.CHAT_PREFIX)
								));
							}

							return Command.SINGLE_SUCCESS;
						})
				)
		);

		return base;
	}

	private void showTokens(Player player, TokenSelectionListener listToken) {
		TokensInventory.showManagementScreen(player, 0, "Tokens Manager", "Left click to remove this token", listToken, true);
	}
}
