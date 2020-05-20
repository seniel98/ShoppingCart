package es.ulpgc.eite.cleancode.shoppingcart.productdetail;

import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.shoppingcart.app.ProductListToDetailState;

public class ProductDetailPresenter implements ProductDetailContract.Presenter {

    public static String TAG = ProductDetailPresenter.class.getSimpleName();

    private WeakReference<ProductDetailContract.View> view;
    private ProductDetailState state;
    private ProductDetailContract.Model model;
    private ProductDetailContract.Router router;

    public ProductDetailPresenter(ProductDetailState state) {
        this.state = state;
    }

    @Override
    public void onStart() {
        Log.e(TAG, "onStart()");

        if (state == null) {
            state = new ProductDetailState();
        }

        ProductListToDetailState savedState = router.getStateFromPreviousScreen();
        if (savedState != null) {
            model.onDataFromPreviousScreen(savedState.productData);
        }


    }

    @Override
    public void onRestart() {
        Log.e(TAG, "onRestart()");

        model.onRestartScreen(state.data);
    }

    @Override
    public void onResume() {
        Log.e(TAG, "onResume()");

        // call the model and update the state
        state.data = model.getStoredData();

        // update the view
        view.get().onDataUpdated(state);

    }

    @Override
    public void onBackPressed() {
        Log.e(TAG, "onBackPressed()");


    }

    @Override
    public void onPause() {
        Log.e(TAG, "onPause()");
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy()");
    }

    @Override
    public void onProductButtonTapped() {
        Log.e(TAG, "onProductButtonTapped()");

        if (state.data.content == 0 && state.data.value == 3) {
            //No se pueden añadir mas productos porque no hay
            view.get().onDataUpdated(state);
        } else {
            state.data.value++; //Inserta uno en el cart
            state.data.content--; //Quita uno del store
            view.get().onDataUpdated(state);
        }


    }

    @Override
    public void injectView(WeakReference<ProductDetailContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(ProductDetailContract.Model model) {
        this.model = model;
    }

    @Override
    public void injectRouter(ProductDetailContract.Router router) {
        this.router = router;
    }
}
