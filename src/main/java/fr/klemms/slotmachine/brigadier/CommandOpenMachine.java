package fr.klemms.slotmachine.brigadier;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import fr.klemms.slotmachine.PriceType;
import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.brigadier.arguments.MachineArgument;
import fr.klemms.slotmachine.translation.Language;
import fr.klemms.slotmachine.utils.Util;
import fr.klemms.slotmachine.utils.Words;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandOpenMachine implements ICommand {
	@Override
	public LiteralArgumentBuilder<CommandSourceStack> register() {
		return Commands.literal("openmachine")
				.requires(sender -> sender.getSender().hasPermission("slotmachine.openmachine"))
				.then(Commands.argument("targets", ArgumentTypes.players())
						.then(Commands.argument("machine UUID", new MachineArgument())
								.executes(ctx -> {
									final PlayerSelectorArgumentResolver resolver = ctx.getArgument("targets", PlayerSelectorArgumentResolver.class);
									final List<Player> players = resolver.resolve(ctx.getSource());
									final SlotMachine machine = ctx.getArgument("machine UUID", SlotMachine.class);

									openMachine(ctx.getSource(), players, machine, false);

									return Command.SINGLE_SUCCESS;
								})
								.then(Commands.argument("override permission", BoolArgumentType.bool())
										.executes(ctx -> {
											final PlayerSelectorArgumentResolver resolver = ctx.getArgument("targets", PlayerSelectorArgumentResolver.class);
											final List<Player> players = resolver.resolve(ctx.getSource());
											final SlotMachine machine = ctx.getArgument("machine UUID", SlotMachine.class);
											final boolean overridePerms = BoolArgumentType.getBool(ctx, "override permission");

											openMachine(ctx.getSource(), players, machine, overridePerms);

											return Command.SINGLE_SUCCESS;
										})
								)
						)
				);
	}

	private void openMachine(CommandSourceStack stack, List<Player> players, SlotMachine machine, boolean overridePermission) {
		players.forEach(player -> {
			if (machine.playerHasPermission(player) || overridePermission) {
				if (machine.getSlotMachineItems().size() > 0) {
					if (machine.getPriceType() == PriceType.COINSENGINE && !SlotPlugin.isCoinsEngineEnabled) {
						stack.sendFailure(Words.mm("<red><prefix><message></red>",
								Placeholder.unparsed("prefix", SlotPlugin.CHAT_PREFIX),
								Placeholder.unparsed("message", Language.translate("slotmachine.access.missingcoinsengine"))
						));
					} else if (machine.getPriceType() == PriceType.MONEY && SlotPlugin.econ == null) {
						stack.sendFailure(Words.mm("<red><prefix><message></red>",
								Placeholder.unparsed("prefix", SlotPlugin.CHAT_PREFIX),
								Placeholder.unparsed("message", Language.translate("slotmachine.access.missingvault"))
						));
					} else {
						player.closeInventory();
						machine.openMachine(player, true);
						Util.playSoundPlayer(player, machine.getMachineOpeningSound(), 1.9f, 1.2f);
						stack.sendSuccess(Words.mm("<green><prefix>Successfully opened machine for <gold><player></gold></green>",
								Placeholder.unparsed("prefix", SlotPlugin.CHAT_PREFIX),
								Placeholder.unparsed("player", player.getName())
						));
					}
				} else {
					stack.sendFailure(Words.mm("<red><prefix><message></red>",
							Placeholder.unparsed("prefix", SlotPlugin.CHAT_PREFIX),
							Placeholder.unparsed("message", Language.translate("command.openmachine.noitems"))
					));
				}
			} else {
				stack.sendFailure(Words.mm("<red>Couldn't open machine for <gold><player></gold> as they do not have the required permission (<aqua><permission></aqua>)</red>",
						Placeholder.unparsed("player", player.getName()),
						Placeholder.unparsed("permission", machine.getGuiPermission())
				));
				player.sendMessage(Words.mm("<red><prefix><message></red>",
						Placeholder.unparsed("prefix", SlotPlugin.CHAT_PREFIX),
						Placeholder.unparsed("message", Language.translate("permission.denied"))
				));
			}
		});
	}
}
