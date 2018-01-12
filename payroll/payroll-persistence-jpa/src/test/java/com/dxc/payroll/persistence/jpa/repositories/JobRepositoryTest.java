package com.dxc.payroll.persistence.jpa.repositories;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.unitils.reflectionassert.ReflectionAssert.assertPropertyLenientEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Assert;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;

import com.dxc.payroll.common.Factory;
import com.dxc.payroll.common.Register;
import com.dxc.payroll.persistence.domain.Job;
import com.dxc.payroll.persistence.domain.Position;
import com.dxc.payroll.persistence.jpa.domain.JpaJobID;
import com.dxc.payroll.persistence.jpa.domain.PositionID;
import com.dxc.payroll.persistence.jpa.utils.TransactionHandlerImpl;
import com.dxc.payroll.persistence.repositories.EmployeeRepository;
import com.dxc.payroll.persistence.repositories.IndexationRepository;
import com.dxc.payroll.persistence.repositories.JobRepository;
import com.dxc.payroll.persistence.repositories.PositionPeriodRepository;
import com.dxc.payroll.persistence.repositories.RoleRepository;
import com.dxc.payroll.persistence.repositories.TaxRepository;
import com.dxc.payroll.persistence.repositories.UserRightRepository;
import com.dxc.payroll.persistence.utils.TransactionHandler;

/**
 * Tests for all methods in JobRepository
 */
@SuppressWarnings("nls")
public class JobRepositoryTest extends UnitilsJUnit4 {
    /**
     * The delta value is indeed the "error" or "uncertainty" allowed in the
     * comparison.
     */
    private static final double DELTA = 1e-15;
    /**
     * Job degree junior
     */
    private static final String JUNIOR = "Junior";
    /**
     * Clean data base file path
     */
    private static final String CLEAN_DB_XML = "CleanDB.xml";
    /**
     * private field - entity manager factory
     */
    private final EntityManagerFactory entityManagerFactory = Persistence
            .createEntityManagerFactory("payroll");

    private static Register<EntityManager> register = registerRepositories();

    /**
     * Register repositories.
     *
     * @return Register
     */
    private static Register<EntityManager> registerRepositories() {
        final Register<EntityManager> repositoryRegister = new Register<>();
        repositoryRegister.registerService(EmployeeRepository.class, JpaEmployeeRepository::new);
        repositoryRegister.registerService(IndexationRepository.class,
                JpaIndexationRepository::new);
        repositoryRegister.registerService(JobRepository.class, JpaJobRepository::new);
        repositoryRegister.registerService(PositionPeriodRepository.class,
                JpaPositionPeriodRepository::new);
        repositoryRegister.registerService(TaxRepository.class, JpaTaxRepository::new);
        repositoryRegister.registerService(RoleRepository.class, JpaRoleRepository::new);
        repositoryRegister.registerService(UserRightRepository.class, JpaUserRightRepository::new);
        return repositoryRegister;
    }

    /**
     * private field - transaction handler
     */
    private final TransactionHandler transactionHandler = new TransactionHandlerImpl(
            entityManagerFactory, register);

    /**
     * Test for create job
     *
     */
    @Test
    @DataSet(CLEAN_DB_XML)
    @ExpectedDataSet("job/JobRepositoryTest-result.xml")
    public void testCreateJob() {
        Assert.assertNotNull("The created job must not be null.",
                transactionHandler.execute((final Factory factory) -> {
                    final JobRepository jobRepository = factory.findService(JobRepository.class);
                    return jobRepository.createJob("Java Developer", JUNIOR, 20, 1200, 2300);
                }));
    }

    /**
     * Test for create position
     *
     */
    @Test
    @DataSet({ "job/CreateJobForPosition.xml", CLEAN_DB_XML })
    @ExpectedDataSet({ "job/CreatePosition-result.xml", "job/CreateJobForPosition.xml" })
    public void testAddPosition() {
        Assert.assertNotNull("The created position must not be null.",
                transactionHandler.execute((final Factory factory) -> {
                    final JobRepository jobRepository = factory.findService(JobRepository.class);
                    final Job job = jobRepository.findBy("Scala Developer", JUNIOR);
                    return jobRepository.addPosition(job, 2, 2300);
                }));
    }

