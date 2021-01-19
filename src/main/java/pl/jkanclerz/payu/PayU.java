package pl.jkanclerz.payu;

public class PayU {
    private final PayUCredentials credentials;

    public PayU(PayUCredentials credentials) {

        this.credentials = credentials;
    }

    public CreateOrderResponse handle(OrderCreateRequest exampleOrderCreateRequest) {
        return new CreateOrderResponse();
    }
}
