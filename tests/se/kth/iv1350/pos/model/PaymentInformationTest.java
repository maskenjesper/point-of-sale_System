package se.kth.iv1350.pos.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.DTO.ItemDTO;
import se.kth.iv1350.pos.DTO.ItemTableEntryDTO;

import static org.junit.jupiter.api.Assertions.*;

class PaymentInformationTest {
    PaymentInformation paymentInformation;
    ItemTable itemTableWith1TestItemDTO;
    ItemDTO testItemDTO;

    @BeforeEach
    void setUp() {
        paymentInformation = new PaymentInformation();
        itemTableWith1TestItemDTO = new ItemTable();
        createTestItemDTO();
        ItemTableEntryDTO entryWithTestItemDTOAndQuantity1 = new ItemTableEntryDTO(testItemDTO, 1);
        itemTableWith1TestItemDTO.add(entryWithTestItemDTOAndQuantity1);
    }

    @AfterEach
    void tearDown() {
        paymentInformation = null;
        itemTableWith1TestItemDTO = null;
        testItemDTO = null;
    }

    @Test
    void calculatePriceTotalPriceWithItemTableWith1TestItemDTO() {
        paymentInformation.calculatePrice(itemTableWith1TestItemDTO);
        double expectedTotalPrice = 120;
        double actualTotalPrice = paymentInformation.getTotalPrice();
        assertEquals(expectedTotalPrice, actualTotalPrice, "Total price was not calculated correctly");
    }

    @Test
    void calculatePriceTotalVATWithItemTableWith1TestItemDTO() {
        paymentInformation.calculatePrice(itemTableWith1TestItemDTO);
        double expectedTotalVAT = 20;
        double actualTotalVAT = paymentInformation.getTotalVAT();
        assertEquals(expectedTotalVAT, actualTotalVAT, "Total price was not calculated correctly");
    }

    @Test
    void calculateDiscount() {
    }

    @Test
    void calculatePayment() {
    }

    @Test
    void getTotalPrice() {
    }

    @Test
    void getTotalVAT() {
    }

    @Test
    void getAmountPaid() {
    }

    @Test
    void getChange() {
    }

    private void createTestItemDTO() {
        int itemIdentifier = 1;
        int price = 100;
        String description = "description";
        String name = "name";
        String currency = "SEK";
        double vatRate = 0.2;
        testItemDTO = new ItemDTO(itemIdentifier, price, description, name, currency, vatRate);
    }
}