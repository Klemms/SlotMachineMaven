package fr.klemms.slotmachine.brigadier;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import fr.klemms.slotmachine.SlotMachineEntity;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.brigadier.arguments.EntityMachineArgument;
import fr.klemms.slotmachine.translation.Language;
import fr.klemms.slotmachine.utils.Words;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.resolvers.FinePositionResolver;
import io.papermc.paper.math.FinePosition;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class CommandTPMachine implements ICommand {
	@Override
	public LiteralArgumentBuilder<CommandSourceStack> register() {
		return Commands.literal("tpmachine")
				.requires(sender -> sender.getSender().hasPermission("slotmachine.tpmachine"))
				.then(Commands.argument("machine UUID", new EntityMachineArgument())
						.executes(ctx -> {
							final SlotMachineEntity machine = ctx.getArgument("machine UUID", SlotMachineEntity.class);

							if (ctx.getSource().getSender() instanceof Player) {
								tpMachine(ctx.getSource(), machine, ctx.getSource().getLocation());
							} else {
								ctx.getSource().sendFailure(Words.mm("<red><prefix>You can only omit the coordinates when using this command in-game</red>",
										Placeholder.unparsed("prefix", SlotPlugin.CHAT_PREFIX)
								));
							}

							return Command.SINGLE_SUCCESS;
						})
						.then(Commands.argument("world", ArgumentTypes.world())
								.then(Commands.argument("position", ArgumentTypes.finePosition(true))
										.executes(ctx -> {
											final SlotMachineEntity machine = ctx.getArgument("machine UUID", SlotMachineEntity.class);
											final World world = ctx.getArgument("world", World.class);
											final FinePositionResolver resolver = ctx.getArgument("position", FinePositionResolver.class);
											final FinePosition pos = resolver.resolve(ctx.getSource());

											tpMachine(ctx.getSource(), machine, pos.toLocation(world));

											return Command.SINGLE_SUCCESS;
										})
								)
						)
				);
	}

	private void tpMachine(CommandSourceStack stack, SlotMachineEntity machine, Location location) {
		Entity entity = machine.getEntity();

		if (entity == null) {
			stack.sendFailure(Words.mm("<red><prefix>Couldn't find the entity of the machine you want to teleport, you may need to load the chunks they're in</red>",
					Placeholder.unparsed("prefix", SlotPlugin.CHAT_PREFIX)
			));

			return;
		}

		entity.teleport(location);
		machine.save();

		stack.sendSuccess(Words.mm("<green><prefix><message> (<aqua><world></aqua> @ <aqua><x></aqua>, <aqua><y></aqua>, <aqua><z></aqua>)</green>",
				Placeholder.unparsed("prefix", SlotPlugin.CHAT_PREFIX),
				Placeholder.unparsed("message", Language.translate("command.tpmachine.successful")),
				Placeholder.unparsed("world", location.getWorld().getName()),
				Placeholder.unparsed("x", String.valueOf(location.getBlockX())),
				Placeholder.unparsed("y", String.valueOf(location.getBlockY())),
				Placeholder.unparsed("z", String.valueOf(location.getBlockZ()))
		));
	}
}
