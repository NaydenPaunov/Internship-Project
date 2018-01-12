package com.dxc.payroll.service.implementation.utils.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.dxc.payroll.service.implementation.utils.SalaryFormulas;

/**
 * Test calculation of salary formulas.
 *
 */
public class SalaryFormulasTest {
    private static final double DELTA = 0.01;

    /**
     * Test calculate gross salary.
     */
    @SuppressWarnings("nls")
    @Test
    public void grossSalaryTest() {
        assertEquals("Wrong gross salary", 2040.0, SalaryFormulas.getGrossSalary(2000, 2, 1),
                DELTA);
    }

    /**
     * Test calculate tax rate for employee.
     */
    @SuppressWarnings("nls")
    @Test
    public void taxRateEmployeeTest() {
        assertEquals("Wrong tax rate employee.", 68.7,
                SalaryFormulas.getTaxRate(515, 13.34), DELTA);
    }

    /**
     * Test calculate tax rate for company.
     */
    @SuppressWarnings("nls")
    @Test
    public void taxRateCompanyTest() {
        assertEquals("Wrong tax rate comapny.", 94.55,
                SalaryFormulas.getTaxRate(515, 18.36), DELTA);
    }

    /**
     * Test calculate net salary.
     */
    @SuppressWarnings("nls")
    @Test
    public void netSalary() {
        assertEquals("Wrong net salary.", 401.67, SalaryFormulas.getNetSalary(446.30, 10), DELTA);
    }

}
