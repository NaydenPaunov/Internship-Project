package com.dxc.payroll.service.implementation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.dxc.payroll.persistence.domain.Job;
import com.dxc.payroll.persistence.domain.Position;
import com.dxc.payroll.persistence.repositories.JobRepository;
import com.dxc.payroll.services.JobService;
import com.dxc.payroll.services.dto.ComboBoxesDTO;
import com.dxc.payroll.services.dto.JobDTO;
import com.dxc.payroll.services.dto.JobTitleAndDegreesDTO;
import com.dxc.payroll.services.dto.JobWithSalaryAddDTO;
import com.dxc.payroll.services.dto.JobWithSalaryDTO;
import com.dxc.payroll.services.dto.LevelAndSalaryDTO;
import com.dxc.payroll.services.dto.PositionDTO;
import com.dxc.payroll.services.dto.ProfessionAddDTO;
import com.dxc.payroll.services.dto.ProfessionDTO;

/**
 * @see JobService
 */
public final class JobServiceLogic {
    private JobServiceLogic() {
    }

    /**
     * Finds a job by jobTitle and all positions for this job.
     *
     * @param jobTitle
     *            - must not be empty or null
     * @param jobRepository
     * @return ProffesionDTO object
     */
    public static ProfessionDTO findJobAndPositionsByJobTitle(final String jobTitle,
            final JobRepository jobRepository) {
        final List<Job> jobs = jobRepository.findByJobTitle(jobTitle) == null ? new ArrayList<>()
                : jobRepository.findByJobTitle(jobTitle);
        final List<JobWithSalaryDTO> jobDtos = new LinkedList<>();
        for (final Job job : jobs) {
            final List<LevelAndSalaryDTO> levelAndSalary = positionsToLevelAndSalaryDTO(
                    job.getPositions());
            final JobWithSalaryDTO jobDto = jobToJobForProfessionDTO(job, levelAndSalary);
            jobDtos.add(jobDto);
        }
        return jobDtos.isEmpty() ? null : new ProfessionDTO(jobs.get(0).getJobTitle(), jobDtos);
    }

    /**
     * Finds a job by jobTitle and all positions for this job.
     *
     * @param jobTitle
     *            - must not be empty or null
     * @param jobRepository
     * @return ProffesionAddDTO object
     */
    public static ProfessionAddDTO findJobAndPositionsByJobTitleForAddProffesion(
            final String jobTitle, final JobRepository jobRepository) {
        final List<Job> jobs = jobRepository.findByJobTitle(jobTitle) == null ? new ArrayList<>()
                : jobRepository.findByJobTitle(jobTitle);
        final List<JobWithSalaryAddDTO> jobDtos = new LinkedList<>();
        for (final Job job : jobs) {
            final List<LevelAndSalaryDTO> levelAndSalary = positionsToLevelAndSalaryDTO(
                    job.getPositions());
            final JobWithSalaryAddDTO jobAddDto = jobToJobForProfessionAddDTO(job, levelAndSalary);
            jobDtos.add(jobAddDto);
        }
        return jobDtos.isEmpty() ? null : new ProfessionAddDTO(jobs.get(0).getJobTitle(), jobDtos);
    }

    /**
     * Add Position to database.
     *
     * @param jobTitle
     *            - must not be empty or null
     * @param jobDegree
     *            - must not be empty or null
     * @param jobLevel
     *            - must not be empty or null
     * @param baseSalary
     *            - must not be empty or null
     * @param jobRepository
     * @return PositionDTO object
     */
    public static PositionDTO addPosition(final String jobTitle, final String jobDegree,
            final int jobLevel, final double baseSalary, final JobRepository jobRepository) {
        final Job job = jobRepository.findBy(jobTitle, jobDegree);
        final Position position = jobRepository.addPosition(job, jobLevel, baseSalary);
        return position == null ? null : positionToPositionDTO(position);
    }

    /**
     * Gets all jobs with the given job title.
     *
     * If there are none, an empty list is returned.
     *
     * @param jobTitle
     *            - must not be empty or null
     * @param jobRepository
     * @return list of jobs
     */
    public static List<JobDTO> findByJobTitle(final String jobTitle,
            final JobRepository jobRepository) {
        final List<Job> jobsList = jobRepository.findByJobTitle(jobTitle) == null
                ? new ArrayList<>() : jobRepository.findByJobTitle(jobTitle);
        final List<JobDTO> jobDTOList = new LinkedList<>();
        for (final Job job : jobsList) {
            final JobDTO jobDTO = jobToJobDTO(job);
            jobDTOList.add(jobDTO);
        }
        return jobDTOList;
    }

