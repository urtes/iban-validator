package com.urte.cliapp.service;


import com.urte.engine.IbanSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Service that is responsible for reading IBAN data from input file outputing IBAN validation results to output file
 */
public class InOutService {

    public final static String OUT_EXTENSION = ".out";

    public List<IbanSource> getIbansToValidate(File inputFile) {
        List<IbanSource> ibans = new ArrayList<>();
        try (Stream<String> ibansInFile = Files.lines(Paths.get(inputFile.getPath()))) {
            ibans  = ibansInFile
                    .map(String::trim)
                    .filter(iban -> !iban.isEmpty())
                    .map(IbanSource::new)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ibans;
    }

    public void outputValidationResult(List<IbanSource> validatedIbans, String inFilePath) {
        File outputFile = new File(getOutFilePath(inFilePath));
        try {
            PrintWriter printWriter = new PrintWriter(outputFile);
            validatedIbans.forEach(printWriter::println);
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String getOutFilePath(String inFilePath) {
        int indexOfDot = inFilePath.lastIndexOf('.');
        return inFilePath.substring(0, indexOfDot) + OUT_EXTENSION;
    }
}
