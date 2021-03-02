package ca.ulaval.ima.ali_choix.ui.scannedproduct;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import ca.ulaval.ima.ali_choix.R;
import ca.ulaval.ima.ali_choix.ui.domain.Global;
import ca.ulaval.ima.ali_choix.ui.domain.OpenFoodFactScannedProduct;
import ca.ulaval.ima.ali_choix.ui.domain.Product;
import ca.ulaval.ima.ali_choix.ui.jsonresponse.JSONResponseElasticSearch;
import ca.ulaval.ima.ali_choix.ui.jsonresponse.JSONResponseOpenFactFood;
import ca.ulaval.ima.ali_choix.ui.network.ElasticSearchRestClient;
import ca.ulaval.ima.ali_choix.ui.network.OpenFoodFactRestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ScannedProductFragment extends Fragment {
    private OpenFoodFactScannedProduct openFoodFactScannedProduct;
    private Product product;
    private ImageView scannedProductImage;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_scanned_product, container, false);
        scannedProductImage = root.findViewById(R.id.scanned_product_image);
        TextView scannedProductDescription = root.findViewById(R.id.scanned_product_description);
        scannedProductDescription.setText("This is the scanned product description");

        getInformationsWithOpendFactFood();

        return root;
    }

    private void getInformationsWithOpendFactFood() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Global.openFoodFactBaseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        OpenFoodFactRestClient openFoodFactRestClient = retrofit.create(OpenFoodFactRestClient.class);
        Call<JSONResponseOpenFactFood> call = openFoodFactRestClient.getFoodWithId("737628064502");
        call.enqueue(new Callback<JSONResponseOpenFactFood>() {
            @Override
            public void onResponse(Call<JSONResponseOpenFactFood> call, Response<JSONResponseOpenFactFood> response) {
                openFoodFactScannedProduct = response.body().getContent();
                showInformations();
            }

            @Override
            public void onFailure(Call<JSONResponseOpenFactFood> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }

    private void getInformationsWithElasticSearch() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Global.elasticsearchBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ElasticSearchRestClient elasticSearchRestClient = retrofit.create(ElasticSearchRestClient.class);
        Call<JSONResponseElasticSearch> call = elasticSearchRestClient.getFoodWithCode("571");
        call.enqueue(new Callback<JSONResponseElasticSearch>() {
            @Override
            public void onResponse(Call<JSONResponseElasticSearch> call, Response<JSONResponseElasticSearch> response) {}

            @Override
            public void onFailure(Call<JSONResponseElasticSearch> call, Throwable t) {

            }
        });
    }

    private void showInformations() {
        String url_front = product.getImage();
        Log.e("url", url_front);
        Picasso.get().load(url_front).into(scannedProductImage);
    }
}