    /**
     * Test for findBy method in JpaJobRepository when there exists job with
     * current job title and job degree
     */
    @Test
    @DataSet({ "job/FindByTest-result.xml", CLEAN_DB_XML })
    public void testFindByPrimaryKey() {
        Assert.assertNotNull("The created position must not be null.",
         transactionHandler.execute((final Factory factory) -> {
            final JobRepository jobRepository = factory.findService(JobRepository.class);
            final Job job = jobRepository.findBy("Scala Developer", JUNIOR);
            checkEquals(job, "Scala Developer", "Junior", 1500, 2900);
            return job;
        }));
        
    }

    /**
     * Test for findBy method in JpaJobRepository when there does not exists job
     * with current job title and job degree
     */
    @Test
    @DataSet({ "job/FindByTest-result.xml", CLEAN_DB_XML })
    public void testFindByPrimaryKeyNoResult() {
        Assert.assertNull("The searched job must be null",
                transactionHandler.execute((final Factory factory) -> {
                    final JobRepository jobRepository = factory.findService(JobRepository.class);
                    final Job job = jobRepository.findBy("Scala Develope", JUNIOR);
                    return job;
                }));
    }

    /**
     * Test for findByJobTitle method in JpaJobRepository when there exists job
     * with current job title
     */
    @Test
    @DataSet({ "job/FindByJobTitleTest-result.xml", CLEAN_DB_XML })
    public void testFindByJobTitle() {
        final List<Job> allJobs = transactionHandler.execute((final Factory factory) -> {
            final JobRepository jobRepository = factory.findService(JobRepository.class);
            final List<Job> jobs = jobRepository.findByJobTitle("Scala Developer");
            return jobs;
        });
        final int listSize = 2;
        assertPropertyLenientEquals("jobTitle", Collections.nCopies(listSize, "Scala Developer"),
                allJobs);
    }

    /**
     * Test for findByJobTitle method in JpaJobRepository if there does not
     * exists job with current job title
     */
    @Test
    @DataSet({ "job/FindByJobTitleTest-result.xml", CLEAN_DB_XML })
    public void testFindByJobTitleNoResults() {
        final List<Job> allJobs = transactionHandler.execute((final Factory factory) -> {
            final JobRepository jobRepository = factory.findService(JobRepository.class);
            final List<Job> jobs = jobRepository.findByJobTitle("Scala Develope");
            return jobs;
        });
        assertEquals("wrong jobs size expected 0", 0, allJobs.size());
    }

    /**
     * Test for findPositionBy method in JpaJobRepository when there exists
     * position with current job title and job degree and job level
     */
    @Test
    @DataSet({ "job/FindPositionByTest-result.xml", CLEAN_DB_XML })
    public void testFindPositionByPrimaryKey() {
        Assert.assertNotNull("The created position must not be null.",
        transactionHandler.execute((final Factory factory) -> {
            final JobRepository jobRepository = factory.findService(JobRepository.class);
            final Position actualPosition = jobRepository.findPositionBy("Scala Developer", JUNIOR,
                    4);
            checkEquals(actualPosition, "Scala Developer", "Junior", 4, 1234);
            return actualPosition;
        }));
        
    }

    /**
     * Test for findBy method in JpaJobRepository when there does not exists
     * position with current job title, job degree and job level
     */
    @Test
    @DataSet({ "job/FindPositionByTest-result.xml", CLEAN_DB_XML })
    public void testFindPositionByPrimaryKeyNoResult() {
        Assert.assertNull("The searched position must be null",
                transactionHandler.execute((final Factory factory) -> {
                    final JobRepository jobRepository = factory.findService(JobRepository.class);
                    final Position position = jobRepository.findPositionBy("Scala Develope", JUNIOR,
                            40);
                    return position;
                }));
    }

    /**
     * Test for getAllJobTitles method in JpaJobRepository when there exists
     * jobs
     */
    @Test
    @DataSet({ "job/GetAllJobTitlesTest.xml", CLEAN_DB_XML })
    public void testGetAllJobTitles() {
        final List<String> actualJobs = transactionHandler.execute((final Factory factory) -> {
            final JobRepository jobRepository = factory.findService(JobRepository.class);
            final List<String> jobs = jobRepository.listJobTitles();
            return jobs;
        });
        final List<String> expected = Arrays.asList("Go Developer", "Java Developer",
                "Ruby Developer", "Scala Developer");
        assertThat("job title lists are not matching", actualJobs, is(expected));
        
       
    }

