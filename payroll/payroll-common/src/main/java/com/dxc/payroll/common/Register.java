package com.dxc.payroll.common;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Register that register each service.
 *
 * @param <R>
 *            EntityManager or TransactionHandler.
 */
public class Register<R> {
    /**
     * Map that holds class type of the interface and the function that return
     * implementation of this interface.
     */
    private final Map<Class<?>, Function<R, ?>> registerMap;

    /**
     * Register Constructor.
     */
    public Register() {
        this.registerMap = new HashMap<>();
    }

    /**
     * Register services.
     *
     * @param classType
     *            must not be null
     * @param function
     *            must not be null
     */
    public <Service> void registerService(final Class<Service> classType,
            final Function<R, Service> function) {
        assert classType != null;
        assert function != null;
        registerMap.put(classType, function);
    }

    /**
     * Get function for creationClass.
     *
     * @param creationClass
     * @return Function<R,?> - function that return constructor.
     */
    public Function<R, ?> getFunction(final Class<?> creationClass) {
        assert creationClass != null;
        return registerMap.get(creationClass);
    }
}
