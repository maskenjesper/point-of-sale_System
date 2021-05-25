package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.model.ShowTotalRevenueObserver;

/**
 * A observer class that implements the ShowTotalRevenueObserver abstract class. Displays total revenue to the console.
 */
public class TotalRevenueView extends ShowTotalRevenueObserver {

    /**
     * Shows the total revenue in the console
     */
    @Override
    protected void  doShowTotalRevenue() {
        System.out.println("######## TotalRevenueView output ########\n total revenue:" + totalRevenue +
                "\n#########################################");
    }

    /**
     * Handles general exception
     * @param e general exception
     */
    @Override
    protected void handleErrors(Exception e) {
        e.printStackTrace();
    }
}
