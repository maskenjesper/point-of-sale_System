package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.DTO.AddressDTO;

/**
 * Stores information about the store
 */
public class StoreInformation {
    private String storeName;
    private AddressDTO storeAddress;

    /**
     * Constructs an instance containing mockdata.
     */
    public StoreInformation() {
        storeName = "Jakobs liv's";
        storeAddress = new AddressDTO("Gatuvägen", "Bostadsstaden", "Sverige", 12345);
    }

    /**
     * Gets the stores name.
     * @return Store name as String
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     * Gets the stores address.
     * @return Store address as String
     */
    public AddressDTO getStoreAddress() {
        return storeAddress;
    }
}
