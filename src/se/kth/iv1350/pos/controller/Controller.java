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
    public Controller() {
        inventory = new Inventory();
        accounting = new Accounting();
        receiptPrinter = new ReceiptPrinter();
        cashRegister = new CashRegister();
    }

    /**
     * Setup for a new sale by creating a new Sale object
     */
    public void startSale() {
        sale = new Sale();
    }

    /**
     * Searches for an identifier match and if successful adds found item together with the quantity in the sales table.
     * @param itemIdentifier Specifies the type of item to add.
     * @param quantity The quantity of the item to be added to the sale.
     * @return Returns a SaleDTO if successful and null otherwise.
     */
    public SaleDTO addItemToSale(int itemIdentifier, int quantity) {
        ItemDTO item = inventory.getItemInfo(itemIdentifier);
        if (item != null) {
            sale.addItem(new ItemTableEntryDTO(item, quantity));
            return new SaleDTO(sale);
        }
        else
            return null;
    }

    /**
     * Calculates the attributes based on itemtable
     * @return SaleDTO of the modified sale
     */
    public SaleDTO endRegistering() {
        return sale.summarize();
    }

    /**
     * Signals discount request
     * @param customerID Used to verify if discount is available
     * @return SaleDTO of the modified sale.
     */
    public SaleDTO discountRequest(int customerID) {
        sale.addDiscount(customerID);
        return new SaleDTO(sale);
    }

    /**
     * Adds payment and calculates change
     * @param amountPaid
     * @return
     */
    public SaleDTO addPayment(double amountPaid) {
        sale.addPayment(amountPaid);
        SaleDTO info = new SaleDTO(sale);
        inventory.updateRegistry(info);
        accounting.addSaleRecord(info);
        cashRegister.addPayment(info);
        receiptPrinter.printReceipt(info);
        return info;
    }
}
