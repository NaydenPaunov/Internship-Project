package com.dxc.payroll.service.implementation;

import java.util.ArrayList;
import java.util.List;

import com.dxc.payroll.persistence.domain.UserRight;
import com.dxc.payroll.persistence.repositories.EmployeeRepository;
import com.dxc.payroll.persistence.repositories.UserRightRepository;
import com.dxc.payroll.services.dto.RightsDTO;

/**
 * This class contains static methods for returning DTOs that are used in the
 * rights service
 *
 */
public final class RightsServiceLogic {

    /**
     * private constructor
     */
    private RightsServiceLogic() {
        /**
         * this class contains only static methods and should not be
         * instantiated
         */
    }

    /**
     * @param ucn
     * @param repository
     * @return
     */
    public static List<RightsDTO> getRights(final String ucn, final EmployeeRepository repository) {
        return userRightListToDTOList(repository.findByUCN(ucn).getRole().getUserRights());
    }

    /**
     * @param repository
     * @return
     */
    public static List<RightsDTO> getAllRights(final UserRightRepository repository) {
        return userRightListToDTOList(repository.getAllRights());
    }

    /**
     * This method takes a list of userRights a returns a list of DTOs that hold
     * those user rights
     *
     * @param userRightList
     *            list of user rights
     * @return a list of DTO objects that hold the user rights
     */
    private static List<RightsDTO> userRightListToDTOList(final List<UserRight> userRightList) {
        final List<RightsDTO> list = new ArrayList<>();
        for (final UserRight ur : userRightList) {
            list.add(new RightsDTO(ur.getTypeOfRight(), ur.getDescription()));
        }
        return list;
    }
}
