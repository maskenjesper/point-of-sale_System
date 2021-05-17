package se.kth.iv1350.pos.model;

public class Price {
    private double price;
    private double VAT;

    public  Price() {
        price = 0;
        VAT = 0;
    }

    public Price(double price, double VAT) {
        this.price = price;
        this.VAT = VAT;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setVAT(double VAT) {
        this.VAT = VAT;
    }

    public double getPrice() {
        return price;
    }

    public double getVAT() {
        return VAT;
    }
}
