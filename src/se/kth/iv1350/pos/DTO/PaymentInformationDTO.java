package se.kth.iv1350.pos.DTO;

import se.kth.iv1350.pos.model.PaymentInformation;

public class PaymentInformationDTO {
    private final PriceDTO totalPrice;
    private final double amountPaid;
    private final double change;

    public PaymentInformationDTO(PaymentInformation paymentInformation) {
        totalPrice = paymentInformation.getTotalPrice();
        amountPaid = paymentInformation.getAmountPaid();
        change = paymentInformation.getChange();
    }

    public PriceDTO getTotalPrice() {
        return totalPrice;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public double getChange() {
        return change;
    }
}
