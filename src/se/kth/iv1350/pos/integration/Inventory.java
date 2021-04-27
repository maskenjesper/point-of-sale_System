package se.kth.iv1350.pos.integration;

import se.kth.iv1350.pos.DTO.ItemDTO;

/**
 * Class used to interface between the application and an external system that handles inventory.
 */
public class Inventory {
    private ItemDTO mockItem1;
    private ItemDTO mockItem2;

    public Inventory() {
        this.mockItem1 = new ItemDTO(1, 25, "Naturens sportdryck", "Mjölk", "SEK", 20);
        this.mockItem2 = new ItemDTO(2, 20, "Nybakat surdegsbröd", "Levain", "SEK", 20);
    }

    public ItemDTO getItemInfo(int itemIdentifier) {
        if (itemIdentifier == 1)
            return mockItem1;
        else if (itemIdentifier == 2)
            return mockItem2;
        return null;
    }
}
