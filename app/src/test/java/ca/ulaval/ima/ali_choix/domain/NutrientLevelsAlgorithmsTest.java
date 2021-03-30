package ca.ulaval.ima.ali_choix.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class NutrientLevelsAlgorithmsTest {
    @Test
    public void lowFatLevel() {
        assertEquals(TrafficLights.GREEN, NutrientLevelsAlgorithms.evaluateFat(1.1f));
    }

    @Test
    public void mediumFatLevel() {
        assertEquals(TrafficLights.YELLOW, NutrientLevelsAlgorithms.evaluateFat(6f));
    }

    @Test
    public void highFatLevel() {
        assertEquals(TrafficLights.RED, NutrientLevelsAlgorithms.evaluateFat(25f));
    }

    @Test
    public void lowSaturatedFatLevel() {
        assertEquals(TrafficLights.GREEN, NutrientLevelsAlgorithms.evaluateSaturatedFat(1f));
    }

    @Test
    public void mediumSaturatedFatLevel() {
        assertEquals(TrafficLights.YELLOW, NutrientLevelsAlgorithms.evaluateSaturatedFat(4.5f));
    }

    @Test
    public void highSaturatedFatLevel() {
        assertEquals(TrafficLights.RED, NutrientLevelsAlgorithms.evaluateSaturatedFat(10f));
    }

    @Test
    public void lowSugarLevel() {
        assertEquals(TrafficLights.GREEN, NutrientLevelsAlgorithms.evaluateSugar(4f));
    }

    @Test
    public void mediumSugarLevel() {
        assertEquals(TrafficLights.YELLOW, NutrientLevelsAlgorithms.evaluateSugar(9.5f));
    }

    @Test
    public void highSugarLevel() {
        assertEquals(TrafficLights.RED, NutrientLevelsAlgorithms.evaluateSugar(13f));
    }

    @Test
    public void lowSaltLevel() {
        assertEquals(TrafficLights.GREEN, NutrientLevelsAlgorithms.evaluateSalt(0.3f));
    }

    @Test
    public void mediumSaltLevel() {
        assertEquals(TrafficLights.YELLOW, NutrientLevelsAlgorithms.evaluateSalt(1.4f));
    }

    @Test
    public void highSaltLevel() {
        assertEquals(TrafficLights.RED, NutrientLevelsAlgorithms.evaluateSalt(2f));
    }
}
