package ca.ulaval.ima.ali_choix.domain;

import org.junit.Test;

import ca.ulaval.ima.ali_choix.domain.product.NutrientLevelsAlgorithms;
import ca.ulaval.ima.ali_choix.domain.product.NutrientLevelsQuantity;

import static org.junit.Assert.*;

public class NutrientLevelsAlgorithmsTest {
    @Test
    public void givenLowFatLevel_whenEvaluatingFat_thenReturnLowNutrientLevelsQuantity() {
        assertEquals(NutrientLevelsQuantity.LOW, NutrientLevelsAlgorithms.evaluateFat(1.1f));
    }

    @Test
    public void givenMediumFatLevel_whenEvaluatingFat_thenReturnModerateNutrientLevelsQuantity() {
        assertEquals(NutrientLevelsQuantity.MODERATE, NutrientLevelsAlgorithms.evaluateFat(6f));
    }

    @Test
    public void givenHighFatLevel_whenEvaluatingFat_thenReturnHighNutrientLevelsQuantity() {
        assertEquals(NutrientLevelsQuantity.HIGH, NutrientLevelsAlgorithms.evaluateFat(25f));
    }

    @Test
    public void givenLowSaturatedFatLevel_whenEvaluatingSaturatedFat_thenReturnLowNutrientLevelsQuantity() {
        assertEquals(NutrientLevelsQuantity.LOW, NutrientLevelsAlgorithms.evaluateSaturatedFat(1f));
    }

    @Test
    public void givenMediumSaturatedFatLevel_whenEvaluatingSaturatedFat_thenReturnModerateNutrientLevelsQuantity() {
        assertEquals(NutrientLevelsQuantity.MODERATE, NutrientLevelsAlgorithms.evaluateSaturatedFat(4.5f));
    }

    @Test
    public void givenHighSaturatedFatLevel_whenEvaluatingSaturatedFat_thenReturnHighNutrientLevelsQuantity() {
        assertEquals(NutrientLevelsQuantity.HIGH, NutrientLevelsAlgorithms.evaluateSaturatedFat(10f));
    }

    @Test
    public void givenLowSugarsLevel_whenEvaluatingSugars_thenReturnLowNutrientLevelsQuantity() {
        assertEquals(NutrientLevelsQuantity.LOW, NutrientLevelsAlgorithms.evaluateSugars(4f));
    }

    @Test
    public void givenMediumSugarsLevel_whenEvaluatingSugars_thenReturnModerateNutrientLevelsQuantity() {
        assertEquals(NutrientLevelsQuantity.MODERATE, NutrientLevelsAlgorithms.evaluateSugars(9.5f));
    }

    @Test
    public void givenHighSugarsLevel_whenEvaluatingSugars_thenReturnHighNutrientLevelsQuantity() {
        assertEquals(NutrientLevelsQuantity.HIGH, NutrientLevelsAlgorithms.evaluateSugars(13f));
    }

    @Test
    public void givenLowSaltLevel_whenEvaluatingSalt_thenReturnLowNutrientLevelsQuantity() {
        assertEquals(NutrientLevelsQuantity.LOW, NutrientLevelsAlgorithms.evaluateSalt(0.3f));
    }

    @Test
    public void givenMediumSaltLevel_whenEvaluatingSalt_thenReturnModerateNutrientLevelsQuantity() {
        assertEquals(NutrientLevelsQuantity.MODERATE, NutrientLevelsAlgorithms.evaluateSalt(1.4f));
    }

    @Test
    public void givenHighSaltLevel_whenEvaluatingSalt_thenReturnHighNutrientLevelsQuantity() {
        assertEquals(NutrientLevelsQuantity.HIGH, NutrientLevelsAlgorithms.evaluateSalt(2f));
    }
}
