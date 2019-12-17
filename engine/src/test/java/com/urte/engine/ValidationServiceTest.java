package com.urte.engine;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidationServiceTest {

    @Test
    public void testIsValid() {
        Validator validator = new Validator();
        assertTrue(validator.isValid("AD1400080001001234567890"));
        assertFalse(validator.isValid("AA1400080001001234567890"));
        assertFalse(validator.isValid("AD1400080001001234567891"));
        assertFalse(validator.isValid("1400080001001234567890"));
        assertFalse(validator.isValid("AD1400"));
        assertFalse(validator.isValid("AD140008000100123456789@0*"));
    }
}
