package pl.jkanclerz.voucherstore.productcatalog;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ProductCatalogTest {

    public static final String MY_DESCRIPTION = "My Fancy Product";
    public static final String MY_PICTURE = "http://my_image.jpeg";

    @Test
    public void itAllowsCreateProduct() {
        //Arrange
        ProductCatalogFacade api = thereIsProductCatalog();
        //Act
        String productId = api.createProduct();
        //Assert
        Assert.assertTrue(api.isExists(productId));
    }

    @Test
    public void itAllowsLoadProduct() {
        //Arrange
        ProductCatalogFacade api = thereIsProductCatalog();
        //Act
        String productId = api.createProduct();
        Product loaded = api.getById(productId);
        //Assert
        Assert.assertEquals(productId, loaded.getId());
    }

    @Test
    public void itAllowToSetProductDetails() {
        ProductCatalogFacade api = thereIsProductCatalog();
        String productId = api.createProduct();

        api.updateProductDetails(productId, MY_DESCRIPTION, MY_PICTURE);
        Product loaded = api.getById(productId);

        Assert.assertEquals(MY_DESCRIPTION, loaded.getDescription());
        Assert.assertEquals(MY_PICTURE, loaded.getPicture());
    }

    @Test
    public void itAllowToApplyPrice() {
        ProductCatalogFacade api = thereIsProductCatalog();
        String productId = api.createProduct();

        api.applyPrice(productId, BigDecimal.TEN);
        Product loaded = api.getById(productId);

        Assert.assertEquals(BigDecimal.TEN, loaded.getPrice());
    }

    @Test
    public void itAllowToListAllPublishedProducts() {
        ProductCatalogFacade api = thereIsProductCatalog();
        String draftProductId = api.createProduct();
        String productId = api.createProduct();
        api.updateProductDetails(productId, MY_DESCRIPTION, MY_PICTURE);
        api.applyPrice(productId, BigDecimal.TEN);

        List<Product> products = api.allPublishedProducts();

        assertThat(products)
                .hasSize(1)
                .extracting(Product::getId)
                .contains(productId)
                .doesNotContain(draftProductId);
    }

    @Test
    public void itDenyActionOnNotExistedProductV1() {
        try {
            ProductCatalogFacade api = thereIsProductCatalog();
            api.applyPrice("notExists", BigDecimal.valueOf(20));
            Assert.fail("Should throw exception");
        } catch (ProductNotFoundException e) {
            Assert.assertTrue(true);
        }
    }

    @Test(expected = ProductNotFoundException.class)
    public void itDenyActionOnNotExistedProductV2() {
        ProductCatalogFacade api = thereIsProductCatalog();
        api.applyPrice("notExists", BigDecimal.valueOf(20));
        api.updateProductDetails("notExists", "desc", "img");
    }

    @Test
    public void itDenyActionOnNotExistedProductV3() {
        ProductCatalogFacade api = thereIsProductCatalog();

        assertThatThrownBy(() -> api.applyPrice("notExists", BigDecimal.valueOf(20)))
                .hasMessage("There is no product with id: notExists");

        assertThatThrownBy(() -> api.updateProductDetails("notExists", "desc", "pic"))
                .hasMessage("There is no product with id: notExists");

        assertThatThrownBy(() -> api.getById("notExists"))
                .hasMessage("There is no product with id: notExists");
    }

    private static ProductCatalogFacade thereIsProductCatalog() {
        return new ProductCatalogConfiguration().productCatalogFacade();
    }
}
