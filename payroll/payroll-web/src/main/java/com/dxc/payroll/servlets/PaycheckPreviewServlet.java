package com.dxc.payroll.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

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
import com.dxc.payroll.services.PaycheckService;

/**
 * Servlet implementation class PaycheckPreviewServlet
 */
@WebServlet("/PaycheckPreviewServlet")
public class PaycheckPreviewServlet extends HttpServlet {

    /**
     * String constant for response redirect to payroll/generatePaycheck
     */
    @SuppressWarnings("nls")
    private static final String PAYROLL_GENERATE_PAYCHECK = "/payroll/generatePaycheck";

    private static final long serialVersionUID = 1128973819L;

    /**
     * String constant for salaryViewModel(for session and request attribute)
     */
    @SuppressWarnings("nls")
    private static final String SALARY_VIEW_MODEL = "salaryViewModel";
    @SuppressWarnings("nls")
    /**
     * String constant for UCN
     */
    private static final String UCN = "UCN";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaycheckPreviewServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        final HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect(PAYROLL_GENERATE_PAYCHECK);
            return;
        }
        final Map<String, Object> salaryViewModel = (Map<String, Object>) session
                .getAttribute(SALARY_VIEW_MODEL);

        request.setAttribute(SALARY_VIEW_MODEL, salaryViewModel);

        final RequestDispatcher requestDispatcher = request
                .getRequestDispatcher(PayrollConstants.PAYCHECK_PREVIEW_JSP);
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
        final HttpSession session = request.getSession(false);
        final Map<String, Object> salaryViewModel = (Map<String, Object>) session
                .getAttribute(SALARY_VIEW_MODEL);
        if (request.getParameter("cancel") != null) {
            session.removeAttribute(SALARY_VIEW_MODEL);
            response.sendRedirect(PAYROLL_GENERATE_PAYCHECK);
        }
        else if (request.getParameter("save") != null) {
            final PaycheckService service = findPaycheckService(request);
            final String employeeUCN = String.valueOf(salaryViewModel.get(UCN));

            final LocalDate dateOfPaycheck = LocalDate
                    .parse(salaryViewModel.get(PayrollConstants.DATE_OF_PAYCHECK).toString());
            final int hoursWorked = Integer
                    .parseInt(salaryViewModel.get(PayrollConstants.HOURS_WORKED).toString());
            final double grossSalary = Double
                    .parseDouble(salaryViewModel.get(PayrollConstants.GROSS_SALARY).toString());
            final double netSalary = Double
                    .parseDouble(salaryViewModel.get(PayrollConstants.NET_SALARY).toString());
            final double taxRate = Double
                    .parseDouble(salaryViewModel.get(PayrollConstants.TAX_RATE).toString());
            final double baseSalary = Double
                    .parseDouble(salaryViewModel.get(PayrollConstants.BASE_SALARY).toString());
            service.savePaycheck(employeeUCN, dateOfPaycheck, hoursWorked, grossSalary, taxRate,
                    netSalary, baseSalary);
            session.removeAttribute(SALARY_VIEW_MODEL);
        }
        else {
            response.sendRedirect(PAYROLL_GENERATE_PAYCHECK);
        }
    }

    private static PaycheckService findPaycheckService(final HttpServletRequest request) {
        final ServletContext ctx = request.getServletContext();
        final Factory factory = (Factory) ctx.getAttribute(PayrollConstants.SERVICE_FACTORY);
        return factory.findService(PaycheckService.class);

    }
}
