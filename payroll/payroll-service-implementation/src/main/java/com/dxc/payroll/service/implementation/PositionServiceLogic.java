package com.dxc.payroll.service.implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.dxc.payroll.persistence.domain.Employee;
import com.dxc.payroll.persistence.domain.Position;
import com.dxc.payroll.persistence.domain.PositionPeriod;
import com.dxc.payroll.persistence.repositories.EmployeeRepository;
import com.dxc.payroll.persistence.repositories.JobRepository;
import com.dxc.payroll.persistence.repositories.PositionPeriodRepository;
import com.dxc.payroll.service.exceptions.EmptyValueException;
import com.dxc.payroll.services.dto.EmployeePositionDTO;
import com.dxc.payroll.services.dto.PositionDTO;
import com.dxc.payroll.services.dto.RaiseAnEmployeeSimulationDTO;

/**
 * 
 * Logic of PositionServiceImpl. Needed for logic tests. The class
 * PositionServiceImpl extends the abstract class - Abstract service.
 * 
 */
public final class PositionServiceLogic {
    /**
     * Constant for global logger.
     * 
     */
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * private constructor for PositionServiceLogic
     * 
     */
    private PositionServiceLogic() {
    }

    /**
     * Finding the current position for an employee. The method receives a
     * string UCN. Checks whether an employee with such a UCN exists. If do not
     * exists return null. If exist return EmployeePositionDTO with a current
     * information for the user
     * 
     *
     * @return RaiseAnEmployeeSimulationDTO
     *
     */
    public static EmployeePositionDTO getPositionByEmployeeUCN(final String employeeUCN,
            final EmployeeRepository employeeRepository) {
        final Employee employee = findEmployeeByUCN(employeeUCN, employeeRepository);
        if (employee == null) {
            return null;
        }
        return positionToEmployeePositionDTO(employee);
    }

    /**
     * Finding the current position for an employee. The method receives a
     * string UCN. Checks whether an employee with such a UCN exists. If exist
     * get employee current position and get all allowable positions for the
     * employee Return RaiseAnEmployeeSimulationDTO witch content current
     * positions all allowable positions and employee name
     *
     * @return RaiseAnEmployeeSimulationDTO
     *
     */
    public static RaiseAnEmployeeSimulationDTO getInformationForRaiseAnEmployee(
            final String employeeUCN, final JobRepository jobRepository,
            final EmployeeRepository employeeRepository) {

        final Employee employee = findEmployeeByUCN(employeeUCN, employeeRepository);
        if (employee == null) {
            return null;
        }
        final Position currentPosition = employee.getCurrentPosition();

        final List<PositionDTO> allowablePositionsDTO = getAllAllowablePositions(jobRepository,
                currentPosition);

        final PositionDTO currentPositionDTO = positionToPositionDTO(currentPosition);
        return new RaiseAnEmployeeSimulationDTO(employee.getName(), currentPositionDTO,
                allowablePositionsDTO);
    }

    /**
     * 
     * This method accepts employee UCN of type String,job degree of type String
     * and job level of type integer . Checks whether an employee with such a
     * UCN exists.Check allowable positions for current employee. And return
     * RaiseAnEmployeeSimulationDTO with chosen position. If there aren't
     * allowable positions return null.If does not exist employee with current
     * UCN return null.
     * 
     * @param employeeUCN
     * @param jobDegree
     * @param jobLevel
     * @return RaiseAnEmployeeSimulationDTO
     * 
     */
    public static RaiseAnEmployeeSimulationDTO simulationRaising(final String employeeUCN,
            final String jobDegree, final int jobLevel, final EmployeeRepository employeeRepository,
            final JobRepository jobRepository) {
        final Employee employee = findEmployeeByUCN(employeeUCN, employeeRepository);
        if (employee == null) {
            return null;
        }
        final Position currentPosition = employee.getCurrentPosition();

        final List<PositionDTO> allowablePositionsDTO = getAllAllowablePositions(jobRepository,
                currentPosition);

        final PositionDTO chosenPosition = getChosenPosition(allowablePositionsDTO, currentPosition,
                jobLevel, jobDegree);

        final PositionDTO currentPositionDTO = positionToPositionDTO(currentPosition);
        return new RaiseAnEmployeeSimulationDTO(employee.getName(), currentPositionDTO,
                allowablePositionsDTO, chosenPosition);
    }

