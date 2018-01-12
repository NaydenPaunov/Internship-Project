package com.dxc.payroll.persistence.jpa.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.dxc.payroll.persistence.domain.Indexation;
import com.dxc.payroll.persistence.domain.Job;
import com.dxc.payroll.persistence.domain.Position;
import com.dxc.payroll.persistence.domain.PositionPeriod;

/**
 * The Class JpaPosition implementation 
 * of Position.
 * 
 */
@Entity
@Table(name = "POSITION")
@NamedQueries({
        @NamedQuery(name = "qryGetAllPositionsForJob",
                query = "SELECT p FROM JpaPosition p "
                        + "WHERE p.job.jobId = :jobId ORDER BY p.positionPK.jobLevel"),
        @NamedQuery(name = "qryGetAllLevelsForJob",
                query = "SELECT p.positionPK.jobLevel FROM JpaPosition p"
                        + " WHERE p.job.jobId = :jobId ORDER BY p.positionPK.jobLevel"),

        @NamedQuery(name = "qryFindCurentDegreeAllowablePositions",
                query = "SELECT p FROM JpaPosition p WHERE p.job.jobId = :jobId AND "
                        + "p.positionPK.jobLevel > :jobLevel ORDER BY p.positionPK.jobLevel"),

        @NamedQuery(name = "qryFindNextDegreeAllowablePositions",
                query = "SELECT p FROM JpaPosition p WHERE "
                        + "p.job.jobId.jobTitle = :jobTitle AND"
                        + " p.job.precedenceNumber = :precedenceNumber"
                        + " ORDER BY p.job.precedenceNumber"),

        @NamedQuery(name = "qryGetNextPrecedenceNumber",
                query = "SELECT p.job.precedenceNumber FROM JpaPosition p WHERE "
                        + "p.job.precedenceNumber > :precedenceNumber "
                        + "ORDER BY p.job.precedenceNumber")

})
public class JpaPosition implements Position {

    /**
     * Private field - positionPk. The PK of Position
     * (JOB_TITLE, JOB_DEGREE,JOB_LEVEL)
     * 
     */
    @EmbeddedId
    private PositionID positionPK;

    /**
     * Private field job. Position has many to one 
     * relation with Job. The primary key of 
     * Job(JOB_TITLE, JOB_DEGREE) is foreign 
     * key in Position.
     * 
     */
    @MapsId("jobId")
    @JoinColumns({ @JoinColumn(name = "JOB_TITLE", referencedColumnName = "JOB_TITLE"),
            @JoinColumn(name = "JOB_DEGREE", referencedColumnName = "JOB_DEGREE") })
    @ManyToOne(targetEntity = JpaJob.class)
    private JpaJob job;

    /**
     * Private field - baseSalary of type double, 
     * corresponding to the BASE_SALARY 
     * column in the database.
     * 
     */
    @Column(name = "BASE_SALARY")
    private double baseSalary;

    /**
     * The List of indexations.
     * 
     */
    @OneToMany(targetEntity = JpaIndexation.class, mappedBy = "position")
    private List<Indexation> indexations = new ArrayList<>();

    /**
     * The List of position periods.
     */
    @OneToMany(targetEntity = JpaPositionPeriod.class, mappedBy = "position")
    private List<PositionPeriod> positionPeriod = new ArrayList<>();

    /**
     * Constructor Needed by JPA
     * 
     */
    public JpaPosition() {
    }

    /**
     * Constructor for the JpaPosition class.
     *
     * @param job
     *            job must not be null
     * @param jobLevel
     *            job level must not be null
     * @param baseSalary
     *            baseSalary must not be null
     * 
     */
    public JpaPosition(final Job job, final int jobLevel, final double baseSalary) {
        assert job != null;
        this.positionPK = new PositionID(new JpaJobID(job.getJobTitle(), job.getJobDegree()),
                jobLevel);
        this.job = (JpaJob) job;
        this.baseSalary = baseSalary;
    }

    /**
     * @see com.dxc.payroll.persistence.domain.Position#getJob()
     * 
     */
    @Override
    public Job getJob() {
        return job;
    }

    /**
     * Gets the job level.
     *
     * @return job level of type int
     * 
     */
    @Override
    public int getJobLevel() {
        return positionPK.getJobLevel();
    }

    /**
     * Gets the base salary.
     *
     * @return Base Salary of type double
     */
    @Override
    public double getBaseSalary() {
        return baseSalary;
    }

    /**
     * Gets the position history.
     * 
     * @return The List of position periods.
     */
    public List<PositionPeriod> getPositionPeriod() {
        return Collections.unmodifiableList(positionPeriod);
    }

    /**
     * Gets the indexations.
     * 
     * @return The list of indexations.
     */
    public List<Indexation> getIndexations() {
        return Collections.unmodifiableList(indexations);
    }

}
