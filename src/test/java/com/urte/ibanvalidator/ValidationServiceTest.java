package com.urte.ibanvalidator;

import com.urte.ibanvalidator.service.ValidationService;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidationServiceTest {

    @Test
    public void testStringValidation() {
        ValidationService validationService = new ValidationService();
        assertTrue(validationService.validate("AD1400080001001234567890"));
        assertFalse(validationService.validate("AA1400080001001234567890"));
        assertFalse(validationService.validate("AD1400080001001234567891"));
        assertFalse(validationService.validate("1400080001001234567890"));
        assertFalse(validationService.validate("AD1400"));
        assertFalse(validationService.validate("AD140008000100123456789@0*"));
    }
}
