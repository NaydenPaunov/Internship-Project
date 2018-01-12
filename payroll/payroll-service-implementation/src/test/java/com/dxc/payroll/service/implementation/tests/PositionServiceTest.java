package com.dxc.payroll.service.implementation.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.dxc.payroll.persistence.domain.Employee;
import com.dxc.payroll.persistence.domain.Job;
import com.dxc.payroll.persistence.domain.Position;
import com.dxc.payroll.persistence.repositories.EmployeeRepository;
import com.dxc.payroll.persistence.repositories.JobRepository;
import com.dxc.payroll.service.implementation.PositionServiceLogic;
import com.dxc.payroll.services.dto.EmployeePositionDTO;
import com.dxc.payroll.services.dto.RaiseAnEmployeeSimulationDTO;

public class PositionServiceTest {
    /**
     * Sample job level
     */
    private static final int JOB_LEVEL = 3;
    /**
     * Sample precedence number
     */
    private static final int PRECEDENCE_NUMBER = 20;
    /**
     * Sample employee name
     */
    private static final String EMPLOYEE_NAME = "Ivan";
    /**
     * Sample employee UCN
     */
    private static final String EMPOYEE_UCN = "1234567890";
    /**
     * Sample wrong employee UCN
     */
    private static final String WRONG_EMPOYEE_UCN = "1234567";
    /**
     * Sample job title
     */
    private static final String JAVA_DEVELOPER = "Java Developer";
    /**
     * Sample job degree
     */
    private static final String JUNIOR = "Junior";
    /**
     * JobRepository mock
     */
    private JobRepository jobRepository;
    /**
     * EmployeeRepository mock
     */
    private EmployeeRepository employeeRepository;
    /**
     * Employee mock
     */
    private Employee employee;
    /**
     * Job mock
     */
    private Job job;
    /**
     * Position mock.
     */
    private Position position;
    /**
     * List of positions mock.
     */
    private List<Position> positions;

    @Before
    public void initialize() {
        job = mock(Job.class);
        when(job.getJobTitle()).thenReturn(JAVA_DEVELOPER);
        when(job.getJobDegree()).thenReturn(JUNIOR);
        when(job.getPrecedenceNumber()).thenReturn(20);
        when(job.getMinSalary()).thenReturn(1500.00);
        when(job.getMaxSalary()).thenReturn(2500.00);

        position = mock(Position.class);
        when(position.getJob()).thenReturn(job);
        when(position.getJobLevel()).thenReturn(3);
        when(position.getBaseSalary()).thenReturn(1800.00);

        positions = new ArrayList<>();
        positions.add(position);

        employee = mock(Employee.class);
        when(employee.getName()).thenReturn(EMPLOYEE_NAME);
        when(employee.getCurrentPosition()).thenReturn(position);
        when(employee.getUCN()).thenReturn(EMPOYEE_UCN);

        jobRepository = mock(JobRepository.class);
        employeeRepository = mock(EmployeeRepository.class);
    }

    @Test
    public void testGetPositionByEmployeeUCN() {
        final String jobTitle = JAVA_DEVELOPER;
        final String employeeUCN = EMPOYEE_UCN;
        when(employeeRepository.findByUCN(employeeUCN)).thenReturn(employee);

        final EmployeePositionDTO employeePositionDTO = PositionServiceLogic
                .getPositionByEmployeeUCN(employeeUCN, employeeRepository);

        assertEquals("employee dismatch", jobTitle, employeePositionDTO.getJobTitle());
    }

    @Test
    public void testGetPositionByEmployeeUCNNoResult() {
        final String employeeUCN = WRONG_EMPOYEE_UCN;
        when(employeeRepository.findByUCN(employeeUCN)).thenReturn(null);
        final EmployeePositionDTO employeePositionDTO = PositionServiceLogic
                .getPositionByEmployeeUCN(employeeUCN, employeeRepository);

        assertNull("The returned employeePositionDTO object must be null. "
                + "The provided UCN is not in the database.", employeePositionDTO);
    }

    @Test
    public void testGetInformationForRaiseAnEmployee() {
        final String employeeUCN = EMPOYEE_UCN;
        final String jobTitle = JAVA_DEVELOPER;
        final String jobDegree = JUNIOR;
        final int precedenceNumber = PRECEDENCE_NUMBER;
        final int jobLevel = JOB_LEVEL;
        when(employeeRepository.findByUCN(employeeUCN)).thenReturn(employee);
        when(jobRepository.findCurrentDegreeAllowablePositions(jobTitle, jobLevel, jobDegree))
                .thenReturn(positions);
        when(jobRepository.findNextDegreeAllowablePositions(jobTitle, precedenceNumber))
                .thenReturn(positions);

        final RaiseAnEmployeeSimulationDTO raiseEmployeeSimulationDTO = PositionServiceLogic
                .getInformationForRaiseAnEmployee(employeeUCN, jobRepository, employeeRepository);

        assertEquals("job titles dismatch", jobTitle,
                raiseEmployeeSimulationDTO.getAllowablePositions().get(0).getJobTitle());
    }

    @Test
    public void testGetInformationForRaiseAnEmployeeNoResult() {
        final String employeeUCN = WRONG_EMPOYEE_UCN;
        when(employeeRepository.findByUCN(employeeUCN)).thenReturn(null);
        final RaiseAnEmployeeSimulationDTO raiseEmployeeSimulationDTO = PositionServiceLogic
                .getInformationForRaiseAnEmployee(employeeUCN, jobRepository, employeeRepository);

        assertNull("The returned RaiseAnEmployeeSimulationDTO object must be null. "
                + "The provided UCN is not in the database.", raiseEmployeeSimulationDTO);
    }

    @Test
    public void testSimulationRaising() {
        final String employeeUCN = EMPOYEE_UCN;
        final String jobTitle = JAVA_DEVELOPER;
        final String jobDegree = JUNIOR;
        final int precedenceNumber = PRECEDENCE_NUMBER;
        final int jobLevel = JOB_LEVEL;

        when(employeeRepository.findByUCN(employeeUCN)).thenReturn(employee);
        when(jobRepository.findCurrentDegreeAllowablePositions(jobTitle, jobLevel, jobDegree))
                .thenReturn(positions);
        when(jobRepository.findNextDegreeAllowablePositions(jobTitle, precedenceNumber))
                .thenReturn(positions);

        final RaiseAnEmployeeSimulationDTO simulationRaising = PositionServiceLogic
                .simulationRaising(employeeUCN, jobDegree, jobLevel, employeeRepository,
                        jobRepository);

        assertEquals("job titles dismatch", jobTitle,
                simulationRaising.getAllowablePositions().get(0).getJobTitle());
    }
}
