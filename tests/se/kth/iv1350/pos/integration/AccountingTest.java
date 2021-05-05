package se.kth.iv1350.pos.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.DTO.SaleDTO;
import se.kth.iv1350.pos.model.Sale;

import static org.junit.jupiter.api.Assertions.*;

class AccountingTest {

    //////////////////////////////
    //          SETUP           //
    //////////////////////////////
    Accounting accounting;

    @BeforeEach
    void setUp() {
        accounting = new Accounting();
    }

    @AfterEach
    void tearDown() {
        accounting = null;
    }

    //////////////////////////////////////
    //          addSaleRecord()         //
    //////////////////////////////////////
    @Test
    void callAddSaleRecord() {
        accounting.addSaleRecord(new SaleDTO(new Sale()));
    }
}