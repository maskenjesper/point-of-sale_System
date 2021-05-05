package se.kth.iv1350.pos.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.DTO.SaleDTO;
import se.kth.iv1350.pos.model.Sale;

class AccountingTest {

    //////////////////////////////
    //          SETUP           //
    //////////////////////////////
    private Accounting accounting;

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
    void addSaleRecordTestCall() {
        accounting.addSaleRecord(new SaleDTO(new Sale()));
    }
}