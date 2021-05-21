package se.kth.iv1350.pos.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ViewTest {
    private View instanceToTest;
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;

    @BeforeEach
    void setUp() {
        instanceToTest = new View();
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
    void sampleExecutionV1StartSale() {
        instanceToTest.sampleExecution();
        String printout = printoutBuffer.toString();
        String expectedOutput = "START SALE:";
        assertTrue(printout.contains(expectedOutput));
    }

    @Test
    void sampleExecutionV1AddItemID1Q20() {
        instanceToTest.sampleExecution();
        String printout = printoutBuffer.toString();
        String expectedOutput = "Item added: Mjölk: Naturens sportdryck\n" +
                                "Price: 25.0 | VAT: 20.0%\n" +
                                "Running total: 600.0 SEK";
        assertTrue(printout.contains(expectedOutput));
    }

    @Test
    void sampleExecutionV1AddItemID2Q20() {
        instanceToTest.sampleExecution();
        String printout = printoutBuffer.toString();
        String expectedOutput = "Item added: Levain: Nybakat surdegsbröd\n" +
                                "Price: 20.0 | VAT: 30.0%\n" +
                                "Running total: 1120.0 SEK";
        assertTrue(printout.contains(expectedOutput));
    }

    @Test
    void sampleExecutionV1AddItemID1Q5Again() {
        instanceToTest.sampleExecution();
        String printout = printoutBuffer.toString();
        String expectedOutput = "Item added: Mjölk: Naturens sportdryck\n" +
                                "Price: 25.0 | VAT: 20.0%\n" +
                                "Running total: 1270.0 SEK";
        assertTrue(printout.contains(expectedOutput));
    }

    @Test
    void sampleExecutionV1AddItemID3Q20() {
        instanceToTest.sampleExecution();
        String printout = printoutBuffer.toString();
        String expectedOutput = "Invalid item identifier: 3";
        assertTrue(printout.contains(expectedOutput));
    }

    @Test
    void sampleExecutionV1AddItemID500Q20() {
        instanceToTest.sampleExecution();
        String printout = printoutBuffer.toString();
        String expectedOutput = "Inventory failure";
        assertTrue(printout.contains(expectedOutput));
    }

    @Test
    void sampleExecutionV1EndRegistering() {
        instanceToTest.sampleExecution();
        String printout = printoutBuffer.toString();
        String expectedOutput = "Sale ended\n" +
                                "Total price: 1270.0 SEK";
        assertTrue(printout.contains(expectedOutput));
    }

    @Test
    void sampleExecutionV1RequestDiscountInvalid() {
        instanceToTest.sampleExecution();
        String printout = printoutBuffer.toString();
        String expectedOutput = "Price after discount: 1270.0 SEK";
        assertTrue(printout.contains(expectedOutput));
    }
    @Test
    void sampleExecutionV1RequestDiscountValid() {
        instanceToTest.sampleExecution();
        String printout = printoutBuffer.toString();
        String expectedOutput = "Price after discount: 635.0 SEK";
        assertTrue(printout.contains(expectedOutput));
    }

    @Test
    void sampleExecutionV1AddPayment() {
        instanceToTest.sampleExecution();
        String printout = printoutBuffer.toString();
        String expectedOutput = "Change to give: 1365.0";
        assertTrue(printout.contains(expectedOutput));
    }

    @Test
    void sampleExecutionV2StartSale() {
        instanceToTest.sampleExecution();
        String printout = printoutBuffer.toString();
        String expectedOutput = "START SALE:";
        assertTrue(printout.contains(expectedOutput));
    }

    @Test
    void sampleExecutionV2AddItemID1Q1() {
        instanceToTest.sampleExecution();
        String printout = printoutBuffer.toString();
        String expectedOutput = "Item added: Mjölk: Naturens sportdryck\n" +
                                "Price: 25.0 | VAT: 20.0%\n" +
                                "Running total: 30.0 SEK";
        assertTrue(printout.contains(expectedOutput));
    }

    @Test
    void sampleExecutionV2AddItemID2Q1() {
        instanceToTest.sampleExecution();
        String printout = printoutBuffer.toString();
        String expectedOutput = "Item added: Levain: Nybakat surdegsbröd\n" +
                                "Price: 20.0 | VAT: 30.0%\n" +
                                "Running total: 56.0 SEK";
        assertTrue(printout.contains(expectedOutput));
    }

    @Test
    void sampleExecutionV2EndRegistering() {
        instanceToTest.sampleExecution();
        String printout = printoutBuffer.toString();
        String expectedOutput = "Sale ended\n" +
                                "Total price: 56.0 SEK";
        assertTrue(printout.contains(expectedOutput));
    }

    @Test
    void sampleExecutionV2AddPayment() {
        instanceToTest.sampleExecution();
        String printout = printoutBuffer.toString();
        String expectedOutput = "Change to give: 1944.0";
        assertTrue(printout.contains(expectedOutput));
    }
}