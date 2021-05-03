package se.kth.iv1350.pos.DTO;

import se.kth.iv1350.pos.model.ItemTable;

/**
 * DTO that bundles the data representing an item.
 */
public class ItemDTO {
    private int identifier;
    private double price;
    private String description;
    private String name;
    private String currency;
    private double VATRate;

    /**
     * Constructs an ItemDTO by filling it's attributes with the corresponding parameters.
     * @param identifier ID of the item.
     * @param price Price of the item in <code>currency</code>.
     * @param description Description of the item.
     * @param name Name of the item.
     * @param currency Currency of the price.
     * @param VATRate VAT-rate for the item in %.
     */
    public ItemDTO(int identifier, double price, String description, String name, String currency, double VATRate) {
        this.identifier = identifier;
        this.price = price;
        this.description = description;
        this.name = name;
        this.currency = currency;
        this.VATRate = VATRate;
    }

    @Override
    public boolean equals(Object o) {
        ItemDTO itemDTO;
        if (o instanceof ItemTableEntryDTO)
            itemDTO = (ItemDTO)o;
        else
            return false;
        return identifier == itemDTO.identifier &&
                price == itemDTO.price &&
                description.equals(itemDTO.description) &&
                name.equals(itemDTO.name) &&
                currency.equals(itemDTO.currency) &&
                VATRate == itemDTO.VATRate;
    }

    /**
     * Creates a string representation of <code>this</code>.
     * @return String representation of <code>this</code>.
     */
    @Override
    public String toString() { // TODO: Kanske flyttar detta till View
        return "| " + price + currency + " (VAT: " + VATRate + "%) | " + name + ": " + description + " | ID: " + identifier + " |";
    }

    /**
     * @return identifier attribute.
     */
    public int getIdentifier() {
        return identifier;
    }

    /**
     * @return price attribute.
     */
    public double getPrice() {
        return price;
    }

    /**
     * @return description attribute.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return name attribute.
     */
    public String getName() {
        return name;
    }

    /**
     * @return currency attribute.
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @return VARRate attribute.
     */
    public double getVATRate() {
        return VATRate;
    }
}
