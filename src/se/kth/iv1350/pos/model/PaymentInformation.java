package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.DTO.ItemTableEntryDTO;
import se.kth.iv1350.pos.utility.VAT;

public class PaymentInformation {
    private double totalPrice;
    private double totalVAT;
    private double amountPaid;
    private double change;

    public PaymentInformation() {
        totalPrice = 0;
        totalVAT = 0;
        amountPaid = 0;
        change = 0;
    }

    public void calculatePrice(ItemTable itemTable) {
        totalPrice = itemTable.getRunningTotalIncludingVAT();
        calculateVAT(itemTable);
    }

    public void calculateDiscount(int customerID) {
        if (customerID == 123)
            totalPrice = totalPrice * 0.5;
    }

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
        /*double totalVAT = 0;
        int numberOfItems = 0;
        for (ItemTableEntryDTO entry:itemTable.getTable()) {
            totalVAT += entry.getItemDTO().getVATRate() * entry.getQuantity();
            numberOfItems += entry.getQuantity();
        }
        this.totalVAT = VAT.convertPercentToCoefficient(totalVAT / numberOfItems) * totalPrice;*/
    }
}
