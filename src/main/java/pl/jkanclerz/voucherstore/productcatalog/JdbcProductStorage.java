package pl.jkanclerz.voucherstore.productcatalog;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

public class JdbcProductStorage implements ProductStorage {
    public JdbcProductStorage(JdbcTemplate jdbcTemplate) {
    }

    @Override
    public void save(Product newProduct) {

    }

    @Override
    public Optional<Product> getById(String productId) {
        return Optional.empty();
    }

    @Override
    public List<Product> allPublishedProducts() {
        return null;
    }
}
