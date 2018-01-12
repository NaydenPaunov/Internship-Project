package com.dxc.payroll.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dxc.payroll.common.Factory;
import com.dxc.payroll.constants.PayrollConstants;
import com.dxc.payroll.service.exceptions.EmployeeNotFoundException;
import com.dxc.payroll.services.PaycheckService;
import com.dxc.payroll.services.dto.PaycheckDTO;

/**
 * Servlet implementation class GeneratePaycheckServlet
 */
@WebServlet("/GeneratePaycheckServlet")
public class GeneratePaycheckServlet extends HttpServlet {

    @SuppressWarnings("nls")
    /**
     * String constant for UCN
     */
    private static final String UCN = "UCN";
    /**
     * logger
     */
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final long serialVersionUID = 12341234242L;

    /**
     * String constant for hoursWorked (request attribute getting and setting)
     */
    @SuppressWarnings("nls")

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GeneratePaycheckServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        final RequestDispatcher requestDispatcher = request
                .getRequestDispatcher(PayrollConstants.GENERATE_PAYCHECK_JSP);
        requestDispatcher.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @SuppressWarnings("nls")
    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        if (!isValidParameter(request.getParameter(UCN))
                || !isValidParameter(request.getParameter(PayrollConstants.DATE_OF_PAYCHECK))
                || !isValidParameter(request.getParameter(PayrollConstants.HOURS_WORKED))) {
            forwardToGeneratePaycheck(request, response);
        }
        else {
            final LocalDate dateOfPaycheck;

            try {
                dateOfPaycheck = LocalDate
                        .parse(request.getParameter(PayrollConstants.DATE_OF_PAYCHECK));
            }
            catch (final DateTimeParseException ex) {
                LOGGER.log(Level.WARNING, "Error while parsing the date from the request ", ex);
                forwardToGeneratePaycheck(request, response);
                return;
            }
            final Integer hoursWorked;
            try {
                hoursWorked = Integer.valueOf(request.getParameter(PayrollConstants.HOURS_WORKED));
            }
            catch (final NumberFormatException ex) {

                LOGGER.log(Level.WARNING, "Error parsing some or all of the request parameters",
                        ex);
                return;
            }
            final String employeeUCN;
            employeeUCN = request.getParameter(UCN);

            final PaycheckService service = findPaycheckService(request);
            final PaycheckDTO newPaycheck;
            try {
                newPaycheck = service.generatePaycheck(employeeUCN, dateOfPaycheck,
                        hoursWorked.intValue());
            }

            catch (final EmployeeNotFoundException e) {
                forwardToGeneratePaycheck(request, response);
                LOGGER.log(Level.WARNING, "Employee was not found", e);
                return;
            }
            final Map<String, Object> salaryViewModel = populateViewModel(dateOfPaycheck,
                    hoursWorked, employeeUCN, newPaycheck);
            final HttpSession session = request.getSession(true);
            session.setAttribute("salaryViewModel", salaryViewModel);
            response.sendRedirect("/payroll/paycheckPreview");
        }
    }

    private static Map<String, Object> populateViewModel(final LocalDate dateOfPaycheck,
            final Integer hoursWorked, final String employeeUCN, final PaycheckDTO newPaycheck) {
        final Map<String, Object> salaryViewModel = new HashMap<>();
        salaryViewModel.put(UCN, employeeUCN);
        salaryViewModel.put(PayrollConstants.BASE_SALARY,
                Double.valueOf(newPaycheck.getBaseSalary()));
        salaryViewModel.put(PayrollConstants.TAX_RATE, Double.valueOf(newPaycheck.getTaxRate()));
        salaryViewModel.put(PayrollConstants.NET_SALARY,
                Double.valueOf(newPaycheck.getNetSalary()));
        salaryViewModel.put(PayrollConstants.GROSS_SALARY,
                Double.valueOf(newPaycheck.getGrossSalary()));
        salaryViewModel.put(PayrollConstants.DATE_OF_PAYCHECK, dateOfPaycheck);
        salaryViewModel.put(PayrollConstants.HOURS_WORKED, hoursWorked);
        return salaryViewModel;
    }

    private static void forwardToGeneratePaycheck(final HttpServletRequest request,
            final HttpServletResponse response) throws ServletException, IOException {
        final RequestDispatcher requestDispatcher;
        requestDispatcher = request.getRequestDispatcher(PayrollConstants.GENERATE_PAYCHECK_JSP);
        requestDispatcher.forward(request, response);
    }

    private static PaycheckService findPaycheckService(final HttpServletRequest request) {
        final ServletContext ctx = request.getServletContext();
        final Factory factory = (Factory) ctx.getAttribute(PayrollConstants.SERVICE_FACTORY);
        return factory.findService(PaycheckService.class);

    }

    // TODO natrupvane na greshki v nqkakuv map i vizualiziraneto im RE
    /**
     * 
     * @param requestParameter
     * @return true if the request parameter is not null or not empty false if
     *         the request parameter is null or empty
     */
    private static boolean isValidParameter(final String requestParameter) {
        return !(requestParameter == null || requestParameter.isEmpty());
    }

}
