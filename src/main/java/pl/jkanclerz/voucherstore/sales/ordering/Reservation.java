package pl.jkanclerz.voucherstore.sales.ordering;

import pl.jkanclerz.voucherstore.sales.offer.Offer;

import java.util.UUID;

public class Reservation {

    String id;
    private ClientData clientData;
    boolean isPaid;

    public Reservation(String id, ClientData clientData) {
        this.id = id;
        this.clientData = clientData;
    }

    public static Reservation of(Offer currentOffer, ClientData clientData) {
        return new Reservation(
                UUID.randomUUID().toString(),
                clientData
        );
    }

    public String getId() {
        return id;
    }

    public boolean isPending() {
        return !isPaid;
    }

    public String getPaymentId() {
        return "";
    }

    public String getCustomerFirstname() {
        return clientData.getFirstname();
    }
}
