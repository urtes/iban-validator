package com.urte.ibanvalidator.service;

import java.io.File;

public class ValidationService {

    public void validate(String iban) {
        System.out.println("Validating iban " + iban);
    }

    public void validate(File inputFile) {
        System.out.println("Validating inputFile " + inputFile.getPath());
    }
}
