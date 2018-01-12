package com.dxc.payroll.persistence.jpa.utils;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Converter between LocalDate and java.sql.Date for JPA entities.
 *
 */
@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

    /**
     * Converts from LocalDate to java.sql.Date.
     * 
     * @see javax.persistence.AttributeConverter#convertToDatabaseColumn(java.lang.Object)
     */
    @Override
    public Date convertToDatabaseColumn(final LocalDate entityValue) {
        return entityValue == null ? null : Date.valueOf(entityValue);
    }

    /**
     * Converts to LocalDate from java.sql.Date.
     * 
     * @see javax.persistence.AttributeConverter#convertToEntityAttribute(java.lang.
     *      Object)
     */
    @Override
    public LocalDate convertToEntityAttribute(final Date dbValue) {
        return dbValue == null ? null : dbValue.toLocalDate();
    }

}
