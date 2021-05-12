package se.kth.iv1350.pos.integration;

/**
 * Exception that conveys that an invalid item identifier has been sent
 */
public class InvalidItemIdentifierException extends Exception{
    private int itemIdentifier;

    /**
     * Constructor of a new Inventory exception
     * @param message error message for the exception
     * @param triedItemIdentifier the invalid identifier that was tried
     */
    public InvalidItemIdentifierException(String message, int triedItemIdentifier) {
        super(message);
        itemIdentifier = triedItemIdentifier;
    }

    /**
     * @return the itemIdentifier attribute
     */
    public int getItemIdentifier() {
        return itemIdentifier;
    }
}
