package ca.ulaval.ima.ali_choix.domain;

public class NutriScoreAlgorithms {
    public static TrafficLights evalFat(Float fatPer100g) {
        if (fatPer100g <= 3) {
            return TrafficLights.GREEN;
        } else if (fatPer100g <= 20) {
            return TrafficLights.YELLOW;
        } else {
            return TrafficLights.RED;
        }
    }

    public static TrafficLights evalSaturatedFat(Float saturatedFatPer100g) {
        if (saturatedFatPer100g <= 1.5) {
            return TrafficLights.GREEN;
        } else if (saturatedFatPer100g <= 5) {
            return TrafficLights.YELLOW;
        } else {
            return TrafficLights.RED;
        }
    }

    public static TrafficLights evalSugar(Float sugarPer100g) {
        if (sugarPer100g <= 5) {
            return TrafficLights.GREEN;
        } else if (sugarPer100g <= 12.5) {
            return TrafficLights.YELLOW;
        } else {
            return TrafficLights.RED;
        }
    }

    public static TrafficLights evalSalt(Float saltPer100g) {
        if (saltPer100g <= 0.3) {
            return TrafficLights.GREEN;
        } else if (saltPer100g <= 1.5) {
            return TrafficLights.YELLOW;
        } else {
            return TrafficLights.RED;
        }
    }
}
