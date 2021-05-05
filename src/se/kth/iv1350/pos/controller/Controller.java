package se.kth.iv1350.pos.controller;

import se.kth.iv1350.pos.DTO.ItemDTO;
import se.kth.iv1350.pos.DTO.ItemTableEntryDTO;
import se.kth.iv1350.pos.DTO.SaleDTO;
import se.kth.iv1350.pos.integration.Accounting;
import se.kth.iv1350.pos.integration.CashRegister;
import se.kth.iv1350.pos.integration.Inventory;
import se.kth.iv1350.pos.integration.ReceiptPrinter;
import se.kth.iv1350.pos.model.Sale;

/**
 * The controller class used to interface between the view and model layers. Handles system operations called from the
 * view.
 */
public class Controller {
    private Inventory inventory;
    private Accounting accounting;
    private ReceiptPrinter receiptPrinter;
    private CashRegister cashRegister;
    private Sale sale;

    /**
     * The default constructor for Controller. Creates and stores references to all external systems.
     */
    public Controller() { // Får controllern bara kommunicera med view genom dess returvärde?
        inventory = new Inventory();
        accounting = new Accounting();
        receiptPrinter = new ReceiptPrinter();
        cashRegister = new CashRegister();
    }

    /**
     * This method does the setup for a new sale by creating a new Sale object and store a reference to this.
     */
    public void startSale() {
        sale = new Sale();
    }

    // Behövde göra så att en entry flyttas längst bak i listan även om bara quantity ökas. Detta för att view ska veta
    // vilket item som lades till senast så att detta kan displayas.
    /**
     * Searches for an identifier match and if successful adds found item together with the quantity in the sales table
     * and updates the running total (including VAT).
     * If there already is an instance of the found item in the list, it's quantity is increased and the entry is moved
     * the last position in the table.
     * @param itemIdentifier Specifies the type of item to add.
     * @param quantity The quantity of the item to be added to the sale.
     * @return Returns a <code>SaleDTO</code> if the <code>itemIdentifier</code> is valid and <code>null</code> otherwise.
     */
    public SaleDTO addItemToSale(int itemIdentifier, int quantity) {    // Det kanske inte framgår att addFoundItemToSale ska
        ItemDTO foundItem = inventory.getItemInfo(itemIdentifier);      // returnera något men å andra sidan gör inte addItemToSale det heller.
        return addFoundItemToSale(foundItem, quantity);
    }

    /**
     * Calculates the total price of the sale as well as how much of it is for VAT.
     * @return <code>SaleDTO</code> of the modified sale.
     */
    public SaleDTO endRegistering() {
        sale.endRegistering();
        return sale.getSaleDTO();
    }

    /**
     * Signals discount request.
     * @param customerID Used to verify if discount is available.
     * @return <code>SaleDTO</code> of the modified sale.
     */
    public SaleDTO discountRequest(int customerID) {
        sale.addDiscount(customerID);
        return sale.getSaleDTO();
    }

    /**
     * Adds payment and calculates change.
     * @param amountPaid The amount the customer has paid.
     * @return <code>SaleDTO</code> of the modified sale.
     */
    public SaleDTO addPayment(double amountPaid) {
        sale.addPayment(amountPaid);
        SaleDTO saleDTO = sale.getSaleDTO();
        updateExternalSystems(saleDTO);
        return saleDTO;
    }

    private SaleDTO addFoundItemToSale(ItemDTO item, int quantity) {
        if (item != null) {
            sale.addItem(new ItemTableEntryDTO(item, quantity));
            return sale.getSaleDTO();
        }
        else
            return null;
    }

    private void updateExternalSystems(SaleDTO info) {
        inventory.updateRegistry(info);
        accounting.addSaleRecord(info);
        cashRegister.addPayment(info);
        receiptPrinter.printReceipt(info);
    }
}
