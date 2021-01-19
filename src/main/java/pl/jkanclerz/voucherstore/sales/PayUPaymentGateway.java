package pl.jkanclerz.voucherstore.sales;

import pl.jkanclerz.payu.PayU;

public class PayUPaymentGateway implements PaymentGateway {
    private final PayU payU;
    public PayUPaymentGateway(PayU payU) {

        this.payU = payU;
    }

    @Override
    public ReservationPaymentDetails register(Reservation reservation) {
        return null;
    }
}
