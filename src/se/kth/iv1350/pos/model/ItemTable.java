package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.DTO.ItemDTO;
import se.kth.iv1350.pos.DTO.ItemTableEntryDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * An instance of this class is used to store a table of items and their corresponding quantities.
 */
public class ItemTable {
    List<ItemTableEntryDTO> table;
    double runningTotal = 0;

    public ItemTable() {
        table = new ArrayList<>();
    }

    /**
     * Adds a new entry to the table.
     * @param entry The entry to add.
     */
    void add(ItemTableEntryDTO entry) {
        if (tableContainsItemType(entry))
            increaseQuantity(indexOfItemType(entry), entry.getQuantity());
        else
            table.add(entry);
        runningTotal += entry.getQuantity() * entry.getItemDTO().getPrice();
    }

    private boolean tableContainsItemType(ItemTableEntryDTO entry) {
        for (ItemTableEntryDTO existingEntry : table)
            if (compareEntries(existingEntry, entry)) {
                return true;
            }
        return false;
    }

    private int indexOfItemType(ItemTableEntryDTO entry) {
        for (int i = 0; i < table.size(); i++)
            if (compareEntries(table.get(i), entry))
                return i;
        return -1;
    }

    private void increaseQuantity(int index, int quantity) {
        int newQuantity = table.get(index).getQuantity() + quantity;
        ItemDTO itemType = table.get(index).getItemDTO();
        table.set(index, new ItemTableEntryDTO(itemType, newQuantity));
    }

    private boolean compareEntries(ItemTableEntryDTO existingEntry, ItemTableEntryDTO newEntry) {
        return existingEntry.getItemDTO().getIdentifier() == newEntry.getItemDTO().getIdentifier();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        for (ItemTableEntryDTO entry:table)
            sb.append(entry.getItemDTO().toString() + "\n");
        return sb.toString();
    }
}
