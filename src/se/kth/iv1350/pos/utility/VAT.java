package se.kth.iv1350.pos.utility;

public class VAT {
    public static double convertPercentToCoefficient(double VATInPercent) {
        return VATInPercent / 100;
    }
}
