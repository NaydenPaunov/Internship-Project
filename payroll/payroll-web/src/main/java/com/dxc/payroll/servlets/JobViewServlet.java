package com.dxc.payroll.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dxc.payroll.common.Factory;
import com.dxc.payroll.constants.JobConstants;
import com.dxc.payroll.constants.PayrollConstants;
import com.dxc.payroll.services.JobService;
import com.dxc.payroll.services.dto.ComboBoxesDTO;
import com.dxc.payroll.services.dto.JobWithSalaryDTO;

/**
 *
 * A Servlet that provides view for the information about the jobs.
 *
 * There are two combo boxes. The first one contains a drop down list with all
 *
 * professions which are currently present in the database. A user must choose a
 *
 * profession in order to see information about the job and all the positions
 *
 * which correspond to it. If there are no jobs present in the database, an
 *
 * error message is displayed.
 *
 * When the user selects the desired profession (job title) in the second combo
 *
 * box he can choose a job degree and filter the results.
 *
 *
 */
public class JobViewServlet extends HttpServlet {
    /**
     *
     * generated serialVersionUID
     *
     */
    private static final long serialVersionUID = 2647203901386308726L;

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
        final JobService jobService = factory.findService(JobService.class);

        final ComboBoxesDTO comboBoxes = jobService.loadComboBoxes();
        final List<String> jobTitles = comboBoxes.getAllJobTitles();
        if (jobTitles.isEmpty()) {
            processError(request, response);
            return;
        }
        final String jobTitle = request.getParameter(PayrollConstants.JOB_TITLE_PARAM);
        final ComboBoxesDTO comboBoxesAttr = filterComboBoxesContent(jobTitle, comboBoxes);
        final String jobDegree = request.getParameter(PayrollConstants.JOB_DEGREE_PARAM);

        final Map<String, Object> viewModel = new HashMap<>();
        viewModel.put(JobConstants.JOB_TITLES_ATTRIBUTE, jobTitles);
        viewModel.put(JobConstants.SELECTED_TITLE_ATTRIBUTE, jobTitle);
        viewModel.put(JobConstants.SELECTED_DEGREE_ATTRIBUTE, jobDegree);
        viewModel.put(JobConstants.COMBOBOXES_ATTRIBUTE, comboBoxesAttr);

        if (jobTitle != null && !jobTitle.isEmpty()) {
            final List<JobWithSalaryDTO> jobs = jobService.getJobInfo(jobTitle, jobDegree);
            viewModel.put(JobConstants.JOB_LIST_ATTRIBUTE, jobs);
        }
        request.setAttribute("viewModel", viewModel);
        request.getRequestDispatcher(PayrollConstants.JOB_JSP).forward(request, response);
    }

    private static void processError(final HttpServletRequest request,
            final HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(PayrollConstants.ERROR_MESSAGE,
                PayrollConstants.NO_RECORDS_ERROR_MESSAGE);
        request.getRequestDispatcher(PayrollConstants.JOB_JSP).forward(request, response);
    }

    private static ComboBoxesDTO filterComboBoxesContent(final String jobTitle,
            final ComboBoxesDTO comboBoxes) {
        return new ComboBoxesDTO(comboBoxes.getDtos().stream()
                .filter(titleAndDegrees -> titleAndDegrees.getJobTitle().equals(jobTitle))
                .collect(Collectors.toList()));
    }
}
