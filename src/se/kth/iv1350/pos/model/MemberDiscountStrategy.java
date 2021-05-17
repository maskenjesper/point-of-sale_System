package se.kth.iv1350.pos.model;

/**
 * A strategy for applying a discount on a sale made by a member
 */
public class MemberDiscountStrategy implements DiscountStrategy {
    private double discountRate = 0.5;
    private PaymentInformation paymentInformation;

    public MemberDiscountStrategy(PaymentInformation paymentInformation) {
        this.paymentInformation = paymentInformation;
    }

    /**
     * Applies discount
     */
    @Override
    public Price calculate() {
        return new Price(paymentInformation.getTotalPrice().getPrice() * discountRate,
                paymentInformation.getTotalPrice().getVAT() * discountRate);
    }
}
