package se.kth.iv1350.pos.integration;

import se.kth.iv1350.pos.DTO.ItemDTO;
import se.kth.iv1350.pos.DTO.SaleDTO;
import se.kth.iv1350.pos.data.ItemRegistry;

/**
 * Class used to interface between the application and an external system that handles inventory.
 */
public class Inventory {
    private final ItemRegistry itemRegistry;

    /**
     * Constructor that instantiates mockdata for showcasing.
     */
    public Inventory() {
        itemRegistry = ItemRegistry.getInstance();
    }

    /**
     * Searches through the available mockdata and returns one with identifier matching <code>itemIdentifier</code>.
     * @param itemIdentifier The identifier that specifies what item is searched for.
     * @return <code>ItemDTO</code> of found item. If none is found, then <code>null</code>.
     * @throws InvalidItemIdentifierException if the identifier sent doesn't correspond with an item in the inventory
     * @throws DatabaseServerNotRunningException simulated case for when the database server is not running
     */
    public ItemDTO getItemInfo(int itemIdentifier) throws InvalidItemIdentifierException, DatabaseServerNotRunningException {
        if (itemIdentifier == 500)
            throw new DatabaseServerNotRunningException("The database server is not running");
        for (ItemDTO item : itemRegistry.getItemList())
            if (item.getIdentifier() == itemIdentifier)
                return item;
        throw new InvalidItemIdentifierException("Invalid identifier", itemIdentifier);
    }

    /**
     * Updates an external registry system.
     * @param info Sale information that specify how the registry should be updated.
     */
    public void updateRegistry(SaleDTO info) {
        //  Implementation
    }
}
