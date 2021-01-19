package pl.jkanclerz.voucherstore.sales;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class ReservationPaymentDetails {
    String reservationId;
    String paymentUrl;
}
