package pl.jkanclerz.voucherstore.sales.offer;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class Offer {
    private final List<OrderLine> orderLines;
    private final BigDecimal total;
    private final Integer productsCount;

    public Offer(List<OrderLine> orderLines, BigDecimal total) {
        this.orderLines = orderLines;
        this.total = total;
        this.productsCount = orderLines.size();
    }

    public BigDecimal getTotal() {
        return total;
    }

    public List<OrderLine> getOfferLines() {
        return Collections.unmodifiableList(orderLines);
    }

    public boolean isEqual(Offer currentOffer) {
        return true;
    }

    public Integer getProductsCount() {
        return productsCount;
    }
}
