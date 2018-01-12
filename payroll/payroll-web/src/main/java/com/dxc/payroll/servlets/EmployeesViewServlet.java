package com.dxc.payroll.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dxc.payroll.common.Factory;
import com.dxc.payroll.constants.PayrollConstants;
import com.dxc.payroll.services.EmployeeService;
import com.dxc.payroll.services.dto.EmployeeDTO;

/**
 *
 */
public class EmployeesViewServlet extends HttpServlet {
    /**
     *
     * generated serialVersionUID
     *
     */
    private static final long serialVersionUID = -4568483161840036770L;

    /**
     *
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     *
     */
    @SuppressWarnings("nls")
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        final ServletContext ctx = request.getServletContext();
        final Factory factory = (Factory) ctx.getAttribute(PayrollConstants.SERVICE_FACTORY);
        final EmployeeService employeeService = factory.findService(EmployeeService.class);

        final List<EmployeeDTO> employees = employeeService.getAllEmployees();

        if (employees.isEmpty()) {
            processError(request, response);
            return;
        }

        request.setAttribute("employees", employees);
        request.getRequestDispatcher(PayrollConstants.EMPLOYEES_JSP).forward(request, response);
    }

    private static void processError(final HttpServletRequest request,
            final HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(PayrollConstants.ERROR_MESSAGE,
                PayrollConstants.NO_RECORDS_ERROR_MESSAGE);
        request.getRequestDispatcher(PayrollConstants.JOB_JSP).forward(request, response);
    }

}
