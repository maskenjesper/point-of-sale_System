package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.DTO.AddressDTO;
import se.kth.iv1350.pos.DTO.ItemTableEntryDTO;
import se.kth.iv1350.pos.DTO.SaleDTO;

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

    /**
     * Calculates the attributes based on itemtable
     * @return SaleDTO of modified sale
     */
    public SaleDTO summarize() {
        totalPrice = itemTable.runningTotal;
        // TODO:    Make it actually summarize by calculating the information in it's table. It just fills the
        //          totalPrice attribute atm.
        return new SaleDTO(this);
    }

    /**
     * Calculate discount
     * @param customerID
     */
    public void addDiscount(int customerID) {
        DiscountCalculator discountCalculator = new DiscountCalculator();
        totalPrice = discountCalculator.calculateDiscount(itemTable, customerID);
    }

    /**
     * Adds payment and change.
     * @param amountPaid
     */
    public void addPayment(double amountPaid) {
        PaymentHandeler paymentHandeler = new PaymentHandeler();
        this.amountPaid = amountPaid;
        change = paymentHandeler.calculatePayment(this.totalPrice, amountPaid);
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
