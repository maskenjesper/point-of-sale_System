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
    public void calculate() {
        paymentInformation.setTotalPrice(paymentInformation.getTotalPrice() * discountRate);
        paymentInformation.setTotalVAT(paymentInformation.getTotalVAT() * discountRate);
    }
}
