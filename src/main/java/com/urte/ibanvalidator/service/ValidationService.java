package com.urte.ibanvalidator.service;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Character.getNumericValue;

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
        try (Stream<String> ibans = Files.lines(Paths.get(inputFile.getPath()))) {
            List<String> validatedIbans = ibans
                    .map(String::trim)
                    .filter(iban -> !iban.isEmpty())
                    .map(iban -> iban + ";" +  validate(iban))
                    .collect(Collectors.toList());
            OutputService outputService = new OutputService();
            outputService.outputValidationResult(validatedIbans, inputFile.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
