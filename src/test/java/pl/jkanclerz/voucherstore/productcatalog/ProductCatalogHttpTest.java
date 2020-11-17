package pl.jkanclerz.voucherstore.productcatalog;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductCatalogHttpTest {
    public static final String PRODUCT_2 = "product 2";
    public static final String PRODUCT_1 = "product 1";
    public static final String DRAFT_PRODUCT = "draft product";
    @LocalServerPort
    int localServerPort;

    @Autowired
    ProductCatalogFacade productCatalogFacade;

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void itListAllAvailableProducts() {
        //Arrange
        thereIsDraftProduct(DRAFT_PRODUCT);
        thereIsReadyToSellProduct(PRODUCT_1);
        thereIsReadyToSellProduct(PRODUCT_2);
        //Act
        ResponseEntity<Product[]> response = callApiForProductList();
        //Assert
        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        assertThat(response.getBody())
                .hasSize(2)
                .extracting(Product::getDescription)
                .contains(PRODUCT_1, PRODUCT_2)
                .doesNotContain(DRAFT_PRODUCT);
    }

    private void thereIsReadyToSellProduct(String productName) {
        var id = productCatalogFacade.createProduct();
        productCatalogFacade.updateProductDetails(id, productName, "nice picture");
        productCatalogFacade.applyPrice(id, BigDecimal.valueOf(222.22));
    }

    private void thereIsDraftProduct(String draftProductName) {
        var id = productCatalogFacade.createProduct();
        productCatalogFacade.updateProductDetails(id, draftProductName, null);
    }

    private ResponseEntity<Product[]> callApiForProductList() {
        var url = String.format("http://localhost:%s/api/products", localServerPort);
        ResponseEntity<Product[]> response = restTemplate.getForEntity(url, Product[].class);
        return response;
    }
}
