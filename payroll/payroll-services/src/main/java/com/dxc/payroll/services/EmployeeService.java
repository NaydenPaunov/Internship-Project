package com.dxc.payroll.services;

import java.util.List;

import com.dxc.payroll.services.dto.EmployeeDTO;

/**
 * Employee service.
 */
public interface EmployeeService {
    /**
     * Get employee by ucn.
     *
     * @param ucn
     * @return EmployeeDto
     */
    EmployeeDTO findEmployee(String ucn);

    /**
     * Find employees by team lead ucn.
     *
     * @param ucn
     * @return List<EmployeeDTO>
     */
    List<EmployeeDTO> findEmployeesByTeamLeadUCN(String ucn);

    /**
     * Gets all employees in the database.
     * 
     * @return List<EmployeeDTO>
     */
    List<EmployeeDTO> getAllEmployees();
}
