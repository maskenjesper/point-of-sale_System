package se.kth.iv1350.pos.model;

/**
 * A strategy for applying a discount on a sale made by a regular customer
 */
public class RegularDiscountStrategy implements DiscountStrategy {
    private PaymentInformation paymentInformation;

    public RegularDiscountStrategy(PaymentInformation paymentInformation) {
        this.paymentInformation = paymentInformation;
    }

    /**
     * Applies discount
     */
    @Override
    public Price calculate() {
        return paymentInformation.getTotalPrice();
    }
}
