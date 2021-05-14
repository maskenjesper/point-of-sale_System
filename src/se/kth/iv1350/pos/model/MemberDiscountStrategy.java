package se.kth.iv1350.pos.model;

/**
 * A strategy for applying a discount on a sale made by a member
 */
public class MemberDiscountStrategy implements DiscountStrategy {
    private double discountRate = 0.5;

    /**
     * Applies discount
     * @param paymentInformation The object to modify
     */
    @Override
    public void calculate(PaymentInformation paymentInformation) {
        paymentInformation.setTotalPrice(paymentInformation.getTotalPrice() * discountRate);
        paymentInformation.setTotalVAT(paymentInformation.getTotalVAT() * discountRate);
    }
}
