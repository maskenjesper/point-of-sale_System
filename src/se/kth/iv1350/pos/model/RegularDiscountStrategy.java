package se.kth.iv1350.pos.model;

/**
 * A strategy for applying a discount on a sale made by a regular customer
 */
public class RegularDiscountStrategy implements DiscountStrategy {

    /**
     * Applies discount
     * @param paymentInformation The object to modify
     */
    @Override
    public void calculate(PaymentInformation paymentInformation) {
        return;
    }
}
