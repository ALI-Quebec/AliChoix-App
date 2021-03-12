package ca.ulaval.ima.ali_choix.ui.network;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public interface ScannedProduct {

    public void get(String id, RequestParams params, AsyncHttpResponseHandler responseHandler);
}
