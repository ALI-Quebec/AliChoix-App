package ca.ulaval.ima.ali_choix.domain.product;

import java.util.HashMap;
import java.util.Map;

import ca.ulaval.ima.ali_choix.domain.exceptions.InvalidNutrientLevelsQuantityException;

public enum NutrientLevelsQuantity {
    LOW("low", "Quantité faible"),
    MODERATE("moderate", "Quantité modérée"),
    HIGH("high", "Quantité élevée");

    private String quantity;
    private String description;
    private static final Map<String, NutrientLevelsQuantity> lookup = new HashMap<>();

    static {
        for (NutrientLevelsQuantity nutrientLevelsQuantity : NutrientLevelsQuantity.values()) {
            lookup.put(nutrientLevelsQuantity.toString(), nutrientLevelsQuantity);
        }
    }

    NutrientLevelsQuantity(String quantity, String description) {
        this.quantity = quantity;
        this.description = description;
    }

    @Override
    public String toString() {
        return quantity;
    }

    public static NutrientLevelsQuantity get(String quantity) {
        if (quantity == null) throw new InvalidNutrientLevelsQuantityException();

        NutrientLevelsQuantity nutrientLevelsQuantity = lookup.get(quantity.toLowerCase());

        if (nutrientLevelsQuantity == null) throw new InvalidNutrientLevelsQuantityException();

        return nutrientLevelsQuantity;
    }

    public String getDescription() { return description; }
}
