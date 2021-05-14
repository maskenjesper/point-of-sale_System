package se.kth.iv1350.pos.model;

public class MemberDiscountStrategy implements DiscountStrategy {
    private double discountRate = 0.5;

    @Override
    public void calculate(PaymentInformation paymentInformation) {
        paymentInformation.setTotalPrice(paymentInformation.getTotalPrice() * discountRate);
        paymentInformation.setTotalVAT(paymentInformation.getTotalVAT() * discountRate);
    }
}
