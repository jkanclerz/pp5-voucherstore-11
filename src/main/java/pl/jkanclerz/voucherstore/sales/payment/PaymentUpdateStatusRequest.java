package pl.jkanclerz.voucherstore.sales.payment;

import lombok.Getter;

@Getter
public class PaymentUpdateStatusRequest {
    private final String signature;
    private final String algorithm;
    private final String body;

    public PaymentUpdateStatusRequest(String signature, String algorithm, String body) {
        this.signature = signature;
        this.algorithm = algorithm;
        this.body = body;
    }

    public static PaymentUpdateStatusRequest of(String signatureHeader, String body) {
        return new PaymentUpdateStatusRequest(
                signatureHeader.split(";")[1].replace("signature=", ""),
                signatureHeader.split(";")[2].replace("algorithm=", ""),
                body
        );
    }
}
