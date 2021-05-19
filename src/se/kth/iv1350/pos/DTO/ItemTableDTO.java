package se.kth.iv1350.pos.DTO;

import se.kth.iv1350.pos.model.ItemTable;

import java.util.ArrayList;
import java.util.List;

public class ItemTableDTO {
    private final List<ItemTableEntryDTO> table;
    private final double runningTotalIncludingVAT;

    public ItemTableDTO(ItemTable itemTable) {
        table = itemTable.getTable();
        runningTotalIncludingVAT = itemTable.getRunningTotalIncludingVAT();
    }

    /**
     * Gets the item type of the last entry in the table
     * @return ItemDTO of the last entry
     */
    public ItemDTO getLastItemInTable() {
        return table.get(table.size() - 1).getItemDTO();
    }

    public List<ItemTableEntryDTO> getTable() {
        List<ItemTableEntryDTO> tableCopy = new ArrayList<>();
        for (ItemTableEntryDTO entry : table)
            tableCopy.add(entry);
        return tableCopy;
    }

    public double getRunningTotalIncludingVAT() {
        return runningTotalIncludingVAT;
    }
}
