package ca.ulaval.ima.ali_choix.domain.product;

import java.util.ArrayList;

import ca.ulaval.ima.ali_choix.domain.DomainConstant;
import kotlin.text.UStringsKt;

public class Product {
    private String image_front_url;
    private String origins;
    private String countries_imported;
    private String product_quantity;
    private String product_name_en;
    private String product_name_fr;
    private String nutriscore_grade;
    private Nutriments nutriments;
    private ArrayList<String> ingredients_analysis_tags;
    private String ecoscore_grade;

    public String getImage() {
        return image_front_url;
    }

    public void setImage(String image_front_url) {
        this.image_front_url = image_front_url;
    }

    public String getOrigin() {
        if (origins != null) return origins;

        return DomainConstant.INFORMATION_NOT_COMPUTED;
    }

    public void setOrigins(String origins) {
        this.origins = origins;
    }

    public String getCountryImported() {
        if (countries_imported != null) return countries_imported;

        return DomainConstant.INFORMATION_NOT_COMPUTED;
    }

    public void setCountriesImported(String countries_imported) {
        this.countries_imported = countries_imported;
    }

    public String getQuantity() {
        if (product_quantity != null) return product_quantity + " g";

        return DomainConstant.INFORMATION_NOT_COMPUTED;
    }

    public void setProductQuantity(String product_quantity) {
        this.product_quantity = product_quantity;
    }

    public String getName() {
        if (product_name_en != null && !product_name_en.trim().equals("")) return product_name_en;
        if (product_name_fr != null && !product_name_fr.trim().equals("")) return product_name_fr;

        return DomainConstant.INFORMATION_NOT_COMPUTED;
    }

    public void setEnglishName(String product_name_en) {
        this.product_name_en = product_name_en;
    }
    
    public void setFrenchName(String product_name_fr) { this.product_name_fr = product_name_fr; }

    public String getNutriScoreGrade() {
        if (nutriscore_grade == null) return NutriScoreGrade.NOT_COMPUTED.toString();

        return nutriscore_grade;
    }

    public void setNutriScoreGrade(String nutriscore_grade) { this.nutriscore_grade = nutriscore_grade; }

    public Nutriments getNutriments() { return nutriments; }

    public void setNutriments(Nutriments nutriments) {  this.nutriments = nutriments; }

    public ArrayList<String> getIngredientsAnalysisTags() { return ingredients_analysis_tags; }

    public void setIngredientsAnalysisTags(ArrayList<String> ingredients_analysis_tags) { this.ingredients_analysis_tags = ingredients_analysis_tags; }

    public void setEcoScoreGrade(String ecoscore_grade) { this.ecoscore_grade = ecoscore_grade; }

    public String getEcoScoreGrade() {
        if (ecoscore_grade == null) return EcoScoreGrade.UNKNOWN.toString();

        return ecoscore_grade;
    }
}
