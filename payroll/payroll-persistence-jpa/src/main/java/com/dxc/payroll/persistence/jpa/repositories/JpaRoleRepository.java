package com.dxc.payroll.persistence.jpa.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import com.dxc.payroll.persistence.jpa.utils.Utils;
import com.dxc.payroll.persistence.domain.Role;
import com.dxc.payroll.persistence.jpa.constants.PayrollPersistenceConstants;
import com.dxc.payroll.persistence.jpa.domain.JpaRole;
import com.dxc.payroll.persistence.repositories.RoleRepository;

/**
 * JpaRoleRepository
 *
 */
public class JpaRoleRepository implements RoleRepository {

    /**
     * entity manager needed to create role entities
     */
    private final EntityManager entityManager;

    /**
     * A JPQL query that returns all roles found in the database
     */
    @SuppressWarnings("nls")
    private static final String QRY_FIND_ALL_ROLES = "qryFindAllRoles";

    /**
     * @param entityManager
     */
    public JpaRoleRepository(final EntityManager entityManager) {
        assert entityManager != null;
        this.entityManager = entityManager;
    }

    /**
     * @param roleName
     *            - name of the role
     * @param description
     *            - description of the role This method creates a new Role
     *            object and persists it in the databasea
     * @return a new role object with with given name and description
     */
    @Override
    public Role createRole(final String roleName, final String description) {
        final Role role = new JpaRole(roleName, description);
        entityManager.persist(role);
        return role;
    }

    /**
     * @param name
     *            - name of the role to find
     * @return the role with the given name
     */
    @Override
    public Role findRoleByName(String name) {
        return entityManager.find(JpaRole.class,
                Utils.padRight(name, PayrollPersistenceConstants.ROLE_NAME_LENGTH));
    }

    /**
     * Returns a list of all the roles
     *
     * @return a list of all roles
     */
    @Override
    public List<Role> getAllRoles() {
        return entityManager.createNamedQuery(QRY_FIND_ALL_ROLES, Role.class).getResultList();
    }

}
