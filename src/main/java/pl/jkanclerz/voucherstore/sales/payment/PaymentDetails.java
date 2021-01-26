package pl.jkanclerz.voucherstore.sales.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
public class PaymentDetails {
    String reservationId;
    String paymentUrl;
    String paymentId;
}
