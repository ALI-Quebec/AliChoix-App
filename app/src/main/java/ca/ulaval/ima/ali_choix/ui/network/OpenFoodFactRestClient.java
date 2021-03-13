package ca.ulaval.ima.ali_choix.ui.network;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import ca.ulaval.ima.ali_choix.ui.domain.GlobalConstant;

public class OpenFoodFactRestClient {
    private static AsyncHttpClient client = new AsyncHttpClient();

    // PUBLIC - GET, POST, PUT, DELETE
    public void get(String id, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(id), params, responseHandler);
    }
    public void post(String id, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(id), params, responseHandler);
    }
    // PRIVATE
    private String getAbsoluteUrl(String id) {
        return GlobalConstant.OPEN_FOOD_FACT_SEARCH_BASE_URL + id + ".json";
    }
}
