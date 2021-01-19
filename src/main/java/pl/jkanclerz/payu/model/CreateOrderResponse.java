package pl.jkanclerz.payu.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateOrderResponse {
    private String  redirectUri;
    private String  orderId;
    private String  extOrderId;
}
