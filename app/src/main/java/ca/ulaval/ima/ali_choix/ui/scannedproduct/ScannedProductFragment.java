package ca.ulaval.ima.ali_choix.ui.scannedproduct;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import cz.msebera.android.httpclient.Header;

import org.json.JSONException;
import org.json.JSONObject;

import ca.ulaval.ima.ali_choix.R;
import ca.ulaval.ima.ali_choix.ui.domain.OpenFoodFactScannedProduct;
import ca.ulaval.ima.ali_choix.ui.domain.Product;
import ca.ulaval.ima.ali_choix.ui.network.OpenFoodFactRestClient;

public class ScannedProductFragment extends Fragment {
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
        OpenFoodFactRestClient OFFClient = new OpenFoodFactRestClient();
        OFFClient.get("737628064502" , null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject dataObject) {
                try {
                    JSONObject productJson = (JSONObject) dataObject.get("product");
                    product = Product.fromJson(productJson);
                    showInformations();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void showInformations() {
        String url_front = product.getImage();
        Picasso.get().load(url_front).into(scannedProductImage);
    }
}