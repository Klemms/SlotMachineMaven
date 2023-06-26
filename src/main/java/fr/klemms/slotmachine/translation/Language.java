package fr.klemms.slotmachine.translation;

import fr.klemms.slotmachine.Config;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;

public class Language {

	public static HashMap<String, HashMap<String, String>> languages = new HashMap<String, HashMap<String, String>>();

	public static boolean isValidLanguage(String language) {
		return languages.containsKey(language);
	}

	public static String translate(String identifier) {
		return translateInLanguage(Config.language, identifier);
	}

	public static String translateInLanguage(String language, String identifier) {
		if(isValidLanguage(language)) {
			if(languages.get(language).containsKey(identifier)) {
				return languages.get(language).get(identifier);
			} else if(languages.get("en").containsKey(identifier)) {
				return languages.get("en").get(identifier);
			}
		} else {
			if(languages.get("en").containsKey(identifier)) {
				return languages.get("en").get(identifier);
			}
		}

		if(identifier.equals("version")) {
			return "0";
		}

		return identifier;
	}

	public static String translateInLanguage(HashMap<String, String> language, String identifier) {
		if(language.containsKey(identifier)) {
			return language.get(identifier);
		}

		if(identifier.equals("version")) {
			return "0";
		}

		return identifier;
	}

	public static void parseLanguageFromStrings(String language, List<String> lines) {
		HashMap<String, String> lang = new HashMap<String, String>();

		for(String line : lines) {
			String[] lineSeparated = StringUtils.split(line, "=", 2);

			if(lineSeparated.length == 2) {
				lang.put(lineSeparated[0], lineSeparated[1]);
			}
		}

		languages.put(language, lang);
	}

	public static HashMap<String, String> getParseLanguageFromStrings(List<String> lines) {
		HashMap<String, String> lang = new HashMap<String, String>();

		for(String line : lines) {
			String[] lineSeparated = StringUtils.split(line, "=", 2);

			if(lineSeparated.length == 2) {
				lang.put(lineSeparated[0], lineSeparated[1]);
			}
		}

		return lang;
	}
}
