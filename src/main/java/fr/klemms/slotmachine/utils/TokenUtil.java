package fr.klemms.slotmachine.utils;

import fr.klemms.slotmachine.Config;
import fr.klemms.slotmachine.tokens.Token;

import java.util.ArrayList;
import java.util.List;

public class TokenUtil {

	public static List<Token> getTokenList() {
		List<Token> tokens = new ArrayList<Token>();
		
		for(String identifier : Config.tokens.keySet()) {
			tokens.add(new Token(identifier, Config.tokens.get(identifier)));
		}
		
		return tokens;
	}
}
