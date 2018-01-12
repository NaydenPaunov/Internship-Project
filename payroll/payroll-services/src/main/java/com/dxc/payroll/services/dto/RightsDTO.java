package com.dxc.payroll.services.dto;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * A DTO object to transfer the right data. A right has two characteristics -
 * name and description
 *
 */
public class RightsDTO {

    /**
     * the name of the right
     */
    private final String name;
    /**
     * description of the right (ex. "Can see his paychecks")
     */
    private final String description;

    /**
     * Constructs a DTO Object with given name and description
     *
     * @param name
     * @param description
     */
    public RightsDTO(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * @return the name of the right
     */
    public String getName() {
        return name;
    }

    /**
     * @return the description of the right
     */
    public String getDescription() {
        return description;
    }

    /**
     * hashcode method
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(name).append(description).toHashCode();
    }

    /**
     * equals method
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj != null && this.getClass() == obj.getClass()) {
            final RightsDTO other = (RightsDTO) obj;
            return new EqualsBuilder().append(name, other.name)
                    .append(description, other.description).isEquals();
        }
        else {
            return false;
        }
    }

}
