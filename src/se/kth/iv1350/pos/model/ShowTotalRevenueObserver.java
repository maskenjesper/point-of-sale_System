package se.kth.iv1350.pos.model;

/**
 * An abstract class that implements TotalRevenueObserver related to showing total revenue.
 */
public abstract class ShowTotalRevenueObserver implements TotalRevenueObserver {
    protected double totalRevenue = 0;

    /**
     * Invoked when the observed Sale has been paid
     * @param totalPrice The total price of the sale that has been paid
     */
    public void saleEnded(double totalPrice) {
        totalRevenue += totalPrice;
        showTotalRevenue();
    }

    private void showTotalRevenue() {
        try {
            doShowTotalRevenue();
        } catch (Exception e) {
            handleErrors(e);
        }
    }

    /**
     * Shows total revenue
     * @throws Exception general exception
     */
    protected abstract void doShowTotalRevenue() throws Exception;

    /**
     * Handles general exceptions
     * @param e general exception
     */
    protected abstract void handleErrors(Exception e);
}
