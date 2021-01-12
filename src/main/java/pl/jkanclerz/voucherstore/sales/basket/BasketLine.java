package pl.jkanclerz.voucherstore.sales.basket;

public class BasketLine {
    private String productId;
    private Integer quantity;

    public BasketLine(String productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
