package com.urte.cliapp.service;

import com.urte.engine.Validator;
import org.junit.Test;
import org.mockito.Mockito;


import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class ValidationServiceTest {

    Validator mockValidator = Mockito.mock(Validator.class);
    ValidationService validationService = new ValidationService(mockValidator);

    @Test
    public void testValidateString() {
        validationService.validate("test");
        verify(mockValidator, times(1)).isValid(anyString());
    }
}
