package com.dxc.payroll.services.dto;

import java.math.BigDecimal;

public class PositionHistoryDTO {
    private final String employeeName;
    private final String employeeUCN;
    private final String jobTitle;
    private final Integer jobLevel;
    private final BigDecimal baseSalary;
    private final String startDate;
    private final String endDate;

    public PositionHistoryDTO(final String employeeName, final String employeeUCN, final String jobTitle,
            final Integer jobLevel, final BigDecimal baseSalary, final String startDate, final String endDate) {
        super();
        this.employeeName = employeeName;
        this.employeeUCN = employeeUCN;
        this.jobTitle = jobTitle;
        this.jobLevel = jobLevel;
        this.baseSalary = baseSalary;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getEmployeeName() {
        return this.employeeName;
    }

    public String getEmployeeUCN() {
        return this.employeeUCN;
    }

    public String getJobTitle() {
        return this.jobTitle;
    }

    public Integer getJobLevel() {
        return this.jobLevel;
    }

    public BigDecimal getBaseSalary() {
        return this.baseSalary;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }
}
