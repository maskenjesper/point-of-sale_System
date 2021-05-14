package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.model.TotalRevenueObserver;

/**
 * A observer class that implements the TotalRevenueObserver interface. Used for observing the totalPrice of ended
 * Sale objects and adding these. This observer prints the totalRevenue to the view.
 */
public class TotalRevenueView implements TotalRevenueObserver {
    private double totalRevenue;

    /**
     * Constructs the object by initializing the totalRevenue to 0
     */
    public TotalRevenueView() {
        totalRevenue = 0;
    }

    /**
     * The method that notifies this observer that the observed object has changed state and gives it the relevant state
     * @param totalPrice The total price of the sale that has been paid
     */
    @Override
    public void saleEnded(double totalPrice) {
        totalRevenue += totalPrice;
        printTotalRevenue();
    }

    /**
     * Prints the totalRevenue to the view
     */
    private void  printTotalRevenue() {
        System.out.println("######## TotalRevenueView output ########\n total revenue:" + totalRevenue +
                "\n#########################################");
    }
}
