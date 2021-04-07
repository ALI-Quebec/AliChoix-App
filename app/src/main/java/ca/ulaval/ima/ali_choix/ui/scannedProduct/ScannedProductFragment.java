package ca.ulaval.ima.ali_choix.ui.scannedproduct;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import cz.msebera.android.httpclient.Header;

import org.json.JSONException;
import org.json.JSONObject;

import ca.ulaval.ima.ali_choix.R;
import ca.ulaval.ima.ali_choix.domain.Product;
import ca.ulaval.ima.ali_choix.network.OpenFoodFactRestClient;

public class ScannedProductFragment extends Fragment {
    private Product product;
    private ImageView scannedProductImage;
    private TextView scannedProductOrigin;
    private TextView scannedProductCountryImported;
    private TextView scannedProductQuantity;
    private TextView scannedProductName;
    private ImageView downArrow;
    private ImageView upArrow;
    private ImageView nutriScoreDrawable;
    private RelativeLayout itemCollapsible;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_scanned_product, container, false);
        scannedProductImage = root.findViewById(R.id.scanned_product_image);
        scannedProductOrigin = root.findViewById(R.id.scanned_product_origin);
        scannedProductCountryImported = root.findViewById(R.id.scanned_product_imported_country);
        scannedProductQuantity = root.findViewById(R.id.scanned_product_quantity);
        scannedProductName = root.findViewById(R.id.scanned_product_name);
        itemCollapsible = root.findViewById(R.id.collapsible_section);
        downArrow = root.findViewById(R.id.down_arrow);
        upArrow = root.findViewById(R.id.up_arrow);
        nutriScoreDrawable = root.findViewById(R.id.nutri_score_drawable);

        upArrow.setVisibility(View.GONE);
        nutriScoreDrawable.setVisibility(View.GONE);

        itemCollapsible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleCollapsibleSection(v);
            }
        });

        getInformationsWithOpenFoodFact();

        return root;
    }


    private void getInformationsWithOpenFoodFact() {
        OpenFoodFactRestClient OFFClient = new OpenFoodFactRestClient();
        OFFClient.get("737628064502", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject dataObject) {
                try {
                    JSONObject productJson = (JSONObject) dataObject.get("product");
                    Gson gson = new Gson();
                    product = gson.fromJson(String.valueOf(productJson), Product.class);
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
        scannedProductOrigin.setText(product.getOrigin());
        scannedProductCountryImported.setText(product.getCountryImported());
        scannedProductQuantity.setText(product.getQuantity());
        scannedProductName.setText(product.getName());
    }

    private void toggleCollapsibleSection(View v) {
        upArrow.setVisibility(upArrow.isShown()
                ? View.GONE
                : View.VISIBLE);
        nutriScoreDrawable.setVisibility(nutriScoreDrawable.isShown()
                ? View.GONE
                : View.VISIBLE);
        downArrow.setVisibility(downArrow.isShown() ? View.GONE : View.VISIBLE);
    }
}