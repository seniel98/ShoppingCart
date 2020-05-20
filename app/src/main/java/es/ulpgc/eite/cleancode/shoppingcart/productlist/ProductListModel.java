package es.ulpgc.eite.cleancode.shoppingcart.productlist;

import android.util.Log;

import java.util.List;

import es.ulpgc.eite.cleancode.shoppingcart.data.OrderData;
import es.ulpgc.eite.cleancode.shoppingcart.data.ProductData;
import es.ulpgc.eite.cleancode.shoppingcart.data.ProductStore;

public class ProductListModel implements ProductListContract.Model {

    public static String TAG = ProductListModel.class.getSimpleName();

    private OrderData data;
    private List<ProductData> datasource;

    public ProductListModel(List<ProductData> datasource) {
        this.datasource = ProductStore.instance().datasource;
    }


    @Override
    public List<ProductData> getStoredDatasource() {
        // Log.e(TAG, "getStoredDatasource()");
        return datasource;
    }

    @Override
    public OrderData getStoredData() {
        // Log.e(TAG, "getStoredData()");
        return data;
    }

    @Override
    public void onRestartScreen(List<ProductData> datasource, OrderData data) {
        // Log.e(TAG, "onRestartScreen()");

        this.datasource = datasource;

        this.data = data;

    }


    @Override
    public void onDataFromNextScreen(ProductData data) {
        Log.e(TAG, "onDataFromNextScreen()");


    }

    @Override
    public void onDataFromPreviousScreen(OrderData data) {
        // Log.e(TAG, "onDataFromPreviousScreen()");
        this.data = data;
    }
}
