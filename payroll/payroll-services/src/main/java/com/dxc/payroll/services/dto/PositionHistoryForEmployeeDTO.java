package com.dxc.payroll.services.dto;

import java.util.List;

/**
 * DTO class for Position History for an employee
 */
public class PositionHistoryForEmployeeDTO {

    /**
     * Private field - employeeName of type String
     */
    private final String employeeName;

    /**
     * Private field - employeeUCN of type String
     */
    private final String employeeUCN;

    /**
     * Private field - positionHistory of type List of PositionPeriodDTOs
     */
    private final List<PositionPeriodDTO> positionHistory;

    /**
     * Constructor for PositionHistoryDTO
     *
     * @param employeeName
     * @param employeeUCN
     * @param positionHistory
     */
    public PositionHistoryForEmployeeDTO(final String employeeName, final String employeeUCN,
            final List<PositionPeriodDTO> positionHistory) {
        super();
        this.employeeName = employeeName;
        this.employeeUCN = employeeUCN;
        this.positionHistory = positionHistory;
    }

    /**
     * getter for employeeName
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * getter for employeeUCN
     */
    public String getEmployeeUCN() {
        return employeeUCN;
    }

    /**
     * getter for positionHistory
     */
    public List<PositionPeriodDTO> getPositionHistory() {
        return positionHistory;
    }

}
