package com.dxc.payroll.persistence.jpa.domain;

import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.dxc.payroll.persistence.domain.Role;
import com.dxc.payroll.persistence.domain.UserRight;

/**
 * UserRight entity.
 */
/**
 * @author tdyakov
 *
 */

@Entity
@Table(name = "USER_RIGHT")
@NamedQueries({
    @NamedQuery(name = "qryFindAllUserRights", query = "select e from JpaUserRight e") })
public class JpaUserRight implements UserRight {

    /**
     * type of the right....
     */
    @Id
    @Column(name = "TYPE_OF_RIGHT")
    private String typeOfRight;

    /**
     * description of the right (ex. "can see his employees")
     */
    @Column(name = "DESCRIPTION")
    private String description;

    /**
     * list of roles that have this user right
     */
    @ManyToMany(targetEntity = JpaRole.class, mappedBy = "userRights")
    private List<Role> roles;

    /**
     * Needed by JPA
     */
    protected JpaUserRight() {
    }

    /**
     * @param typeOfRight
     *            the type of right of the user right
     * @param description
     *            description of the user right
     */
    public JpaUserRight(final String typeOfRight, final String description) {
        assert typeOfRight != null && description != null;
        this.typeOfRight = typeOfRight;
        this.description = description;
    }

    /**
     * @return the type of the right
     */
    @Override
    public String getTypeOfRight() {
        return typeOfRight;
    }

    /**
     * Method to return description of right (ex. "can see his employees")
     *
     * @return the description of the right
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * a method to get all roles that have this right assigned to them
     *
     * @return a list of the roles with this right
     */
    public List<Role> getRoles() {
        return Collections.unmodifiableList(roles);
    }

}
