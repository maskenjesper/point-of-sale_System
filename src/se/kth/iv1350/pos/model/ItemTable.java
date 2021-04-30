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
    private double runningTotal; // Är det dumt att ha runningTotal här? Borde jag bara använda mig av totalPrice i paymentInformation?

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
    void add(ItemTableEntryDTO newEntry) {
        int index = searchForIdentifierMatch(newEntry);
        if (index != -1)
            increaseQuantityOfEntryAt(index, newEntry.getQuantity());
        else
            table.add(newEntry);
        addEntryPriceToRunningTotal(newEntry);
    }

    /**
     * Creates a string representation of <code>this</code>.
     * @return String representation of <code>this</code>.
     */
    @Override
    public String toString() { // TODO: Kanske flyttar detta till View
        StringBuilder sb = new StringBuilder("");
        for (ItemTableEntryDTO entry:table)
            sb.append("| " + entry.getQuantity() + "st " + entry.getItemDTO().toString() + "\n");
        return sb.toString();
    }

    public ItemDTO getLastItemInTable() {
        return table.get(table.size() - 1).getItemDTO();
    }

    public List<ItemTableEntryDTO> getTable() {
        return table;
    }

    public double getRunningTotal() {
        return runningTotal;
    }

    private int searchForIdentifierMatch(ItemTableEntryDTO entry) {
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

    private void addEntryPriceToRunningTotal(ItemTableEntryDTO newEntry) {
        runningTotal += newEntry.getQuantity() * newEntry.getItemDTO().getPrice() *
                ((newEntry.getItemDTO().getVATRate() /100) + 1);
    }
}
