package com.urte.webapp.service;

import com.urte.engine.Validator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ValidationServiceTest {

    Validator mockValidator = Mockito.mock(Validator.class);
    ValidationService validationService = new ValidationService(mockValidator);

    @Test
    public void testValidate() {
        validationService.validate(new ArrayList<>(Arrays.asList("one", "two")));
        verify(mockValidator, times(2)).isValid(anyString());
    }
}