    /**
     * Test for getAllJobTitles method in JpaJobRepository when there does not
     * exists any jobs
     */
    @Test
    @DataSet({ "job/GetAllJobTitlesNoResultsTest.xml", CLEAN_DB_XML })
    public void testGetAllJobTitlesNoResult() {
        final List<String> allJobTitles = transactionHandler.execute((final Factory factory) -> {
            final JobRepository jobRepository = factory.findService(JobRepository.class);
            final List<String> jobTitles = jobRepository.listJobTitles();
            return jobTitles;
        });
        assertEquals("wrong jobTitles size expected 0", 0, allJobTitles.size());
    }

    /**
     * Test for getAllJobDegrees method in JpaJobRepository the given job exists
     */
    @Test
    @DataSet({ "job/GetAllJobDegreesTest.xml", CLEAN_DB_XML })
    public void testGetAllJobDegrees() {

        final List<String> actualDegrees = transactionHandler.execute((final Factory factory) -> {
            final JobRepository jobRepository = factory.findService(JobRepository.class);
            final List<String> degrees = jobRepository.getJobDegrees("Developer");
            return degrees;
        });
        final List<String> expected = Arrays.asList("Junior", "Senior");
        assertThat("job degrees lists are not matching", actualDegrees, is(expected));
    }

    /**
     * Test for getAllJobDegrees method in JpaJobRepository when there are no
     * results
     */
    @Test
    @DataSet({ "job/GetAllJobDegreesNoResultsTest.xml", CLEAN_DB_XML })
    public void testGetAllJobDegreesNoResult() {
        final List<String> allDegrees = transactionHandler.execute((final Factory factory) -> {
            final JobRepository jobRepository = factory.findService(JobRepository.class);
            final List<String> jobDegrees = jobRepository.getJobDegrees("Developer");
            return jobDegrees;
        });
        assertEquals("wrong jobDegrees size expected 0", 0, allDegrees.size());
    }

    /**
     * Test for getAllJobs method in JpaJobRepository when there exists jobs in
     * database.
     */
    @Test
    @DataSet({ "job/FindByJobTitleTest-result.xml", CLEAN_DB_XML })
    public void testFindAllJobs() {
        final List<Job> allJobs = transactionHandler.execute((final Factory factory) -> {
            final JobRepository jobRepository = factory.findService(JobRepository.class);
            final List<Job> jobs = jobRepository.getAllJobs();
            return jobs;
        });
        final int listSize = 4;
        assertEquals("wrong jobs number", listSize, allJobs.size());
    }

    /**
     * Test for getAllJobs method in JpaJobRepository when there not exists jobs
     * in database.
     */
    @Test
    @DataSet({ CLEAN_DB_XML })
    public void testFindAllJobsNoResults() {
        final List<Job> allJobs = transactionHandler.execute((final Factory factory) -> {
            final JobRepository jobRepository = factory.findService(JobRepository.class);
            final List<Job> jobs = jobRepository.getAllJobs();
            return jobs;
        });
        assertEquals("wrong jobs size expected 0", 0, allJobs.size());
    }

    /**
     * Test for getAllPositions method in JpaJobRepository when there exists
     * positions for the given job in database.
     */
    @Test
    @DataSet({ "job/FindPositionsByTest-result.xml", CLEAN_DB_XML })
    public void testFindPositionsBy() {
        final List<Position> allPositions = transactionHandler.execute((final Factory factory) -> {
            final JobRepository jobRepository = factory.findService(JobRepository.class);
            final List<Position> positions = jobRepository.findPositionsBy("Scala Developer",
                    "Junior");
            return positions;
        });
        final int listSize = 3;
        assertEquals("wrong positions number", listSize, allPositions.size());
    }

    /**
     * Test for getAllPositions method in JpaJobRepository when there are no
     * positions for the given job in the database.
     */
    @Test
    @DataSet({ CLEAN_DB_XML })
    public void testFindPositionsByNoResults() {
        final List<Position> allPositions = transactionHandler.execute((final Factory factory) -> {
            final JobRepository jobRepository = factory.findService(JobRepository.class);
            final List<Position> positions = jobRepository.findPositionsBy("Scala Developer",
                    "Junior");
            return positions;
        });
        assertEquals("wrong jobs size expected 0", 0, allPositions.size());
    }

