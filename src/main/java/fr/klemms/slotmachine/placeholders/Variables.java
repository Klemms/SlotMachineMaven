package fr.klemms.slotmachine.placeholders;

import fr.klemms.slotmachine.ChatContent;
import fr.klemms.slotmachine.SlotMachine;
import fr.klemms.slotmachine.SlotPlugin;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public enum Variables {
	
	PLAYER(new VariablePlayer(), "player", "placeholders.player"),
	BALANCE(new VariableBalance(), "balance", "placeholders.balance"),
	PRICE(new VariablePrice(), "price", "placeholders.price"),
	ITEMS(new VariableItems(), "items", "placeholders.items"),
	MACHINE_NAME(new VariableMachineName(), "machineName", "placeholders.machineName"),
	GAME_POINTS(new VariableGamePoints(), "gamePoints", "placeholders.gamePoints"),
	CHANCE_TO_WIN(new VariableChanceToWin(), "chanceToWin", "placeholders.chanceToWin"),
	TM_TOKENS(new VariableTMTokens(), "tmTokensPlayer", "placeholders.tmTokensPlayer"),
	TOKENNAME(new VariableTokenName(), "tokenName", "placeholders.tokenName"),
	TOKENS(new VariableTokens(), "tkens", "placeholders.tokens"),
	EXPERIENCE(new VariableExperience(), "experience", "placeholders.experience"),
	VOTINGPLUGIN(new VariableVotingPlugin(), "votingplugin", "placeholders.votingplugin");
	
	public Variable variable;
	public String variableName;
	public String variableDescription;
	
	Variables(Variable variable, String variableName, String variableDescription) {
		this.variable = variable;
		this.variableName = variableName;
		this.variableDescription = variableDescription;
	}
	
	public static String replaceVariable(Player player, SlotMachine slotMachine, String command) {
		for(int a = 0; a < Variables.values().length; a++) {
			command = command.replace("$" + Variables.values()[a].variableName, Variables.values()[a].variable.getVariable(player, slotMachine));
		}
		return SlotPlugin.isPlaceholderAPIEnabled ? PlaceholderAPI.setPlaceholders(player, command) : command;
	}
	
	public static List<String> getFormattedStrings(String message, Player player, SlotMachine slotMachine) {
		List<String> messageList = new ArrayList<String>();
		String[] messages = message.split("\\$newline");
		for(int a = 0; a < messages.length; a++) {
			messageList.add(ChatContent.translateColorCodes(Variables.replaceVariable(player, slotMachine, messages[a])));
		}
		return messageList;
	}
	
	public static String getFormattedString(String message, Player player, SlotMachine slotMachine) {
		return ChatContent.translateColorCodes(Variables.replaceVariable(player, slotMachine, message));
	}
}
