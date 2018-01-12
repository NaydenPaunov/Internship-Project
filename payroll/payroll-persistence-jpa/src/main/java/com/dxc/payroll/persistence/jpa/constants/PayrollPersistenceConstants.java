package com.dxc.payroll.persistence.jpa.constants;

/**
 * Class containing all the constants for the fix-length fields of type CHAR in
 * the database
 */
public final class PayrollPersistenceConstants {
    /**
     * constant for the fixed length of the JOB_TITLE column in the database
     */
    public static final int JOB_TITLE_LENGTH = 32;
    /**
     * constant for the fixed length of the JOB_DEGREE column in the database
     */
    public static final int JOB_DEGREE_LENGTH = 20;

    /**
     * constant for the fixed length of the ROLE_NAME column in the database
     */
    public static final int ROLE_NAME_LENGTH = 32;

    /**
     * Private constructor for the PayrollPersistenceConstants class
     */
    private PayrollPersistenceConstants() {
    }
}
