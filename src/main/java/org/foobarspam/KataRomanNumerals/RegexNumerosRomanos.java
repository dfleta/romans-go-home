package org.foobarspam.KataRomanNumerals;

import java.util.HashMap;

public class RegexNumerosRomanos {

	private HashMap<String, String> regex = new HashMap<String, String>();

	public void addRegex(String key, String value) {
		this.regex.put(key, value);
	}

	public HashMap<String, String> getRegex() {
		return regex;
	}

	public String getRegexValue(String key) {
		return regex.get(key);
	}
}
