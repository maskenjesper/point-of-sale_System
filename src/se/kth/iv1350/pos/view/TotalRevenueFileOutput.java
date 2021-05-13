package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.model.TotalRevenueObserver;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TotalRevenueFileOutput implements TotalRevenueObserver {
    private double totalRevenue;
    private PrintWriter logStream;

    public TotalRevenueFileOutput() {
        totalRevenue = 0;
        try {
            logStream = new PrintWriter(new FileWriter("total_revenue_log.txt"), true);
        } catch (IOException e) {
            System.out.println("CAN NOT LOG");
            e.printStackTrace();
        }
    }

    @Override
    public void saleEnded(double totalPrice) {
        totalRevenue += totalPrice;
        printTotalRevenue();
    }

    private void  printTotalRevenue() {
        logStream.println(totalRevenue);
    }
}
