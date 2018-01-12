package com.dxc.payroll.common;

import java.util.function.Function;

/**
 * Service Factory.
 *
 * @param <T>
 */
public class FactoryImpl<T> implements Factory {
    /**
     * Factory parameter- TransactionHandler or EntityManager
     */
    private final T constructorParameter;

    /**
     * Register that register services.
     */
    private final Register<T> register;

    /**
     * ServiceFactory
     *
     * @param constructorParameter
     * @param register
     */
    public FactoryImpl(final T constructorParameter, final Register<T> register) {
        this.constructorParameter = constructorParameter;
        this.register = register;
    }

    /**
     * Find Component that is needed where the component is Repository or
     * service.
     *
     * @param service
     * @return <Service>
     */
    @Override
    public <Service> Service findService(final Class<Service> service) {
        assert service != null;

        final Function<T, ?> func = register.getFunction(service);
        Service result = null;

        if (func != null) {
            final Object serviceImplementation = func.apply(constructorParameter);
            assert service.isAssignableFrom(serviceImplementation.getClass());
            result = service.cast(serviceImplementation);
        }

        return result;
    }
}
