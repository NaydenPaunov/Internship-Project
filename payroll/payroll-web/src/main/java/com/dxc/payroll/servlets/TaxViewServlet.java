package com.dxc.payroll.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
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
import com.dxc.payroll.service.exceptions.TaxNotFoundException;
import com.dxc.payroll.services.TaxService;
import com.dxc.payroll.services.dto.TaxDTO;

/**
 * Servlet implementation class TaxViewServlet
 */
public class TaxViewServlet extends HttpServlet {

    /**
     * private field serialVersionUID
     */
    private static final long serialVersionUID = 4256350814798100980L;
    /**
     * private field for logging exceptions
     */
    private static final Logger LOGGER = Logger.getLogger(UpdateTaxServlet.class.getName());

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    @SuppressWarnings("nls")
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        final RequestDispatcher requestDispatcher;
        final String typeOfTax = request.getParameter("taxName");
        final String date = request.getParameter("date");
        if (typeOfTax == null || date == null) {
            request.setAttribute(PayrollConstants.ERROR_MESSAGE, "missingParam");
            requestDispatcher = request.getRequestDispatcher(PayrollConstants.ERROR_PAGE);
            requestDispatcher.forward(request, response);
            return;
        }
        final ServletContext servletContext = request.getServletContext();
        final Factory factory = (Factory) servletContext
                .getAttribute("serviceFactory");
        final TaxService taxService = factory.findService(TaxService.class);
        final DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        final LocalDate startDate = LocalDate.parse(date, formatter);
        final TaxDTO tax;
        try {
            tax = taxService.findTaxByNameAndDate(startDate, typeOfTax);
        }
        catch (final TaxNotFoundException e) {
            LOGGER.log(Level.INFO, String.format("No tax found with name %s and date %s.",
                    e.getTypeOfTax(), e.getStartDate().toString()), e);
            request.setAttribute(PayrollConstants.ERROR_MESSAGE, "noSuchTax");
            requestDispatcher = request.getRequestDispatcher(PayrollConstants.ERROR_PAGE);
            requestDispatcher.forward(request, response);
            return;
        }
        request.setAttribute("tax", tax);
        requestDispatcher = request.getRequestDispatcher(PayrollConstants.TAX_JSP);
        requestDispatcher.forward(request, response);
    }
}
