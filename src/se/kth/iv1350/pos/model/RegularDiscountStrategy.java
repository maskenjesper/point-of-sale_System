package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.DTO.PriceDTO;

/**
 * A strategy for applying a discount on a sale made by a regular customer
 */
public class RegularDiscountStrategy implements DiscountStrategy {

    /**
     * @param paymentInformation Information to base calculations on
     * @return The modified Price
     */
    @Override
    public PriceDTO calculate(PriceDTO price) {
        return price;
    }
}
