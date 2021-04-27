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
     * Constructs an ItemDTO by filling it's attributes.
     * @param identifier
     * @param price
     * @param description
     * @param name
     * @param currency
     * @param VATRate
     */
    public ItemDTO(int identifier, double price, String description, String name, String currency, double VATRate) {
        this.identifier = identifier;
        this.price = price;
        this.description = description;
        this.name = name;
        this.currency = currency;
        this.VATRate = VATRate;
    }

    public int getIdentifier() {
        return identifier;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getCurrency() {
        return currency;
    }

    public double getVATRate() {
        return VATRate;
    }
}