    /**
     * Finds the job, corresponding to the provided job title and job degree.
     *
     * @param jobTitle
     *            - must not be empty or null
     * @param jobDegree
     *            - must not be empty or null
     * @param jobRepository
     *            - must not be empty or null
     * @return the found job
     */
    public static JobDTO findBy(final String jobTitle, final String jobDegree,
            final JobRepository jobRepository) {
        final Job job = jobRepository.findBy(jobTitle, jobDegree);
        return job == null ? null : jobToJobDTO(job);
    }

    /**
     * Gets all jobTitles which are in the database.
     *
     * @param jobRepository
     * @return list of all job titles
     */
    public static List<String> listJobTitles(final JobRepository jobRepository) {
        return jobRepository.listJobTitles();
    }

    /**
     * Finds all positions for the job with the given job title and job degree.
     *
     * @param jobTitle
     *            - must not be empty or null
     * @param jobDegree
     *            - must not be empty or null
     * @param jobRepository
     * @return list of PositionDTO
     */
    public static List<PositionDTO> findPositionsBy(final String jobTitle, final String jobDegree,
            final JobRepository jobRepository) {
        final List<Position> positions = jobRepository.findPositionsBy(jobTitle, jobDegree);
        return positionsToPositionDTO(positions);
    }

    /**
     * Returns a list of JobWithSalaryDTO objects, containing information about
     * all jobs for the given profession.
     *
     * @param jobTitle
     *            - must not be empty or null
     * @param jobRepository
     * @return all jobs for the given profession
     */
    public static List<JobWithSalaryDTO> getJobsForProfession(final String jobTitle,
            final JobRepository jobRepository) {
        final List<Job> jobs = jobRepository.findByJobTitle(jobTitle) == null ? new ArrayList<>()
                : jobRepository.findByJobTitle(jobTitle);
        final List<JobWithSalaryDTO> jobList = new ArrayList<>();
        for (final Job job : jobs) {
            jobList.add(jobToJobForProfessionDTO(job,
                    getLevelAndSalaryBy(jobTitle, job.getJobDegree(), jobRepository)));
        }
        return jobList;
    }

    /**
     * Gets a list of LevelAndSalaryDTO objects, containing information for all
     * levels and corresponding base salaries which are linked to the given job.
     *
     * @param jobTitle
     *            - must not be empty or null
     * @param jobDegree
     *            - must not be empty or null
     * @param jobRepository
     * @return list LevelAndSalaryDTO objects
     */
    public static List<LevelAndSalaryDTO> getLevelAndSalaryBy(final String jobTitle,
            final String jobDegree, final JobRepository jobRepository) {
        final List<Position> positions = jobRepository.findPositionsBy(jobTitle, jobDegree) == null
                ? new ArrayList<>() : jobRepository.findPositionsBy(jobTitle, jobDegree);
        return positionsToLevelAndSalaryDTO(positions);
    }

    /**
     * Gets all jobDegrees for the given jobTitle from the database.
     *
     * @param jobTitle
     * @param jobRepository
     * @return all jobDegrees for the given jobTitle
     */
    public static List<String> getJobDegrees(final String jobTitle,
            final JobRepository jobRepository) {
        return jobRepository.getJobDegrees(jobTitle);
    }

    /**
     * Finds all Jobs in the database.
     *
     * @param jobRepository
     * @return list of JobDTO objects
     */
    public static List<JobDTO> findJobs(final JobRepository jobRepository) {
        final List<Job> jobsList = jobRepository.getAllJobs() == null ? new ArrayList<>()
                : jobRepository.getAllJobs();
        final List<JobDTO> jobDTOList = new LinkedList<>();
        for (final Job job : jobsList) {
            final JobDTO jobDTO = jobToJobDTO(job);
            jobDTOList.add(jobDTO);
        }
        return jobDTOList;
    }

    /**
     * @param jobTitle
     *            - must not be empty or null
     * @param jobDegree
     *            - must not be empty or null
     * @param jobRepository
     *            - must not be empty or null
     * @return all job levels for the given job
     */
    public static List<Integer> getJobLevelsBy(final String jobTitle, final String jobDegree,
            final JobRepository jobRepository) {
        return jobRepository.getJobLevelsBy(jobTitle, jobDegree) == null ? new ArrayList<>()
                : jobRepository.getJobLevelsBy(jobTitle, jobDegree);
    }

