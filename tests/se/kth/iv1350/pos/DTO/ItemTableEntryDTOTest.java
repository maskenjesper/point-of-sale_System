package se.kth.iv1350.pos.DTO;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTableEntryDTOTest {

    //////////////////////////////
    //          SETUP           //
    //////////////////////////////
    private ItemTableEntryDTO itemTableEntryDTO;
    private ItemTableEntryDTO itemTableEntryDTOSame;
    private ItemTableEntryDTO itemTableEntryDTODifferent;

    private ItemDTO itemDTO;
    private ItemDTO itemDTOSame;
    private ItemDTO itemDTODifferent;

    @BeforeEach
    void setup() {
        itemDTO = new ItemDTO(1, 10, "d", "n", "c", 0.2);
        itemDTOSame = new ItemDTO(1, 10, "d", "n", "c", 0.2);
        itemDTODifferent = new ItemDTO(2, 10, "d", "n", "c", 0.2);

        itemTableEntryDTO = new ItemTableEntryDTO(itemDTO, 1);
        itemTableEntryDTOSame = new ItemTableEntryDTO(itemDTOSame, 1);
        itemTableEntryDTODifferent = new ItemTableEntryDTO(itemDTODifferent, 1);
    }

    @AfterEach
    void tearDown() {
        itemDTO = null;
        itemDTOSame = null;
        itemDTODifferent = null;

        itemTableEntryDTO = null;
        itemTableEntryDTOSame = null;
        itemTableEntryDTODifferent = null;
    }

    //////////////////////////////////
    //          equals()            //
    //////////////////////////////////
    @Test
    void equalsTestSame() {
        boolean expectedResult = true;
        boolean actualResult = itemTableEntryDTO.equals(itemTableEntryDTOSame);
        assertEquals(expectedResult, actualResult, "Equal ItemTableEntryDTO were evaluated not equal");
    }

    @Test
    void equalsTestDifferent() {
        boolean expectedResult = false;
        boolean actualResult = itemTableEntryDTO.equals(itemTableEntryDTODifferent);
        assertEquals(expectedResult, actualResult, "Not equal ItemTableEntryDTO were evaluated equal");
    }
}