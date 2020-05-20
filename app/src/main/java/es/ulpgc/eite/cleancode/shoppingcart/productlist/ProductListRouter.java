package es.ulpgc.eite.cleancode.shoppingcart.productlist;

import es.ulpgc.eite.cleancode.shoppingcart.app.AppMediator;
import es.ulpgc.eite.cleancode.shoppingcart.app.OrderToProductListState;
import es.ulpgc.eite.cleancode.shoppingcart.app.ProductDetailToListState;
import es.ulpgc.eite.cleancode.shoppingcart.app.ProductListToDetailState;
import es.ulpgc.eite.cleancode.shoppingcart.app.ProductToOrderListState;

public class ProductListRouter implements ProductListContract.Router {

    public static String TAG = ProductListRouter.class.getSimpleName();

    private AppMediator mediator;

    public ProductListRouter(AppMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public ProductDetailToListState getStateFromNextScreen() {
        return mediator.getProductDetailToListState();
    }

    @Override
    public void passStateToNextScreen(ProductListToDetailState state) {
        mediator.setNextProductListToDetailState(state);
    }

    @Override
    public void passStateToPreviousScreen(ProductToOrderListState state) {
        mediator.setPreviousProductToOrderListState(state);
    }

    @Override
    public OrderToProductListState getStateFromPreviousScreen() {
        return mediator.getOrderToProductListState();
    }

}
