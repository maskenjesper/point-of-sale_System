package se.kth.iv1350.pos.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Contains utility methods related to time.
 */
public class Time {
    /**
     * Gets the systems current time
     * @return System time as String
     */
    public static String getCurrentSystemTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
