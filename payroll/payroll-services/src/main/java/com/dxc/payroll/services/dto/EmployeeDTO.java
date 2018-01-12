package com.dxc.payroll.services.dto;

/**
 * EmpolyeeDTO implementation
 *
 * @author myordanov
 *
 */
public class EmployeeDTO {
    /**
     * Name.
     */
    private final String name;

    /**
     * Employee UCN.
     */
    private final String ucn;

    /**
     * Username.
     */
    private final String username;

    /**
     * Previous work experience.
     */
    private final int previousWorkExperience;

    /**
     * Contract type.
     */
    private final String contractType;

    /**
     * Work hours.
     */
    private final int workHours;

    /**
     * Team lead ucn.
     */
    private final String teamLeadName;

    /**
     * Role name.
     */
    private final String roleName;

    /**
     * Net salary.
     */
    private final double netSalary;

    /**
     * Constructor of EmployeeDTO.
     *
     * @param name
     * @param ucn
     * @param username
     * @param previousWorkExperience
     * @param contractType
     * @param workHours
     * @param teamLeadName
     * @param roleName
     * @param netSalary
     */
    public EmployeeDTO(final String name, final String ucn, final String username,
            final int previousWorkExperience, final String contractType, final int workHours,
            final String teamLeadName, final String roleName, final double netSalary) {
        super();
        this.name = name;
        this.ucn = ucn;
        this.username = username;
        this.previousWorkExperience = previousWorkExperience;
        this.contractType = contractType;
        this.workHours = workHours;
        this.teamLeadName = teamLeadName;
        this.roleName = roleName;
        this.netSalary = netSalary;
    }

    /**
     * Name getter.
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Username getter.
     *
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get previous work experience getter.
     *
     * @return Integer
     */
    public int getPreviousWorkExperience() {
        return previousWorkExperience;
    }

    /**
     * Contract type getter.
     *
     * @return String
     */
    public String getContractType() {
        return contractType;
    }

    /**
     * Work hours getter.
     *
     * @return Integer
     */
    public int getWorkHours() {
        return workHours;
    }

    /**
     * Team lead ucn getter.
     *
     * @return Sting
     */
    public String getTeamLeadName() {
        return teamLeadName;
    }

    /**
     * Role name getter.
     *
     * @return String
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Get net salary.
     *
     * @return double
     */
    public double getNetSalary() {
        return netSalary;
    }

    /**
     * Gets the Employee UCN.
     *
     * @return String
     */
    public String getUcn() {
        return ucn;
    }

}
