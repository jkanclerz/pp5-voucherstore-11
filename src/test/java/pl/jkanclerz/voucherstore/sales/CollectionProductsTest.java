package pl.jkanclerz.voucherstore.sales;

import org.junit.Before;
import org.junit.Test;
import pl.jkanclerz.voucherstore.sales.basket.Basket;
import pl.jkanclerz.voucherstore.sales.offer.Offer;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class CollectionProductsTest extends SalesTestCase {

    @Before
    public void setUp() {
        productCatalog = thereIsProductCatalog();
        basketStorage = thereIsBasketStorage();
        inventory = therIsInventory();
        currentCustomerContext = thereIsCurrentCustomerContext();
        offerMaker = thereIsOfferMaker(productCatalog);
    }

    @Test
    public void itAllowAddProductToBasket() {

        //Arrange
        SalesFacade salesFacade = thereIsSalesModule();
        var productId1 = thereIsProductAvailable();
        var productId2 = thereIsProductAvailable();
        var customerId = thereIsCustomerWhoIsDoingSomeShoping();

        //Act
        salesFacade.addProduct(productId1);
        salesFacade.addProduct(productId2);

        //Assert
        thereIsXproductsInCustemersBasket(2, customerId);
    }

    @Test
    public void itAllowAddProductToBasketByMultipleCustomers() {

        //Arrange
        SalesFacade salesFacade = thereIsSalesModule();
        var productId1 = thereIsProductAvailable();
        var productId2 = thereIsProductAvailable();

        //Act
        var customerId1 = thereIsCustomerWhoIsDoingSomeShoping();
        salesFacade.addProduct(productId1);
        salesFacade.addProduct(productId2);
        var customerId2 = thereIsCustomerWhoIsDoingSomeShoping();
        salesFacade.addProduct(productId1);

        //Assert
        thereIsXproductsInCustemersBasket(2, customerId1);
        thereIsXproductsInCustemersBasket(1, customerId2);
    }

    @Test
    public void itGenerateOfferBasedOnSingleProduct() {
        //Arrange
        SalesFacade salesFacade = thereIsSalesModule();
        var productId1 = thereIsProductAvailable();

        //Act
        var customerId1 = thereIsCustomerWhoIsDoingSomeShoping();
        salesFacade.addProduct(productId1);

        Offer offer = salesFacade.getCurrentOffer();
        //Assert
        assertThat(offer.getTotal())
                .isEqualTo(BigDecimal.valueOf(10));
    }

    @Test
    public void itGenerateOfferBasedOnCollectedItems() {
        //Arrange
        SalesFacade salesFacade = thereIsSalesModule();
        var productId1 = thereIsProductAvailable();
        var productId2 = thereIsProductAvailable();

        //Act
        var customerId1 = thereIsCustomerWhoIsDoingSomeShoping();
        salesFacade.addProduct(productId1);
        salesFacade.addProduct(productId1);
        salesFacade.addProduct(productId2);
        Offer offer = salesFacade.getCurrentOffer();

        //Assert
        assertThat(offer.getTotal())
                .isEqualTo(BigDecimal.valueOf(30));
    }


    private void thereIsXproductsInCustemersBasket(int expectedProductsCount, String customerId) {
        Basket basket = basketStorage.loadForCustomer(customerId)
                .orElse(Basket.empty());

        assertThat(basket.getProductsCount()).isEqualTo(expectedProductsCount);
    }

}
