package se.kth.iv1350.pos.DTO;

import se.kth.iv1350.pos.model.ItemTable;

/**
 * DTO that bundles the data representing an item.
 */
public class ItemDTO { // Insåg att denna klass kan brytas upp i flera klasser. Borde jag göra detta?
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
    public ItemDTO(int identifier, double price, String description, String name, String currency, double VATRate) { // Jag fick en ide om att eliminera denna långa parameterlista genom
        this.identifier = identifier;                                                                                // att istället bara ha identifier som parameter och beroende på denna
        this.price = price;                                                                                          // så instansieras objektet på ett specifikt sätt. Är det en bra ide?
        this.description = description;
        this.name = name;
        this.currency = currency;
        this.VATRate = VATRate;
    }

    /**
     * Checks if two itemDTOs have identical attributes.
     * @param other Object to check equality against.
     * @return True if equal; false if not
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ItemDTO))
            return false;
        ItemDTO otherItemDTO = (ItemDTO)other;
        return identifier == otherItemDTO.identifier &&
                price == otherItemDTO.price &&
                description.equals(otherItemDTO.description) &&
                name.equals(otherItemDTO.name) &&
                currency.equals(otherItemDTO.currency) &&
                VATRate == otherItemDTO.VATRate;
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
