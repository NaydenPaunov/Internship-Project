package com.dxc.payroll.persistence.repositories;

import java.util.List;

import com.dxc.payroll.persistence.domain.UserRight;

/**
 * @author tdyakov
 *
 */
public interface UserRightRepository {
    /**
     * A method to return a list of all the rights
     * 
     * @return list of all rights
     */
    List<UserRight> getAllRights();

    /**
     * A method that finds and returns a right with the given name
     * 
     * @return a user right with the given name
     */
    UserRight findRightByName(String userRightName);
}
