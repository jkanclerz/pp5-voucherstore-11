package pl.jkanclerz.voucherstore.sales.offer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class OrderLine {
    private final String productId;
    private final String description;
    private final BigDecimal unitPrice;
    private final int quantity;
}
