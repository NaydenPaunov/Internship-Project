package com.dxc.payroll.service.validation;

/**
 * @author tdyakov
 *
 */
@SuppressWarnings("nls")
public final class Validator {

    private Validator() {
    }

    /**
     *
     * @param str
     *            the string to check
     * @return true if and only if the given string can be a valid bulgarian
     *         UCN. That means it is of length 10 and contains only decimal
     *         digits
     */
    public static boolean isValidUCN(final String str) {
        return str.matches("[0-9]{10}");
    }

    private static final int MIN_NAME_LEN = 1;
    private static final int MAX_NAME_LEN = 60;

    /**
     *
     * @param str
     *            the string to check
     * @return true if and only if the given string consists of English letters
     *         and its length is between MIN_NAME_LEN and MAX_NAME_LEN
     */
    public static boolean isValidName(final String str) {
        return Utils.isAlphabetic(str) && str.length() >= MIN_NAME_LEN
                && str.length() <= MAX_NAME_LEN;

    }

    private static final int MIN_USERNAME_LEN = 6;
    private static final int MAX_USERNAME_LEN = 32;

    /**
     *
     * @param str
     *            the string to check
     * @return true if and only if the given string consists only of English
     *         letters, digits and '_' and is between MIN_USERNAME_LEN and
     *         MAX_USERNAME_LEN characters
     */
    public static boolean isValidUsername(final String str) {
        return str.matches("[0-9A-Za-z_]*") && str.length() >= MIN_USERNAME_LEN
                && str.length() <= MAX_USERNAME_LEN;
    }

    // TODO pass validation logic
    private static final int MIN_PASS_LEN = 8;
    private static final int MAX_PASS_LEN = 32;

    /**
     *
     * @param str
     *            the string to check
     * @return true if and only if the length of the given string is between
     *         MIN_PASS_LEN and MAX_PASS_LEN characters and contains only ASCII
     *         characters
     */
    public static boolean isValidPassword(final String str) {
        return str.matches("^\\p{ASCII}*$") && str.length() >= MIN_PASS_LEN
                && str.length() <= MAX_PASS_LEN;
    }

}
