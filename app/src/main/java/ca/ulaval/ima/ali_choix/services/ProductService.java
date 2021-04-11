package ca.ulaval.ima.ali_choix.services;

import android.app.Service;

import java.util.HashMap;

import ca.ulaval.ima.ali_choix.domain.GlobalConstant;
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

        nutrientLevelsQuantity.put(GlobalConstant.FAT_NUTRIENT_LEVELS_QUANTITY, NutrientLevelsAlgorithms.evaluateFat(nutriments.getFat100g()));
        nutrientLevelsQuantity.put(GlobalConstant.SATURATED_FAT_NUTRIENT_LEVELS_QUANTITY, NutrientLevelsAlgorithms.evaluateSaturatedFat(nutriments.getSaturatedFat100g()));
        nutrientLevelsQuantity.put(GlobalConstant.SUGARS_NUTRIENT_LEVELS_QUANTITY, NutrientLevelsAlgorithms.evaluateSugars(nutriments.getSugars100g()));
        nutrientLevelsQuantity.put(GlobalConstant.SALT_NUTRIENT_LEVELS_QUANTITY, NutrientLevelsAlgorithms.evaluateSalt(nutriments.getSalt100g()));

        return nutrientLevelsQuantity;
    }

    public String getNutrientLevelsDescription(String level) {
        NutrientLevelsQuantity nutrientLevelsQuantity = NutrientLevelsQuantity.get(level);
        return nutrientLevelsQuantity.getDescription();
    }
}
