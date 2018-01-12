package com.dxc.payroll.services.dto;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * A DTO that holds the fields of a role and a boolean state the boolean state
 * indicates whether a role holding a reference to this object has this right
 * assigned or not
 */
public class RightMappingDTO {
    /**
     * the name of the right (ID)
     */
    private final String rightName;

    /**
     * Description of the right
     */
    private final String description;

    /**
     * true if a right referencing this object has this right assigned
     */
    private final boolean checked;

    /**
     * @param rightName
     * @param description
     * @param state
     */
    public RightMappingDTO(final String rightName, final String description, final boolean state) {
        this.rightName = rightName;
        this.checked = state;
        this.description = description;
    }

    /**
     * @return the name of the right
     */
    public String getRightName() {
        return rightName;
    }

    /**
     * @return the state of the relationship
     */
    public boolean isChecked() {
        return checked;
    }

    /**
     * @return description of the right
     */
    public String getDescription() {
        return description;
    }

    /**
     * hashCode method
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(rightName).append(description).append(checked)
                .toHashCode();
    }

    /**
     * equals method
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj != null && this.getClass() == obj.getClass()) {
            final RightMappingDTO other = (RightMappingDTO) obj;
            return new EqualsBuilder().append(rightName, other.rightName)
                    .append(description, other.description).append(checked, other.checked)
                    .isEquals();
        }
        else {
            return false;
        }
    }
}
