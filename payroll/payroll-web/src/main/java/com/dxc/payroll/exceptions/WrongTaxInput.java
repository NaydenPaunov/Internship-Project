package com.dxc.payroll.exceptions;

import java.util.List;
import java.util.Map;

/**
 * Exception for the wrong user input in the UpdateTaxServlet Extends Exception
 *
 */
public class WrongTaxInput extends Exception {
    /**
     * private field serialVersionUID
     */
    private static final long serialVersionUID = 7996791643479058651L;

    /**
     * private field needed to store the users input
     */
    private final Map<String, List<Double>> input;

    /**
     * Constructor for the WrongtaxInput exception
     *
     * @param message
     *            - the exception message
     * @param input
     *            - the users input
     */
    public WrongTaxInput(final String message, final Map<String, List<Double>> input) {
        super(message);
        this.input = input;
    }

    /**
     * getter for the user input
     *
     */
    public Map<String, List<Double>> getInput() {
        return input;
    }
}
