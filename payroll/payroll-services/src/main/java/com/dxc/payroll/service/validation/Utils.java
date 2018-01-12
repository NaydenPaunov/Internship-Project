package com.dxc.payroll.service.validation;

import java.util.ArrayList;
import java.util.List;

/**
 * Utilities for validation of inputs
 *
 */
public final class Utils {

    /**
     * This class should not be instantiated
     */
    private Utils() {
        // this class should not be instantiated
    }

    /**
     *
     * @param str
     *            the string to check
     * @return true if and only if the given consists of upper and lower case
     *         English letters and '-'
     */
    @SuppressWarnings("nls")
    public static boolean isAlphabetic(final String str) {
        return str.matches("[a-zA-Z-]*");
    }

    /**
     * this method checks if the length is below or above the min amd max len
     *
     * @param str
     * @param minLen
     * @param maxLen
     * @return a list of error codes specifiying whether the length is below min
     *         or above max length or an empty list if it is in the range
     */
    public static List<ErrorCode> getLengthErrorCodes(final String str, final int minLen,
            final int maxLen) {
        final List<ErrorCode> errorCodes = new ArrayList<>();
        if (str.length() < minLen) {
            errorCodes.add(ErrorCode.TOO_SHORT);
        }
        if (str.length() > maxLen) {
            errorCodes.add(ErrorCode.TOO_LONG);
        }
        return errorCodes;
    }
}
