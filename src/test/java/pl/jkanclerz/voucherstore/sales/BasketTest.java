package pl.jkanclerz.voucherstore.sales;

import org.junit.Test;
import pl.jkanclerz.voucherstore.productcatalog.Product;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

public class BasketTest {

    public static final String PRODUCT_1 = "lego-8297";
    public static final String PRODUCT_2 = "lego-9999";

    @Test
    public void newlyCreateBasketIsEmpty() {
        //Arrange
        Basket basket = Basket.empty();
        //Act
        //Assert
        assertThat(basket.isEmpty())
                .isTrue();
    }

    @Test
    public void basketWithProductsIsNotEmpty() {
        //Arrange
        Basket basket = Basket.empty();
        Product product = thereIsProduct(PRODUCT_1);
        //Act
        basket.add(product);
        //Assert
        assertThat(basket.isEmpty())
                .isFalse();
    }

    @Test
    public void itShowsProductsCount() {
        //Arrange
        Basket basket = Basket.empty();
        Product product1 = thereIsProduct(PRODUCT_1);
        Product product2 = thereIsProduct(PRODUCT_2);
        //Act
        basket.add(product1);
        basket.add(product2);
        //Assert
        assertThat(basket.isEmpty())
                .isFalse();

        assertThat(basket.getProductsCount())
            .isEqualTo(2);
    }

    @Test
    public void itIncrementQuantityWhenAddedTwice() {
        //Arrange
        Basket basket = Basket.empty();
        Product product1 = thereIsProduct(PRODUCT_1);
        //Act
        basket.add(product1);
        basket.add(product1);

        assertThat(basket.getProductsCount())
                .isEqualTo(1);

        basketContainsProductWithQuantity(basket, product1, 2);
    }

    @Test
    public void itStoreQuantityForMultipleProducts() {
        //Arrange
        Basket basket = Basket.empty();
        Product product1 = thereIsProduct(PRODUCT_1);
        Product product2 = thereIsProduct(PRODUCT_2);
        //Act
        basket.add(product1);
        basket.add(product1);
        basket.add(product2);

        basketContainsProductWithQuantity(basket, product1, 2);
        basketContainsProductWithQuantity(basket, product2, 1);
    }

    private void basketContainsProductWithQuantity(Basket basket, Product product1, int expectedQuantity) {
        assertThat(basket.getBasketItems())
                .filteredOn(basketLine -> basketLine.getProductId().equals(product1.getId()))
                .extracting(BasketLine::getQuantity)
                .first()
                .isEqualTo(expectedQuantity);
    }


    private Product thereIsProduct(String description) {
        Product product = new Product(UUID.randomUUID());
        product.setDescription(description);
        return product;
    }
}
