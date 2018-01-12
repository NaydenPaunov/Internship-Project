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
 * Servlet implementation class AllIndexationsServlet
 *
 */
public class AllIndexationsServlet extends HttpServlet {

    /**
     * Generated serialVersionUID
     */
    private static final long serialVersionUID = -3520736995988339698L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @SuppressWarnings("nls")
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher requestDispatcher;

        final ServletContext ctx = request.getServletContext();
        final Factory factory = (Factory) ctx
                .getAttribute(PayrollConstants.SERVICE_FACTORY);
        final IndexationService indexationService = factory.findService(IndexationService.class);
        final List<IndexationDTO> indexationsList = indexationService.getAllIndexations();

        if (indexationsList == null || indexationsList.isEmpty()) {
            request.setAttribute(PayrollConstants.ERROR_MESSAGE, "noIndexations");
            requestDispatcher = request.getRequestDispatcher(PayrollConstants.ALL_INDEXATIONS_JSP);
            requestDispatcher.forward(request, response);
            return;
        }
        request.setAttribute("indexationsList", indexationsList);
        requestDispatcher = request.getRequestDispatcher(PayrollConstants.ALL_INDEXATIONS_JSP);
        requestDispatcher.forward(request, response);

    }
}
