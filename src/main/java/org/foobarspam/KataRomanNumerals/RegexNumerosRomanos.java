package org.foobarspam.KataRomanNumerals;

import java.util.Collection;
import java.util.HashMap;

public class RegexNumerosRomanos {

	private HashMap<String, String> regex = new HashMap<String, String>();

	public void addRegex(String key, String value) {
		this.regex.put(key, value);
	}

	public HashMap<String, String> getRegex() {
		return this.regex;
	}

	public String getRegexValue(String key) {
		return this.regex.get(key);
	}

	public Collection<String> getValues() {
		return this.regex.values();
	}
}
