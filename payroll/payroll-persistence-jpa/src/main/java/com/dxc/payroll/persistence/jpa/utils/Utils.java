package com.dxc.payroll.persistence.jpa.utils;

/**
 * Utils class .
 */
public final class Utils {
    /**
     * Private constructor to hide the explicit public one
     */
    private Utils() {
    }

    /**
     *
     * @param size
     *            the number of the symbols preferred for your word, the size
     *            must be larger than or equal to zero
     * @param str
     *            the word that you want to extend, must not be null
     * @return The method returns the word filled with spaces. If the size of
     *         the given word is larger than or same as the preferred one, the
     *         returned string is the same as the given one
     */
    @SuppressWarnings("nls")
    public static String padRight(final String str, final int size) {
        final String format = new StringBuilder("%1$-").append(size).append("s").toString();
        return String.format(format, str);
    }

}
