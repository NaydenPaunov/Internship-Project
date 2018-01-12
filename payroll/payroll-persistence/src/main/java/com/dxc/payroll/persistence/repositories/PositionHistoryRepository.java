package com.dxc.payroll.persistence.repositories;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.dxc.payroll.persistence.domain.PositionHistory;

/**
 * Interface for Position History Repository
 *
 * @author dtanev
 */
public interface PositionHistoryRepository {
    /**
     * This method creates new position input
     *
     * @param UCN
     * @param startDate
     * @param endDate
     * @param baseSalary
     * @param jobTitle
     * @param jobLevel
     * @return Position History Object
     */
    PositionHistory createPositionInput(String UCN, Date startDate, Date endDate,
            BigDecimal baseSalary,
            String jobTitle, Integer jobLevel);

    /**
     * This method finds Position History by UCN
     *
     * @param UCN
     * @return List of Position History Object
     */
    List<PositionHistory> findPositionHistoryByUCN(String UCN);
}
