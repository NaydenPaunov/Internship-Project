package com.dxc.payroll.service.implementation.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.dxc.payroll.persistence.domain.Job;
import com.dxc.payroll.persistence.domain.Position;
import com.dxc.payroll.persistence.repositories.JobRepository;
import com.dxc.payroll.service.implementation.JobServiceLogic;
import com.dxc.payroll.services.dto.ComboBoxesDTO;
import com.dxc.payroll.services.dto.JobDTO;
import com.dxc.payroll.services.dto.JobWithSalaryDTO;
import com.dxc.payroll.services.dto.LevelAndSalaryDTO;
import com.dxc.payroll.services.dto.PositionDTO;
import com.dxc.payroll.services.dto.ProfessionDTO;

/**
 *
 */
@SuppressWarnings("nls")
public class JobServiceTest {
    /**
     * Wrong job title name
     */
    private static final String WRONG_JOB_TITLE = "Java Dev";
    /**
     * Sample job title
     */
    private static final String JAVA_DEVELOPER = "Java Developer";
    /**
     * Sample job degree
     */
    private static final String JUNIOR = "Junior";
    /**
     * JobRepository mock
     */
    private JobRepository jobRepository;
    /**
     * Job mock
     */
    private Job job;
    /**
     * List of jobs mock.
     */
    private List<Job> jobs;
    /**
     * List of job titles mock.
     */
    private List<String> jobTitles;
    /**
     * List of job degrees mock.
     */
    private List<String> jobDegrees;
    /**
     * List of job levels mock.
     */
    private List<Integer> jobLevels;
    /**
     * Position mock.
     */
    private Position position;
    /**
     * List of positions mock.
     */
    private List<Position> positions;

    /**
     * Initializing objects with new mock repository before each test.
     */
    @Before
    public void initialize() {
        // TODO create JobMock class
        job = mock(Job.class);
        when(job.getJobTitle()).thenReturn(JAVA_DEVELOPER);
        when(job.getJobDegree()).thenReturn(JUNIOR);
        when(job.getPrecedenceNumber()).thenReturn(20);
        when(job.getMinSalary()).thenReturn(1500.00);
        when(job.getMaxSalary()).thenReturn(2500.00);

        jobs = new ArrayList<>();
        jobs.add(job);
        // TODO create PositionMock class
        position = mock(Position.class);
        when(position.getJob()).thenReturn(job);
        when(position.getJobLevel()).thenReturn(3);
        when(position.getBaseSalary()).thenReturn(1800.00);

        positions = new ArrayList<>();
        positions.add(position);

        jobTitles = new ArrayList<>();
        jobTitles.add(JAVA_DEVELOPER);
        jobTitles.add("Python Developer");
        jobTitles.add("Scala Developer");

        jobDegrees = new ArrayList<>();
        jobDegrees.add(JUNIOR);
        jobDegrees.add("Senior");

        jobLevels = new ArrayList<>();
        jobLevels.add(3);
        jobLevels.add(5);

        jobRepository = mock(JobRepository.class);
    }

    /**
     *
     * Tests the case when a job is successfully found by its jobTitle and
     * JobDegree.
     *
     */
    @Test
    public void testFindBy() {
        // given
        final String jobTitle = JAVA_DEVELOPER;
        final String jobDegree = JUNIOR;
        when(jobRepository.findBy(jobTitle, jobDegree)).thenReturn(job);
        // when
        final JobDTO jobDto = JobServiceLogic.findBy(jobTitle, jobDegree, jobRepository);
        // then
        assertNotNull("The jobDto object should not be null.", jobDto);
        assertEquals("Job titles differ", jobTitle, jobDto.getJobTitle());
    }

    /**
     *
     * Tests the case when there are no results.
     *
     */
    @Test
    public void testFindByNoResults() {
        // given
        final String jobTitle = WRONG_JOB_TITLE;
        final String jobDegree = JUNIOR;
        when(jobRepository.findBy(jobTitle, jobDegree)).thenReturn(null);
        // when
        final JobDTO jobDto = JobServiceLogic.findBy(jobTitle, jobDegree, jobRepository);
        // then
        assertNull("The returned jobDTO object must be null. "
                + "The provided job title is not in the database.", jobDto);
    }

