package ca.ulaval.ima.ali_choix.domain;

public class NutrientLevelsAlgorithms {
    public static NutrientLevelsQuantity evaluateFat(Float fatPer100g) {
        if (fatPer100g <= 3f) {
            return NutrientLevelsQuantity.LOW;
        } else if (fatPer100g <= 20f) {
            return NutrientLevelsQuantity.MODERATE;
        } else {
            return NutrientLevelsQuantity.HIGH;
        }
    }

    public static NutrientLevelsQuantity evaluateSaturatedFat(Float saturatedFatPer100g) {
        if (saturatedFatPer100g <= 1.5f) {
            return NutrientLevelsQuantity.LOW;
        } else if (saturatedFatPer100g <= 5f) {
            return NutrientLevelsQuantity.MODERATE;
        } else {
            return NutrientLevelsQuantity.HIGH;
        }
    }

    public static NutrientLevelsQuantity evaluateSugar(Float sugarPer100g) {
        if (sugarPer100g <= 5f) {
            return NutrientLevelsQuantity.LOW;
        } else if (sugarPer100g <= 12.5f) {
            return NutrientLevelsQuantity.MODERATE;
        } else {
            return NutrientLevelsQuantity.HIGH;
        }
    }

    public static NutrientLevelsQuantity evaluateSalt(Float saltPer100g) {
        if (saltPer100g <= 0.3f) {
            return NutrientLevelsQuantity.LOW;
        } else if (saltPer100g <= 1.5f) {
            return NutrientLevelsQuantity.MODERATE;
        } else {
            return NutrientLevelsQuantity.HIGH;
        }
    }
}
