package com.urte.ibanvalidator.service;

import java.io.File;
import java.math.BigInteger;

import static java.lang.Character.getNumericValue;

public class ValidationService {

    public static final int MIN_IBAN_LENGTH = 15;
    public static final int MAX_IBAN_LENGTH = 34;
    public static final BigInteger DIVIDER = new BigInteger("97");

    public final static String IBAN_IS_INVALID = "IBAN is invalid";
    public final static String IBAN_IS_VALID = "IBAN is valid";

    public boolean validate(String iban) {
        System.out.println("Validating iban " + iban);
        if (lengthIsValid(iban)
                && startsWithCountryCode(iban.substring(0, 2))
                && isAlphaNumeric(iban)) {
            String modifiedIban = (iban.substring(4) + iban.substring(0, 4));
            StringBuilder numericIban = new StringBuilder();
            modifiedIban.chars().forEach(character -> numericIban.append(appendChar(character)));
            BigInteger ibanNumber = new BigInteger(numericIban.toString());
            System.out.println(IBAN_IS_VALID);
            return ibanNumber.mod(DIVIDER).intValue() == 1;
        } else {
            System.out.println(IBAN_IS_INVALID);
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

    private int appendChar(int character) {
        return Character.isLetter(character) ? Character.toUpperCase(getNumericValue(character)) : character;
    }
}
