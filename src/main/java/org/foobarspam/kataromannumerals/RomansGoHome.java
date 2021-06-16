package org.foobarspam.kataromannumerals;

import static org.assertj.core.api.Assertions.assertThat;

public class RomansGoHome {

	public static void main(String[] args) {

		String test = "MMMDCCCLXXXVIII"; // 3888
		test = "MMDCCLXXVII";  // 2777
		test = "CDXLIV"; // 444
		test = "CDXXXIX"; // 439

		NumeroRomano numeroRomano = new NumeroRomano(test);
		numeroRomano.addRegex("grupoSumatorio", "(?<!C)[DM]|(?<!X)[LC](?![DM])|(?<!I)[VX](?![LC])|I(?![VX])");
		numeroRomano.addRegex("grupoSustractivo", "(C[DM])|(X[LC])|(I[VX])");

		assertThat(numeroRomano.toDecimal()).isEqualTo((short) 439);

		System.out.println(numeroRomano.getNumeroRomano() + " = " + numeroRomano.getNumeroDecimal());
	}
}
