package org.foobarspam.KataRomanNumerals;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.foobarspam.KataRomanNumerals.SimbolosRomanos;

public class NumeroRomano {

	private String numeroRomano = null;
	private short numeroDecimal = 0;

	private RegexNumerosRomanos regex = new RegexNumerosRomanos();

	public NumeroRomano() {
		this.initArrayRegex();
	};

	public NumeroRomano(String numeroRomano) {
		this.numeroRomano = numeroRomano;
		this.initArrayRegex();
	}

	public void setNumeroRomano(String numeroRomano) {
		this.numeroRomano = numeroRomano;
		this.setNumeroDecimal((short) 0);
	}

	public void setRegex(RegexNumerosRomanos regex) {
		this.regex = regex;
	}

	public RegexNumerosRomanos getRegex() {
		return this.regex;
	}

	public String getNumeroRomano() {
		return this.numeroRomano;
	}

	public void setNumeroDecimal(short numeroDecimal) {
		this.numeroDecimal = numeroDecimal;
	} 

	public short getNumeroDecimal() {
		return this.numeroDecimal;
	}

	public void initArrayRegex() {
		regex.addRegex("grupoSumatorio", "(?<!C)[DM]|(?<!X)[LC](?![DM])|(?<!I)[VX](?![LC])|I(?![VX])");
		regex.addRegex("grupoSustractivo", "(C[DM])|(X[LC])|(I[VX])");
	}

	public short toDecimal() {
		for(String regex : getRegex().getValues()) {
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
