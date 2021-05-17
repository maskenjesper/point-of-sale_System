package se.kth.iv1350.pos.model;

/**
 * A strategy for applying a discount on a sale made by a member
 */
public class MemberDiscountStrategy implements DiscountStrategy {
    private double discountRate = 0.5;

    /**
     * @param paymentInformation Information to base calculations on
     * @return The modified Price
     */
    @Override
    public Price calculate(PaymentInformation paymentInformation) {
        return new Price(paymentInformation.getTotalPrice().getPrice() * discountRate,
                paymentInformation.getTotalPrice().getVAT() * discountRate);
    }
}
