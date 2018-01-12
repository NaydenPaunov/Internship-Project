package com.dxc.payroll.persistence.jpa.repositories;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import com.dxc.payroll.persistence.domain.Job;
import com.dxc.payroll.persistence.domain.Position;
import com.dxc.payroll.persistence.jpa.constants.PayrollPersistenceConstants;
import com.dxc.payroll.persistence.jpa.domain.JpaJob;
import com.dxc.payroll.persistence.jpa.domain.JpaJobID;
import com.dxc.payroll.persistence.jpa.domain.JpaPosition;
import com.dxc.payroll.persistence.jpa.domain.PositionID;
import com.dxc.payroll.persistence.jpa.utils.Utils;
import com.dxc.payroll.persistence.repositories.JobRepository;

/**
 * Implementation of JobRepository interface
 */
public class JpaJobRepository implements JobRepository {
    @SuppressWarnings("nls")
    /**
     * jobId constant
     */
    private static final String JOB_ID_PARAM = "jobId";
    @SuppressWarnings("nls")
    /**
     * jobTitle constant
     */
    private static final String JOB_TITLE_PARAM = "jobTitle";
    /**
     *
     * Entity manager.
     *
     * Needed for the job repository.
     *
     */
    private final EntityManager entityManager;

    /**
     *
     * Constructor for the JpaJobRepository.
     *
     * It accepts one argument - the entity manager.
     *
     * @param entityManager
     *
     */
    public JpaJobRepository(final EntityManager entityManager) {
        assert entityManager != null;
        this.entityManager = entityManager;
    }

    /**
     *
     * Creates new job with the provided job title, minimum and maximum salary.
     *
     * @see com.dxc.payroll.persistence.repositories.JobRepository#createJob(java.
     *      lang.String, double, double)
     *
     */
    @Override
    public Job createJob(final String jobTitle, final String jobDegree, final int precedenceNumber,
            final double minSalary, final double maxSalary) {
        final Job job = new JpaJob(jobTitle, jobDegree, precedenceNumber, minSalary, maxSalary);
        entityManager.persist(job);
        return job;
    }

    /**
     *
     * Gets all positions for the job with the given jobTitle and jobDegree.
     *
     * @see com.dxc.payroll.persistence.repositories.JobRepository#findPositionsBy(java.lang.String,
     *      java.lang.String)
     *
     */
    @SuppressWarnings("nls")
    @Override
    public List<Position> findPositionsBy(final String jobTitle, final String jobDegree) {
        final JpaJobID jobId = new JpaJobID(jobTitle, jobDegree);
        return entityManager.createNamedQuery("qryGetAllPositionsForJob", Position.class)
                .setParameter(JOB_ID_PARAM, jobId).getResultList();
    }

    /**
     * Returns list of jobs, which correspond to the given job title
     *
     * @see com.dxc.payroll.persistence.repositories.JobRepository#findByJobTitle(
     *      java.lang.String)
     *
     */
    @SuppressWarnings("nls")
    @Override
    public List<Job> findByJobTitle(final String jobTitle) {
        return entityManager.createNamedQuery("qryFindJobsByJobTitle", Job.class)
                .setParameter(JOB_TITLE_PARAM,
                        Utils.padRight(jobTitle, PayrollPersistenceConstants.JOB_TITLE_LENGTH))
                .getResultList();
    }

    /**
     *
     * Finds job by provided JobTitle and JobDegree. The PK of the Job.
     *
     * @see com.dxc.payroll.persistence.repositories.JobRepository#findBy(java.lang.
     *      String)
     *
     */
    @Override
    public Job findBy(final String jobTitle, final String jobDegree) {
        final JpaJobID jobId = new JpaJobID(jobTitle, jobDegree);
        return entityManager.find(JpaJob.class, jobId);
    }

    /**
     *
     * @see com.dxc.payroll.persistence.repositories.JobRepository#addPosition(com.dxc.payroll.
     *      persistence.domain.Job, int, double)
     *
     */
    @Override
    public Position addPosition(final Job job, final int jobLevel, final double baseSalary) {
        final Position position = new JpaPosition(job, jobLevel, baseSalary);
        entityManager.persist(position);
        return position;
    }

