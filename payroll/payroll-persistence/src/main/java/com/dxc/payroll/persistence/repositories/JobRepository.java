package com.dxc.payroll.persistence.repositories;

import java.util.List;

import com.dxc.payroll.persistence.domain.Job;
import com.dxc.payroll.persistence.domain.Position;

/**
 * Interface which provides a way to manage the operations related to jobs in
 * the database.
 */
public interface JobRepository {

    /**
     * Creates a job with the provided parameters.
     *
     * @param jobTitle
     * @param jobDegree
     * @param precedenceNumber
     * @param minSalary
     * @param maxSalary
     * @return the created job
     */
    Job createJob(String jobTitle, String jobDegree, int precedenceNumber, double minSalary,
            double maxSalary);

    /**
     * Gets all jobs with the given job title.
     *
     * If there are none, an empty list is returned.
     *
     * @param jobTitle
     * @return list of jobs
     */
    List<Job> findByJobTitle(String jobTitle);

    /**
     * Finds the job by it's primary key.
     *
     * Returns null if there is no job with the given job title and job degree.
     *
     * @param jobTitle
     * @param jobDegree
     * @return the found job
     */
    Job findBy(String jobTitle, String jobDegree);

    /**
     * Adds position linked to the given job.
     *
     * @param job
     * @param jobLevel
     * @param baseSalary
     * @return created position
     */
    Position addPosition(Job job, int jobLevel, double baseSalary);

    /**
     * Finds position by primary key.
     *
     * Returns null if there is no position with the given job title, job degree
     * and job level.
     *
     * @param jobTitle
     * @param jobDegree
     * @param jobLevel
     * @return the found position
     */
    Position findPositionBy(String jobTitle, String jobDegree, int jobLevel);

    /**
     * Gets all jobTitles which are in the database.
     *
     * Returns an empty list if there are no jobs in the database.
     *
     * @return list of all job titles
     */
    List<String> listJobTitles();

    /**
     * Gets all jobDegrees for the given jobTitle from the database.
     *
     * Returns an empty list if there is no job with the given jobTitle in the
     * database.
     *
     * @param jobTitle
     * @return list of all job degrees
     */
    List<String> getJobDegrees(String jobTitle);

    /**
     * Gets all jobs from database.
     *
     * Returns an empty list if there are no jobs in the database.
     *
     * @return list of jobs.
     */
    List<Job> getAllJobs();

    /**
     * Gets all positions for the job with the given jobTitle and jobDegree.
     *
     * Returns an empty list if there are none.
     *
     * @param jobTitle
     * @param jobDegree
     * @return list of positions
     */
    List<Position> findPositionsBy(String jobTitle, String jobDegree);

    /**
     * Gets all job levels for the job with the given jobTitle and jobDegree.
     *
     * Returns an empty list if there are none.
     *
     * @param jobTitle
     * @param jobDegree
     * @return list of job levels
     */
    List<Integer> getJobLevelsBy(String jobTitle, String jobDegree);

    /**
     * Gets next precedence number
     *
     * @param precedenceNumber
     * @return Integer
     */
    Integer getNextPrecedensNumber(int precedenceNumber);

    /**
     * find all allowable positions for current job Degree
     *
     * @param jobTitle
     * @param jobLevel
     * @param jobDegree
     * @return List<Position>
     */
    List<Position> findCurrentDegreeAllowablePositions(String jobTitle, int jobLevel,
            String jobDegree);

    /**
     * Find all allowable positions for next job degree
     *
     * @param jobTitle
     * @param precedenceNumber
     * @return List<Position>
     */
    List<Position> findNextDegreeAllowablePositions(String jobTitle, int precedenceNumber);

}
