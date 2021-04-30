package se.kth.iv1350.pos.model;

/**
 * Used to handle the calculations related to payment for a sale.
 */
public class PaymentHandeler {

    /**
     * Calculates the payment and change.
     * @param totalPrice The total price of the sale.
     * @param amountPaid The amount paid by the customer.
     * @return Change of the sale.
     */
    public void calculatePayment(PaymentInformation paymentInformation, double amountPaid) {
        paymentInformation.setAmountPaid(amountPaid);
        paymentInformation.setChange(amountPaid - paymentInformation.getTotalPrice());
    }
}
