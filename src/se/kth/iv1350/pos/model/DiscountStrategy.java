package se.kth.iv1350.pos.model;

public interface DiscountStrategy {

    void calculate(PaymentInformation paymentInformation);
}
