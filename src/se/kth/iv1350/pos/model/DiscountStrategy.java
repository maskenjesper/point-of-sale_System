package se.kth.iv1350.pos.model;

/**
 * A strategy interface for different implementations of a discount strategy
 */
public interface DiscountStrategy {

    /**
     * Calculates the discount and applies it to the PaymentInformation object
     */
    Price calculate(PaymentInformation paymentInformation);
}