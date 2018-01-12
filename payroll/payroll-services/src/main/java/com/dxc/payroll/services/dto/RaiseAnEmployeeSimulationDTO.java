package com.dxc.payroll.services.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * DTO need for raising an employee
 *
 */
public class RaiseAnEmployeeSimulationDTO {
    /**
     * Employee name for the RaiseAnEmployeeSimulationDTO object
     */
    private final String employeeName;

    /**
     * Employee current positionDTO for the RaiseAnEmployeeSimulationDTO object
     */
    private final PositionDTO currentPosition;
    /**
     * AllowablePositions for the RaiseAnEmployeeSimulationDTO object
     */
    private final List<PositionDTO> allowablePositions = new ArrayList<>();
    /**
     * Chosen position for the RaiseAnEmployeeSimulationDTO object
     */
    private final PositionDTO chosenPosition;

    /**
     * Constructor of RaiseAnEmployeeSimulationDTO object
     *
     * @param employeeName
     * @param cureentPosition
     * @param allowablePositions
     */
    public RaiseAnEmployeeSimulationDTO(final String employeeName,
            final PositionDTO currentPosition, final List<PositionDTO> allowablePositions) {
        this.employeeName = employeeName;
        this.currentPosition = currentPosition;
        this.allowablePositions.addAll(allowablePositions);
        this.chosenPosition = new PositionDTO();
    }

    /**
     * Constructor with chosen position of RaiseAnEmployeeSimulationDTO object
     * 
     * @param employeeName
     * @param currentPosition
     * @param allowablePositions
     * @param chosenPosition
     */
    public RaiseAnEmployeeSimulationDTO(final String employeeName,
            final PositionDTO currentPosition, final List<PositionDTO> allowablePositions,
            final PositionDTO chosenPosition) {
        this.employeeName = employeeName;
        this.currentPosition = currentPosition;
        this.allowablePositions.addAll(allowablePositions);
        this.chosenPosition = chosenPosition;
    }

    /**
     * Get employee name
     * 
     * @return String
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * Get employee current position
     * 
     * @return PositionDTO
     */
    public PositionDTO getCurrentPosition() {
        return currentPosition;
    }

    /**
     * Get all Allowable Positions
     * 
     * @return List<PositionDTO>
     */
    public List<PositionDTO> getAllowablePositions() {
        return Collections.unmodifiableList(allowablePositions);
    }

    /**
     * Get chosen position
     * 
     * @return PositionDTO
     */
    public PositionDTO getChosenPosition() {
        return chosenPosition;
    }
}
