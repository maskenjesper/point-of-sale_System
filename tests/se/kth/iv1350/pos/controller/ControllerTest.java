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
    void addItemToSaleTestItemDTO() {
        SaleDTO saleDTO = createSaleWithTwoSameItems();
        ItemDTO expectedResult = getItemInfoAt(1);
        ItemDTO actualResult = saleDTO.getItemTable().getLastItemInTable();
        assertEquals(expectedResult, actualResult, "Item wasn't added correctly");
    }

    @Test
    void addItemToSaleTestQuantity() {
        SaleDTO saleDTO = createSaleWithTwoSameItems();
        int expectedResult = 2;
        int actualResult = saleDTO.getItemTable().getTable().get(0).getQuantity();
        assertEquals(expectedResult, actualResult, "Quantity was incorrect");
    }

    @Test
    void addItemToSaleTestRunningTotalIncludingVAT() {
        SaleDTO saleDTO = createSaleWithTwoSameItems();
        double expectedResult = 2 * 25 * 1.2;
        double actualResult = saleDTO.getItemTable().getRunningTotalIncludingVAT();
        assertEquals(expectedResult, actualResult, "Running total including VAT was incorrect");
    }

    //////////////////////////////////////////
    //          endRegistering()            //
    //////////////////////////////////////////
    @Test
    void endRegisteringTestTotalPriceOneItem() {
        SaleDTO saleDTO = createAndEndSaleWithOneItem();
        double expectedResult = 25 * 1.2;
        double actualResult = saleDTO.getPaymentInformation().getTotalPrice();
        assertEquals(expectedResult, actualResult, "Total Price was not calculated correctly");
    }

    @Test
    void endRegisteringTestTotalPriceTwoDifferentItems() {
        SaleDTO saleDTO = createAndEndSaleWithTwoDifferentItems();
        double expectedResult = 25 * 1.2 + 20 * 1.3;
        double actualResult = saleDTO.getPaymentInformation().getTotalPrice();
        assertEquals(expectedResult, actualResult, "Total Price was not calculated correctly");
    }

    @Test
    void endRegisteringTestTotalVATOneItem() {
        SaleDTO saleDTO = createAndEndSaleWithOneItem();
        double expectedResult = 25 * 0.2;
        double actualResult = saleDTO.getPaymentInformation().getTotalVAT();
        assertEquals(expectedResult, actualResult, "Total VAT was not calculated correctly");
    }

    @Test
    void endRegisteringTestTotalVATTwoDifferentItems() {
        SaleDTO saleDTO = createAndEndSaleWithTwoDifferentItems();
        double expectedResult = 25 * 0.2 + 20 * 0.3;
        double actualResult = saleDTO.getPaymentInformation().getTotalVAT();
        assertEquals(expectedResult, actualResult, "Total VAT was not calculated correctly");
    }

    ////////////////////////////////////////
    //          discountRequest()         //
    ////////////////////////////////////////
    @Test
    void discountRequestTestTotalPriceValid() {
        createAndEndSaleWithOneItem();
        int eligibleCustomer = 123;
        SaleDTO saleDTO = controller.requestDiscount(eligibleCustomer);
        double expectedResult = 25 * 1.2 / 2;
        double actualResult = saleDTO.getPaymentInformation().getTotalPrice();
        assertEquals(expectedResult, actualResult, "Total Price was not correctly modified by discount");
    }


    @Test
    void discountRequestTestTotalVATValid() {
        createAndEndSaleWithOneItem();
        int eligibleCustomer = 123;
        SaleDTO saleDTO = controller.requestDiscount(eligibleCustomer);
        double expectedResult = 25 * 0.2 / 2;
        double actualResult = saleDTO.getPaymentInformation().getTotalVAT();
        assertEquals(expectedResult, actualResult, "Total VAT was not correctly modified by discount");
    }

    @Test
    void discountRequestTestTotalPriceInvalid() {
        createAndEndSaleWithOneItem();
        int ineligibleCustomer = 1;
        SaleDTO saleDTO = controller.requestDiscount(ineligibleCustomer);
        double expectedResult = 25 * 1.2;
        double actualResult = saleDTO.getPaymentInformation().getTotalPrice();
        assertEquals(expectedResult, actualResult, "Total Price was not correctly modified by discount");
    }

    @Test
    void discountRequestTestTotalVATInvalid() {
        createAndEndSaleWithOneItem();
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
    void addPaymentTestAmountPaid() {
        createAndEndSaleWithOneItem();
        double amountPaid = 1000;
        SaleDTO saleDTO = controller.addPayment(amountPaid);
        double expected = 1000;
        double actual = saleDTO.getPaymentInformation().getAmountPaid();
        assertEquals(expected, actual, "AmountPaid was not added correctly");
    }

    @Test
    void addPaymentTestChange() {
        createAndEndSaleWithOneItem();
        double amountPaid = 1000;
        SaleDTO saleDTO = controller.addPayment(amountPaid);
        double expected = amountPaid - 25 * 1.2;
        double actual = saleDTO.getPaymentInformation().getChange();
        assertEquals(expected, actual, "Change was not calculated correctly");
    }

    //////////////////////////////////////
    //          Helper Methods          //
    //////////////////////////////////////
    private SaleDTO addItemToSale(int itemIdentifier, int quantity) { // Måste jag ha @Test här?
        try {
            return controller.addItemToSale(itemIdentifier, quantity);
        } catch (Exception e) {                // Vilken typ av exception ska jag kasta i testerna?
            fail("Exception was thrown from adding valid item");
        }
        return null;
    }

    private ItemDTO getItemInfoAt(int index) {                        // och denna?
        try {
            ItemDTO itemInfo = new Inventory().getItemInfo(index);
            return itemInfo;
        } catch (Exception e) {
            fail("Exception was thrown from adding valid item");
        }
        return null;
    }

    private SaleDTO createSaleWithTwoSameItems() {
        controller.startSale();
        return addItemToSale(1, 2);
    }

    private SaleDTO createAndEndSaleWithOneItem() {
        controller.startSale();
        addItemToSale(1, 1);
        return controller.endRegistering();
    }

    private SaleDTO createAndEndSaleWithTwoDifferentItems() {
        controller.startSale();
        addItemToSale(1, 1);
        addItemToSale(2, 1);
        return controller.endRegistering();
    }
}


