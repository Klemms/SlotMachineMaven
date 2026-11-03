package fr.klemms.slotmachine.brigadier.arguments;

import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import fr.klemms.slotmachine.SlotMachine;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.CustomArgumentType;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class MachineArgument implements CustomArgumentType<SlotMachine, UUID> {

	private static final SimpleCommandExceptionType ERROR_BAD_SOURCE = new SimpleCommandExceptionType(
        new LiteralMessage("The source needs to be a valid CommandSourceStack!")
    );

	private static final SimpleCommandExceptionType ERROR_SM_NOT_FOUND = new SimpleCommandExceptionType(
        new LiteralMessage("This slot machine doesn't exist")
    );

	@Override
	public ArgumentType<UUID> getNativeType() {
		return ArgumentTypes.uuid();
	}

	@Override
	public SlotMachine parse(StringReader reader) throws CommandSyntaxException {
		UUID machineUUID = this.getNativeType().parse(reader);
		SlotMachine slotMachine = SlotMachine.getSlotMachineByUUID(machineUUID);

		if (slotMachine == null) {
			throw ERROR_SM_NOT_FOUND.create();
		}

		return slotMachine;
	}

	@Override
	public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
		SlotMachine.getSlotMachines().forEach(slotMachine -> {
			builder.suggest(slotMachine.getMachineUUID().toString());
		});

		return builder.buildFuture();
	}
}
