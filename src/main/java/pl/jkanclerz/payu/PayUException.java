package pl.jkanclerz.payu;

public class PayUException extends Exception {
    public PayUException(Exception e) {
        super(e);
    }
}
