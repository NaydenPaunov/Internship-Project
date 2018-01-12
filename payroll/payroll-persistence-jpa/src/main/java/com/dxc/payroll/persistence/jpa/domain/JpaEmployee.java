package com.dxc.payroll.persistence.jpa.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.dxc.payroll.persistence.domain.Employee;
import com.dxc.payroll.persistence.domain.Paycheck;
import com.dxc.payroll.persistence.domain.Position;
import com.dxc.payroll.persistence.domain.PositionPeriod;
import com.dxc.payroll.persistence.domain.Role;

/**
 * Employee entity.
 */
@Entity
@Table(name = "EMPLOYEE")
@NamedQueries({
        @NamedQuery(name = "qryFindEmployeeByName",
                query = "select e from JpaEmployee e where e.name = :name"),
        @NamedQuery(name = "qryFindEmployeesByTeamLeadUCN",
                query = "select e from JpaEmployee e where e.teamLeadUCN =:teamLeadUCN"),
        @NamedQuery(name = "qryGetAllEmployees",
                query = "select e from JpaEmployee e ORDER BY e.name") })
public class JpaEmployee implements Employee {
    /**
     * private field - UCN of the employee
     */
    @Id
    private String ucn;

    /**
     * private field - name of the employee
     */
    private String name;

    /**
     * private field - userName of the employee
     */
    @Column(name = "USER_NAME")
    private String userName;

    /**
     * private field - password of the employee
     */
    private String password;

    /**
     * Previous work experience.
     */
    @Column(name = "PREVIOUS_WORK_EXPERIENCE")
    private int previousWorkExperience;

    /**
     * contract type
     */
    @Column(name = "CONTRACT_TYPE")
    private String contractType;

    /**
     * Work hours.
     */
    @Column(name = "WORK_HOURS")
    private int workHours;

    /**
     * Team lead ucn.
     */
    @Column(name = "TEAM_LEAD_UCN")
    private String teamLeadUCN;

    /**
     * role of the employee
     */
    @ManyToOne(targetEntity = JpaRole.class)
    @JoinColumn(name = "ROLE_NAME")
    private Role role;

    /**
     * paychecks associated with the employee
     */
    @OneToMany(mappedBy = "employee", targetEntity = JpaPaycheck.class)
    @OrderBy("DATE_OF_PAYCHECK DESC")
    private List<Paycheck> paychecks = new ArrayList<>();

    /**
     * position history for the employee
     */
    @OneToMany(mappedBy = "employee", targetEntity = JpaPositionPeriod.class)
    @OrderBy("START_DATE DESC")
    private List<PositionPeriod> positionHistory = new ArrayList<>();

    /**
     * Needed by JPA
     */
    protected JpaEmployee() {
    }

    /**
     * @param ucn
     * @param name
     * @param userName
     * @param password
     * @param contractType
     */
    public JpaEmployee(final String ucn, final String name, final String userName,
            final String password, final String contractType) {
        assert ucn != null && name != null && contractType != null;
        this.ucn = ucn;
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.contractType = contractType;
    }

    /**
     * Return list of position history.
     */
    @Override
    public List<PositionPeriod> getPositionHistory() {
        return Collections.unmodifiableList(positionHistory);
    }

    /**
     * Get Ucn.
     */
    @Override
    public String getUCN() {
        return ucn;
    }

    /**
     * Get name.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Get username.
     */
    @Override
    public String getUserName() {
        return userName;
    }

    /**
     * Get password.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Get contract type.
     */
    @Override
    public String getContractType() {
        return contractType;
    }

    /**
     * Get paychecks.
     */
    @Override
    public List<Paycheck> getPaychecks() {
        return Collections.unmodifiableList(paychecks);
    }

    /**
     * Get previous work experience
     */
    @Override
    public int getPreviousWorkExperience() {
        return previousWorkExperience;
    }

    /**
     * Get workHours.
     */
    @Override
    public int getWorkHours() {
        return workHours;
    }

    /**
     * Get team lead ucn.
     */
    @Override
    public String getTeamLeadUCN() {
        return teamLeadUCN;
    }

    /**
     * Get role.
     */
    @Override
    public Role getRole() {
        return role;
    }

    /**
     * Get base salary.
     */
    @Override
    public double getBaseSalary() {
        return this.positionHistory.get(0).getBaseSalary();
    }

    @Override
    public Position getCurrentPosition() {
        return positionHistory.get(0).getPosition();
    }
}
