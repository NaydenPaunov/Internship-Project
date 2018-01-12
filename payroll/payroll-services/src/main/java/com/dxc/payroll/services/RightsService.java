package com.dxc.payroll.services;

import java.util.List;

import com.dxc.payroll.services.dto.RightsDTO;

/**
 * The service that returns rights DTOs
 *
 */
public interface RightsService {
    /**
     * @param ucn
     *            the ucn of a user
     * @return a list of the rights the user with the given ucn has
     */
    List<RightsDTO> getRightsByUCN(String ucn);

    /**
     * @return a list of DTO containing all the rights
     */
    List<RightsDTO> getAllRights();
}
