package se.kth.iv1350.pos.model;

/**
 * Represents a Price as the total amount and the amount of it that is VAT
 */
public class Price {
    private double price;
    private double VAT;

    /**
     * Default constructor
     */
    public  Price() {
        price = 0;
        VAT = 0;
    }

    /**
     * Constructor that sets attributes
     * @param price price attribute
     * @param VAT VAT attribute
     */
    public Price(double price, double VAT) {
        this.price = price;
        this.VAT = VAT;
    }

    /**
     * Sets the price attribute
     * @param price new price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets the VAT attribute
     * @param VAT new VAT
     */
    public void setVAT(double VAT) {
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
