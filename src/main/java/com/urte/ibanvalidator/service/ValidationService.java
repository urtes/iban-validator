package com.urte.ibanvalidator.service;

import java.io.File;
import java.math.BigInteger;

public class ValidationService {

    public static final int MIN_IBAN_LENGTH = 15;
    public static final int MAX_IBAN_LENGTH = 34;
    public static final BigInteger DIVIDER = new BigInteger("97");

    public final static String INVALID_IBAN = "IBAN is invalid";
    public final static String VALID_IBAN = "IBAN is valid";

    public boolean validate(String iban) {
        System.out.println("Validating iban " + iban);
        if (lengthIsValid(iban) && startsWithCountryCode(iban.substring(0, 2)) && isAlphaNumeric(iban)) {
            String modifiedIban = (iban.substring(4) + iban.substring(0, 4)).toUpperCase();
            StringBuilder numericIban = new StringBuilder();
            modifiedIban.chars().forEach(character -> numericIban.append(Character.getNumericValue(character)));
            BigInteger ibanNumber = new BigInteger(numericIban.toString());
            System.out.println(VALID_IBAN);
            return ibanNumber.mod(DIVIDER).intValue() == 1;
        } else {
            System.out.println(INVALID_IBAN);
            return false;
        }
    }

    public void validate(File inputFile) {
        System.out.println("Validating inputFile " + inputFile.getPath());
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
