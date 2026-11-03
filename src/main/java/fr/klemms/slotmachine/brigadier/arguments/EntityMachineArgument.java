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
import fr.klemms.slotmachine.SlotMachineEntity;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.CustomArgumentType;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class EntityMachineArgument implements CustomArgumentType<SlotMachineEntity, UUID> {

	private static final SimpleCommandExceptionType ERROR_SM_NOT_ENTITY = new SimpleCommandExceptionType(
        new LiteralMessage("This slot machine is not of entity type")
    );

	private static final SimpleCommandExceptionType ERROR_SM_NOT_FOUND = new SimpleCommandExceptionType(
        new LiteralMessage("This slot machine doesn't exist")
    );

	@Override
	public ArgumentType<UUID> getNativeType() {
		return ArgumentTypes.uuid();
	}

	@Override
	public SlotMachineEntity parse(StringReader reader) throws CommandSyntaxException {
		UUID machineUUID = this.getNativeType().parse(reader);
		SlotMachine slotMachine = SlotMachine.getSlotMachineByUUID(machineUUID);

		if (slotMachine == null) {
			throw ERROR_SM_NOT_FOUND.create();
		} else if (!(slotMachine instanceof SlotMachineEntity)) {
			throw ERROR_SM_NOT_ENTITY.create();
		}

		return (SlotMachineEntity) slotMachine;
	}

	@Override
	public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
		SlotMachine.getAllSlotMachineEntities().forEach(slotMachine -> {
			builder.suggest(slotMachine.getMachineUUID().toString());
		});

		return builder.buildFuture();
	}
}