    /**
     * Test for getAllPositions method in JpaJobRepository when there exists
     * positions for the given job in database.
     */
    @Test
    @DataSet({ "job/GetJobLevelsByTest-result.xml", CLEAN_DB_XML })
    public void testGetJobLevelsBy() {
        final List<Integer> jobLevels = transactionHandler.execute((final Factory factory) -> {
            final JobRepository jobRepository = factory.findService(JobRepository.class);
            final List<Integer> levels = jobRepository.getJobLevelsBy("Scala Developer", "Junior");
            return levels;
        });
        final int listSize = 3;
        assertEquals("wrong positions number", listSize, jobLevels.size());
    }

    /**
     * Test for getAllPositions method in JpaJobRepository when there are no
     * positions for the given job in the database.
     */
    @Test
    @DataSet({ CLEAN_DB_XML })
    public void testGetJobLevelsByNoResults() {
        final List<Integer> jobLevels = transactionHandler.execute((final Factory factory) -> {
            final JobRepository jobRepository = factory.findService(JobRepository.class);
            final List<Integer> levels = jobRepository.getJobLevelsBy("Scala Developer", "Junior");
            return levels;
        });
        assertEquals("wrong jobs size expected 0", 0, jobLevels.size());
    }

    /**
     * Test for GetNextPrecedensNumber method in JpaJobRepository when there is
     * next precedence number
     */
    @Test
    @DataSet({ "job/GetNextPrecedensNumber-result.xml", CLEAN_DB_XML })
    public void testGetNextPrecedensNumber() {
        final int nextPrecedensNumber = transactionHandler.execute((final Factory factory) -> {
            final JobRepository jobRepository = factory.findService(JobRepository.class);
            final int precedensNumber = jobRepository.getNextPrecedensNumber(20);
            return precedensNumber;
        });
        assertEquals("wrong precedens number", 40, nextPrecedensNumber);
    }

    /**
     * Test for GetNextPrecedensNumber method in JpaJobRepository when there is
     * not next precedence number
     */
    @Test
    @DataSet({ "job/GetNextPrecedensNumber-result.xml", CLEAN_DB_XML })
    public void testGetNextPrecedensNumberNoResult() {
        final int nextPrecedensNumber = transactionHandler.execute((final Factory factory) -> {
            final JobRepository jobRepository = factory.findService(JobRepository.class);
            final int precedensNumber = jobRepository.getNextPrecedensNumber(50);
            return precedensNumber;
        });
        assertEquals("wrong precedens number", 50, nextPrecedensNumber);
    }

    /**
     * Test for get all allowable positions for current job degree
     */
    @Test
    @DataSet({ "job/GetAllAllowablePositions.xml", CLEAN_DB_XML })
    public void testGetCurrentDegreeAllowablePositions() {
        final List<Position> allAllowablePositions = transactionHandler
                .execute((final Factory factory) -> {
                    final JobRepository jobRepository = factory.findService(JobRepository.class);
                    final List<Position> allowablePositions = jobRepository
                            .findCurrentDegreeAllowablePositions("Java Developer", 2, "Junior");
                    return allowablePositions;
                });
        assertPropertyLenientEquals("positionPK.jobId.jobDegree",
                Collections.nCopies(allAllowablePositions.size(), "Junior"), allAllowablePositions);
    }

    @Test
    @DataSet({ "job/GetAllAllowablePositions.xml", CLEAN_DB_XML })
    public void testGetCurrentDegreeAllowablePositionsNoResult() {
        final List<Position> allAllowablePositions = transactionHandler
                .execute((final Factory factory) -> {
                    final JobRepository jobRepository = factory.findService(JobRepository.class);
                    final List<Position> allowablePositions = jobRepository
                            .findCurrentDegreeAllowablePositions("Java Developer", 10, "Junior");
                    return allowablePositions;
                });
        assertEquals("wrong allAllowablePositions number", 0, allAllowablePositions.size());
    }

