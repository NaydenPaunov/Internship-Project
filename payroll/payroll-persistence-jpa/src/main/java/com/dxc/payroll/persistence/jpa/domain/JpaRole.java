package com.dxc.payroll.persistence.jpa.domain;

import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.dxc.payroll.persistence.domain.Employee;
import com.dxc.payroll.persistence.domain.Role;
import com.dxc.payroll.persistence.domain.UserRight;
import com.dxc.payroll.persistence.jpa.constants.PayrollPersistenceConstants;
import com.dxc.payroll.persistence.jpa.utils.Utils;

/**
 * JPA class for the Role Entity
 * A role has two characteristics: a name and a description
 * Every Role has one or more User Rights
 * there is a many to many relationship between Role and User Rights 
 */
/**
 * @author tdyakov
 *
 */
@Entity
@Table(name = "ROLE")
@NamedQueries({ @NamedQuery(name = "qryFindAllRoles", query = "select c from JpaRole c") })
public class JpaRole implements Role {

    /**
     * name of role (ex. "Manager")
     */
    @Id
    @Column(name = "ROLE_NAME")
    private String roleName;

    /**
     * description of the role (ex. "Can see his employees")
     */
    @Column(name = "DESCRIPTION")
    private String description;

    /**
     * list of user rights this role has
     */
    @ManyToMany(targetEntity = JpaUserRight.class)
    @JoinTable(name = "ROLE_RIGHT", joinColumns = @JoinColumn(name = "ROLE_NAME"),
            inverseJoinColumns = @JoinColumn(name = "TYPE_OF_RIGHT"))
    private List<UserRight> userRights;

    /**
     * a list of the employees that have this role
     */
    @OneToMany(targetEntity = JpaEmployee.class, mappedBy = "role")
    private List<Employee> employees;

    /**
     * Needed by JPA
     */
    protected JpaRole() {
    }

    /**
     * @param roleName
     *            name of the role
     * @param description
     *            description of the role
     */
    public JpaRole(final String roleName, final String description) {
        assert roleName != null && description != null;
        this.roleName = Utils.padRight(roleName, PayrollPersistenceConstants.ROLE_NAME_LENGTH);
        this.description = description;
    }

    /**
     * @return the name of the role (eg. "Manager")
     */
    @Override
    public String getRoleName() {
        return this.roleName.trim();
    }

    /**
     * @return the description of the role
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * @return the rights that this role has
     */
    @Override
    public List<UserRight> getUserRights() {
        return Collections.unmodifiableList(this.userRights);
    }

    /**
     * @return a list of employees that have this role
     */
    public List<Employee> getEmployeesWithThisRole() {
        return Collections.unmodifiableList(employees);
    }

    /**
     * Replaces the assigned rights to the role with the given user rights
     * 
     * @param userRights
     *            the rights that will be assigned to the role
     */

    @Override
    public void updateUserRights(List<UserRight> updatedUserRights) {
        this.userRights = Collections.unmodifiableList(updatedUserRights);
    }

}
