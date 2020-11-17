package pl.jkanclerz.voucherstore.productcatalog;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Product {
    @Id
    private String productId;
    private String description;
    private String picture;
    private BigDecimal price;

    public Product(UUID productId) {
        this.productId = productId.toString();
    }

    public String getId() {
        return productId.toString();
    }
}
