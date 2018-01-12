package com.dxc.payroll.common;

/**
 * Factory Interface.
 */
public interface Factory {
    /**
     * Method returns Service Implementation or Repository Implementation.
     *
     * @param service
     * @return <Service>
     */
    <Service> Service findService(final Class<Service> service);
}
