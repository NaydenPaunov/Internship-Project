package com.dxc.payroll.persistence.jpa.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


/**
 * Tests utils methods
 */
public final class UtilsTest {

    /**
     * Tests if the method Utils.padRight works correctly for words with smaller
     * size than the preferred one. The returned word must be filled with
     * spaces.
     */
    @SuppressWarnings("nls")
    @Test
    public void swallerWordMustBeExtended() {
        final String expected = "name                            ";
        final String input = "name";
        checkPadding(expected, input, expected.length());
    }

    /**
     * Tests if the method Utils.padRight works correctly for words with the
     * same size as the preferred one. The returned word mustn't be changed.
     */
    @SuppressWarnings("nls")
    @Test
    public void wordWithTheSameLengthMustNotBeChanged() {
        final String expected = "name";
        final String input = "name";
        checkPadding(expected, input, input.length());
    }

    /**
     * Tests if the method Utils.padRight works correctly for words with larger
     * size than the preferred one. The returned word mustn't be changed.
     */
    @SuppressWarnings("nls")
    @Test
    public void largerWordMustNotBeChanged() {
        final String expected = "name";
        final String input = "name";
        checkPadding(expected, input, input.length() - 1);
    }

    @SuppressWarnings({ "static-method", "nls" })
    private void checkPadding(final String expected, final String input, final int toSize) {
        final String actual = Utils.padRight(input, toSize);
        assertEquals("Wrong padding.", expected, actual);
    }

}
