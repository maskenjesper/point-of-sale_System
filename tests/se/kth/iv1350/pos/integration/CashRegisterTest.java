package se.kth.iv1350.pos.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.DTO.SaleDTO;
import se.kth.iv1350.pos.model.Sale;

import static org.junit.jupiter.api.Assertions.*;

class CashRegisterTest {

    //////////////////////////////
    //          SETUP           //
    //////////////////////////////
    CashRegister cashRegister;

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
    void callAddPayment() {
        cashRegister.addPayment(new SaleDTO(new Sale()));
    }
}