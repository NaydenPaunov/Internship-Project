package com.dxc.payroll.constants;

/**
 * Constants specific for the JobViewServlet
 */
@SuppressWarnings("nls")
public final class JobConstants {
    /**
     * selected job title
     */
    public static final String SELECTED_TITLE_ATTRIBUTE = "selectedTitle";
    /**
     * selected job degree
     */
    public static final String SELECTED_DEGREE_ATTRIBUTE = "selectedDegree";
    /**
     * constant for the attribute which contains list of all job titles in the
     * database
     */
    public static final String JOB_TITLES_ATTRIBUTE = "jobTitles";
    /**
     * constant for the attribute which contains list of all job degrees
     * corresponding to the given job title
     */
    public static final String JOB_DEGREES_ATTRIBUTE = "jobDegrees";
    /**
     * constant for the attribute which contains the list with the job
     * information which was requested by the user
     */
    public static final String JOB_LIST_ATTRIBUTE = "jobList";

    /**
     * constant for the attribute which contains the content for combo boxes
     */
    public static final String COMBOBOXES_ATTRIBUTE = "comboBoxes";

    /**
     * private constructor
     */
    private JobConstants() {
    }

}
