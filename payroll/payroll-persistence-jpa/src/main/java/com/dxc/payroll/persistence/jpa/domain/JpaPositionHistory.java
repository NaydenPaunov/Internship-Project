package com.dxc.payroll.persistence.jpa.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.dxc.payroll.persistence.domain.PositionHistory;

/**
 * Position History - Entity
 *
 * @author dtanev
 */
@Entity
@Table(name = "POSITION_HISTORY")
@IdClass(value = IdPositionHistory.class)
@NamedQueries({ @NamedQuery(name = "qryFindPositionHistoryByUCN",
        query = "select pHistory from JpaPositionHistory pHistory where pHistory.UCN = :USN") })
public class JpaPositionHistory implements PositionHistory {

    @Id
    @ManyToOne(targetEntity = JpaEmployee.class)
    @PrimaryKeyJoinColumn(name = "UCN")
    private String UCN;

    @Id
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "BASE_SALARY")
    private BigDecimal baseSalary;

    // @ManyToOne(targetEntity = JpaPosition.class)
    // @JoinColumn(name = "JOB_TITLE")
    @Column(name = "JOB_TITLE")
    private String jobTitle;

    // @ManyToOne(targetEntity = JpaPosition.class)
    // @JoinColumn(name = "JOB_LEVEL")
    @Column(name = "JOB_LEVEL")
    private Integer jobLevel;

    /**
     * Needed by JPA
     */
    protected JpaPositionHistory() {
    }

    /**
     * @param UCN
     * @param startDate
     * @param endDate
     * @param baseSalary
     * @param jobTitle
     * @param jobLevel
     */
    public JpaPositionHistory(final String UCN, final Date startDate, final Date endDate,
            final BigDecimal baseSalary, final String jobTitle, final Integer jobLevel) {
        assert UCN != null && startDate != null && endDate != null && baseSalary != null
                && jobTitle != null && jobLevel != null;
        this.UCN = UCN;
        this.startDate = startDate;
        this.endDate = endDate;
        this.baseSalary = baseSalary;
        this.jobTitle = jobTitle;
        this.jobLevel = jobLevel;
    }

    /**
     * getter for UCN
     */
    @Override
    public String getUCN() {
        return UCN;
    }

    /**
     * getter for startDate
     */
    @Override
    public Date getStartDate() {
        return startDate;
    }

    /**
     * getter for endDate
     */
    @Override
    public Date getEndDate() {
        return endDate;
    }

    /**
     * getter for baseSalary
     */
    @Override
    public BigDecimal getBaseSalary() {
        return baseSalary;
    }

    /**
     * getter for jobTitle
     */
    @Override
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * getter for jobLevel
     */
    @Override
    public Integer getJobLevel() {
        return jobLevel;
    }

}

final class IdPositionHistory implements Serializable {
    private static final long serialVersionUID = 1L;

    private String UCN;
    private Date startDate;

    /**
     * equals for Position History primary key
     */
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof JpaPositionHistory)) {
            return false;
        }
        final JpaPositionHistory positionHistory = (JpaPositionHistory) o;
        return positionHistory.getUCN().equals(this.UCN)
                && positionHistory.getStartDate().equals(this.startDate);
    }

    /**
     * hash code for Position History primary key
     */
    @Override
    public int hashCode() {
        return UCN.hashCode() + startDate.hashCode();
    }
}
