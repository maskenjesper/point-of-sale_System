package se.kth.iv1350.pos.model;

import java.util.List;

public class DiscountFactory {
    private int memberID = 123;

    public DiscountStrategy createDiscountStrategy(int customerID) {
        if (customerID == memberID)
            return new MemberDiscountStrategy(); // Choose MemberDiscountStrategy
        else
            return new RegularDiscountStrategy(); // Choose RegularDiscountStrategy
    }
}
