package ca.ulaval.ima.ali_choix.domain;

public class NutrientLevelsAlgorithms {
    public static NutrientQuality evaluateFat(Float fatPer100g) {
        if (fatPer100g <= 3f) {
            return NutrientQuality.GOOD;
        } else if (fatPer100g <= 20f) {
            return NutrientQuality.MODERATE;
        } else {
            return NutrientQuality.BAD;
        }
    }

    public static NutrientQuality evaluateSaturatedFat(Float saturatedFatPer100g) {
        if (saturatedFatPer100g <= 1.5f) {
            return NutrientQuality.GOOD;
        } else if (saturatedFatPer100g <= 5f) {
            return NutrientQuality.MODERATE;
        } else {
            return NutrientQuality.BAD;
        }
    }

    public static NutrientQuality evaluateSugar(Float sugarPer100g) {
        if (sugarPer100g <= 5f) {
            return NutrientQuality.GOOD;
        } else if (sugarPer100g <= 12.5f) {
            return NutrientQuality.MODERATE;
        } else {
            return NutrientQuality.BAD;
        }
    }

    public static NutrientQuality evaluateSalt(Float saltPer100g) {
        if (saltPer100g <= 0.3f) {
            return NutrientQuality.GOOD;
        } else if (saltPer100g <= 1.5f) {
            return NutrientQuality.MODERATE;
        } else {
            return NutrientQuality.BAD;
        }
    }
}
