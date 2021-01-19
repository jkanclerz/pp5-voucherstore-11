package pl.jkanclerz.payu.exceptions;

public class PayUException extends Exception {
    public PayUException(Exception e) {
        super(e);
    }
}
