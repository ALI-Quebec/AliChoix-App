package ca.ulaval.ima.ali_choix.services;

import java.util.HashMap;

import ca.ulaval.ima.ali_choix.domain.DomainConstant;
import ca.ulaval.ima.ali_choix.domain.exceptions.InvalidEcoScoreGradeException;
import ca.ulaval.ima.ali_choix.domain.exceptions.InvalidNutriScoreGradeException;
import ca.ulaval.ima.ali_choix.domain.exceptions.InvalidNutrientLevelsQuantityException;
import ca.ulaval.ima.ali_choix.domain.product.EcoScoreGrade;
import ca.ulaval.ima.ali_choix.domain.product.NutriScoreGrade;
import ca.ulaval.ima.ali_choix.domain.product.NutrientLevelsAlgorithms;
import ca.ulaval.ima.ali_choix.domain.product.NutrientLevelsQuantity;
import ca.ulaval.ima.ali_choix.domain.product.Nutriments;

public class ProductService {

    public ProductService() {
    }

    public String getNutriScoreDescription(String grade) {
        try {
            NutriScoreGrade nutriScoreGrade = NutriScoreGrade.get(grade);

            return nutriScoreGrade.getDescription();
        } catch (InvalidNutriScoreGradeException e) {
            return NutriScoreGrade.UNKNOWN.getDescription();
        }
    }

    public String getEcoScoreDescription(String grade) {
        try {
            EcoScoreGrade ecoScoreGrade = EcoScoreGrade.get(grade);

            return ecoScoreGrade.getDescription();
        } catch (InvalidEcoScoreGradeException e) {
            return EcoScoreGrade.UNKNOWN.getDescription();
        }
    }

    public HashMap getNutrientLevelsQuantity(Nutriments nutriments) {
        HashMap<String, NutrientLevelsQuantity> nutrientLevelsQuantity = new HashMap<>();
        nutrientLevelsQuantity.put(DomainConstant.FAT_NUTRIENT_LEVELS_QUANTITY_KEY, NutrientLevelsAlgorithms.evaluateFat(nutriments.getFat100g()));
        nutrientLevelsQuantity.put(DomainConstant.SATURATED_FAT_NUTRIENT_LEVELS_QUANTITY_KEY, NutrientLevelsAlgorithms.evaluateSaturatedFat(nutriments.getSaturatedFat100g()));
        nutrientLevelsQuantity.put(DomainConstant.SUGARS_NUTRIENT_LEVELS_QUANTITY_KEY, NutrientLevelsAlgorithms.evaluateSugars(nutriments.getSugars100g()));
        nutrientLevelsQuantity.put(DomainConstant.SALT_NUTRIENT_LEVELS_QUANTITY_KEY, NutrientLevelsAlgorithms.evaluateSalt(nutriments.getSalt100g()));

        return nutrientLevelsQuantity;
    }

    public String getNutrientLevelsDescription(String level) {
        try {
            NutrientLevelsQuantity nutrientLevelsQuantity = NutrientLevelsQuantity.get(level);

            return nutrientLevelsQuantity.getDescription();
        } catch (InvalidNutrientLevelsQuantityException e) {
            return NutrientLevelsQuantity.UNKNOWN.getDescription();
        }
    }
}
