package org.foobarspam.kataromannumerals;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumeroRomano {

	private String numeroRomano = null;
	private short numeroDecimal = 0;

	private RegexNumerosRomanos regexDiccionario = new RegexNumerosRomanos();

	public NumeroRomano() {};

	public NumeroRomano(String numeroRomano) {
		this.numeroRomano = numeroRomano;
	}

	public void setNumeroRomano(String numeroRomano) {
		this.numeroRomano = numeroRomano;
		this.setNumeroDecimal((short) 0);
	}

	public void setRegexDiccionario(RegexNumerosRomanos regex) {
		this.regexDiccionario = regex;
	}

	public RegexNumerosRomanos getRegexDiccionario() {
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
	
	public void initRegexDicionario() {
		getRegexDiccionario().addRegex("grupoSumatorio", "(?<!C)[DM]|(?<!X)[LC](?![DM])|(?<!I)[VX](?![LC])|I(?![VX])");
		getRegexDiccionario().addRegex("grupoSustractivo", "(C[DM])|(X[LC])|(I[VX])");
	}

	public void addRegex(String descripcion, String regex) {
		getRegexDiccionario().addRegex(descripcion, regex);
	}

	public List<String> getExpresionesRegulares() {
		List<String> listaRegex = new ArrayList<String>(getRegexDiccionario().getRegex().values());
		return listaRegex;
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
