package se.kth.iv1350.pos.integration;

import se.kth.iv1350.pos.model.TotalRevenueObserver;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * A observer class that implements the TotalRevenueObserver interface. Used for observing the totalPrice of ended
 * Sale objects and adding these. This observer prints the totalRevenue to a file.
 */
public class TotalRevenueFileOutput implements TotalRevenueObserver {
    private double totalRevenue;
    private PrintWriter logStream;

    /**
     * Constructs the object by initializing the totalRevenue to 0 and starts a PrintWriter.
     */
    public TotalRevenueFileOutput() {
        totalRevenue = 0;
        try {
            logStream = new PrintWriter(new FileWriter("total_revenue_log.txt"), true);
        } catch (IOException e) {
            System.out.println("CAN NOT LOG");
            e.printStackTrace();
        }
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
     * Prints the totalRevenue to a file
     */
    private void  printTotalRevenue() {
        logStream.println(totalRevenue);
    }
}
