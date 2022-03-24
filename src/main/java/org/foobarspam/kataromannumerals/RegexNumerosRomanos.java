package org.foobarspam.kataromannumerals;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class RegexNumerosRomanos {

	private Map<String, String> regexDiccionario = new HashMap<String, String>();

	public void addRegex(String key, String value) {
		this.regexDiccionario.putIfAbsent(key, value);
	}

	public Map<String, String> getRegex() {
		return this.regexDiccionario;
	}

	public String getRegexValue(String key) {
		return this.regexDiccionario.get(key);
	}

	public Collection<String> getValues() {
		return this.regexDiccionario.values();
	}

	public long longitud() {
		return this.regexDiccionario.size();
	}
}
