package se.kth.iv1350.pos.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.DTO.ItemDTO;
import se.kth.iv1350.pos.DTO.SaleDTO;
import se.kth.iv1350.pos.model.Sale;

import java.lang.Exception;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    //////////////////////////////
    //          SETUP           //
    //////////////////////////////
    private Inventory inventory;

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
    void getItemInfoTestID1() {
        try {
            ItemDTO expectedResult = new ItemDTO(1, 25, "Naturens sportdryck", "Mjölk", "SEK", 0.2);
            ItemDTO actualResult = inventory.getItemInfo(1);
            assertEquals(expectedResult, actualResult, "Inventory retrieved wrong item");
        } catch (Exception e) {
            fail("Exception thrown when valid identifier was sent");
        }
    }

    @Test
    void getItemInfoTestID2() {
        try {
            ItemDTO expectedResult = new ItemDTO(2, 20, "Nybakat surdegsbröd", "Levain", "SEK", 0.3);
            ItemDTO actualResult = inventory.getItemInfo(2);
            assertEquals(expectedResult, actualResult, "Inventory retrieved wrong item");
        } catch (Exception e) {
            fail("Exception thrown when valid identifier was sent");
        }
    }

    @Test
    void getItemInfoTestInvalidID() {
        try {
            inventory.getItemInfo(3);
            fail("Exception was not thrown when invalid identifier was sent");
        } catch (Exception e) {
            assertTrue(e instanceof InvalidItemIdentifierException);
        }
    }

    @Test
    void getItemInfoTestServerNotRunning() {
        try {
            inventory.getItemInfo(500);
            fail("Exception was not thrown when the database server wasn't running");
        } catch (Exception e) {
            assertTrue(e instanceof DatabaseServerNotRunningException);
        }
    }

    //////////////////////////////////////////
    //          updateRegistry()            //
    //////////////////////////////////////////
    @Test
    void updateRegistryTestCall() {
        inventory.updateRegistry(new SaleDTO(new Sale()));
    }
}