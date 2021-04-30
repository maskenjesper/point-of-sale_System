package se.kth.iv1350.pos.model;

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

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setTotalVAT(double totalVAT) {
        this.totalVAT = totalVAT;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public void setChange(double change) {
        this.change = change;
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
}
