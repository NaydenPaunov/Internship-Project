package com.dxc.payroll.persistence.jpa.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.dxc.payroll.persistence.domain.Indexation;
import com.dxc.payroll.persistence.domain.Position;

/**
 * JPA implementation of the Indexation entity.
 */
@Entity
@Table(name = "INDEXATION")
@NamedQueries({
    @NamedQuery(name = "qryShowIndexationByJobTitle",
            query = "select i from JpaIndexation i where i.indexationID.positionId.jobId.jobTitle  "
                    + "= :jobTitle ORDER BY  i.indexationID.dateOfIndexation DESC, "
                    + "i.position.job.precedenceNumber ASC, "
                    + "i.indexationID.positionId.jobLevel ASC "),
    @NamedQuery(name = "qryGetAllIndexations",
    query = "select i from JpaIndexation i ORDER BY i.indexationID.dateOfIndexation DESC") })

public class JpaIndexation implements Indexation {

    /**
     * Private field indexationID of the type IndexationID - Embedded class for
     * the Indexation primary key.
     */
    @EmbeddedId
    private IndexationID indexationID;

    /**
     * Private field position. Has many to one relationship with the JpaPosition
     * class.
     */
    @MapsId("positionId")
    @ManyToOne(targetEntity = JpaPosition.class)
    @JoinColumns({ @JoinColumn(name = "JOB_TITLE", referencedColumnName = "JOB_TITLE"),
        @JoinColumn(name = "JOB_DEGREE", referencedColumnName = "JOB_DEGREE"),
        @JoinColumn(name = "JOB_LEVEL", referencedColumnName = "JOB_LEVEL") })
    private JpaPosition position;

    /**
     * Private field percentage of type double - representation of the
     * PERCENTAGE column in the INDEXATION table of the database.
     */
    @Column(name = "PERCENTAGE")
    private double percentage;

    /**
     * Needed by JPA
     */
    protected JpaIndexation() {
    }

    /**
     * Constructor of the JpaIndexation class
     *
     * @param percentage
     *            must not be null
     * @param dateOfIndexation
     *            must not be null
     * @param position
     *            must not be null
     */
    public JpaIndexation(final Position position, final double percentage,
            final LocalDate dateOfIndexation) {
        this.indexationID = new IndexationID(
                new PositionID(new JpaJobID(position.getJob().getJobTitle(),
                        position.getJob().getJobDegree()), position.getJobLevel()),
                dateOfIndexation);
        this.position = (JpaPosition) position;
        this.percentage = percentage;
    }

    /**
     * Gets the percentage of indexation.
     */
    @Override
    public double getPercentage() {
        return percentage;
    }

    /**
     * Gets the date of indexation.
     */
    @Override
    public LocalDate getDateOfIndexation() {
        return indexationID.getDateOfIndexation();
    }

    /**
     * Gets the position.
     */
    @Override
    public Position getPosition() {
        return position;
    }

}
