package com.dxc.payroll.service.implementation.utils;

/**
 * Formulas that estimate salaries and taxes.
 *
 */
public final class SalaryFormulas {
    private SalaryFormulas() {
    }

    /**
     * Convert number from database to percentage.
     *
     * @param salaryPercentage
     * @return double
     */
    private static double convertToPercentage(final double salaryPercentage) {
        return salaryPercentage / 100;
    }

    /**
     * Estimates gross salary.
     *
     * @param baseSalary
     * @param workExperience
     * @param workExpPercentage
     * @return double - gross salary
     */
    public static double getGrossSalary(final double baseSalary, final int workExperience,
            final double workExpPercentage) {
        return baseSalary + workExperience * convertToPercentage(workExpPercentage) * baseSalary;
    }

    /**
     * Estimate employee tax rate.
     *
     * @param grossSalary
     * @param taxPercentage
     * @return double - employee tax rate
     */
    public static double getTaxRate(final double grossSalary, final double taxPercentage) {
        return grossSalary * convertToPercentage(taxPercentage);
    }

    /**
     * Estimate net salary.
     *
     * @param taxRate
     * @param incomeTax
     * @return double - net salary
     */
    public static double getNetSalary(final double taxRate, final double incomeTax) {
        return taxRate - taxRate * convertToPercentage(incomeTax);
    }
}
