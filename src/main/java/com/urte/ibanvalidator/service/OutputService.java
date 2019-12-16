package com.urte.ibanvalidator.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class OutputService {

    public final static String OUT_EXTENSION = ".out";

    public void outputValidationResult(List<String> validatedIbans, String inFilePath) {
        File outputFile = new File(getOutFilePath(inFilePath));
        try {
            PrintWriter printWriter = new PrintWriter(outputFile);
            validatedIbans.forEach(iban -> printWriter.println(iban));
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String getOutFilePath(String inFilePath) {
        int indexOfDot = inFilePath.lastIndexOf('.');
        String name = inFilePath.substring(0, indexOfDot);
        return inFilePath.substring(0, indexOfDot) + OUT_EXTENSION;
    }
}
