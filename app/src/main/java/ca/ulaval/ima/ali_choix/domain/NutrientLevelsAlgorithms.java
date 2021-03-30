package ca.ulaval.ima.ali_choix.domain;

public class NutrientLevelsAlgorithms {
    public static NutrientQualities evaluateFat(Float fatPer100g) {
        if (fatPer100g <= 3f) {
            return NutrientQualities.GOOD;
        } else if (fatPer100g <= 20f) {
            return NutrientQualities.MODERATE;
        } else {
            return NutrientQualities.BAD;
        }
    }

    public static NutrientQualities evaluateSaturatedFat(Float saturatedFatPer100g) {
        if (saturatedFatPer100g <= 1.5f) {
            return NutrientQualities.GOOD;
        } else if (saturatedFatPer100g <= 5f) {
            return NutrientQualities.MODERATE;
        } else {
            return NutrientQualities.BAD;
        }
    }

    public static NutrientQualities evaluateSugar(Float sugarPer100g) {
        if (sugarPer100g <= 5f) {
            return NutrientQualities.GOOD;
        } else if (sugarPer100g <= 12.5f) {
            return NutrientQualities.MODERATE;
        } else {
            return NutrientQualities.BAD;
        }
    }

    public static NutrientQualities evaluateSalt(Float saltPer100g) {
        if (saltPer100g <= 0.3f) {
            return NutrientQualities.GOOD;
        } else if (saltPer100g <= 1.5f) {
            return NutrientQualities.MODERATE;
        } else {
            return NutrientQualities.BAD;
        }
    }
}
