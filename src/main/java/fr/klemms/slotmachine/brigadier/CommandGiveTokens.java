package fr.klemms.slotmachine.brigadier;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import fr.klemms.slotmachine.brigadier.arguments.TokenArgument;
import fr.klemms.slotmachine.tokens.Token;
import fr.klemms.slotmachine.tokens.TokensInventory;
import fr.klemms.slotmachine.utils.ItemStackUtil;
import fr.klemms.slotmachine.utils.Words;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class CommandGiveTokens implements ICommand {
	@Override
	public LiteralArgumentBuilder<CommandSourceStack> register() {
		LiteralArgumentBuilder<CommandSourceStack> baseCommand = Commands.literal("givetokens")
				.requires(sender -> sender.getSender().hasPermission("slotmachine.givetokens"));

		var cToken = Commands.argument("token", new TokenArgument()).executes(ctx -> {
			int amount = IntegerArgumentType.getInteger(ctx, "amount");
			Token token = ctx.getArgument("token", Token.class);
			final PlayerSelectorArgumentResolver resolver = ctx.getArgument("targets", PlayerSelectorArgumentResolver.class);
			final List<Player> players = resolver.resolve(ctx.getSource());

			this.giveTokens(ctx.getSource(), amount, players, token);

			return Command.SINGLE_SUCCESS;
		});
		/*var cAmount = Commands.argument("amount", IntegerArgumentType.integer(1))
				.requires(stack -> stack.getSender() instanceof Player);*/
		var cAmount= Commands.argument("amount", IntegerArgumentType.integer(1));
		var cAmountAlone = Commands.argument("amount", IntegerArgumentType.integer(1));
		var cTargets = Commands.argument("targets", ArgumentTypes.players());

		baseCommand.then(cTargets.then(cAmountAlone.executes(ctx -> {
					int amount = IntegerArgumentType.getInteger(ctx, "amount");
					final PlayerSelectorArgumentResolver resolver = ctx.getArgument("targets", PlayerSelectorArgumentResolver.class);
					final List<Player> players = resolver.resolve(ctx.getSource());

					TokensInventory.showManagementScreen((Player) ctx.getSource().getSender(), 0, "Pick a Token to give", "Left click to pick this Token", (player1, token) -> {
						player1.closeInventory();
						this.giveTokens(ctx.getSource(), amount, players, token);
					}, false);


					return Command.SINGLE_SUCCESS;
				}
		)));
		baseCommand.then(cTargets.then(cAmount.then(cToken.executes(ctx -> {
					int amount = IntegerArgumentType.getInteger(ctx, "amount");
					Token token = ctx.getArgument("token", Token.class);
					final PlayerSelectorArgumentResolver resolver = ctx.getArgument("targets", PlayerSelectorArgumentResolver.class);
					final List<Player> players = resolver.resolve(ctx.getSource());

					this.giveTokens(ctx.getSource(), amount, players, token);

					return Command.SINGLE_SUCCESS;
				}
		))));

		return baseCommand;
	}

	private void giveTokens(CommandSourceStack sender, int amount, List<Player> players, Token token) {
		players.forEach(player -> {
			player.getInventory().addItem(ItemStackUtil.changeItemStackAmount(new ItemStack(token.itemStack), amount));
			sender.sendSuccess(Words.mm("<green>You have successfully given <amount> of '<token>' token to <gold><player></gold></green>",
					Placeholder.unparsed("amount", String.valueOf(amount)),
					Placeholder.unparsed("token", token.identifier),
					Placeholder.unparsed("player", player.getName())
			));
		});
	}
}
