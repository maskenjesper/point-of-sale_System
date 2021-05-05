package se.kth.iv1350.pos.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.DTO.ItemDTO;
import se.kth.iv1350.pos.DTO.SaleDTO;
import se.kth.iv1350.pos.integration.Inventory;

import static org.junit.jupiter.api.Assertions.*;


class ControllerTest { // Ska jag verkligen göra tester för controller? Det känns som jag bara checkar alla
                        // saker som redan checkas av Sale själv.

    //////////////////////////////
    //          SETUP           //
    //////////////////////////////
    private Controller controller;

    @BeforeEach
    void setUp() {
        controller = new Controller();
    }

    @AfterEach
    void tearDown() {
        controller = null;
    }

    //////////////////////////////////
    //          startSale()         //
    //////////////////////////////////
    @Test
    void startSale() {
        controller.startSale();
        controller.endRegistering();
    }

    //////////////////////////////////////
    //          addItemToSale()         //
    //////////////////////////////////////
    @Test
    void addItemToSaleItemDTO() {
        controller.startSale();
        ItemDTO expectedResult = new Inventory().getItemInfo(1);
        SaleDTO saleDTO = controller.addItemToSale(1, 2);
        ItemDTO actualResult = saleDTO.getItemTable().getLastItemInTable();
        assertEquals(expectedResult, actualResult, "Item wasn't added correctly");
    }

    @Test
    void addItemToSaleQuantity() {
        controller.startSale();
        int expectedResult = 2;
        SaleDTO saleDTO = controller.addItemToSale(1, 2);
        int actualResult = saleDTO.getItemTable().getTable().get(0).getQuantity();
        assertEquals(expectedResult, actualResult, "Quantity was incorrect");
    }

    @Test
    void addItemToSaleRunningTotalIncludingVAT() {
        controller.startSale();
        double expectedResult = 2 * 25 * 1.2;
        SaleDTO saleDTO = controller.addItemToSale(1, 2);
        double actualResult = saleDTO.getItemTable().getRunningTotalIncludingVAT();
        assertEquals(expectedResult, actualResult, "Running total including VAT was incorrect");
    }

    //////////////////////////////////////////
    //          endRegistering()            //
    //////////////////////////////////////////
    @Test
    void endRegistering() {
    }

    ////////////////////////////////////////
    //          discountRequest()         //
    ////////////////////////////////////////
    @Test
    void discountRequest() {
    }

    //////////////////////////////////////
    //          addPayment()            //
    //////////////////////////////////////
    @Test
    void addPayment() {
    }
}