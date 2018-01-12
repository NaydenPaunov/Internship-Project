package com.dxc.payroll.persistence.jpa.repositories;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.dxc.payroll.persistence.domain.PositionHistory;
import com.dxc.payroll.persistence.jpa.domain.JpaPositionHistory;
import com.dxc.payroll.persistence.repositories.PositionHistoryRepository;

@SuppressWarnings("nls")
public class JpaPositionHistoryRepository implements PositionHistoryRepository {

    private static final String QRY_FIND_POSITION_HISTORY_BY_UCN = "qryFindPositionHistoryByUCN";
    private static final String EMPLOYEE_UCN = "UCN";

    private final EntityManager entityManager;

    public JpaPositionHistoryRepository(final EntityManager entityManager) {
        assert entityManager != null;
        this.entityManager = entityManager;
    }

    @Override
    public PositionHistory createPositionInput(final String UCN, final Date startDate,
            final Date endDate, final BigDecimal baseSalary,
            final String jobTitle, final Integer jobLevel) {
        final PositionHistory positionHistory = new JpaPositionHistory(UCN, startDate, endDate,
                baseSalary, jobTitle, jobLevel);
        entityManager.persist(positionHistory);
        return positionHistory;
    }

    @Override
    public List<PositionHistory> findPositionHistoryByUCN(final String UCN) {
        return entityManager
                .createNamedQuery(QRY_FIND_POSITION_HISTORY_BY_UCN, PositionHistory.class)
                .setParameter(EMPLOYEE_UCN, UCN).getResultList();
    }

}
