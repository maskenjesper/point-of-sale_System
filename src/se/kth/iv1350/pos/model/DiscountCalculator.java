package se.kth.iv1350.pos.model;

/**
 * An instance of this class is used to handle all logic related to calculating a discount for a sale.
 */
public class DiscountCalculator {

    /**
     * Calculates a discount based on discount rules, customer ID and sale information.
     * @param itemTable Necessary info about the sale.
     * @param customerID The customers ID.
     * @return New totalPrice for the sale whose itemTable is given as parameter.
     */
    public void calculateDiscount(PaymentInformation paymentInformation, int customerID) {
        if (customerID == 123)
            paymentInformation.setTotalPrice(paymentInformation.getTotalPrice() * 0.5); // Har just nu detta som placeholder. Borde jag göra någon bättre implementation?
    }                                                       // Ska VAT också förändras?
}                                                           // Borde det kanske ske någon kommunikation med Accounting för att kolla om kunden är berättigad?
                                                            // Borde det vara controller som anropar Accounting då?
