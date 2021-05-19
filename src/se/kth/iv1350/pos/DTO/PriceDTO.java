package se.kth.iv1350.pos.DTO;

/**
 * Represents a Price as the total amount and the amount of it that is VAT
 */
public class PriceDTO {
    private final double price;
    private final double VAT;

    /**
     * Default constructor
     */
    public PriceDTO() {
        price = 0;
        VAT = 0;
    }

    /**
     * Constructor that sets attributes
     * @param price price attribute
     * @param VAT VAT attribute
     */
    public PriceDTO(double price, double VAT) {
        this.price = price;
        this.VAT = VAT;
    }

    /**
     * @return price attribute
     */
    public double getPrice() {
        return price;
    }

    /**
     * @return VAT attribute
     */
    public double getVAT() {
        return VAT;
    }
}
