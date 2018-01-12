package com.dxc.payroll.persistence.domain;

/**
 * @author tdyakov
 *
 */
public interface UserRight {
    /**
     * @return the type of the right
     */
    String getTypeOfRight();

    /**
     * @return the description of the right (ex. "can see his employees")
     */
    String getDescription();
}
