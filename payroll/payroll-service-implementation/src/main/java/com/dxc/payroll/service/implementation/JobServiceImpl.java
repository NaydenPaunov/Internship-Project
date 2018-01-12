package com.dxc.payroll.service.implementation;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.dxc.payroll.common.Factory;
import com.dxc.payroll.persistence.repositories.JobRepository;
import com.dxc.payroll.persistence.utils.TransactionHandler;
import com.dxc.payroll.service.exceptions.EmptyValueException;
import com.dxc.payroll.services.JobService;
import com.dxc.payroll.services.dto.ComboBoxesDTO;
import com.dxc.payroll.services.dto.JobDTO;
import com.dxc.payroll.services.dto.JobWithSalaryDTO;
import com.dxc.payroll.services.dto.LevelAndSalaryDTO;
import com.dxc.payroll.services.dto.PositionDTO;
import com.dxc.payroll.services.dto.ProfessionAddDTO;
import com.dxc.payroll.services.dto.ProfessionDTO;

/**
 *
 */
public class JobServiceImpl extends AbstractService implements JobService {
    /**
     * Constant for global logger.
     */
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Constructor for the JobServiceImpl
     *
     * @param transactionHandler
     */
    public JobServiceImpl(final TransactionHandler transactionHandler) {
        super(transactionHandler);
    }

    /**
     * @see com.dxc.payroll.services.JobService#findJobAndPositionsByJobTitle
     *      (java.lang.String)
     */
    @Override
    public ProfessionDTO findJobAndPositionsByJobTitle(final String jobTitle) {
        return transactionHandler.execute((final Factory factory) -> JobServiceLogic
                .findJobAndPositionsByJobTitle(jobTitle, factory.findService(JobRepository.class)));
    }

    /**
     * @see com.dxc.payroll.services.JobService#findJobAndPositionsByJobTitleForAddProffesion
     *      (java.lang.String)
     */
    @Override
    public ProfessionAddDTO findJobAndPositionsByJobTitleForAddProffesion(final String jobTitle) {
        return transactionHandler.execute((final Factory factory) -> JobServiceLogic
                .findJobAndPositionsByJobTitleForAddProffesion(jobTitle,
                        factory.findService(JobRepository.class)));
    }

    /**
     * @see com.dxc.payroll.services.JobService#addPosition()
     *
     * @throws EmptyValueException
     */
    @SuppressWarnings("nls")
    @Override
    public PositionDTO addPosition(final String jobTitle, final String jobDegree,
            final int jobLevel, final double baseSalary) {
        if ((jobTitle == null) || (jobTitle.isEmpty())) {
            nullOrEmpty("jobTitle");
        }
        else if ((jobDegree == null) || (jobDegree.isEmpty())) {
            nullOrEmpty("jobDegree");
        }
        else if (jobLevel <= 0) {
            noPositiveNumber("jobLevel");
        }
        else if (baseSalary <= 0) {
            noPositiveNumber("baseSalary");
        }
        return transactionHandler
                .execute((final Factory factory) -> JobServiceLogic.addPosition(jobTitle, jobDegree,
                        jobLevel, baseSalary, factory.findService(JobRepository.class)));
    }

    /**
     * @see com.dxc.payroll.services.JobService#findByJobTitle(java.lang.String)
     */
    @Override
    public List<JobDTO> findByJobTitle(final String jobTitle) {
        return transactionHandler.execute((final Factory factory) -> JobServiceLogic
                .findByJobTitle(jobTitle, factory.findService(JobRepository.class)));
    }

    /**
     * @see com.dxc.payroll.services.JobService#findBy(java.lang.String)
     */
    @Override
    public JobDTO findBy(final String jobTitle, final String jobDegree) {
        return transactionHandler.execute((final Factory factory) -> JobServiceLogic
                .findBy(jobTitle, jobDegree, factory.findService(JobRepository.class)));
    }

    /**
     * @see com.dxc.payroll.services.JobService#listJobTitles()
     */
    @Override
    public List<String> getAllJobTitles() {
        return transactionHandler.execute((final Factory factory) -> JobServiceLogic
                .listJobTitles(factory.findService(JobRepository.class)));
    }

    /**
     * @see com.dxc.payroll.services.JobService#findPositionByTitleAndDegree
     *      (java.lang.String,java.lang.String)
     *
     */
    @Override
    public List<PositionDTO> findPositionsBy(final String jobTitle, final String jobDegree) {
        return transactionHandler.execute((final Factory factory) -> JobServiceLogic
                .findPositionsBy(jobTitle, jobDegree, factory.findService(JobRepository.class)));
    }

    /**
     * @param jobTitle
     * @return all jobs for the given job title
     */
    @Override
    public List<JobWithSalaryDTO> getJobsForProfession(final String jobTitle) {
        return transactionHandler.execute((final Factory factory) -> JobServiceLogic
                .getJobsForProfession(jobTitle, factory.findService(JobRepository.class)));
    }

    /**
     * @see com.dxc.payroll.services.JobService#findPositionByTitleAndDegree
     *      (java.lang.String,java.lang.String)
     *
     */
    @Override
    public List<LevelAndSalaryDTO> getLevelAndSalaryBy(final String jobTitle,
            final String jobDegree) {
        return transactionHandler
                .execute((final Factory factory) -> JobServiceLogic.getLevelAndSalaryBy(jobTitle,
                        jobDegree, factory.findService(JobRepository.class)));
    }

    /**
     * @see com.dxc.payroll.services.JobService#getJobDegrees(java.lang.String)
     */
    @Override
    public List<String> getJobDegrees(final String jobTitle) {
        return transactionHandler.execute((final Factory factory) -> JobServiceLogic
                .getJobDegrees(jobTitle, factory.findService(JobRepository.class)));
    }

    /**
     * @see com.dxc.payroll.services.JobService#findPositions()
     */
    @Override
    public List<JobDTO> findJobs() {
        return transactionHandler.execute((final Factory factory) -> JobServiceLogic
                .findJobs(factory.findService(JobRepository.class)));
    }

    /**
     * @see com.dxc.payroll.services.JobService#getJobLevelsBy(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public List<Integer> getJobLevelsBy(final String jobTitle, final String jobDegree) {
        return transactionHandler.execute((final Factory factory) -> JobServiceLogic
                .getJobLevelsBy(jobTitle, jobDegree, factory.findService(JobRepository.class)));
    }

    /**
     * @param jobTitle
     * @param jobDegree
     * @return information, based on the given parameters
     */

    @Override
    public List<JobWithSalaryDTO> getJobInfo(final String jobTitle, final String jobDegree) {
        return transactionHandler.execute((final Factory factory) -> JobServiceLogic
                .getJobInfo(jobTitle, jobDegree, factory.findService(JobRepository.class)));
    }

    /**
     * @return comboBoxesContent
     */
    @Override
    public ComboBoxesDTO loadComboBoxes() {
        return transactionHandler.execute((final Factory factory) -> JobServiceLogic
                .loadComboBoxes(factory.findService(JobRepository.class)));
    }

    @SuppressWarnings("nls")
    private static void nullOrEmpty(final String parameterName) {
        final String exceptionMessage = parameterName + "must not be null or empty.";
        LOGGER.log(Level.WARNING, exceptionMessage);
        throw new EmptyValueException(exceptionMessage);
    }

    @SuppressWarnings("nls")
    private static void noPositiveNumber(final String parameterName) {
        final String exceptionMessage = parameterName + "must be greater than 0.";
        LOGGER.log(Level.WARNING, exceptionMessage);
        throw new EmptyValueException(exceptionMessage);
    }

}
