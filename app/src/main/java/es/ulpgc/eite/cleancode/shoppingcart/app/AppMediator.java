package es.ulpgc.eite.cleancode.shoppingcart.app;

import es.ulpgc.eite.cleancode.shoppingcart.oderdetail.OrderDetailState;
import es.ulpgc.eite.cleancode.shoppingcart.orderlist.OrderListState;
import es.ulpgc.eite.cleancode.shoppingcart.productdetail.ProductDetailState;
import es.ulpgc.eite.cleancode.shoppingcart.productlist.ProductListState;

public class AppMediator {

    private static AppMediator instance;

    private OrderListState orderListState;
    private ProductDetailState productDetailState;
    private ProductListState productListState;
    private OrderDetailState orderDetailState;

    private OrderListToDetailState orderListToDetailState;


    private OrderToProductListState orderToProductListState;
    private ProductDetailToListState productDetailToListState;
    private ProductListToDetailState productListToDetailState;
    private ProductToOrderListState productToOrderListState;

    private AppMediator() {
        orderListState = new OrderListState();
        productDetailState = new ProductDetailState();
        productListState = new ProductListState();
        orderDetailState = new OrderDetailState();
    }

    public static void resetInstance() {
        AppMediator.instance = null;
    }

    public static AppMediator getInstance() {
        if (instance == null) {
            instance = new AppMediator();
        }

        return instance;
    }


    public void setProductListState(ProductListState state) {
        this.productListState = state;
    }

    public OrderListState getOrderListState() {
        return orderListState;
    }

    public OrderDetailState getOrderDetailState() {
        return orderDetailState;
    }

    public ProductDetailState getProductDetailState() {
        return productDetailState;
    }

    public ProductListState getProductListState() {
        return productListState;
    }

    public void setNextProductListToDetailState(ProductListToDetailState state) {
        productListToDetailState = state;
    }

    public ProductListToDetailState getProductListToDetailState() {
        ProductListToDetailState state = productListToDetailState;
        productListToDetailState = null;
        return state;
    }

    public void setPreviousProductDetailToListState(ProductDetailToListState state) {
        productDetailToListState = state;
    }

    public ProductDetailToListState getProductDetailToListState() {
        ProductDetailToListState state = productDetailToListState;
        productDetailToListState = null;
        return state;
    }

    public void setPreviousProductToOrderListState(ProductToOrderListState state) {
        productToOrderListState = state;
    }

    public ProductToOrderListState getProductToOrderListState() {
        ProductToOrderListState state = productToOrderListState;
        productToOrderListState = null;
        return state;
    }

    public void setOrderListToDetailState(OrderListToDetailState state) {
        orderListToDetailState = state;
    }

    public OrderListToDetailState getOrderListToDetailState() {
        OrderListToDetailState state = orderListToDetailState;
        orderListToDetailState = null;
        return state;
    }

    public OrderToProductListState getOrderToProductListState() {
        OrderToProductListState state = orderToProductListState;
        orderToProductListState = null;
        return state;
    }

    public void setOrderToProductListState(OrderToProductListState orderToProductListState) {
        this.orderToProductListState = orderToProductListState;
    }
}
