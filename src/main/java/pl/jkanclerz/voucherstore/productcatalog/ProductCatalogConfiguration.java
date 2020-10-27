package pl.jkanclerz.voucherstore.productcatalog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;

@Configuration
public class ProductCatalogConfiguration {

    public ProductCatalogFacade productCatalogFacade() {
        return new ProductCatalogFacade(new HashMapProductStorage());
    }

    @Bean
    public ProductStorage productionProductStorage() {
        return new HashMapProductStorage();
//        return new JDBCProductStorage("mysql://localhost:3306/voucher-shop");
    }

    @Bean
    public ProductCatalogFacade fixturesAwareProductCatalogFacade(ProductStorage productStorage) {
        ProductCatalogFacade productCatalogFacade = new ProductCatalogFacade(productStorage);

        String p1 = productCatalogFacade.createProduct();
        productCatalogFacade.applyPrice(p1, BigDecimal.valueOf(20.20));
        productCatalogFacade.updateProductDetails(p1, "My fancy product 1", "http://nice.image");

        String p2 = productCatalogFacade.createProduct();
        productCatalogFacade.applyPrice(p2, BigDecimal.valueOf(80.20));
        productCatalogFacade.updateProductDetails(p2, "My fancy product 2", "http://nice.image");

        String p3 = productCatalogFacade.createProduct();
        productCatalogFacade.applyPrice(p3, BigDecimal.valueOf(120.20));
        productCatalogFacade.updateProductDetails(p3, "My fancy product 3", "http://nice.image");

        return productCatalogFacade;
    }


}
