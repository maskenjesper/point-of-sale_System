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
        AddItemToSale(1, 20);                                       // Det kanske är bättre att bara göra själva anropet i metoden istället
                                                                    // för att skicka som parameter och ta bort "" prefixet? Som jag gjort nu.
        System.out.println("ADD ITEM WITH IDENTIFIER 2 QUANTITY 20:");
        AddItemToSale(2, 20);

        System.out.println("ADD ITEM WITH IDENTIFIER 1 AGAIN QUANTITY 5:");
        AddItemToSale(1, 5);

        System.out.println("ADD ITEM WITH IDENTIFIER 3 (INVALID) QUANTITY 20:");
        AddItemToSale(3, 20);

        System.out.println("END REGISTERING:");
        EndRegistering();

        System.out.println("REQUEST DISCOUNT (INVALID CUSTOMERID):");
        DiscountRequest(1);

        System.out.println("REQUEST DISCOUNT:");
        DiscountRequest(123);

        System.out.println("ADD PAYMENT:");
        AddPayment(2000);

    }

    private void AddItemToSale(int itemIdentifier, int quantity) {
        try {
            SaleDTO saleDTO = controller.addItemToSale(itemIdentifier, quantity);
            System.out.println("Item added: " + saleDTO.getItemTable().getLastItemInTable().getName() +
                    ": " + saleDTO.getItemTable().getLastItemInTable().getDescription() +
                    "\nPrice: " + saleDTO.getItemTable().getLastItemInTable().getPrice() +
                    "\nRunning total: " + saleDTO.getItemTable().getRunningTotalIncludingVAT() + " " +
                    saleDTO.getItemTable().getLastItemInTable().getCurrency() + "\n");
        } catch (Exception e) {
            System.out.println("Invalid identifier\n");
        }
    }

    private void EndRegistering() {
        SaleDTO saleDTO = controller.endRegistering();
        System.out.println("Sale ended\nTotal price: " + saleDTO.getPaymentInformation().getTotalPrice() + " SEK\n");
    }

    private void DiscountRequest(int customerID) {
        SaleDTO saleDTO = controller.requestDiscount(customerID);
        System.out.println("Price after discount: " + saleDTO.getPaymentInformation().getTotalPrice() + " SEK\n");
    }

    private void AddPayment(double amountPaid) {
        SaleDTO saleDTO = controller.addPayment(amountPaid);
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
