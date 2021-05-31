package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.DTO.ItemTableEntryDTO;
import se.kth.iv1350.pos.DTO.PaymentInformationDTO;
import se.kth.iv1350.pos.DTO.PriceDTO;

/**
 * Stores the payment related data for a sale and contains methods related to these.
 */
public class PaymentInformation {
    private PriceDTO totalPrice;
    private double amountPaid;
    private double change;
    private DiscountFactory discountFactory;

    /**
     * Constructs initial payment info
     */
    public PaymentInformation() {
        totalPrice = new PriceDTO();
        amountPaid = 0;
        change = 0;
        discountFactory = new DiscountFactory();
    }

    /**
     * Calculates the totalPrice and totalVAT attributes based on the itemTable.
     * @param itemTable contains the information that the calculations are based on.
     */
    void calculatePrice(ItemTable itemTable) {
        totalPrice = new PriceDTO(itemTable.getRunningTotalIncludingVAT(), totalPrice.getVAT());
        calculateVAT(itemTable);
    }

    /**
     * Alters total price if customer is eligible for discount
     * @param customerID ID of the customer.
     */
    void calculateDiscount(int customerID) {
        totalPrice = discountFactory.createDiscountStrategy(customerID).calculate(totalPrice);
    }

    /**
     * Registers amount paid and calculates discount.
     * @param amountPaid The amount paid by the customer
     */
    void calculatePayment(double amountPaid) {
        this.amountPaid = amountPaid;
        change = amountPaid - totalPrice.getPrice();
    }

    public PaymentInformationDTO getPaymentInformationDTO() {
        return new PaymentInformationDTO(this);
    }

    public PriceDTO getTotalPrice() {
        return totalPrice;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public double getChange() {
        return change;
    }

    private void calculateVAT(ItemTable itemTable) {
        for (ItemTableEntryDTO entry : itemTable.getTable())
            totalPrice = new PriceDTO(totalPrice.getPrice(), totalPrice.getVAT() +
                    entry.getItemDTO().getPrice() * entry.getQuantity() * entry.getItemDTO().getVATRate());
    }
}
