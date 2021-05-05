package se.kth.iv1350.pos.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.DTO.SaleDTO;
import se.kth.iv1350.pos.model.Sale;

class CashRegisterTest {

    //////////////////////////////
    //          SETUP           //
    //////////////////////////////
    private CashRegister cashRegister;

    @BeforeEach
    void setUp() {
        cashRegister = new CashRegister();
    }

    @AfterEach
    void tearDown() {
        cashRegister = null;
    }

    //////////////////////////////////////
    //          addPayment()            //
    //////////////////////////////////////
    @Test
    void addPaymentTestCall() {
        cashRegister.addPayment(new SaleDTO(new Sale()));
    }
}