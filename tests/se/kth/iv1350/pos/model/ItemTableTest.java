package se.kth.iv1350.pos.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.DTO.ItemDTO;
import se.kth.iv1350.pos.DTO.ItemTableEntryDTO;
import se.kth.iv1350.pos.integration.Inventory;

import static org.junit.jupiter.api.Assertions.*;

class ItemTableTest { // Måste man ha test för toString?
    ItemTable itemTable;
    ItemDTO itemDTO;
    ItemTableEntryDTO itemTableEntryDTO;


    @BeforeEach
    void setUp() {
        itemTable = new ItemTable();
        itemDTO = new ItemDTO(1, 100, "", "", "", 0.2);
        itemTableEntryDTO = new ItemTableEntryDTO(itemDTO, 1);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addToEmptyItemTableCheckTable() { // Verkar inte som min equals() fungerar
        itemTable.add(itemTableEntryDTO);
        ItemTableEntryDTO expectedResult = itemTableEntryDTO;
        ItemTableEntryDTO actualResult = itemTable.getTable().get(0);
        assertEquals(expectedResult, actualResult, "Table was not updated correctly");
    }

    @Test
    void addToEmptyItemTableCheckRunningTotalIncludingVAT() {
        itemTable.add(itemTableEntryDTO);
        double expectedResult = 120;
        double actualResult = itemTable.getRunningTotalIncludingVAT();
        assertEquals(expectedResult, actualResult, "RunningTotalIncludingVAT was not updated correctly");
    }

    @Test
    void addWithMatchingItemDTO() {

    }

    @Test
    void getLastItemInTable() {

    }
}