package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.model.ShowTotalRevenueObserver;

/**
 * A observer class that implements the TotalRevenueObserver interface. Used for observing the totalPrice of ended
 * Sale objects and adding these. This observer prints the totalRevenue to the view.
 */
public class TotalRevenueView extends ShowTotalRevenueObserver {

    @Override
    protected void  doShowTotalRevenue() {
        System.out.println("######## TotalRevenueView output ########\n total revenue:" + totalRevenue +
                "\n#########################################");
    }

    @Override
    protected void handleErrors(Exception e) {
        e.printStackTrace();
    }
}
