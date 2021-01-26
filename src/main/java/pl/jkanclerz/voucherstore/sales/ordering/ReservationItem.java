package pl.jkanclerz.voucherstore.sales.ordering;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
public class ReservationItem {
    @Id
    Integer id;
    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    private String productId;
    private String description;
    private BigDecimal unitPrice;
    private int quantity;

    public ReservationItem(String productId, String description, BigDecimal unitPrice, int quantity) {
        this.productId = productId;
        this.description = description;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public String getName() {
        return description;
    }

    public Integer getUnitPrice() {
        return unitPrice.intValue()*100;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
