package se.kth.iv1350.pos.integration;

import se.kth.iv1350.pos.DTO.ItemDTO;

/**
 * Class used to interface between the application and an external system that handles inventory.
 */
public class Inventory {

    public ItemDTO getItemInfo(int itemIdentifier) {
        if (itemIdentifier == 1)
            return new ItemDTO();
        else
            return new ItemDTO();
        // TODO:    Make some actual mockdata to chose from.
        //          Write the ItemDTO class.
    }
}
