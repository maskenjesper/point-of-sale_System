package se.kth.iv1350.pos.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.DTO.ItemDTO;
import se.kth.iv1350.pos.DTO.ItemTableEntryDTO;

import static org.junit.jupiter.api.Assertions.*;

class PaymentInformationTest { // Måste man skriva test för getters?

    //////////////////////////////
    //          SETUP           //
    //////////////////////////////
    PaymentInformation paymentInformation;
    ItemTable itemTableWith1TestItemDTO1;
    ItemTable itemTableWith2TestItemDTO1;
    ItemTable itemTableWith1TestItemDTO2;
    ItemTable itemTableWith2TestItemDTO2;
    ItemTable itemTableWith1TestItemDTO1And1TestItemDTO2;
    ItemDTO testItemDTO1;
    ItemDTO testItemDTO2;

    @BeforeEach
    void setUp() {
        paymentInformation = new PaymentInformation();
        createTestItemDTO1();
        createTestItemDTO2();

        createItemTableWith1TestItemDTO1();
        createItemTableWith2TestItemDTO1();
        createItemTableWith1TestItemDTO2();
        createItemTableWith2TestItemDTO2();
        createItemTableWith1TestItemDTO1And1TestItemDTO2();
    }

    @AfterEach
    void tearDown() {
        paymentInformation = null;
        itemTableWith1TestItemDTO1 = null;
        itemTableWith2TestItemDTO1 = null;
        itemTableWith1TestItemDTO2 = null;
        itemTableWith2TestItemDTO2 = null;
        itemTableWith1TestItemDTO1And1TestItemDTO2 = null;
        testItemDTO1 = null;
        testItemDTO2 = null;
    }

    //////////////////////////////////////////
    //          calculatePrice()            //
    //////////////////////////////////////////
    @Test
    void calculatePriceTotalPriceWithItemTableWith1TestItemDTO1() {
        paymentInformation.calculatePrice(itemTableWith1TestItemDTO1);
        double expectedTotalPrice = 120;
        double actualTotalPrice = paymentInformation.getTotalPrice();
        assertEquals(expectedTotalPrice, actualTotalPrice, "Total price was not calculated correctly");
    }

    @Test
    void calculatePriceTotalPriceWithItemTableWith2TestItemDTO1() {
        paymentInformation.calculatePrice(itemTableWith2TestItemDTO1);
        double expectedTotalPrice = 240;
        double actualTotalPrice = paymentInformation.getTotalPrice();
        assertEquals(expectedTotalPrice, actualTotalPrice, "Total price was not calculated correctly");
    }

    @Test
    void calculatePriceTotalPriceWithItemTableWith1TestItemDTO2() {
        paymentInformation.calculatePrice(itemTableWith1TestItemDTO2);
        double expectedTotalPrice = 195;
        double actualTotalPrice = paymentInformation.getTotalPrice();
        assertEquals(expectedTotalPrice, actualTotalPrice, "Total price was not calculated correctly");
    }

    @Test
    void calculatePriceTotalPriceWithItemTableWith2TestItemDTO2() {
        paymentInformation.calculatePrice(itemTableWith2TestItemDTO2);
        double expectedTotalPrice = 390;
        double actualTotalPrice = paymentInformation.getTotalPrice();
        assertEquals(expectedTotalPrice, actualTotalPrice, "Total price was not calculated correctly");
    }

    @Test
    void calculatePriceTotalPriceWithItemTableWith1TestItemDTO1And1TestItemDTO2() {
        paymentInformation.calculatePrice(itemTableWith1TestItemDTO1And1TestItemDTO2);
        double expectedTotalPrice = 315;
        double actualTotalPrice = paymentInformation.getTotalPrice();
        assertEquals(expectedTotalPrice, actualTotalPrice, "Total price was not calculated correctly");
    }

    @Test
    void calculatePriceTotalVATWithItemTableWith1TestItemDTO1() {
        paymentInformation.calculatePrice(itemTableWith1TestItemDTO1);
        double expectedTotalVAT = 20;
        double actualTotalVAT = paymentInformation.getTotalVAT();
        assertEquals(expectedTotalVAT, actualTotalVAT, "Total VAT was not calculated correctly");
    }

    @Test
    void calculatePriceTotalVATWithItemTableWith2TestItemDTO1() {
        paymentInformation.calculatePrice(itemTableWith2TestItemDTO1);
        double expectedTotalVAT = 40;
        double actualTotalVAT = paymentInformation.getTotalVAT();
        assertEquals(expectedTotalVAT, actualTotalVAT, "Total VAT was not calculated correctly");
    }

    @Test
    void calculatePriceTotalVATWithItemTableWith1TestItemDTO2() {
        paymentInformation.calculatePrice(itemTableWith1TestItemDTO2);
        double expectedTotalVAT = 45;
        double actualTotalVAT = paymentInformation.getTotalVAT();
        assertEquals(expectedTotalVAT, actualTotalVAT, "Total VAT was not calculated correctly");
    }

    @Test
    void calculatePriceTotalVATWithItemTableWith2TestItemDTO2() {
        paymentInformation.calculatePrice(itemTableWith2TestItemDTO2);
        double expectedTotalVAT = 90;
        double actualTotalVAT = paymentInformation.getTotalVAT();
        assertEquals(expectedTotalVAT, actualTotalVAT, "Total VAT was not calculated correctly");
    }

