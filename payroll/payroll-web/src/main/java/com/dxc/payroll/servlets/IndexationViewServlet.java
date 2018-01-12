package com.dxc.payroll.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dxc.payroll.common.Factory;
import com.dxc.payroll.constants.PayrollConstants;
import com.dxc.payroll.services.IndexationService;
import com.dxc.payroll.services.dto.IndexationDTO;

/**
 * Servlet implementation class IndexationViewServlet
 *
 */
@SuppressWarnings("nls")
public class IndexationViewServlet extends HttpServlet {

    /**
     * Generated serialVersionUID
     */
    private static final long serialVersionUID = -222449128878097288L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        final String jobTitleParam = request.getParameter(PayrollConstants.JOB_TITLE_PARAM);
        if (jobTitleParam == null || jobTitleParam.isEmpty()) {
            request.setAttribute(PayrollConstants.ERROR_MESSAGE, "missingParam");
            RequestDispatcher requestDispatcher = request
                    .getRequestDispatcher(PayrollConstants.ERROR_PAGE);
            requestDispatcher.forward(request, response);
            return;
        }

        final ServletContext ctx = request.getServletContext();
        final Factory factory = (Factory) ctx.getAttribute(PayrollConstants.SERVICE_FACTORY);
        final IndexationService indexationService = factory.findService(IndexationService.class);
        final List<IndexationDTO> indexationsList = indexationService
                .getIndexationsBy(jobTitleParam);

        if (indexationsList == null || indexationsList.isEmpty()) {
            request.setAttribute(PayrollConstants.ERROR_MESSAGE, "noIndexationsForThisJob");

            RequestDispatcher requestDispatcher = request
                    .getRequestDispatcher(PayrollConstants.INDEXATION_JSP);
            requestDispatcher.forward(request, response);
            return;
        }
        request.setAttribute("indexationsList", indexationsList);
        request.setAttribute("jobTitleParam", jobTitleParam);

        RequestDispatcher requestDispatcher = request
                .getRequestDispatcher(PayrollConstants.INDEXATION_JSP);
        requestDispatcher.forward(request, response);

    }
}
