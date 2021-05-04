package se.kth.iv1350.pos.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.DTO.SaleDTO;
import se.kth.iv1350.pos.model.Sale;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptPrinterTest {

    //////////////////////////////
    //          SETUP           //
    //////////////////////////////
    ReceiptPrinter receiptPrinter;
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
    void callPrintReceipt() {
        receiptPrinter.printReceipt(new SaleDTO(new Sale()));
    }
}