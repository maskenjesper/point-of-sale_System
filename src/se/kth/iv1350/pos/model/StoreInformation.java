package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.DTO.AddressDTO;

public class StoreInformation {
    private String storeName;
    private AddressDTO storeAddress;

    public StoreInformation() {
        storeName = "Jakobs liv's";
        storeAddress = new AddressDTO("Gatuvägen", "Bostadsstaden", "Sverige", 12345);
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setStoreAddress(AddressDTO storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getStoreName() {
        return storeName;
    }

    public AddressDTO getStoreAddress() {
        return storeAddress;
    }
}
