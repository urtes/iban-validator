package com.urte.ibanvalidator.service;

import java.io.File;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Service that is responsible for IBAN validation
 */
public class ValidationService {

    public static final int MIN_IBAN_LENGTH = 15;
    public static final int MAX_IBAN_LENGTH = 34;
    public static final BigInteger DIVIDER = new BigInteger("97");

    public final static String IBAN_IS_INVALID = "IBAN is invalid";
    public final static String IBAN_IS_VALID = "IBAN is valid";

    public boolean validate(String iban) {
        if (lengthIsValid(iban)
                && startsWithCountryCode(iban.substring(0, 2))
                && isAlphaNumeric(iban)) {
            String modifiedIban = (iban.substring(4) + iban.substring(0, 4));
            StringBuilder numericIban = new StringBuilder();
            for (int i = 0; i < modifiedIban.length(); i++) {
                numericIban.append(Character.getNumericValue(modifiedIban.charAt(i)));
            }
            BigInteger ibanNumber = new BigInteger(numericIban.toString());
            if (ibanNumber.mod(DIVIDER).intValue() == 1) {
                System.out.println(iban + IBAN_IS_VALID);
                return true;
            } else {
                System.out.println(iban + IBAN_IS_INVALID);
                return false;
            }
        } else {
            System.out.println(iban + IBAN_IS_INVALID);
            return false;
        }
    }

    public void validate(File inputFile) {
        InOutService inOutService = new InOutService();
        List<String> ibansToValidate = inOutService.getIbansToValidate(inputFile);
        List<String> ibansWithValidation = ibansToValidate
                .stream()
                .map(iban -> iban + ";" + validate(iban))
                .collect(Collectors.toList());
        inOutService.outputValidationResult(ibansWithValidation, inputFile.getPath());
    }

    private boolean lengthIsValid(String iban) {
        return iban.length() >= MIN_IBAN_LENGTH && iban.length() <= MAX_IBAN_LENGTH;
    }

    private boolean startsWithCountryCode(String countryCode) {
        return countryCode.chars().allMatch(Character::isLetter);
    }

    private boolean isAlphaNumeric(String iban) {
        return iban.chars().allMatch(Character::isLetterOrDigit);
    }
}
