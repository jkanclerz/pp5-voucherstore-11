package pl.jkanclerz.voucherstore.productcatalog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.UUID;
import static org.assertj.core.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class JDBCProductStorageTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void itAllowStorePproduct() {
        var product = new Product(UUID.randomUUID());
        ProductStorage jdbcStorage = thereIsJDBCProductStorage();

        jdbcStorage.save(product);

        assertThat(jdbcStorage.getById(product.getId()))
                .isNotEmpty()
                .map(product1 -> product.getId())
                .contains(product.getId());
    }

    @Test
    public void itAllowLoadAllProducts() {

    }

    private ProductStorage thereIsJDBCProductStorage() {
        return new JdbcProductStorage(jdbcTemplate);
    }
}
