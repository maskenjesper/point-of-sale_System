package se.kth.iv1350.pos.model;

/**
 * A strategy for applying a discount on a sale made by a regular customer
 */
public class RegularDiscountStrategy implements DiscountStrategy {
    private PaymentInformation paymentInformation;

    /**
     * @param paymentInformation Information to base calculations on
     * @return The modified Price
     */
    @Override
    public Price calculate(PaymentInformation paymentInformation) {
        return paymentInformation.getTotalPrice();
    }
}
