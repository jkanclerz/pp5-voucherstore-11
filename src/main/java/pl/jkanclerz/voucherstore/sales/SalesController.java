package pl.jkanclerz.voucherstore.sales;

import org.springframework.web.bind.annotation.*;
import pl.jkanclerz.voucherstore.sales.offer.Offer;
import pl.jkanclerz.voucherstore.sales.payment.PaymentUpdateStatusRequest;

@RestController
public class SalesController {

    private final SalesFacade sales;

    public SalesController(SalesFacade salesFacade) {
        this.sales = salesFacade;
    }

    @GetMapping("/api/current-offer")
    public Offer currentOffer() {
        return sales.getCurrentOffer();
    }

    @PostMapping("/api/add-product/{productId}")
    public void addProductToBasket(@PathVariable String productId) {
        sales.addProduct(productId);
    }

    @PostMapping("/api/accept-offer")
    public void acceptOffer() {

    }

    @PostMapping("/api/payment/status")
    public void updatePaymentStatus(@RequestHeader("OpenPayu-Signature") String signatureHeader, @RequestBody String body) {
        PaymentUpdateStatusRequest paymentUpdateStatusRequest = PaymentUpdateStatusRequest.of(signatureHeader, body);

        sales.handlePaymentStatusChanged(paymentUpdateStatusRequest);
    }
}
