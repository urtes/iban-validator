package com.urte.cliapp;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.SystemOutRule;


public class AppTest
{
    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void exitsIfArgsLengthIncorrect() {
        exit.expectSystemExitWithStatus(1);
        exit.checkAssertionAfterwards(() -> assertEquals("Please enter mode and data\n",
                systemOutRule.getLogWithNormalizedLineSeparator()));
        App.main(new String[]{"A"});
    }

    @Test
    public void exitsIfCannotParseMode() {
        exit.expectSystemExitWithStatus(1);
        exit.checkAssertionAfterwards(() -> assertEquals("Invalid mode\n",
                systemOutRule.getLogWithNormalizedLineSeparator()));
        App.main(new String[]{"A", "B"});
    }

    @Test
    public void exitsIfModeInvalid() {
        exit.expectSystemExitWithStatus(1);
        exit.checkAssertionAfterwards(() -> assertEquals("Invalid mode\n",
                systemOutRule.getLogWithNormalizedLineSeparator()));
        App.main(new String[]{"0", "B"});
    }

    @Test
    public void exitsIfFileNotFound() {
        exit.expectSystemExitWithStatus(1);
        exit.checkAssertionAfterwards(() -> assertEquals("File not found\n",
                systemOutRule.getLogWithNormalizedLineSeparator()));
        App.main(new String[]{"2", "B"});
    }
}
