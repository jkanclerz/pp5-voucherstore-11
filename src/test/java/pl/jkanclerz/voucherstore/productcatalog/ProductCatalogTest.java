package pl.jkanclerz.voucherstore.productcatalog;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

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

        Assert.assertEquals(1, products.size());
    }


    private static ProductCatalogFacade thereIsProductCatalog() {
        return new ProductCatalogFacade();
    }
}
