package com.dxc.payroll.service.validation;

/**
 * @author tdyakov
 *
 */
public final class Utils {
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
}
