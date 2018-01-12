package com.dxc.payroll.persistence.jpa.repositories;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.dxc.payroll.persistence.jpa.utils.Utils;
import com.dxc.payroll.persistence.domain.Tax;
import com.dxc.payroll.persistence.jpa.domain.JpaTax;
import com.dxc.payroll.persistence.jpa.domain.TaxID;
import com.dxc.payroll.persistence.repositories.TaxRepository;

/**
 * Implements TaxRepository
 *
 */
public class JpaTaxRepository implements TaxRepository {
    /**
     * private field - the entity manager
     */
    private final EntityManager entityManager;

    /**
     * @param entityManager
     */
    public JpaTaxRepository(final EntityManager entityManager) {
        assert entityManager != null;
        this.entityManager = entityManager;
    }

    /**
     * see com.dxc.payroll.persistence.repositories.TaxRepository;
     */
    @Override
    public Tax createTax(final String tName, final LocalDate sDate, final LocalDate eDate,
            final double percE, final double percC) {
        assert tName != null && sDate != null;
        final Tax tax = new JpaTax(tName, sDate, eDate, percE, percC);
        entityManager.persist(tax);
        return tax;
    }

    /**
     * see com.dxc.payroll.persistence.repositories.TaxRepository;
     */
    @Override
    public Tax findTaxByNameAndDate(final LocalDate sDate, final String typeOfTax) {
        assert typeOfTax != null && sDate != null;
        return entityManager.find(JpaTax.class, new TaxID(typeOfTax, sDate));
    }

    /**
     * see com.dxc.payroll.persistence.repositories.TaxRepository;
     */
    @SuppressWarnings("nls")
    @Override
    public List<Tax> findUnclosedTaxesWithGivenName(final String typeOfTax) {
        assert typeOfTax != null;
        final TypedQuery<Tax> query = entityManager.createNamedQuery("qryFindLastTaxByName",
                Tax.class);
        query.setParameter("typeOfTax", Utils.padRight(typeOfTax, TaxID.SIZE_TAX_NAME));
        return query.getResultList();
    }

    /**
     * see com.dxc.payroll.persistence.repositories.TaxRepository;
     */
    @SuppressWarnings("nls")
    @Override
    public List<Tax> getAllCurrentTaxes() {
        return entityManager.createNamedQuery("qryGetAllCurrentTaxes", Tax.class).getResultList();
    }

    /**
     * see com.dxc.payroll.persistence.repositories.TaxRepository;
     */
    @Override
    public boolean closeTax(final Tax taxToClose, final LocalDate endDate) {
        return taxToClose.closeTax(endDate);

    }

    /**
     * see com.dxc.payroll.persistence.repositories.TaxRepository;
     */
    @SuppressWarnings("nls")
    @Override
    public List<Tax> getTaxHistory(final String typeOfTax) {
        return entityManager.createNamedQuery("qryGetTaxHistory", Tax.class)
                .setParameter("typeOfTax", Utils.padRight(typeOfTax, TaxID.SIZE_TAX_NAME))
                .getResultList();
    }

}
