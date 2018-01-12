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
import com.dxc.payroll.services.TaxService;
import com.dxc.payroll.services.dto.TaxDTO;

/**
 *
 * Servlet for viewing current taxes and choosing which of them to update.
 * 
 * Contains redirection to UpdateTaxServlet with the names of the chosen taxes
 * for updating
 *
 */
public class CurrentTaxesServlet extends HttpServlet {

    /**
     * private field serialVersionUID
     */
    private static final long serialVersionUID = 1674443711481990496L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     * 
     *      The method gets all taxes with null end date and redirects to
     *      UpdateTaxServlet for updating taxes with the given names
     * 
     */
    @SuppressWarnings("nls")
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        final RequestDispatcher requestDispatcher;
        final ServletContext servletContext = request.getServletContext();
        final Factory factory = (Factory) servletContext
                .getAttribute(PayrollConstants.SERVICE_FACTORY);
        final TaxService taxService = factory.findService(TaxService.class);
        final List<TaxDTO> currentTaxes = taxService.getAllCurrentTaxes();
        request.setAttribute("currentTaxes", currentTaxes);
        requestDispatcher = request.getRequestDispatcher(PayrollConstants.CURRENT_TAX_JSP);
        requestDispatcher.forward(request, response);
    }

}
