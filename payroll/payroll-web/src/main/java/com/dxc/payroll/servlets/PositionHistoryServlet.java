package com.dxc.payroll.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dxc.payroll.common.Factory;
import com.dxc.payroll.constants.PayrollConstants;
import com.dxc.payroll.services.PositionHistoryService;
import com.dxc.payroll.services.dto.PositionHistoryForEmployeeDTO;

/**
 * Servlet implementation class PositionHistoryServlet
 *
 */
@SuppressWarnings("nls")
public final class PositionHistoryServlet extends HttpServlet {
    private static final long serialVersionUID = 7490209724949528397L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        final String employeeUCN = request.getParameter(PayrollConstants.EMPLOYEE_UCN_PARAM);
        if (employeeUCN == null || employeeUCN.isEmpty()) {
            request.setAttribute(PayrollConstants.ERROR_MESSAGE,
                    PayrollConstants.EMPLOYEE_UCN_NO_PARAMETHER);
            request.getRequestDispatcher(PayrollConstants.ERROR_PAGE).forward(request, response);
        }
        else {
            final ServletContext ctx = request.getServletContext();
            final Factory factory = (Factory) ctx.getAttribute(PayrollConstants.SERVICE_FACTORY);
            final PositionHistoryService positionHistoryService = factory
                    .findService(PositionHistoryService.class);
            final PositionHistoryForEmployeeDTO positionHistory = positionHistoryService
                    .findPositionHistory(employeeUCN);
            if (positionHistory == null) {
                request.setAttribute(PayrollConstants.ERROR_MESSAGE,
                        PayrollConstants.NO_RESULTS_MESSAGE);
                request.getRequestDispatcher(PayrollConstants.ERROR_PAGE).forward(request,
                        response);
            }
            else {
                request.setAttribute("name", positionHistory.getEmployeeName());
                request.setAttribute("ucn", positionHistory.getEmployeeUCN());
                request.setAttribute("pHistory", positionHistory.getPositionHistory());
                request.getRequestDispatcher(PayrollConstants.POSITION_HISTORY_JSP).forward(request,
                        response);
            }
        }
    }

}
