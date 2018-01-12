package com.dxc.payroll.service.validation;

/**
 * Constants for the things to validate
 *
 */
public final class Constants {
    /**
     * length of a valid UCN
     */
    public static final int UCN_LEN = 10;
    /**
     * minimum acceptable length of a name
     */
    public static final int MIN_NAME_LEN = 1;
    /**
     * maximum acceptable length of a name
     */
    public static final int MAX_NAME_LEN = 60;
    /**
     * minimum acceptable length of a user name
     */
    public static final int MIN_USERNAME_LEN = 6;
    /**
     * maximum acceptable length of a name
     */
    public static final int MAX_USERNAME_LEN = 32;
    /**
     * minimum acceptable length of a password
     */
    public static final int MIN_PASS_LEN = 8;
    /**
     * maximum acceptable length of a password
     */
    public static final int MAX_PASS_LEN = 32;

    /**
     * This class should not be instantiated
     */
    private Constants() {
        /**
         * This class should not be instantiated
         */
    }

}
