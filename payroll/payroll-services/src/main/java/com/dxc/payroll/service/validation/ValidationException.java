package com.dxc.payroll.service.validation;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dxc.payroll.service.exceptions.PayrollException;

/**
 * This exception is thrown when there is a invalid ucn, username, name or
 * password. It contains a map that maps entities to the error codes that
 * occurred in their validation
 *
 */
public class ValidationException extends PayrollException {

    /**
     *
     */
    private static final long serialVersionUID = 5695466762024923071L;
    /**
     * map that maps validation entities to the error codes that occurred in
     * their validation
     */
    private final Map<ValidationEntity, List<ErrorCode>> entityToErrorCodes;

    /**
     * @param entityToErrorCode
     */
    public ValidationException(final Map<ValidationEntity, List<ErrorCode>> entityToErrorCode,
            final String exceptionMessage) {
        super(exceptionMessage);
        this.entityToErrorCodes = new ConcurrentHashMap<>(entityToErrorCode);
    }

    /**
     * This method returns a map that maps a validation entity to the error
     * codes that occured in its validation
     *
     * @return map that maps a validation entity to the error codes that occured
     *         in its validation
     */
    public Map<ValidationEntity, List<ErrorCode>> getEntityToErrorCodesMap() {
        return Collections.unmodifiableMap(entityToErrorCodes);
    }

}