    /**
     * Loads the content of the combo boxes.
     *
     * Returns ComboBoxesDTO object, containing list of all job titles and the
     * job degrees, corresponding to them.
     *
     * @param jobRepository
     * @return ComboBoxesDTO object
     */
    public static ComboBoxesDTO loadComboBoxes(final JobRepository jobRepository) {
        final List<String> jobTitles = jobRepository.listJobTitles();
        final List<JobTitleAndDegreesDTO> dtos = new ArrayList<>();
        for (final String jobTitle : jobTitles) {
            dtos.add(createjobTitleAndDegreeDto(jobTitle, jobRepository));
        }
        return new ComboBoxesDTO(dtos);
    }

    /**
     * Gets the required job information. If there are no jobs which matching to
     * the given search parameters IllegalArgumentException is thrown.
     *
     * @param jobTitle
     *            - must not be empty or null
     * @param jobDegree
     * @param jobRepository
     * @return return job and position information based on the input parameters
     */
    @SuppressWarnings("nls")
    public static List<JobWithSalaryDTO> getJobInfo(final String jobTitle, final String jobDegree,
            final JobRepository jobRepository) {
        if (jobTitle != null && !jobTitle.isEmpty()) {
            List<JobWithSalaryDTO> jobList;
            if (jobDegree == null || jobDegree.isEmpty()) {
                jobList = getJobsForProfession(jobTitle, jobRepository);
            }
            else {
                jobList = filterByJobTitleAndJobDegree(jobTitle, jobDegree, jobRepository);
            }
            return jobList;
        }
        throw new IllegalArgumentException("Incorrect input");
    }

    private static JobTitleAndDegreesDTO createjobTitleAndDegreeDto(final String jobTitle,
            final JobRepository jobRepository) {
        return new JobTitleAndDegreesDTO(jobTitle, jobRepository.getJobDegrees(jobTitle));
    }

    private static List<JobWithSalaryDTO> filterByJobTitleAndJobDegree(final String jobTitle,
            final String jobDegree, final JobRepository jobRepository) {
        return getJobsForProfession(jobTitle, jobRepository).stream()
                .filter(job -> job.getJobDegree().equals(jobDegree)).collect(Collectors.toList());
    }

    private static JobDTO jobToJobDTO(final Job job) {
        return new JobDTO(job.getJobTitle(), job.getJobDegree(), job.getMinSalary(),
                job.getMaxSalary());
    }

    private static PositionDTO positionToPositionDTO(final Position position) {
        return new PositionDTO(position.getJob().getJobTitle(), position.getJob().getJobDegree(),
                position.getJobLevel(), position.getBaseSalary());
    }

    private static List<PositionDTO> positionsToPositionDTO(final List<Position> positions) {
        final List<PositionDTO> positionsList = new ArrayList<>();
        for (final Position position : positions) {
            positionsList.add(positionToPositionDTO(position));
        }
        return positionsList;
    }

    private static LevelAndSalaryDTO positionToLevelAndSalaryDTO(final Position position) {
        return new LevelAndSalaryDTO(position.getJobLevel(), position.getBaseSalary());
    }

    private static List<LevelAndSalaryDTO> positionsToLevelAndSalaryDTO(
            final List<Position> positions) {
        final List<LevelAndSalaryDTO> levelAndSalaries = new ArrayList<>();
        for (final Position position : positions) {
            levelAndSalaries.add(positionToLevelAndSalaryDTO(position));
        }
        return levelAndSalaries;
    }

    private static JobWithSalaryDTO jobToJobForProfessionDTO(final Job job,
            final List<LevelAndSalaryDTO> levelsAndSalaries) {
        return new JobWithSalaryDTO(job.getJobDegree(), job.getMinSalary(), job.getMaxSalary(),
                levelsAndSalaries);
    }

    private static JobWithSalaryAddDTO jobToJobForProfessionAddDTO(final Job job,
            final List<LevelAndSalaryDTO> levelsAndSalaries) {
        final int jobLevelToBeAdded = levelsAndSalaries.isEmpty() ? 1
                : levelsAndSalaries.get(levelsAndSalaries.size() - 1).getJobLevel() + 1;
        final double minSalaryToBeChecked = levelsAndSalaries.isEmpty() ? job.getMinSalary()
                : levelsAndSalaries.get(levelsAndSalaries.size() - 1).getBaseSalary();
        return new JobWithSalaryAddDTO(job.getJobDegree(), job.getMinSalary(), job.getMaxSalary(),
                levelsAndSalaries, jobLevelToBeAdded, minSalaryToBeChecked);
    }

}
