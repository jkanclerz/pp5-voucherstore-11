package pl.jkanclerz.voucherstore.productcatalog;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class HashMapProductStorage implements ProductStorage {
    Map<String, Product> products;

    public HashMapProductStorage() {
        this.products = new ConcurrentHashMap<>();
    }

    @Override
    public void save(Product newProduct) {
        products.put(newProduct.getId(), newProduct);
    }

    @Override
    public Optional<Product> getById(String productId) {
        return Optional.ofNullable(products.get(productId));
    }

    @Override
    public List<Product> allPublishedProducts() {
        return products.values()
                .stream()
                .filter(p -> p.getDescription() != null)
                .filter(p -> p.getPrice() != null)
                .collect(Collectors.toList());
    }
}
