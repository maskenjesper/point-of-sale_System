package se.kth.iv1350.pos.controller;

import se.kth.iv1350.pos.DTO.ItemDTO;
import se.kth.iv1350.pos.DTO.ItemTableEntryDTO;
import se.kth.iv1350.pos.DTO.SaleDTO;
import se.kth.iv1350.pos.integration.*;
import se.kth.iv1350.pos.model.InventoryException;
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
    public Controller() {
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

    /**
     * Searches for an identifier match and if successful adds found item together with the quantity in the sales table
     * and updates the running total (including VAT).
     * If there already is an instance of the found item in the list, it's quantity is increased and the entry is moved
     * the last position in the table.
     * @param itemIdentifier Specifies the type of item to add.
     * @param quantity The quantity of the item to be added to the sale.
     * @return Returns a <code>SaleDTO</code> if the <code>itemIdentifier</code> is valid and <code>null</code> otherwise.
     */
    public SaleDTO addItemToSale(int itemIdentifier, int quantity) throws InventoryException { // Det mer specifika undantaget fångas av controllern och kastar ett mer generellt
        try {                                                                                  // undantag istället med det specifika som cause
            ItemDTO foundItem = inventory.getItemInfo(itemIdentifier);
            sale.addItem(new ItemTableEntryDTO(foundItem, quantity));
            return sale.getSaleDTO();
        } catch (InvalidItemIdentifierException e) {
            throw new InventoryException("Inventory failure", e);
        }
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
    public SaleDTO requestDiscount(int customerID) {
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

    private void updateExternalSystems(SaleDTO info) {
        inventory.updateRegistry(info);
        accounting.addSaleRecord(info);
        cashRegister.addPayment(info);
        receiptPrinter.printReceipt(info);
    }
}
