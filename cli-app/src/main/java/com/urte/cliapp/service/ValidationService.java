package com.urte.cliapp.service;

import com.urte.engine.IbanSource;
import com.urte.engine.Validator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Service that is responsible for IBAN validation
 */
public class ValidationService {

    Validator validator;

    public ValidationService(Validator validator) {
        this.validator = validator;
    }

    public void validate(String iban) {
        validator.isValid(iban);
    }

    public void validate(File inputFile) {
        InOutService inOutService = new InOutService();
        List<IbanSource> ibansToValidate = inOutService.getIbansToValidate(inputFile);
        List<IbanSource> ibansWithValidation = new ArrayList<>();
        ibansToValidate
                .forEach(ibanSource -> {
                    ibanSource.setValid(validator.isValid(ibanSource.getIban()));
                    ibansWithValidation.add(ibanSource);
                });
        inOutService.outputValidationResult(ibansWithValidation, inputFile.getPath());
    }
}
