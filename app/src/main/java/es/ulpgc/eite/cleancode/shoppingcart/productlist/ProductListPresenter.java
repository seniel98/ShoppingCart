package es.ulpgc.eite.cleancode.shoppingcart.productlist;

import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.shoppingcart.app.OrderToProductListState;
import es.ulpgc.eite.cleancode.shoppingcart.app.ProductDetailToListState;
import es.ulpgc.eite.cleancode.shoppingcart.app.ProductListToDetailState;
import es.ulpgc.eite.cleancode.shoppingcart.app.ProductToOrderListState;
import es.ulpgc.eite.cleancode.shoppingcart.data.ProductData;

public class ProductListPresenter implements ProductListContract.Presenter {

    public static String TAG = ProductListPresenter.class.getSimpleName();

    private WeakReference<ProductListContract.View> view;
    private ProductListState state;
    private ProductListContract.Model model;
    private ProductListContract.Router router;

    public ProductListPresenter(ProductListState state) {
        this.state = state;
    }

    @Override
    public void onStart() {
        Log.e(TAG, "onStart()");

        if (state == null) {
            state = new ProductListState();
        }

        OrderToProductListState orderToProductListState = router.getStateFromPreviousScreen();
        if (orderToProductListState != null) {
            state.data = orderToProductListState.orderData;
        }


    }

    @Override
    public void onRestart() {
        Log.e(TAG, "onRestart()");

        model.onRestartScreen(state.datasource, state.data);
    }

    @Override
    public void onResume() {
        Log.e(TAG, "onResume()");

        // call the model and update the state
        state.datasource = model.getStoredDatasource();

        // update the view
        view.get().onDataUpdated(state);

        ProductDetailToListState savedState = router.getStateFromNextScreen();
        if (savedState != null) {
            model.onDataFromNextScreen(savedState.productData);
            state.data.content.add(savedState.productData);
            //Log.e("OnStart()", "tate.data.content.add(savedState.productData)" + state.data.content.toString());
        }

    }

    @Override
    public void onBackPressed() {
        Log.e(TAG, "onBackPressed()");

        ProductToOrderListState productToOrderListState = new ProductToOrderListState();

        productToOrderListState.orderData = state.data;
        router.passStateToPreviousScreen(productToOrderListState);
    }

    @Override
    public void onPause() {
        Log.e(TAG, "onPause()");

    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy()");
        state.datasource = model.getStoredDatasource();
    }

    @Override
    public void onListTapped(ProductData data) {
        Log.e(TAG, "onListTapped()");

        ProductListToDetailState productListToDetailState = new ProductListToDetailState();
        productListToDetailState.productData = data;
        router.passStateToNextScreen(productListToDetailState);
        view.get().navigateToNextScreen();

    }

    @Override
    public void injectView(WeakReference<ProductListContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(ProductListContract.Model model) {
        this.model = model;
    }

    @Override
    public void injectRouter(ProductListContract.Router router) {
        this.router = router;
    }
}
