package com.urte.cliapp;

import com.urte.cliapp.service.ValidationService;
import com.urte.engine.Validator;

import java.io.File;
import java.util.Scanner;

/**
 * Responsible for user input reception and validation. If the input is valid, passes it for further processing.
 */

public class App {

    private final static String ARGUMENTS_ERROR = "Please enter mode and data";
    private final static String MODE_ERROR = "Invalid mode";
    private final static String FILE_ERROR = "File not found";

    public static void main( String[] args ) {

        if (args.length != 2) {
            exit(ARGUMENTS_ERROR);
        }

        try {
            int mode = Integer.parseInt(args[0]);

            if (!isValidMode(mode)) {
                exit(MODE_ERROR);
            } else if (mode == 1) {
                validateIban(args[1]);
            } else {
                validateFile(args[1]);
            }

        } catch (NumberFormatException exception) {
            exit(MODE_ERROR);
        }
    }

    private static boolean isValidMode(int mode) {
        return mode == 1 || mode == 2;
    }

    private static void validateIban(String iban) {
        ValidationService validationService = new ValidationService(new Validator());
        validationService.validate(iban);
    }

    private static void validateFile(String filePath) {
        File inputFile = new File(filePath);

        if (inputFile.exists()) {
            ValidationService validationService = new ValidationService(new Validator());
            validationService.validate(inputFile);
        } else {
            exit(FILE_ERROR);
        }
    }

    private static void exit(String errorMessage) {
        System.out.println(errorMessage);
        System.exit(1);
    }
}
