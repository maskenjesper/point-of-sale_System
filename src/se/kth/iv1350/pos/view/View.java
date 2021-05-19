package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.DTO.*;
import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.integration.InvalidItemIdentifierException;
import se.kth.iv1350.pos.controller.InventoryException;

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
        controller.activateTotalRevenueView(new TotalRevenueView());
        performSaleV1();
        performSaleV2();
    }

    /**
     * Performs a specific variation of a sale
     */
    private void performSaleV1() {
        System.out.println("START SALE:\n");
        controller.startSale();

        System.out.println("ADD ITEM WITH IDENTIFIER 1 QUANTITY 20:");
        addItemToSale(1, 20);

        System.out.println("ADD ITEM WITH IDENTIFIER 2 QUANTITY 20:");
        addItemToSale(2, 20);

        System.out.println("ADD ITEM WITH IDENTIFIER 1 AGAIN QUANTITY 5:");
        addItemToSale(1, 5);

        System.out.println("ADD ITEM WITH IDENTIFIER 3 (INVALID) QUANTITY 20:");
        addItemToSale(3, 20);

        System.out.println("ADD ITEM WITH IDENTIFIER 500 (SHOWCASE DATABASE SERVER NOT RUNNING) QUANTITY 20:");
        addItemToSale(500, 20);

        System.out.println("END REGISTERING:");
        endRegistering();

        System.out.println("REQUEST DISCOUNT (INVALID CUSTOMERID):");
        discountRequest(1);

        System.out.println("REQUEST DISCOUNT:");
        discountRequest(123);

        System.out.println("ADD PAYMENT:");
        addPayment(2000);
    }

    /**
     * Performs a specific variation of a sale
     */
    private void performSaleV2() {
        System.out.println("START SALE:\n");
        controller.startSale();

        System.out.println("ADD ITEM WITH IDENTIFIER 1 QUANTITY 1:");
        addItemToSale(1, 1);

        System.out.println("ADD ITEM WITH IDENTIFIER 2 QUANTITY 1:");
        addItemToSale(2, 1);

        System.out.println("END REGISTERING:");
        endRegistering();

        System.out.println("ADD PAYMENT:");
        addPayment(2000);
    }

    private void addItemToSale(int itemIdentifier, int quantity) {
        try {
            SaleDTO saleDTO = controller.addItemToSale(itemIdentifier, quantity);
            System.out.println("Item added: " + saleDTO.getItemTableDTO().getLastItemInTable().getName() +
                    ": " + saleDTO.getItemTableDTO().getLastItemInTable().getDescription() +
                    "\nPrice: " + saleDTO.getItemTableDTO().getLastItemInTable().getPrice() + " | VAT: " +
                    saleDTO.getItemTableDTO().getLastItemInTable().getVATRate() * 100 + "%" +
                    "\nRunning total: " + saleDTO.getItemTableDTO().getRunningTotalIncludingVAT() + " " +
                    saleDTO.getItemTableDTO().getLastItemInTable().getCurrency() + "\n");
        } catch (InventoryException e) {
            System.out.println("Inventory failure\n");
        } catch (InvalidItemIdentifierException e) {
            System.out.println("Invalid item identifier: " + e.getItemIdentifier() + "\n");
        }
    }

    private void endRegistering() {
        SaleDTO saleDTO = controller.endRegistering();
        System.out.println("Sale ended\nTotal price: " + saleDTO.getPaymentInformationDTO().getTotalPrice().getPrice()
                + " SEK\n");
    }

    private void discountRequest(int customerID) {
        SaleDTO saleDTO = controller.requestDiscount(customerID);
        System.out.println("Price after discount: " + saleDTO.getPaymentInformationDTO().getTotalPrice().getPrice()
                + " SEK\n");
    }

    private void addPayment(double amountPaid) {
        SaleDTO saleDTO = controller.addPayment(amountPaid);
        System.out.println("Change to give: " + saleDTO.getPaymentInformationDTO().getChange() + "\n");
        printReceipt(saleDTO);
    }

    private void printReceipt(SaleDTO saleDTO) {
        System.out.println("#################################- RECEIPT -#################################");
        System.out.println("Totalt pris: " + saleDTO.getPaymentInformationDTO().getTotalPrice().getPrice() +
                "\nVarav VAT: " + saleDTO.getPaymentInformationDTO().getTotalPrice().getVAT() + "\nBetalat: " +
                saleDTO.getPaymentInformationDTO().getAmountPaid() + "\nVäxel: " +
                saleDTO.getPaymentInformationDTO().getChange() + "\nDatum och tid: " + saleDTO.getDateAndTime() +
                "\nButik: " + saleDTO.getStoreInformationDTO().getStoreName() + "\nAdress: " +
                addressDTOToString(saleDTO.getStoreInformationDTO().getStoreAddress()) + "\n" +
                itemTableDTOToString(saleDTO.getItemTableDTO()));
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
