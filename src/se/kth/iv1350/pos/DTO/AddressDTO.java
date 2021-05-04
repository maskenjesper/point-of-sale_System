package se.kth.iv1350.pos.DTO;

/**
 * DTO that bundles the data representing an address.
 */
public class AddressDTO {
    private String street;
    private String city;
    private String country;
    private int ZIP;

    /**
     * Constructs an <code>AddressDTO</code> by copying the parameters to corresponding attributes.
     * @param street Street for the address.
     * @param city City of the address.
     * @param country Country of the address.
     * @param ZIP ZIP of the address.
     */
    public AddressDTO(String street, String city, String country, int ZIP) {
        this.street = street;
        this.city = city;
        this.country = country;
        this.ZIP = ZIP;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public int getZIP() {
        return ZIP;
    }
}
