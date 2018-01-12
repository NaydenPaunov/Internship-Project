package com.dxc.payroll.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UtilsTest {

    /**
     * Tests the case when the string is successfully parsed.
     */
    @SuppressWarnings({ "nls", "static-method" })
    @Test
    public void stringToIntSuccess() {
        final String value = "111";
        final int expected = 111;
        final int actual = Utils.stringToInt(value);
        assertEquals("Failed to parse string.", expected, actual);
    }
}
