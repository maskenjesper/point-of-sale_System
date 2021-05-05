package se.kth.iv1350.pos.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.DTO.ItemDTO;
import se.kth.iv1350.pos.DTO.ItemTableEntryDTO;

import static org.junit.jupiter.api.Assertions.*;

class PaymentInformationTest {

    //////////////////////////////
    //          SETUP           //
    //////////////////////////////
    private PaymentInformation paymentInformation;
    private ItemTable itemTableWithOneitem1;
    private ItemTable itemTableWithTwoitem1;
    private ItemTable itemTableWithOneitem2;
    private ItemTable itemTableWithTwoitem2;
    private ItemTable itemTableWithOneitem1AndOneitem2;
    private ItemDTO item1;
    private ItemDTO item2;

    @BeforeEach
    void setUp() {
        paymentInformation = new PaymentInformation();
        createitem1();
        createitem2();

        createItemTableWithOneitem1();
        createItemTableWithTwoitem1();
        createItemTableWithOneitem2();
        createItemTableWithTwoitem2();
        createItemTableWithOneitem1AndOneitem2();
    }

    @AfterEach
    void tearDown() {
        paymentInformation = null;
        itemTableWithOneitem1 = null;
        itemTableWithTwoitem1 = null;
        itemTableWithOneitem2 = null;
        itemTableWithTwoitem2 = null;
        itemTableWithOneitem1AndOneitem2 = null;
        item1 = null;
        item2 = null;
    }

    //////////////////////////////////////////
    //          calculatePrice()            //
    //////////////////////////////////////////
    @Test
    void calculatePriceTestTotalPriceWithItemTableWithOneitem1() {
        paymentInformation.calculatePrice(itemTableWithOneitem1);
        double expectedTotalPrice = 120;
        double actualTotalPrice = paymentInformation.getTotalPrice();
        assertEquals(expectedTotalPrice, actualTotalPrice, "Total price was not calculated correctly");
    }

    @Test
    void calculatePriceTestTotalPriceWithItemTableWithTwoitem1() {
        paymentInformation.calculatePrice(itemTableWithTwoitem1);
        double expectedTotalPrice = 240;
        double actualTotalPrice = paymentInformation.getTotalPrice();
        assertEquals(expectedTotalPrice, actualTotalPrice, "Total price was not calculated correctly");
    }

    @Test
    void calculatePriceTestTotalPriceWithItemTableWithOneitem2() {
        paymentInformation.calculatePrice(itemTableWithOneitem2);
        double expectedTotalPrice = 195;
        double actualTotalPrice = paymentInformation.getTotalPrice();
        assertEquals(expectedTotalPrice, actualTotalPrice, "Total price was not calculated correctly");
    }

    @Test
    void calculatePriceTestTotalPriceWithItemTableWithTwoitem2() {
        paymentInformation.calculatePrice(itemTableWithTwoitem2);
        double expectedTotalPrice = 390;
        double actualTotalPrice = paymentInformation.getTotalPrice();
        assertEquals(expectedTotalPrice, actualTotalPrice, "Total price was not calculated correctly");
    }

    @Test
    void calculatePriceTestTotalPriceWithItemTableWithOneitem1AndOneitem2() {
        paymentInformation.calculatePrice(itemTableWithOneitem1AndOneitem2);
        double expectedTotalPrice = 315;
        double actualTotalPrice = paymentInformation.getTotalPrice();
        assertEquals(expectedTotalPrice, actualTotalPrice, "Total price was not calculated correctly");
    }

    @Test
    void calculatePriceTestTotalVATWithItemTableWithOneitem1() {
        paymentInformation.calculatePrice(itemTableWithOneitem1);
        double expectedTotalVAT = 20;
        double actualTotalVAT = paymentInformation.getTotalVAT();
        assertEquals(expectedTotalVAT, actualTotalVAT, "Total VAT was not calculated correctly");
    }

    @Test
    void calculatePriceTestTotalVATWithItemTableWithTwoitem1() {
        paymentInformation.calculatePrice(itemTableWithTwoitem1);
        double expectedTotalVAT = 40;
        double actualTotalVAT = paymentInformation.getTotalVAT();
        assertEquals(expectedTotalVAT, actualTotalVAT, "Total VAT was not calculated correctly");
    }

    @Test
    void calculatePriceTestTotalVATWithItemTableWithOneitem2() {
        paymentInformation.calculatePrice(itemTableWithOneitem2);
        double expectedTotalVAT = 45;
        double actualTotalVAT = paymentInformation.getTotalVAT();
        assertEquals(expectedTotalVAT, actualTotalVAT, "Total VAT was not calculated correctly");
    }

    @Test
    void calculatePriceTestTotalVATWithItemTableWithTwoitem2() {
        paymentInformation.calculatePrice(itemTableWithTwoitem2);
        double expectedTotalVAT = 90;
        double actualTotalVAT = paymentInformation.getTotalVAT();
        assertEquals(expectedTotalVAT, actualTotalVAT, "Total VAT was not calculated correctly");
    }

