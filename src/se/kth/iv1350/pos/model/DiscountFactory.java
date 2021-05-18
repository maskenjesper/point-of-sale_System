package se.kth.iv1350.pos.model;

/**
 * Factory for instantiating DiscountStrategy objects based on customerID.
 */
public class DiscountFactory {
    private int memberID = 123;

    /**
     * Decides on discount strategy
     * @param customerID ID of customer that decides strategy
     * @return Strategy to use
     */
    public DiscountStrategy createDiscountStrategy(int customerID) {
        if (customerID == memberID)
            return new MemberDiscountStrategy();
        else
            return new RegularDiscountStrategy();
    }
}
