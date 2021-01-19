package pl.jkanclerz.payu;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateOrderResponse {
    private String  redirectUri;
    private String  orderId;
    private String  extOrderId;
}
