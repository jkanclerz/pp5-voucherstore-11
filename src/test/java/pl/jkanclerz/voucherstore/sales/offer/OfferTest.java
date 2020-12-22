package pl.jkanclerz.voucherstore.sales.offer;

import org.junit.Test;
import pl.jkanclerz.voucherstore.sales.basket.BasketLine;
import pl.jkanclerz.voucherstore.sales.product.ProductDetails;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class OfferTest {
    @Test
    public void itCalculateOfferBasedOnSingleBasketItem() {
        List<BasketLine> basketItems = Collections.singletonList(
                new BasketLine("prod2", 1)
        );

        OfferMaker offerMaker = thereIsOfferMaker();
        Offer offer = offerMaker.calculateOffer(basketItems);

        assertThat(offer.getTotal())
                .isEqualTo(BigDecimal.valueOf(10));

        assertThat(offer.getOfferLines())
                .hasSize(1);
    }

    @Test
    public void itCalculateOfferBasedOnBasketItems() {
        List<BasketLine> basketItems = Arrays.asList(
                new BasketLine("prod1", 2),
                new BasketLine("prod2", 1)
        );

        OfferMaker offerMaker = thereIsOfferMaker();
        Offer offer = offerMaker.calculateOffer(basketItems);

        assertThat(offer.getTotal())
                .isEqualTo(BigDecimal.valueOf(30));

        assertThat(offer.getOfferLines())
                .hasSize(2);
    }

    private OfferMaker thereIsOfferMaker() {
        return new OfferMaker(productId -> new ProductDetails(
                productId,
                String.format("%s-desc", productId),
                BigDecimal.valueOf(10)
        ));
    }
}
