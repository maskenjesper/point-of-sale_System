package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.DTO.AddressDTO;
import se.kth.iv1350.pos.DTO.ItemDTO;
import se.kth.iv1350.pos.DTO.ItemTableEntryDTO;
import se.kth.iv1350.pos.DTO.SaleDTO;
import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.model.ItemTable;

/**
 * A placeholder class to represent the interface between the application and user. Used to call system operations.
 */
public class View {
    private Controller controller;

    /**
     * The default constructor for a View that creates a Controller instance and stores a reference to it.
     */
    public View() {
        controller = new Controller();
    }

    /**
     * A method that calls all the sysops in appropriate order and with variations to showcase the programs
     * functionality.
     */
    public void sampleExecution() {
        System.out.println("START SALE:\n");
        controller.startSale();

        System.out.println("ADD ITEM WITH IDENTIFIER 1 QUANTITY 20:");
        printAfterAddItemToSale(controller.addItemToSale(1, 20));

        System.out.println("ADD ITEM WITH IDENTIFIER 2 QUANTITY 20:");
        printAfterAddItemToSale(controller.addItemToSale(2, 20));

        System.out.println("ADD ITEM WITH IDENTIFIER 1 AGAIN QUANTITY 5:");
        printAfterAddItemToSale(controller.addItemToSale(1, 5));

        System.out.println("ADD ITEM WITH IDENTIFIER 3 (INVALID) QUANTITY 20:");
        printAfterAddItemToSale(controller.addItemToSale(3, 20));

        System.out.println("END REGISTERING:");
        printAfterEndRegistering(controller.endRegistering());

        System.out.println("REQUEST DISCOUNT (INVALID CUSTOMERID):");
        printAfterDiscountRequest(controller.requestDiscount(124));

        System.out.println("REQUEST DISCOUNT:");
        printAfterDiscountRequest(controller.requestDiscount(123));

        System.out.println("ADD PAYMENT:");
        printAfterAddPayment(controller.addPayment(2000));

    }

    private void printAfterAddItemToSale(SaleDTO saleDTO) {
        if (saleDTO != null)
            System.out.println("Item added: " + saleDTO.getItemTable().getLastItemInTable().getName() +
                    ": " + saleDTO.getItemTable().getLastItemInTable().getDescription() +
                    "\nPrice: " + saleDTO.getItemTable().getLastItemInTable().getPrice() +
                    "\nRunning total: " + saleDTO.getItemTable().getRunningTotalIncludingVAT() + " " +
                    saleDTO.getItemTable().getLastItemInTable().getCurrency() + "\n");
        else
            System.out.println("Invalid identifier\n");
    }

    private void printAfterEndRegistering(SaleDTO saleDTO) {
        System.out.println("Sale ended\nTotal price: " + saleDTO.getPaymentInformation().getTotalPrice() + " SEK\n");
    }

    private void printAfterDiscountRequest(SaleDTO saleDTO) {
        System.out.println("Price after discount: " + saleDTO.getPaymentInformation().getTotalPrice() + " SEK\n");
    }

    private void printAfterAddPayment(SaleDTO saleDTO) {
        System.out.println("Change to give: " + saleDTO.getPaymentInformation().getChange());
        printReceipt(saleDTO);
    }

    private void printReceipt(SaleDTO saleDTO) {
        System.out.println("Receipt:\n" + "Totalt pris: " + saleDTO.getPaymentInformation().getTotalPrice() +
                "\nVarav VAT: " + saleDTO.getPaymentInformation().getTotalVAT() + "\nBetalat: " +
                saleDTO.getPaymentInformation().getAmountPaid() + "\nVäxel: " +
                saleDTO.getPaymentInformation().getChange() + "\nDatum och tid: " + saleDTO.getDateAndTime() +
                "\nButik: " + saleDTO.getStoreInformation().getStoreName() + "\nAdress: " +
                addressDTOToString(saleDTO.getStoreInformation().getStoreAddress()) + "\n" +
                itemTableToString(saleDTO.getItemTable()));
    }

    private String addressDTOToString(AddressDTO addressDTO) {
        return addressDTO.getStreet() + ", " + addressDTO.getCity() + ", " + addressDTO.getCountry() + ", " +
                addressDTO.getZIP();
    }

    private String itemTableToString(ItemTable itemTable) {
        StringBuilder sb = new StringBuilder();
        for (ItemTableEntryDTO entry:itemTable.getTable())
            sb.append("| " + entry.getQuantity() + "st " + itemDTOToString(entry.getItemDTO()) + "\n");
        return sb.toString();
    }

    private String itemDTOToString(ItemDTO itemDTO) {
        return "| " + itemDTO.getPrice() + itemDTO.getCurrency() + " (VAT: " + itemDTO.getVATRate() * 100 + "%) | " +
                itemDTO.getName() + ": " + itemDTO.getDescription() + " | ID: " + itemDTO.getIdentifier() + " |";
    }
}
