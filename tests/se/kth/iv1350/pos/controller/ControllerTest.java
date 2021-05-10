package se.kth.iv1350.pos.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.DTO.ItemDTO;
import se.kth.iv1350.pos.DTO.SaleDTO;
import se.kth.iv1350.pos.integration.Inventory;

import static org.junit.jupiter.api.Assertions.*;


class ControllerTest {

    //////////////////////////////
    //          SETUP           //
    //////////////////////////////
    private Controller controller;

    @BeforeEach
    void setUp() throws Exception {
        controller = new Controller();
    }

    @AfterEach
    void tearDown() throws Exception {
        controller = null;
    }

    //////////////////////////////////
    //          startSale()         //
    //////////////////////////////////
    @Test
    void startSale() throws Exception {
        controller.startSale();
        controller.endRegistering();
    }

    //////////////////////////////////////
    //          addItemToSale()         //
    //////////////////////////////////////
    @Test
    void addItemToSaleTestItemDTO() throws Exception {
        controller.startSale();
        ItemDTO expectedResult = new Inventory().getItemInfo(1);
        SaleDTO saleDTO = controller.addItemToSale(1, 2);
        ItemDTO actualResult = saleDTO.getItemTable().getLastItemInTable();
        assertEquals(expectedResult, actualResult, "Item wasn't added correctly");
    }

    @Test
    void addItemToSaleTestQuantity() throws Exception {
        controller.startSale();
        int expectedResult = 2;
        SaleDTO saleDTO = controller.addItemToSale(1, 2);
        int actualResult = saleDTO.getItemTable().getTable().get(0).getQuantity();
        assertEquals(expectedResult, actualResult, "Quantity was incorrect");
    }

    @Test
    void addItemToSaleTestRunningTotalIncludingVAT() throws Exception {
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
    void endRegisteringTestTotalPriceOneItem() throws Exception {
        controller.startSale();
        controller.addItemToSale(1, 1);
        SaleDTO saleDTO = controller.endRegistering();
        double expectedResult = 25 * 1.2;
        double actualResult = saleDTO.getPaymentInformation().getTotalPrice();
        assertEquals(expectedResult, actualResult, "Total Price was not calculated correctly");
    }

    @Test
    void endRegisteringTestTotalPriceTwoDifferentItems() throws Exception {
        controller.startSale();
        controller.addItemToSale(1, 1);
        controller.addItemToSale(2, 1);
        SaleDTO saleDTO = controller.endRegistering();
        double expectedResult = 25 * 1.2 + 20 * 1.3;
        double actualResult = saleDTO.getPaymentInformation().getTotalPrice();
        assertEquals(expectedResult, actualResult, "Total Price was not calculated correctly");
    }

    @Test
    void endRegisteringTestTotalVATOneItem() throws Exception {
        controller.startSale();
        controller.addItemToSale(1, 1);
        SaleDTO saleDTO = controller.endRegistering();
        double expectedResult = 25 * 0.2;
        double actualResult = saleDTO.getPaymentInformation().getTotalVAT();
        assertEquals(expectedResult, actualResult, "Total VAT was not calculated correctly");
    }

    @Test
    void endRegisteringTestTotalVATTwoDifferentItems() throws Exception {
        controller.startSale();
        controller.addItemToSale(1, 1);
        controller.addItemToSale(2, 1);
        SaleDTO saleDTO = controller.endRegistering();
        double expectedResult = 25 * 0.2 + 20 * 0.3;
        double actualResult = saleDTO.getPaymentInformation().getTotalVAT();
        assertEquals(expectedResult, actualResult, "Total VAT was not calculated correctly");
    }

    ////////////////////////////////////////
    //          discountRequest()         //
    ////////////////////////////////////////
    @Test
    void discountRequestTestTotalPriceValid() throws Exception {
        controller.startSale();
        controller.addItemToSale(1, 1);
        controller.endRegistering();
        int eligibleCustomer = 123;
        SaleDTO saleDTO = controller.requestDiscount(eligibleCustomer);
        double expectedResult = 25 * 1.2 / 2;
        double actualResult = saleDTO.getPaymentInformation().getTotalPrice();
        assertEquals(expectedResult, actualResult, "Total Price was not correctly modified by discount");
    }

    @Test
    void discountRequestTestTotalVATValid() throws Exception {
        controller.startSale();
        controller.addItemToSale(1, 1);
        controller.endRegistering();
        int eligibleCustomer = 123;
        SaleDTO saleDTO = controller.requestDiscount(eligibleCustomer);
        double expectedResult = 25 * 0.2 / 2;
        double actualResult = saleDTO.getPaymentInformation().getTotalVAT();
        assertEquals(expectedResult, actualResult, "Total VAT was not correctly modified by discount");
    }

    @Test
    void discountRequestTestTotalPriceInvalid() throws Exception {
        controller.startSale();
        controller.addItemToSale(1, 1);
        controller.endRegistering();
        int ineligibleCustomer = 1;
        SaleDTO saleDTO = controller.requestDiscount(ineligibleCustomer);
        double expectedResult = 25 * 1.2;
        double actualResult = saleDTO.getPaymentInformation().getTotalPrice();
        assertEquals(expectedResult, actualResult, "Total Price was not correctly modified by discount");
    }

    @Test
    void discountRequestTestTotalVATInvalid() throws Exception {
        controller.startSale();
        controller.addItemToSale(1, 1);
        controller.endRegistering();
        int ineligibleCustomer = 1;
        SaleDTO saleDTO = controller.requestDiscount(ineligibleCustomer);
        double expectedResult = 25 * 0.2;
        double actualResult = saleDTO.getPaymentInformation().getTotalVAT();
        assertEquals(expectedResult, actualResult, "Total VAT was not correctly modified by discount");
    }

    //////////////////////////////////////
    //          addPayment()            //
    //////////////////////////////////////
    @Test
    void addPaymentTestAmountPaid() throws Exception {
        controller.startSale();
        controller.addItemToSale(1, 1);
        controller.endRegistering();
        double amountPaid = 1000;
        SaleDTO saleDTO = controller.addPayment(amountPaid);
        double expected = 1000;
        double actual = saleDTO.getPaymentInformation().getAmountPaid();
        assertEquals(expected, actual, "AmountPaid was not added correctly");
    }

    @Test
    void addPaymentTestChange() throws Exception {
        controller.startSale();
        controller.addItemToSale(1, 1);
        controller.endRegistering();
        double amountPaid = 1000;
        SaleDTO saleDTO = controller.addPayment(amountPaid);
        double expected = amountPaid - 25 * 1.2;
        double actual = saleDTO.getPaymentInformation().getChange();
        assertEquals(expected, actual, "Change was not calculated correctly");
    }
}