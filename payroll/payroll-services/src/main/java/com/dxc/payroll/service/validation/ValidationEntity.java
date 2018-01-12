package com.dxc.payroll.service.validation;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * An object that holds the type of the entity to validate and its string
 * representation which should be validated
 */
class ValidationEntity implements Serializable {
    /**
     * default serial version UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * the type of the validation entity (ex. Type.UCN, Type.USERNAME)
     */
    private final Type type;
    /**
     * The string to validate
     */
    private final String value;

    /**
     * @param type
     * @param value
     */
    public ValidationEntity(final Type t, final String value) {
        this.type = t;
        this.value = value;
    }

    /**
     * @return the type of the entity
     */
    public Type getType() {
        return type;
    }

    /**
     * @return the string to validate
     */
    public String getString() {
        return value;
    }

    /**
     * to string method
     */
    @SuppressWarnings("nls")
    @Override
    public String toString() {
        return "ValidationEntity [type=" + type + ", str=" + value + "]";
    }

    /**
     * hashCode method
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(type).append(value).toHashCode();
    }

    /**
     * equals method
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj != null && this.getClass() == obj.getClass()) {
            final ValidationEntity other = (ValidationEntity) obj;
            return new EqualsBuilder().append(type, other.type).append(value, other.value)
                    .isEquals();
        }
        else {
            return false;
        }
    }

}
