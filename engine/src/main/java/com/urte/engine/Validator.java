package com.urte.engine;

import java.math.BigInteger;

/**
 * Class that is responsible for IBAN validation.
 */
public class Validator {

    /**
     * Used to perform basic mod-97 operation (as described in ISO 7064), required for IBAN validation procedure.
     */
    private static final BigInteger DIVIDER = new BigInteger("97");

    private final static String IBAN_IS_INVALID = "IBAN is invalid";
    private final static String IBAN_IS_VALID = "IBAN is valid";

    private static final int MIN_IBAN_LENGTH = 15;
    private static final int MAX_IBAN_LENGTH = 34;

    public boolean isValid(String iban) {

        if (isLengthInRange(iban)
                && countryCodeMatchesLength(iban)
                && isAlphaNumeric(iban)) {
            String modifiedIban = iban.substring(4) + iban.substring(0, 4);
            StringBuilder numericIban = new StringBuilder();

            for (int i = 0; i < modifiedIban.length(); i++) {
                // Each character is transformtd to numeric value, since it is assumed that checking weather it's
                // letter or digit would be the same speed wise.
                numericIban.append(Character.getNumericValue(modifiedIban.charAt(i)));
            }

            BigInteger ibanNumber = new BigInteger(numericIban.toString());

            if (ibanNumber.mod(DIVIDER).intValue() == 1) {
                System.out.println(iban + " " + IBAN_IS_VALID);
                return true;
            } else {
                System.out.println(iban + " " + IBAN_IS_INVALID);
                return false;
            }

        } else {
            System.out.println(iban + " " + IBAN_IS_INVALID);
            return false;
        }
    }

    private boolean isLengthInRange(String iban) {
        return iban.length() >= MIN_IBAN_LENGTH && iban.length() <= MAX_IBAN_LENGTH;
    }

    private boolean countryCodeMatchesLength(String iban) {
        String countryCode = iban.substring(0, 2).toUpperCase();
        return IbanByCountry.LENGTH_BY_COUNTRY.containsKey(countryCode)
                && iban.length() == IbanByCountry.LENGTH_BY_COUNTRY.get(countryCode);
    }

    private boolean isAlphaNumeric(String iban) {
        return iban.chars().allMatch(Character::isLetterOrDigit);
    }
}
