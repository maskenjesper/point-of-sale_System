package se.kth.iv1350.pos.model;

/**
 * An observer interface for observers concerned with tracking total price of paid sales
 */
public abstract class TotalRevenueObserver {
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

    protected abstract void doShowTotalRevenue() throws Exception;

    protected abstract void handleErrors(Exception e);
}
