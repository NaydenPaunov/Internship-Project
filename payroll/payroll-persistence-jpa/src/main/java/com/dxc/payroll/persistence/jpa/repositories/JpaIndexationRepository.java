package com.dxc.payroll.persistence.jpa.repositories;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import com.dxc.payroll.persistence.domain.Indexation;
import com.dxc.payroll.persistence.domain.Position;
import com.dxc.payroll.persistence.jpa.constants.PayrollPersistenceConstants;
import com.dxc.payroll.persistence.jpa.domain.JpaIndexation;
import com.dxc.payroll.persistence.jpa.utils.Utils;
import com.dxc.payroll.persistence.repositories.IndexationRepository;

/**
 * Implementation of IndexationRepository interface.
 */
public class JpaIndexationRepository implements IndexationRepository {

    /**
     * private field of type EntityManager
     *
     * Needed for the indexation repository.
     */
    private final EntityManager entityManager;

    /**
     * Constructor of the JpaIndexationRepository
     *
     * @param entityManager
     *            must not be null
     */
    public JpaIndexationRepository(final EntityManager entityManager) {
        assert entityManager != null;
        this.entityManager = entityManager;
    }

    /**
     * @see #createIndexation(com.dxc.payroll.persistence.domain.Position,
     *      double, LocalDate)
     */
    @Override
    public Indexation createIndexation(final Position position, final double percentage,
            final LocalDate dateOfIndexation) {

        final Indexation indexation = new JpaIndexation(position, percentage, dateOfIndexation);
        entityManager.persist(indexation);
        return indexation;
    }

    /**
     * @see #findIndexationBy(java.lang.String)
     */
    @SuppressWarnings("nls")
    @Override
    public List<Indexation> findIndexationBy(final String jobTitle) {
        return entityManager.createNamedQuery("qryShowIndexationByJobTitle", Indexation.class)
                .setParameter("jobTitle",
                        Utils.padRight(jobTitle, PayrollPersistenceConstants.JOB_TITLE_LENGTH))
                .getResultList();
    }

    /**
     * @see #getIndexations()
     */
    @SuppressWarnings("nls")
    @Override
    public List<Indexation> getIndexations() {
        return entityManager.createNamedQuery("qryGetAllIndexations", Indexation.class)
                .getResultList();
    }

}
