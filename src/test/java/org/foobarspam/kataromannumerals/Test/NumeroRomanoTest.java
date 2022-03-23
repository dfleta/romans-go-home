package org.foobarspam.kataromannumerals.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.foobarspam.kataromannumerals.NumeroRomano;
import org.junit.BeforeClass;
import org.junit.Test;

public class NumeroRomanoTest {

    public static NumeroRomano numeroRomano;

    @BeforeClass
    public static void setup() {
        numeroRomano = new NumeroRomano();
        numeroRomano.initRegexDicionario();
    }

    /**
     * Grupos sumatorios M, C, X, I
     */

    @Test
    public void grupo_M_test() {

        String testCase = "M";
        numeroRomano.setNumeroRomano(testCase);
        assertEquals(1000, numeroRomano.toDecimal());

        testCase = "UMMU";
        numeroRomano.setNumeroRomano(testCase);
        assertEquals(2000, numeroRomano.toDecimal());

        testCase = "UMMMU";
        numeroRomano.setNumeroRomano(testCase);
        assertEquals(3000, numeroRomano.toDecimal());

        testCase = "UCMU";
        numeroRomano.setNumeroRomano(testCase);
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
        numeroRomano.setNumeroRomano(testCase);
        assertEquals(3300, numeroRomano.toDecimal());
    }

    @Test
    public void tres_repeticiones_X_test() {

        String testCase = "UMMMUXXXU";
        numeroRomano.setNumeroRomano(testCase);

        assertEquals(3030, numeroRomano.toDecimal());
    }

    @Test
    public void tres_repeticiones_I_test() {

        String testCase = "UMMMUIIIU";
        numeroRomano.setNumeroRomano(testCase);

        assertEquals(3003, numeroRomano.toDecimal());
    }

    @Test
    public void una_D_test() {

        String testCase = "UMMMUDUIIIU";
        numeroRomano.setNumeroRomano(testCase);
        assertEquals(3503, numeroRomano.toDecimal());

        testCase = "MMMUCDUIIIU";
        numeroRomano.setNumeroRomano(testCase);
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
        numeroRomano.setNumeroRomano(testCase);
        assertEquals(400, numeroRomano.toDecimal());

        testCase = "UCMU";
        numeroRomano.setNumeroRomano(testCase);
        assertEquals(900, numeroRomano.toDecimal());
    }

    @Test
    public void grupo_X_LC_test() {

        String testCase = "UXLU";
        numeroRomano.setNumeroRomano(testCase);
        assertEquals(40, numeroRomano.toDecimal());  

        testCase = "UXCU";
        numeroRomano.setNumeroRomano(testCase);
        assertEquals(90, numeroRomano.toDecimal());        
    }

    @Test
    public void grupo_I_VX_test() {

        String testCase = "UIVU";
        numeroRomano.setNumeroRomano(testCase);
        assertEquals(4, numeroRomano.toDecimal());  

        testCase = "UIXU";
        numeroRomano.setNumeroRomano(testCase);
        assertEquals(9, numeroRomano.toDecimal());  
    }

    @Test
    public void grupos_sumatorios_tres_digitos_test() {
        String test = "MMMDCCCLXXXVIII"; // 3888
        numeroRomano.setNumeroRomano(test);
        assertEquals(3888, numeroRomano.toDecimal());
    }

    @Test
    public void grupos_sumatorios_test() {
        String test = "MMDCCLXXVII"; // 2777
        numeroRomano.setNumeroRomano(test);
        assertEquals(2777, numeroRomano.toDecimal());
    }

    @Test
    public void grupos_substractivos_test() {
        String test = "CDXLIV"; // 444
        numeroRomano.setNumeroRomano(test);
        assertEquals(444, numeroRomano.toDecimal());

        test = "CDXXXIX"; // 439
        numeroRomano.setNumeroRomano(test);
        assertEquals(439, numeroRomano.toDecimal());
    }

    @Test
    public void initArrayRegex_test() {
        String test = "V";
        numeroRomano.setNumeroRomano(test);
        assertEquals(2, numeroRomano.getRegexDiccionario().longitud());
        assertEquals(5, numeroRomano.valorDecimal(test));
        assertEquals("(?<!C)[DM]|(?<!X)[LC](?![DM])|(?<!I)[VX](?![LC])|I(?![VX])", numeroRomano.getRegexDiccionario().getRegexValue("grupoSumatorio"));
		assertEquals("(C[DM])|(X[LC])|(I[VX])", numeroRomano.getRegexDiccionario().getRegexValue("grupoSustractivo"));
    }

    @Test
    public void toDecimal() {
        String test = "V";
        numeroRomano.setNumeroRomano(test);
        assertEquals(2, numeroRomano.getExpresionesRegulares().size());
        assertTrue(numeroRomano.getRegexDiccionario().getValues().contains("(?<!C)[DM]|(?<!X)[LC](?![DM])|(?<!I)[VX](?![LC])|I(?![VX])"));
        assertTrue(numeroRomano.getRegexDiccionario().getValues().contains("(C[DM])|(X[LC])|(I[VX])"));		
    }

    @Test
    public void valorDecimal_test() {
        String test = "V";
        numeroRomano.setNumeroRomano(test);
        assertEquals(2, numeroRomano.getRegexDiccionario().getRegex().size());
        assertEquals(5, numeroRomano.valorDecimal(test));

        test = "IV"; 
        numeroRomano.setNumeroRomano(test);
        assertEquals(4, numeroRomano.valorDecimal(test));

        test = "CM"; 
        numeroRomano.setNumeroRomano(test);
        assertEquals(900, numeroRomano.valorDecimal(test));

        /**
         *  test = "U";
         * numeroRomano.setNumeroRomano("U");
         * assertEquals(900, numeroRomano.valorDecimal(test));
         */
    }
}