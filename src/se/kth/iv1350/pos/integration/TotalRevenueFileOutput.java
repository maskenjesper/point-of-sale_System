package se.kth.iv1350.pos.integration;

import se.kth.iv1350.pos.model.ShowTotalRevenueObserver;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * A observer class that implements the ShowTotalRevenueObserver abstract class. Writes total revenue to file.
 */
public class TotalRevenueFileOutput extends ShowTotalRevenueObserver {
    private PrintWriter logStream;

    /**
     * Constructor that instantiates writing to file and handles possible exceptions
     */
    public TotalRevenueFileOutput() {
        try {
            logStream = new PrintWriter(new FileWriter("total_revenue_log.txt"), true);
        } catch (IOException e) {
            System.out.println("CAN NOT LOG");
            e.printStackTrace();
        }
    }

    /**
     * Writes total revenue to file
     */
    @Override
    protected void doShowTotalRevenue() {
        logStream.println(totalRevenue);
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
