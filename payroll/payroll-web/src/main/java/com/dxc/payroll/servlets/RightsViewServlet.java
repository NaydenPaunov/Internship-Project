package com.dxc.payroll.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dxc.payroll.common.Factory;
import com.dxc.payroll.constants.PayrollConstants;
import com.dxc.payroll.services.EmployeeService;
import com.dxc.payroll.services.RightsService;
import com.dxc.payroll.services.dto.EmployeeDTO;
import com.dxc.payroll.services.dto.RightsDTO;

/**
 * A servlet used for the rights view in our web app
 *
 * @author tdyakov
 *
 */
public class RightsViewServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 6844884503660900391L;

    /**
     * Creates a new servlet for the rights view page
     */
    public RightsViewServlet() {
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

        final String ucn = request.getParameter("emplId");
        if (ucn == null) {
            request.setAttribute(PayrollConstants.ERROR_MESSAGE, "missingParam");
            final RequestDispatcher requestDispatcher = request
                    .getRequestDispatcher(PayrollConstants.ERROR_PAGE);
            requestDispatcher.forward(request, response);
            return;
        }
        final EmployeeDTO employee = factory.findService(EmployeeService.class).findEmployee(ucn);
        final List<RightsDTO> rights = factory.findService(RightsService.class).getRightsByUCN(ucn);

        final Map<String, Object> viewModel = new HashMap<>();
        viewModel.put("employee", employee);
        viewModel.put("rights", rights);
        request.setAttribute("viewModel", viewModel);

        final RequestDispatcher requestDispatcher = request
                .getRequestDispatcher(PayrollConstants.RIGHTS_JSP);
        requestDispatcher.forward(request, response);
    }

}
