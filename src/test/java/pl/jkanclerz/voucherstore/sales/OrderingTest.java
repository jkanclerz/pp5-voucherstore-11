package pl.jkanclerz.voucherstore.sales;

import org.junit.Before;
import org.junit.Test;
import pl.jkanclerz.voucherstore.sales.offer.Offer;
import pl.jkanclerz.voucherstore.sales.ordering.ClientData;
import pl.jkanclerz.voucherstore.sales.payment.PaymentDetails;

import static org.assertj.core.api.Assertions.*;

public class OrderingTest extends SalesTestCase {
    @Before
    public void setUp() {
        productCatalog = thereIsProductCatalog();
        basketStorage = thereIsBasketStorage();
        inventory = therIsInventory();
        currentCustomerContext = thereIsCurrentCustomerContext();
        offerMaker = thereIsOfferMaker(productCatalog);
        paymentGateway = thereIsPaymentGateway();
    }

    @Test
    public void itCreatesReservationBasedOnCurrentOffer() {

        //Arrange
        SalesFacade salesFacade = thereIsSalesModule();
        var productId1 = thereIsProductAvailable();
        var productId2 = thereIsProductAvailable();

        //Act
        var customerId1 = thereIsCustomerWhoIsDoingSomeShoping();
        salesFacade.addProduct(productId1);

        salesFacade.addProduct(productId2);
        Offer seenOffer = salesFacade.getCurrentOffer();

        PaymentDetails paymentDetails = salesFacade.acceptOffer(seenOffer, clientProvideHisData());

        thereIsPendingReservationWithId(paymentDetails.getReservationId());
        thereIsPaymentRegisteredForReservation(paymentDetails.getReservationId());
        assertThat(paymentDetails.getPaymentUrl()).isNotNull();
    }

    private void thereIsPaymentRegisteredForReservation(String reservationId) {

    }

    private ClientData clientProvideHisData() {
        return new ClientData();
    }

    private void thereIsPendingReservationWithId(String reservationId) {
        assertThat(false).isFalse();
    }
}
