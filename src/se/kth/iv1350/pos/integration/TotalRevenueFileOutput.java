package se.kth.iv1350.pos.integration;

import se.kth.iv1350.pos.model.TotalRevenueObserver;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * A observer class that implements the TotalRevenueObserver interface. Used for observing the totalPrice of ended
 * Sale objects and adding these. This observer prints the totalRevenue to a file.
 */
public class TotalRevenueFileOutput extends TotalRevenueObserver {
    private PrintWriter logStream;

    @Override
    protected void doShowTotalRevenue() throws IOException {
        logStream = new PrintWriter(new FileWriter("total_revenue_log.txt"), true);
        logStream.println(totalRevenue);
    }

    @Override
    protected void handleErrors(Exception e) {
        System.out.println("CAN NOT LOG");
        e.printStackTrace();
    }
}
