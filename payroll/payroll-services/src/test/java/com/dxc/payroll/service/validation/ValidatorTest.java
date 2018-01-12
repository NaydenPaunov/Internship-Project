package com.dxc.payroll.service.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

/**
 * @author tdyakov
 *
 */

@SuppressWarnings({ "static-method", "nls" })
public class ValidatorTest {

    @Test
    public void testGetUCNvalidationCodesValid() {
        final String[] validUCNs = new String[] { "1234567890", "9876543210", "1234554321" };
        for (final String s : validUCNs) {
            final String message = "I was expecting" + s + "to be a valid UCN but it is invalid";
            assertEquals(message, Collections.emptyList(), Validator.getUCNvalidationCodes(s));
        }
    }

    @Test
    public void testGetPasswordValidationCodesValid() {
        final String[] validPasswords = new String[] { "Dragon123", "PESHO$$$_123_*^", "kotka967",
                "petkan$$$_123&*(", "12345678" };
        for (final String s : validPasswords) {
            final String message = "I was expecting" + s
                    + "to be a valid password but it is invalid";
            assertEquals(message, Collections.emptyList(), Validator.getPasswordValidationCodes(s));
        }
    }

    @Test
    public void testGetUsernameValidationCodesValid() {
        final String[] validUsernames = new String[] { "Dragon123", "abcdefghj", "kotka967",
                "Ivan_P_95", "12345678" };
        for (final String s : validUsernames) {
            final String message = "I was expecting" + s
                    + "to be a valid username but it is invalid";
            assertEquals(message, Collections.emptyList(), Validator.getUsernameValidationCodes(s));
        }
    }

    @Test
    public void testGetNameValidationCodesValid() {
        final String[] validNames = new String[] { "Maria", "Maria-Magdalena", "Martin",
                "Alexander" };
        for (final String s : validNames) {
            final String message = "I was expecting" + s + "to be a valid name but it is invalid";
            assertEquals(message, Collections.emptyList(), Validator.getNameValidationCodes(s));
        }
    }

    @Test
    public void testGetNameValidationCodesNonAlphabetic() {
        final String invalidName = "123abc";
        final List<ErrorCode> expectedErrorCodes = new ArrayList<>();
        expectedErrorCodes.add(ErrorCode.CONTAINS_NON_ALPHABETIC);
        final String message = "I was expecting" + invalidName
                + "to be an invalid name but it is valid";
        assertEquals(message, expectedErrorCodes, Validator.getNameValidationCodes(invalidName));
    }

    @Test
    public void testGetPasswordValidationCodesNonAscii() {
        final String invalidPassword = "котка123";
        final List<ErrorCode> expectedErrorCodes = new ArrayList<>();
        expectedErrorCodes.add(ErrorCode.CONTAINS_NON_ASCII);
        final String message = "I was expecting" + invalidPassword
                + "to be an invalid password but it is valid";
        assertEquals(message, expectedErrorCodes,
                Validator.getPasswordValidationCodes(invalidPassword));
    }

    @Test
    public void testGetUsernameValidationCodesNonAlphanumeric() {
        final String invalidUsername = "$$pesho";
        final List<ErrorCode> expectedErrorCodes = new ArrayList<>();
        expectedErrorCodes.add(ErrorCode.CONTAINS_NON_ALPHANUMERIC);
        final String message = "I was expecting" + invalidUsername
                + "to be an invalid username but it is valid";
        assertEquals(message, expectedErrorCodes,
                Validator.getUsernameValidationCodes(invalidUsername));
    }

    @Test
    public void testGetUCNValidationCodesNonAlphanumeric() {
        final String invalidUCN = "123abc4567";
        final List<ErrorCode> expectedErrorCodes = new ArrayList<>();
        expectedErrorCodes.add(ErrorCode.CONTAINS_NON_DIGITS);
        final String message = "I was expecting" + invalidUCN
                + "to be an invalid UCN but it is valid";
        assertEquals(message, expectedErrorCodes, Validator.getUCNvalidationCodes(invalidUCN));
    }

    @Test
    public void testAddForValidation() {
        final Validator validator = new Validator();
        validator.addForValidation(Type.NAME, "Martin");
        assertTrue("was expecting for this entity to be added for validation",
                validator.isForValidation(new ValidationEntity(Type.NAME, "Martin")));
    }

    @Test(expected = ValidationException.class)
    public void testValidateAllThrowsValidationExceptionName() throws ValidationException {
        final Validator validator = new Validator();
        validator.addForValidation(Type.NAME, "Martin123");
        validator.validateAll();
    }

    @Test(expected = ValidationException.class)
    public void testValidateAllThrowsValidationExceptionPass() throws ValidationException {
        final Validator validator = new Validator();
        validator.addForValidation(Type.PASSWORD, "123");
        validator.validateAll();
    }

    @Test(expected = ValidationException.class)
    public void testValidateAllThrowsValidationExceptionUsername() throws ValidationException {
        final Validator validator = new Validator();
        validator.addForValidation(Type.USERNAME, "123");
        validator.validateAll();
    }

    @Test(expected = ValidationException.class)
    public void testValidateAllThrowsValidationExceptionUCN() throws ValidationException {
        final Validator validator = new Validator();
        validator.addForValidation(Type.UCN, "abc");
        validator.validateAll();
    }

    @Test
    public void testValidateAllDoesNotThrowValidationException() {
        final Validator validator = new Validator();
        validator.addForValidation(Type.UCN, "1234567890");
        validator.addForValidation(Type.NAME, "Martin");
        validator.addForValidation(Type.PASSWORD, "pass1234");
        validator.addForValidation(Type.USERNAME, "petar123");
        boolean thrown = false;
        try {
            validator.validateAll();
        }
        catch (ValidationException e) {
            thrown = true;
            e.printStackTrace();
        }
        assertFalse("I expected validateAll() not throw an exception but it did", thrown);
    }

}
