package com.dxc.payroll.service.implementation;

import java.util.List;

import com.dxc.payroll.persistence.domain.Employee;
import com.dxc.payroll.persistence.domain.Tax;
import com.dxc.payroll.persistence.repositories.EmployeeRepository;
import com.dxc.payroll.persistence.repositories.TaxRepository;
import com.dxc.payroll.service.implementation.utils.SalaryFormulas;

/**
 * This class is used to get the base salary, gross salary, tax rate salaries
 * and net salary.
 *
 */
public final class SalaryCalculator {

    /**
     * Work_Experience.
     */
    @SuppressWarnings("nls")
    private static final String WORK_EXPERIENCE = "Work_Experience";

    /**
     * General common income.
     */
    @SuppressWarnings("nls")
    private static final String DOD = "DOD";

    /**
     * Calculate salary service implementation.
     *
     * @param transactionHandler
     */
    private SalaryCalculator() {
    }

    /**
     * Get base salary.If employee does not exist the method throws
     * EmployeeNotFoundException with approriate message.
     *
     * @param ucn
     * @param employeeRepository
     * @return double
     */
    public static double getBaseSalary(final String ucn,
            final EmployeeRepository employeeRepository) {
        final Employee employee = getEmployee(ucn, employeeRepository);
        return employee.getBaseSalary();
    }

    /**
     * Get gross salary.
     *
     * @param ucn
     * @param employeeRepository
     * @param taxRepository
     * @return double
     */
    public static double calculateGrossSalary(final String ucn,
            final EmployeeRepository employeeRepository, final TaxRepository taxRepository) {
        return findGrossSalary(ucn, employeeRepository, taxRepository);
    }

    /**
     * Get tax rate for employee.
     *
     * @param ucn
     * @param employeeRepository
     * @param taxRepository
     * @return double
     */
    public static double calculateTaxRateEmployee(final String ucn,
            final EmployeeRepository employeeRepository, final TaxRepository taxRepository) {
        return findTaxRateEmployee(ucn, employeeRepository, taxRepository);

    }

    /**
     * Get tax rate for employee.
     *
     * @param ucn
     * @param employeeRepository
     * @param taxRepository
     * @return double
     */
    public static double calculateTaxRateCompany(final String ucn,
            final EmployeeRepository employeeRepository, final TaxRepository taxRepository) {
        return findTaxRateCompany(ucn, employeeRepository, taxRepository);
    }

    /**
     * Get net salary.
     *
     * @param ucn
     * @param employeeRepository
     * @param taxRepository
     * @return double
     */
    public static double calculateNetSalary(final String ucn,
            final EmployeeRepository employeeRepository, final TaxRepository taxRepository) {
        final double taxRateEmployee = findTaxRateEmployee(ucn, employeeRepository, taxRepository);
        final Tax dod = getTax(taxRepository, DOD);
        return SalaryFormulas.getNetSalary(taxRateEmployee, dod.getPercentageEmployee());
    }

    /**
     * @param ucn
     * @param factory
     * @return double
     */
    private static double findTaxRateEmployee(final String ucn,
            final EmployeeRepository employeeRepository, final TaxRepository taxRepository) {
        final double grossSalary = findGrossSalary(ucn, employeeRepository, taxRepository);
        final List<Tax> taxes = getCurrentActiveTaxes(taxRepository);
        return calculateTaxRateEmployeeByGivenTax(grossSalary, taxes);
    }

    /**
     * Gets all currently active taxes
     *
     * @param factory
     * @return List<Tax>
     */
    private static List<Tax> getCurrentActiveTaxes(final TaxRepository taxRepository) {
        return taxRepository.getAllCurrentTaxes();
    }

    /**
     * @param ucn
     * @param factory
     * @return double
     */
    private static double findTaxRateCompany(final String ucn,
            final EmployeeRepository employeeRepository, final TaxRepository taxRepository) {
        final double grossSalary = findGrossSalary(ucn, employeeRepository, taxRepository);
        final List<Tax> taxes = getCurrentActiveTaxes(taxRepository);
        return calculateTaxRateCompanyByGivenTax(grossSalary, taxes);
    }

    /**
     * Get employee.
     *
     * @param ucn
     * @param factory
     * @return Employee
     */
    private static Employee getEmployee(final String ucn,
            final EmployeeRepository employeeRepository) {
        return employeeRepository.findByUCN(ucn);
    }

    /**
     * Get work experience tax.
     *
     * @param factory
     * @param taxName
     * @return Tax
     */
    private static Tax getTax(final TaxRepository taxRepository, final String taxName) {
        final List<Tax> taxes = taxRepository.findUnclosedTaxesWithGivenName(taxName);
        return taxes.get(0);
    }

    /**
     * @param ucn
     * @param factory
     * @return double
     */
    private static double findGrossSalary(final String ucn,
            final EmployeeRepository employeeRepository, final TaxRepository taxRepository) {
        final Employee employee = getEmployee(ucn, employeeRepository);
        final Tax workExperienceTax = getTax(taxRepository, WORK_EXPERIENCE);
        return SalaryFormulas.getGrossSalary(employee.getBaseSalary(),
                employee.getPreviousWorkExperience(), workExperienceTax.getPercentageEmployee());
    }

    /**
     * Get tax rate employee.
     *
     * @param grossSalary
     * @param taxes
     * @return double
     */
    private static double calculateTaxRateEmployeeByGivenTax(final double grossSalary,
            final List<Tax> taxes) {
        double result = grossSalary;

        for (final Tax tax : taxes) {
            if (!tax.getTypeOfTax().equals(WORK_EXPERIENCE) && !tax.getTypeOfTax()
                    .equals(DOD)) {
                result -= SalaryFormulas.getTaxRate(grossSalary,
                        tax.getPercentageEmployee());

            }
        }

        return result;
    }

    /**
     * Get tax rate company by given taxes.
     *
     * @param grossSalary
     * @param taxes
     * @return double
     */
    private static double calculateTaxRateCompanyByGivenTax(final double grossSalary,
            final List<Tax> taxes) {
        double result = grossSalary;

        for (final Tax tax : taxes) {
            if (!tax.getTypeOfTax().equals(WORK_EXPERIENCE) && !tax.getTypeOfTax()
                    .equals(DOD)) {
                result -= SalaryFormulas.getTaxRate(grossSalary,
                        tax.getPercentageCompany());

            }
        }

        return result;
    }
}