    @Test
    void calculatePriceTotalVATWithItemTableWith1TestItemDTO1And1TestItemDTO2() {
        paymentInformation.calculatePrice(itemTableWith1TestItemDTO1And1TestItemDTO2);
        double expectedTotalVAT = 65;
        double actualTotalVAT = paymentInformation.getTotalVAT();
        assertEquals(expectedTotalVAT, actualTotalVAT, "Total VAT was not calculated correctly");
    }

    //////////////////////////////////////////
    //          calculateDiscount()         //
    //////////////////////////////////////////
    @Test
    void calculateDiscountDeniedTotalPrice() {
        paymentInformation.calculatePrice(itemTableWith1TestItemDTO1);
        int deniedCustomerID = 1;
        paymentInformation.calculateDiscount(deniedCustomerID);
        double expectedTotalPrice = 120;
        double actualTotalPrice = paymentInformation.getTotalPrice();
        assertEquals(expectedTotalPrice, actualTotalPrice, "Total price was changed by denied discount request");
    }

    @Test
    void calculateDiscountDeniedTotalVAT() {
        paymentInformation.calculatePrice(itemTableWith1TestItemDTO1);
        int deniedCustomerID = 1;
        paymentInformation.calculateDiscount(deniedCustomerID);
        double expectedTotalVAT = 20;
        double actualTotalVAT = paymentInformation.getTotalVAT();
        assertEquals(expectedTotalVAT, actualTotalVAT, "Total VAT was changed by denied discount request");
    }

    @Test
    void calculateDiscountApprovedTotalPrice() {
        paymentInformation.calculatePrice(itemTableWith1TestItemDTO1);
        int approvedCustomerID = 123;
        paymentInformation.calculateDiscount(approvedCustomerID);
        double expectedTotalPrice = 60;
        double actualTotalPrice = paymentInformation.getTotalPrice();
        assertEquals(expectedTotalPrice, actualTotalPrice, "Total price was changed by denied discount request");
    }

    @Test
    void calculateDiscountApprovedTotalVAT() {
        paymentInformation.calculatePrice(itemTableWith1TestItemDTO1);
        int approvedCustomerID = 123;
        paymentInformation.calculateDiscount(approvedCustomerID);
        double expectedTotalVAT = 10;
        double actualTotalVAT = paymentInformation.getTotalVAT();
        assertEquals(expectedTotalVAT, actualTotalVAT, "Total VAT was changed by denied discount request");
    }

    //////////////////////////////////////////
    //          calculatePayment()          //
    //////////////////////////////////////////
    @Test
    void calculatePaymentAmountPaid() {
        paymentInformation.calculatePrice(itemTableWith1TestItemDTO1);
        double amountPaid = 1000;
        paymentInformation.calculatePayment(amountPaid);
        double expectedAmountPaid = 1000;
        double actualAmountPaid = paymentInformation.getAmountPaid();
        assertEquals(expectedAmountPaid, actualAmountPaid, "Amount paid was not updated correctly");
    }

    @Test
    void calculatePaymentChange() {
        paymentInformation.calculatePrice(itemTableWith1TestItemDTO1);
        double amountPaid = 1000;
        paymentInformation.calculatePayment(amountPaid);
        double expectedChange = 880;
        double actualChange = paymentInformation.getChange();
        assertEquals(expectedChange, actualChange, "Amount paid was not updated correctly");
    }

    //////////////////////////////////////
    //          Helper methods          //
    //////////////////////////////////////
    private void createItemTableWith1TestItemDTO1() {
        itemTableWith1TestItemDTO1 = new ItemTable();
        itemTableWith1TestItemDTO1.add(new ItemTableEntryDTO(testItemDTO1, 1));
    }

    private void createItemTableWith2TestItemDTO1() {
        itemTableWith2TestItemDTO1 = new ItemTable();
        itemTableWith2TestItemDTO1.add(new ItemTableEntryDTO(testItemDTO1, 2));
    }

    private void createItemTableWith1TestItemDTO2() {
        itemTableWith1TestItemDTO2 = new ItemTable();
        itemTableWith1TestItemDTO2.add(new ItemTableEntryDTO(testItemDTO2, 1));
    }

    private void createItemTableWith2TestItemDTO2() {
        itemTableWith2TestItemDTO2 = new ItemTable();
        itemTableWith2TestItemDTO2.add(new ItemTableEntryDTO(testItemDTO2, 2));
    }

    private void createItemTableWith1TestItemDTO1And1TestItemDTO2() {
        itemTableWith1TestItemDTO1And1TestItemDTO2 = new ItemTable();
        itemTableWith1TestItemDTO1And1TestItemDTO2.add(new ItemTableEntryDTO(testItemDTO1, 1));
        itemTableWith1TestItemDTO1And1TestItemDTO2.add(new ItemTableEntryDTO(testItemDTO2, 1));
    }

    private void createTestItemDTO1() {
        int itemIdentifier = 1;
        int price = 100;
        String description = "description";
        String name = "name";
        String currency = "SEK";
        double vatRate = 0.2;
        testItemDTO1 = new ItemDTO(itemIdentifier, price, description, name, currency, vatRate);
    }

    private void createTestItemDTO2() {
        int itemIdentifier = 2;
        int price = 150;
        String description = "description";
        String name = "name";
        String currency = "SEK";
        double vatRate = 0.3;
        testItemDTO2 = new ItemDTO(itemIdentifier, price, description, name, currency, vatRate);
    }
}