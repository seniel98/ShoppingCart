package es.ulpgc.eite.cleancode.shoppingcart.productdetail;

import es.ulpgc.eite.cleancode.shoppingcart.app.AppMediator;
import es.ulpgc.eite.cleancode.shoppingcart.app.ProductDetailToListState;
import es.ulpgc.eite.cleancode.shoppingcart.app.ProductListToDetailState;

public class ProductDetailRouter implements ProductDetailContract.Router {

    public static String TAG = ProductDetailRouter.class.getSimpleName();

    private AppMediator mediator;

    public ProductDetailRouter(AppMediator mediator) {
        this.mediator = mediator;
    }


    @Override
    public void passStateToPreviousScreen(ProductDetailToListState state) {
        mediator.setPreviousProductDetailToListState(state);
    }

    @Override
    public ProductListToDetailState getStateFromPreviousScreen() {
        return mediator.getProductListToDetailState();
    }

}
