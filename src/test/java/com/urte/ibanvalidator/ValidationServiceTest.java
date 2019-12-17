package com.urte.ibanvalidator;

import com.urte.ibanvalidator.service.ValidationService;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidationServiceTest {

    @Test
    public void testIsValid() {
        ValidationService validationService = new ValidationService();
        assertTrue(validationService.isValid("AD1400080001001234567890"));
        assertFalse(validationService.isValid("AA1400080001001234567890"));
        assertFalse(validationService.isValid("AD1400080001001234567891"));
        assertFalse(validationService.isValid("1400080001001234567890"));
        assertFalse(validationService.isValid("AD1400"));
        assertFalse(validationService.isValid("AD140008000100123456789@0*"));
    }
}
