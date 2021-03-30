package ca.ulaval.ima.ali_choix.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class NutrientLevelsAlgorithmsTest {
    @Test
    public void givenLowFatLevel_whenEvaluatingFat_thenReturnGoodNutrientQuality() {
        assertEquals(NutrientQuality.GOOD, NutrientLevelsAlgorithms.evaluateFat(1.1f));
    }

    @Test
    public void givenMediumFatLevel_whenEvaluatingFat_thenReturnModerateNutrientQuality() {
        assertEquals(NutrientQuality.MODERATE, NutrientLevelsAlgorithms.evaluateFat(6f));
    }

    @Test
    public void givenHighFatLevel_whenEvaluatingFat_thenReturnBadNutrientQuality() {
        assertEquals(NutrientQuality.BAD, NutrientLevelsAlgorithms.evaluateFat(25f));
    }

    @Test
    public void givenLowSaturatedFatLevel_whenEvaluatingSaturatedFat_thenReturnGoodNutrientQuality() {
        assertEquals(NutrientQuality.GOOD, NutrientLevelsAlgorithms.evaluateSaturatedFat(1f));
    }

    @Test
    public void givenMediumSaturatedFatLevel_whenEvaluatingSaturatedFat_thenReturnModerateNutrientQuality() {
        assertEquals(NutrientQuality.MODERATE, NutrientLevelsAlgorithms.evaluateSaturatedFat(4.5f));
    }

    @Test
    public void givenHighSaturatedFatLevel_whenEvaluatingSaturatedFat_thenReturnBadNutrientQuality() {
        assertEquals(NutrientQuality.BAD, NutrientLevelsAlgorithms.evaluateSaturatedFat(10f));
    }

    @Test
    public void givenLowSugarLevel_whenEvaluatingSugar_thenReturnGoodNutrientQuality() {
        assertEquals(NutrientQuality.GOOD, NutrientLevelsAlgorithms.evaluateSugar(4f));
    }

    @Test
    public void givenMediumSugarLevel_whenEvaluatingSugar_thenReturnModerateNutrientQuality() {
        assertEquals(NutrientQuality.MODERATE, NutrientLevelsAlgorithms.evaluateSugar(9.5f));
    }

    @Test
    public void givenHighSugarLevel_whenEvaluatingSugar_thenReturnBadNutrientQuality() {
        assertEquals(NutrientQuality.BAD, NutrientLevelsAlgorithms.evaluateSugar(13f));
    }

    @Test
    public void givenLowSaltLevel_whenEvaluatingSalt_thenReturnGoodNutrientQuality() {
        assertEquals(NutrientQuality.GOOD, NutrientLevelsAlgorithms.evaluateSalt(0.3f));
    }

    @Test
    public void givenMediumSaltLevel_whenEvaluatingSalt_thenReturnModerateNutrientQuality() {
        assertEquals(NutrientQuality.MODERATE, NutrientLevelsAlgorithms.evaluateSalt(1.4f));
    }

    @Test
    public void givenHighSaltLevel_whenEvaluatingSalt_thenReturnBadNutrientQuality() {
        assertEquals(NutrientQuality.BAD, NutrientLevelsAlgorithms.evaluateSalt(2f));
    }
}
