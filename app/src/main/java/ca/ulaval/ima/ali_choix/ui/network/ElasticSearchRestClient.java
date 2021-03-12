package ca.ulaval.ima.ali_choix.ui.network;

import com.loopj.android.http.*;

import ca.ulaval.ima.ali_choix.ui.domain.Global;
import cz.msebera.android.httpclient.Header;

public class ElasticSearchRestClient {
 private static AsyncHttpClient client;

 public ElasticSearchRestClient(){
  this.client = new AsyncHttpClient();
//  this.client.setBasicAuth("aissa","aissa123");
 }

 // PUBLIC - GET, POST, PUT, DELETE

 public void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
  client.get(getAbsoluteUrl(url), params, responseHandler);
 }

 public void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
  client.post(getAbsoluteUrl(url), params, responseHandler);
 }

 public void put(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
  client.post(getAbsoluteUrl(url), params, responseHandler);
 }

 public void delete(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
  client.post(getAbsoluteUrl(url), params, responseHandler);
 }

 // PRIVATE

 private String getAbsoluteUrl(String relativeUrl) {
  return Global.elasticsearchBaseUrl + relativeUrl;
 }
}
