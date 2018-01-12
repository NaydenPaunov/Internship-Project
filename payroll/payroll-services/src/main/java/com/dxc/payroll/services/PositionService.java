package com.dxc.payroll.services;

import com.dxc.payroll.services.dto.EmployeePositionDTO;
import com.dxc.payroll.services.dto.RaiseAnEmployeeSimulationDTO;

/**
 * Interface for Position Service
 */
public interface PositionService {

    /**
     * This method accepts employee UCN of type String. Checks whether an
     * employee with such a UCN exists.Check allowable positions for current
     * employee. And return RaiseAnEmployeeSimulationDTO. If there aren't
     * allowable positions return null.If does not exist employee with current
     * UCN return null.
     * 
     * @param EmployeeUCN
     * @return RaiseAnEmployeeSimulationDTO
     */
    RaiseAnEmployeeSimulationDTO getInformationForRaiseAnEmployee(final String employeeUCN);

    /**
     * This method accepts employeeUCN of type String. Checks whether an
     * employee with such a UCN exists.If exist return PositionDTO, else return
     * null. PositionDTO containing current employee position information
     * Employee - name, job title, base salary and position level
     * 
     * @param EmployeeUCN
     * @return PositionDTO
     */
    EmployeePositionDTO getPositionByEmployeeUCN(final String employeeUCN);

    /**
     * Add end date of current position and new position of the employee
     * 
     * @param employeeUCN
     * @param jobLevel
     * @param positionDTO
     */
    void raiseEmployee(final String employeeUCN, final String currentDegreeAndLevel,
            final int jobLevel);

    /**
     * This method accepts employee UCN of type String,job degree of type String
     * and job level of type integer . Checks whether an employee with such a UCN
     * exists.Check allowable positions for current employee. And return
     * RaiseAnEmployeeSimulationDTO with chosen position. If there aren't
     * allowable positions return null.If does not exist employee with current
     * UCN return null.
     * 
     * @param employeeUCN
     * @param jobDegree
     * @param jobLevel
     * @return RaiseAnEmployeeSimulationDTO
     */
    RaiseAnEmployeeSimulationDTO simulationRaising(final String employeeUCN,
            final String jobDegree,final int jobLevel);

}
