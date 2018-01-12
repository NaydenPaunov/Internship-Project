package com.dxc.payroll.services.dto;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * DTO to hold the name of a role (id)
 *
 */
public class RoleNameDTO {

    /**
     * name of the role
     */
    private final String roleName;

    /**
     * @param roleName
     */
    public RoleNameDTO(final String roleNameArg) {
        this.roleName = roleNameArg;
    }

    /**
     * Getter for role name
     * 
     * @return roleName - name of the role
     */
    public String getRoleName() {
        return this.roleName;
    }

    /**
     * hashCode method
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(roleName).toHashCode();
    }

    /**
     * equals method
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj != null && this.getClass() == obj.getClass()) {
            final RoleNameDTO other = (RoleNameDTO) obj;
            return new EqualsBuilder().append(roleName, other.roleName).isEquals();
        }
        else {
            return false;
        }
    }
}
