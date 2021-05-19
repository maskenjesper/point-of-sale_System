package se.kth.iv1350.pos.DTO;

import se.kth.iv1350.pos.model.Sale;

/**
 * DTO for the Sale class.
 */
public class SaleDTO {
    private final PaymentInformationDTO paymentInformationDTO;
    private final String dateAndTime;
    private final StoreInformationDTO storeInformationDTO;
    private final ItemTableDTO itemTableDTO;

    /**
     * Constructor that builds the DTO from given Sale object.
     * @param sale Sale object to build from.
     */
    public SaleDTO(Sale sale) {
        paymentInformationDTO = sale.getPaymentInformation().getPaymentInformationDTO();
        dateAndTime = sale.getDateAndTime();
        storeInformationDTO = sale.getStoreInformation();
        itemTableDTO = sale.getItemTable().getItemTableDTO();
    }

    public PaymentInformationDTO getPaymentInformationDTO() {
        return paymentInformationDTO;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public StoreInformationDTO getStoreInformationDTO() {
        return storeInformationDTO;
    }

    public ItemTableDTO getItemTableDTO() {
        return itemTableDTO;
    }
}
