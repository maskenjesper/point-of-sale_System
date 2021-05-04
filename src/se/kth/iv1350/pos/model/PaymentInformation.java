package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.DTO.ItemTableEntryDTO;

/**
 * Stores the payment related data for a sale and contains methods related to these.
 */
public class PaymentInformation {
    private double totalPrice;
    private double totalVAT;
    private double amountPaid;
    private double change;

    /**
     * Constructs initial payment info
     */
    public PaymentInformation() {
        totalPrice = 0;
        totalVAT = 0;
        amountPaid = 0;
        change = 0;
    }

    /**
     * Calculates the totalPrice and totalVAT attributes based on the itemTable.
     * @param itemTable contains the information that the calculations are based on.
     */
    public void calculatePrice(ItemTable itemTable) {
        totalPrice = itemTable.getRunningTotalIncludingVAT();
        calculateVAT(itemTable);
    }

    /**
     * Alters total price if customer is eligible for discount
     * @param customerID ID of the customer.
     */
    public void calculateDiscount(int customerID) {
        if (customerID == 123) {
            totalPrice *= 0.5;
            totalVAT *= 0.5;
        }
    }

    /**
     * Registers amount paid and calculates discount.
     * @param amountPaid
     */
    public void calculatePayment(double amountPaid) {
        this.amountPaid = amountPaid;
        change = amountPaid - totalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getTotalVAT() {
        return totalVAT;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public double getChange() {
        return change;
    }

    private void calculateVAT(ItemTable itemTable) {
        for (ItemTableEntryDTO entry : itemTable.getTable())
            totalVAT += entry.getItemDTO().getPrice() * entry.getQuantity() * entry.getItemDTO().getVATRate();
    }
}
