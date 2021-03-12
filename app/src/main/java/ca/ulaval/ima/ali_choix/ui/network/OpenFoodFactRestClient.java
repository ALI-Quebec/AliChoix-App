package ca.ulaval.ima.ali_choix.ui.network;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import ca.ulaval.ima.ali_choix.ui.domain.Global;

public class OpenFoodFactRestClient implements ScannedProduct {
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
        return Global.openFoodFactBaseUrl + id + ".json";
    }
}
