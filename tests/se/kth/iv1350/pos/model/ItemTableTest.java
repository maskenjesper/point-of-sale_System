package se.kth.iv1350.pos.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.DTO.ItemDTO;
import se.kth.iv1350.pos.DTO.ItemTableEntryDTO;

import static org.junit.jupiter.api.Assertions.*;

class ItemTableTest {

    //////////////////////////////////////
    //              SETUP               //
    //////////////////////////////////////
    private ItemTable itemTable;
    private ItemDTO itemDTO;
    private ItemDTO itemDTOOther;
    private ItemTableEntryDTO itemTableEntryDTO;
    private ItemTableEntryDTO itemTableEntryDTOOther;

    @BeforeEach
    void setUp() {
        itemTable = new ItemTable();
        itemDTO = new ItemDTO(1, 100, "", "", "", 0.2);
        itemTableEntryDTO = new ItemTableEntryDTO(itemDTO, 1);
        itemDTOOther = new ItemDTO(2, 10, "2", "2", "2", 0.2);
        itemTableEntryDTOOther = new ItemTableEntryDTO(itemDTOOther, 2);
    }

    @AfterEach
    void tearDown() {
        itemTable = null;
        itemDTO = null;
        itemDTOOther = null;
        itemTableEntryDTO = null;
        itemTableEntryDTOOther = null;
    }

    ////////////////////////////
    //          add()         //
    ////////////////////////////
    @Test
    void addToEmptyItemTableCheckTable() {
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
    void addToNotEmptyItemTableCheckRunningTotalIncludingVAT() {
        itemTable.add(itemTableEntryDTO);
        itemTable.add(itemTableEntryDTOOther);
        double expectedResult = 144;
        double actualResult = itemTable.getRunningTotalIncludingVAT();
        assertEquals(expectedResult, actualResult, "RunningTotalIncludingVAT was not updated correctly");
    }

    @Test
    void addWithMatchingItemDTOCheckRunningTotalIncludingVAT() {
        itemTable.add(itemTableEntryDTO);
        itemTable.add(itemTableEntryDTO);
        double expectedResult = 240;
        double actualResult = itemTable.getRunningTotalIncludingVAT();
        assertEquals(expectedResult, actualResult, "RunningTotalIncludingVAT was not updated correctly");
    }

    @Test
    void addWithMatchingItemDTOCheckQuantity() {
        itemTable.add(itemTableEntryDTO);
        itemTable.add(itemTableEntryDTO);
        double expectedResult = 2;
        double actualResult = itemTable.getTable().get(0).getQuantity();
        assertEquals(expectedResult, actualResult, "Quantity was not updated correctly");
    }

    @Test
    void addCheckLastEntryOfListAfterQuantityUpdate() {
        itemTable.add(itemTableEntryDTO);
        itemTable.add(itemTableEntryDTOOther);
        itemTable.add(itemTableEntryDTO);
        ItemDTO expectedResult = itemTableEntryDTO.getItemDTO();
        ItemDTO actualResult = itemTable.getItemTableDTO().getLastItemInTable();
        assertEquals(expectedResult, actualResult, "Entry was not moved to last place in table after having it's quantity increased");
    }

    //////////////////////////////////////////////
    //          getLastItemInTable()            //
    //////////////////////////////////////////////
    @Test
    void getLastItemInTable() {
        itemTable.add(itemTableEntryDTO);
        itemTable.add(itemTableEntryDTOOther);
        ItemDTO expectedResult = itemTableEntryDTOOther.getItemDTO();
        ItemDTO actualResult = itemTable.getItemTableDTO().getLastItemInTable();
        assertEquals(expectedResult, actualResult, "Didn't get last ItemDTO in the table");
    }
}