package com.dxc.payroll.persistence.domain;

import java.util.List;

/**
 * @author tdyakov
 *
 */
public interface Role {
    /**
     * @return the name of the role (eg. "Manager")
     */
    String getRoleName();

    /**
     * @return the description of the role
     */
    String getDescription();

    /**
     * @return the rights that this role has
     */
    List<UserRight> getUserRights();

    /**
     * Replaces the assigned rights to the role with the given user rights
     *
     * @param userRights
     *            the rights that will be assigned to the role
     */
    void updateUserRights(List<UserRight> userRights);

}
