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
    public void calculatePayment(Sale sale, double amountPaid) {
        sale.setAmountPaid(amountPaid);
        sale.setChange(amountPaid - sale.getTotalPrice());
    }
}
