package ca.ulaval.ima.ali_choix.network;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import ca.ulaval.ima.ali_choix.domain.DomainConstant;

public class OpenFoodFactRestClient implements ClientAPI {
    private static final AsyncHttpClient client = new AsyncHttpClient();

    public void get(String id, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(id), params, responseHandler);
    }

    private String getAbsoluteUrl(String id) {
        return DomainConstant.OPEN_FOOD_FACT_SEARCH_BASE_URL + id + ".json";
    }
}
