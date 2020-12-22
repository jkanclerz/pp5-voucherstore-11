package pl.jkanclerz.voucherstore.sales.offer;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class Offer {
    private final List<OrderLine> orderLines;
    private final BigDecimal total;

    public Offer(List<OrderLine> orderLines, BigDecimal total) {
        this.orderLines = orderLines;
        this.total = total;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public List<OrderLine> getOfferLines() {
        return Collections.unmodifiableList(orderLines);
    }
}
