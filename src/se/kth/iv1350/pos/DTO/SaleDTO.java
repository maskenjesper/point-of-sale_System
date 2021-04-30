package se.kth.iv1350.pos.DTO;

import se.kth.iv1350.pos.model.ItemTable;
import se.kth.iv1350.pos.model.PaymentInformation;
import se.kth.iv1350.pos.model.Sale;
import se.kth.iv1350.pos.model.StoreInformation;

/**
 * DTO for the Sale class.
 */
public class SaleDTO {
    private PaymentInformation paymentInformation;
    private String dateAndTime;
    private StoreInformation storeInformation;
    private ItemTable itemTable;

    /**
     * Constructor that builds the DTO from given Sale object.
     * @param sale Sale object to build from.
     */
    public SaleDTO(Sale sale) {
        paymentInformation = sale.getPaymentInformation();
        dateAndTime = sale.getDateAndTime();
        storeInformation = sale.getStoreInformation();
        itemTable = sale.getItemTable();
    }

    /**
     * Creates a string representation of <code>this</code>.
     * @return String representation of <code>this</code>.
     */
    @Override
    public String toString() {
        return "Totalt pris: " + paymentInformation.getTotalPrice() + "\nVarav VAT: " +
                paymentInformation.getTotalVAT() + "\nBetalat: " + paymentInformation.getAmountPaid() + "\nVäxel: " +
                paymentInformation.getChange() + "\nDatum och tid: " + dateAndTime + "\nButik: " +
                storeInformation.getStoreName() + "\nAdress: " + storeInformation.getStoreName() + "\n" + itemTable;
    }

    public PaymentInformation getPaymentInformation() {
        return paymentInformation;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public StoreInformation getStoreInformation() {
        return storeInformation;
    }

    public ItemTable getItemTable() {
        return itemTable;
    }
}