    /**
     *
     * Tests the case when positions are successfully found by jobTitle.
     *
     */
    @Test
    public void testFindJobAndPositionsByJobTitle() {
        // given
        final String jobTitle = JAVA_DEVELOPER;
        final String jobDegree = JUNIOR;
        when(jobRepository.findByJobTitle(jobTitle)).thenReturn(jobs);
        // when
        final ProfessionDTO professionDto = JobServiceLogic.findJobAndPositionsByJobTitle(jobTitle,
                jobRepository);
        // then
        assertEquals("Job degrees differ", jobDegree,
                professionDto.getJobWithSalaryDTOs().get(0).getJobDegree());
    }

    /**
     *
     * Tests the case when there are no positions to be found.
     *
     */
    @Test
    public void testFindJobAndPositionsByJobTitleNoResults() {
        // given
        final String jobTitle = WRONG_JOB_TITLE;
        when(jobRepository.findByJobTitle(jobTitle)).thenReturn(null);
        // when
        final ProfessionDTO professionDto = JobServiceLogic.findJobAndPositionsByJobTitle(jobTitle,
                jobRepository);
        // then
        assertNull("The returned professionDTO object must be null. "
                + "The provided job title is not in the database.", professionDto);
    }

    /**
     *
     * Tests the case when the position is successfully added.
     *
     */
    @Test
    public void testAddPosition() {
        // given
        final String jobTitle = JAVA_DEVELOPER;
        final String jobDegree = JUNIOR;
        final int jobLevel = 3;
        final double baseSalary = 1800;
        when(jobRepository.findBy(jobTitle, jobDegree)).thenReturn(job);
        when(jobRepository.addPosition(job, jobLevel, baseSalary)).thenReturn(position);
        // when
        final PositionDTO positionDto = JobServiceLogic.addPosition(jobTitle, jobDegree, jobLevel,
                baseSalary, jobRepository);
        // then
        assertEquals("Job titles differ", jobTitle, positionDto.getJobTitle());
    }

    /**
     *
     * Tests the case when the position cannot be added.
     *
     */
    @Test
    public void testAddPositionFail() {
        // given
        final String jobTitle = JAVA_DEVELOPER;
        final String jobDegree = JUNIOR;
        final int jobLevel = 4;
        final double baseSalary = 800;
        when(jobRepository.findBy(jobTitle, jobDegree)).thenReturn(job);
        when(jobRepository.addPosition(job, jobLevel, baseSalary)).thenReturn(null);
        // when
        final PositionDTO positionDto = JobServiceLogic.addPosition(jobTitle, jobDegree, jobLevel,
                baseSalary, jobRepository);
        // then
        assertNull("The returned PositionDTO object must be null. "
                + "The position should not be added to the database.", positionDto);
    }

    /**
     *
     * Tests the case when the list of jobs is successfully found by jobTitle.
     *
     */
    @Test
    public void testFindByJobTitle() {
        // given
        final String jobTitle = JAVA_DEVELOPER;
        when(jobRepository.findByJobTitle(jobTitle)).thenReturn(jobs);
        // when
        final List<JobDTO> jobDtos = JobServiceLogic.findByJobTitle(jobTitle, jobRepository);
        // then
        assertEquals("Job titles differ", jobTitle, jobDtos.get(0).getJobTitle());
    }

    /**
     *
     * Tests the case when there are no results for this jobTitle.
     *
     */
    @Test
    public void testFindByJobTitleNoResults() {
        // given
        final String jobTitle = WRONG_JOB_TITLE;
        when(jobRepository.findByJobTitle(jobTitle)).thenReturn(null);
        // when
        final List<JobDTO> jobDtos = JobServiceLogic.findByJobTitle(jobTitle, jobRepository);
        // then
        assertEquals("wrong jobDtos size expected 0", 0, jobDtos.size());
    }

