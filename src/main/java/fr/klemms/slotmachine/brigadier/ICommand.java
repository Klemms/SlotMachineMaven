package fr.klemms.slotmachine.brigadier;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.papermc.paper.command.brigadier.CommandSourceStack;

public interface ICommand {

	public LiteralArgumentBuilder<CommandSourceStack> register();
}