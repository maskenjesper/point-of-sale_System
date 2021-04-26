package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.DTO.ItemTableEntryDTO;

import java.util.List;

/**
 * An instance of this class is used to store a table of items and their corresponding quantities.
 */
public class ItemTable {
    List<ItemTableEntryDTO> table;
    double runningTotal = 0;

    /**
     * Adds a new entry to the table.
     * @param entry The entry to add.
     */
    void add(ItemTableEntryDTO entry) {
        table.add(entry);
        // TODO:    Logic for same item being added multiple times.
        //          Calculating runningTotal.
    }
}
