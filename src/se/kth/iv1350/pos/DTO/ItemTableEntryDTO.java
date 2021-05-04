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
    public boolean equals(Object other) { // Är detta en OK equals?
        if (!(other instanceof ItemTableEntryDTO))
            return false;
        ItemTableEntryDTO otherItemTableEntryDTO = (ItemTableEntryDTO)other;
        return itemDTO.equals(otherItemTableEntryDTO.itemDTO) &&
                quantity == otherItemTableEntryDTO.quantity;
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
