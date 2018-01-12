package com.dxc.payroll.services.dto;

/**
 * SalaryDTO.
 */
public class SalaryDTO {
    /**
     * Base salary.
     */
    private final double baseSalary;
    
    /**
     * Gross salary.
     */
    private final double grossSalary;
    
    /**
     * Tax rate employee.
     */
    private final double taxRateEmployee;
    
    /**
     * Tax rate company.
     */
    private final double taxRateCompany;
    
    /**
     * Net salary.
     */
    private final double netSalary;

    /**
     * Constructor salaryDTO.
     * 
     * @param baseSalary
     * @param grossSalary
     * @param taxRateEmployee
     * @param taxRateCompany
     * @param netSalary
     */
    public SalaryDTO(final double baseSalary, final double grossSalary,
            final double taxRateEmployee, final double taxRateCompany, final double netSalary) {
        this.baseSalary = baseSalary;
        this.grossSalary = grossSalary;
        this.taxRateEmployee = taxRateEmployee;
        this.taxRateCompany = taxRateCompany;
        this.netSalary = netSalary;
    }

    /**
     * Get baseSalary.
     * 
     * @return double.
     */
    public double getBaseSalary() {
        return baseSalary;
    }

    /**
     * Get gross salary.
     * 
     * @return double
     */
    public double getGrossSalary() {
        return grossSalary;
    }

    /**
     * Get tax rate for employee.
     * 
     * @return double
     */
    public double getTaxRateEmployee() {
        return taxRateEmployee;
    }

    /**
     * Get tax rate for company.
     * 
     * @return double
     */
    public double getTaxRateCompany() {
        return taxRateCompany;
    }

    /**
     * Get net salary.
     * 
     * @return double
     */
    public double getNetSalary() {
        return netSalary;
    }
}
