package pl.jkanclerz.voucherstore.productcatalog;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Product {
    @Id
    private String productId;
    private String description;
    private String picture;
    private BigDecimal price;

    Product() {}

    public Product(UUID productId) {
        this.productId = productId.toString();
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
