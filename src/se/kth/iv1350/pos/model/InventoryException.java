package se.kth.iv1350.pos.model;

/**
 * Exception that conveys a general inventory error
 */
public class InventoryException extends Exception{

    /**
     * Constructor of a new Inventory exception
     * @param message error message for the exception
     */
    public InventoryException(String message) {
        super(message);
    }

    /**
     * Constructor of a new Inventory exception
     * @param message error message for the exception
     * @param cause the exception that caused this
     */
    public InventoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
