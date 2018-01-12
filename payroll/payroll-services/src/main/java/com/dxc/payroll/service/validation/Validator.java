package com.dxc.payroll.service.validation;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The validator is used to validate the string representation of the ucn,
 * username, name and password entities
 *
 */
@SuppressWarnings("nls")
public class Validator {

    /**
     * A list of entities which will be validated when validateAll() is called
     */
    private Set<ValidationEntity> entitiesToValidate = new HashSet<>();

    /**
     * Constructor
     */
    public Validator() {
        /**
         * Constructs a new empty validator
         */
    }

    /**
     * Check if the given validation entity is waiting for validation
     *
     * @param ve
     *            - validation entity
     * @return true if and only if ve is waiting for validation
     */
    public boolean isForValidation(final ValidationEntity ve) {
        return entitiesToValidate.contains(ve);
    }

    /**
     * Add a new entity for validation
     *
     * @param type
     *            the type of the entity to validate
     * @param str
     *            the string to validate
     */
    public void addForValidation(final Type type, final String str) {
        entitiesToValidate.add(new ValidationEntity(type, str));
    }

    /**
     * This method validates the list of entities that the validator holds
     * validates them and throws an exception if some of them are not valid then
     * resets the list of entities
     *
     * @param entitiesToValidate
     * @throws ValidationException
     */
    public void validateAll() throws ValidationException {
        final Map<ValidationEntity, List<ErrorCode>> entityToErrorCodes = new ConcurrentHashMap<>();
        for (final ValidationEntity etv : entitiesToValidate) {
            switch (etv.getType()) {
            case NAME:
                entityToErrorCodes.put(etv, getNameValidationCodes(etv.getString()));
                break;
            case USERNAME:
                entityToErrorCodes.put(etv, getUsernameValidationCodes(etv.getString()));
                break;
            case PASSWORD:
                entityToErrorCodes.put(etv, getPasswordValidationCodes(etv.getString()));
                break;
            case UCN:
                entityToErrorCodes.put(etv, getUCNvalidationCodes(etv.getString()));
                break;
            default:
                break;
            }
        }
        /**
         * remove the keys in the map for which there are no error codes
         */
        for (final Entry<?, ?> entry : entityToErrorCodes.entrySet()) {
            if (entry.getValue().equals(Collections.emptyList())) {
                entityToErrorCodes.remove(entry.getKey());
            }
        }
        /*
         * reset the entities to validate
         */
        entitiesToValidate = Collections.emptySet();
        /*
         * if the map is not empty there are entities which are not valid
         */
        if (!entityToErrorCodes.isEmpty()) {
            throw new ValidationException(entityToErrorCodes, exceptionMessage(entityToErrorCodes));
        }
    }

    private static String exceptionMessage(
            final Map<ValidationEntity, List<ErrorCode>> entityToErrorCodes) {
        final StringBuilder messageBuffer = new StringBuilder();
        for (final Entry<?, ?> entry : entityToErrorCodes.entrySet()) {
            messageBuffer.append(entry.getKey());
            messageBuffer.append(entry.getValue());
        }
        return messageBuffer.toString();
    }

    /**
     * This method returns the list of error codes for the specified thing to
     * validate
     *
     * @param pass
     * @return list of error codes for the specified thing to validate
     */
    protected static List<ErrorCode> getPasswordValidationCodes(final String pass) {
        final List<ErrorCode> errorCodes = Utils.getLengthErrorCodes(pass, Constants.MIN_PASS_LEN,
                Constants.MAX_PASS_LEN);
        if (!pass.matches("^\\p{ASCII}*$")) {
            errorCodes.add(ErrorCode.CONTAINS_NON_ASCII);
        }
        return errorCodes;
    }

    /**
     * This method returns the list of error codes for the specified thing to
     * validate
     *
     * @param username
     * @return list of error codes for the specified thing to validate
     */
    protected static List<ErrorCode> getUsernameValidationCodes(final String username) {
        final List<ErrorCode> errorCodes = Utils.getLengthErrorCodes(username,
                Constants.MIN_USERNAME_LEN, Constants.MAX_USERNAME_LEN);
        if (!username.matches("[0-9A-Za-z_]*")) {
            errorCodes.add(ErrorCode.CONTAINS_NON_ALPHANUMERIC);
        }
        return errorCodes;
    }

    /**
     * This method returns the list of error codes for the specified thing to
     * validate
     *
     * @param name
     * @return list of error codes for the specified thing to validate
     */
    protected static List<ErrorCode> getNameValidationCodes(final String name) {
        final List<ErrorCode> errorCodes = Utils.getLengthErrorCodes(name, Constants.MIN_NAME_LEN,
                Constants.MAX_NAME_LEN);
        if (!Utils.isAlphabetic(name)) {
            errorCodes.add(ErrorCode.CONTAINS_NON_ALPHABETIC);
        }
        return errorCodes;
    }

    /**
     * This method returns the list of error codes for the specified thing to
     * validate
     *
     * @param ucn
     * @return list of error codes for the specified thing to validate
     */
    protected static List<ErrorCode> getUCNvalidationCodes(final String ucn) {
        final List<ErrorCode> errorCodes = Utils.getLengthErrorCodes(ucn, Constants.UCN_LEN,
                Constants.UCN_LEN);
        if (!ucn.matches("[0-9]*")) {
            errorCodes.add(ErrorCode.CONTAINS_NON_DIGITS);
        }
        return errorCodes;
    }

}
