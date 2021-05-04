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
