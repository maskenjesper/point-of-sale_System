package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.model.TotalRevenueObserver;

/**
 * A observer class that implements the TotalRevenueObserver interface. Used for observing the totalPrice of ended
 * Sale objects and adding these. This observer prints the totalRevenue to the view.
 */
public class TotalRevenueView extends TotalRevenueObserver {

    @Override
    protected void  doShowTotalRevenue() {
        System.out.println("######## TotalRevenueView output ########\n total revenue:" + super.totalRevenue +
                "\n#########################################");
    }

    @Override
    protected void handleErrors(Exception e) {

    }
}
