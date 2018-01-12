package com.dxc.payroll.servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dxc.payroll.common.Factory;
import com.dxc.payroll.constants.PayrollConstants;
import com.dxc.payroll.service.exceptions.EmployeeNotFoundException;
import com.dxc.payroll.services.EmployeeService;
import com.dxc.payroll.services.dto.EmployeeDTO;

/**
 * Employee servlet view.
 */
public class EmployeeServlet extends HttpServlet {
    private final static transient Logger LOGGER = Logger
            .getLogger(EmployeeServlet.class.getName());

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    @SuppressWarnings("nls")
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        final String ucn = request.getParameter("ucn");
        if (ucn == null) {
            forwardToErrorPage(request, response, "Invalid argument.");
        }
        else {
            EmployeeDTO employee = null;
            try {
                employee = findEmployeeByUCN(request, ucn);
            }
            catch (EmployeeNotFoundException ex) {
                LOGGER.log(Level.WARNING, ex.getMessage());
                LOGGER.log(Level.WARNING, ex.getStackTrace().toString());
                forwardToErrorPage(request, response, ex.getMessage());
                return;
            }

            forwardEmployeeToJSP(request, response, employee);
        }
    }

    /**
     * This method forward to employee.jsp
     *
     * @param request
     * @param response
     * @param employee
     * @throws ServletException
     * @throws IOException
     */
    @SuppressWarnings("nls")
    private static void forwardEmployeeToJSP(final HttpServletRequest request,
            final HttpServletResponse response, final EmployeeDTO employee)
            throws ServletException, IOException {
        request.setAttribute("employee", employee);
        final RequestDispatcher requestDispatcher = request
                .getRequestDispatcher(PayrollConstants.EMPLOYEE_JSP);
        requestDispatcher.forward(request, response);
    }

    /**
     * This method find employee by his ucn.
     *
     * @param request
     * @param ucn
     * @return EmployeeDTO
     */
    @SuppressWarnings("nls")
    private static EmployeeDTO findEmployeeByUCN(final HttpServletRequest request,
            final String ucn) {
        final ServletContext ctx = request.getServletContext();
        final Factory factory = (Factory) ctx.getAttribute("serviceFactory");
        final EmployeeService employeeService = factory.findService(EmployeeService.class);
        return employeeService.findEmployee(ucn);
    }

    /**
     * This method forward to error page.
     *
     * @param request
     * @param response
     * @param message
     * @throws ServletException
     * @throws IOException
     */
    @SuppressWarnings("nls")
    private static void forwardToErrorPage(final HttpServletRequest request,
            final HttpServletResponse response, final String message)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        request.setAttribute("errorMessage", message);
        requestDispatcher = request.getRequestDispatcher(PayrollConstants.ERROR_PAGE);
        requestDispatcher.forward(request, response);
    }
}
