package com.dxc.payroll.service.validation;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class ValidationExceptionTest {

    @SuppressWarnings({ "static-method", "nls" })
    @Test
    public void getEntityToErrorCodesMap() {
        final Validator validator = new Validator();
        validator.addForValidation(Type.UCN, "123a");
        final List<ErrorCode> errorCodes = new ArrayList<>();
        errorCodes.add(ErrorCode.TOO_SHORT);
        errorCodes.add(ErrorCode.CONTAINS_NON_DIGITS);
        final Map<ValidationEntity, List<ErrorCode>> expectedMap = new HashMap<>();
        expectedMap.put(new ValidationEntity(Type.UCN, "123a"), errorCodes);
        Map<ValidationEntity, List<ErrorCode>> actualMap = null;
        try {
            validator.validateAll();
        }
        catch (final ValidationException e) {
            actualMap = e.getEntityToErrorCodesMap();
        }
        assertEquals("was expecting equal maps", expectedMap, actualMap);
    }

}
