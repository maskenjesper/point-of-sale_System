package se.kth.iv1350.pos.integration;

import se.kth.iv1350.pos.DTO.*;

/**
 * Class used to interface between the application and an external system that handles a receipt printer.
 */
public class ReceiptPrinter {

    // Placeholder implementation that just prints to console
    /**
     * Communicates with an external system that handles a receipt printer that it should print a receipt.
     * @param content The content of receipt.
     */
    public void printReceipt(SaleDTO content) {
        System.out.println("#################################- RECEIPT -#################################");
        System.out.println("Totalt pris: " + content.getPaymentInformationDTO().getTotalPrice().getPrice() +
                "\nVarav VAT: " + content.getPaymentInformationDTO().getTotalPrice().getVAT() + "\nBetalat: " +
                content.getPaymentInformationDTO().getAmountPaid() + "\nVäxel: " +
                content.getPaymentInformationDTO().getChange() + "\nDatum och tid: " + content.getDateAndTime() +
                "\nButik: " + content.getStoreInformationDTO().getStoreName() + "\nAdress: " +
                addressDTOToString(content.getStoreInformationDTO().getStoreAddress()) + "\n" +
                itemTableDTOToString(content.getItemTableDTO()));
        System.out.println("#############################################################################");
    }


    private String addressDTOToString(AddressDTO addressDTO) {
        return addressDTO.getStreet() + ", " + addressDTO.getCity() + ", " + addressDTO.getCountry() + ", " +
                addressDTO.getZIP();
    }

    private String itemTableDTOToString(ItemTableDTO itemTableDTO) {
        StringBuilder sb = new StringBuilder();
        for (ItemTableEntryDTO entry:itemTableDTO.getTable())
            sb.append("| " + entry.getQuantity() + "st " + itemDTOToString(entry.getItemDTO()) + "\n");
        return sb.toString();
    }

    private String itemDTOToString(ItemDTO itemDTO) {
        return "| " + itemDTO.getPrice() + itemDTO.getCurrency() + " (VAT: " + itemDTO.getVATRate() * 100 + "%) | " +
                itemDTO.getName() + ": " + itemDTO.getDescription() + " | ID: " + itemDTO.getIdentifier() + " |";
    }
}
