package se.kth.iv1350.pos.integration;

/**
 * Exception that conveys that the database server is not running
 */
public class DatabaseServerNotRunningException extends Exception {

    /**
     * Constructor of a new Inventory exception
     * @param message error message for the exception
     */
    public DatabaseServerNotRunningException(String message) {
        super(message);
    }
}
