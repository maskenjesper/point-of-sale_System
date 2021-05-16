package se.kth.iv1350.pos.model;

/**
 * A strategy for applying a discount on a sale made by a regular customer
 */
public class RegularDiscountStrategy implements DiscountStrategy {

    public RegularDiscountStrategy(PaymentInformation paymentInformation) {

    }

    /**
     * Applies discount
     */
    @Override
    public void calculate() {
        return;
    }
}
