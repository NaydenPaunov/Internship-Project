package com.dxc.payroll.service.validation;

/**
 * error codes for the entities to validate
 *
 */
public enum ErrorCode {
    /**
     * Contains symbols that are not digits
     */
    CONTAINS_NON_DIGITS,
    /**
     * Shorter than minimum acceptable length
     */
    TOO_SHORT,
    /**
     * Longer than minimum acceptable length
     */
    TOO_LONG,
    /**
     * Contains symbols that are not ASCII
     */
    CONTAINS_NON_ASCII,
    /**
     * Contains symbols other than English letters and digits
     */
    CONTAINS_NON_ALPHANUMERIC,
    /**
     * contains sybols other than English letters and '-'
     */
    CONTAINS_NON_ALPHABETIC
}
