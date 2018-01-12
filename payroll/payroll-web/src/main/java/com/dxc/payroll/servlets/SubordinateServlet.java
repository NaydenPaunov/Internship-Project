package com.dxc.payroll.servlets;

import java.io.IOException;
import java.util.List;
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
 * Servlet implementation class SubordinateServlet.
 */
public class SubordinateServlet extends HttpServlet {
    private final static transient Logger LOGGER = Logger
            .getLogger(EmployeeServlet.class.getName());

    private static final long serialVersionUID = 1L;

    /**
     * DoGet query.
     */
    @SuppressWarnings("nls")
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        final String ucn = request.getParameter("ucn");
        if (ucn == null) {
            forwardToErrorPage(request, response, "Invalid parameter.");
        }
        else {
            List<EmployeeDTO> subordinateList = null;
            try {
                subordinateList = findSubordinateList(request, ucn);
            }
            catch (EmployeeNotFoundException ex) {
                LOGGER.log(Level.WARNING, ex.getMessage());
                forwardToErrorPage(request, response, ex.getMessage());
                return;
            }

            forwardEmployeeToJSP(request, response, subordinateList);
        }
    }

    /**
     * Find subordinate list.
     *
     * @param request
     * @param ucn
     * @return List<EmployeeDTO>
     */
    @SuppressWarnings("nls")
    private static List<EmployeeDTO> findSubordinateList(final HttpServletRequest request,
            final String ucn) {
        final ServletContext ctx = request.getServletContext();
        final Factory factory = (Factory) ctx.getAttribute("serviceFactory");
        final EmployeeService subordinateService = factory.findService(EmployeeService.class);
        return subordinateService.findEmployeesByTeamLeadUCN(ucn);
    }

    /**
     * Forward to error page that display appropriate message.
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

    /**
     * Forward to subordinate jsp.
     *
     * @param request
     * @param response
     * @param employee
     * @throws ServletException
     * @throws IOException
     */
    @SuppressWarnings("nls")
    private static void forwardEmployeeToJSP(final HttpServletRequest request,
            final HttpServletResponse response, final List<EmployeeDTO> subordinateList)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        request.setAttribute("subordinate", subordinateList);
        requestDispatcher = request.getRequestDispatcher(PayrollConstants.SUBORDINATE_JSP);
        requestDispatcher.forward(request, response);
    }

}
