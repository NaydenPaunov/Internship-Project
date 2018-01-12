package com.dxc.payroll.persistence.jpa.utils;

import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.dxc.payroll.common.Factory;
import com.dxc.payroll.common.FactoryImpl;
import com.dxc.payroll.common.Register;

import com.dxc.payroll.persistence.utils.TransactionHandler;

/**
 * Implementation of transaction handler.
 *
 */
public class TransactionHandlerImpl implements TransactionHandler {
    /**
     * Entity manager factory.
     */
    private final EntityManagerFactory entityManagerFactory;

    /**
     * Register.
     */
    private final Register<EntityManager> register;

    /**
     *
     * @param entityManagerFactory
     * @param repositoryRegister
     */
    public TransactionHandlerImpl(final EntityManagerFactory entityManagerFactory,
            final Register<EntityManager> repositoryRegister) {
        this.entityManagerFactory = entityManagerFactory;
        this.register = repositoryRegister;
    }

    /**
     * This execute our code in transaction.If transaction fails , the method
     * throws appropriate exception and rollback transaction.
     *
     * @param code
     *            where RepositoryFactory is parameter of our function. R -
     *            Return type of the function.
     *
     * @return R - return type of the function.
     */
    @Override
    public <R> R execute(final Function<Factory, R> code) {
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return executeTransaction(code, entityManager);
        }
        finally {
            entityManager.close();
        }
    }

    /**
     * Private method that execute transaction.
     *
     * @param code
     * @param entityManager
     * @return R - return type of our function.
     */
    private <R> R executeTransaction(final Function<Factory, R> code,
            final EntityManager entityManager) {
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            final Factory factory = new FactoryImpl<>(entityManager,
                    register);
            final R result = code.apply(factory);
            transaction.commit();

            return result;
        }
        finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }
}
