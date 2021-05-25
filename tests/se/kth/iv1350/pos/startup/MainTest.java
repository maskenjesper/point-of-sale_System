package se.kth.iv1350.pos.startup;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    //////////////////////////////
    //          SETUP           //
    //////////////////////////////
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;

    @BeforeEach
    void setUp() {
        printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
        String[] args = new String[1];
        Main.main(args);
    }

    @AfterEach
    void tearDown() {
        printoutBuffer = null;
        System.setOut(originalSysOut);
    }

    /////////////////////////////
    //          main           //
    /////////////////////////////
    @Test
    void mainV1StartSale() {
        String printout = printoutBuffer.toString();
        String expectedOutput = "START SALE:";
        assertTrue(printout.contains(expectedOutput));
    }

    @Test
    void mainV1AddItemID1Q20() {
        String printout = printoutBuffer.toString();
        String expectedOutput = "Item added: Mjölk: Naturens sportdryck\n" +
                "Price: 25.0 | VAT: 20.0%\n" +
                "Running total: 600.0 SEK";
        assertTrue(printout.contains(expectedOutput));
    }

    @Test
    void mainV1AddItemID2Q20() {
        String printout = printoutBuffer.toString();
        String expectedOutput = "Item added: Levain: Nybakat surdegsbröd\n" +
                "Price: 20.0 | VAT: 30.0%\n" +
                "Running total: 1120.0 SEK";
        assertTrue(printout.contains(expectedOutput));
    }

    @Test
    void mainV1AddItemID1Q5Again() {
        String printout = printoutBuffer.toString();
        String expectedOutput = "Item added: Mjölk: Naturens sportdryck\n" +
                "Price: 25.0 | VAT: 20.0%\n" +
                "Running total: 1270.0 SEK";
        assertTrue(printout.contains(expectedOutput));
    }

    @Test
    void mainV1AddItemID3Q20() {
        String printout = printoutBuffer.toString();
        String expectedOutput = "Invalid item identifier: 3";
        assertTrue(printout.contains(expectedOutput));
    }

    @Test
    void mainV1AddItemID500Q20() {
        String printout = printoutBuffer.toString();
        String expectedOutput = "Inventory failure";
        assertTrue(printout.contains(expectedOutput));
    }

    @Test
    void mainV1EndRegistering() {
        String printout = printoutBuffer.toString();
        String expectedOutput = "Sale ended\n" +
                "Total price: 1270.0 SEK";
        assertTrue(printout.contains(expectedOutput));
    }

    @Test
    void mainV1RequestDiscountInvalid() {
        String printout = printoutBuffer.toString();
        String expectedOutput = "Price after discount: 1270.0 SEK";
        assertTrue(printout.contains(expectedOutput));
    }
    @Test
    void mainV1RequestDiscountValid() {
        String printout = printoutBuffer.toString();
        String expectedOutput = "Price after discount: 635.0 SEK";
        assertTrue(printout.contains(expectedOutput));
    }

    @Test
    void mainV1AddPayment() {
        String printout = printoutBuffer.toString();
        String expectedOutput = "Change to give: 1365.0";
        assertTrue(printout.contains(expectedOutput));
    }

    @Test
    void mainV2StartSale() {
        String printout = printoutBuffer.toString();
        String expectedOutput = "START SALE:";
        assertTrue(printout.contains(expectedOutput));
    }

    @Test
    void mainV2AddItemID1Q1() {
        String printout = printoutBuffer.toString();
        String expectedOutput = "Item added: Mjölk: Naturens sportdryck\n" +
                "Price: 25.0 | VAT: 20.0%\n" +
                "Running total: 30.0 SEK";
        assertTrue(printout.contains(expectedOutput));
    }

    @Test
    void mainV2AddItemID2Q1() {
        String printout = printoutBuffer.toString();
        String expectedOutput = "Item added: Levain: Nybakat surdegsbröd\n" +
                "Price: 20.0 | VAT: 30.0%\n" +
                "Running total: 56.0 SEK";
        assertTrue(printout.contains(expectedOutput));
    }

    @Test
    void mainV2EndRegistering() {
        String printout = printoutBuffer.toString();
        String expectedOutput = "Sale ended\n" +
                "Total price: 56.0 SEK";
        assertTrue(printout.contains(expectedOutput));
    }

    @Test
    void mainV2AddPayment() {
        String printout = printoutBuffer.toString();
        String expectedOutput = "Change to give: 1944.0";
        assertTrue(printout.contains(expectedOutput));
    }

    @Test
    void mainTotalRevenueV1() {
        String printout = printoutBuffer.toString();
        String expectedOutput = "######## TotalRevenueView output ########\n" +
                " total revenue:635.0\n" +
                "#########################################";
        assertTrue(printout.contains(expectedOutput));
    }

    @Test
    void mainTotalRevenueV2() {
        String printout = printoutBuffer.toString();
        String expectedOutput = "######## TotalRevenueView output ########\n" +
                " total revenue:691.0\n" +
                "#########################################";
        assertTrue(printout.contains(expectedOutput));
    }

    @Test
    void mainDeveloperLog() {
        String printout = printoutBuffer.toString();
        String expectedOutput = "DEVELOPER LOG: The database server is not running";
        assertTrue(printout.contains(expectedOutput));
    }

    @Test
    void mainReceipt1() {
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
    void mainReceipt2() {
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