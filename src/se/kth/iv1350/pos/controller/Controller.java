package se.kth.iv1350.pos.controller;

import se.kth.iv1350.pos.DTO.ItemDTO;
import se.kth.iv1350.pos.DTO.ItemTableEntryDTO;
import se.kth.iv1350.pos.DTO.SaleDTO;
import se.kth.iv1350.pos.integration.Accounting;
import se.kth.iv1350.pos.integration.CashRegister;
import se.kth.iv1350.pos.integration.Inventory;
import se.kth.iv1350.pos.integration.ReceiptPrinter;
import se.kth.iv1350.pos.model.Sale;

// Alla system operationer returnerar en SaleDTO i princip. Jag kanske har varit lite lat med designen då jag vet att SaleDTO innehåller all info som viewn behöver.
// Men jag kanske borde skicka bara det som krävs enligt kravspecen.
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
    public Controller() { // Borde detta göras på något annat sätt eller är detta okej? Man kanske kan ha något registryCreator som skapar dessa?
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
     * and updates the running total.
     * @param itemIdentifier Specifies the type of item to add.
     * @param quantity The quantity of the item to be added to the sale.
     * @return Returns a <code>SaleDTO</code> if the <code>itemIdentifier</code> is valid and <code>null</code> otherwise.
     */
    public SaleDTO addItemToSale(int itemIdentifier, int quantity) { // Gör denna metod bara en sak? Kan inte komma på något att bryta ut.
        ItemDTO item = inventory.getItemInfo(itemIdentifier);
        if (item != null) {
            sale.addItem(new ItemTableEntryDTO(item, quantity));
            return sale.getSaleDTO(); // Eller borde jag skriva new SaleDTO(sale)? Har skapat en getSaleDTO i Sale klassen.
        }
        else
            return null;
    }

    /**
     * Calculates the attributes of <code>this</code> based on <code>itemtable</code>.
     * @return <code>SaleDTO</code> of the modified sale.
     */
    public SaleDTO endRegistering() {
        return sale.summarize();
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
        return updateExternalSystems();
    }

    // Man ska inte ha javadocs på privata metoder?
    private SaleDTO updateExternalSystems() { // Är detta en lämplig utbrytning?
        SaleDTO info = sale.getSaleDTO();
        inventory.updateRegistry(info);
        accounting.addSaleRecord(info);
        cashRegister.addPayment(info);
        receiptPrinter.printReceipt(info);
        return info;
    }
}
