package se.kth.iv1350.pos.integration;

import se.kth.iv1350.pos.DTO.SaleDTO;

/**
 * Class used to interface between the application and an external system that handles a cash register.
 */
public class CashRegister {

    /**
     * Communicates with an external system that handles a cash register that the balance should be updated.
     * @param info The information about the sale whose payment shall be added.
     */
    public void addPayment(SaleDTO info) {
        //      Implementation
    }
}
