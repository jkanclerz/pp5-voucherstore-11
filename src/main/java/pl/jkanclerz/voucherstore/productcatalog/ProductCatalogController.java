package pl.jkanclerz.voucherstore.productcatalog;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductCatalogController {

    ProductCatalogFacade productCatalogFacade;

    public ProductCatalogController(ProductCatalogFacade productCatalogFacade) {
        this.productCatalogFacade = productCatalogFacade;
    }

    @GetMapping("/api/products")
    public List<Product> myProducts() {
        return productCatalogFacade.allPublishedProducts();
    }
}
