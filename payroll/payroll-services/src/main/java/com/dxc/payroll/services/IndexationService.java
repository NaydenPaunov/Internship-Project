package com.dxc.payroll.services;

import java.util.List;

import com.dxc.payroll.services.dto.IndexationDTO;

/**
 * Indexation service interface
 */
public interface IndexationService {

    /**
     * Method that gets all indexations in the database for the given job title.
     *
     * @param jobTitle
     *
     * @return List of all indexations and information for them (percentage,
     *         date, job title and degree) by the job title
     */
    List<IndexationDTO> getIndexationsBy(final String jobTitle);

    /**
     * Method that gets all indexations in the database.
     *
     * @return List of all indexations and information for them (percentage,
     *         date, job title and degree)
     */
    List<IndexationDTO> getAllIndexations();

}
