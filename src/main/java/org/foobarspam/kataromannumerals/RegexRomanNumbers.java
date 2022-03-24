package org.foobarspam.kataromannumerals;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class RegexRomanNumbers {

	private final Map<String, String> regexCollection = new HashMap<String, String>();

	public RegexRomanNumbers() {
		this.initRegexCollection();
	}

	private void initRegexCollection() {
		this.addRegex("grupoSumatorio", "(?<!C)[DM]|(?<!X)[LC](?![DM])|(?<!I)[VX](?![LC])|I(?![VX])");
		this.addRegex("grupoSustractivo", "(C[DM])|(X[LC])|(I[VX])");
	}

	void addRegex(String key, String value) {
		this.regexCollection.putIfAbsent(key, value);
	}

	public Map<String, String> getRegex() {
		return this.regexCollection;
	}

	public String getRegexValue(String key) {
		return this.regexCollection.get(key);
	}

	public Collection<String> getValues() {
		return this.regexCollection.values();
	}

	public long numRegex() {
		return this.regexCollection.size();
	}
}
