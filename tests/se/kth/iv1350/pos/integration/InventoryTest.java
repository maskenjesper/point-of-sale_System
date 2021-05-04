package se.kth.iv1350.pos.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.DTO.ItemDTO;
import se.kth.iv1350.pos.DTO.SaleDTO;
import se.kth.iv1350.pos.model.Sale;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    //////////////////////////////
    //          SETUP           //
    //////////////////////////////
    Inventory inventory;
    @BeforeEach
    void setUp() {
        inventory = new Inventory();
    }

    @AfterEach
    void tearDown() {
        inventory = null;
    }

    //////////////////////////////////////
    //          getItemInfo()           //
    //////////////////////////////////////
    @Test
    void getItemInfo() {
        ItemDTO expectedResult = new ItemDTO(1, 25, "Naturens sportdryck", "Mjölk", "SEK", 0.2);
        ItemDTO actualResult = inventory.getItemInfo(1);
        assertEquals(expectedResult, actualResult, "Inventory retrieved wrong item");
    }

    @Test
    void getItemInfoInvalidID() {
        ItemDTO expectedResult = null;
        ItemDTO actualResult = inventory.getItemInfo(3);
        assertEquals(expectedResult, actualResult, "Inventory item from invalid item identifier");
    }

    //////////////////////////////////////////
    //          updateRegistry()            //
    //////////////////////////////////////////
    @Test
    void callUpdateRegistry() {
        inventory.updateRegistry(new SaleDTO(new Sale()));
    }
}