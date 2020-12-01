package pl.jkanclerz.voucherstore.sales;

import org.junit.Test;

public class SalesTest {

    @Test
    public void itAllowAddProductToBasket() {

        //Arrange
        SalesFacade salesFacade = thereIsSalesModule();
        var productId1 = thereIsProductavailable();
        var productId2 = thereIsProductavailable();
        var customerId = thereIsCustomerWhoIsDoingSomeShoping();

        //Act
        salesFacade.addProduct(productId1);
        salesFacade.addProduct(productId2);

        //Asserr
        thereIsXproductCountInCustemersBasket(2, customerId);
    }

    private void thereIsXproductCountInCustemersBasket(int i, String customerId) {

    }

    private String thereIsCustomerWhoIsDoingSomeShoping() {
        return null;
    }

    private String thereIsProductavailable() {
        return null;
    }

    private SalesFacade thereIsSalesModule() {
        return null;
    }
}
