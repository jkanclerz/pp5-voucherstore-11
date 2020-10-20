package pl.jkanclerz.voucherstore.productcatalog;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
    private final UUID productId;
    private String description;
    private String picture;
    private BigDecimal price;

    public Product(UUID productId) {
        this.productId = productId;
    }

    public String getId() {
        return productId.toString();
    }

    public String getDescription() {
        return description;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public Product setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Product setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
