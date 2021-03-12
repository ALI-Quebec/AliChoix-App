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

import org.json.JSONObject;

import ca.ulaval.ima.ali_choix.R;
import ca.ulaval.ima.ali_choix.ui.domain.OpenFoodFactScannedProduct;
import ca.ulaval.ima.ali_choix.ui.network.OpenFoodFactRestClient;

public class ScannedProductFragment extends Fragment {
    private OpenFoodFactScannedProduct openFoodFactScannedProduct;
    private ImageView scannedProductImage;
    private TextView scannedProductOriginText;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_scanned_product, container, false);
        scannedProductImage = root.findViewById(R.id.scanned_product_image);
        scannedProductOriginText = root.findViewById(R.id.scanned_product_origin);
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
                openFoodFactScannedProduct = OpenFoodFactScannedProduct.fromJson(dataObject);
                showInformations();
            }
        });
    }

    private void showInformations() {
        String url_front = openFoodFactScannedProduct.getProduct().getImage();
        Picasso.get().load(url_front).into(scannedProductImage);

    }
}