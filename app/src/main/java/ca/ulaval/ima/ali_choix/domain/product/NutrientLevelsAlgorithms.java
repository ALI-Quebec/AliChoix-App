package ca.ulaval.ima.ali_choix.domain.product;

public class NutrientLevelsAlgorithms {
    private static final Float LOW_FAT_VALUE = 3f;
    private static final Float MODERATE_FAT_VALUE = 20f;
    private static final Float LOW_SATURATED_FAT_VALUE = 1.5f;
    private static final Float MODERATE_SATURATED_FAT_VALUE = 5f;
    private static final Float LOW_SUGARS_VALUE = 5f;
    private static final Float MODERATE_SUGARS_VALUE = 12.5f;
    private static final Float LOW_SALT_VALUE = 0.3f;
    private static final Float MODERATE_SALT_VALUE = 1.5f;

    public static NutrientLevelsQuantity evaluateFat(Float fatPer100g) {
        if (fatPer100g <= LOW_FAT_VALUE) {
            return NutrientLevelsQuantity.LOW;
        } else if (fatPer100g <= MODERATE_FAT_VALUE) {
            return NutrientLevelsQuantity.MODERATE;
        } else {
            return NutrientLevelsQuantity.HIGH;
        }
    }

    public static NutrientLevelsQuantity evaluateSaturatedFat(Float saturatedFatPer100g) {
        if (saturatedFatPer100g <= LOW_SATURATED_FAT_VALUE) {
            return NutrientLevelsQuantity.LOW;
        } else if (saturatedFatPer100g <= MODERATE_SATURATED_FAT_VALUE) {
            return NutrientLevelsQuantity.MODERATE;
        } else {
            return NutrientLevelsQuantity.HIGH;
        }
    }

    public static NutrientLevelsQuantity evaluateSugars(Float sugarsPer100g) {
        if (sugarsPer100g <= LOW_SUGARS_VALUE) {
            return NutrientLevelsQuantity.LOW;
        } else if (sugarsPer100g <= MODERATE_SUGARS_VALUE) {
            return NutrientLevelsQuantity.MODERATE;
        } else {
            return NutrientLevelsQuantity.HIGH;
        }
    }

    public static NutrientLevelsQuantity evaluateSalt(Float saltPer100g) {
        if (saltPer100g <= LOW_SALT_VALUE) {
            return NutrientLevelsQuantity.LOW;
        } else if (saltPer100g <= MODERATE_SALT_VALUE) {
            return NutrientLevelsQuantity.MODERATE;
        } else {
            return NutrientLevelsQuantity.HIGH;
        }
    }
}
