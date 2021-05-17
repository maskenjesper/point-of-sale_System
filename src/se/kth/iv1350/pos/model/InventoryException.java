package se.kth.iv1350.pos.model;

/**
 * Exception that conveys a general inventory error
 */
public class InventoryException extends Exception {

    /**
     * Constructor of a new Inventory exception
     * @param message error message for the exception
     */
    public InventoryException(String message) {
        super(message);
    }
}
