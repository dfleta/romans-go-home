package org.foobarspam.KataRomanNumerals;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.foobarspam.KataRomanNumerals.SimbolosRomanos;

public class NumeroRomano {
	
	private String numeroRomano = null;
	private short numeroDecimal = 0;
	
	private RegexNumerosRomanos regex;

	public NumeroRomano() {};

	public NumeroRomano(String numeroRomano, RegexNumerosRomanos regex){
		this.numeroRomano = numeroRomano;
		this.regex = regex;
	}

	public void setNumeroRomano(String numeroRomano) {
		this.numeroRomano = numeroRomano;
	}

	public void setRegex(RegexNumerosRomanos regex) {
		this.regex = regex;
	}
	
	public String getNumeroRomano(){
		return this.numeroRomano;
	}
	
	public short getNumeroDecimal(){
		return this.numeroDecimal;
	}
	
	public void initArrayRegex() {
		assertThat(regex).isNotEqualTo(null);
		regex.addRegex("grupoSumatorio", "[MCXI]{3}|(?<!C)[DM]|(?<!X)[LC](?!D)|(?<!I)[VX](?![LC])|I(?![VX])");
		regex.addRegex("grupoSustractivo", "(C[DM])|(X[LC])|(I[VX])");
	}
	
	public short toDecimal(){
		// assertThat(regex).isNotEqualTo(null);
		Matcher matcher = createMatcher(this.getRegexGrupo("grupoSumatorio"));
		groupSumatoryToDecimal(matcher);
		matcher = createMatcher(this.getRegexGrupo("grupoSustractivo"));
		groupSustractiveToDecimal(matcher);
		return getNumeroDecimal();
	}
	
	private Matcher createMatcher(String regex){
        Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(this.numeroRomano);
		return matcher;
	}
	
	private String getRegexGrupo(String grupo){
		return regex.getRegexValue(grupo);
	}
	
	private void groupSumatoryToDecimal(Matcher matcher){
		this.numeroDecimal = 0;
		while(matcher.find()){
			 // System.out.println( matcher.group() + " PASS");
			 for(char numeroRomano : matcher.group().toCharArray()){					 
				 this.numeroDecimal += valorDecimal(numeroRomano);
			 }
			 // System.out.println("En decimal: " + numeroDecimal);
		}
	}
	
	private void groupSustractiveToDecimal(Matcher matcher){
		while(matcher.find()){
				 // System.out.println( matcher.group() + " PASS");
				 // System.out.println("En decimal: " + valorGrupoSustractivo(matcher.group()));
				 this.numeroDecimal += valorGrupoSustractivo(matcher.group());
		}
	}
	
	private short valorDecimal(char numeroRomano) {
    	SimbolosRomanos simbolo = Enum.valueOf(SimbolosRomanos.class, String.valueOf(numeroRomano));    	
    	return (short) simbolo.getValorDecimal();
	}
	
    private short valorGrupoSustractivo(String grupoSustractivo){
    	return (short) (valorDecimal(grupoSustractivo.charAt(1)) - valorDecimal(grupoSustractivo.charAt(0)));
    }
}