    /**
     * Add end date of current position and add new position of the employee
     * 
     * @param employeeUCN
     * @param jobLevel
     * @param positionDTO
     * 
     */
    public static PositionPeriod raiseEmployee(final String employeeUCN, final String jobDegree,
            final int jobLevel, final EmployeeRepository employeeRepository,
            final JobRepository jobRepository,
            final PositionPeriodRepository positionPeriodRepository) {
        final Employee employee = findEmployeeByUCN(employeeUCN, employeeRepository);
        if (employee == null) {
            checkNull("employee does not exist");
        }
        final List<PositionDTO> allowablePositionsDTO = getAllAllowablePositions(jobRepository,
                employee.getCurrentPosition());
        final PositionDTO chosenPosition = getChosenPosition(allowablePositionsDTO,
                employee.getCurrentPosition(), jobLevel, jobDegree);
        if (chosenPosition == null) {
            checkNull("chosen position does not exist");
        }
        employee.getPositionHistory().get(0).setEndDate(LocalDate.now());
        final Position position = findPositionBy(chosenPosition, jobRepository);
        if (position == null) {
            checkNull("chosen position does not exist");
        }
        return positionPeriodRepository.createPositionInput(LocalDate.now(),
                position.getBaseSalary(), position, employee);
    }

    /**
     * Filter all allowablePosition and return chosen position PositionDTO if
     * chosen position is not found in list of allowable positions return null
     * 
     * 
     * @param allowablePositionsDTO
     * @param currentPosition
     * @param jobLevel
     * @param jobDegree
     * @return PositionDTO
     * 
     */
    private static PositionDTO getChosenPosition(final List<PositionDTO> allowablePositionsDTO,
            final Position currentPosition, final int jobLevel, final String jobDegree) {
        final String jobTitle = currentPosition.getJob().getJobTitle();
        return allowablePositionsDTO
                .stream().filter(c -> c.getJobDegree().equals(jobDegree)
                        && c.getJobTitle().equals(jobTitle) && c.getJobLevel() == jobLevel)
                .findFirst().orElse(null);
    }

    /**
     * Transform Position to Position DTO
     * 
     * @param position
     * @return
     */
    private static PositionDTO positionToPositionDTO(final Position position) {
        return new PositionDTO(position.getJob().getJobTitle(), position.getJob().getJobDegree(),
                position.getJobLevel(), position.getBaseSalary());
    }

    /**
     * Find employee by UCN
     * 
     * @param employeeUCN
     * @param employeeRepository
     * @return
     */
    private static Employee findEmployeeByUCN(final String employeeUCN,
            final EmployeeRepository employeeRepository) {
        final Employee employee = employeeRepository.findByUCN(employeeUCN);
        if (employee == null) {
            return null;
        }
        return employee;
    }

    /**
     * Get all allowable position by given current position
     * 
     * @param jobRepository
     * @param currentPosition
     * @return List<PositionDTO>
     */
    private static List<PositionDTO> getAllAllowablePositions(final JobRepository jobRepository,
            final Position currentPosition) {
        final String currentJobTitle = currentPosition.getJob().getJobTitle();
        final String currentJobDegree = currentPosition.getJob().getJobDegree();
        final int currentJobLevel = currentPosition.getJobLevel();
        final int currentPrecedenceNumber = currentPosition.getJob().getPrecedenceNumber();

        final List<Position> allowablePositions = jobRepository.findCurrentDegreeAllowablePositions(
                currentJobTitle, currentJobLevel, currentJobDegree);
        allowablePositions.addAll(jobRepository.findNextDegreeAllowablePositions(currentJobTitle,
                currentPrecedenceNumber));

        return positionsToPositionDTO(allowablePositions);
    }

    /**
     * Find Position by given PositionDTO
     * 
     * @param positionDTO
     * @param jobRepository
     * @return Position
     */
    private static Position findPositionBy(final PositionDTO positionDTO,
            final JobRepository jobRepository) {
        final Position position = jobRepository.findPositionBy(positionDTO.getJobTitle(),
                positionDTO.getJobDegree(), positionDTO.getJobLevel());
        if (position == null) {
            return null;
        }
        return position;
    }

    /**
     * Transform List of Position to List of PositionDTO
     * 
     * @param allPositions
     * @return List<PositionDTO>
     */
    private static List<PositionDTO> positionsToPositionDTO(final List<Position> allPositions) {
        final List<PositionDTO> positionsList = new ArrayList<>();
        for (final Position currentPosition : allPositions) {
            positionsList.add(positionToPositionDTO(currentPosition));
        }
        return positionsList;
    }

    /**
     * Transform employee to EmployeePositionDTO
     * 
     * @param employee
     * @return EmployeePositionDTO
     */
    private static EmployeePositionDTO positionToEmployeePositionDTO(final Employee employee) {
        final Position currentPosition = employee.getCurrentPosition();
        return new EmployeePositionDTO(employee.getName(), currentPosition.getJob().getJobTitle(),
                currentPosition.getJob().getJobDegree(), currentPosition.getJobLevel(),
                currentPosition.getBaseSalary());
    }

    @SuppressWarnings("nls")
    private static void checkNull(final String exceptionMessage) {
        LOGGER.log(Level.WARNING, exceptionMessage);
        throw new EmptyValueException(exceptionMessage);
    }
}
