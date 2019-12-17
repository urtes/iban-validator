package com.urte.cliapp;

import com.urte.cliapp.service.ValidationService;
import com.urte.engine.Validator;

import java.io.File;
import java.util.Scanner;

/**
 * Responsible for user input reception and validation. If the input is valid, passes it for further processing.
 */

public class App
{
    private final static String PROMPT =
            "Please enter:\n validation mode (1 or 2)\n iban - for mode 1 or file name with full path - for mode 2";

    private final static String MODE_ERROR = "Invalid mode";
    private final static String FILE_ERROR = "File not found";

    private static Scanner scanner = new Scanner(System.in);
    private static Validator validator = new Validator();

    public static void main( String[] args ) {
        System.out.println(PROMPT);
        String modeInput = scanner.nextLine();
        try {
            int mode = Integer.parseInt(modeInput);
            if (!isValidMode(mode)) {
                exit(MODE_ERROR);
            } else if (mode == 1) {
                validateIban();
            } else {
                validateFile();
            }
        } catch (NumberFormatException exception) {
            exit(MODE_ERROR);
        }
    }

    private static boolean isValidMode(int mode) {
        return mode == 1 || mode == 2;
    }

    private static void validateIban() {
        String iban = scanner.nextLine().trim();
        validator.isValid(iban);
    }

    private static void validateFile() {
        String fileName = scanner.nextLine().trim();
        File inputFile = new File(fileName);
        if (inputFile.exists()) {
            ValidationService validationService = new ValidationService(validator);
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