    /**
     * Test for get all allowable positions for next job degree
     */
    @Test
    @DataSet({ "job/GetAllAllowablePositions.xml", CLEAN_DB_XML })
    public void testGetNextDegreeAllowablePositions() {
        final List<Position> allAllowablePositions = transactionHandler
                .execute((final Factory factory) -> {
                    final JobRepository jobRepository = factory.findService(JobRepository.class);
                    final List<Position> allowablePositions = jobRepository
                            .findNextDegreeAllowablePositions("Java Developer", 20);
                    return allowablePositions;
                });
        assertPropertyLenientEquals("positionPK.jobId.jobDegree",
                Collections.nCopies(allAllowablePositions.size(), "Senior"), allAllowablePositions);
    }

    /**
     * Test for get all allowable positions for next job degree no result test
     */
    @Test
    @DataSet({ "job/GetAllAllowablePositions.xml", CLEAN_DB_XML })
    public void testGetNextDegreeAllowablePositionsNoResult() {
        final List<Position> allAllowablePositions = transactionHandler
                .execute((final Factory factory) -> {
                    final JobRepository jobRepository = factory.findService(JobRepository.class);
                    final List<Position> allowablePositions = jobRepository
                            .findNextDegreeAllowablePositions("Java Developer", 40);
                    return allowablePositions;
                });
        assertEquals("wrong allAllowablePositions number", 0, allAllowablePositions.size());
    }

    /**
     * Test hashcode consistency of JpaJobID
     */
    @Test
    public void testHashCodeOfJpaJobIDSameObject() {
        final JpaJobID first = new JpaJobID("Dev", "Junior");
        assertEquals("different hashcode for one object:", first.hashCode(), first.hashCode());
    }

    /**
     * Test equals method of JpaJobID with the same object
     */
    @Test
    public void testHashCodeOfJpaJobIDDifferentObjects() {
        final JpaJobID first = new JpaJobID("Dev", "Junior");
        final JpaJobID second = new JpaJobID("Dev", "Junior");
        assertEquals("different hashcode for equal objects:", first.hashCode(), second.hashCode());
    }

    /**
     * Test equals of JpaJobID with two equal objects
     */
    @Test
    public void testEqualsOfJpaJobID() {
        final JpaJobID first = new JpaJobID("Dev", "Junior");
        final JpaJobID second = new JpaJobID("Dev", "Junior");
        assertTrue("the objects are not equal", first.equals(second) && second.equals(first));
    }

    /**
     * Test equals of JpaJobID with two objects of different types
     */
    @Test
    public void testNotEqualOfJpaJobID() {
        final JpaJobID first = new JpaJobID("Dev", "Junior");
        final PositionID second = new PositionID(first, 5);
        assertFalse("the objects are equal", first.equals(second) || second.equals(first));
    }

    /**
     * Test equals of JpaJobID with two unequal objects
     */
    @Test
    public void testEqualsOfJpaJobIDWithDifferentObjects() {
        final JpaJobID first = new JpaJobID("Dev", "Junior");
        final JpaJobID second = new JpaJobID("Dev", "Senior");
        assertFalse("the objects are equal", first.equals(second) || second.equals(first));
    }

    /**
     * Test equals of JpaJobID with one object
     */
    @Test
    public void testEqualsOfJpaJobIDSameObject() {
        final JpaJobID first = new JpaJobID("Dev", "Junior");
        assertEquals("the objects are not equal", first, first);
    }

    private static void checkEquals(final Job job, final String jobTitle, final String jobDegree,
            final double minSalary, final double maxSalary) {
        assertEquals("wrong job title", jobTitle, job.getJobTitle());
        assertEquals("wrong job degree", jobDegree, job.getJobDegree());
        assertEquals("wrong job max Salary", maxSalary, job.getMaxSalary(), DELTA);
        assertEquals("wrong job min Salary", minSalary, job.getMinSalary(), DELTA);
    }

    private static void checkEquals(final Position position, final String jobTitle,
            final String jobDegree, final int jobLevel, final double baseSalary) {
        assertEquals("wrong job title", jobTitle, position.getJob().getJobTitle());
        assertEquals("wrong job degree", jobDegree, position.getJob().getJobDegree());
        assertEquals("wrong job level", jobLevel, position.getJobLevel());
        assertEquals("wrong base salary", baseSalary, position.getBaseSalary(), DELTA);

    }
}
