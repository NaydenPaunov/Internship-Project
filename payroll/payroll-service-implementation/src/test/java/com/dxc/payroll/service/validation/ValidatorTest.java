package com.dxc.payroll.service.validation;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

/**
 * @author tdyakov
 *
 */

@SuppressWarnings({ "static-method", "nls" })
public class ValidatorTest {

    @Test
    public void isValidUCNTestValid() {
        assert Validator.isValidUCN("1234567890");
        assert Validator.isValidUCN("9871234567");
        assert Validator.isValidUCN("2345123456");
    }

    @Test
    public void isValidUCNTestShorter() {
        final String testString = "123456789";
        assert testString.length() == 9;
        assertFalse(Validator.isValidUCN(testString));
    }

    @Test
    public void isValidUCNTestLonger() {
        final String testString = "12345678910";
        assert testString.length() == 11;
        assertFalse(Validator.isValidUCN(testString));
    }

    @Test
    public void isValidUCNTestLetters() {
        final String testString = "123abc4567";
        assert testString.length() == 10;
        assertFalse(Validator.isValidUCN(testString));
    }

    @Test
    public void isValidPasswordTest() {
        final String[] validPasswords = new String[] { "Dragon123", "PESHO$$$_123_*^", "kotka967",
                "petkan$$$_123&*(", "12345678" };
        for (final String s : validPasswords) {
            assert Validator.isValidPassword(s);
        }
    }

    @Test
    public void isValidPasswordTestNonASCII() {
        final String nonASCII_STRING = "коткаКуче";
        assertFalse(Validator.isValidPassword(nonASCII_STRING));
        final String nonASCII_STRING_MIXED = "котка123abc";
        assertFalse(Validator.isValidPassword(nonASCII_STRING_MIXED));
    }

    @Test
    public void isValidUsernameTest() {
        final String[] validUsernames = new String[] { "Dragon123", "abcdefghj", "kotka967",
                "Ivan_P_95", "12345678" };
        for (final String s : validUsernames) {
            assert Validator.isValidUsername(s);
        }
    }

    @Test
    public void isValidUsernameTestNonASCII() {
        final String nonASCII_STRING = "коткаКуче";
        assertFalse(Validator.isValidUsername(nonASCII_STRING));
        final String nonASCII_STRING_MIXED = "котка123abc";
        assertFalse(Validator.isValidUsername(nonASCII_STRING_MIXED));
    }

    @Test
    public void isValidNameTest() {
        final String[] validNames = new String[] { "Maria", "Maria-Magdalena", "Martin",
                "Alexander" };
        for (String s : validNames) {
            assert (Validator.isValidName(s));
        }
    }

}
