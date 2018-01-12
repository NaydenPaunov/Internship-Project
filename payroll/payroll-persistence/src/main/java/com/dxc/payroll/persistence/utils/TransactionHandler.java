package com.dxc.payroll.persistence.utils;

import java.util.function.Function;

import com.dxc.payroll.common.Factory;



/**
 * Transaction handler interface.
 */
public interface TransactionHandler {
    /**
     * Execute transaction.
     * @param code
     * @return R
     */
    <R> R execute(final Function<Factory, R> code);
}
