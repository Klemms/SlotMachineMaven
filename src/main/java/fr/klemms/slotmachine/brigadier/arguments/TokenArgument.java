package fr.klemms.slotmachine.brigadier.arguments;

import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import fr.klemms.slotmachine.tokens.Token;
import fr.klemms.slotmachine.utils.TokenUtil;
import io.papermc.paper.command.brigadier.argument.CustomArgumentType;

import java.util.concurrent.CompletableFuture;

public class TokenArgument implements CustomArgumentType<Token, String> {

	private static final SimpleCommandExceptionType ERROR_BAD_SOURCE = new SimpleCommandExceptionType(
        new LiteralMessage("The source needs to be a valid CommandSourceStack!")
    );

	private static final SimpleCommandExceptionType ERROR_TOKEN_NOT_FOUND = new SimpleCommandExceptionType(
        new LiteralMessage("The token identifier doesn't exist")
    );

	@Override
	public ArgumentType<String> getNativeType() {
		return StringArgumentType.word();
	}

	@Override
	public Token parse(StringReader reader) throws CommandSyntaxException {
		String tokenId = this.getNativeType().parse(reader);
		Token token = TokenUtil.getToken(tokenId);

		if (token == null) {
			throw ERROR_TOKEN_NOT_FOUND.create();
		}

		return token;
	}

	@Override
	public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
		TokenUtil.getTokenList().forEach(token -> {
			builder.suggest(token.identifier);
		});

		return builder.buildFuture();
	}
}
