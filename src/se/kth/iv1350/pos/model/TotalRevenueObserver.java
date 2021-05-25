package se.kth.iv1350.pos.model;

/**
 * Interface for observers related to total revenue
 */
public interface TotalRevenueObserver {

    /**
     * Notify that a sale has finished
     * @param totalPrice total price of the finished sale
     */
    void saleEnded(double totalPrice);
}
