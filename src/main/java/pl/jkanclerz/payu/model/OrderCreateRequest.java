package pl.jkanclerz.payu.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderCreateRequest {
    private String notifyUrl;
    private String extOrderId;
    private String customerIp;
    private String merchantPosId;
    private String description;
    private String currencyCode;
    private Integer totalAmount;
    private String continueUrl;
    private Buyer buyer;
    private List<Product> products;
    /*
    {
        "notifyUrl": "https://your.eshop.com/notify",
        "customerIp": "127.0.0.1",
        "merchantPosId": "145227",
        "description": "RTV market",
        "currencyCode": "PLN",
        "totalAmount": "21000",
        "buyer": {
            "email": "john.doe@example.com",
            "phone": "654111654",
            "firstName": "John",
            "lastName": "Doe",
            "language": "pl"
        },
        "products": [
            {
                "name": "Wireless Mouse for Laptop",
                "unitPrice": "15000",
                "quantity": "1"
            },
            {
                "name": "HDMI cable",
                "unitPrice": "6000",
                "quantity": "1"
            }
        ]
    }
     */
}
