package pl.jkanclerz.voucherstore.sales.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class PaymentDetails {
    String reservationId;
    String paymentUrl;
}