    @Test
    void calculatePriceTestTotalVATWithItemTableWithOneitem1AndOneitem2() {
        paymentInformation.calculatePrice(itemTableWithOneitem1AndOneitem2);
        double expectedTotalVAT = 65;
        double actualTotalVAT = paymentInformation.getTotalVAT();
        assertEquals(expectedTotalVAT, actualTotalVAT, "Total VAT was not calculated correctly");
    }

    //////////////////////////////////////////
    //          calculateDiscount()         //
    //////////////////////////////////////////
    @Test
    void calculateDiscountTestTotalPriceDenied() {
        paymentInformation.calculatePrice(itemTableWithOneitem1);
        int deniedCustomerID = 1;
        paymentInformation.calculateDiscount(deniedCustomerID);
        double expectedTotalPrice = 120;
        double actualTotalPrice = paymentInformation.getTotalPrice();
        assertEquals(expectedTotalPrice, actualTotalPrice, "Total price was changed by denied discount request");
    }

    @Test
    void calculateDiscountTestTotalVATDenied() {
        paymentInformation.calculatePrice(itemTableWithOneitem1);
        int deniedCustomerID = 1;
        paymentInformation.calculateDiscount(deniedCustomerID);
        double expectedTotalVAT = 20;
        double actualTotalVAT = paymentInformation.getTotalVAT();
        assertEquals(expectedTotalVAT, actualTotalVAT, "Total VAT was changed by denied discount request");
    }

    @Test
    void calculateDiscountTestTotalPriceApproved() {
        paymentInformation.calculatePrice(itemTableWithOneitem1);
        int approvedCustomerID = 123;
        paymentInformation.calculateDiscount(approvedCustomerID);
        double expectedTotalPrice = 60;
        double actualTotalPrice = paymentInformation.getTotalPrice();
        assertEquals(expectedTotalPrice, actualTotalPrice, "Total price was changed by denied discount request");
    }

    @Test
    void calculateDiscountTestTotalVATApproved() {
        paymentInformation.calculatePrice(itemTableWithOneitem1);
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
    void calculatePaymentTestAmountPaid() {
        paymentInformation.calculatePrice(itemTableWithOneitem1);
        double amountPaid = 1000;
        paymentInformation.calculatePayment(amountPaid);
        double expectedAmountPaid = 1000;
        double actualAmountPaid = paymentInformation.getAmountPaid();
        assertEquals(expectedAmountPaid, actualAmountPaid, "Amount paid was not updated correctly");
    }

    @Test
    void calculatePaymentTestChange() {
        paymentInformation.calculatePrice(itemTableWithOneitem1);
        double amountPaid = 1000;
        paymentInformation.calculatePayment(amountPaid);
        double expectedChange = 880;
        double actualChange = paymentInformation.getChange();
        assertEquals(expectedChange, actualChange, "Amount paid was not updated correctly");
    }

    //////////////////////////////////////
    //          Helper methods          //
    //////////////////////////////////////
    private void createItemTableWithOneitem1() {
        itemTableWithOneitem1 = new ItemTable();
        itemTableWithOneitem1.add(new ItemTableEntryDTO(item1, 1));
    }

    private void createItemTableWithTwoitem1() {
        itemTableWithTwoitem1 = new ItemTable();
        itemTableWithTwoitem1.add(new ItemTableEntryDTO(item1, 2));
    }

    private void createItemTableWithOneitem2() {
        itemTableWithOneitem2 = new ItemTable();
        itemTableWithOneitem2.add(new ItemTableEntryDTO(item2, 1));
    }

    private void createItemTableWithTwoitem2() {
        itemTableWithTwoitem2 = new ItemTable();
        itemTableWithTwoitem2.add(new ItemTableEntryDTO(item2, 2));
    }

    private void createItemTableWithOneitem1AndOneitem2() {
        itemTableWithOneitem1AndOneitem2 = new ItemTable();
        itemTableWithOneitem1AndOneitem2.add(new ItemTableEntryDTO(item1, 1));
        itemTableWithOneitem1AndOneitem2.add(new ItemTableEntryDTO(item2, 1));
    }

    private void createitem1() {
        int itemIdentifier = 1;
        int price = 100;
        String description = "description";
        String name = "name";
        String currency = "SEK";
        double vatRate = 0.2;
        item1 = new ItemDTO(itemIdentifier, price, description, name, currency, vatRate);
    }

    private void createitem2() {
        int itemIdentifier = 2;
        int price = 150;
        String description = "description";
        String name = "name";
        String currency = "SEK";
        double vatRate = 0.3;
        item2 = new ItemDTO(itemIdentifier, price, description, name, currency, vatRate);
    }
}