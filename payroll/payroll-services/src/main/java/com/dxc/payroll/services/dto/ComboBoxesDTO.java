package com.dxc.payroll.services.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Holds information, needed to fill the comboBoxes in the JobViewServlet.
 */
public class ComboBoxesDTO {
    /**
     * List of JobTitleAndDegreesDTOs.
     */
    private final List<JobTitleAndDegreesDTO> dtos;

    /**
     * Constructor for the ComboBoxesDTO.
     *
     * @param dtos
     */
    public ComboBoxesDTO(final List<JobTitleAndDegreesDTO> dtos) {
        this.dtos = Collections.unmodifiableList(dtos);
    }

    /**
     * Gets all job titles. If there are no jobs returns an empty list.
     *
     * @return List of all job titles
     */
    public List<String> getAllJobTitles() {
        if (dtos.isEmpty()) {
            return new ArrayList<>();
        }
        return dtos.stream().map(JobTitleAndDegreesDTO::getJobTitle).collect(Collectors.toList());
    }

    /**
     * @return List of JobTitleAndDegreesDTOs.
     */
    public List<JobTitleAndDegreesDTO> getDtos() {
        return Collections.unmodifiableList(dtos);
    }

}
