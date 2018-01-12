package com.dxc.payroll.services.dto;

import java.util.List;

/**
 *
 */
public class ProfessionDTO {
    /**
     * job title for the job DTO object
     */
    private final String jobTitle;

    /**
     * List of JobWithSalaryDTO objects for the job DTO object
     */
    private final List<JobWithSalaryDTO> jobWithSalaryDTOs;

    /**
     * Constructor for ProfessionDTO
     *
     * @param jobTitle
     * @param jobWithSalaryDTOs
     */
    public ProfessionDTO(final String jobTitle, final List<JobWithSalaryDTO> jobWithSalaryDTOs) {
        super();
        this.jobTitle = jobTitle;
        this.jobWithSalaryDTOs = jobWithSalaryDTOs;
    }

    /**
     * @return job title
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * @return list of jobjobWithSalaryDTOs
     */
    public List<JobWithSalaryDTO> getJobWithSalaryDTOs() {
        return jobWithSalaryDTOs;
    }

}
