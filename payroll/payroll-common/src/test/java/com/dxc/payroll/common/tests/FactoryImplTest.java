package com.dxc.payroll.common.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.junit.Before;
import org.junit.Test;

import com.dxc.payroll.common.Factory;
import com.dxc.payroll.common.FactoryImpl;
import com.dxc.payroll.common.Register;

@SuppressWarnings("nls")
public class FactoryImplTest {

    /**
     * Mock for constructorParameter of type List.
     */
    private transient List<?> constructorParameter;

    /**
     * Mock for register of type Register.
     */
    private transient Register register;

    /**
     * Private field factory of type Factory.
     */
    private transient Factory factory;

    /**
     * Creates new factory before each test.
     */
    @Before
    public void initialize() {
        constructorParameter = mock(List.class);
        register = mock(Register.class);
        factory = new FactoryImpl(constructorParameter, register);
    }

    // TODO Documentation, but OK?
    /**
     * Tests the case when findService function returns the correct instance of
     * a class.
     */
    @Test
    public void testFindService() {
        final ArrayList<?> arrayList = new ArrayList<>();
        final Function<List<?>, ArrayList> function = (final List<?> l) -> arrayList;
        when(register.getFunction(ArrayList.class)).thenReturn(function);
        final ArrayList<?> service = factory.findService(ArrayList.class);
        assertEquals("Function findService returns the correct instance of a class", arrayList,
                service);
    }

    /**
     * Tests the case when findService function returns null.
     */
    @Test
    public void testFindServiceReturnsNull() {
        final List<?> service = factory.findService(List.class);
        assertNull("Function findService returns null", service);
    }

}
