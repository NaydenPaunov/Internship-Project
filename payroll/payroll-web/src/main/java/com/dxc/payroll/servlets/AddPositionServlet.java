package com.dxc.payroll.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dxc.payroll.common.Factory;
import com.dxc.payroll.constants.PayrollConstants;
import com.dxc.payroll.services.JobService;
import com.dxc.payroll.services.dto.PositionDTO;
import com.dxc.payroll.services.dto.ProfessionAddDTO;

/**
 * Servlet implementation class AddPositionServlet
 *
 */
@SuppressWarnings("nls")
public final class AddPositionServlet extends HttpServlet {
    private static final long serialVersionUID = 1952653207503164496L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        final String jobProfession = request.getParameter(PayrollConstants.PROFESSION);
        if (jobProfession == null || jobProfession.isEmpty()) {
            request.setAttribute(PayrollConstants.ERROR_MESSAGE,
                    PayrollConstants.PROFESSION_NO_PARAMETHER);
            request.getRequestDispatcher(PayrollConstants.ERROR_PAGE).forward(request, response);
        }
        final JobService jobService = getJobService(request);
        final ProfessionAddDTO profession = jobService
                .findJobAndPositionsByJobTitleForAddProffesion(jobProfession);
        if (profession == null) {
            request.setAttribute(PayrollConstants.ERROR_MESSAGE,
                    PayrollConstants.NO_RESULTS_MESSAGE);
            request.getRequestDispatcher(PayrollConstants.ERROR_PAGE).forward(request, response);
        }
        else {
            request.setAttribute("profession", profession);
            request.getRequestDispatcher(PayrollConstants.ADD_POSITION_JSP).forward(request,
                    response);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    // TODO validations
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        final String jobTitle = request.getParameter(PayrollConstants.JOB_TITLE_PARAM);
        final String jobDegree = request.getParameter(PayrollConstants.JOB_DEGREE_PARAM);
        final int jobLevel = Integer
                .parseInt(request.getParameter(PayrollConstants.JOB_LEVEL_PARAM));
        final double baseSalary = Double
                .parseDouble(request.getParameter(PayrollConstants.BASE_SALARY));
        final JobService jobService = getJobService(request);
        final PositionDTO position = jobService.addPosition(jobTitle, jobDegree, jobLevel,
                baseSalary);
        final String url = "addPosition?profession=" + position.getJobTitle();
        response.sendRedirect(url);
    }

    private static JobService getJobService(final HttpServletRequest request) {
        final ServletContext ctx = request.getServletContext();
        final Factory factory = (Factory) ctx.getAttribute(PayrollConstants.SERVICE_FACTORY);
        return factory.findService(JobService.class);
    }

}
