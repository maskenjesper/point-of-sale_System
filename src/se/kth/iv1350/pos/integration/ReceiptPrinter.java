package se.kth.iv1350.pos.integration;

import se.kth.iv1350.pos.DTO.SaleDTO;

/**
 * Class used to interface between the application and an external system that handles a receipt printer.
 */
public class ReceiptPrinter {

    /**
     * Communicates with an external system that handles a receipt printer that it should print a receipt.
     * @param content The content of receipt.
     */
    public void printReceipt(SaleDTO content) {
        // TODO     Implementation
    }
}
