package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.DTO.PriceDTO;

/**
 * A strategy interface for different implementations of a discount strategy
 */
public interface DiscountStrategy {

    /**
     * Calculates the discount and applies it to the PaymentInformation object
     */
    PriceDTO calculate(PaymentInformation paymentInformation);
}
