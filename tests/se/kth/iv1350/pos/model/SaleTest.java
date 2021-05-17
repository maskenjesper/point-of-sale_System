package se.kth.iv1350.pos.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.DTO.ItemDTO;
import se.kth.iv1350.pos.DTO.ItemTableEntryDTO;

import static org.junit.jupiter.api.Assertions.*;

class SaleTest {

    //////////////////////////////
    //          SETUP           //
    //////////////////////////////
    private Sale sale;
    private ItemDTO testItem;
    private ItemDTO otherTestItem;
    private ItemTableEntryDTO testEntry;
    private ItemTableEntryDTO otherTestEntry;


    @BeforeEach
    void setUp() {
        sale = new Sale();
        testItem = new ItemDTO(1, 10, "", "", "", 0.2);
        testEntry = new ItemTableEntryDTO(testItem, 1);
        otherTestItem = new ItemDTO(2, 20, "", "", "", 0.5);
        otherTestEntry = new ItemTableEntryDTO(otherTestItem, 2);
    }

    @AfterEach
    void tearDown() {
        sale = null;
        testItem = null;
        testEntry = null;
        otherTestItem = null;
        otherTestEntry = null;
    }

    //////////////////////////////////
    //          addItem()           //
    //////////////////////////////////
    @Test
    void addItemTestTableOneItem() {
        sale.addItem(testEntry);
        ItemDTO expectedResult = testItem;
        ItemDTO actualResult = sale.getItemTable().getLastItemInTable();
        assertEquals(expectedResult, actualResult, "Item was not added to the table of sale");
    }

    @Test
    void addItemTestTableMultipleSameItems() {
        sale.addItem(testEntry);
        sale.addItem(testEntry);
        sale.addItem(testEntry);
        sale.addItem(testEntry);
        int expectedQuantity = 4;
        int expectedTableLength = 1;
        int actualQuantity = sale.getItemTable().getTable().get(0).getQuantity();
        int actualTableLength = sale.getItemTable().getTable().size();
        assertEquals(expectedQuantity, actualQuantity, "Quantity of the entry was not updated correctly");
        assertEquals(expectedTableLength, actualTableLength, "The table contains creates more entries when adding items " +
                "with same ID");
    }

    @Test
    void addItemTestRunningTotalOneItem() {
        sale.addItem(testEntry);
        double expectedResult = 12;
        double actualResult = sale.getItemTable().getRunningTotalIncludingVAT();
        assertEquals(expectedResult, actualResult, "The running total including VAT was not calculated correctly");
    }

    @Test
    void addItemTestRunningTotalMultipleSameItems() {
        sale.addItem(testEntry);
        sale.addItem(testEntry);
        double expectedResult = 24;
        double actualResult = sale.getItemTable().getRunningTotalIncludingVAT();
        assertEquals(expectedResult, actualResult, "The running total including VAT was not calculated correctly");
    }

    @Test
    void addItemTestRunningTotalMultipleDifferentItems() {
        sale.addItem(testEntry);
        sale.addItem(otherTestEntry);
        double expectedResult = 72;
        double actualResult = sale.getItemTable().getRunningTotalIncludingVAT();
        assertEquals(expectedResult, actualResult, "The running total including VAT was not calculated correctly");
    }

    //////////////////////////////////////////
    //          endRegistering()            //
    //////////////////////////////////////////
    @Test
    void endRegisteringTestTotalPrice() {
        sale.addItem(testEntry);
        sale.addItem(otherTestEntry);
        sale.endRegistering();
        double expectedResult = 12 + 30 * 2;
        double actualResult = sale.getPaymentInformation().getTotalPrice().getPrice();
        assertEquals(expectedResult, actualResult, "Total price was not calculated correctly");
    }

    @Test
    void endRegisteringTestTotalVAT() {
        sale.addItem(testEntry);
        sale.addItem(otherTestEntry);
        sale.endRegistering();
        double expectedResult = 2 + 10 * 2;
        double actualResult = sale.getPaymentInformation().getTotalPrice().getVAT();
        assertEquals(expectedResult, actualResult, "Total VAT was not calculated correctly");
    }

    //////////////////////////////////////
    //          addDiscount()           //
    //////////////////////////////////////
    @Test
    void addDiscountTestValid() {
        sale.addItem(testEntry);
        sale.endRegistering();
        int validID = 123;
        sale.addDiscount(validID);
        double expectedResult = 6;
        double actualResult = sale.getPaymentInformation().getTotalPrice().getPrice();
        assertEquals(expectedResult, actualResult, "Discount was not applied properly");
    }

    @Test
    void addDiscountTestInvalid() {
        sale.addItem(testEntry);
        sale.endRegistering();
        int invalidID = 1;
        sale.addDiscount(invalidID);
        double expectedResult = 12;
        double actualResult = sale.getPaymentInformation().getTotalPrice().getPrice();
        assertEquals(expectedResult, actualResult, "Discount was applied properly for invalid customerID");
    }

    //////////////////////////////////////
    //          addPayment()            //
    //////////////////////////////////////
    @Test
    void addPaymentTestAmountPaid() {
        sale.addItem(testEntry);
        sale.endRegistering();
        sale.addPayment(100);
        double expectedResult = 100;
        double actualResult = sale.getPaymentInformation().getAmountPaid();
        assertEquals(expectedResult, actualResult, "Amount paid was not added to PaymentInformation correctly");
    }

    @Test
    void addPaymentTestChange() {
        sale.addItem(testEntry);
        sale.endRegistering();
        sale.addPayment(100);
        double expectedResult = 88;
        double actualResult = sale.getPaymentInformation().getChange();
        assertEquals(expectedResult, actualResult, "Change was not calculated correctly");
    }
}