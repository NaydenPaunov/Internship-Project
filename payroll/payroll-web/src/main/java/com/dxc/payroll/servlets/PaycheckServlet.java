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
import com.dxc.payroll.services.PaycheckService;
import com.dxc.payroll.services.dto.PaycheckDTO;

/**
 * @author lmitov
 * 
 *
 */
public class PaycheckServlet extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * private field logger
     */
    private static final Logger LOGGER = Logger.getLogger(PaycheckServlet.class.getName());

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaycheckServlet() {
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

        String ucn = null;
        String errorMessage;
        ucn = request.getParameter("id");
        request.setAttribute("employeeId", ucn);
        if (ucn == null) {
            errorMessage = "Invalid or missing parameter.";
            forwardToErrorPage(request, response, errorMessage);
            return;
        }
        final PaycheckService paycheckService = findPaycheckService(request);
        List<PaycheckDTO> paychecks;
        try {
            paychecks = paycheckService.getEmployeePaychecksByUCN(ucn);
        }
        catch (final EmployeeNotFoundException e) {
            errorMessage = "Employee with" + ucn + "was not found";
            LOGGER.log(Level.WARNING, "Employee with the given ucn was not found", e);
            forwardToErrorPage(request, response, errorMessage);
            return;
        }
        RequestDispatcher requestDispatcher;
        request.setAttribute("paychecks", paychecks);
        requestDispatcher = request.getRequestDispatcher(PayrollConstants.PAYCHECK_JSP);
        requestDispatcher.forward(request, response);
    }

    private static void forwardToErrorPage(final HttpServletRequest request,
            final HttpServletResponse response, final String errorMessage)
            throws ServletException, IOException {
        final RequestDispatcher requestDispatcher = request
                .getRequestDispatcher(PayrollConstants.ERROR_PAGE);
        request.setAttribute(PayrollConstants.ERROR_MESSAGE, errorMessage);
        requestDispatcher.forward(request, response);
    }

    @SuppressWarnings("nls")
    private static PaycheckService findPaycheckService(final HttpServletRequest request) {
        final ServletContext ctx = request.getServletContext();
        final Factory factory = (Factory) ctx.getAttribute("serviceFactory");
        return factory.findService(PaycheckService.class);

    }

}
