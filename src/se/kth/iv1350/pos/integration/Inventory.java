package se.kth.iv1350.pos.integration;

import se.kth.iv1350.pos.DTO.ItemDTO;
import se.kth.iv1350.pos.DTO.SaleDTO;

/**
 * Class used to interface between the application and an external system that handles inventory.
 */
public class Inventory {
    // Är tanken att man ska göra på detta vis eftersom vi inte ska implementera något i data lagret?
    // Och instantiera dessa attribut i konstruktorn. Tänker att jag bara behöver två olika items för att kunna
    // visa allt programmet kan göra men jag kanske borde göra en array åtminstone (eller List)?
    private ItemDTO mockItem1;
    private ItemDTO mockItem2;

    /**
     * Constructor that instantiates mockdata for showcasing.
     */
    public Inventory() {
        this.mockItem1 = new ItemDTO(1, 25, "Naturens sportdryck", "Mjölk", "SEK", 20);
        this.mockItem2 = new ItemDTO(2, 20, "Nybakat surdegsbröd", "Levain", "SEK", 30);
    }

    /**
     * Searches through the available mockdata and returns one with identifier matching <code>itemIdentifier</code>.
     * @param itemIdentifier The identifier that specifies what item is searched for.
     * @return <code>ItemDTO</code> of found item. If none is found, then <code>null</code>.
     */
    public ItemDTO getItemInfo(int itemIdentifier) {
        if (itemIdentifier == 1)
            return mockItem1;
        else if (itemIdentifier == 2)
            return mockItem2;
        return null;
    }

    /**
     * Updates an external registry system.
     * @param info Sale information that specify how the registry should be updated.
     */
    public void updateRegistry(SaleDTO info) { // Är tanken med dessa att dem bara ska vara tomma? Att dem bara finns för att man ska ha något att anrop?
        // TODO     Implementation              // Samma med metoderna i dem andra klasserna i integration?
    }                                           // Är det dåligt att jag bara skickar SaleDTO till dessa system?
}
