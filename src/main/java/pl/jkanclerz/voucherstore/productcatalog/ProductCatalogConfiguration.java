package pl.jkanclerz.voucherstore.productcatalog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductCatalogConfiguration {

    public ProductCatalogFacade productCatalogFacade() {
        return new ProductCatalogFacade(new HashMapProductStorage());
    }


    @Bean
    public ProductCatalogFacade fixturesAwareProductCatalogFacade(ProductStorage productStorage) {
        ProductCatalogFacade productCatalogFacade = new ProductCatalogFacade(productStorage);

        return productCatalogFacade;
    }
}
