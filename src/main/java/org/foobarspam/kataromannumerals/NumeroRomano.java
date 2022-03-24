package org.foobarspam.kataromannumerals;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumeroRomano {

	private final String numeroRomano;
	private short numeroDecimal = 0;

	private RegexRomanNumbers regexDiccionario = new RegexRomanNumbers();

	public NumeroRomano(String numeroRomano) {
		this.numeroRomano = numeroRomano;
		this.setNumeroDecimal((short) 0);
	}

	public void setRegexDiccionario(RegexRomanNumbers regex) {
		this.regexDiccionario = regex;
	}

	public RegexRomanNumbers getRegexDiccionario() {
		return this.regexDiccionario;
	}

	public String getNumeroRomano() {
		return this.numeroRomano;
	}

	private void setNumeroDecimal(short numeroDecimal) {
		this.numeroDecimal = numeroDecimal;
	} 

	public short getNumeroDecimal() {
		return this.numeroDecimal;
	}

	public void addRegex(String descripcion, String regex) {
		this.getRegexDiccionario().addRegex(descripcion, regex);
	}

	public List<String> getExpresionesRegulares() {
		return new ArrayList<String>(getRegexDiccionario().getRegex().values());
	}

	public short toDecimal() {
		for(String regex : getRegexDiccionario().getValues()) {
			Matcher matcher = createMatcher(regex);
			groupSumatoryToDecimal(matcher);
		}		
		return getNumeroDecimal();
	}

	private Matcher createMatcher(String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(this.numeroRomano);
		return matcher;
	}

	private void groupSumatoryToDecimal(Matcher matcher) {
		while (matcher.find()) {
				this.numeroDecimal += valorDecimal(matcher.group());
		}
	}

	public short valorDecimal(String numeroRomano) {
		SimbolosRomanos simbolo = Enum.valueOf(SimbolosRomanos.class, String.valueOf(numeroRomano));
		return (short) simbolo.getValorDecimal();
	}
}
