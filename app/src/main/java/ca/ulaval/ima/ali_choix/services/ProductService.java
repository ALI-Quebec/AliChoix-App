package ca.ulaval.ima.ali_choix.services;

import android.app.Service;

import java.util.HashMap;

import ca.ulaval.ima.ali_choix.domain.DomainConstant;
import ca.ulaval.ima.ali_choix.domain.product.NutriScoreGrade;
import ca.ulaval.ima.ali_choix.domain.product.NutrientLevelsAlgorithms;
import ca.ulaval.ima.ali_choix.domain.product.NutrientLevelsQuantity;
import ca.ulaval.ima.ali_choix.domain.product.Nutriments;

public abstract class ProductService extends Service {

    public ProductService(){}

    public String getNutriScoreDescription(String grade) {
        NutriScoreGrade nutriScoreGrade = NutriScoreGrade.get(grade);
        return nutriScoreGrade.getDescription();
    }

    public HashMap getNutrientLevelsQuantity(Nutriments nutriments) {
        HashMap<String, NutrientLevelsQuantity> nutrientLevelsQuantity = new HashMap<>();

        nutrientLevelsQuantity.put(DomainConstant.FAT_NUTRIENT_LEVELS_QUANTITY, NutrientLevelsAlgorithms.evaluateFat(nutriments.getFat100g()));
        nutrientLevelsQuantity.put(DomainConstant.SATURATED_FAT_NUTRIENT_LEVELS_QUANTITY, NutrientLevelsAlgorithms.evaluateSaturatedFat(nutriments.getSaturatedFat100g()));
        nutrientLevelsQuantity.put(DomainConstant.SUGARS_NUTRIENT_LEVELS_QUANTITY, NutrientLevelsAlgorithms.evaluateSugars(nutriments.getSugars100g()));
        nutrientLevelsQuantity.put(DomainConstant.SALT_NUTRIENT_LEVELS_QUANTITY, NutrientLevelsAlgorithms.evaluateSalt(nutriments.getSalt100g()));

        return nutrientLevelsQuantity;
    }

    public String getNutrientLevelsDescription(String level) {
        NutrientLevelsQuantity nutrientLevelsQuantity = NutrientLevelsQuantity.get(level);
        return nutrientLevelsQuantity.getDescription();
    }
}
