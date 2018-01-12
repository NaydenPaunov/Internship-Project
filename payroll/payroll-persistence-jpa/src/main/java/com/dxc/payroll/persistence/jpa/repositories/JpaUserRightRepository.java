package com.dxc.payroll.persistence.jpa.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import com.dxc.payroll.persistence.domain.UserRight;
import com.dxc.payroll.persistence.jpa.domain.JpaUserRight;
import com.dxc.payroll.persistence.jpa.utils.Utils;
import com.dxc.payroll.persistence.repositories.UserRightRepository;

/**
 * A repository for UserRight obejcts with methods for creation and finding user
 * rights
 *
 */
public class JpaUserRightRepository implements UserRightRepository {

    /**
     * the maximum length of the role in the database (fuck this shit)
     */
    private static final int USER_RIGHT_NAME_LENGTH = 32;

    /**
     * A JPQL query that return all user rights found in the base
     */
    private static final String QRY_FIND_ALL_USER_RIGHTS = "qryFindAllUserRights"; //$NON-NLS-1$

    /**
     * entity manager
     */
    private final EntityManager entityManager;

    /**
     * @param entityManager
     */
    public JpaUserRightRepository(final EntityManager entityManager) {
        assert entityManager != null;
        this.entityManager = entityManager;
    }

    /**
     * A method to return a list of all the rights
     *
     * @return list of all rights
     */
    @Override
    public List<UserRight> getAllRights() {
        return entityManager.createNamedQuery(QRY_FIND_ALL_USER_RIGHTS, UserRight.class)
                .getResultList();
    }

    /**
     * A method that finds and returns a right with the given name
     *
     * @return a user right with the given name
     */
    @Override
    public UserRight findRightByName(final String userRightName) {
        return entityManager.find(JpaUserRight.class,
                Utils.padRight(userRightName, USER_RIGHT_NAME_LENGTH));
    }
}
