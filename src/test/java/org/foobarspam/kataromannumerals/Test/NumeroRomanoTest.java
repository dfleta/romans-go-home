package org.foobarspam.kataromannumerals.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.foobarspam.kataromannumerals.NumeroRomano;
import org.junit.Test;

public class NumeroRomanoTest {

    public NumeroRomano numeroRomano;

    /**
     * Grupos sumatorios M, C, X, I
     */

    @Test
    public void grupo_M_test() {

        String testCase = "M";
        numeroRomano = new NumeroRomano(testCase);
        assertEquals(1000, numeroRomano.toDecimal());

        testCase = "UMMU";
        numeroRomano = new NumeroRomano(testCase);
        assertEquals(2000, numeroRomano.toDecimal());

        testCase = "UMMMU";
        numeroRomano = new NumeroRomano(testCase);
        assertEquals(3000, numeroRomano.toDecimal());

        testCase = "UCMU";
        numeroRomano = new NumeroRomano(testCase);
        assertNotEquals(1000, numeroRomano.toDecimal());

        /**
         * El caso MMMM es control de errores 
         * y no puede estar en el test de la logica
         * Asumimos que la entrada es correcta.
         * Sino, hay que programar la gestion de errores
         */
    }

    @Test
    public void tres_repeticiones_C_test() {

        String testCase = "UMMMUCCCU";
        numeroRomano = new NumeroRomano(testCase);
        assertEquals(3300, numeroRomano.toDecimal());
    }

    @Test
    public void tres_repeticiones_X_test() {

        String testCase = "UMMMUXXXU";
        numeroRomano = new NumeroRomano(testCase);

        assertEquals(3030, numeroRomano.toDecimal());
    }

    @Test
    public void tres_repeticiones_I_test() {

        String testCase = "UMMMUIIIU";
        numeroRomano = new NumeroRomano(testCase);

        assertEquals(3003, numeroRomano.toDecimal());
    }

    @Test
    public void una_D_test() {

        String testCase = "UMMMUDUIIIU";
        numeroRomano = new NumeroRomano(testCase);
        assertEquals(3503, numeroRomano.toDecimal());

        testCase = "MMMUCDUIIIU";
        numeroRomano = new NumeroRomano(testCase);
        assertNotEquals(3503, numeroRomano.toDecimal());
    }

    /**
     * Grupos substractivos
     * IV(4), IX(9), 
     * XL(40), XC(90), 
     * CD(400), CM(900)
     */

    @Test
    public void grupo_C_DM_test() {

        String testCase = "UCDU";
        numeroRomano = new NumeroRomano(testCase);
        assertEquals(400, numeroRomano.toDecimal());

        testCase = "UCMU";
        numeroRomano = new NumeroRomano(testCase);
        assertEquals(900, numeroRomano.toDecimal());
    }

    @Test
    public void grupo_X_LC_test() {

        String testCase = "UXLU";
        numeroRomano = new NumeroRomano(testCase);
        assertEquals(40, numeroRomano.toDecimal());  

        testCase = "UXCU";
        numeroRomano = new NumeroRomano(testCase);
        assertEquals(90, numeroRomano.toDecimal());        
    }

    @Test
    public void grupo_I_VX_test() {

        String testCase = "UIVU";
        numeroRomano = new NumeroRomano(testCase);
        assertEquals(4, numeroRomano.toDecimal());  

        testCase = "UIXU";
        numeroRomano = new NumeroRomano(testCase);
        assertEquals(9, numeroRomano.toDecimal());  
    }

    @Test
    public void grupos_sumatorios_tres_digitos_test() {
        String testCase = "MMMDCCCLXXXVIII"; // 3888
        numeroRomano = new NumeroRomano(testCase);
        assertEquals(3888, numeroRomano.toDecimal());
    }

    @Test
    public void grupos_sumatorios_test() {
        String testCase = "MMDCCLXXVII"; // 2777
        numeroRomano = new NumeroRomano(testCase);
        assertEquals(2777, numeroRomano.toDecimal());
    }

    @Test
    public void grupos_substractivos_test() {
        String testCase = "CDXLIV"; // 444
        numeroRomano = new NumeroRomano(testCase);
        assertEquals(444, numeroRomano.toDecimal());

        testCase = "CDXXXIX"; // 439
        numeroRomano = new NumeroRomano(testCase);
        assertEquals(439, numeroRomano.toDecimal());
    }

    @Test
    public void initArrayRegex_test() {
        String testCase = "V";
        numeroRomano = new NumeroRomano(testCase);
        assertEquals(2, numeroRomano.getRegexDiccionario().numRegex());
        assertEquals(5, numeroRomano.valorDecimal(testCase));
        assertEquals("(?<!C)[DM]|(?<!X)[LC](?![DM])|(?<!I)[VX](?![LC])|I(?![VX])", numeroRomano.getRegexDiccionario().getRegexValue("grupoSumatorio"));
		assertEquals("(C[DM])|(X[LC])|(I[VX])", numeroRomano.getRegexDiccionario().getRegexValue("grupoSustractivo"));
    }

    @Test
    public void toDecimal() {
        String testCase = "V";
        numeroRomano = new NumeroRomano(testCase);
        assertEquals(2, numeroRomano.getExpresionesRegulares().size());
        assertTrue(numeroRomano.getRegexDiccionario().getValues().contains("(?<!C)[DM]|(?<!X)[LC](?![DM])|(?<!I)[VX](?![LC])|I(?![VX])"));
        assertTrue(numeroRomano.getRegexDiccionario().getValues().contains("(C[DM])|(X[LC])|(I[VX])"));		
    }

    @Test
    public void valorDecimal_test() {
        String testCase = "V";
        numeroRomano = new NumeroRomano(testCase);
        assertEquals(2, numeroRomano.getRegexDiccionario().getRegex().size());
        assertEquals(5, numeroRomano.valorDecimal(testCase));

        testCase = "IV"; 
        numeroRomano = new NumeroRomano(testCase);
        assertEquals(4, numeroRomano.valorDecimal(testCase));

        testCase = "CM"; 
        numeroRomano = new NumeroRomano(testCase);
        assertEquals(900, numeroRomano.valorDecimal(testCase));

        /**
         *  test = "U";
         * numeroRomano.setNumeroRomano("U");
         * assertEquals(900, numeroRomano.valorDecimal(test));
         */
    }
}