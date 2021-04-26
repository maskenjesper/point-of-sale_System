package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.DTO.AddressDTO;
import se.kth.iv1350.pos.DTO.ItemTableEntryDTO;

/**
 * This class is used to create an object containing all information about a specific sale.
 */
public class Sale {
    private double totalPrice;
    private double totalVAT;
    private double amountPaid;
    private double change;
    private String dateAndTime;
    private String storeName;
    private AddressDTO storeAddress;
    private ItemTable itemTable;

    /**
     * Constructor for a Sale that initializes by creating an empty ItemTable.
     */
    public Sale() {
        itemTable = new ItemTable();
        dateAndTime = "23:17";
        storeName = "Jakobs liv's";
        storeAddress = new AddressDTO("Gatuvägen", "Bostadsstaden", "Sverige", 12345);
    }

    /**
     * Adds an entry to the sales item table.
     * @param entry The entry to add.
     */
    public void addItem(ItemTableEntryDTO entry) {
        itemTable.add(entry);
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getTotalVAT() {
        return totalVAT;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public double getChange() {
        return change;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public String getStoreName() {
        return storeName;
    }

    public AddressDTO getStoreAddress() {
        return storeAddress;
    }

    public ItemTable getItemTable() {
        return itemTable;
    }
}
