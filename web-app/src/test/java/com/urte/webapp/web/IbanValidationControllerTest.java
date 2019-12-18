package com.urte.webapp.web;

import com.urte.webapp.service.ValidationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class IbanValidationControllerTest {

    ValidationService mockValidationService = Mockito.mock(ValidationService.class);
    IbanValidationController ibanValidationController = new IbanValidationController(mockValidationService);

    @Test
    public void testCallsValidationService() {
        ibanValidationController.validate(new ArrayList<>(Arrays.asList("one", "two")));
        verify(mockValidationService, times(1)).validate(anyList());
    }
}
