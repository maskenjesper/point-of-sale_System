package se.kth.iv1350.pos.integration;

import se.kth.iv1350.pos.model.ShowTotalRevenueObserver;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * A observer class that implements the TotalRevenueObserver interface. Used for observing the totalPrice of ended
 * Sale objects and adding these. This observer prints the totalRevenue to a file.
 */
public class TotalRevenueFileOutput extends ShowTotalRevenueObserver {
    private PrintWriter logStream;

    public TotalRevenueFileOutput() {
        try {
            logStream = new PrintWriter(new FileWriter("total_revenue_log.txt"), true);
        } catch (IOException e) {
            System.out.println("CAN NOT LOG");
            e.printStackTrace();
        }
    }

    @Override
    protected void doShowTotalRevenue() {
        logStream.println(totalRevenue);
    }

    @Override
    protected void handleErrors(Exception e) {
        e.printStackTrace();
    }
}
