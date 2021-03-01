package ca.ulaval.ima.ali_choix.ui.network;

import ca.ulaval.ima.ali_choix.ui.jsonresponse.JSONResponseElasticSearch;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ElasticSearchRestClient {

 @GET("/foods_enriched/_search?q=food_code:{code}")
 Call<JSONResponseElasticSearch> getFoodWithCode(@Path("code") String code);
}
