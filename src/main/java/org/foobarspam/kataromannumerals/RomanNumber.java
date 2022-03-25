package org.foobarspam.kataromannumerals;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RomanNumber {

	private final String romanNum;
	private short decimalNum = 0;

	private RegexRomanNumbers regexCollection = null;

	public RomanNumber(String numeroRomano) {
		this.romanNum = numeroRomano;
		this.decimalNum = (short) 0;
		regexCollection = new RegexRomanNumbers();
	}

	public RegexRomanNumbers getRegexCollection() {
		return this.regexCollection;
	}

	@Override
	public String toString() {
		return this.romanNum;
	}

	private short getNumeroDecimal() {
		return this.decimalNum;
	}

	public void addRegex(String descripcion, String regex) {
		this.getRegexCollection().addRegex(descripcion, regex);
	}

	public short toDecimal() {
		for(String regex : regexCollection()) {
			Matcher matcher = createMatcher(regex);
			groupSumatoryToDecimal(matcher);
		}		
		return getNumeroDecimal();
	}

	private List<String> regexCollection() {
		return getRegexCollection().getAllRegex();
	}

	private Matcher createMatcher(String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(this.romanNum);
		return matcher;
	}

	private void groupSumatoryToDecimal(Matcher matcher) {
		while (matcher.find()) {
				this.decimalNum += decimalValue(matcher.group());
		}
	}

	public short decimalValue(String numeroRomano) {
		RomanSymbols simbolo = Enum.valueOf(RomanSymbols.class, String.valueOf(numeroRomano));
		return (short) simbolo.getDecimalValue();
	}
}
