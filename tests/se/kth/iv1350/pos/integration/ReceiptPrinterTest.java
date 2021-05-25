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
    private SaleDTO testSaleDTO;
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;

    @BeforeEach
    void setUp() {
        receiptPrinter = new ReceiptPrinter();
        testSaleDTO = buildTestSaleDTO();
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
    void printReceipt() {
        receiptPrinter.printReceipt(testSaleDTO);
        String printout = printoutBuffer.toString();
        String expectedOutput = "Totalt pris: 30.0\n" +
                                "Varav VAT: 5.0\n" +
                                "Betalat: 100.0\n" +
                                "Växel: 70.0\n" +
                                "Datum och tid: ";
        assertTrue(printout.contains(expectedOutput));
        expectedOutput = "Butik: Jakobs liv's\n" +
                        "Adress: Gatuvägen, Bostadsstaden, Sverige, 12345";
        assertTrue(printout.contains(expectedOutput));
    }

    //////////////////////////////
    //          Helper          //
    //////////////////////////////
    private SaleDTO buildTestSaleDTO() {
        Sale sale = new Sale();
        sale.addItem(new ItemTableEntryDTO(ItemRegistry.getInstance().getItemList().get(0),1));
        sale.endRegistering();
        sale.addPayment(100);
        return new SaleDTO(sale);
    }
}