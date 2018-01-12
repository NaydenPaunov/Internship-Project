package com.dxc.payroll.services;

import java.util.List;

import com.dxc.payroll.service.exceptions.EmptyValueException;
import com.dxc.payroll.services.dto.ComboBoxesDTO;
import com.dxc.payroll.services.dto.JobDTO;
import com.dxc.payroll.services.dto.JobWithSalaryDTO;
import com.dxc.payroll.services.dto.LevelAndSalaryDTO;
import com.dxc.payroll.services.dto.PositionDTO;
import com.dxc.payroll.services.dto.ProfessionAddDTO;
import com.dxc.payroll.services.dto.ProfessionDTO;

/**
 * Interface for a service that gets information for the jobs and transfers it
 * from the persistence to the web layer.
 */
public interface JobService {
    /**
     * Gets all jobs with the given job title.
     *
     * If there are none, an empty list is returned.
     *
     * @param jobTitle
     * @return list of JobDTO objects
     * @see JobDTO
     */
    List<JobDTO> findByJobTitle(String jobTitle);

    /**
     * Finds the job by it's primary key.
     *
     * Returns null if there is no job with the given job title and job degree.
     *
     * @param jobTitle
     *            - must not be empty or null
     * @param jobDegree
     *            - must not be empty or null
     * @return JobDTO object
     * @see JobDTO
     */
    JobDTO findBy(String jobTitle, String jobDegree);

    /**
     * Gets all job titles which are in the database.
     *
     * If there are none, an empty list is returned.
     *
     * @return list of all job titles
     */
    List<String> getAllJobTitles();

    /**
     * Gets all job degrees for the given jobTitle.
     *
     * If there are none, an empty list is returned.
     *
     * @param jobTitle
     *            - must not be empty or null
     * @return list of job degrees
     */
    List<String> getJobDegrees(String jobTitle);

    /**
     * Finds all jobs in the database.
     *
     * If there are none, an empty list is returned.
     *
     * @return list of JobDTO objects
     * @see JobDTO
     */
    List<JobDTO> findJobs();

    /**
     * Gets a list of all positions with the provided job title and job degree.
     *
     * If there are none, an empty list is returned.
     *
     * @param jobTitle
     *            - must not be empty or null
     * @param jobDegree
     *            - must not be empty or null
     * @return list of PositionDTO objects
     * @see PositionDTO
     */
    List<PositionDTO> findPositionsBy(String jobTitle, String jobDegree);

    /**
     * Gets all job levels for the job with the given jobTitle and jobDegree.
     *
     * Returns an empty list if there are none.
     *
     * @param jobTitle
     *            - must not be empty or null
     * @param jobDegree
     *            - must not be empty or null
     * @return all job levels for the given job
     */
    List<Integer> getJobLevelsBy(String jobTitle, String jobDegree);

    /**
     * Gets a list of LevelAndSalaryDTO objects, containing information for all
     * levels and corresponding base salaries which are linked to the given job.
     *
     * @param jobTitle
     *            - must not be empty or null
     * @param jobDegree
     *            - must not be empty or null
     * @return list of LevelAndSalaryDTO objects
     */
    List<LevelAndSalaryDTO> getLevelAndSalaryBy(String jobTitle, String jobDegree);

    /**
     * Gets a list of JobWithSalaryDTO objects corresponding to the given job
     * title.
     *
     * Returns an empty list if there are no jobs in the database.
     *
     * @param jobTitle
     *            - must not be empty or null
     * @return JobWithSalaryDTO objects
     * @see JobWithSalaryDTO
     */
    List<JobWithSalaryDTO> getJobsForProfession(String jobTitle);

    /**
     * Gets job and position information based on the given job title and job
     * degree.
     *
     * Returns an empty list if there are no jobs in the database.
     *
     * @param jobTitle
     *            - must not be empty or null
     * @param jobDegree
     * @return JobWithSalaryDTO objects
     */
    List<JobWithSalaryDTO> getJobInfo(String jobTitle, String jobDegree);

    /**
     * Add Position to database.
     *
     * @param jobTitle
     *            - must not be empty or null.
     * @param jobDegree
     *            - must not be empty or null.
     * @param jobLevel
     *            - must be positive number.
     * @param baseSalary
     *            - must be positive number.
     * @return PositionDTO object
     * @throws EmptyValueException
     */
    PositionDTO addPosition(String jobTitle, String jobDegree, int jobLevel, double baseSalary);

    /**
     * Finds a job by jobTitle and all positions for this job.
     *
     * @param jobTitle
     *            - must not be empty or null
     * @return ProffesionDTO object
     */
    ProfessionDTO findJobAndPositionsByJobTitle(String jobTitle);

    /**
     * Finds a job by jobTitle and all positions for this job.
     *
     * @param jobTitle
     *            - must not be empty or null
     * @return ProffesionAddDTO object
     */
    ProfessionAddDTO findJobAndPositionsByJobTitleForAddProffesion(String jobTitle);

    /**
     * Returns ComboBoxesDTO object, containing information for all job titles
     * and the corresponding job degrees.
     *
     * @return ComboBoxesDTO object
     */
    ComboBoxesDTO loadComboBoxes();

}
