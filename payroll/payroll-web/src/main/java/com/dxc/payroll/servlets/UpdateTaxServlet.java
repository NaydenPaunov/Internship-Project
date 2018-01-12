package com.dxc.payroll.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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
import com.dxc.payroll.exceptions.WrongTaxInput;
import com.dxc.payroll.service.exceptions.TaxException;
import com.dxc.payroll.services.TaxService;
import com.dxc.payroll.services.dto.TaxDTO;

/**
 * Servlet for updating tax with given name
 *
 */
public class UpdateTaxServlet extends HttpServlet {
    /**
     * private field serialVersionUID
     */
    private static final long serialVersionUID = 750478552399162939L;
    /**
     * private field logger
     */
    private static final Logger LOGGER = Logger.getLogger(UpdateTaxServlet.class.getName());

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @SuppressWarnings("nls")
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        String[] selected;
        selected = request.getParameterValues("typeOfTax");
        if (selected == null) {
            response.sendRedirect("/payroll/currentTax");
            return;
        }

        final ServletContext servletContext = request.getServletContext();
        final Factory factory = (Factory) servletContext
                .getAttribute("serviceFactory");
        final TaxService taxService = factory.findService(TaxService.class);
        final List<TaxDTO> currentTaxes = taxService.getAllCurrentTaxes();
        final Map<String, List<Double>> userInput = new ConcurrentHashMap<>();
        for (final String name : selected) {
            userInput.put(name, getPercentages(currentTaxes, name));
        }
        request.setAttribute("taxes", userInput);
        final RequestDispatcher requestDispatcher;
        requestDispatcher = request.getRequestDispatcher(PayrollConstants.UPDATE_TAX_JSP);
        requestDispatcher.forward(request, response);
    }

    private static List<Double> getPercentages(final List<TaxDTO> currentTaxes,
            final String typeOfTax) {
        final List<Double> percentages = new ArrayList<>();
        for (final TaxDTO tax : currentTaxes) {
            if (tax.getTypeOfTax().equals(typeOfTax)) {
                percentages.add(Double.valueOf(tax.getPercentageEmployee()));
                percentages.add(Double.valueOf(tax.getPercentageCompany()));
            }
        }
        return percentages;
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @SuppressWarnings("nls")
    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        Map<String, List<Double>> userInput;
        try {
            userInput = getUserInput(request);
        }
        catch (final WrongTaxInput e) {
            LOGGER.log(Level.INFO, "Wrong input", e);
            request.setAttribute("taxes", e.getInput());
            requestDispatcher = request.getRequestDispatcher(PayrollConstants.UPDATE_TAX_JSP);
            requestDispatcher.forward(request, response);
            return;
        }

        final ServletContext servletContext = request.getServletContext();
        final Factory factory = (Factory) servletContext
                .getAttribute("serviceFactory");
        final TaxService taxService = factory.findService(TaxService.class);
        try {
            taxService.updateTaxesWithGivenNames(userInput);
        }
        catch (final TaxException e) {
            LOGGER.log(Level.INFO,
                    String.format("Type of tax - %s, List length - %d", e.getTypeOfTax(),
                            Integer.valueOf(e.getListLength())),
                    e);
            if (e.getListLength() > 1) {
                request.setAttribute("errorMessage", "There is more than one current tax found");
                requestDispatcher = request.getRequestDispatcher(PayrollConstants.ERROR_PAGE);
                requestDispatcher.forward(request, response);
                return;
            }
            else if (e.getListLength() == 0) {
                request.setAttribute("errorMessage", "There is no current tax found");
                requestDispatcher = request.getRequestDispatcher(PayrollConstants.ERROR_PAGE);
                requestDispatcher.forward(request, response);
                return;
            }
        }
        response.sendRedirect("/payroll/currentTax");
    }

    @SuppressWarnings("nls")
    private static Map<String, List<Double>> getUserInput(final HttpServletRequest request)
            throws WrongTaxInput {
        final String[] percentageEmployee = request
                .getParameterValues(PayrollConstants.PERCENTAGE_EMPLOYEE);
        final String[] percentageCompany = request
                .getParameterValues(PayrollConstants.PERCENTAGE_COMPANY);
        final String[] typeOfTaxes = request.getParameterValues("typeOfTax");

        final Map<String, List<Double>> userInput = new ConcurrentHashMap<>();
        boolean correctInput = true;
        for (int i = 0; i < typeOfTaxes.length; i++) {
            final List<Double> percentages = new ArrayList<>();
            final boolean wrongInputInEmployeePercentage = parseUserInput(percentageEmployee[i],
                    percentages);
            final boolean wrongInputInCompanyPercentage = parseUserInput(percentageCompany[i],
                    percentages);
            if (!wrongInputInEmployeePercentage && wrongInputInCompanyPercentage) {
                correctInput = false;
            }
            userInput.put(typeOfTaxes[i], percentages);
        }
        if (!correctInput) {
            throw (new WrongTaxInput("Wrong input!", userInput));
        }
        return userInput;
    }

    @SuppressWarnings("nls")
    private static boolean parseUserInput(final String percentage, final List<Double> percentages) {
        try {
            final double percentageParsed = Double.parseDouble(percentage);
            percentages.add(Double.valueOf(percentageParsed));
        }
        catch (final NumberFormatException e) {
            LOGGER.log(Level.INFO, "Wrong user input", e);
            percentages.add(null);
            return false;
        }
        return true;
    }

}
