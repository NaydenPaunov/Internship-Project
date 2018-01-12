package com.dxc.payroll.constants;

/**
 * Common constants for the web module. Constants needed for the Servlets,
 * representing attribute names and relative path-s to jsp files.
 */
@SuppressWarnings("nls")
public final class PayrollConstants {
    /**
     * constant for the serviceFactory String
     */
    public static final String SERVICE_FACTORY = "serviceFactory";
    /**
     * constant for the percentage for the employee
     */
    public static final String PERCENTAGE_EMPLOYEE = "percentageEmployee";

    /**
     * constant for the percentage for the company
     */
    public static final String PERCENTAGE_COMPANY = "percentageCompany";
    /**
     * constant for the type of the tax
     */
    public static final String TYPE_OF_TAX = "taxName";
    /**
     * constant for the jobTitle String
     */
    public static final String JOB_TITLE_PARAM = "jobTitle";
    /**
     * constant for the jobDegree String
     */
    public static final String JOB_DEGREE_PARAM = "jobDegree";
    /**
     * constant for the jobDegree and JobLevel String
     */
    public static final String DEGREE_AND_LEVEL_PARAM = "jobDegreeAndLevel";
    /**
     * constant for the jobLevel String
     */
    public static final String JOB_LEVEL_PARAM = "jobLevel";
    /**
     * constant for current Employee UCN parameter
     */
    public static final String EMPLOYEE_UCN_PARAM = "employeeUCN";
    /**
     * constant for parameter employeeName needed by raisingAnEmployee.xml
     */
    public static final String EMPLOYEE_NAME_PARAM = "employeeName";
    /**
     * constant for current Employee Base salary parameter
     */
    public static final String BASE_SALARY = "baseSalary";
    /**
     * constant for current Profession parameter
     */
    public static final String PROFESSION = "profession";
    /**
     * constant for parameter positionDTO needed by positionInformation.xml
     */
    public static final String POSITION_DTO_PARAM = "positionDTO";

    /**
     * constant for current Employee taxRate string
     */
    public static final String TAX_RATE = "taxRate";
    /**
     * constant for current Employee netSalary string
     */
    public static final String NET_SALARY = "netSalary";
    /**
     * constant for current Employee grossSalary string
     */
    public static final String GROSS_SALARY = "grossSalary";
    /**
     * constant for dateOfPaycheck string
     */
    public static final String DATE_OF_PAYCHECK = "dateOfPaycheck";
    /**
     * constant for hoursWorked string
     */
    public static final String HOURS_WORKED = "hoursWorked";

    // ERROR MESSAGES
    /**
     * constant for the error message displaying when there are no records in
     * the database
     */
    public static final String NO_RECORDS_ERROR_MESSAGE = "noRecordsErrorMessage";
    /**
     * constant for the error message displaying when there is no such parameter
     */
    public static final String INVALID_PARAMETER = "Invalid parameter.";
    /**
     * constant for the error message displaying when there is no employeeUCN
     * parameter
     */
    public static final String EMPLOYEE_UCN_NO_PARAMETHER = "There in no employeeUCN "
            + "paramether added.";
    /**
     * constant for the error message displaying when there is no employeeUCN
     * parameter
     */
    public static final String PROFESSION_NO_PARAMETHER = "There in no profession "
            + "paramether added.";
    /**
     * constant for the error message when there are no results
     */
    public static final String NO_RESULTS_MESSAGE = "noResultsErrorMessage";
    /**
     * constant for the error message displaying when there is no job title
     * selected
     */
    public static final String NO_JOB_TITLE_SELECTED = "You haven't selected any job title.";
    /**
     * Default error message.
     */
    public static final String DEFAULT_ERROR_MESSAGE = "defaultErrorMessage";
    /**
     * Error message for RuntimeException uncaught from the servlets.
     */
    public static final String RUNTIME_EXCEPTION_ERROR_MESSAGE = "runtimeException";
    /**
     * Error message for NumberFormatExeption exception uncaught from the
     * servlets.
     */
    public static final String NUMBER_FORMAT_EXCEPTION_ERROR_MESSAGE = "numberFormatException";
    /**
     * Error message for NullPointer exception uncaught from the servlets.
     */
    public static final String NULL_POINTER_EXCEPTION_ERROR_MESSAGE = "nullPointerException";

