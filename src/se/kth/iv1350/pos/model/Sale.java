package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.DTO.ItemTableEntryDTO;
import se.kth.iv1350.pos.DTO.SaleDTO;
import se.kth.iv1350.pos.utility.Time;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to create an object containing all information about a specific sale. As well as observers of this instance.
 */
public class Sale {
    private final PaymentInformation paymentInformation;
    private final String dateAndTime;
    private final StoreInformation storeInformation;
    private final ItemTable itemTable;
    private List<TotalRevenueObserver> totalRevenueObservers;

    /**
     * Constructor for a Sale that initializes a new instance.
     */
    public Sale() {
        itemTable = new ItemTable();
        dateAndTime = Time.getCurrentSystemTime();
        paymentInformation = new PaymentInformation();
        storeInformation = new StoreInformation();
        totalRevenueObservers = new ArrayList<>();
    }

    /**
     * Adds a totalRevenueObserver to the sale
     * @param totalRevenueObserver the observer to be added
     */
    public void addTotalRevenueObserver(TotalRevenueObserver totalRevenueObserver) {
        totalRevenueObservers.add(totalRevenueObserver);
    }
    
    /**
     * Adds an item entry to the sale.
     * @param entry The entry to add.
     */
    public void addItem(ItemTableEntryDTO entry) {
        itemTable.add(entry);
    }

    /**
     * Ends the registering phase of the sale by calculating the price.
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
        notifyObservers();
    }

    /**
     * Calls the constructor of SaleDTO that constructs a SaleDTO object from this Sale object.
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

    private void notifyObservers() {
        for (TotalRevenueObserver observer : totalRevenueObservers)
            observer.saleEnded(this.paymentInformation.getTotalPrice().getPrice());
    }
}
