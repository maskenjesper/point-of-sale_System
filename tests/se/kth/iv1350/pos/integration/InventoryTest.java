package se.kth.iv1350.pos.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.DTO.ItemDTO;
import se.kth.iv1350.pos.DTO.SaleDTO;
import se.kth.iv1350.pos.model.Sale;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    //////////////////////////////
    //          SETUP           //
    //////////////////////////////
    private Inventory inventory;

    @BeforeEach
    void setUp() throws Exception {
        inventory = new Inventory();
    }

    @AfterEach
    void tearDown() throws Exception {
        inventory = null;
    }

    //////////////////////////////////////
    //          getItemInfo()           //
    //////////////////////////////////////
    @Test
    void getItemInfoTestID1() throws Exception {
        ItemDTO expectedResult = new ItemDTO(1, 25, "Naturens sportdryck", "Mjölk", "SEK", 0.2);
        ItemDTO actualResult = inventory.getItemInfo(1);
        assertEquals(expectedResult, actualResult, "Inventory retrieved wrong item");
    }

    @Test
    void getItemInfoTestID2() throws Exception {
        ItemDTO expectedResult = new ItemDTO(2, 20, "Nybakat surdegsbröd", "Levain", "SEK", 0.3);
        ItemDTO actualResult = inventory.getItemInfo(2);
        assertEquals(expectedResult, actualResult, "Inventory retrieved wrong item");
    }

    @Disabled
    @Test
    void getItemInfoTestInvalidID() throws Exception {
        ItemDTO expectedResult = null;
        ItemDTO actualResult = inventory.getItemInfo(3);
        assertEquals(expectedResult, actualResult, "Inventory item from invalid item identifier");
    }

    //////////////////////////////////////////
    //          updateRegistry()            //
    //////////////////////////////////////////
    @Test
    void updateRegistryTestCall() throws Exception {
        inventory.updateRegistry(new SaleDTO(new Sale()));
    }
}