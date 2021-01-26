package pl.jkanclerz.voucherstore.sales.ordering;

import pl.jkanclerz.voucherstore.sales.offer.Offer;
import pl.jkanclerz.voucherstore.sales.payment.PaymentDetails;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
public class Reservation {

    Reservation() {}

    @Id
    String id;

    @Embedded
    private ClientData clientData;

    @OneToMany(mappedBy = "reservation")
    private List<ReservationItem> items;

    private BigDecimal total;
    boolean isPaid;
    String paymentId;
    String paymentUrl;

    public Reservation(String id, ClientData clientData, List<ReservationItem> items, BigDecimal total) {
        this.id = id;
        this.clientData = clientData;
        this.items = items;
        this.total = total;
        items.forEach((item -> item.reservation = this));
    }

    public static Reservation of(Offer currentOffer, ClientData clientData) {
        List<ReservationItem> items = currentOffer.getOfferLines()
                .stream()
                .map(orderLine -> new ReservationItem(orderLine.getProductId(), orderLine.getDescription(), orderLine.getUnitPrice(), orderLine.getQuantity()))
                .collect(Collectors.toList());

        return new Reservation(
                UUID.randomUUID().toString(),
                clientData,
                items,
                currentOffer.getTotal()
        );
    }

    public String getId() {
        return id;
    }

    public boolean isPending() {
        return !isPaid;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public Integer getTotal() {
        return total.intValue()*100;
    }

    public String getCustomerFirstname() {
        return clientData.getFirstname();
    }

    public String getCustomerEmail() {
        return clientData.getEmail();
    }

    public String getCustomerLastname() {
        return clientData.getLastname();
    }

    public List<ReservationItem> getItems() {
        return Collections.emptyList();
    }

    public void fillWithPayment(PaymentDetails paymentData) {
        paymentUrl = paymentData.getPaymentUrl();
        paymentId = paymentData.getPaymentId();
    }
}
