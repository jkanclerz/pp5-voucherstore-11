package pl.jkanclerz.voucherstore.sales;

import org.junit.Before;
import org.junit.Test;
import pl.jkanclerz.voucherstore.productcatalog.ProductCatalogConfiguration;
import pl.jkanclerz.voucherstore.productcatalog.ProductCatalogFacade;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

public class SalesTest {

    ProductCatalogFacade productCatalog;
    private InMemoryBasketStorage basketStorage;
    private CurrentCustomerContext currentCustomerContext;
    private Inventory inventory;
    String customerId;

    @Before
    public void setUp() {
        productCatalog = new ProductCatalogConfiguration().productCatalogFacade();
        basketStorage = new InMemoryBasketStorage();
        inventory = (productId -> true);
        currentCustomerContext = () -> customerId;
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

    private void thereIsXproductsInCustemersBasket(int expectedProductsCount, String customerId) {
        Basket basket = basketStorage.loadForCustomer(customerId)
                .orElse(Basket.empty());

        assertThat(basket.getProductsCount()).isEqualTo(expectedProductsCount);
    }

    private String thereIsCustomerWhoIsDoingSomeShoping() {
        customerId = UUID.randomUUID().toString();
        return new String(customerId);
    }

    private String thereIsProductAvailable() {
        var id = productCatalog.createProduct();
        productCatalog.applyPrice(id, BigDecimal.valueOf(10));
        productCatalog.updateProductDetails(id, "desc", "http://image");
        return id;
    }

    private SalesFacade thereIsSalesModule() {
        return new SalesFacade(
                productCatalog,
                basketStorage,
                currentCustomerContext,
                inventory
        );
    }
}
