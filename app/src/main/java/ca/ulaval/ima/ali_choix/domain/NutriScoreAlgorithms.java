package ca.ulaval.ima.ali_choix.domain;

public class NutriScoreAlgorithms {
    public static TrafficLights getSc(Float fatPer100g) {
        if (fatPer100g <= 3) {
            return TrafficLights.GREEN;
        } else if (fatPer100g <= 20) {
            return TrafficLights.YELLOW;
        } else {
            return TrafficLights.RED;
        }
    }
}
