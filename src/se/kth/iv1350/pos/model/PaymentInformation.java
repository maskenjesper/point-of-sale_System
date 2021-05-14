package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.DTO.ItemTableEntryDTO;

/**
 * Stores the payment related data for a sale and contains methods related to these.
 */
public class PaymentInformation {
    private double totalPrice;
    private double totalVAT;
    private double amountPaid;
    private double change;
    private DiscountStrategy discountStrategy;

    /**
     * Constructs initial payment info
     */
    public PaymentInformation() {
        totalPrice = 0;
        totalVAT = 0;
        amountPaid = 0;
        change = 0;
    }

    /**
     * Calculates the totalPrice and totalVAT attributes based on the itemTable.
     * @param itemTable contains the information that the calculations are based on.
     */
    void calculatePrice(ItemTable itemTable) {
        totalPrice = itemTable.getRunningTotalIncludingVAT();
        calculateVAT(itemTable);
    }

    /**
     * Alters total price if customer is eligible for discount
     * @param customerID ID of the customer.
     */
    void calculateDiscount(int customerID) {
        int memberID = 123;
        if (customerID == memberID)
            discountStrategy = new MemberDiscountStrategy(); // Choose MemberDiscountStrategy
        else
            discountStrategy = new RegularDiscountStrategy(); // Choose RegularDiscountStrategy
        discountStrategy.calculate(this);
    }
    // Det känns som att denna implementation av strategy blir ganska dålig. Vilken algoritm som ska appliceras ska bero
    // på customerID men då måste jag ha en if-sats som väljer algoritmen (eller stratergin snarare) som ska användas.
    // Är inte själva meningen med strategy att eliminera if-satser dock?
    //
    // Det känns också som att DiscountStrategy implementationerna inte kan göra så mycket eftersom dem inte har tillgång
    // attributen som dem ska redigera. Skulle behöva lägga till setters hos PaymentInformation för att dem ska kunna
    // utföra sin uppgift men detta känns inte så bra.
    //
    // Detta hade ju kunnats implementeras mycket snyggare genom att bara ha två olika privata metoder i denna klass.

    /**
     * Registers amount paid and calculates discount.
     * @param amountPaid The amount paid by the customer
     */
    void calculatePayment(double amountPaid) {
        this.amountPaid = amountPaid;
        change = amountPaid - totalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getTotalVAT() {
        return totalVAT;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public double getChange() {
        return change;
    }

    public void setTotalPrice(double newTotalPrice) { // Needed for discount calculation
        totalPrice = newTotalPrice;
    }

    public void setTotalVAT(double newTotalVAT) { // Needed for discount calculation
        totalVAT = newTotalVAT;
    }

    private void calculateVAT(ItemTable itemTable) {
        for (ItemTableEntryDTO entry : itemTable.getTable())
            totalVAT += entry.getItemDTO().getPrice() * entry.getQuantity() * entry.getItemDTO().getVATRate();
    }
}
