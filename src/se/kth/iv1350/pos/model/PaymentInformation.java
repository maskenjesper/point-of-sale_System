package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.DTO.ItemTableEntryDTO;

/**
 * Stores the payment related data for a sale and contains methods related to these.
 */
public class PaymentInformation {
    private Price totalPrice;
    private double amountPaid;
    private double change;
    private DiscountStrategy discountStrategy;

    /**
     * Constructs initial payment info
     */
    public PaymentInformation() {
        totalPrice = new Price();
        amountPaid = 0;
        change = 0;
    }

    /**
     * Calculates the totalPrice and totalVAT attributes based on the itemTable.
     * @param itemTable contains the information that the calculations are based on.
     */
    void calculatePrice(ItemTable itemTable) {
        totalPrice.setPrice(itemTable.getRunningTotalIncludingVAT());
        calculateVAT(itemTable);
    }

    /**
     * Alters total price if customer is eligible for discount
     * @param customerID ID of the customer.
     */
    void calculateDiscount(int customerID) {
        int memberID = 123;
        if (customerID == memberID)
            discountStrategy = new MemberDiscountStrategy(this); // Choose MemberDiscountStrategy
        else
            discountStrategy = new RegularDiscountStrategy(this); // Choose RegularDiscountStrategy
        totalPrice = discountStrategy.calculate();
    }
    // Det känns som att denna implementation av strategy blir ganska dålig. Vilken algoritm som ska appliceras ska bero
    // på customerID men då måste jag ha en if-sats som väljer algoritmen (eller stratergin snarare) som ska användas.
    // Är inte själva meningen med strategy att eliminera if-satser dock?

    /**
     * Registers amount paid and calculates discount.
     * @param amountPaid The amount paid by the customer
     */
    void calculatePayment(double amountPaid) {
        this.amountPaid = amountPaid;
        change = amountPaid - totalPrice.getPrice();
    }

    public Price getTotalPrice() {
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
            totalPrice.setVAT(totalPrice.getVAT() + entry.getItemDTO().getPrice() * entry.getQuantity() *
                    entry.getItemDTO().getVATRate());
    }
}
