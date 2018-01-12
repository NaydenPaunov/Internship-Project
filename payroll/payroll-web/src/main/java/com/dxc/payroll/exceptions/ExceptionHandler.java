package com.dxc.payroll.exceptions;

import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dxc.payroll.constants.PayrollConstants;
import com.dxc.payroll.service.exceptions.PayrollException;

/**
 * Servlet implementation class ExceptionHandler
 */
@WebServlet("/ExceptionHandler")
public class ExceptionHandler extends HttpServlet {
    /**
     * Serial version UID
     */
    private static final long serialVersionUID = -3084394273371789193L;
    /**
     * Logger
     */
    private static final Logger LOGGER = Logger.getLogger(ExceptionHandler.class.getName());

    /**
     * @see javax.servlet.http.HttpServlet#doGet
     *      (javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        processError(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    public void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        processError(request, response);
    }

    private static void processError(final HttpServletRequest request,
            final HttpServletResponse response) throws ServletException, IOException {
        final Throwable throwable = (Throwable) request
                .getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        final Integer statusCode = (Integer) request
                .getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String requestUri = (String) request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
        if (requestUri == null) {
            requestUri = PayrollConstants.UNKNOWN;
        }
        String servletName = (String) request.getAttribute(RequestDispatcher.ERROR_SERVLET_NAME);
        if (servletName == null) {
            servletName = PayrollConstants.UNKNOWN;
        }
        final UUID errorId = UUID.randomUUID();
        final String message = new StringBuilder(PayrollConstants.ERROR_ID).append(errorId)
                .append(PayrollConstants.ERROR_STATUS_CODE).append(statusCode)
                .append(PayrollConstants.ERROR_REQUEST_URI).append(requestUri)
                .append(PayrollConstants.ERROR_SERVLET_NAME).append(servletName).toString();
        try {
            throw throwable;
        }
        catch (PayrollException e) {
            LOGGER.log(Level.WARNING, message, e);
            setErrorMessage(errorId, e.getMessage(), request);
        }
        catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, message, e);
            setErrorMessage(errorId, PayrollConstants.NUMBER_FORMAT_EXCEPTION_ERROR_MESSAGE,
                    request);
        }
        catch (NullPointerException e) {
            LOGGER.log(Level.SEVERE, message, e);
            setErrorMessage(errorId, PayrollConstants.NULL_POINTER_EXCEPTION_ERROR_MESSAGE,
                    request);
        }
        catch (RuntimeException e) {
            LOGGER.log(Level.SEVERE, message, e);
            setErrorMessage(errorId, PayrollConstants.RUNTIME_EXCEPTION_ERROR_MESSAGE, request);
        }
        catch (Throwable e) {
            LOGGER.log(Level.SEVERE, message, e);
            setErrorMessage(errorId, PayrollConstants.DEFAULT_ERROR_MESSAGE, request);

        }
        finally {
            final RequestDispatcher requestDispatcher = request
                    .getRequestDispatcher(PayrollConstants.ERROR_JSP);
            requestDispatcher.forward(request, response);
        }
    }

    private static void setErrorMessage(UUID errorId, String message,
            final HttpServletRequest request) {
        request.setAttribute(PayrollConstants.ERROR_COODE_ATTRIBUTE, errorId);
        request.setAttribute(PayrollConstants.ERROR_MESSAGE, message);
    }
}
