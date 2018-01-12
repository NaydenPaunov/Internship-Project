package com.dxc.payroll.service.implementation;

import com.dxc.payroll.persistence.utils.TransactionHandler;
import com.dxc.payroll.service.validation.Validator;

/**
 * Abstract service. Common code for all services.
 */
public abstract class AbstractService {

    /**
     * transactionHandler To be used for transactions management. Not null.
     */
    protected final TransactionHandler transactionHandler;

    /**
     * Validator for parameters.
     */
    protected Validator validator;

    /**
     * Constructor of abstract service.
     *
     * @param transactionHandler
     *            To be used for transactions management. Must not be null.
     *
     */
    public AbstractService(final TransactionHandler transactionHandler) {
        assert transactionHandler != null;
        this.transactionHandler = transactionHandler;
        this.validator = new Validator();
    }

}
