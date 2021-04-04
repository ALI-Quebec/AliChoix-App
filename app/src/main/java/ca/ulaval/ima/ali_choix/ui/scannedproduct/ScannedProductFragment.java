package ca.ulaval.ima.ali_choix.ui.scannedproduct;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import ca.ulaval.ima.ali_choix.domain.NutrientLevelsQuantity;
import ca.ulaval.ima.ali_choix.domain.Nutriments;
import ca.ulaval.ima.ali_choix.services.ProductService;
import cz.msebera.android.httpclient.Header;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

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
    private ImageView nutriScoreDownArrow;
    private ImageView nutriScoreUpArrow;
    private RelativeLayout nutriScoreCollapsible;
    private RelativeLayout nutriScoreLayout;
    private ImageView nutriScoreDrawable;
    private RelativeLayout ingredientsAnalysisCollapsible;
    private ImageView ingredientAnalysisDownArrow;
    private ImageView ingredientAnalysisUpArrow;
    private TextView isVegetarian;
    private TextView isVegan;
    private RelativeLayout ingredientsAnalysisLayout;
    private TextView isPalmOilFree;
    private RelativeLayout nutrientLevelsCollapsible;
    private RelativeLayout nutrientLevelsLayout;
    private ImageView nutrientLevelsDownArrow;
    private ImageView nutrientLevelsUpArrow;
    private TextView nutriScoreDescription;
    private String scannedProductNutriScoreGrade;
    private ImageView fatQuantityIndicator;
    private ImageView saturatedFatQuantityIndicator;
    private ImageView sugarsQuantityIndicator;
    private ImageView saltQuantityIndicator;
    private TextView fatQuantityDescription;
    private TextView saturatedFatQuantityDescription;
    private TextView sugarsQuantityDescription;
    private TextView saltQuantityDescription;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_scanned_product, container, false);
        scannedProductImage = root.findViewById(R.id.scanned_product_image);
        scannedProductOrigin = root.findViewById(R.id.scanned_product_origin);
        scannedProductCountryImported = root.findViewById(R.id.scanned_product_imported_country);
        scannedProductQuantity = root.findViewById(R.id.scanned_product_quantity);
        scannedProductName = root.findViewById(R.id.scanned_product_name);

        nutriScoreCollapsible = root.findViewById(R.id.collapsible_nutri_score_section);
        nutriScoreDrawable = root.findViewById(R.id.nutri_score_drawable);
        nutriScoreDescription = root.findViewById(R.id.nutri_score_description);
        nutriScoreLayout = root.findViewById(R.id.nutri_score_layout);
        nutriScoreDownArrow = root.findViewById(R.id.nutri_score_down_arrow);
        nutriScoreUpArrow = root.findViewById(R.id.nutri_score_up_arrow);

        nutriScoreUpArrow.setVisibility(View.GONE);
        nutriScoreLayout.setVisibility(View.GONE);

        nutriScoreCollapsible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleCollapsibleSection(v, nutriScoreUpArrow, nutriScoreDownArrow, nutriScoreLayout);
            }
        });

        ingredientsAnalysisCollapsible = root.findViewById(R.id.collapsible_diet_section);
        ingredientsAnalysisLayout = root.findViewById(R.id.diet_type_layout);
        ingredientAnalysisDownArrow = root.findViewById(R.id.diet_down_arrow);
        ingredientAnalysisUpArrow = root.findViewById(R.id.diet_up_arrow);
        isVegetarian = root.findViewById(R.id.is_vegeterian);
        isVegan = root.findViewById(R.id.is_vegan);
        isPalmOilFree = root.findViewById(R.id.is_palm_oil_free);

        ingredientAnalysisUpArrow.setVisibility(View.GONE);
        ingredientsAnalysisLayout.setVisibility(View.GONE);

        ingredientsAnalysisCollapsible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleCollapsibleSection(v, ingredientAnalysisUpArrow, ingredientAnalysisDownArrow, ingredientsAnalysisLayout);
            }
        });

        nutrientLevelsCollapsible = root.findViewById(R.id.collapsible_nutrient_levels_section);
        nutrientLevelsLayout = root.findViewById(R.id.nutrient_levels_layout);
        nutrientLevelsDownArrow = root.findViewById(R.id.nutrient_levels_down_arrow);
        nutrientLevelsUpArrow = root.findViewById(R.id.nutrient_levels_up_arrow);

        nutrientLevelsUpArrow.setVisibility(View.GONE);
        nutrientLevelsLayout.setVisibility(View.GONE);

        nutrientLevelsCollapsible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleCollapsibleSection(v, nutrientLevelsUpArrow, nutrientLevelsDownArrow, nutrientLevelsLayout);
            }
        });

        fatQuantityIndicator = root.findViewById(R.id.fat_quantity_indicator);
        saturatedFatQuantityIndicator = root.findViewById(R.id.saturated_fat_quantity_indicator);
        sugarsQuantityIndicator = root.findViewById(R.id.sugars_indicator);
        saltQuantityIndicator = root.findViewById(R.id.salt_indicator);
        fatQuantityDescription = root.findViewById(R.id.fat_quantity);
        saturatedFatQuantityDescription = root.findViewById(R.id.saturated_fat_quantity);
        sugarsQuantityDescription = root.findViewById(R.id.sugars_quantity);
        saltQuantityDescription = root.findViewById(R.id.salt_quantity);

        getInformationsWithOpenFoodFact();

        return root;
    }


    private void getInformationsWithOpenFoodFact() {
        OpenFoodFactRestClient OFFClient = new OpenFoodFactRestClient();
        OFFClient.get("3229820100234", null, new JsonHttpResponseHandler() {
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

    @SuppressLint("UseCompatLoadingForDrawables")
    private void showInformations() {
        ProductService productService = new ProductService() {
            @Nullable
            @Override
            public IBinder onBind(Intent intent) {
                return null;
            }
        };

        String englishName = product.getEnglishName();
        String frenchName = product.getFrenchName();
        String url_front = product.getImage();
        String origin = product.getOrigin();
        String countryImported = product.getCountryImported();
        String quantity = product.getQuantity();
        Nutriments nutriments = product.getNutriments();

        Picasso.get().load(url_front).into(scannedProductImage);
        scannedProductOrigin.setText( origin == null || origin.isEmpty() ? "No results found" : origin);
        scannedProductCountryImported.setText(countryImported == null || countryImported.isEmpty()  ? "No results found" : countryImported);
        scannedProductQuantity.setText(quantity == null || quantity.isEmpty() ? "No results found" : quantity+'g');
        if (englishName != null && !englishName.trim().equals("")) {
            scannedProductName.setText(englishName);
        }
        if (frenchName != null && !frenchName.trim().equals("")){
            scannedProductName.setText(frenchName);
        }

        scannedProductNutriScoreGrade = product.getNutriScoreGrade();
        nutriScoreDrawable.setBackground(getNutriScoreGradeDrawable(scannedProductNutriScoreGrade.toLowerCase()));
        nutriScoreDescription.setText(productService.getNutriScoreDescription(scannedProductNutriScoreGrade.toLowerCase()));

        HashMap nutrientLevels = productService.getNutrientLevelsQuantity(nutriments);
        NutrientLevelsQuantity fatNutrientLevelsQuantity = (NutrientLevelsQuantity) nutrientLevels.get("fatNutrientLevelsQuantity");
        NutrientLevelsQuantity saturatedFatNutrientLevelsQuantity = (NutrientLevelsQuantity) nutrientLevels.get("saturatedFatNutrientLevelsQuantity");
        NutrientLevelsQuantity sugarsNutrientLevelsQuantity = (NutrientLevelsQuantity) nutrientLevels.get("sugarNutrientLevelsQuantity");
        NutrientLevelsQuantity saltNutrientLevelsQuantity = (NutrientLevelsQuantity) nutrientLevels.get("saltNutrientLevelsQuantity");

        //TODO est-ce qu'il ne faudrait pas ici mettre un interface pour pas que le UI soit dépendant du naming du service ?
        fatQuantityIndicator.setBackground(getNutrientLevelsQuantityDrawable(fatNutrientLevelsQuantity.toString()));
        saturatedFatQuantityIndicator.setBackground(getNutrientLevelsQuantityDrawable(saturatedFatNutrientLevelsQuantity.toString()));
        sugarsQuantityIndicator.setBackground(getNutrientLevelsQuantityDrawable(sugarsNutrientLevelsQuantity.toString()));
        saltQuantityIndicator.setBackground(getNutrientLevelsQuantityDrawable(saltNutrientLevelsQuantity.toString()));

        fatQuantityDescription.setText(productService.getNutrientLevelsDescription(fatNutrientLevelsQuantity.toString()));
        saturatedFatQuantityDescription.setText(productService.getNutrientLevelsDescription(saturatedFatNutrientLevelsQuantity.toString()));
        sugarsQuantityDescription.setText(productService.getNutrientLevelsDescription(sugarsNutrientLevelsQuantity.toString()));
        saltQuantityDescription.setText(productService.getNutrientLevelsDescription(saltNutrientLevelsQuantity.toString()));

        ArrayList<String> ingredientsAnalysisTags = product.getIngredientsAnalysisTags();
        for (String tag: ingredientsAnalysisTags) {
            if (tag.contains("palm")) setIngredientsAnalysisTextView(isPalmOilFree, tag);
            if (tag.contains("vegan")) setIngredientsAnalysisTextView(isVegan, tag);
            if (tag.contains("vegetarian")) setIngredientsAnalysisTextView(isVegetarian, tag);
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private Drawable getNutriScoreGradeDrawable(String grade) {
        switch (grade) {
            case "a":
                return getResources().getDrawable(R.drawable.ic_nutriscore_a);
            case "b":
                return getResources().getDrawable(R.drawable.ic_nutriscore_b);
            case "c":
                return getResources().getDrawable(R.drawable.ic_nutriscore_c);
            case "d":
                return getResources().getDrawable(R.drawable.ic_nutriscore_d);
            case "e":
                return getResources().getDrawable(R.drawable.ic_nutriscore_e);
            default:
                break;
        }

        return null;
    }

    private void setIngredientsAnalysisTextView(TextView textView, String tag) {
        switch (tag) {
            case "en:palm-oil-free":
                textView.setText("Oui");
                break;
            case "en:vegan":
                textView.setText("Oui");
                break;
            case "en:vegetarian":
                textView.setText("Oui");
                break;
            default:
                textView.setText("Non");
                break;
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private Drawable getNutrientLevelsQuantityDrawable(String level) {
        switch (level) {
            case "low":
                return getResources().getDrawable(R.drawable.green_circle);
            case "moderate":
                return getResources().getDrawable(R.drawable.orange_circle);
            case "high":
                return getResources().getDrawable(R.drawable.red_circle);
            default:
                break;
        }

        return null;
    }

    private void toggleCollapsibleSection(View v, ImageView upArrow, ImageView downArrow, RelativeLayout relativeLayout) {
        upArrow.setVisibility(upArrow.isShown()
                ? View.GONE
                : View.VISIBLE);
        relativeLayout.setVisibility(relativeLayout.isShown()
                ? View.GONE
                : View.VISIBLE);
        downArrow.setVisibility(downArrow.isShown() ? View.GONE : View.VISIBLE);
    }
}