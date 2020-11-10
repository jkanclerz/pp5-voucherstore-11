package pl.jkanclerz.voucherstore.productcatalog;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;


public class ListProductStorageTest {
    @Test
    public void itAllowStoreProduct() {
        Product p1 = thereIsProduct();
        ProductStorage storage = new ListProductStorage();

        storage.save(p1);

    }

    private Product thereIsProduct() {
        Product product = new Product(UUID.randomUUID());
        product.setPrice(BigDecimal.valueOf(10));

        return product;
    }
}