    // PATH FOR JSP FILES
    /**
     * constant for the path of the error.jsp
     */
    public static final String ERROR_JSP = "/WEB-INF/errors/error.jsp";
    /**
     * constant for the path of employeeID.jsp
     */
    public static final String EMPLOYEE_JSP = "/WEB-INF/pages/employeeId.jsp";
    /**
     * constant for the path of subordinate.jsp
     */
    public static final String SUBORDINATE_JSP = "/WEB-INF/pages/subordinate.jsp";
    /**
     * constant for the path of paycheck.jsp
     */
    public static final String PAYCHECK_JSP = "/WEB-INF/pages/paycheck.jsp";
    /**
     * constant for the path of generatePaycheck.jsp
     */
    public static final String GENERATE_PAYCHECK_JSP = "/WEB-INF/pages/generatePaycheck.jsp";
    /**
     * constant for the path of paycheckPreview.jsp
     */
    public static final String PAYCHECK_PREVIEW_JSP = "/WEB-INF/pages/paycheckPreview.jsp";
    /**
     * constant for the path of the job.jsp
     */
    public static final String JOB_JSP = "/WEB-INF/pages/job.jsp";
    /**
     * constant for the path of the position.jsp
     */
    public static final String POSITION_JSP = "/WEB-INF/pages/position.jsp";
    /**
     * constant for the path of the addPosition.jsp
     */
    public static final String ADD_POSITION_JSP = "/WEB-INF/pages/addPosition.jsp";
    /**
     * constant for the error page path
     */
    public static final String ERROR_PAGE = "/WEB-INF/errors/ErrorPage.jsp";
    /**
     * constant for Raising an employee JSP file path
     */
    public static final String RAISE_EMPLOYEE_JSP = "/WEB-INF/pages/raiseAnEmployee.jsp";
    /**
     * constant for positionInformation JSP page path
     */
    public static final String POSITION_INFORMATION_JSP = "/WEB-INF/pages/"
            + "positionInformation.jsp";
    /**
     * constant for the path of the indexation.jsp
     */
    public static final String INDEXATION_JSP = "/WEB-INF/pages/indexation.jsp";
    /**
     * constant for the path of the positionHistory.jsp
     */
    public static final String POSITION_HISTORY_JSP = "/WEB-INF/pages/positionHistory.jsp";
    /**
     * constant for the path of rights.jsp
     */
    public static final String RIGHTS_JSP = "/WEB-INF/pages/rights.jsp";
    /**
     * constant for the path of rightsChangeSuccess.jsp
     */
    public static final String RIGHTS_CHANGE_SUCCESS_JSP = "/WEB-INF/pages/rightsChangeSuccess.jsp";
    /**
     * constant for the path of rightsChange.jsp
     */
    public static final String RIGHTS_CHANGE_JSP = "/WEB-INF/pages/rightsChange.jsp";
    /**
     * constant for the path of tax.jsp
     */
    public static final String TAX_JSP = "/WEB-INF/pages/tax.jsp";

    /**
     * constant for the path of updateTax.jsp
     */
    public static final String UPDATE_TAX_JSP = "/WEB-INF/pages/updateTax.jsp";

    /**
     * constant for the path of currentTax.jsp
     */
    public static final String CURRENT_TAX_JSP = "/WEB-INF/pages/currentTax.jsp";
    /**
     * constant for the path of viewTaxHistory.jsp
     */
    public static final String VIEW_TAX_HISTORY_JSP = "/WEB-INF/pages/viewTaxHistory.jsp";

    /**
     * constant for the path of the allIndexations.jsp
     */
    public static final String ALL_INDEXATIONS_JSP = "/WEB-INF/pages/allIndexations.jsp";

    /**
     * constant for the path of the employees.jsp
     */
    public static final String EMPLOYEES_JSP = "/WEB-INF/pages/employees.jsp";
    /**
     * constant for the path of the successfullyRaised.jsp
     */
    public static final String SUCCESSFULLY_RAISED_JPS = "/WEB-INF/pages/successfullyRaised.jsp";
    // ATTRIBUTES
    /**
     * constant for the attribute error message
     */
    public static final String ERROR_MESSAGE = "errorMessage";

    /**
     * constant for the attribute error code
     */
    public static final String ERROR_COODE_ATTRIBUTE = "errorCode";

    // LOG DESCRIPTION
    /**
     * Default value when there is no value for
     * RequestDispatcher.ERROR_SERVLET_NAME and
     * RequestDispatcher.ERROR_REQUEST_URI
     */
    public static final String UNKNOWN = "Unknown";
    /**
     * Error id
     */
    public static final String ERROR_ID = "Error ID : ";
    /**
     * Error servlet name
     */
    public static final String ERROR_SERVLET_NAME = "Error servlet name: ";
    /**
     * Error request URI
     */
    public static final String ERROR_REQUEST_URI = "Error request URI: ";
    /**
     * Error status code
     */
    public static final String ERROR_STATUS_CODE = "Error status code: ";

    /**
     * used to separate names in a parameter name
     */
    public static final String PARAM_NAME_SEPARATOR = ":";

    /**
     * Private constructor for the PayrollConstantsClass
     */
    private PayrollConstants() {
    }
}
