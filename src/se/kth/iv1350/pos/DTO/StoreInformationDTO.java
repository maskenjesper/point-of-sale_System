package se.kth.iv1350.pos.DTO;

/**
 * Stores information about the store
 */
public class StoreInformationDTO {
    private final String storeName;
    private final AddressDTO storeAddress;

    /**
     * Constructs an instance containing mockdata.
     */
    public StoreInformationDTO() {
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
