package se.kth.iv1350.pos.model;

/**
 * Used to handle the calculations related to payment for a sale.
 */
public class PaymentHandeler {

    public double calculatePayment(double totalPrice, double amountPaid) {
        return amountPaid - totalPrice;
    }
}
