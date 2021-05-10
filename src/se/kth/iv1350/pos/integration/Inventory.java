package se.kth.iv1350.pos.integration;

import se.kth.iv1350.pos.DTO.ItemDTO;
import se.kth.iv1350.pos.DTO.SaleDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used to interface between the application and an external system that handles inventory.
 */
public class Inventory {
    private List<ItemDTO> mockItems;

    /**
     * Constructor that instantiates mockdata for showcasing.
     */
    public Inventory() {
        mockItems = new ArrayList<>();
        mockItems.add(new ItemDTO(1, 25, "Naturens sportdryck", "Mjölk", "SEK", 0.2));
        mockItems.add(new ItemDTO(2, 20, "Nybakat surdegsbröd", "Levain", "SEK", 0.3));
    }

    /**
     * Searches through the available mockdata and returns one with identifier matching <code>itemIdentifier</code>.
     * @param itemIdentifier The identifier that specifies what item is searched for.
     * @return <code>ItemDTO</code> of found item. If none is found, then <code>null</code>.
     */
    public ItemDTO getItemInfo(int itemIdentifier) throws Exception {   // TODO create custom exception
        for (ItemDTO item : mockItems)
            if (item.getIdentifier() == itemIdentifier)
                return item;
        throw new Exception("Invalid identifier");
    }

    /**
     * Updates an external registry system.
     * @param info Sale information that specify how the registry should be updated.
     */
    public void updateRegistry(SaleDTO info) {
        //  Implementation
    }
}
