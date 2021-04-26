package se.kth.iv1350.pos.model;

/**
 * This class is used to create an object containing all information about a specific sale.
 */
public class Sale {
    private ItemTable itemTable;

    /**
     * Constructor for a Sale that initializes by creating an empty ItemTable.
     */
    public Sale() {
        itemTable = new ItemTable();
    }
}
