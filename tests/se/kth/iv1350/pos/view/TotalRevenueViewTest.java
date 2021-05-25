package se.kth.iv1350.pos.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class TotalRevenueViewTest {
    TotalRevenueView instanceToTest;
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;

    @BeforeEach
    void setUp() {
        instanceToTest = new TotalRevenueView();
        printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
    }

    @AfterEach
    void tearDown() {
        instanceToTest = null;
        printoutBuffer = null;
        System.setOut(originalSysOut);
    }

    @Test
    void doShowTotalRevenue() {
        instanceToTest.saleEnded(100);
        instanceToTest.doShowTotalRevenue();
        String printout = printoutBuffer.toString();
        String expectedOutput = "######## TotalRevenueView output ########\n" +
                                " total revenue:100.0\n" +
                                "#########################################";
        assertTrue(printout.contains(expectedOutput));
    }
}