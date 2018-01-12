package com.dxc.payroll.services.dto;

import java.util.Collections;
import java.util.List;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * This DTO is used to transfer the the states of the assigned and unassigned
 * right to a role. It holds the name of the role (which is an ID) and a list of
 * all posssible rights and whether they are assigned to this role which are
 * stored in a rights mapping DTO.
 *
 */
public class RoleMappingDTO {
    /**
     * The name of the role (id)
     */
    private final String roleName;
    /**
     * Used to hold the realtionship between this role and all the
     * rights(whether the role has a given right assgined or not)
     */
    private final List<RightMappingDTO> rightMapping;

    /**
     * Creates a new RoleMappingDTO object that holds the name of the role, and
     * a list of right mapping DTOs
     * 
     * @param roleName
     * @param rightMapping
     */
    public RoleMappingDTO(final String roleName, final List<RightMappingDTO> rightMapping) {
        this.roleName = roleName;
        this.rightMapping = Collections.unmodifiableList(rightMapping);
    }

    /**
     * @return the lis of role right mappings
     */
    public List<RightMappingDTO> getRightMapping() {
        return Collections.unmodifiableList(rightMapping);
    }

    /**
     * @return the name of the role (id)
     */
    public String getRoleName() {
        return this.roleName;
    }

    /**
     * hashCode method
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(roleName).append(rightMapping).toHashCode();
    }

    /**
     * equals method
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj != null && this.getClass() == obj.getClass()) {
            final RoleMappingDTO other = (RoleMappingDTO) obj;
            return new EqualsBuilder().append(roleName, other.roleName)
                    .append(rightMapping, other.rightMapping).isEquals();
        }
        else {
            return false;
        }
    }
}
