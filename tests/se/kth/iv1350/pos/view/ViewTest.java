package se.kth.iv1350.pos.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.utility.Time;

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

    @Test
    void sampleExecutionTotalRevenueV1() {
        instanceToTest.sampleExecution();
        String printout = printoutBuffer.toString();
        String expectedOutput = "######## TotalRevenueView output ########\n" +
                                " total revenue:635.0\n" +
                                "#########################################";
        assertTrue(printout.contains(expectedOutput));
    }

    @Test
    void sampleExecutionTotalRevenueV2() {
        instanceToTest.sampleExecution();
        String printout = printoutBuffer.toString();
        String expectedOutput = "######## TotalRevenueView output ########\n" +
                                " total revenue:691.0\n" +
                                "#########################################";
        assertTrue(printout.contains(expectedOutput));
    }

    @Test
    void sampleExecutionDeveloperLog() {
        instanceToTest.sampleExecution();
        String printout = printoutBuffer.toString();
        String expectedOutput = "DEVELOPER LOG: The database server is not running";
        assertTrue(printout.contains(expectedOutput));
    }

    @Test
    void sampleExecutionReceipt1() {
        instanceToTest.sampleExecution();
        String printout = printoutBuffer.toString();
        String expectedOutput = "Totalt pris: 635.0\n" +
                                "Varav VAT: 122.5\n" +
                                "Betalat: 2000.0\n" +
                                "Växel: 1365.0\n" +
                                "Datum och tid: ";
        assertTrue(printout.contains(expectedOutput));
        expectedOutput = "Butik: Jakobs liv's\n" +
                        "Adress: Gatuvägen, Bostadsstaden, Sverige, 12345\n" +
                        "| 20st | 20.0SEK (VAT: 30.0%) | Levain: Nybakat surdegsbröd | ID: 2 |\n" +
                        "| 25st | 25.0SEK (VAT: 20.0%) | Mjölk: Naturens sportdryck | ID: 1 |\n";
        assertTrue(printout.contains(expectedOutput));
    }

    @Test
    void sampleExecutionReceipt2() {
        instanceToTest.sampleExecution();
        String printout = printoutBuffer.toString();
        String expectedOutput = "Totalt pris: 56.0\n" +
                                "Varav VAT: 11.0\n" +
                                "Betalat: 2000.0\n" +
                                "Växel: 1944.0\n" +
                                "Datum och tid: ";
        assertTrue(printout.contains(expectedOutput));
        expectedOutput = "Butik: Jakobs liv's\n" +
                        "Adress: Gatuvägen, Bostadsstaden, Sverige, 12345\n" +
                        "| 1st | 25.0SEK (VAT: 20.0%) | Mjölk: Naturens sportdryck | ID: 1 |\n" +
                        "| 1st | 20.0SEK (VAT: 30.0%) | Levain: Nybakat surdegsbröd | ID: 2 |\n";
        assertTrue(printout.contains(expectedOutput));
    }
}