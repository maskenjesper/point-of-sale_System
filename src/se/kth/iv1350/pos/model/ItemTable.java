package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.DTO.ItemDTO;
import se.kth.iv1350.pos.DTO.ItemTableEntryDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * An instance of this class is used to store a table of items and their corresponding quantities.
 */
public class ItemTable {
    private List<ItemTableEntryDTO> table;
    private double runningTotal;

    /**
     * Creates an object and initializes the <code>table</code> attribute.
     */
    public ItemTable() {
        table = new ArrayList<>();
        runningTotal = 0;
    }

    /**
     * Adds a new entry to the table.
     * @param newEntry The entry to add.
     */
    public void add(ItemTableEntryDTO newEntry) {
        if (tableContainsItemDTOOfEntry(newEntry))  // Skulle jag kunna göra så att tableContainsItemDTOOfEntry får
                                                    // returnera vilket index den hittar match på och returnera -1 om
                                                    // inget hittas?
            increaseQuantityOfEntryAt(indexOfItemDTO(newEntry), newEntry.getQuantity());
        else
            table.add(newEntry);
        runningTotal += newEntry.getQuantity() * newEntry.getItemDTO().getPrice() * ((newEntry.getItemDTO().getVATRate() /100) + 1);
    }

    /**
     * Creates a string representation of <code>this</code>.
     * @return String representation of <code>this</code>.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        for (ItemTableEntryDTO entry:table)
            sb.append("| " + entry.getQuantity() + "st " + entry.getItemDTO().toString() + "\n");
        return sb.toString();
    }

    public List<ItemTableEntryDTO> getTable() {
        return table;
    }

    public double getRunningTotal() {
        return runningTotal;
    }

    public ItemDTO getLastItemInTable() {
        return table.get(table.size() - 1).getItemDTO();
    }

    private boolean tableContainsItemDTOOfEntry(ItemTableEntryDTO entry) {
        for (ItemTableEntryDTO existingEntry : table)
            if (compareEntries(existingEntry, entry)) {
                return true;
            }
        return false;
    }

    private int indexOfItemDTO(ItemTableEntryDTO entry) {
        for (int i = 0; i < table.size(); i++)
            if (compareEntries(table.get(i), entry))
                return i;
        return -1;
    }

    private void increaseQuantityOfEntryAt(int index, int quantity) {
        int newQuantity = table.get(index).getQuantity() + quantity;
        ItemDTO itemType = table.get(index).getItemDTO();
        table.set(index, new ItemTableEntryDTO(itemType, newQuantity));
    }

    private boolean compareEntries(ItemTableEntryDTO existingEntry, ItemTableEntryDTO newEntry) {
        return existingEntry.getItemDTO().getIdentifier() == newEntry.getItemDTO().getIdentifier();
    }
}
