package se.kth.iv1350.pos.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.DTO.ItemDTO;
import se.kth.iv1350.pos.DTO.SaleDTO;
import se.kth.iv1350.pos.integration.InvalidItemIdentifierException;
import se.kth.iv1350.pos.integration.Inventory;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
    void addItemToSaleTestItemDTO() throws Exception {
        SaleDTO saleDTO = createAndEndSaleWithTwoDifferentItems();
        ItemDTO expectedResult = new Inventory().getItemInfo(2);
        ItemDTO actualResult = saleDTO.getItemTableDTO().getLastItemInTable();
        assertEquals(expectedResult, actualResult, "Item wasn't added correctly");
    }

    @Test
    void addItemToSaleTestQuantity() {
        SaleDTO saleDTO = createSaleWithTwoSameItems();
        int expectedResult = 2;
        int actualResult = saleDTO.getItemTableDTO().getTable().get(0).getQuantity();
        assertEquals(expectedResult, actualResult, "Quantity was incorrect");
    }

    @Test
    void addItemToSaleTestRunningTotalIncludingVAT() {
        SaleDTO saleDTO = createSaleWithTwoSameItems();
        double expectedResult = 2 * 25 * 1.2;
        double actualResult = saleDTO.getItemTableDTO().getRunningTotalIncludingVAT();
        assertEquals(expectedResult, actualResult, "Running total including VAT was incorrect");
    }

    @Test
    void addItemToSaleTestInvalidIdentifier() {
        try {
            controller.addItemToSale(3, 1);
            fail("Exception was not thrown when invalid id was sent");
        } catch (Exception e) {
            assertTrue(e instanceof InvalidItemIdentifierException, "Wrong Exception type");
        }
    }

    @Test
    void addItemToSaleTestDatabaseServerNotRunning() {
        try {
            controller.addItemToSale(500, 1);
            fail("Exception was not thrown when database server wasn't running");
        } catch (Exception e) {
            assertTrue(e instanceof InventoryException, "Wrong Exception type");
        }
    }

    @Test
    void addItemToSaleTestDatabaseServerNotRunningErrorMessage() {
        ByteArrayOutputStream printoutBuffer;
        PrintStream originalSysOut;
        printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
        try {
            controller.addItemToSale(500, 1);
            System.setOut(originalSysOut);
            fail("Exception was not thrown when database server wasn't running");
        } catch (Exception e) {
            String printout = printoutBuffer.toString();
            String expectedOutput = "DEVELOPER LOG: The database server is not running";
            assertTrue(printout.contains(expectedOutput));
            assertTrue(e instanceof InventoryException, "Wrong Exception type");
        }
    }

    //////////////////////////////////////////
    //          endRegistering()            //
    //////////////////////////////////////////
    @Test
    void endRegisteringTestTotalPriceOneItem() {
        SaleDTO saleDTO = createAndEndSaleWithOneItem();
        double expectedResult = 25 * 1.2;
        double actualResult = saleDTO.getPaymentInformationDTO().getTotalPrice().getPrice();
        assertEquals(expectedResult, actualResult, "Total Price was not calculated correctly");
    }

    @Test
    void endRegisteringTestTotalPriceTwoDifferentItems() {
        SaleDTO saleDTO = createAndEndSaleWithTwoDifferentItems();
        double expectedResult = 25 * 1.2 + 20 * 1.3;
        double actualResult = saleDTO.getPaymentInformationDTO().getTotalPrice().getPrice();
        assertEquals(expectedResult, actualResult, "Total Price was not calculated correctly");
    }

    @Test
    void endRegisteringTestTotalVATOneItem() {
        SaleDTO saleDTO = createAndEndSaleWithOneItem();
        double expectedResult = 25 * 0.2;
        double actualResult = saleDTO.getPaymentInformationDTO().getTotalPrice().getVAT();
        assertEquals(expectedResult, actualResult, "Total VAT was not calculated correctly");
    }

    @Test
    void endRegisteringTestTotalVATTwoDifferentItems() {
        SaleDTO saleDTO = createAndEndSaleWithTwoDifferentItems();
        double expectedResult = 25 * 0.2 + 20 * 0.3;
        double actualResult = saleDTO.getPaymentInformationDTO().getTotalPrice().getVAT();
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
        double actualResult = saleDTO.getPaymentInformationDTO().getTotalPrice().getPrice();
        assertEquals(expectedResult, actualResult, "Total Price was not correctly modified by discount");
    }


    @Test
    void discountRequestTestTotalVATValid() {
        createAndEndSaleWithOneItem();
        int eligibleCustomer = 123;
        SaleDTO saleDTO = controller.requestDiscount(eligibleCustomer);
        double expectedResult = 25 * 0.2 / 2;
        double actualResult = saleDTO.getPaymentInformationDTO().getTotalPrice().getVAT();
        assertEquals(expectedResult, actualResult, "Total VAT was not correctly modified by discount");
    }

    @Test
    void discountRequestTestTotalPriceInvalid() {
        createAndEndSaleWithOneItem();
        int ineligibleCustomer = 1;
        SaleDTO saleDTO = controller.requestDiscount(ineligibleCustomer);
        double expectedResult = 25 * 1.2;
        double actualResult = saleDTO.getPaymentInformationDTO().getTotalPrice().getPrice();
        assertEquals(expectedResult, actualResult, "Total Price was not correctly modified by discount");
    }

    @Test
    void discountRequestTestTotalVATInvalid() {
        createAndEndSaleWithOneItem();
        int ineligibleCustomer = 1;
        SaleDTO saleDTO = controller.requestDiscount(ineligibleCustomer);
        double expectedResult = 25 * 0.2;
        double actualResult = saleDTO.getPaymentInformationDTO().getTotalPrice().getVAT();
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
        double actual = saleDTO.getPaymentInformationDTO().getAmountPaid();
        assertEquals(expected, actual, "AmountPaid was not added correctly");
    }

    @Test
    void addPaymentTestChange() {
        createAndEndSaleWithOneItem();
        double amountPaid = 1000;
        SaleDTO saleDTO = controller.addPayment(amountPaid);
        double expected = amountPaid - 25 * 1.2;
        double actual = saleDTO.getPaymentInformationDTO().getChange();
        assertEquals(expected, actual, "Change was not calculated correctly");
    }

    //////////////////////////////////////
    //          Helper Methods          //
    //////////////////////////////////////
    private SaleDTO createSaleWithTwoSameItems() {
        try {
            controller.startSale();
            return controller.addItemToSale(1, 2);
        } catch (Exception e) {
            fail("Exception was thrown when constructing valid test sale");
        }
        return null;
    }

    private SaleDTO createAndEndSaleWithOneItem() {
        try {
            controller.startSale();
            controller.addItemToSale(1, 1);
            return controller.endRegistering();
        } catch (Exception e) {
            fail("Exception was thrown when constructing valid test sale");
        }
        return null;
    }

    private SaleDTO createAndEndSaleWithTwoDifferentItems() {
        try {
            controller.startSale();
            controller.addItemToSale(1, 1);
            controller.addItemToSale(2, 1);
            return controller.endRegistering();
        } catch (Exception e) {
            fail("Exception was thrown when constructing valid test sale");
        }
        return null;
    }
}


