package com.dxc.payroll.service.implementation;

import java.util.ArrayList;
import java.util.List;

import com.dxc.payroll.persistence.domain.Employee;
import com.dxc.payroll.persistence.domain.PositionPeriod;
import com.dxc.payroll.persistence.repositories.EmployeeRepository;
import com.dxc.payroll.services.dto.PositionHistoryForEmployeeDTO;
import com.dxc.payroll.services.dto.PositionPeriodDTO;

/**
 * Implementation for Position History Service.
 *
 * The class has one method findPositionHistory(), that receives a UCN and
 * return PositionHistoryForEmployeeDTO.
 *
 * Extends AbstractService class.
 *
 */
public final class PositionHistoryServiceLogic {

    private PositionHistoryServiceLogic() {
    }

    /**
     * Finding the position history for an employee. The method receives a
     * string UCN. Checks whether there is positions with this UCN. If exist get
     * all position history for the employee and return
     * PositionHistoryForEmployeeDTO, else return null.
     *
     * @param employeeUCN
     *            of type String, required
     *
     * @return List of Position History DTO objects.
     */
    public static PositionHistoryForEmployeeDTO findPositionHistory(final String employeeUCN,
            final EmployeeRepository employeeRepository) {
        final Employee employee = employeeRepository.findByUCN(employeeUCN);
        if (employee == null) {
            return null;
        }
        return toPositionHistoryDTO(employee);

    }

    private static PositionHistoryForEmployeeDTO toPositionHistoryDTO(final Employee employee) {
        final String employeeName = employee.getName();
        final String employeeUCN = employee.getUCN();
        final List<PositionPeriodDTO> positionHistory = convertPositionHistory(
                employee.getPositionHistory());
        return new PositionHistoryForEmployeeDTO(employeeName, employeeUCN, positionHistory);
    }

    private static List<PositionPeriodDTO> convertPositionHistory(
            final List<PositionPeriod> pHistory) {
        final List<PositionPeriodDTO> pPeriods = new ArrayList<>();
        for (final PositionPeriod pHistoryEntity : pHistory) {
            pPeriods.add(new PositionPeriodDTO(pHistoryEntity.getPosition().getJob().getJobTitle(),
                    pHistoryEntity.getPosition().getJobLevel(), pHistoryEntity.getBaseSalary(),
                    pHistoryEntity.getStartDate(), pHistoryEntity.getEndDate()));
        }
        return pPeriods;
    }

}
