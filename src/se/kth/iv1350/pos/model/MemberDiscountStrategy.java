package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.DTO.PriceDTO;

/**
 * A strategy for applying a discount on a sale made by a member
 */
public class MemberDiscountStrategy implements DiscountStrategy {
    private final double discountRate = 0.5;

    /**
     * @param price Information to base calculations on
     * @return The modified Price
     */
    @Override
    public PriceDTO calculate(PriceDTO price) {
        return new PriceDTO(price.getPrice() * discountRate,
                price.getVAT() * discountRate);
    }
}
