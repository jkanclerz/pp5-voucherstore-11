package pl.jkanclerz.voucherstore.productcatalog;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class JdbcProductStorage implements ProductStorage {
    public static final String INSERT_SQL = "INSERT INTO `products_catalog__products` " +
            "(`id`, `description`, `picture`, `price`) " +
            "values " +
            "(?,?,?,?)";

    public static final String SELECT_SINGLE_SQL = "Select * from `products_catalog__products` where id = ?";

    public static final String SELECT_PUBLISHED = "Select * from `products_catalog__products` where price IS NOT NULL and description is NOT NULL";

    private final JdbcTemplate jdbcTemplate;

    public JdbcProductStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Product newProduct) {
        jdbcTemplate.update(
                INSERT_SQL,
                newProduct.getId(), newProduct.getDescription(), newProduct.getPicture(), newProduct.getPrice());
    }

    @Override
    public Optional<Product> getById(String productId) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SELECT_SINGLE_SQL, new Object[] {productId}, getProductRowMapper()));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private static RowMapper<Product> getProductRowMapper() {
        return (rs, i) -> {
            Product p = new Product(UUID.fromString(rs.getString("id")));
            p.setDescription(rs.getString("description"));
            p.setPrice(rs.getBigDecimal("price"));
            p.setPicture(rs.getString("picture"));

            return p;
        };
    }

    @Override
    public List<Product> allPublishedProducts() {
        return jdbcTemplate.query(SELECT_PUBLISHED, getProductRowMapper());
    }
}