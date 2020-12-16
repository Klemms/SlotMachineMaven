package fr.klemms.slotmachine;

public enum PriceType {

	MONEY("Money"),
	TOKEN("Tokens"),
	PLAYERPOINTS("PlayerPoints"),
	TOKENMANAGER("Tokens (TokenManager)"),
	EXPERIENCE("Experience");
	
	public String name;
	
	PriceType(String name) {
		this.name = name;
	}
}
