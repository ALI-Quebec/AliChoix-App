package ca.ulaval.ima.ali_choix.ui.scannedproduct;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import ca.ulaval.ima.ali_choix.domain.DomainConstant;
import ca.ulaval.ima.ali_choix.domain.exceptions.HistoryEmptyException;
import ca.ulaval.ima.ali_choix.services.HistoryService;

import ca.ulaval.ima.ali_choix.domain.product.NutrientLevelsQuantity;
import ca.ulaval.ima.ali_choix.domain.product.Nutriments;

import ca.ulaval.ima.ali_choix.services.ProductService;
import ca.ulaval.ima.ali_choix.services.ServiceLocator;
import ca.ulaval.ima.ali_choix.ui.dialog.DialogFromProductToScanFragment;
import ca.ulaval.ima.ali_choix.ui.UIConstant;
import cz.msebera.android.httpclient.Header;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import ca.ulaval.ima.ali_choix.R;
import ca.ulaval.ima.ali_choix.domain.product.Product;
import ca.ulaval.ima.ali_choix.network.OpenFoodFactRestClient;

import static ca.ulaval.ima.ali_choix.ui.UIConstant.DIALOG_MESSAGE_KEY;
import static ca.ulaval.ima.ali_choix.ui.UIConstant.NO_PRODUCT_SCAN_YET_MESSAGE;
import static ca.ulaval.ima.ali_choix.ui.UIConstant.PRODUCT_ID_KEY;
import static ca.ulaval.ima.ali_choix.ui.UIConstant.PRODUCT_NOT_FOUND_MESSAGE;

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
    private String scannedProductNutriScoreGrade;
    private RelativeLayout nutriScoreLayout;
    private ImageView nutriScoreDrawable;
    private TextView nutriScoreDescription;
    private RelativeLayout ingredientsAnalysisCollapsible;
    private ImageView ingredientAnalysisDownArrow;
    private ImageView ingredientAnalysisUpArrow;
    private RelativeLayout ingredientsAnalysisLayout;
    private TextView isVegetarian;
    private TextView isVegan;
    private TextView isPalmOilFree;
    private RelativeLayout nutrientLevelsCollapsible;
    private RelativeLayout nutrientLevelsLayout;
    private ImageView nutrientLevelsDownArrow;
    private ImageView nutrientLevelsUpArrow;
    private ImageView fatQuantityIndicator;
    private ImageView saturatedFatQuantityIndicator;
    private ImageView sugarsQuantityIndicator;
    private ImageView saltQuantityIndicator;
    private TextView fatQuantityDescription;
    private TextView saturatedFatQuantityDescription;
    private TextView sugarsQuantityDescription;
    private TextView saltQuantityDescription;
    private RelativeLayout nutritionFactsCollapsibleSection;
    private RelativeLayout nutritionFactsLayout;
    private ImageView nutritionFactsDownArrow;
    private ImageView nutritionFactsUpArrow;
    private TextView nutritionFactsEnergyKj;
    private TextView nutritionFactsEnergyKcal;
    private TextView nutritionFactsFat;
    private TextView nutritionFactsSaturatedFat;
    private TextView nutritionFactsCarbohydrates;
    private TextView nutritionFactsSugars;
    private TextView nutritionFactsFibers;
    private TextView nutritionFactsMagnesium;
    private TextView nutritionFactsProteins;
    private TextView nutritionFactsSalt;
    private TextView nutritionFactsSodium;
    private TextView nutritionFactsAlcohol;
    private TextView nutritionFactsIron;
    private HistoryService historyService;
    private RelativeLayout ecoScoreCollapsibleSection;
    private RelativeLayout ecoScoreLayout;
    private ImageView ecoScoreDownArrow;
    private ImageView ecoScoreUpArrow;
    private ImageView ecoScoreDrawable;
    private TextView ecoScoreDescription;
    private String scannedProductEcoScoreGrade;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_scanned_product, container, false);

        historyService = (HistoryService) ServiceLocator.getInstance().get(HistoryService.class);

        assignVisualElements(root);

        if(getArguments() != null){
            String productId = getArguments().getString(PRODUCT_ID_KEY);

            if (productId == null){
                productId = "";
            }

            getInformationsWithOpenFoodFact(getArguments().getString(PRODUCT_ID_KEY));
        } else {
            try {
                String productId = historyService.getLastSearchedProductId();
                getInformationsWithOpenFoodFact(productId);
            } catch (HistoryEmptyException e) {
                DialogFragment dialog = new DialogFromProductToScanFragment();
                Bundle bundle = new Bundle();
                bundle.putString(DIALOG_MESSAGE_KEY, NO_PRODUCT_SCAN_YET_MESSAGE);
                dialog.setArguments(bundle);
                dialog.show(getFragmentManager(), "DialogFromProductToScanFragment");
            }
        }

        return root;
    }

    private void getInformationsWithOpenFoodFact(String productId) {
        OpenFoodFactRestClient OFFClient = new OpenFoodFactRestClient();
        OFFClient.get(productId, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject dataObject) {
                try {
                    JSONObject productJson = (JSONObject) dataObject.get("product");
                    Gson gson = new Gson();
                    product = gson.fromJson(String.valueOf(productJson), Product.class);
                    showInformations();
                    historyService.addHistoryElement(productId,product.getImage(),product.getName());
                } catch (JSONException e) {
                    e.printStackTrace();
                    DialogFragment dialog = new DialogFromProductToScanFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString(DIALOG_MESSAGE_KEY, PRODUCT_NOT_FOUND_MESSAGE);
                    dialog.setArguments(bundle);
                    dialog.show(getFragmentManager(), "DialogFromProductToScanFragment");
                }
            }
        });
    }

    @SuppressLint({"UseCompatLoadingForDrawables", "SetTextI18n", "DefaultLocale"})
    private void showInformations() {
        ProductService productService = new ProductService();
        String productName = product.getName();
        String imageUrl = product.getImage();
        String origin = product.getOrigins();
        String countryImported = product.getCountriesImported();
        String quantity = product.getQuantity();
        Nutriments nutriments = product.getNutriments();

        if (imageUrl != null) {
            Picasso.get().load(imageUrl).into(scannedProductImage);
        } else {
            scannedProductImage.setBackground(getResources().getDrawable(R.drawable.no_image_available));
        };

        scannedProductOrigin.setText(origin);
        scannedProductCountryImported.setText(countryImported);
        scannedProductQuantity.setText(quantity.equals(UIConstant.UNKNOWN) ? quantity : quantity+ " g");
        scannedProductName.setText(productName);

        scannedProductNutriScoreGrade = product.getNutriScoreGrade();
        nutriScoreDrawable.setBackground(getNutriScoreGradeDrawable(scannedProductNutriScoreGrade.toLowerCase()));
        nutriScoreDescription.setText(productService.getNutriScoreDescription(scannedProductNutriScoreGrade.toLowerCase()));

        scannedProductEcoScoreGrade = product.getEcoScoreGrade();
        ecoScoreDrawable.setBackground(getEcoScoreGradeDrawable(scannedProductEcoScoreGrade.toLowerCase()));
        ecoScoreDescription.setText(productService.getEcoScoreDescription(scannedProductEcoScoreGrade.toLowerCase()));

        HashMap nutrientLevels = productService.getNutrientLevelsQuantity(nutriments);
        NutrientLevelsQuantity fatNutrientLevelsQuantity = (NutrientLevelsQuantity) nutrientLevels.get(DomainConstant.FAT_NUTRIENT_LEVELS_QUANTITY_KEY);
        NutrientLevelsQuantity saturatedFatNutrientLevelsQuantity = (NutrientLevelsQuantity) nutrientLevels.get(DomainConstant.SATURATED_FAT_NUTRIENT_LEVELS_QUANTITY_KEY);
        NutrientLevelsQuantity sugarsNutrientLevelsQuantity = (NutrientLevelsQuantity) nutrientLevels.get(DomainConstant.SUGARS_NUTRIENT_LEVELS_QUANTITY_KEY);
        NutrientLevelsQuantity saltNutrientLevelsQuantity = (NutrientLevelsQuantity) nutrientLevels.get(DomainConstant.SALT_NUTRIENT_LEVELS_QUANTITY_KEY);

        fatQuantityIndicator.setBackground(getNutrientLevelsQuantityDrawable(fatNutrientLevelsQuantity.toString()));
        saturatedFatQuantityIndicator.setBackground(getNutrientLevelsQuantityDrawable(saturatedFatNutrientLevelsQuantity.toString()));
        sugarsQuantityIndicator.setBackground(getNutrientLevelsQuantityDrawable(sugarsNutrientLevelsQuantity.toString()));
        saltQuantityIndicator.setBackground(getNutrientLevelsQuantityDrawable(saltNutrientLevelsQuantity.toString()));

        fatQuantityDescription.setText(productService.getNutrientLevelsDescription(fatNutrientLevelsQuantity.toString()));
        saturatedFatQuantityDescription.setText(productService.getNutrientLevelsDescription(saturatedFatNutrientLevelsQuantity.toString()));
        sugarsQuantityDescription.setText(productService.getNutrientLevelsDescription(sugarsNutrientLevelsQuantity.toString()));
        saltQuantityDescription.setText(productService.getNutrientLevelsDescription(saltNutrientLevelsQuantity.toString()));

        ArrayList<String> ingredientsAnalysisTags = product.getIngredientsAnalysisTags();
        if (ingredientsAnalysisTags != null) {
            for (String tag : ingredientsAnalysisTags) {
                if (tag.contains(UIConstant.PALM))
                    setIngredientsAnalysisTextView(isPalmOilFree, tag);
                if (tag.contains(UIConstant.VEGAN)) setIngredientsAnalysisTextView(isVegan, tag);
                if (tag.contains(UIConstant.VEGETARIAN))
                    setIngredientsAnalysisTextView(isVegetarian, tag);
            }
        }

        setNutritionFactsText(nutritionFactsEnergyKj, nutriments.getEnergyKj100g(), " kj");
        setNutritionFactsText(nutritionFactsEnergyKcal, nutriments.getEnergyKcal100g(), " kcal");
        setNutritionFactsText(nutritionFactsFat, nutriments.getFat100g()," g");
        setNutritionFactsText(nutritionFactsSaturatedFat, nutriments.getSaturatedFat100g(), " g");
        setNutritionFactsText(nutritionFactsCarbohydrates, nutriments.getCarbohydrates100g()," g");
        setNutritionFactsText(nutritionFactsSugars, nutriments.getSugars100g()," g");
        setNutritionFactsText(nutritionFactsFibers, nutriments.getFibers100g()," g");
        setNutritionFactsText(nutritionFactsProteins, nutriments.getProteins100g(), " g");
        setNutritionFactsText(nutritionFactsSalt, nutriments.getSalt100g(), " g");
        setNutritionFactsText(nutritionFactsSodium, nutriments.getSodium100g()," g");
        setNutritionFactsText(nutritionFactsAlcohol, nutriments.getAlcohol100g(), " % vol");
        setNutritionFactsText(nutritionFactsIron, nutriments.calculateToMilligram(nutriments.getIron100g()), " mg");
        setNutritionFactsText(nutritionFactsMagnesium, nutriments.calculateToMilligram(nutriments.getMagnesium100g()), " mg");
    }

    private void setNutritionFactsText(TextView textView, Float value, String type){
        DecimalFormat decimalFormat = new DecimalFormat("#.#");

        if (value == null){
            textView.setText(UIConstant.UNKNOWN);
        } else {
            textView.setText(decimalFormat.format(value)+type);
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
            case "unknown":
            default:
                return getResources().getDrawable(R.drawable.ic_nutriscore_unknown);
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private Drawable getEcoScoreGradeDrawable(String grade) {
        switch (grade) {
            case "a":
                return getResources().getDrawable(R.drawable.ic_ecoscore_a);
            case "b":
                return getResources().getDrawable(R.drawable.ic_ecoscore_b);
            case "c":
                return getResources().getDrawable(R.drawable.ic_ecoscore_c);
            case "d":
                return getResources().getDrawable(R.drawable.ic_ecoscore_d);
            case "e":
                return getResources().getDrawable(R.drawable.ic_ecoscore_e);
            default:
                return getResources().getDrawable(R.drawable.ic_ecoscore_unknown);
        }
    }

    private void setIngredientsAnalysisTextView(TextView textView, String tag) {
        switch (tag) {
            case "en:palm-oil-free":
            case "en:vegan":
            case "en:vegetarian":
                textView.setText("Oui");
                break;
            case "en:palm-oil-content-unknown":
            case "en:vegan-status-unknown":
            case "en:vegetarian-status-unknown":
                textView.setText(UIConstant.UNKNOWN);
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
            case "unknown":
            default:
                return getResources().getDrawable(R.drawable.gray_circle);
        }
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

    private void assignVisualElements(View root) {
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

        nutriScoreDownArrow.setVisibility(View.GONE);

        ingredientsAnalysisCollapsible = root.findViewById(R.id.collapsible_diet_section);
        ingredientsAnalysisLayout = root.findViewById(R.id.diet_type_layout);
        ingredientAnalysisDownArrow = root.findViewById(R.id.diet_down_arrow);
        ingredientAnalysisUpArrow = root.findViewById(R.id.diet_up_arrow);
        isVegetarian = root.findViewById(R.id.is_vegeterian);
        isVegan = root.findViewById(R.id.is_vegan);
        isPalmOilFree = root.findViewById(R.id.is_palm_oil_free);

        ingredientAnalysisUpArrow.setVisibility(View.GONE);
        ingredientsAnalysisLayout.setVisibility(View.GONE);

        nutrientLevelsCollapsible = root.findViewById(R.id.collapsible_nutrient_levels_section);
        nutrientLevelsLayout = root.findViewById(R.id.nutrient_levels_layout);
        nutrientLevelsDownArrow = root.findViewById(R.id.nutrient_levels_down_arrow);
        nutrientLevelsUpArrow = root.findViewById(R.id.nutrient_levels_up_arrow);

        nutrientLevelsUpArrow.setVisibility(View.GONE);
        nutrientLevelsLayout.setVisibility(View.GONE);

        fatQuantityIndicator = root.findViewById(R.id.fat_quantity_indicator);
        saturatedFatQuantityIndicator = root.findViewById(R.id.saturated_fat_quantity_indicator);
        sugarsQuantityIndicator = root.findViewById(R.id.sugars_indicator);
        saltQuantityIndicator = root.findViewById(R.id.salt_indicator);
        fatQuantityDescription = root.findViewById(R.id.fat_quantity);
        saturatedFatQuantityDescription = root.findViewById(R.id.saturated_fat_quantity);
        sugarsQuantityDescription = root.findViewById(R.id.sugars_quantity);
        saltQuantityDescription = root.findViewById(R.id.salt_quantity);

        nutritionFactsCollapsibleSection = root.findViewById(R.id.collapsible_nutrition_facts_section);
        nutritionFactsLayout = root.findViewById(R.id.nutrition_facts_layout);
        nutritionFactsDownArrow = root.findViewById(R.id.nutrition_facts_down_arrow);
        nutritionFactsUpArrow = root.findViewById(R.id.nutrition_facts_up_arrow);

        nutritionFactsUpArrow.setVisibility(View.GONE);
        nutritionFactsLayout.setVisibility(View.GONE);

        nutritionFactsEnergyKj = root.findViewById(R.id.nutrition_facts_energy_kj_value);
        nutritionFactsEnergyKcal = root.findViewById(R.id.nutrition_facts_energy_kcal_value);
        nutritionFactsFat = root.findViewById(R.id.nutrition_facts_fat_value);
        nutritionFactsSaturatedFat = root.findViewById(R.id.nutrition_facts_saturated_fat_value);
        nutritionFactsCarbohydrates = root.findViewById(R.id.nutrition_facts_carbohydrates_value);
        nutritionFactsSugars = root.findViewById(R.id.nutrition_facts_sugars_value);
        nutritionFactsMagnesium = root.findViewById(R.id.nutrition_facts_magnesium_value);
        nutritionFactsProteins = root.findViewById(R.id.nutrition_facts_proteins_value);
        nutritionFactsSalt = root.findViewById(R.id.nutrition_facts_salt_value);
        nutritionFactsFibers = root.findViewById(R.id.nutrition_facts_fibers_value);
        nutritionFactsSodium = root.findViewById(R.id.nutrition_facts_sodium_value);
        nutritionFactsAlcohol = root.findViewById(R.id.nutrition_facts_alcohol_value);
        nutritionFactsIron = root.findViewById(R.id.nutrition_facts_iron_value);

        ecoScoreCollapsibleSection = root.findViewById(R.id.collapsible_eco_score_section);
        ecoScoreLayout = root.findViewById(R.id.eco_score_layout);
        ecoScoreDownArrow = root.findViewById(R.id.eco_score_down_arrow);
        ecoScoreUpArrow = root.findViewById(R.id.eco_score_up_arrow);
        ecoScoreDrawable = root.findViewById(R.id.eco_score_drawable);
        ecoScoreDescription = root.findViewById(R.id.eco_score_description);

        ecoScoreUpArrow.setVisibility(View.GONE);
        ecoScoreLayout.setVisibility(View.GONE);

        nutriScoreCollapsible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleCollapsibleSection(v, nutriScoreUpArrow, nutriScoreDownArrow, nutriScoreLayout);
            }
        });

        ingredientsAnalysisCollapsible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleCollapsibleSection(v, ingredientAnalysisUpArrow, ingredientAnalysisDownArrow, ingredientsAnalysisLayout);
            }
        });

        nutrientLevelsCollapsible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleCollapsibleSection(v, nutrientLevelsUpArrow, nutrientLevelsDownArrow, nutrientLevelsLayout);
            }
        });

        nutritionFactsCollapsibleSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleCollapsibleSection(v, nutritionFactsUpArrow, nutritionFactsDownArrow, nutritionFactsLayout);
            }
        });

        ecoScoreCollapsibleSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleCollapsibleSection(v, ecoScoreUpArrow, ecoScoreDownArrow, ecoScoreLayout);
            }
        });
    }
}