    /**
     *
     * Test listJobTitles method
     *
     */
    @Test
    public void testListJobTitles() {
        // given
        when(jobRepository.listJobTitles()).thenReturn(jobTitles);
        final List<String> expected = Arrays.asList(JAVA_DEVELOPER, "Python Developer",
                "Scala Developer");
        // when
        final List<String> actual = JobServiceLogic.listJobTitles(jobRepository);
        // then
        assertThat("job title lists are not matching", actual, is(expected));
    }

    /**
     *
     * Tests the case when the list of positions is successfully found by
     * jobTitle and jobDegree.
     *
     */
    @Test
    public void testFindPositionsBy() {
        // given
        final String jobTitle = JAVA_DEVELOPER;
        final String jobDegree = JUNIOR;
        when(jobRepository.findPositionsBy(jobTitle, jobDegree)).thenReturn(positions);
        // when
        final List<PositionDTO> positionDtos = JobServiceLogic.findPositionsBy(jobTitle, jobDegree,
                jobRepository);
        // then
        assertEquals("Job degrees differ", jobDegree, positionDtos.get(0).getJobDegree());
    }

    /**
     *
     * Tests the case when there are no positions for the given jobTitle and
     * jobDegree.
     *
     */
    @Test
    public void testFindPositionsByNoResults() {
        // given
        final String jobTitle = WRONG_JOB_TITLE;
        final String jobDegree = JUNIOR;
        when(jobRepository.findPositionsBy(jobTitle, jobDegree)).thenReturn(new ArrayList<>());
        // when
        final List<PositionDTO> positionDtos = JobServiceLogic.findPositionsBy(jobTitle, jobDegree,
                jobRepository);
        // then
        assertEquals("wrong positionDtos list size expected 0", 0, positionDtos.size());
    }

    /**
     *
     * Tests the case when the jobs are successfully found by the given
     * jobTitle.
     *
     */
    @Test
    public void testGetJobsForProfession() {
        // given
        final String jobTitle = JAVA_DEVELOPER;
        final String jobDegree = JUNIOR;
        when(jobRepository.findByJobTitle(jobTitle)).thenReturn(jobs);
        // when
        final List<JobWithSalaryDTO> dtos = JobServiceLogic.getJobsForProfession(jobTitle,
                jobRepository);
        // then
        assertEquals("Job degrees differ", jobDegree, dtos.get(0).getJobDegree());
    }

    /**
     *
     * Tests the case when there are no jobs for the given jobTitle.
     *
     */
    @Test
    public void testGetJobsForProfessionNoResults() {
        // given
        final String jobTitle = WRONG_JOB_TITLE;
        when(jobRepository.findByJobTitle(jobTitle)).thenReturn(null);
        // when
        final List<JobWithSalaryDTO> dtos = JobServiceLogic.getJobsForProfession(jobTitle,
                jobRepository);
        // then
        assertEquals("wrong dtos size expected 0", 0, dtos.size());
    }

    /**
     *
     * Tests getLevelsAndSalaryBy method.
     *
     */
    @Test
    public void testGetLevelAndSalaryBy() {
        // given
        final String jobTitle = WRONG_JOB_TITLE;
        final String jobDegree = JUNIOR;
        when(jobRepository.findPositionsBy(jobTitle, jobDegree)).thenReturn(positions);
        // when
        final List<LevelAndSalaryDTO> dtos = JobServiceLogic.getLevelAndSalaryBy(jobTitle,
                jobDegree, jobRepository);
        // then
        assertEquals("Job levels differ", 3, dtos.get(0).getJobLevel());
    }

    /**
     *
     * Tests the case when there are no positions.
     *
     */
    @Test
    public void testGetLevelAndSalaryByNoResults() {
        // given
        final String jobTitle = WRONG_JOB_TITLE;
        final String jobDegree = JUNIOR;
        when(jobRepository.findPositionsBy(jobTitle, jobDegree)).thenReturn(null);
        // when
        final List<LevelAndSalaryDTO> dtos = JobServiceLogic.getLevelAndSalaryBy(jobTitle,
                jobDegree, jobRepository);
        // then
        assertEquals("wrong dtos size expected 0", 0, dtos.size());
    }

