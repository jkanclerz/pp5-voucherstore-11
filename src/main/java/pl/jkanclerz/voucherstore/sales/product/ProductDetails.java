package pl.jkanclerz.voucherstore.sales.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class ProductDetails {
    private final String productId;
    private final String description;
    private final BigDecimal unitPrice;
}
