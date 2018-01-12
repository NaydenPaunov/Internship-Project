package com.dxc.payroll.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dxc.payroll.common.Factory;
import com.dxc.payroll.constants.PayrollConstants;
import com.dxc.payroll.services.RoleService;
import com.dxc.payroll.services.dto.RoleMappingDTO;

/**
 * A servlet for the page that is used to to modify the rights of a given role
 *
 * @author tdyakov
 *
 */
public class RoleRightsChangeServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = -8657198335330962330L;

    /**
     * Creates a new servlet used for the change of the rights of a role
     */
    public RoleRightsChangeServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @SuppressWarnings("nls")
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        final ServletContext ctx = request.getServletContext();
        final Factory factory = (Factory) ctx.getAttribute(PayrollConstants.SERVICE_FACTORY);

        final RoleService roleService = factory.findService(RoleService.class);
        final List<RoleMappingDTO> roleRightStateMapping = roleService.getRoleRightStateMappings();
        final Map<String, Object> viewModel = new HashMap<>();
        viewModel.put("mapping", roleRightStateMapping);
        request.setAttribute("viewModel", viewModel);

        final RequestDispatcher rd = request
                .getRequestDispatcher(PayrollConstants.RIGHTS_CHANGE_JSP);
        rd.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        final ServletContext ctx = request.getServletContext();
        final Factory factory = (Factory) ctx.getAttribute(PayrollConstants.SERVICE_FACTORY);
        final Enumeration<String> parameterNames = request.getParameterNames();

        final Map<String, List<String>> roleNameToListOfRightNames = new ConcurrentHashMap<>();

        while (parameterNames.hasMoreElements()) {
            final String parameterName = parameterNames.nextElement();
            final String[] tokens = parameterName.split(PayrollConstants.PARAM_NAME_SEPARATOR);
            final String roleName = tokens[0].trim();
            final String rightName = tokens[1].trim();
            List<String> rightNames = roleNameToListOfRightNames.get(roleName);
            if (rightNames == null) {
                rightNames = new ArrayList<>();
            }
            rightNames.add(rightName);
            roleNameToListOfRightNames.put(roleName, rightNames);
        }

        factory.findService(RoleService.class).updateRightsOnAllRoles(roleNameToListOfRightNames);
        final RequestDispatcher requestDispatcher = request
                .getRequestDispatcher(PayrollConstants.RIGHTS_CHANGE_SUCCESS_JSP);
        requestDispatcher.forward(request, response);
    }

}
