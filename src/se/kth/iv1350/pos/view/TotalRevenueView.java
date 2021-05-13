package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.model.TotalRevenueObserver;

public class TotalRevenueView implements TotalRevenueObserver {
    private double totalRevenue;

    public TotalRevenueView() {
        totalRevenue = 0;
    }

    @Override
    public void saleEnded(double totalPrice) {
        totalRevenue += totalPrice;
        printTotalRevenue();
    }

    private void  printTotalRevenue() {
        System.out.println("######## TotalRevenueView output ########\n total revenue:" + totalRevenue +
                "\n#########################################");
    }
}
