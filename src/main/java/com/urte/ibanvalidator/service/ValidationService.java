package com.urte.ibanvalidator.service;

import com.urte.ibanvalidator.IbanByCountry;
import com.urte.ibanvalidator.domain.IbanSource;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


/**
 * Service that is responsible for IBAN validation
 */
public class ValidationService {

    /**
     * Used to perform basic mod-97 operation (as described in ISO 7064), required for IBAN validation procedure
     */
    public static final BigInteger DIVIDER = new BigInteger("97");

    public final static String IBAN_IS_INVALID = "IBAN is invalid";
    public final static String IBAN_IS_VALID = "IBAN is valid";

    public boolean isValid(String iban) {
        if (countryCodeMatchesLength(iban) && isAlphaNumeric(iban)) {
            String modifiedIban = iban.substring(4) + iban.substring(0, 4);
            StringBuilder numericIban = new StringBuilder();
            for (int i = 0; i < modifiedIban.length(); i++) {
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

    public void validate(File inputFile) {
        InOutService inOutService = new InOutService();
        List<IbanSource> ibansToValidate = inOutService.getIbansToValidate(inputFile);
        List<IbanSource> ibansWithValidation = new ArrayList<>();
        ibansToValidate
                .forEach(ibanSource -> {
                    ibanSource.setValid(isValid(ibanSource.getIban()));
                    ibansWithValidation.add(ibanSource);
                });
        inOutService.outputValidationResult(ibansWithValidation, inputFile.getPath());
    }

    private boolean countryCodeMatchesLength(String iban) {
        String countryCode = iban.substring(0, 2);
        countryCode = IbanByCountry.IBAN_BY_COUNTRY.containsKey(countryCode) ? countryCode : null;
        return countryCode != null
               && iban.length() == IbanByCountry.IBAN_BY_COUNTRY.get(countryCode);
    }

    private boolean isAlphaNumeric(String iban) {
        return iban.chars().allMatch(Character::isLetterOrDigit);
    }
}
