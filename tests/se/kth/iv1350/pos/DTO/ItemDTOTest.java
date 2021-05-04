package se.kth.iv1350.pos.DTO;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemDTOTest {
    ItemDTO itemDTO;
    ItemDTO itemDTOSame;
    ItemDTO itemDTODifferent;

    @BeforeEach
    void setup() {
        itemDTO = new ItemDTO(1, 10, "d", "n", "c", 0.2);
        itemDTOSame = new ItemDTO(1, 10, "d", "n", "c", 0.2);
        itemDTODifferent = new ItemDTO(2, 10, "d", "n", "c", 0.2);
    }

    @AfterEach
    void tearDown() {
        itemDTO = null;
        itemDTOSame = null;
        itemDTODifferent = null;
    }

    @Test
    void equals() {
        boolean expectedResult = true;
        boolean actualResult = itemDTO.equals(itemDTO);
        assertEquals(expectedResult, actualResult, "equals evaluated equal ItemDTOs as not equal");
    }

    @Test
    void notEquals() {
        boolean expectedResult = false;
        boolean actualResult = itemDTO.equals(itemDTODifferent);
        assertEquals(expectedResult, actualResult, "equals evaluated not equal ItemDTOs as equal");
    }
}