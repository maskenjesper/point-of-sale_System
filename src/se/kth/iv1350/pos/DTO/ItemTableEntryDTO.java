package se.kth.iv1350.pos.DTO;

/**
 * DTO for the ItemTableEntry class.
 */
public class ItemTableEntryDTO {
    ItemDTO itemDTO;
    int quantity;

    /**
     * Constructor for an ItemTableEntryDTO.
     * @param item The type of item.
     * @param quantity The quantity of the item.
     */
    public ItemTableEntryDTO(ItemDTO item, int quantity) {
        itemDTO = item;
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) { // Är detta en OK equals?
        ItemTableEntryDTO itemTableEntryDTO;
        if (o instanceof ItemTableEntryDTO)
            itemTableEntryDTO = (ItemTableEntryDTO)o;
        else
            return false;
        return itemDTO.equals(itemTableEntryDTO.itemDTO) &&
                quantity == itemTableEntryDTO.quantity;
    }

    /**
     * @return itemDTO attribute.
     */
    public ItemDTO getItemDTO() {
        return itemDTO;
    }

    /**
     * @return quantity attribute.
     */
    public int getQuantity() {
        return quantity;
    }
}
