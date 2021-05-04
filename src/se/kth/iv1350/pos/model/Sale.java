package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.DTO.ItemTableEntryDTO;
import se.kth.iv1350.pos.DTO.SaleDTO;
import se.kth.iv1350.pos.utility.Time;

/**
 * This class is used to create an object containing all information about a specific sale.
 */
public class Sale {
    private PaymentInformation paymentInformation;
    private String dateAndTime; // Gör custom objekt istället?
    private StoreInformation storeInformation;
    private ItemTable itemTable;

    /**
     * Constructor for a Sale that initializes a new instance.
     */
    public Sale() {
        itemTable = new ItemTable();
        dateAndTime = Time.getCurrentSystemTime(); // Använder ett utility paket här. Passade inte riktigt som privat metod för denna klass.
        paymentInformation = new PaymentInformation();
        storeInformation = new StoreInformation();
    }

    /**
     * Adds an item entry to the sale.
     * @param entry The entry to add.
     */
    public void addItem(ItemTableEntryDTO entry) {
        itemTable.add(entry);
    }

    /**
     * Ends the registering phase of the sale.
     */
    public void endRegistering() {
        paymentInformation.calculatePrice(itemTable);
    }

    /**
     * Adds a discount to the sale.
     * @param customerID Used for verification.
     */
    public void addDiscount(int customerID) {
        paymentInformation.calculateDiscount(customerID);
    }

    /**
     * Adds payment and change.
     * @param amountPaid Amount paid by customer for the sale.
     */
    public void addPayment(double amountPaid) {
        paymentInformation.calculatePayment(amountPaid);
    }

    /**
     * Calls the constructor of SaleDTO that constructs a SaleDTO object from a Sale object.
     * @return SaleDTO of <code>this</code>.
     */
    public SaleDTO getSaleDTO() {
        return new SaleDTO(this);
    }

    /**
     * @return totalPrice attribute.
     */
    public PaymentInformation getPaymentInformation() {
        return paymentInformation;
    }

    /**
     * @return dateAndTime attribute.
     */
    public String getDateAndTime() {
        return dateAndTime;
    }

    /**
     * @return storeAddress attribute.
     */
    public StoreInformation getStoreInformation() {
        return storeInformation;
    }

    /**
     * @return itemTable attribute.
     */
    public ItemTable getItemTable() {
        return itemTable;
    }
}