    /**
     *
     * Finds Position by provided JobTitle, JobDegree and JobLevel. The PK of
     * Position.
     *
     * @see com.dxc.payroll.persistence.repositories.JobRepository#findBy(java.lang.
     *      String)
     *
     */
    @Override
    public Position findPositionBy(final String jobTitle, final String jobDegree,
            final int jobLevel) {
        final JpaJobID jobId = new JpaJobID(jobTitle, jobDegree);
        final PositionID positionID = new PositionID(jobId, jobLevel);
        return entityManager.find(JpaPosition.class, positionID);
    }

    /**
     * Returns a list, containing all job titles present in the database, if
     * there are none - an empty list is returned.
     *
     * @see com.dxc.payroll.persistence.repositories.JobRepository#listJobTitles()
     *
     */
    @SuppressWarnings("nls")
    @Override
    public List<String> listJobTitles() {
        final List<String> jobTitles = entityManager
                .createNamedQuery("qryListJobTitles", String.class).getResultList();
        return jobTitles.stream().map(String::trim).collect(Collectors.toList());
    }

    /**
     * Returns a list, containing all job degrees for the given jobTitle, if
     * there are none - an empty list is returned.
     *
     * @see com.dxc.payroll.persistence.repositories.JobRepository#getJobDegrees()
     *
     */
    @SuppressWarnings("nls")
    @Override
    public List<String> getJobDegrees(final String jobTitle) {
        final List<String> jobDegrees = entityManager
                .createNamedQuery("qryGetJobDegrees", String.class)
                .setParameter(JOB_TITLE_PARAM,
                        Utils.padRight(jobTitle, PayrollPersistenceConstants.JOB_TITLE_LENGTH))
                .getResultList();
        return jobDegrees.stream().map(String::trim).collect(Collectors.toList());
    }

    /**
     *
     * Gets all jobs from the database.
     *
     * @see com.dxc.payroll.persistence.repositories.JobRepository#getAllJobs()
     *
     */
    @SuppressWarnings("nls")
    @Override
    public List<Job> getAllJobs() {
        return entityManager.createNamedQuery("qryFindAllJobs", Job.class).getResultList();
    }

    /**
     *
     * @see com.dxc.payroll.persistence.repositories.JobRepository#getJobLevelsBy(java.lang.String,
     *      java.lang.String)
     *
     */
    @SuppressWarnings("nls")
    @Override
    public List<Integer> getJobLevelsBy(final String jobTitle, final String jobDegree) {
        final JpaJobID jobId = new JpaJobID(jobTitle, jobDegree);
        return entityManager.createNamedQuery("qryGetAllLevelsForJob", Integer.class)
                .setParameter(JOB_ID_PARAM, jobId).getResultList();
    }

    /**
     *
     * @see com.dxc.payroll.persistence.repositories.JobRepository
     *      #getNextPrecedensNumber(java.lang.Integer)
     *
     */
    @SuppressWarnings("nls")
    @Override
    public Integer getNextPrecedensNumber(final int precedenceNumber) {
        final List<Integer> precedenceNumbers = entityManager
                .createNamedQuery("qryGetNextPrecedenceNumber", Integer.class)
                .setParameter("precedenceNumber", precedenceNumber).getResultList();
        return precedenceNumbers.isEmpty() ? precedenceNumber : precedenceNumbers.get(0);
    }

    /**
     *
     * @see com.dxc.payroll.persistence.repositories.JobRepository
     *      #getNextPrecedensNumber(java.lang.Integer)
     *
     */
    @SuppressWarnings("nls")
    @Override
    public List<Position> findCurrentDegreeAllowablePositions(final String jobTitle,
            final int jobLevel, final String jobDegree) {
        final JpaJobID jobId = new JpaJobID(jobTitle, jobDegree);
        return entityManager
                .createNamedQuery("qryFindCurentDegreeAllowablePositions", Position.class)
                .setParameter(JOB_ID_PARAM, jobId).setParameter("jobLevel", jobLevel)
                .getResultList();
    }

    /**
     *
     * @see com.dxc.payroll.persistence.repositories.JobRepository
     *      #getNextPrecedensNumber(java.lang.Integer)
     *
     */
    @SuppressWarnings("nls")
    @Override
    public List<Position> findNextDegreeAllowablePositions(final String jobTitle,
            final int precedenceNumber) {
        return entityManager.createNamedQuery("qryFindNextDegreeAllowablePositions", Position.class)
                .setParameter(JOB_TITLE_PARAM,
                        Utils.padRight(jobTitle, PayrollPersistenceConstants.JOB_TITLE_LENGTH))
                .setParameter("precedenceNumber", getNextPrecedensNumber(precedenceNumber))
                .getResultList();
    }

}
