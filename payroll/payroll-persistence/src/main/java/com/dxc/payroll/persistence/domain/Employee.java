package com.dxc.payroll.persistence.domain;

import java.util.List;

/**
 * @author sshishkov
 *
 */

public interface Employee {
    /**
     * Get ucn.
     * 
     * @return String
     */
    String getUCN();

    /**
     * Get name.
     * 
     * @return String
     */
    String getName();

    /**
     * Get username.
     * 
     * @return String
     */
    String getUserName();

    /**
     * Get password.
     * 
     * @return String
     */

    String getPassword();

    /**
     * Get previous work experience.
     * 
     * @return String
     */

    int getPreviousWorkExperience();

    /**
     * Get contractType.
     * 
     * @return Integer
     */
    String getContractType();

    /**
     * Get workHours.
     * 
     * @return Integer
     */
    int getWorkHours();

    /**
     * Get teamLead.
     * 
     * @return String
     */
    String getTeamLeadUCN();

    /**
     * Get paychecks.
     * 
     * @return List<Employee>
     */
    List<Paycheck> getPaychecks();

    /**
     * Get paychecks.
     * 
     * @return List<PositionHistory>
     */
    List<PositionPeriod> getPositionHistory();

    /**
     * get role of employee
     * 
     * @return Role
     */
    Role getRole();
    
    /**
     * Get base salary.
     * @return double
     */
    double getBaseSalary();
    
    /**
     * Get current position.
     * @return Position
     */
    Position getCurrentPosition();
}
