package se.kth.iv1350.pos.model;

/**
 * An observer interface for observers concerned with tracking total price of paid sales
 */
public interface TotalRevenueObserver {

    /**
     * Invoked when the observed Sale has been paid
     * @param totalPrice The total price of the sale that has been paid
     */
    void paymentAddedToSale(double totalPrice);
}
