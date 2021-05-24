package com.Sandra.ISBNtools;

import com.Sandra.ISBNtools.ISBNtools.ValidateISBN;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValidateISBNTest {

    @Test
    public void checkAvalid10ISBN(){
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("0140449116");
        assertTrue("First value", result);
        result = validator.checkISBN("0140177396");
        assertTrue("Second value", result);
    }

/*
    @Test
    public void TenDigitsISBNNumberEndingWithAnXAreValid(){
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("012000030X");
        assertTrue(result);
    }
*/

    @Test
    public void checkAnInvalid10ISBN(){
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("0140449117");
        assertFalse(result);
    }


    @Test
    public void checkAnInvalid13ISBN(){
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("9781853260086");
        assertFalse(result);
    }


    @Test
    public void checkValid13DigitISBN(){
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("9781853260087");
        assertTrue("first value", result);
        result = validator.checkISBN("9781853267338");
        assertTrue("second value", result);
    }



    @Test(expected = StringIndexOutOfBoundsException.class)
    public void nineDigitsISBNsAreNotAllowed(){
        ValidateISBN validator = new ValidateISBN();
        validator.checkISBN("123456789");
    }

    @Test (expected = NumberFormatException.class)
    public void onlyNumbersAllowed(){
    ValidateISBN validator = new ValidateISBN();
    validator.checkISBN("helloworld");
    }

}
