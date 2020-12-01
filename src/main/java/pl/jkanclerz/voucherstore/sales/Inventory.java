package pl.jkanclerz.voucherstore.sales;

public interface Inventory {
    boolean isAvailable(String productId);
}
