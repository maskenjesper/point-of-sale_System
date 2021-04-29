package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.DTO.AddressDTO;
import se.kth.iv1350.pos.DTO.ItemDTO;
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
    public Sale() { // Är detta lämpligt sätt att initiera sale på?
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
    public SaleDTO summarize() { // Är det tanken att VAT ska kalkyleras här eller ska det göras på varje item när dem läggs.
        totalPrice = itemTable.getRunningTotal();
        totalVAT = (calculateVAT(itemTable) / 100) * totalPrice;
        return new SaleDTO(this);
    }

    private double calculateVAT(ItemTable itemTable) {
        double totalVAT = 0;
        int numberOfItems = 0;
        for (ItemTableEntryDTO entry:itemTable.getTable()) {
            totalVAT += entry.getItemDTO().getVATRate() * entry.getQuantity();
            numberOfItems += entry.getQuantity();
        }
        return totalVAT / numberOfItems;
    }

    /**
     * Calculate discount
     * @param customerID Used for verification.
     */
    public void addDiscount(int customerID) {
        DiscountCalculator discountCalculator = new DiscountCalculator();
        discountCalculator.calculateDiscount(this, customerID);
    }

    /**
     * Adds payment and change.
     * @param amountPaid Amount paid by customer for the sale.
     */
    public void addPayment(double amountPaid) {
        PaymentHandeler paymentHandeler = new PaymentHandeler();
        this.amountPaid = amountPaid;
        paymentHandeler.calculatePayment(this, amountPaid);
    }

    /**
     * Calls the constructor of SaleDTO that constructs a SaleDTO object from a Sale object. Sends <code>this</code>.
     * @return SaleDTO of <code>this</code>.
     */
    public SaleDTO getSaleDTO() {
        return new SaleDTO(this);
    }

    /**
     * @return totalPrice attribute.
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * @return totalVAT attribute.
     */
    public double getTotalVAT() {
        return totalVAT;
    }

    /**
     * @return amountPaid attribute.
     */
    public double getAmountPaid() {
        return amountPaid;
    }

    /**
     * @return change attribute.
     */
    public double getChange() {
        return change;
    }

    /**
     * @return dateAndTime attribute.
     */
    public String getDateAndTime() {
        return dateAndTime;
    }

    /**
     * @return storeName attribute.
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     * @return storeAddress attribute.
     */
    public AddressDTO getStoreAddress() {
        return storeAddress;
    }

    /**
     * @return itemTable attribute.
     */
    public ItemTable getItemTable() {
        return itemTable;
    }

    /**
     * Setter for totalPrice.
     * @param newTotalPrice New total price.
     */
    public void setTotalPrice(double newTotalPrice) {
        totalPrice = newTotalPrice;
    }

    /**
     * Sets new amountPaid
     * @param newAmountPaid
     */
    public void setAmountPaid(double newAmountPaid) {
        amountPaid = newAmountPaid;
    }

    /**
     * Sets new change
     * @param newChange
     */
    public void setChange(double newChange) {
        change = newChange;
    }
}
