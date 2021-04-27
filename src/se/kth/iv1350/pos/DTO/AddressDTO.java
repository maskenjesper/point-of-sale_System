package se.kth.iv1350.pos.DTO;

/**
 * DTO that bundles the data representing an address.
 */
public class AddressDTO {
    private String street;
    private String city;
    private String country;
    private int ZIP;

    public AddressDTO(String street, String city, String country, int ZIP) {
        this.street = street;
        this.city = city;
        this.country = country;
        this.ZIP = ZIP;
    }

    @Override
    public String toString() {
        return street + ", " + city + ", " + country + ", " + ZIP;
    }
}
