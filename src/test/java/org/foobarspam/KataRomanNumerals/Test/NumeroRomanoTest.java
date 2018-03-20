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

        String testCase = "UMU";
        numeroRomano.setNumeroRomano(testCase);
        assertEquals(1000, numeroRomano.toDecimal());

        testCase = "UMMU";
        numeroRomano.setNumeroRomano(testCase);
        assertEquals(2000, numeroRomano.toDecimal());

        testCase = "UMMMU";
        numeroRomano.setNumeroRomano(testCase);
        assertEquals(3000, numeroRomano.toDecimal());

        /**
         * El caso MMMM es control de errores 
         * y no puede estas en el test de la logica
         * Asumimos que la entrada es correcta.
         * Sino, hay que añadir un modulo barricada
         */
    }

    @Test
    public void tres_repeticiones_C_test() {

        String testCase = "UCCCU";
        numeroRomano.setNumeroRomano(testCase);
        assertEquals(300, numeroRomano.toDecimal());
    }

    @Test
    public void tres_repeticiones_X_test() {

        String testCase = "UXXXU";
        numeroRomano.setNumeroRomano(testCase);

        assertEquals(30, numeroRomano.toDecimal());
    }

    @Test
    public void tres_repeticiones_I_test() {

        String testCase = "UIIIU";
        numeroRomano.setNumeroRomano(testCase);

        assertEquals(3, numeroRomano.toDecimal());
    }

    /**
     * Grupos substractivos
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

        String testCase = "XL";
        numeroRomano.setNumeroRomano(testCase);
        assertEquals(40, numeroRomano.toDecimal());  

        testCase = "XC";
        numeroRomano.setNumeroRomano(testCase);
        assertEquals(90, numeroRomano.toDecimal());        
    }

    @Test
    public void grupo_I_VX_test() {

        String testCase = "IV";
        numeroRomano.setNumeroRomano(testCase);
        assertEquals(4, numeroRomano.toDecimal());  

        testCase = "IX";
        numeroRomano.setNumeroRomano(testCase);
        assertEquals(9, numeroRomano.toDecimal());  
    }
}