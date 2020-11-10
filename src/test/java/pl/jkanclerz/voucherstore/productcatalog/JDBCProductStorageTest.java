package pl.jkanclerz.voucherstore.productcatalog;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class JDBCProductStorageTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        jdbcTemplate.execute("DROP TABLE products_catalog__products IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE `products_catalog__products` (" +
                "`id` varchar(100) NOT NULL," +
                "`description` varchar(255)," +
                "`picture` varchar(150)," +
                "`price` DECIMAL(12,2)," +
                "PRIMARY KEY (id)" +
                ");");
    }

    @Test
    public void itAllowStoreProduct() {
        var product = ProductFixtures.draftProduct();
        ProductStorage jdbcStorage = thereIsJDBCProductStorage();

        jdbcStorage.save(product);

        assertThat(jdbcStorage.getById(product.getId()))
                .isNotEmpty()
                .map(product1 -> product.getId())
                .contains(product.getId());
    }

    @Test
    public void thereIsEmptyWheNoProduct() {
        ProductStorage jdbcStorage = thereIsJDBCProductStorage();
        assertThat(jdbcStorage.getById("not_exists"))
                .isEmpty();
    }

    @Test
    public void itAllowLoadAllProducts() {
        var p1 = ProductFixtures.readyToSellProduct("p1", 20.20);
        var p2 = ProductFixtures.readyToSellProduct("p2", 20.20);
        var p3 = ProductFixtures.draftProduct();

        ProductStorage jdbcStorage = thereIsJDBCProductStorage();

        jdbcStorage.save(p1);
        jdbcStorage.save(p2);

        List<Product> products = jdbcStorage.allPublishedProducts();

        assertThat(products)
                .hasSize(2)
                .extracting(Product::getId)
                .contains(p1.getId())
                .doesNotContain(p3.getId());
    }

    private ProductStorage thereIsJDBCProductStorage() {
        return new JdbcProductStorage(jdbcTemplate);
    }
}
