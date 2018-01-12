package com.dxc.payroll.services;

import java.util.List;

import com.dxc.payroll.services.dto.EmployeeDTO;

/**
 * Team lead employee service.
 * @author myordanov
 *
 */
public interface SubordinateService {
    /**
     * Find employees by teamLeadUCN.
     * @param ucn
     * @return List<EmployeeDTO>
     */
    List<EmployeeDTO> findEmployeesByTeamLeadUCN(String ucn);
}
