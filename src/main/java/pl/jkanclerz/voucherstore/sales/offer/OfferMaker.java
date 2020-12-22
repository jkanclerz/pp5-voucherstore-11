package pl.jkanclerz.voucherstore.sales.offer;

import pl.jkanclerz.voucherstore.sales.basket.BasketLine;
import pl.jkanclerz.voucherstore.sales.product.ProductDetails;
import pl.jkanclerz.voucherstore.sales.product.ProductDetailsProvider;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class OfferMaker {
    private final ProductDetailsProvider productDetailsProvider;

    public OfferMaker(ProductDetailsProvider productDetailsProvider) {
        this.productDetailsProvider = productDetailsProvider;
    }

    public Offer calculateOffer(List<BasketLine> basketItems) {
        List<OrderLine> orderLines = basketItems.stream()
                .map(this::createOrderLine)
                .collect(Collectors.toList());

        return new Offer(orderLines, calculateTotal(orderLines));
    }

    private OrderLine createOrderLine(BasketLine basketLine) {
        ProductDetails details = productDetailsProvider.getByProductId(basketLine.getProductId());

        return new OrderLine(
                basketLine.getProductId(),
                details.getDescription(),
                details.getUnitPrice(),
                basketLine.getQuantity()
        );
    }

    private BigDecimal calculateTotal(List<OrderLine> orderLines) {
        return orderLines.stream()
                .map(orderLine -> orderLine.getUnitPrice().multiply(BigDecimal.valueOf(orderLine.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
}
