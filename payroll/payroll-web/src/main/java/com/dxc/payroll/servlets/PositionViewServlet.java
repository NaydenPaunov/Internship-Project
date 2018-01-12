package com.dxc.payroll.servlets;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dxc.payroll.common.Factory;
import com.dxc.payroll.constants.PayrollConstants;
import com.dxc.payroll.services.PositionService;
import com.dxc.payroll.services.dto.EmployeePositionDTO;

/**
 * 
 * Servlet implementation class PositionViewServlet
 * 
 */
@SuppressWarnings("nls")
public class PositionViewServlet extends HttpServlet {
    /**
     * serialVersionUID initialization
     */
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     * 
     *      Method get parameter employeeUCN If current parameter is null send
     *      you to errorPage with errorMessage. If there is no employee with
     *      this UCN again send you to errorPage with error message. Else show
     *      you position information for this employee - name , job title , base
     *      salary and job level
     * 
     */
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        final String employeeUCN = request.getParameter(PayrollConstants.EMPLOYEE_UCN_PARAM);
        if (employeeUCN == null) {
            response.sendRedirect("/payroll/home");
            return;
        }
        else {
            final ServletContext ctx = request.getServletContext();
            final Factory factory = (Factory) ctx
                    .getAttribute(PayrollConstants.SERVICE_FACTORY);
            final PositionService positionService = factory.findService(PositionService.class);
            final EmployeePositionDTO employeePositionDTO = positionService
                    .getPositionByEmployeeUCN(employeeUCN);

            if (employeePositionDTO == null) {
                forwardToErrorPage(PayrollConstants.NO_RESULTS_MESSAGE, request, response);
                return;
            }
            else {
                final Map<String, Object> viewModel = new HashMap<>();
                viewModel.put(PayrollConstants.EMPLOYEE_NAME_PARAM,
                        employeePositionDTO.getEmployeeName());
                viewModel.put(PayrollConstants.POSITION_DTO_PARAM, employeePositionDTO);
                viewModel.put(PayrollConstants.EMPLOYEE_UCN_PARAM, employeeUCN);
                request.setAttribute("viewModel",Collections.unmodifiableMap(viewModel));
                requestDispatcher = request
                        .getRequestDispatcher(PayrollConstants.POSITION_INFORMATION_JSP);
                requestDispatcher.forward(request, response);
            }
        }
    }

    private static void forwardToErrorPage(final String errorMessage,
            final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        request.setAttribute(PayrollConstants.ERROR_MESSAGE, errorMessage);
        requestDispatcher = request.getRequestDispatcher(PayrollConstants.ERROR_PAGE);
        requestDispatcher.forward(request, response);

    }
}
