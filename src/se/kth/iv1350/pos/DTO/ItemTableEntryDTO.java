package se.kth.iv1350.pos.DTO;

/**
 * DTO for the ItemTableEntry class.
 */
public class ItemTableEntryDTO {
    private final ItemDTO itemDTO;
    private final int quantity;

    /**
     * Constructor for an ItemTableEntryDTO.
     * @param item The type of item.
     * @param quantity The quantity of the item.
     */
    public ItemTableEntryDTO(ItemDTO item, int quantity) {
        itemDTO = item;
        this.quantity = quantity;
    }

    /**
     * Checks if two item table entries have identical attributes.
     * @param other Object to check equality against.
     * @return True if equal; false if not
     */
    @Override
    public boolean equals(Object other) {
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
