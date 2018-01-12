package com.dxc.payroll.service.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

@SuppressWarnings({ "static-method", "nls" })
public class UtilsTest {

    @Test
    public void testGetLengthErrorCodesValid() {
        final String[] testStrings = new String[] { "12345", "1234", "123" };
        final int MIN_LEN = 3;
        final int MAX_LEN = 5;
        for (final String s : testStrings) {
            assertEquals("expected the error code list to be empty", Collections.emptyList(),
                    Utils.getLengthErrorCodes(s, MIN_LEN, MAX_LEN));
        }
    }

    @Test
    public void testGetLengthErrorCodesShorter() {
        final String[] testStrings = new String[] { "123", "1", "12" };
        final int MIN_LEN = 4;
        final int MAX_LEN = 1000;
        final List<ErrorCode> expectedErrorCodes = new ArrayList<>();
        expectedErrorCodes.add(ErrorCode.TOO_SHORT);
        for (final String s : testStrings) {
            assertEquals("expected the error code list to contain only TOO_SHORT",
                    expectedErrorCodes, Utils.getLengthErrorCodes(s, MIN_LEN, MAX_LEN));
        }
    }

    @Test
    public void testGetLengthErrorCodesLonger() {
        final String[] testStrings = new String[] { "1234", "123", "12" };
        final int MIN_LEN = 0;
        final int MAX_LEN = 1;
        final List<ErrorCode> expectedErrorCodes = new ArrayList<>();
        expectedErrorCodes.add(ErrorCode.TOO_LONG);
        for (final String s : testStrings) {
            assertEquals("expected the error code list to contain only TOO_LONG",
                    expectedErrorCodes, Utils.getLengthErrorCodes(s, MIN_LEN, MAX_LEN));
        }
    }

    @Test
    public void testisAlphabeticValid() {
        assertTrue("i was expecting abcde to be an aphabetic string", Utils.isAlphabetic("abcde"));
    }

    @Test
    public void testisAlphabeticInvalid() {
        assertFalse("i was expecting 1abc to not be an aphabetic string",
                Utils.isAlphabetic("1abc"));
    }

}
