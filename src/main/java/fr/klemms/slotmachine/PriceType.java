package fr.klemms.slotmachine;

public enum PriceType {

	MONEY("Money"),
	TOKEN("Tokens"),
	GAMEPOINTS("GamePoints"),
	PLAYERPOINTS("PlayerPoints"),
	TOKENMANAGER("Tokens (TokenManager)"),
	EXPERIENCE("Experience"),
	VOTINGPLUGIN("VotingPlugin");

	public String name;

	PriceType(String name) {
		this.name = name;
	}
}
