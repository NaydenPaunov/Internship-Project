package com.dxc.payroll.persistence.repositories;

import java.time.LocalDate;
import java.util.List;

import com.dxc.payroll.persistence.domain.Indexation;
import com.dxc.payroll.persistence.domain.Position;

/**
 * Repository for the Indexation
 */
public interface IndexationRepository {

    /**
     * Method that creates new Indexation
     *
     * @param percentage
     * @param dateOfIndexation
     * @param position
     * @return Indexation object
     */
    Indexation createIndexation(Position position, double percentage, LocalDate dateOfIndexation);

    /**
     * Method that finds indexation by the given job title.
     *
     * @param jobTitle
     * @return list of indexations
     */
    List<Indexation> findIndexationBy(String jobTitle);

    /**
     * Method that gets all the indexations in the database.
     *
     * @return list of indexations
     */
    List<Indexation> getIndexations();

}
