package se.kth.iv1350.pos.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.DTO.SaleDTO;
import se.kth.iv1350.pos.model.Sale;

class ReceiptPrinterTest {

    //////////////////////////////
    //          SETUP           //
    //////////////////////////////
    private ReceiptPrinter receiptPrinter;

    @BeforeEach
    void setUp() {
        receiptPrinter = new ReceiptPrinter();
    }

    @AfterEach
    void tearDown() {
        receiptPrinter = null;
    }

    //////////////////////////////////////
    //          printReceipt()          //
    //////////////////////////////////////
    @Test
    void printReceiptTestCall() {
        receiptPrinter.printReceipt(new SaleDTO(new Sale()));
    }
}