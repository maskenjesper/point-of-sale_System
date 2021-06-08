package se.kth.iv1350.pos.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.DTO.ItemTableEntryDTO;
import se.kth.iv1350.pos.DTO.SaleDTO;
import se.kth.iv1350.pos.data.ItemRegistry;
import se.kth.iv1350.pos.model.Sale;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ReceiptPrinterTest {

    //////////////////////////////
    //          SETUP           //
    //////////////////////////////
    private ReceiptPrinter receiptPrinter;
    private SaleDTO testSaleDTOV1;
    private SaleDTO testSaleDTOV2;
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;

    @BeforeEach
    void setUp() {
        receiptPrinter = new ReceiptPrinter();
        testSaleDTOV1 = buildTestSaleDTOV1();
        testSaleDTOV2 = buildTestSaleDTOV2();
        printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
    }

    @AfterEach
    void tearDown() {
        receiptPrinter = null;
        printoutBuffer = null;
        System.setOut(originalSysOut);
    }

    //////////////////////////////////////
    //          printReceipt()          //
    //////////////////////////////////////
    @Test
    void printReceiptV1() {
        receiptPrinter.printReceipt(testSaleDTOV1);
        String printout = printoutBuffer.toString();
        String expectedOutput = "Totalt pris: 30.0\n" +
                                "Varav VAT: 5.0\n" +
                                "Betalat: 100.0\n" +
                                "Växel: 70.0\n" +
                                "Datum och tid: ";
        assertTrue(printout.contains(expectedOutput), "The correct output for first half of receipt was not displayed");
        expectedOutput = "Butik: Jakobs liv's\n" +
                        "Adress: Gatuvägen, Bostadsstaden, Sverige, 12345\n" +
                        "| 1st | 25.0SEK (VAT: 20.0%) | Mjölk: Naturens sportdryck | ID: 1 |\n";
        assertTrue(printout.contains(expectedOutput), "The correct output for second half of receipt was not displayed");
    }

    @Test
    void printReceiptV2() {
        receiptPrinter.printReceipt(testSaleDTOV2);
        String printout = printoutBuffer.toString();
        String expectedOutput = "Totalt pris: 86.0\n" +
                "Varav VAT: 16.0\n" +
                "Betalat: 100.0\n" +
                "Växel: 14.0\n" +
                "Datum och tid: ";
        assertTrue(printout.contains(expectedOutput), "The correct output for first half of receipt was not displayed");
        expectedOutput = "Butik: Jakobs liv's\n" +
                        "Adress: Gatuvägen, Bostadsstaden, Sverige, 12345\n" +
                        "| 2st | 25.0SEK (VAT: 20.0%) | Mjölk: Naturens sportdryck | ID: 1 |\n" +
                        "| 1st | 20.0SEK (VAT: 30.0%) | Levain: Nybakat surdegsbröd | ID: 2 |\n";
        assertTrue(printout.contains(expectedOutput), "The correct output for second half of receipt was not displayed");
    }

    //////////////////////////////
    //          Helper          //
    //////////////////////////////
    private SaleDTO buildTestSaleDTOV1() {
        Sale sale = new Sale();
        sale.addItem(new ItemTableEntryDTO(ItemRegistry.getInstance().getItemList().get(0),1));
        sale.endRegistering();
        sale.addPayment(100);
        return new SaleDTO(sale);
    }

    private SaleDTO buildTestSaleDTOV2() {
        Sale sale = new Sale();
        sale.addItem(new ItemTableEntryDTO(ItemRegistry.getInstance().getItemList().get(0),1));
        sale.addItem(new ItemTableEntryDTO(ItemRegistry.getInstance().getItemList().get(0),1));
        sale.addItem(new ItemTableEntryDTO(ItemRegistry.getInstance().getItemList().get(1),1));
        sale.endRegistering();
        sale.addPayment(100);
        return new SaleDTO(sale);
    }
}