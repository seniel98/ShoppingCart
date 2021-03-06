package es.ulpgc.eite.cleancode.shoppingcart.orderlist;

import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.shoppingcart.app.OrderListToDetailState;
import es.ulpgc.eite.cleancode.shoppingcart.app.OrderToProductListState;
import es.ulpgc.eite.cleancode.shoppingcart.app.ProductToOrderListState;
import es.ulpgc.eite.cleancode.shoppingcart.data.OrderData;

public class OrderListPresenter implements OrderListContract.Presenter {

    public static String TAG = OrderListPresenter.class.getSimpleName();

    private WeakReference<OrderListContract.View> view;
    private OrderListState state;
    private OrderListContract.Model model;
    private OrderListContract.Router router;

    public OrderListPresenter(OrderListState state) {
        this.state = state;
    }

    @Override
    public void onStart() {
        Log.e(TAG, "onStart()");

        if (state == null) {
            state = new OrderListState();
        }

        model.onResetDatastore(); // for testing

        ProductToOrderListState savedState = router.getStateFromNextScreen();
        if (savedState != null) {
            model.onDataFromNextScreen(savedState.orderData);
        }

    }

    @Override
    public void onRestart() {
        Log.e(TAG, "onRestart()");


        model.onRestartScreen(state.datasource);
    }

    @Override
    public void onResume() {
        Log.e(TAG, "onResume()");

        // call the model and update the state
        state.datasource = model.getStoredDatasource();

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
    public void onButtonTapped() {
        Log.e(TAG, "onButtonTapped()");

        state.numberOrders++;
        int numberOrders = state.numberOrders;
        OrderData orderData = new OrderData(String.valueOf(numberOrders));
        state.datasource.add(orderData);
        OrderToProductListState orderToProductListState = new OrderToProductListState();
        orderToProductListState.orderData = orderData;
        router.passStateToNextScreen(orderToProductListState);
        view.get().navigateToNextScreen();
        view.get().onDataUpdated(state);

    }

    @Override
    public void onListTapped(OrderData data) {
        Log.e(TAG, "onListTapped()");

        OrderListToDetailState orderListToDetailState = new OrderListToDetailState();
        orderListToDetailState.data = data;
        router.passStateToDetailScreen(orderListToDetailState);
        view.get().navigateToDetailScreen();
    }

    @Override
    public void injectView(WeakReference<OrderListContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(OrderListContract.Model model) {
        this.model = model;
    }

    @Override
    public void injectRouter(OrderListContract.Router router) {
        this.router = router;
    }
}
