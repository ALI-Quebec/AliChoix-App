package ca.ulaval.ima.ali_choix.services;

import android.app.Service;

import java.util.HashMap;

import ca.ulaval.ima.ali_choix.domain.NutriScoreGrade;
import ca.ulaval.ima.ali_choix.domain.NutrientLevelsAlgorithms;
import ca.ulaval.ima.ali_choix.domain.NutrientLevelsQuantity;
import ca.ulaval.ima.ali_choix.domain.Nutriments;

public abstract class ProductService extends Service {

    public ProductService(){}

    public String getNutriScoreDescription(String grade) {
        NutriScoreGrade nutriScoreGrade = NutriScoreGrade.get(grade);
        return nutriScoreGrade.getDescription();
    }

    public HashMap getNutrientLevelsQuantity(Nutriments nutriments) {
        HashMap<String, NutrientLevelsQuantity> nutrientLevelsQuantity = new HashMap<>();

        nutrientLevelsQuantity.put("fatNutrientLevelsQuantity", NutrientLevelsAlgorithms.evaluateFat(nutriments.getFat100g()));
        nutrientLevelsQuantity.put("saturatedFatNutrientLevelsQuantity", NutrientLevelsAlgorithms.evaluateSaturatedFat(nutriments.getSaturatedFat100g()));
        nutrientLevelsQuantity.put("sugarNutrientLevelsQuantity", NutrientLevelsAlgorithms.evaluateSugar(nutriments.getSugar100g()));
        nutrientLevelsQuantity.put("saltNutrientLevelsQuantity", NutrientLevelsAlgorithms.evaluateSalt(nutriments.getSalt100g()));

        return nutrientLevelsQuantity;
    }
}
