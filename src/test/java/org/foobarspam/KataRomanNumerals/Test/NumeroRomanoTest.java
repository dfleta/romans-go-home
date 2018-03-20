package org.foobarspam.KataRomanNumerals.Test;

import static org.junit.Assert.assertEquals;

import org.foobarspam.KataRomanNumerals.NumeroRomano;
import org.foobarspam.KataRomanNumerals.RegexNumerosRomanos;
import org.junit.BeforeClass;
import org.junit.Test;

public class NumeroRomanoTest {

    public static RegexNumerosRomanos arrayRegex;
    public static NumeroRomano numeroRomano;

    @BeforeClass
    public static void setup() {
        arrayRegex = new RegexNumerosRomanos();
        numeroRomano = new NumeroRomano();
        numeroRomano.setRegex(arrayRegex);
        numeroRomano.initArrayRegex();
    }

    /**
     * Grupos sumatorios M, C, X, I
     */
    @Test
    public void grupo_M_test() {

        String testCase = "M";
        numeroRomano.setNumeroRomano(testCase);
        assertEquals(1000, numeroRomano.toDecimal());

        testCase = "MM";
        numeroRomano.setNumeroRomano(testCase);
        assertEquals(2000, numeroRomano.toDecimal());

        testCase = "MMM";
        numeroRomano.setNumeroRomano(testCase);
        assertEquals(3000, numeroRomano.toDecimal());

        testCase = "MMMM";
        numeroRomano.setNumeroRomano(testCase);
        assertEquals(0, numeroRomano.toDecimal());
    }

    @Test
    public void tres_repeticiones_C_test() {

        String testCase = "CCC";
        numeroRomano.setNumeroRomano(testCase);

        assertEquals(300, numeroRomano.toDecimal());
    }

    @Test
    public void tres_repeticiones_X_test() {

        String testCase = "XXX";
        numeroRomano.setNumeroRomano(testCase);

        assertEquals(30, numeroRomano.toDecimal());
    }

    @Test
    public void tres_repeticiones_I_test() {

        String testCase = "III";
        numeroRomano.setNumeroRomano(testCase);

        assertEquals(3, numeroRomano.toDecimal());
    }

    /**
     * Grupos substractivos
     */

    @Test
    public void grupo_C_DM_test() {

        String testCase = "CD";
        numeroRomano.setNumeroRomano(testCase);
        assertEquals(400, numeroRomano.toDecimal());

        testCase = "CM";
        numeroRomano.setNumeroRomano(testCase);
        assertEquals(900, numeroRomano.toDecimal());
    }

    @Test
    public void grupo_X_LC_test() {

        String testCase = "XL";
        numeroRomano.setNumeroRomano(testCase);
        assertEquals(40, numeroRomano.toDecimal());      
    }
}