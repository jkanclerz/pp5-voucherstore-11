package pl.jkanclerz.voucherstore.productcatalog;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface ProductStorage extends Repository<Product, String> {
    void save(Product newProduct);

    @Query("Select p from Product p where p.productId = :productId")
    Optional<Product> getById(@Param("productId") String productId);

    @Query("Select p from Product p where p.price is NOT NULL and p.description IS NOT NULL")
    List<Product> allPublishedProducts();
}
