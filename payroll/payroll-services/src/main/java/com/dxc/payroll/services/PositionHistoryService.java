package com.dxc.payroll.services;

import com.dxc.payroll.services.dto.PositionHistoryForEmployeeDTO;

/**
 * Interface for Position History Service
 *
 * @author dtanev
 */
public interface PositionHistoryService {

    /**
     * This method accepts employeeUCN of type String. The method returns list
     * from PositionHistoryDTO objects.
     *
     * @return List<PositionHistoryDTO>
     */
    PositionHistoryForEmployeeDTO findPositionHistory(String employeeUCN);
}
