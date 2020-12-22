package pl.jkanclerz.voucherstore.sales.offer;

public interface ProductDetailsProvider {
    ProductDetails getByProductId(String productId);
}
