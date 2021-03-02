package ca.ulaval.ima.ali_choix.ui.network;

import ca.ulaval.ima.ali_choix.ui.jsonresponse.JSONResponseOpenFactFood;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface OpenFoodFactRestClient {

    @Headers("Content-Type: application/json")
    @GET("/{id}")
    Call<JSONResponseOpenFactFood> getFoodWithId(@Path("id") String id);
}
