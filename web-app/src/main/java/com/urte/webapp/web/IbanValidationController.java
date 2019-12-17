package com.urte.webapp.web;

import com.urte.engine.IbanSource;
import com.urte.webapp.service.ValidationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller that receives and handles HTTP requests for IBAN validation
 */
@RestController
public class IbanValidationController {

    private ValidationService validationService;

    public IbanValidationController(ValidationService validationService) {
        this.validationService = validationService;
    }

    @PostMapping("/validate")
    public List<IbanSource> validate(@RequestBody List<String> request) {
        return validationService.validate(request);
    }
}
