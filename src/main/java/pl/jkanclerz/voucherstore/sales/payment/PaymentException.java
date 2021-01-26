package pl.jkanclerz.voucherstore.sales.payment;

import pl.jkanclerz.payu.exceptions.PayUException;

public class PaymentException extends IllegalStateException {
    public PaymentException(PayUException e) {
        super(e);
    }
}
