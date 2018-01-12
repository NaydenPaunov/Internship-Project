package com.dxc.payroll.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dxc.payroll.common.Factory;
import com.dxc.payroll.constants.PayrollConstants;
import com.dxc.payroll.services.PositionService;
import com.dxc.payroll.services.dto.PositionDTO;
import com.dxc.payroll.services.dto.RaiseAnEmployeeSimulationDTO;

/**
 * Servlet implementation class RaisingEmployeeServlet
 * 
 */
public class RaisingEmployeeServlet extends HttpServlet {
    /**
     * serialVersionUID initialization
     */
    private static final long serialVersionUID = -1350029678284458661L;
    /**
     * Constant for chosen position
     */
    private static final String CHOSEN_POSITION_PARAM = "chosenPosition";
    /**
     * Constant for allowable positions
     */
    private static final String ALLOWABLE_POSITIONS = "allowablePositions";

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     * 
     *      Method get parameter employeeUCN If current parameter is null send
     *      you to errorPage. If there is no employee with this UCN again send
     *      you to errorPage. Else show you position information for this
     *      employee - name , job title , base salary and job level.And gave you
     *      an option to rice an Employee to next levels or next jobDegree (but
     *      not more than one up)
     * 
     */
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        final String employeeUCN = request.getParameter(PayrollConstants.EMPLOYEE_UCN_PARAM);
        if (employeeUCN == null) {
            response.sendRedirect("/payroll/home");
            return;
        }
        final PositionService positionService = getPositionService(request);
        final RaiseAnEmployeeSimulationDTO raiseAnEmployeeSimulationDTO = positionService
                .getInformationForRaiseAnEmployee(employeeUCN);
        if (raiseAnEmployeeSimulationDTO == null) {
            forwardToErrorPage(PayrollConstants.NO_RESULTS_MESSAGE, request, response);
            return;
        }
        forwardSimulationInfoToJSP(request, response, employeeUCN, raiseAnEmployeeSimulationDTO);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @SuppressWarnings("nls")
    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        if ("check".equals(request.getParameter("check"))) {
            simulationRaising(request, response);
        }
        else if ("update".equals(request.getParameter("update"))) {
            raiseEmployee(request, response);
        }
        else {
            forwardToErrorPage(PayrollConstants.INVALID_PARAMETER, request, response);
        }
    }

    private static void raiseEmployee(final HttpServletRequest request,
            final HttpServletResponse response) throws ServletException, IOException {
        final String employeeUCN = request.getParameter(PayrollConstants.EMPLOYEE_UCN_PARAM);
        if (employeeUCN == null) {
            forwardToErrorPage(PayrollConstants.INVALID_PARAMETER, request, response);
            return;
        }
        final String currentDegreeAndLevel = request
                .getParameter(PayrollConstants.DEGREE_AND_LEVEL_PARAM);
        final List<String> degreeAndLevel = splitDegreeAndLevel(currentDegreeAndLevel);
        if (degreeAndLevel.isEmpty()) {
            forwardToErrorPage(PayrollConstants.INVALID_PARAMETER, request, response);
            return;
        }
        final PositionService positionService = getPositionService(request);
        positionService.raiseEmployee(employeeUCN, degreeAndLevel.get(0),
                Integer.parseInt(degreeAndLevel.get(1)));

        final RequestDispatcher requestDispatcher = request
                .getRequestDispatcher(PayrollConstants.SUCCESSFULLY_RAISED_JPS);
        requestDispatcher.forward(request, response);
    }

    private static void simulationRaising(final HttpServletRequest request,
            final HttpServletResponse response) throws ServletException, IOException {
        final String employeeUCN = request.getParameter(PayrollConstants.EMPLOYEE_UCN_PARAM);
        if (employeeUCN == null) {
            forwardToErrorPage(PayrollConstants.INVALID_PARAMETER, request, response);
            return;
        }
        final String currentDegreeAndLevel = request
                .getParameter(PayrollConstants.DEGREE_AND_LEVEL_PARAM);

        final List<String> degreeAndLevel = splitDegreeAndLevel(currentDegreeAndLevel);
        if (degreeAndLevel.isEmpty()) {
            forwardToErrorPage(PayrollConstants.INVALID_PARAMETER, request, response);
            return;
        }

        final PositionService positionService = getPositionService(request);
        final RaiseAnEmployeeSimulationDTO raiseAnEmployeeSimulationDTO = positionService
                .simulationRaising(employeeUCN, degreeAndLevel.get(0),
                        Integer.parseInt(degreeAndLevel.get(1)));
        if (raiseAnEmployeeSimulationDTO == null) {
            forwardToErrorPage(PayrollConstants.NO_RESULTS_MESSAGE, request, response);
            return;
        }

        final PositionDTO chosenPosition = raiseAnEmployeeSimulationDTO.getChosenPosition();
        if (chosenPosition != null) {
            request.setAttribute(CHOSEN_POSITION_PARAM, chosenPosition);
        }
        forwardSimulationInfoToJSP(request, response, employeeUCN, raiseAnEmployeeSimulationDTO);
    }

    private static List<String> splitDegreeAndLevel(final String currentDegreeAndLevel) {
        final List<String> splitedDegreeAndLevel = new ArrayList<>();
        if (currentDegreeAndLevel == null) {
            return splitedDegreeAndLevel;
        }
        final String[] degreeAndLevel = currentDegreeAndLevel.split(":");
        if (degreeAndLevel.length != 2) {
            return splitedDegreeAndLevel;
        }
        Collections.addAll(splitedDegreeAndLevel, degreeAndLevel);
        return splitedDegreeAndLevel;
    }

    private static PositionService getPositionService(final HttpServletRequest request) {
        final ServletContext ctx = request.getServletContext();
        final Factory factory = (Factory) ctx.getAttribute(PayrollConstants.SERVICE_FACTORY);
        return factory.findService(PositionService.class);
    }

    private static void forwardSimulationInfoToJSP(final HttpServletRequest request,
            final HttpServletResponse response, final String employeeUCN,
            final RaiseAnEmployeeSimulationDTO raiseAnEmployeeSimulationDTO)
            throws ServletException, IOException {
        final Map<String, Object> viewModel = new HashMap<>();
        viewModel.put(PayrollConstants.EMPLOYEE_NAME_PARAM,
                raiseAnEmployeeSimulationDTO.getEmployeeName());
        viewModel.put(ALLOWABLE_POSITIONS, raiseAnEmployeeSimulationDTO.getAllowablePositions());
        viewModel.put(PayrollConstants.POSITION_DTO_PARAM,
                raiseAnEmployeeSimulationDTO.getCurrentPosition());
        viewModel.put(PayrollConstants.EMPLOYEE_UCN_PARAM, employeeUCN);

        request.setAttribute("viewModel", Collections.unmodifiableMap(viewModel));
        final RequestDispatcher requestDispatcher = request
                .getRequestDispatcher(PayrollConstants.RAISE_EMPLOYEE_JSP);
        requestDispatcher.forward(request, response);
    }

    private static void forwardToErrorPage(final String errorMessage,
            final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        request.setAttribute(PayrollConstants.ERROR_MESSAGE, errorMessage);
        requestDispatcher = request.getRequestDispatcher(PayrollConstants.ERROR_PAGE);
        requestDispatcher.forward(request, response);
    }
}
