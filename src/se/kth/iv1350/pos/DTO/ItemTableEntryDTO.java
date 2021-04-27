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

    public ItemDTO getItemDTO() {
        return itemDTO;
    }

    public int getQuantity() {
        return quantity;
    }
}
