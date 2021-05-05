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
    Sale sale;
    ItemDTO testItem;
    ItemDTO otherTestItem;          // Ska attribut till tester vara private?
    ItemTableEntryDTO testEntry;
    ItemTableEntryDTO otherTestEntry;


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
    void addItem() {
        sale.addItem(testEntry);
        ItemDTO expectedResult = testItem;
        ItemDTO actualResult = sale.getItemTable().getLastItemInTable();
        assertEquals(expectedResult, actualResult, "Item was not added to the table of sale");
    }

    @Test
    void addSameItemMultipleTimes() {
        sale.addItem(testEntry);
        sale.addItem(testEntry);
        sale.addItem(testEntry);
        sale.addItem(testEntry);
        int expectedQuantity = 4;
        int expectedTableLength = 1;
        int actualQuantity = sale.getItemTable().getTable().get(0).getQuantity();
        int actualTableLength = sale.getItemTable().getTable().size(); // Är det helt fel att ha två assert? Borde jag bryta upp den?
        assertEquals(expectedQuantity, actualQuantity, "Quantity of the entry was not updated correctly");
        assertEquals(expectedTableLength, actualTableLength, "The table contains creates more entries when adding items " +
                "with same ID");
    }

    @Test
    void addItemCheckRunningTotal() {
        sale.addItem(testEntry);
        double expectedResult = 12;
        double actualResult = sale.getItemTable().getRunningTotalIncludingVAT();
        assertEquals(expectedResult, actualResult, "The running total including VAT was not calculated correctly");
    }

    @Test
    void addMultipleItemCheckRunningTotal() {
        sale.addItem(testEntry);
        sale.addItem(testEntry);
        double expectedResult = 24;
        double actualResult = sale.getItemTable().getRunningTotalIncludingVAT();
        assertEquals(expectedResult, actualResult, "The running total including VAT was not calculated correctly");
    }

    @Test
    void addMultipleDifferentItemCheckRunningTotal() {
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
    void endRegisteringTotalPrice() {
        sale.addItem(testEntry);
        sale.addItem(otherTestEntry);
        sale.endRegistering();
        double expectedResult = 12 + 30 * 2;
        double actualResult = sale.getPaymentInformation().getTotalPrice();
        assertEquals(expectedResult, actualResult, "Total price was not calculated correctly");
    }

    @Test
    void endRegisteringTotalVAT() {
        sale.addItem(testEntry);
        sale.addItem(otherTestEntry);
        sale.endRegistering();
        double expectedResult = 2 + 10 * 2;
        double actualResult = sale.getPaymentInformation().getTotalVAT();
        assertEquals(expectedResult, actualResult, "Total VAT was not calculated correctly");
    }

    //////////////////////////////////////
    //          addDiscount()           //
    //////////////////////////////////////
    @Test
    void addDiscountValid() {
        sale.addItem(testEntry);
        sale.endRegistering();
        int validID = 123;
        sale.addDiscount(validID);
        double expectedResult = 6;
        double actualResult = sale.getPaymentInformation().getTotalPrice();
        assertEquals(expectedResult, actualResult, "Discount was not applied properly");
    }

    @Test
    void addDiscountInvalid() {
        sale.addItem(testEntry);
        sale.endRegistering();
        int invalidID = 1;
        sale.addDiscount(invalidID);
        double expectedResult = 12;
        double actualResult = sale.getPaymentInformation().getTotalPrice();
        assertEquals(expectedResult, actualResult, "Discount was applied properly for invalid customerID");
    }

    //////////////////////////////////////
    //          addPayment()            //
    //////////////////////////////////////
    @Test
    void addPaymentCheckAmountPaid() {
        sale.addItem(testEntry);
        sale.endRegistering();
        sale.addPayment(100);
        double expectedResult = 100;
        double actualResult = sale.getPaymentInformation().getAmountPaid();
        assertEquals(expectedResult, actualResult, "Amount paid was not added to PaymentInformation correctly");
    }

    @Test
    void addPaymentCheckChange() {
        sale.addItem(testEntry);
        sale.endRegistering();
        sale.addPayment(100);
        double expectedResult = 88;
        double actualResult = sale.getPaymentInformation().getChange();
        assertEquals(expectedResult, actualResult, "Change was not calculated correctly");
    }
}