    /**
     *
     * Tests the case when the jobDegrees are successfully found by the given
     * jobTitle.
     *
     */
    @Test
    public void testGetJobDegrees() {
        // given
        final String jobTitle = JAVA_DEVELOPER;
        when(jobRepository.getJobDegrees(jobTitle)).thenReturn(jobDegrees);
        final List<String> expected = Arrays.asList(JUNIOR, "Senior");
        // when
        final List<String> actual = JobServiceLogic.getJobDegrees(jobTitle, jobRepository);
        // then
        assertThat("job title lists are not matching", actual, is(expected));
    }

    /**
     *
     * Tests the case when there are no jobDegrees for the given jobTitle.
     *
     */
    @Test
    public void testGetJobDegreesNoResults() {
        // given
        final String jobTitle = WRONG_JOB_TITLE;
        when(jobRepository.getJobDegrees(jobTitle)).thenReturn(new ArrayList<>());
        // when
        final List<String> degrees = JobServiceLogic.getJobDegrees(jobTitle, jobRepository);
        // then
        assertEquals("wrong degrees list size expected 0", 0, degrees.size());
    }

    /**
     *
     * Tests the case when all jobs are successfully retrieved.
     *
     */
    @Test
    public void testFindJobs() {
        // given
        final String jobDegree = JUNIOR;
        when(jobRepository.getAllJobs()).thenReturn(jobs);
        // when
        final List<JobDTO> jobDtos = JobServiceLogic.findJobs(jobRepository);
        // then
        assertEquals("Job degrees differ", jobDegree, jobDtos.get(0).getJobDegree());
    }

    /**
     *
     * Tests the case when there are no jobs.
     *
     */
    @Test
    public void testFindJobsNoResults() {
        // given
        when(jobRepository.getAllJobs()).thenReturn(null);
        // when
        final List<JobDTO> jobDtos = JobServiceLogic.findJobs(jobRepository);
        // then
        assertEquals("wrong jobDtos size expected 0", 0, jobDtos.size());
    }

    /**
     *
     * Tests the case when all levels are successfully retrieved.
     *
     */
    @Test
    public void testGetJobLevelsBy() {
        // given
        final String jobTitle = JAVA_DEVELOPER;
        final String jobDegree = JUNIOR;
        when(jobRepository.getJobLevelsBy(jobTitle, jobDegree)).thenReturn(jobLevels);
        // when
        final List<Integer> levels = JobServiceLogic.getJobLevelsBy(jobTitle, jobDegree,
                jobRepository);
        // then
        assertEquals("Job levles differ", Integer.valueOf(5), levels.get(1));
    }

    /**
     *
     * Tests the case when there are no job levels associated with the given
     * jobTitle and jobDegree.
     *
     */
    @Test
    public void testGetJobLevelsByNoResults() {
        // given
        final String jobTitle = JAVA_DEVELOPER;
        final String jobDegree = JUNIOR;
        when(jobRepository.getJobLevelsBy(jobTitle, jobDegree)).thenReturn(null);
        // when
        final List<Integer> levels = JobServiceLogic.getJobLevelsBy(jobTitle, jobDegree,
                jobRepository);
        // then
        assertEquals("wrong levles list size expected 0", 0, levels.size());
    }

    /**
     *
     * Test loadComboBoxes method
     *
     */
    @Test
    public void testLoadComboBoxes() {
        // given
        when(jobRepository.listJobTitles()).thenReturn(jobTitles);
        // when
        final ComboBoxesDTO comboBoxes = JobServiceLogic.loadComboBoxes(jobRepository);
        // then
        assertEquals("Job titles differ", JAVA_DEVELOPER, comboBoxes.getAllJobTitles().get(0));
    }

}
