package se.kth.iv1350.pos.DTO;

import se.kth.iv1350.pos.model.ItemTable;
import se.kth.iv1350.pos.model.Sale;

/**
 * DTO for the Sale class.
 */
public class SaleDTO {
    private double totalPrice;
    private double totalVAT;
    private double amountPaid;
    private double change;
    private String dateAndTime;
    private String storeName;
    private AddressDTO storeAddress;
    private ItemTable itemTable;

    /**
     * Constructor that builds the DTO from given Sale object.
     * @param sale Sale object to build from.
     */
    public SaleDTO(Sale sale) {
        totalPrice = sale.getTotalPrice();
        totalVAT = sale.getTotalVAT();
        amountPaid = sale.getAmountPaid();
        change = sale.getChange();
        dateAndTime = sale.getDateAndTime();
        storeName = sale.getStoreName();
        storeAddress = sale.getStoreAddress();
        itemTable = sale.getItemTable();
    }

    /**
     * Creates a string representation of <code>this</code>.
     * @return String representation of <code>this</code>.
     */
    @Override
    public String toString() {
        return "Totalt pris: " + totalPrice + "\nVarav VAT: " + totalVAT + "\nBetalat: " + amountPaid + "\nVäxel: " +
                change + "\nDatum och tid: " + dateAndTime + "\nButik: " + storeName + "\nAdress: " + storeAddress +
                "\n" + itemTable;
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
