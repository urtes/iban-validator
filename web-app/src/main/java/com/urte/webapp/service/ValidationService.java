package com.urte.webapp.service;

import com.urte.engine.IbanSource;
import com.urte.engine.Validator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service that is responsible for IBAN validation
 */
@Service
public class ValidationService {

    private Validator validator;

    public ValidationService(Validator validator) {
        this.validator = validator;
    }

    public List<IbanSource> validate(List<String> request) {
        List<IbanSource> ibansToValidate = getIbansToValidate(request);
        List<IbanSource> ibansWithValidation = new ArrayList<>();
        ibansToValidate
                .forEach(ibanSource -> {
                    ibanSource.setValid(validator.isValid(ibanSource.getIban()));
                    ibansWithValidation.add(ibanSource);
                });
        return ibansWithValidation;
    }

    public List<IbanSource> getIbansToValidate(List<String> request) {
        return request
                .stream()
                .map(IbanSource::new)
                .collect(Collectors.toList());
    }
}
