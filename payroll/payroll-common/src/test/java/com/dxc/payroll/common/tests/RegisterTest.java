package com.dxc.payroll.common.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.function.Function;

import org.junit.Before;
import org.junit.Test;

import com.dxc.payroll.common.Register;

public class RegisterTest {

    /**
     * Private field register of type Register.
     */
    private transient Register register;

    /**
     * Private field function of type Function<String, String>.
     */
    private transient Function<String, String> function;

    /**
     * Creates new factory before each test.
     */
    @Before
    public void initialize() {
        register = new Register();
        function = (final String s) -> s;
    }

    /**
     * Tests the case when getFunction function returns the correct function.
     */
    @Test
    public void testGetFunction() {
        register.registerService(String.class, function);
        final Function<String, String> actualFunction = register.getFunction(String.class);
        assertEquals("Function getFunction returns added function", function, actualFunction);
    }

    /**
     * Tests the case when getFunction function returns null.
     */
    @Test
    public void testGetFunctionReturnsNull() {
        final Function<String, String> actualFunction = register.getFunction(String.class);
        assertNull("Function getFunction returns null", actualFunction);
    }

}
